package rentalsystemtest.workertest;

import rentalsystem.worker.*;

public class RepairerTest extends WorkerTest {

	@Override
	protected Worker createWorker() {

		return new Repairer("Luigi");
	}

}
