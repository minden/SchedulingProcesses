
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args) {

        CSVReader csvReader = new CSVReader();
        ArrayList<Process> processes = csvReader.read();

        Schedule fcfsSchedule = FirstComeFirstServe.generateProcessSchedule(processes);
        Schedule hrrnSchedule = HighestResponseRatioNext.generateProcessSchedule(processes);
        Schedule spnSchedule = ShortestProcessNext.generateProcessSchedule(processes);
        Schedule srtSchedule = ShortestRemainingTime.generateProcessSchedule(processes);
        Schedule rrSchedule = RoundRobin.generateProcessSchedule(processes);

        for(Process process : processes){
            System.out.println(process.getProcessID() + ";" + "SRT;" + srtSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "FCFS;" + fcfsSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "HRRN;" + hrrnSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "RR10;" + rrSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "SPN;" + spnSchedule.getTurnAroundTime(process));
        }


    }

    public static void printProcesses(ArrayList<Process> processes) {
        for (Process process : processes) {
            System.out.println("ProcessID: " + process.getProcessID());
            System.out.println("  ProcessingTime: " + process.getProcessingTime());
            System.out.println("  ArrivalTime: " + process.getArrivalTime());
            System.out.println("  IOBlockProbability: " + process.getIOBlockProbability());
            System.out.println("  IOBlockTime: " + process.getIOBlockTime());
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }



}
