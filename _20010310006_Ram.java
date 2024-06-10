

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _20010310006_Ram {
    private int startAddress;
    private int endAddress;
    private Map<Integer, Integer> memoryMap;

    public _20010310006_Ram(int startAddress, int endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.memoryMap = new HashMap<>();
       
        allocateMemory(0, 1000);
       
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(int endAddress) {
        this.endAddress = endAddress;
    }

    public int getSize() {
        return endAddress - startAddress + 1;
    }

    public boolean allocateMemory(int processId, int requiredSize) {
       
        if (processId == -1) {
            memoryMap.put(startAddress, requiredSize); 
            return true;
        }

      
        int remainingSize = endAddress - startAddress + 1 - requiredSize;

       
        List<Integer> emptySpaces = new ArrayList<>();
        emptySpaces.add(startAddress); 
       
        for (Map.Entry<Integer, Integer> entry : memoryMap.entrySet()) {
            int allocatedStartAddress = entry.getKey();
            int allocatedSize = entry.getValue();
            emptySpaces.add(allocatedStartAddress + convertKilobytesToBytes(allocatedSize)); 
        }
        emptySpaces.add(endAddress + 1); 

        for (int i = 0; i < emptySpaces.size() - 1; i++) {
            int currentStart = emptySpaces.get(i);
            int nextStart = emptySpaces.get(i + 1);
            int currentEmptySpaceSize = nextStart - currentStart;

           
            if (currentEmptySpaceSize >= requiredSize) {
                memoryMap.put(currentStart, requiredSize);
                return true;
            }
        }

        return false;
    }
 void deallocateMemory(int processId) {
        memoryMap.remove(processId);
    }

   
    public int convertKilobytesToBytes(int kilobytes) {
        return kilobytes * 1024;
    }
}
