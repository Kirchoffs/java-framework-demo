package org.syh.demo.spring.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class ValueValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String name = parameters.getString("name");
        if (name.equals("akira")) {
            throw new JobParametersInvalidException("name value is not allowed");
        }
    }
}
