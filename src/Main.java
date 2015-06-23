import java.lang.*;
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args){
        CSVReader csvReader = new CSVReader();
        ArrayList<Process> processes = csvReader.read();
        printProcesses(processes);
        ArrayList<ScheduleItem> FCFSSchedule = FirstComeFirstServe.generateProcessSchedule(processes);
        printSchedule(FCFSSchedule);

    }

    public static void printProcesses(ArrayList<Process> processes){
        for(Process process: processes){
            System.out.println("ProcessID: " + process.getProcessID());
            System.out.println("  ProcessingTime: " + process.getProcessingTime());
            System.out.println("  ArrivalTime: " + process.getArrivalTime());
            System.out.println("  IOBlockProbability: " + process.getIOBlockProbability());
            System.out.println("  IOBlockTime: " + process.getIOBlockTime());
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

    public static void printSchedule(ArrayList<ScheduleItem> schedule){
        for(ScheduleItem item: schedule){
            System.out.println("ProcessID: " + item.getItemID());
            System.out.println("  ProcessStartTime: " + item.getStartTime());
            System.out.println("  ProcessEndTime: " + item.getEndTime());
            System.out.println("  ProcessFinished: " + item.getFinished());
            System.out.println();
        }
        System.out.println("----------------------------------------------");

    }
}
