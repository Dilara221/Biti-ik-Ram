
public class _20010310006_PCB {
	private static int pidCounter = 1000; 
	private int pid;
	private String name;
    private int startTime;
    private int endTime;
    private int globalVariablesSize;
    private int codeSize;
    private int stackSize;
    private int heapSize;

    public _20010310006_PCB(String name, int startTime, int endTime, int globalVariablesSize, int codeSize, int stackSize, int heapSize) {
    	this.pid = pidCounter++; 
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.globalVariablesSize = globalVariablesSize;
        this.codeSize = codeSize;
        this.stackSize = stackSize;
        this.heapSize = heapSize;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }


    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getGlobalVariablesSize() {
        return globalVariablesSize;
    }

    public int getCodeSize() {
        return codeSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getHeapSize() {
        return heapSize;
    }
    
    
}


