import java.util.ArrayList;

/**
 * Created by indenml on 24.06.15.
 */
public class Schedule {
    protected ArrayList<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
    public void add(ScheduleItem item){
        this.schedule.add(item);
    }

    public ArrayList<ScheduleItem> getSchedule() {
        return schedule;
    }

    public Integer getTurnAroundTime(Process process){
        String processID = process.getProcessID();
        Integer arrivalTime = process.getArrivalTime();
        Integer finishTime = 0;

        for (ScheduleItem item : schedule){
            if (item.getFinished() && item.getItemID() == processID){
                finishTime = item.endTime;
                break;
            }

        }

        return finishTime - arrivalTime;
    }

    public void printSchedule() {
        for (ScheduleItem item : this.schedule) {
            System.out.println("ProcessID: " + item.getItemID());
            System.out.println("  ProcessStartTime: " + item.getStartTime());
            System.out.println("  ProcessEndTime: " + item.getEndTime());
            System.out.println("  ProcessFinished: " + item.getFinished());
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }
}
