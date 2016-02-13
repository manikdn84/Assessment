package com.cba.assessment1.implementations;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Manish This class acts as a In Memory Cache considering the following
 *         test cases.
 *
 *         The TTL of the items is fairly short (~10 seconds).
 * 
 *         The system has plenty of memory.
 * 
 *         The keys are widely distributed across the key-space.
 * 
 *         Each item may or may not be accessed during the TTL.
 * 
 *         Each item might be accessed multiple times during the TTL
 * @param <K>
 * @param <T>
 */
public class InMemoryCache<K, T> {

    private long timeToLive;
    private ConcurrentHashMap<K, CacheObject> cacheMap;

    private class CacheObject {
	private long lastAccessed = System.currentTimeMillis();
	private T value;

	private CacheObject(T value) {
	    this.value = value;
	}

	public long getLastAccessed() {
	    return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
	    this.lastAccessed = lastAccessed;
	}
    }

    public InMemoryCache(long ttl, final long timerInterval) {
	this.timeToLive = ttl * 1000;
	this.cacheMap = new ConcurrentHashMap<K, CacheObject>();

	if (timeToLive > 0 && timerInterval > 0) {

	    Thread t = new Thread(new Runnable() {
		public void run() {
		    while (true) {
			try {
			    Thread.sleep(timerInterval * 1000);
			} catch (InterruptedException ex) {
			}
			cleanup();
		    }
		}
	    });

	    t.setDaemon(true);
	    t.start();
	}
    }

    /**
     * Maps the specified key to the specified value in this table. Neither the
     * key nor the value can be null.
     * 
     * The value can be retrieved by calling the get method with a key that is
     * equal to the original key.
     * 
     * @param key
     * @param value
     */
    public void put(K key, T value) {
	cacheMap.put(key, new CacheObject(value));
    }

    /**
     * Returns the value of the Object in the Cache.
     * 
     * @param key
     * @return
     */
    public T get(K key) {
	CacheObject c = (CacheObject) cacheMap.get(key);

	if (c == null)
	    return null;
	else {
	    c.setLastAccessed(System.currentTimeMillis());
	    return c.value;
	}

    }

    /**
     * Removes the key (and its corresponding value) from this map. This method
     * does nothing if the key is not in the map.
     * 
     * @param key
     */
    public void remove(K key) {
	cacheMap.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this map. If the map contains
     * more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * 
     * @return
     */
    public int size() {
	return cacheMap.size();
    }

    /**
     * This method evicts the Objects which breach the ttl
     */
    public void cleanup() {

	long now = System.currentTimeMillis();
	ArrayList<K> keysToBeEvicted = null;

	keysToBeEvicted = new ArrayList<K>((cacheMap.size() / 2) + 1);

	for (Map.Entry<K, CacheObject> entry : cacheMap.entrySet()) {
	    if (now > (timeToLive + entry.getValue().getLastAccessed())) {
		keysToBeEvicted.add(entry.getKey());
	    }
	}

	for (K key : keysToBeEvicted) {
	    cacheMap.remove(key);
	    Thread.yield();
	}
    }
}