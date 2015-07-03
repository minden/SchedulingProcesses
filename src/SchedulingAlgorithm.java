import java.util.ArrayList;

/**
 * Created by indenml on 03.07.15.
 */
public class SchedulingAlgorithm {

    protected ArrayList<Process> processes;
    protected ArrayList<Process> processQueue;
    protected Integer ct;
    protected Schedule schedule;
    protected Process currentlyRunningProcess;
    protected Integer startTimeCuRuPr;

    public void fillProcessQue(){
        for(Process process : processes){
            //Currently arrived not blocked processes
            if(process.getArrivalTime().equals(ct) && !(process.isBlocked(ct))){
                processQueue.add(process);
                continue;
            }
            //Processes that were blocked but are now ready
            if(process.getBlockedTill().equals(ct)){
                processQueue.add(process);
            }
        }
    }

    public void runProcess(Process process){
        currentlyRunningProcess = process;
        startTimeCuRuPr = ct;
        currentlyRunningProcess.start(ct);
        processQueue.remove(process);
        ct++;
    }


}
