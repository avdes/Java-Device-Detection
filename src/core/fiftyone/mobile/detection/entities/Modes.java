package fiftyone.mobile.detection.entities;

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
/**
 * The modes of operation the data set can be built in.
 * 
 * FILE: The device data is held on disk and loaded into memory when needed. 
 * Caching is used to clear out stale items. Lowest memory use and slowest 
 * device detection.
 * 
 * MEMORY: The device data is loaded into memory. Offers the fastest device 
 * detection in Java managed code, but a slower startup time.
 * 
 * MEMORY_MAPPED: The device data is loaded into memory as a byte array. 
 * Java class instances are created when needed and then cleared from the cache.
 */
public enum Modes {
    FILE,
    MEMORY,
    MEMORY_MAPPED
}
