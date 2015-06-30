import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class RoundRobin {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = new ArrayList<Process>();

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }

        ArrayList<Process> processesQue = new ArrayList<>();
        Schedule schedule = new Schedule();
        Integer quantum = 4;

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

        while(processes.size() > 1){

            //Fill processesQue
            for(Process process : processes){
                if(process.getArrivalTime() == currentTime)
                    processesQue.add(process);
            }

            if(currentlyRunningProcess != null){

                //Check if currently running Process is finished
                if(currentlyRunningProcess.getProcessingTime() == currentTime-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                }

                //Check if currently running Process is out of time
                else if(currentTime-startTimeCuRuPr >= quantum){
                    Integer remainingTime = currentlyRunningProcess.getProcessingTime() - (currentTime-startTimeCuRuPr);
                    currentlyRunningProcess.setProcessingTime(remainingTime);
                    processesQue.add(currentlyRunningProcess);
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, false));
                    currentlyRunningProcess = null;
                }
                else {
                    currentTime++;
                    continue;
                }
            }

            currentlyRunningProcess = processesQue.get(0);
            processesQue.remove(0);
            startTimeCuRuPr = currentTime;
            currentTime++;

        }

        //Add last currentlyRunningProcess to schedule
        schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, startTimeCuRuPr + currentlyRunningProcess.getProcessingTime(), true));

        return schedule;

    }
}
