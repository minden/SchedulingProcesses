import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class RoundRobin {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = (ArrayList<Process>) inputProcesses.clone();
        Schedule schedule = new Schedule();
        Integer quantum = 10;

        //Sort processes by arrival time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime().compareTo(p2.getArrivalTime());
            }
        });

        Integer currentTime = 0;
        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = 0;

        while(processes.size() > 0){
            if(currentlyRunningProcess != null){

                //Check if currently running Process is finished
                if(currentlyRunningProcess.getProcessingTime() == currentTime-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, true));
                    currentlyRunningProcess = null;
                }

                //Check if currently running Process is out of time
                else if(currentTime-startTimeCuRuPr >= quantum){
                    Integer remainingTime = currentlyRunningProcess.getProcessingTime() - (currentTime-startTimeCuRuPr);
                    currentlyRunningProcess.setProcessingTime(remainingTime);
                    processes.add(currentlyRunningProcess);
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, false));
                    currentlyRunningProcess = null;
                }
                else {
                    currentTime++;
                    continue;
                }
            }

            currentlyRunningProcess = processes.get(0);
            processes.remove(0);
            startTimeCuRuPr = currentTime;
            currentTime++;

        }

        //Add last currentlyRunningProcess to schedule
        schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, startTimeCuRuPr + currentlyRunningProcess.getProcessingTime(), true));

        return schedule;

    }
}
