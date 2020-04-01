package org.bkatwal.parkinglot.cache;

public interface CacheService<K, V> {

  void put(String cacheName, K cacheKey, V value);

  V getValue(String cacheName, K cacheKey);
}
