package com.cba.assessment2.interfaces;

import java.util.Collection;

/**
 * @author Manish
 *
 *         Defines the abstract implementation for the WorkItemExecutor
 */
public interface WorkItemExecutor {
    /**
     * This method is used to execute collection of work items
     * 
     * @param collWorkItem
     * @param parallelism
     */
    void executeWorkItem(Collection<WorkItem> collWorkItem, int parallelism);
}
