import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by indenml on 21.06.15.
 */
public class HighestResponseRatioNext {
    public static Schedule generateProcessSchedule(ArrayList<Process> processes) {
        Integer currentTime = 0;
        Schedule schedule = new Schedule();

        //Sort processes by arrival time
        Collections.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime().compareTo(p2.getArrivalTime());
            }
        });

        while(processes.size() != 0) {

            ArrayList<Process> relevantProcesses = new ArrayList<>();
            //Get all processes that are currently waiting
            for (Process process : processes) {
                if (process.getArrivalTime() <= currentTime){
                    relevantProcesses.add(process);
                }
            }

            //Find process with highest response ratio
            Integer interestingProcess = 0;
            Float highestResponseRatio = getResponseRatio(currentTime, relevantProcesses.get(0));
            for(int i = 0; i < relevantProcesses.size(); i++ ){
                if (getResponseRatio(currentTime, relevantProcesses.get(i)) > highestResponseRatio){
                    interestingProcess = i;
                    highestResponseRatio = getResponseRatio(currentTime, relevantProcesses.get(i));
                }
            }

            //add process to schedule
            Process processToBeAdded = relevantProcesses.get(interestingProcess);
            ScheduleItem item = new ScheduleItem(processToBeAdded.getProcessID(),currentTime, currentTime + processToBeAdded.getProcessingTime(), true);
            schedule.add(item);

            //delete process from list
            processes.remove(processToBeAdded);

            currentTime = currentTime + processToBeAdded.getProcessingTime();

        }

        Main.printProcesses(processes);

        return schedule;
    }

    public static Float getResponseRatio(Integer currentTime, Process process){
        return (Float.valueOf((currentTime - process.getArrivalTime()) + process.getProcessingTime()) / Float.valueOf(process.getProcessingTime()));
    }
}
