/* *********************************************************************
 * This Source Code Form is copyright of 51Degrees Mobile Experts Limited. 
 * Copyright © 2017 51Degrees Mobile Experts Limited, 5 Charlotte Close,
 * Caversham, Reading, Berkshire, United Kingdom RG4 7BY
 * 
 * This Source Code Form is the subject of the following patents and patent
 * applications, owned by 51Degrees Mobile Experts Limited of 5 Charlotte
 * Close, Caversham, Reading, Berkshire, United Kingdom RG4 7BY: 
 * European Patent No. 2871816;
 * European Patent Application No. 17184134.9;
 * United States Patent Nos. 9,332,086 and 9,350,823; and
 * United States Patent Application No. 15/686,066.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0.
 * 
 * If a copy of the MPL was not distributed with this file, You can obtain
 * one at http://mozilla.org/MPL/2.0/.
 * 
 * This Source Code Form is "Incompatible With Secondary Licenses", as
 * defined by the Mozilla Public License, v. 2.0.
 * ********************************************************************* */
package fiftyone.mobile.detection.factories.stream;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.entities.Node;
import fiftyone.mobile.detection.IndirectDataset;
import fiftyone.mobile.detection.factories.NodeFactoryV32;
import fiftyone.mobile.detection.readers.BinaryReader;
import java.io.IOException;

/**
 * Factory used to create stream Entities.Node entities of version 3.2.
 * <p>
 * Objects of this class should not be created directly as they are part of the 
 * internal logic.
 */
public class NodeStreamFactoryV32 extends NodeFactoryV32 {

    /**
     * Constructs a new instance of NodeStreamFactoryV32.
     */
    public NodeStreamFactoryV32() {}

    /**
     * Constructs a new "Entities.Stream.NodeV32 entity from the offset 
     * provided.
     * 
     * @param dataSet The data set the node is contained within.
     * @param index The offset in the data structure to the node.
     * @param reader  Reader connected to the source data structure and 
     * positioned to start reading.
     * @return A new Entities.Node entity from the data set.
     * @throws java.io.IOException if there was a problem accessing data file.
     */
    @Override
    protected Node construct(Dataset dataSet, int index, BinaryReader reader) 
                                                            throws IOException {
        return new fiftyone.mobile.detection.entities.stream.NodeV32(
                (IndirectDataset)dataSet,
                index, 
                reader);
    }
    
}
