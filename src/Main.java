
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args) {

        if(args.length != 1){
            if (args.length > 1){
                System.out.println("To many arguments specified!");
                System.exit(1);
            }
            if(args.length < 1){
                System.out.println("Please specify an input file");
                System.exit(1);
            }
        }

        CSVReader csvReader = new CSVReader();
        ArrayList<Process> processes = csvReader.read(args[0]);
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

        discussion(srtSchedule, fcfsSchedule, hrrnSchedule, rrSchedule, processes);

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

    public static void discussion(Schedule srt, Schedule fcfs, Schedule hrrn, Schedule rr, ArrayList<Process> processes){

        /*----------------------------SRT----------------------------*/
        Integer sum = 0;
        Integer count = 0;
        for(Process process : processes){
            sum += srt.getTurnAroundTime(process);
            count ++;
        }
        System.out.println("Average SRT all processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes){
            if(process.getIOBlockProbability().equals(0.0f)){
                sum += srt.getTurnAroundTime(process);
                count ++;
            }
        }
        System.out.println("Average SRT cpu bounded processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes) {
            if (process.getIOBlockProbability() >= 0.5f){
                sum += srt.getTurnAroundTime(process);
                count++;
            }
        }
        System.out.println("Average SRT I/O bound processes: " + (sum / count));

        /*----------------------------FCFS----------------------------*/
         sum = 0;
         count = 0;
        for(Process process : processes){
            sum += fcfs.getTurnAroundTime(process);
            count ++;
        }
        System.out.println("Average FCFS all processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes){
            if(process.getIOBlockProbability().equals(0.0f)){
                sum += fcfs.getTurnAroundTime(process);
                count ++;
            }
        }
        System.out.println("Average FCFS cpu bounded processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes) {
            if (process.getIOBlockProbability() >= 0.5f){
                sum += fcfs.getTurnAroundTime(process);
                count++;
            }
        }
        System.out.println("Average FCFS I/O bound processes: " + (sum / count));

        /*----------------------------HRRN----------------------------*/
         sum = 0;
         count = 0;
        for(Process process : processes){
            sum += hrrn.getTurnAroundTime(process);
            count ++;
        }
        System.out.println("Average HRRN all processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes){
            if(process.getIOBlockProbability().equals(0.0f)){
                sum += hrrn.getTurnAroundTime(process);
                count ++;
            }
        }
        System.out.println("Average HRRN cpu bounded processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes) {
            if (process.getIOBlockProbability() >= 0.5f){
                sum += hrrn.getTurnAroundTime(process);
                count++;
            }
        }
        System.out.println("Average HRRN I/O bound processes: " + (sum / count));


        /*----------------------------RR----------------------------*/
         sum = 0;
         count = 0;
        for(Process process : processes){
            sum += rr.getTurnAroundTime(process);
            count ++;
        }
        System.out.println("Average RR all processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes){
            if(process.getIOBlockProbability().equals(0.0f)){
                sum += rr.getTurnAroundTime(process);
                count ++;
            }
        }
        System.out.println("Average RR cpu bounded processes: " + (sum / count));

        sum = 0;
        count = 0;
        for(Process process : processes) {
            if (process.getIOBlockProbability() >= 0.5f){
                sum += rr.getTurnAroundTime(process);
                count++;
            }
        }
        System.out.println("Average RR I/O bound processes: " + (sum / count));


    }



}
