package com.cba.assessment2.implementations;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Manish
 * 
 *         This class is the handler implementation for tasks that cannot be
 *         executed by a ThreadPoolExecutor.
 *
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang
     * .Runnable, java.util.concurrent.ThreadPoolExecutor)
     */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
	System.out.println(r.toString() + " is rejected. No Problem I will handle it..");
    }

}