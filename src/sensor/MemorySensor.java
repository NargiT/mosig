package sensor;

import java.lang.management.MemoryMXBean;
import java.lang.management.ManagementFactory;

/**
 * 
 * Memory Sensor Provide the memory used by the operating system.
 * 
 * @author Tigran Tchougourian
 */
public class MemorySensor {

	/**
	 * Memory object from where the sensor pick data.
	 */
	private MemoryMXBean memory;

	/**
	 * Initialize the sensor using JMX Factory
	 */
	public MemorySensor() {
		memory = ManagementFactory.getMemoryMXBean();
	}

	/**
	 * 
	 * @return the (heap) memory used by the operating system
	 */
	public long getMemory() {
		return memory.getHeapMemoryUsage().getUsed();
	}

}
