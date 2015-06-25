import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 25.06.15.
 */
public class ShortestProcessNext {
    public static Schedule generateProcessSchedule(ArrayList<Process> processes) {
        Integer currentTime = 0;
        Schedule schedule = new Schedule();


        //Sort processes by processing time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getProcessingTime().compareTo(p2.getProcessingTime());
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

        Main.printProcesses(processes);

        return schedule;
    }
}