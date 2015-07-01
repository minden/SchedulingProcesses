import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class RoundRobin {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {

        /* ---------------------- Initialize ----------------------*/
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList<Process> processQueue = new ArrayList<>();
        Schedule schedule = new Schedule();
        Integer quantum = 4;
        Integer ct = 0;
        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = 0;

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }

        //Sort processes by arrival time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime().compareTo(p2.getArrivalTime());
            }
        });

        /* ---------------------- Algorithm ----------------------*/
        while(processes.size() > 0){

            if(currentlyRunningProcess != null){

                //Check if the currently running process is done
                if(currentlyRunningProcess.getProcessingTime() == ct-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }


                //Check if the currently running Process is blocked
                else if(currentlyRunningProcess.isBlocked(ct)){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess.setProcessingTime(currentlyRunningProcess.getProcessingTime() - ( ct-startTimeCuRuPr));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }

                //Check if currently running Process is out of time
                else if(ct-startTimeCuRuPr >= quantum){
                    Integer remainingTime = currentlyRunningProcess.getProcessingTime() - (ct-startTimeCuRuPr);
                    currentlyRunningProcess.setProcessingTime(remainingTime);
                    processQueue.add(currentlyRunningProcess);
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }

            }

            //Fill processQueue
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

            //If there are no processes currently ready
            if (processQueue.size() == 0){
                ct++;
                continue;
            }

            if(currentlyRunningProcess != null){
                ct++;
                continue;
            }

            //run the process
            currentlyRunningProcess = processQueue.get(0);
            startTimeCuRuPr = ct;
            currentlyRunningProcess.start(ct);
            processQueue.remove(currentlyRunningProcess);
            ct++;



        }


        return schedule;

    }
}
