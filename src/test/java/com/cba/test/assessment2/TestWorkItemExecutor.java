package com.cba.test.assessment2;

import java.util.ArrayList;
import java.util.Collection;

import com.cba.assessment2.implementations.WorkItemCompletionCallbackImpl;
import com.cba.assessment2.implementations.WorkItemExecutorImpl;
import com.cba.assessment2.implementations.WorkItemImpl;
import com.cba.assessment2.interfaces.WorkItem;

/**
 * @author Manish
 * 
 *         This is the test class to test the assignment.
 *
 */
public class TestWorkItemExecutor {

    public static void main(String[] args) {
	Collection<WorkItem> workItemCollection = new ArrayList<WorkItem>();

	/**
	 * Using the For loop below, any number of Work Items can be created and
	 * added to the List.
	 */
	for (int i = 0; i < 100; i++) {
	    workItemCollection.add(new WorkItemImpl(new WorkItemCompletionCallbackImpl()));
	}

	// Creating and Calling the Framework to execute the WorkItems.
	WorkItemExecutorImpl workItemExecutor = new WorkItemExecutorImpl();
	workItemExecutor.executeWorkItem(workItemCollection, 20);

    }

}
