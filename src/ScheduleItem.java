/**
 * Created by indenml on 24.06.15.
 */
public class ScheduleItem {
    protected String itemID;
    protected Integer startTime;
    protected Integer endTime;
    protected Boolean finished;

    public ScheduleItem(String itemID, Integer startTime, Integer endTime, Boolean finished){
        this.itemID = itemID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finished = finished;
    }

    public String getItemID() {
        return itemID;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public Boolean isFinished() {
        return finished;
    }
}
