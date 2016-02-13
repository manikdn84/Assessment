package com.cba.test.assessment1;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.cba.assessment1.implementations.InMemoryCache;

/**
 * @author Manish
 * 
 *         This class is used to test the functioning of the InMemoryCache.
 *
 */
public class TestInMemoryCache {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws InterruptedException {

	TestInMemoryCache testInMemoryCache = new TestInMemoryCache();

	logger.log(Level.INFO, "\n\n==========Test1: Test for Adding/Removing Objects ==========");
	testInMemoryCache.addRemoveObjects();
	logger.log(Level.INFO, "\n\n==========Test2: Test for ObjectsCleanupTime ==========");
	testInMemoryCache.cleanupTime();
    }

    /**
     * This method tests adding and removing objects to the cache.
     */
    private void addRemoveObjects() {

	/**
	 * Test with timeToLiveInSeconds = 200 seconds
	 * 
	 * TimerIntervalInSeconds = 500 seconds
	 */

	InMemoryCache<String, String> cache = new InMemoryCache<String, String>(200, 500);

	cache.put("Mani", "Mani");
	cache.put("Deepa", "Deepa");
	cache.put("Varshan", "Varshan");
	cache.put("Hari", "Hari");
	cache.put("Teju", "Teju");
	cache.put("Kria", "Kria");

	logger.log(Level.INFO, "Six objects are added.. Cache size is : " + cache.size());
	cache.remove("Kria");
	logger.log(Level.INFO, "One object is removed.. Cache size is : " + cache.size());

	cache.put("Sanvi", "Sanvi");
	cache.put("Minnu", "Minnu");
	logger.log(Level.INFO, "Again two objects are added.. Cache size is : " + cache.size());

    }

    /**
     * This method adds 50000 objects and makes sure that these objects are
     * living in the cache for more than 10 secs and the Deamon thread which
     * kick starts, evicts the objects whose ttl are breached.
     * 
     * @throws InterruptedException
     */
    private void cleanupTime() throws InterruptedException {
	int size = 500000;

	/**
	 * Test with timeToLiveInSeconds = 10 seconds
	 * 
	 * TimerIntervalInSeconds = 100 seconds
	 */

	InMemoryCache<String, String> cache = new InMemoryCache<String, String>(10, 100);

	for (int i = 0; i < size; i++) {
	    String value = Integer.toString(i);
	    cache.put(value, value);
	}

	Thread.sleep(200);

	long start = System.currentTimeMillis();
	cache.cleanup();
	double finish = (double) (System.currentTimeMillis() - start) / 1000.0;

	logger.log(Level.INFO, "Total time for cleaning up " + size + " objects is " + finish + " s");

    }

}
