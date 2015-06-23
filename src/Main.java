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
        FirstComeFirstServe.FirstComeFirstServe(processes);

    }

    public static void printProcesses(ArrayList<Process> processes){
        for(Process process: processes){
            System.out.println("ProcessID: " + process.getProcessID());
            System.out.println("ProcessingTime: " + process.getProcessingTime());
            System.out.println("ArrivalTime: " + process.getArrivalTime());
            System.out.println("IOBlockProbability: " + process.getIOBlockProbability());
            System.out.println("IOBlockTime: " + process.getIOBlockTime());
            System.out.println();
        }
    }
}
