import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class ShortestRemainingTime {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = new ArrayList<Process>();

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }
        Integer currentTime = 0;
        Schedule schedule = new Schedule();

        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = 0;

        while(processes.size() > 0){

            //Check if the currently running process is done
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.getProcessingTime() == currentTime-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, true));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = currentTime;
                }
            }

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
                if(process.getProcessingTime() == favouriteProcess.getProcessingTime()){
                    if(process.getArrivalTime() < favouriteProcess.getArrivalTime())
                        favouriteProcess = process;
                }
            }


            //If there is no process currently running, run favouriteProcess
            if(currentlyRunningProcess == null){
                currentlyRunningProcess = favouriteProcess;
                processes.remove(currentlyRunningProcess);
                startTimeCuRuPr = currentTime;
                currentTime++;
                continue;
            }

            //If favoriteProcess is the runningProcess
            if(currentlyRunningProcess.equals(favouriteProcess)){
                currentTime++;
                continue;
            }

            //If currently running process is still faster than favouriteProcess
            if(currentlyRunningProcess.getProcessingTime()-(currentTime-startTimeCuRuPr) < favouriteProcess.getProcessingTime()){
                currentTime++;
                continue;
            }

            //If currently running process is slower than run favouriteProcess
            if(currentlyRunningProcess.getProcessingTime()-(currentTime-startTimeCuRuPr) > favouriteProcess.getProcessingTime()){
                Integer remainingTime = currentlyRunningProcess.getProcessingTime() - (currentTime-startTimeCuRuPr);
                currentlyRunningProcess.setProcessingTime(remainingTime);
                processes.add(currentlyRunningProcess);
                schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, currentTime, false));
                currentlyRunningProcess = favouriteProcess;
                processes.remove(currentlyRunningProcess);
                startTimeCuRuPr = currentTime;
                currentTime++;
                continue;
            }


        }

        //Add last currentlyRunningProcess to schedule
        schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, startTimeCuRuPr + currentlyRunningProcess.getProcessingTime(), true));




        return schedule;
    }
}
