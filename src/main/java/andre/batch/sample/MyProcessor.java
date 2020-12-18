package andre.batch.sample;

import javax.batch.api.chunk.ItemProcessor;

public class MyProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object arg0) throws Exception {
		Thread.sleep(250);
		System.out.println(Thread.currentThread().getName() + ":Processing " + arg0);// TODO Auto-generated method stub
		return new String(arg0.toString() + " processed.");
	}

}
