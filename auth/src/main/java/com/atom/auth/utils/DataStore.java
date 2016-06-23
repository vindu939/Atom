package com.atom.auth.utils;

import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by aravindp on 21/6/16.
 */
public class DataStore {

    public static FileDataStoreFactory getFileDataStore(String filePath) throws IOException {
        File credentialDirectory = new File(filePath);
        credentialDirectory.setReadable(true, true);
        credentialDirectory.setWritable(true, true);
        return new FileDataStoreFactory(credentialDirectory);
    }
}
