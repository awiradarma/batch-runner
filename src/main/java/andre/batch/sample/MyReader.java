package andre.batch.sample;

import java.io.Serializable;

import javax.batch.api.chunk.ItemReader;

public class MyReader implements ItemReader {

	//private long myCounter=0;
	private Counter counter = Counter.getInstance();
	
	@Override
	public Serializable checkpointInfo() throws Exception {
		long myCounter = counter.getReadCounter();
		System.out.println(Thread.currentThread().getName() + ":MyReader.checkpointInfo() is called, myCounter = " + myCounter);
		return myCounter;
	}

	@Override
	public void close() throws Exception {
		System.out.println(Thread.currentThread().getName() + ":MyReader.close() is called");

	}

	@Override
	public void open(Serializable arg0) throws Exception {
		if (arg0 == null) {
			System.out.println(Thread.currentThread().getName() + ":MyReader.open is called with a null argument");
		} else {
			counter.setReadCounter((Long) arg0);
			//myCounter = (Long) arg0;
			System.out.println(Thread.currentThread().getName() + ":MyReader.open is called with this argument " + arg0);
		}

	}

	@Override
	public Object readItem() throws Exception {
		long myCounter = counter.retrieveAndIncrementReadCounter();
		if (myCounter == -1) return null;

		String item = new String("Item:" + myCounter);
		System.out.println(Thread.currentThread().getName() + ":MyReader.readItem is called, myCounter is at " + myCounter);
		//myCounter++;
		//counter.incrementReadCounter();
		return item;
	}

}
