import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class ShortestRemainingTime {
    public static Schedule generateProcessSchedule(ArrayList<Process> inputProcesses) {
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList<Process> processQueue = new ArrayList<Process>();
        Integer ct = 0;
        Schedule schedule = new Schedule();
        Process currentlyRunningProcess = null;
        Integer startTimeCuRuPr = 0;

        //clone inputProcesses
        for(Process process : inputProcesses){
            processes.add(process.clone());
        }


        while(processes.size() > 0){

            //Check if the currently running process is done
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.getProcessingTime() == ct-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Check if the currently running Process is blocked
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.isBlocked(ct)){
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess.setProcessingTime(currentlyRunningProcess.getProcessingTime() - (ct - startTimeCuRuPr));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Fill the processQue
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


            //Get the process with the smallest processing time
            Process favouriteProcess = processQueue.get(0);
            for(Process process : processQueue){
                if(process.getProcessingTime() < favouriteProcess.getProcessingTime())
                    favouriteProcess = process;
                if(process.getProcessingTime() == favouriteProcess.getProcessingTime()){
                    if(process.getArrivalTime() < favouriteProcess.getArrivalTime())
                        favouriteProcess = process;
                }
            }


            if(currentlyRunningProcess != null) {

                //If favoriteProcess is the runningProcess
                if (currentlyRunningProcess.equals(favouriteProcess)) {
                    ct++;
                    continue;
                }

                //If currently running process is still faster than favouriteProcess
                if (currentlyRunningProcess.getProcessingTime() - (ct - startTimeCuRuPr) <= favouriteProcess.getProcessingTime()) {
                    ct++;
                    continue;
                }

                //If currently running process is slower then run favouriteProcess
                if (currentlyRunningProcess.getProcessingTime() - (ct - startTimeCuRuPr) > favouriteProcess.getProcessingTime()) {
                    Integer remainingTime = currentlyRunningProcess.getProcessingTime() - (ct-startTimeCuRuPr);
                    currentlyRunningProcess.setProcessingTime(remainingTime);
                    processQueue.add(currentlyRunningProcess);
                    schedule.add(new ScheduleItem(currentlyRunningProcess.getProcessID(), startTimeCuRuPr, ct, false));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //run that process
            currentlyRunningProcess = favouriteProcess;
            startTimeCuRuPr = ct;
            currentlyRunningProcess.start(ct);
            processQueue.remove(favouriteProcess);
            ct++;

        }

        return schedule;
    }
}
