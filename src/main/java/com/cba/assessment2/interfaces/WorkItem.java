package com.cba.assessment2.interfaces;

/**
 * @author Manish
 * 
 *         Defines the abstract implementation for the WorkItem
 */
public interface WorkItem extends Runnable {
    /**
     * This method is used to execute the Work Item
     * 
     * @param callback
     */
    void execute(WorkItemCompletionCallback callback);
}
