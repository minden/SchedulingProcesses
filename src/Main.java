
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args) {

        CSVReader csvReader = new CSVReader();
        ArrayList<Process> processes = csvReader.read();
        System.out.println("Reading CSV file finished");

        SchedulingAlgorithm fcfsAlg = new FirstComeFirstServe(processes);
        Schedule fcfsSchedule = fcfsAlg.generateProcessSchedule();
        System.out.println("FCFS done");

        SchedulingAlgorithm hrrnAlg = new HighestResponseRatioNext(processes);
        Schedule hrrnSchedule= hrrnAlg.generateProcessSchedule();
        System.out.println("HRRN done");

        //Schedule spnSchedule = ShortestProcessNext.generateProcessSchedule(processes);
        //System.out.println("SPN done");

        SchedulingAlgorithm srtAlg = new ShortestRemainingTime(processes);
        Schedule srtSchedule = srtAlg.generateProcessSchedule();
        System.out.println("SRT done");

        SchedulingAlgorithm rrAlg = new RoundRobin(processes);
        Schedule rrSchedule = rrAlg.generateProcessSchedule();
        System.out.println("RR done");

        for(Process process : processes){
            System.out.println(process.getProcessID() + ";" + "SRT;" +  srtSchedule.getTurnAroundTime(process) );
            System.out.println(process.getProcessID() + ";" + "FCFS;" + fcfsSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "HRRN;" + hrrnSchedule.getTurnAroundTime(process));
            System.out.println(process.getProcessID() + ";" + "RR10;" + rrSchedule.getTurnAroundTime(process));
            //System.out.println(process.getProcessID() + ";" + "SPN;" + spnSchedule.getTurnAroundTime(process));
        }

//        //TODO: Löschen
//        for(Process process : processes){
//            System.out.println(process.getProcessID() + ";" + "SRT;" +  srtSchedule.getTurnAroundTime(process) + ";" + srtSchedule.getEndTime(process));
//            System.out.println(process.getProcessID() + ";" + "FCFS;" + fcfsSchedule.getTurnAroundTime(process)+ ";" + fcfsSchedule.getEndTime(process));
//            System.out.println(process.getProcessID() + ";" + "HRRN;" + hrrnSchedule.getTurnAroundTime(process)+ ";" + hrrnSchedule.getEndTime(process));
//            System.out.println(process.getProcessID() + ";" + "RR10;" + rrSchedule.getTurnAroundTime(process)+ ";" + rrSchedule.getEndTime(process));
//            //System.out.println(process.getProcessID() + ";" + "SPN;" + spnSchedule.getTurnAroundTime(process)+ ";" + srtSchedule.getEndTime(process));
//        }


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
