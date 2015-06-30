import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class FirstComeFirstServe {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = new ArrayList<Process>();

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }
        Integer currentTime = 0;
        Schedule schedule = new Schedule();


        //Sort processes by arrival time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime().compareTo(p2.getArrivalTime());
            }
        });

        for (Process process : processes) {
            Integer processStartTime;
            if (process.getArrivalTime() > currentTime)
                processStartTime = process.getArrivalTime();
            else
                processStartTime = currentTime;
            schedule.add(new ScheduleItem(process.getProcessID(), processStartTime, processStartTime + process.getProcessingTime(), true));
            currentTime = processStartTime + process.getProcessingTime();
        }


        return schedule;
    }
}
