package dsa.structs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dude
 */
public class fileIO {
    
    String data;

    public fileIO() {
        
    }

//    public void readFromCSV(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                String[] lin = line.split(",");
//                data.add( lin[0], lin[1] );   // for now just put the first column
//                }
//            }
//            catch (IOException | NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
    
//    public void writeCSV(String filePath) {
//        
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
//            // Write header
////            bw.write("ID,Timestamp,Latitude,Longitude\n");
//
//            // Write data rows
//            for (K, V : data) {
//                if (K != null) {
//                    // Write data row
//                    bw.write( String.format(",%s,%s", K, V) );
//                    bw.write("\n");
//                }
//            }
//        } 
//        catch (IOException e) {
//            e.printStackTrace();
//            }
//    }
}
