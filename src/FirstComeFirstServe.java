import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by indenml on 21.06.15.
 */
public class FirstComeFirstServe extends SchedulingAlgorithm{

    public FirstComeFirstServe(List<Process> inputProcesses){
        super(inputProcesses);
    }

    @Override
    public  Schedule generateProcessSchedule() {

        while(processes.size() > 0){

            //Check if the currently running process is done
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.getProcessingTime() == ct-startTimeCuRuPr){
                    schedule.add(new ScheduleItem(
                            currentlyRunningProcess.getProcessID(),
                            startTimeCuRuPr,
                            ct,
                            true));
                    processes.remove(currentlyRunningProcess);
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Check if the currently running Process is blocked
            if(currentlyRunningProcess != null){
                if(currentlyRunningProcess.isBlocked(ct)){
                    schedule.add(new ScheduleItem(
                            currentlyRunningProcess.getProcessID(),
                            startTimeCuRuPr,
                            ct,
                            false));
                    currentlyRunningProcess.setProcessingTime(currentlyRunningProcess.getProcessingTime() - ( ct-startTimeCuRuPr));
                    currentlyRunningProcess = null;
                    startTimeCuRuPr = null;
                }
            }

            //Fill the processQue
            fillProcessQue();

            if(currentlyRunningProcess != null){
                ct++;
                continue;
            }

            //If no processes are waiting
            if(processQueue.size()  == 0) {
                ct++;
                continue;
            }

            runProcess(processQueue.get(0));
        }




        return schedule;
    }


}
