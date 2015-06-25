
import java.lang.*;
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args) {

        CSVReader csvReader = new CSVReader();
        ArrayList<Process> processes = csvReader.read();
        //printProcesses(processes);

        //Schedule fcfsSchedule = FirstComeFirstServe.generateProcessSchedule(processes);
        //fcfsSchedule.printSchedule();
        //System.out.println(fcfsSchedule.getTurnAroundTime(processes.get(0)));

        //Schedule hrrnSchedule = HighestResponseRatioNext.generateProcessSchedule(processes);
        //hrrnSchedule.printSchedule();
        //System.out.println(hrrnSchedule.getTurnAroundTime(processes.get(0)));

        //Schedule spnSchedule = ShortestProcessNext.generateProcessSchedule(processes);
        //spnSchedule.printSchedule();

        //Schedule srtSchedule = ShortestRemainingTime.generateProcessSchedule(processes);
        //srtSchedule.printSchedule();

        Schedule rrSchedule = RoundRobin.generateProcessSchedule(processes);
        rrSchedule.printSchedule();


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
