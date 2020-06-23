package com.zy.storage.core;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public interface IStorage {
    <T> boolean save(String key,T value);
    <T> T get(String key);
}
