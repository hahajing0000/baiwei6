package com.zy.storage.core.impl;

import com.zy.storage.core.IStorage;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public class FileStorage implements IStorage {
    @Override
    public <T> boolean save(String key, T value) {
        return false;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }
}
