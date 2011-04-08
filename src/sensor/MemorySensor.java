package sensor;

import java.lang.management.MemoryMXBean;
import java.lang.management.ManagementFactory;

public class MemorySensor {
	
	private MemoryMXBean memory;

	public MemorySensor() {
		memory = ManagementFactory.getMemoryMXBean();
	}

	public long getMemory(){
        return memory.getHeapMemoryUsage().getUsed();
	}
}
