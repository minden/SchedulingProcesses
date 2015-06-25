import java.util.ArrayList;


/**
 * Created by indenml on 25.06.15.
 */
public class ShortestProcessNext {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = (ArrayList<Process>) inputProcesses.clone();
        Integer currentTime = 0;
        Schedule schedule = new Schedule();

        while(processes.size() > 0){

            //Get all currently waiting Processes
            ArrayList<Process> waitingProcesses = new ArrayList<>();
            for (Process process : processes){
                if(process.getArrivalTime()<=currentTime)
                    waitingProcesses.add(process);
            }

            //Check if no processes are currently waiting
            if(waitingProcesses.size() == 0){
                currentTime++;
                continue;
            }

            //Get the process with the smallest processing time
            Process favouriteProcess = waitingProcesses.get(0);
            for(Process process : waitingProcesses){
                if(process.getProcessingTime() < favouriteProcess.getProcessingTime())
                    favouriteProcess = process;
            }

            //Add favourite Process to schedule
            schedule.add(new ScheduleItem(favouriteProcess.getProcessID(),currentTime, favouriteProcess.getProcessingTime() + currentTime, true));

            processes.remove(favouriteProcess);
            currentTime = currentTime + favouriteProcess.getProcessingTime();
        }


        return schedule;
    }
}