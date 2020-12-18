package andre.batch.sample;

public class Counter {
	static Counter myOnlyInstance = new Counter();
	static final long MAX = 50;
	long readCounter = 0;
	long writeCounter = 0;
	private Counter() {
		
	}
	
	static Counter getInstance() {
		return myOnlyInstance;
	}
	
	synchronized void setReadCounter(long l) {
		myOnlyInstance.readCounter = l;
	}
	
	synchronized long getReadCounter() {
		return myOnlyInstance.readCounter;
	}

	synchronized long getWriteCounter() {
		return myOnlyInstance.writeCounter;
	}
	
	synchronized long retrieveAndIncrementReadCounter() {
		long result = myOnlyInstance.readCounter;
		if (myOnlyInstance.readCounter >= MAX) {
			result = -1;
			myOnlyInstance.readCounter = MAX;
		} else {
			myOnlyInstance.readCounter++;
		}
		return result;
	}
	
	synchronized void incrementWriteCounter() {
		myOnlyInstance.writeCounter++;
	}
}
