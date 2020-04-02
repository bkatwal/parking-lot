package org.bkatwal.parkinglot.cache;

/**
 * talks to cache server/cache applications
 */
public interface CacheService {

  <V> void put(String cacheName, String cacheKey, V value);

  <V> V getValue(String cacheName, String cacheKey);

  void remove(String cacheName, String cacheKey);

  void registerCache(String cacheName);

  void invalidateCache(String cacheName);

  void invalidateAllCache();
}
