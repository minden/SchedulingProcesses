import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by indenml on 21.06.15.
 */
public class HighestResponseRatioNext {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {

        /* ---------------------- Initialize ----------------------*/
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList<Process> processQueue = new ArrayList<Process>();
        Integer ct = 0;
        Schedule schedule = new Schedule();
        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = null;

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }

        /* ---------------------- Algorithm ----------------------*/
        while(processes.size() != 0) {

            //Check if the currently running process is done
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.getProcessingTime() == ct-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Check if the currently running Process is blocked
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.isBlocked(ct)){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess.setProcessingTime(currentlyRunningProcess.getProcessingTime() - ( ct-startTimeCuRuPr));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Fill the processQue
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

            //If there are no processes currently ready
            if (processQueue.size() == 0){
                ct++;
                continue;
            }

            if(currentlyRunningProcess != null){
                ct++;
                continue;
            }

            //Find process with highest response ratio
            Process interestingProcess = getProcessHighestResponseRatio(ct, processQueue);

            //run that process
            currentlyRunningProcess = interestingProcess;
            startTimeCuRuPr = ct;
            currentlyRunningProcess.start(ct);
            processQueue.remove(interestingProcess);
            ct++;

        }

        return schedule;
    }

    public static Process getProcessHighestResponseRatio(Integer ct, List<Process> processQueue){
        Process interestingProcess = processQueue.get(0);
        Float highestResponseRatio = getResponseRatio(ct, interestingProcess);
        for(Process process : processQueue){
            if (getResponseRatio(ct, process) > highestResponseRatio){
                interestingProcess = process;
                highestResponseRatio = getResponseRatio(ct, process);
            }
        }
        return interestingProcess;
    }

    public static Float getResponseRatio(Integer currentTime, Process process){
        return (Float.valueOf((currentTime - process.getArrivalTime()) + process.getProcessingTime()) / Float.valueOf(process.getProcessingTime()));
    }


}
