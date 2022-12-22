package org.syh.demo.spring.springbatch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepResultListener implements StepExecutionListener {
    private String stepName;

    public StepResultListener(String stepName) {
        this.stepName = stepName;
    }

    @Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Called beforeStep() of " + stepName + ".");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("Called afterStep() of " + stepName + ".");
		return ExitStatus.COMPLETED;
	}
}
