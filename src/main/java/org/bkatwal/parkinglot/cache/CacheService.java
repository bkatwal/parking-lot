package org.bkatwal.parkinglot.cache;

public interface CacheService {

  <V> void put(String cacheName, String cacheKey, V value);

  <V> V getValue(String cacheName, String cacheKey);

  void registerCache(String cacheName);
}
