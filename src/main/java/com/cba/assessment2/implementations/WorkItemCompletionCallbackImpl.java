package com.cba.assessment2.implementations;

import com.cba.assessment2.interfaces.WorkItemCompletionCallback;

/**
 * @author Manish
 * 
 *         This class is the implementation of WorkItemCompletionCallback
 *         Interface.
 *
 */
public class WorkItemCompletionCallbackImpl implements WorkItemCompletionCallback {

    /*
     * (non-Javadoc)
     * 
     * @see com.cba.assessment2.interfaces.WorkItemCompletionCallback#complete()
     */
    public void complete() {
	System.out.println("Hello, I'm the callback of " + Thread.currentThread().getName());
	System.out.println("***************************************");

    }

}
