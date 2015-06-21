/**
 * Created by indenml on 21.06.15.
 */
public class Main {
    public static void main(String[] args){
        CSVReader csvReader = new CSVReader();
        for (Process process : csvReader.read()) {
            System.out.print(process.getProcessID());
            System.out.print(process.getProcessingTime());
            System.out.print(process.getIOBlockProbability());
            System.out.println(process.getIOBlockTime());
        }

    }
}
