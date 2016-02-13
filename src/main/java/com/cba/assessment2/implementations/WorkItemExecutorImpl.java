package com.cba.assessment2.implementations;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.cba.assessment2.interfaces.WorkItem;
import com.cba.assessment2.interfaces.WorkItemExecutor;

/**
 * @author Manish
 *
 *         This class is the implementation of WorkItemExecutor Interface.
 */

public class WorkItemExecutorImpl implements WorkItemExecutor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cba.assessment2.interfaces.WorkItemExecutor#executeWorkItem(java.
     * util. Collection, int)
     */
    public void executeWorkItem(Collection<WorkItem> collWorkItem, int parallelism) {
	/**
	 * RejectedExecutionHandlerImpl is created to handle if the job is
	 * Rejected to be queued.
	 */
	RejectedExecutionHandlerImpl rejectedExecutionHandlerImpl = new RejectedExecutionHandlerImpl();

	/**
	 * 
	 * As per the requirement given in the assessment, even ExecutorServices
	 * can be used. However, I have preferred to use ThreadPoolExecutor,
	 * because it is more flexible in terms of control and options compared
	 * to ExecutorServices.
	 * 
	 * Also, call back can easily be achieved by overriding the
	 * afterExecute() method of ThreadPoolExecutor. However, I have not done
	 * it that way here.
	 * 
	 */
	ThreadPoolExecutor executorPool = new ThreadPoolExecutor(10, parallelism, 10, TimeUnit.SECONDS,
		new ArrayBlockingQueue<Runnable>(1000), rejectedExecutionHandlerImpl);
	try {
	    for (WorkItem workItem : collWorkItem) {
		executorPool.execute(workItem);
	    }

	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	// Shut down the pool after a silent period of 2 seconds.
	executorPool.shutdown();

    }

}
