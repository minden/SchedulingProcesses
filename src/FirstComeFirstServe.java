import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class FirstComeFirstServe {
    public static  ArrayList<ScheduleItem> generateProcessSchedule(ArrayList<Process> processes){

        ArrayList<ScheduleItem> schedule = new ArrayList<ScheduleItem>();

        //Sort processes by arrival time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime().compareTo(p2.getArrivalTime());
            }
        });

        Integer currentTime = 0;
        for (Process process : processes){

            Integer processStartTime;

            if(process.getArrivalTime() > currentTime)
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
