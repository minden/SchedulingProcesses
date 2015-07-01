import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class FirstComeFirstServe {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {

        /* ---------------------- Initialize ----------------------*/
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList<Process> processQueue = new ArrayList<Process>();
        Integer ct = 0; //current Time
        Schedule schedule = new Schedule();
        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = null;

        //clone inputProcesses
        for(Process process : inputProcesses) {
            processes.add(process.clone());
        }

        /* ---------------------- Algorithm ----------------------*/
        while(processes.size() > 0){

            //Check if the currently running Process is blocked
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.isBlocked(ct)){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess.setProcessingTime(currentlyRunningProcess.getProcessingTime() - ( ct-startTimeCuRuPr));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Check if the currently running process is done
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.getProcessingTime() == ct-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Fill the processQue
            for(Process process : processes){
                //Currently arrived not blocked processes
                if(process.getArrivalTime() == ct && !(process.isBlocked(ct))){
                    processQueue.add(process);
                    continue;
                }
                //Processes that were blocked but are now ready
                if(process.getBlockedTill() == ct){
                    processQueue.add(process);
                }
            }

            if(currentlyRunningProcess != null){
                ct++;
                continue;
            }

            //If no processes are waiting
            if(processQueue.size()  == 0) {
                ct++;
                continue;
            }


            currentlyRunningProcess = processQueue.get(0);
            startTimeCuRuPr = ct;
            currentlyRunningProcess.start(ct);
            processQueue.remove(0);
            ct++;

        }

        //Add last Process to schedule



        return schedule;
    }


}
