package rentalsystemtest.workertest;

import rentalsystem.worker.*;
import rentalsystemtest.MockWorker;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rentalsystem.Vehicle;

public abstract class WorkerTest {
	protected Worker w;
	protected Vehicle v;

	protected abstract Worker createWorker();

	@BeforeEach
	void setUp() {
		this.w = createWorker();
		this.v = new MockVehicle(1);
	}

	@Test
	/* Abstract method effect is called when a worker visits a Vehicle */
	void EffectIsCalledWhenWorkerVisits() {
		MockWorker mockW = new MockWorker("Wario");
		assertTrue(mockW.effectCalled == 0);
		mockW.visit(v);
		assertTrue(mockW.effectCalled == 1);

	}

}
