package test.andre;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.StepExecution;

import org.jberet.runtime.JobExecutionImpl;
import org.jberet.runtime.StepExecutionImpl;

public class BatchRunner {

    long jobExecutionId;
    static JobOperator jobOperator = BatchRuntime.getJobOperator();
    
    Properties params = new Properties();
    JobExecutionImpl jobExecution; 
    long jobTimeout;

    List<StepExecution> stepExecutions;
    StepExecutionImpl stepExecution0;


    protected void startJob(final String jobXml) {
        jobExecutionId = jobOperator.start(jobXml, params);
        jobExecution = (JobExecutionImpl) jobOperator.getJobExecution(jobExecutionId);
    }

    protected void awaitTermination(final JobExecutionImpl... exes) throws InterruptedException {
        final JobExecutionImpl exe = exes.length == 0 ? jobExecution : exes[0];
        exe.awaitTermination(getJobTimeoutSeconds(), TimeUnit.SECONDS);
        stepExecutions = jobOperator.getStepExecutions(exe.getExecutionId());
        if (!stepExecutions.isEmpty()) {
            stepExecution0 = (StepExecutionImpl) stepExecutions.get(0);
        }
    }protected long getJobTimeoutSeconds() {
        return jobTimeout;
    }

    protected void startJobAndWait(final String jobXml) throws Exception {
        startJob(jobXml);
        awaitTermination();
    }


    public static void main(String[] args) throws Exception {

        BatchRunner runner = new BatchRunner();
        runner.startJobAndWait(args[0]);
//        jobOperator = BatchRuntime.getJobOperator();
//        Properties jobParameters = new Properties();
    
//        long executionId = -1;
//        executionId = jobOperator.start(args[0], jobParameters);
//        System.out.println("Started job : " + executionId);
    }


}
