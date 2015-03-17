package com.webapp.utils.akka;

import java.net.URL;
import java.util.Arrays;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class EhcacheUtils {

	public static void main(String[] args) {

		EhcacheUtils cache = EhcacheUtils.getInstance();

		cache.showCacheName();

		cache.put("key", "val");
		Object object = cache.get("key");

		System.out.println(object);

	}

	private CacheManager cacheManager;
	private ThreadLocal<String> local = new ThreadLocal<String>();

	private EhcacheUtils() {
		URL url = EhcacheUtils.class.getResource("/ehcache.xml");
		cacheManager = CacheManager.create(url);
	}
	private static class SingleHolder {
		private static EhcacheUtils instance = new EhcacheUtils();
	}

	public static EhcacheUtils getInstance() {
		return SingleHolder.instance;
	}

	public EhcacheUtils setDefaultCache(String cacheName) {
	    local.set(cacheName);
	    return this;
    }

	public void addCache(Cache cache) {
	    cacheManager.addCache(cache);
    }
	public void addCache(CacheConfiguration cacheConfiguration) {
	    Cache cache = new Cache(cacheConfiguration);
	    cacheManager.addCache(cache);
    }

	public void addCache(String cacheName) {
	    cacheManager.addCache(cacheName);
    }

	public Cache getCache(String cacheName) {
		return cacheManager.getCache(cacheName);
    }

	public void put(Element element) {
		Cache cache = cacheManager.getCache(defaultCache());
	    cache.put(element);
    }
	public void put(Object key, Object val) {
	    Cache cache = cacheManager.getCache(defaultCache());
	    Element element = new Element(key, val);
	    cache.put(element);
    }

	public void put(String cacheName, Element element) {
	    Cache cache = cacheManager.getCache(cacheName);
	    cache.put(element);
    }
	public void put(String cacheName, Object key, Object val) {
	    Cache cache = cacheManager.getCache(cacheName);
	    Element element = new Element(key, val);
	    cache.put(element);
    }

	public Object get(Object key) {
	    Element element = cacheManager.getCache(defaultCache()).get(key);
	    return element.getObjectValue();
    }

	public Object get(String cacheName, Object key) {
	    Element element = cacheManager.getCache(cacheName).get(key);
	    return element.getObjectValue();
    }

	public boolean remove(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        return cache.remove(key);
    }

	public boolean remove(String key) {
        Cache cache = cacheManager.getCache(defaultCache());
        return cache.remove(key);
    }

	private String defaultCache() {
		String defaultCache = local.get();
		if(defaultCache == null && !cacheManager.cacheExists("cache_default")){
			cacheManager.addCache("cache_default");
		}
		return "cache_default";
    }

	public void showCacheName() {
	    String[] cacheNames = cacheManager.getCacheNames();
	    System.out.println(Arrays.toString(cacheNames));
    }

}
