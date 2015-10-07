/* *********************************************************************
 * This Source Code Form is copyright of 51Degrees Mobile Experts Limited. 
 * Copyright 2014 51Degrees Mobile Experts Limited, 5 Charlotte Close,
 * Caversham, Reading, Berkshire, United Kingdom RG4 7BY
 * 
 * This Source Code Form is the subject of the following patent 
 * applications, owned by 51Degrees Mobile Experts Limited of 5 Charlotte
 * Close, Caversham, Reading, Berkshire, United Kingdom RG4 7BY: 
 * European Patent Application No. 13192291.6; and 
 * United States Patent Application Nos. 14/085,223 and 14/085,301.
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
package fiftyone.mobile.detection.entities.headers;

import fiftyone.mobile.detection.readers.BinaryReader;

/**
 * Every list contains a standard initial header. This class provides the basic
 * properties needed to access lists irrespective of the storage implementation.
 */
public class Header {

    /**
     * The number of items contain in the collection.
     */
    private final int count;
    /**
     * The position in the file where the data structure starts.
     */
    private final int startPosition;
    /**
     * The number of bytes consumed by the data structure.
     */
    private final int length;

    /**
     * Constructs a new instance of Header
     *
     * @param reader Reader connected to the source data structure and
     * positioned to start reading
     */
    public Header(BinaryReader reader) {
        startPosition = reader.readInt32();
        length = reader.readInt32();
        count = reader.readInt32();
    }

    public int getCount() {
        return count;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getLength() {
        return length;
    }
}
