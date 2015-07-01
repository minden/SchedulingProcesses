import java.util.Random;

/**
 * Created by indenml on 21.06.15.
 */
public class Process {

    private String ProcessID;
    private Integer ProcessingTime;
    private Integer ArrivalTime;
    private Float IOBlockProbability;
    private Integer IOBlockTime;

    private Integer blockedTill = 0;

    public Process(String ProceddID, Integer ProcessingTime, Integer ArrivalTime, Float IOBlockProbability, Integer IOBlockTime){

        this.ProcessID = ProceddID;
        this.ProcessingTime = ProcessingTime;
        this.ArrivalTime = ArrivalTime;
        this.IOBlockProbability = IOBlockProbability;
        this.IOBlockTime = IOBlockTime;

        this.blockedTill = -1;

    }

    public void setProcessingTime(Integer processingTime) {
        ProcessingTime = processingTime;
    }

    public Float getIOBlockProbability() {
        return IOBlockProbability;
    }

    public String getProcessID() {
        return ProcessID;
    }

    public Integer getProcessingTime() { return ProcessingTime; }

    public Integer getArrivalTime() {
        return ArrivalTime;
    }

    public Integer getIOBlockTime() {
        return IOBlockTime;
    }

    public Process clone(){ return new Process(this.ProcessID, this.ProcessingTime, this.ArrivalTime, this.IOBlockProbability, this.IOBlockTime);}

    public void start(Integer ct){
        Float max = 1f;
        Float min = 0f;

        Random random = new Random();
        Float ranFloat = random.nextFloat() * (max-min)+min;

        if(IOBlockProbability >= ranFloat)
            blockedTill = ct + IOBlockTime + 1 + 1; //+1 because of block after 1 ms
    }

    public Boolean isBlocked(Integer ct){
        return blockedTill >= ct;
    }

    public Integer getBlockedTill(){
        return blockedTill;
    }


}
