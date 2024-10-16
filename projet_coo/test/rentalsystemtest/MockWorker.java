package rentalsystemtest;

import rentalsystem.Vehicle;
import rentalsystem.worker.Worker;

public class MockWorker extends Worker {
	public int effectCalled = 0;

	public MockWorker(String name) {
		super(name);
	}

	@Override
	public void effect(Vehicle v) {
		this.effectCalled++;
	}

}
