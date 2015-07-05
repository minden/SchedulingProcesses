import java.util.ArrayList;
import java.util.List;

/**
 * Created by indenml on 03.07.15.
 */
public abstract class SchedulingAlgorithm {

    protected List<Process> processes;
    protected List<Process> processQueue;
    protected Integer ct;
    protected Schedule schedule;
    protected Process currentlyRunningProcess;
    protected Integer startTimeCuRuPr;

    public SchedulingAlgorithm(List<Process> inputProcesses){
        processes = new ArrayList<Process>();
        processQueue = new ArrayList<Process>();
        ct = 0;
        schedule = new Schedule();
        currentlyRunningProcess = null;
        startTimeCuRuPr = null;

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }
    }

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

    abstract Schedule generateProcessSchedule();
}
