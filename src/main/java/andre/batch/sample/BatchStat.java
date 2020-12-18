package andre.batch.sample;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;

public class BatchStat {

	public static void main(String[] args) {
		JobOperator o = BatchRuntime.getJobOperator();
		
		long executionId = -1;

		try {
			executionId = Long.parseLong(args[0]);
		} catch (NumberFormatException notRestart) {
		}
		
		JobExecution e = o.getJobExecution(executionId);
		System.out.println("Job ID: " + executionId);
		System.out.println("Job Status: "+ e.getBatchStatus().toString());
		System.out.println("Job Name: "+e.getJobName());
		System.out.println("Exit Status: " + e.getExitStatus());
		System.out.println("Create Time: "+e.getCreateTime());
		System.out.println("End Time: "+e.getEndTime());
		System.out.println("Start Time: "+ e.getStartTime());
		System.out.println("Last Update: "+e.getLastUpdatedTime());
		// TODO Auto-generated method stub

	}

}
