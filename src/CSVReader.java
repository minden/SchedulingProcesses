import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by indenml on 21.06.15.
 */
public class CSVReader {
    public ArrayList<Process> read(){
        String csvFile = "/home/indenml/S4/csos/bonus/03/SchedulingAlgorithms/example.csv";
        BufferedReader br = null;
        String line = null;
        String csvSplitBy = ";";
        ArrayList<Process> processes = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            //Jump header of file
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] readResult = line.split(csvSplitBy);
                processes.add(new Process(readResult[0], Integer.parseInt(readResult[1]), Integer.parseInt(readResult[2]), Float.parseFloat(readResult[3]), Integer.parseInt(readResult[4])));
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            if(br != null){
                try{
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return processes;

    }
}
