/**
 * Created by indenml on 21.06.15.
 */
public class Process {

    public String ProcessID;
    public Integer ProcessingTime;
    public Integer ArrivalTime;
    public Float IOBlockProbability;
    public Integer IOBlockTime;
    
    public Process(String ProceddID, Integer ProcessingTime, Integer ArrivalTime, Float IOBlockProbability, Integer IOBlockTime){

        this.ProcessID = ProceddID;
        this.ProcessingTime = ProcessingTime;
        this.ArrivalTime = ArrivalTime;
        this.IOBlockProbability = IOBlockProbability;
        this.IOBlockTime = IOBlockTime;

    }
}
