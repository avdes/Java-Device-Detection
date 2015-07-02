package fiftyone.mobile.detection.entities.stream;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.entities.IntegerEntity;
import fiftyone.mobile.detection.entities.NodeIndex;
import fiftyone.mobile.detection.factories.NodeFactoryShared;
import fiftyone.mobile.detection.readers.BinaryReader;
import fiftyone.properties.DetectionConstants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Node which can be used with the Stream data set. NumericChidren 
 * and RankedSignatureIndexes are not loaded into memory when the entity is 
 * constructed, they're only loaded from the data source when requested.
 */
public class NodeV32 extends Node {

    /**
     * A list of all the signature indexes that relate to this node.
     */
    private int[] rankedSignatureIndexes;
    
    /**
     * Constructs a new instance of NodeV32.
     * @param dataSet The data set the node is contained within.
     * @param offset The offset in the data structure to the node.
     * @param reader Reader connected to the source data structure and 
     * positioned to start reading.
     */
    public NodeV32(fiftyone.mobile.detection.entities.stream.Dataset dataSet, 
                    int offset, BinaryReader reader) {
        super(dataSet, offset, reader);
    }

    /**
     * Reads the ranked signature count from a 2 byte ushort.
     * @param reader Reader connected to the source data structure and 
     * positioned to start reading.
     * @return The count of ranked signatures associated with the node.
     */
    @Override
    public int readerRankedSignatureCount(BinaryReader reader) {
        return reader.readUInt16();
    }

    /**
     * Used by the constructor to read the variable length list of child node 
     * indexes associated with the node. Returns node indexes from V32 data 
     * format.
     * @param dataSet The data set the node is contained within.
     * @param reader Reader connected to the source data structure and 
     * positioned to start reading.
     * @param offset The offset in the data structure to the node.
     * @param count The number of node indexes that need to be read.
     * @return An array of child node indexes for the node.
     */
    @Override
    protected NodeIndex[] readNodeIndexes(Dataset dataSet, BinaryReader reader, 
            int offset, int count) {
        return NodeFactoryShared.readNodeIndexesV32(dataSet, reader, 
                                                    offset, count);
    }

    /**
     * Returns a list of all the signature indexes that relate to this node.
     * @return a list of all the signature indexes that relate to this node.
     */
    @Override
    public int[] getRankedSignatureIndexes() {
        if (rankedSignatureIndexes == null) {
            synchronized (this) {
                if (rankedSignatureIndexes == null) {
                    rankedSignatureIndexes = getRankedSignatureIndexesAsArray();
                }
            }
        }
        return rankedSignatureIndexes;
    }
    
    /**
     * Gets the ranked signature indexes array for the node.
     * @return An array of length _rankedSignatureCount filled with ranked 
     * signature indexes.
     */
    private int[] getRankedSignatureIndexesAsArray() {
        int[] rsi = null;
        if (signatureCount == 0) {
            rsi = new int[0];
        } else {
            BinaryReader reader = null;
            try {
                reader = pool.getReader();
                
                // Position the reader after the numeric children.
                reader.setPos(position + ((DetectionConstants.SIZE_OF_SHORT + 
                                           DetectionConstants.SIZE_OF_INT) * 
                                                getNumericChildrenLength()));
                
                // Read the index.
                int index = reader.readInt32();
                
                if (signatureCount == 1) {
                    // If the count is one then the value is the 
                    // ranked signature index.
                    rsi = new int[signatureCount];
                    rsi[0] = index;
                } else {
                    // If the count is greater than one then the value is the 
                    // index of the first ranked signature index in the merged 
                    // list.
                    Iterable<IntegerEntity> range = 
                            dataSet.getNodeRankedSignatureIndexes()
                                    .getRange(index, signatureCount);
                    
                    //Determine the number of elements in the rsi array.
                    int arraySize = 0;
                    while (range.iterator().hasNext()) {
                        arraySize++;
                    }
                    
                    //Fill the array with values.
                    int currentIndex = 0;
                    rsi = new int[arraySize];
                    while (range.iterator().hasNext()) {
                        IntegerEntity ie = range.iterator().next();
                        rsi[currentIndex] = ie.getValue();
                        currentIndex++;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(
                        NodeV32.class.getName()).log(Level.SEVERE, null, ex
                        );
            } finally {
                if (reader != null)
                    pool.release(reader);
            }
        }
        return rsi;
    }
}
