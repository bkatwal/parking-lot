package org.bkatwal.parkinglot.cache;

import static org.bkatwal.parkinglot.utils.ParkingLotUtils.uncheckedCast;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceImpl implements CacheService {

  private static Map<String, Map<String, Object>> cache = new ConcurrentHashMap<>();

  @Override
  public <V> void put(final String cacheName, final String cacheKey, final V value) {
    cache.getOrDefault(cacheName, new ConcurrentHashMap<>()).put(cacheKey, value);
  }

  @Override
  public <V> V getValue(final String cacheName, final String cacheKey) {
    Map<String, Object> cacheMap = cache.get(cacheName);
    if (cacheMap == null) {
      return null;
    }
    return uncheckedCast(cacheMap.get(cacheKey));
  }

  @Override
  public void remove(String cacheName, String cacheKey) {
    Map<String, Object> cacheMap = cache.get(cacheName);
    if (cacheMap == null) {
      return;
    }
    cacheMap.remove(cacheKey);
  }

  @Override
  public void registerCache(final String cacheName) {
    cache.put(cacheName, new ConcurrentHashMap<>());
  }

  @Override
  public void invalidateCache(String cacheName) {
    Map<String, Object> cacheMap = cache.get(cacheName);
    if (cacheMap == null) {
      return;
    }
    cache.get(cacheName).clear();
  }

  @Override
  public void invalidateAllCache() {
    cache.clear();
  }
}
