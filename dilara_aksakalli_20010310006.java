import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dilara_aksakalli_20010310006 {

    public static void main(String[] args) {
        List<_20010310006_PCB> programs = new ArrayList<>();
        
        try {
            File file = new File("girdi.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                String name = parts[0];
                int startTime = Integer.parseInt(parts[1]);
                int endTime = Integer.parseInt(parts[2]);
                int globalVariablesSize = Integer.parseInt(parts[3]);
                int codeSize = Integer.parseInt(parts[4]);
                int stackSize = Integer.parseInt(parts[5]);
                int heapSize = Integer.parseInt(parts[6]);

                _20010310006_PCB program = new _20010310006_PCB(name, startTime, endTime, globalVariablesSize, codeSize, stackSize, heapSize);
                programs.add(program);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }

        _20010310006_Ram ram = new _20010310006_Ram(0, 1000000); 
        ram.allocateMemory(0, 1000);
        for (_20010310006_PCB program : programs) {
            allocateProgramToRAM(ram, program); 
        }

      

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Lütfen RAM’in durumunu görmek istediğiniz saniyeyi giriniz:");
        int desiredSecond = inputScanner.nextInt();

        printMemoryStatusAtSecond(ram, programs, desiredSecond, inputScanner);

        inputScanner.close();
    }


    public static void allocateProgramToRAM(_20010310006_Ram ram, _20010310006_PCB program) {
        boolean allocated = ram.allocateMemory(program.getPid(), program.getGlobalVariablesSize() + program.getCodeSize() + program.getStackSize() + program.getHeapSize());
        if (allocated) {
            System.out.println(program.getName() + " programı RAM'e başarıyla yerleştirildi.");
        } else {
            System.out.println(program.getName() + " programı için yeterli boş alan bulunamadı.");
        }
    }
    public static int convertKilobytesToBytes(int kilobytes) {
        return kilobytes * 1024;
    }
    public static void printMemoryStatusAtSecond(_20010310006_Ram ram, List<_20010310006_PCB> programs, int second, Scanner inputScanner) {
        System.out.println(second + ". Saniyede RAM’in dolu olan kısımları:");

        boolean isAnyProcessFound = false;

        for (_20010310006_PCB program : programs) {
            if (program.getStartTime() <= second && program.getEndTime() >= second) {
                isAnyProcessFound = true;
                int startAddress = ram.convertKilobytesToBytes(1000); 
                for (_20010310006_PCB p : programs) {
                    if (p.getPid() < program.getPid() && p.getEndTime() >= second) {
                       
                        startAddress += ram.convertKilobytesToBytes(p.getGlobalVariablesSize() + p.getCodeSize() + p.getStackSize() + p.getHeapSize());
                    }
                }
                int endAddress =startAddress +ram.convertKilobytesToBytes( program.getGlobalVariablesSize() + program.getCodeSize() + program.getStackSize() + program.getHeapSize() )-1;
                System.out.println(startAddress + ". Ve " + endAddress + ". Adresler arasında " + program.getName() + " programı bulunmaktadır.");
            }
        }

        if (!isAnyProcessFound) {
            System.out.println("RAM'de herhangi bir program bulunmamaktadır.");
        }

        System.out.print("PCB’si bulunan Prosesler: ");
        for (_20010310006_PCB program : programs) {
            if (program.getStartTime() <= second && program.getEndTime() >= second) {
                System.out.print(program.getName() + " ");
            }
        }
        System.out.println();

        System.out.println(second + ". saniyedeki PCB’sini görüntülemek istediğiniz proses ismini giriniz:");
        String processName = inputScanner.next();

        printPCBAtSecond(programs, second, processName);
    }

    public static void printPCBAtSecond(List<_20010310006_PCB> programs, int second, String processName) {
        for (_20010310006_PCB program : programs) {
            if (program.getName().equalsIgnoreCase(processName) && program.getStartTime() <= second && program.getEndTime() >= second) {
                System.out.println(processName + " isimli prosesin " + second + ". Saniyedeki PCB bilgileri şu şekildedir:");
                System.out.println("Proses numarası: " + program.getPid());
                System.out.println("Prosesin yaratılma zamanı: " + program.getStartTime() + ". saniye");
                System.out.println("Prosesin toplam büyüklüğü: " + (program.getGlobalVariablesSize() + program.getCodeSize() + program.getStackSize() + program.getHeapSize()) + " KB");
                return;
            }
        }
        System.out.println("Belirtilen isimde bir proses bulunamadı veya istenen saniyede bu proses çalışmamıştır.");
    }
}
