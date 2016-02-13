package com.cba.assessment2.implementations;

import com.cba.assessment2.interfaces.WorkItem;
import com.cba.assessment2.interfaces.WorkItemCompletionCallback;

/**
 * @author Manish
 * 
 *         This class is the implementation of WorkItem Interface.
 *
 */
public class WorkItemImpl implements WorkItem {

    private WorkItemCompletionCallback callback;

    public WorkItemImpl(WorkItemCompletionCallback callback) {
	this.callback = callback;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	System.out.println("***************************************");
	System.out.println(Thread.currentThread().getName() + " Started..");
	System.out.println(Thread.currentThread().getName() + " Ended..");
	// Calling the CallBack method
	execute(callback);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cba.assessment2.interfaces.WorkItem#execute(com.cba.assessment.
     * interfaces.WorkItemCompletionCallback)
     */
    public void execute(WorkItemCompletionCallback callback) {
	callback.complete();
    }

}
