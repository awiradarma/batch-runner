package andre.batch.sample;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;

public class MyWriter implements ItemWriter {

	private long writerCounter=0;
	@Override
	public Serializable checkpointInfo() throws Exception {
		System.out.println(Thread.currentThread().getName() + ":MyWriter.checkpointInfo called, writerCounter is at " + writerCounter);
		return writerCounter;
	}

	@Override
	public void close() throws Exception {
		System.out.println(Thread.currentThread().getName() + ":MyWriter.close is called, writerCounter is at " + writerCounter);
		
	}

	@Override
	public void open(Serializable arg0) throws Exception {
		if (arg0 == null) {
			System.out.println(Thread.currentThread().getName() + ":MyWriter.open is called with a null argument, starting with 0");
		} else {
			writerCounter = (Long) arg0;
			System.out.println(Thread.currentThread().getName() + ":MyWriter.open is called with argument : " + writerCounter);
		}

	}

	@Override
	public void writeItems(List<Object> arg0) throws Exception {
		for (Object object : arg0) {
			System.out.println(Thread.currentThread().getName() + ":Writing out: " + object.toString());
			writerCounter++;
		}

	}

}
