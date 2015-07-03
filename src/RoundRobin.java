import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by indenml on 21.06.15.
 */
public class RoundRobin extends SchedulingAlgorithm {

    protected Integer quantum;

    public RoundRobin(List<Process> inputProcesses){
        super(inputProcesses);
        quantum = 10;
    }

    public  Schedule generateProcessSchedule() {

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

            fillProcessQue();

            //If there are no processes currently ready
            if (processQueue.size() == 0){
                ct++;
                continue;
            }

            if(currentlyRunningProcess != null){
                ct++;
                continue;
            }

            runProcess(processQueue.get(0));

        }


        return schedule;

    }
}
