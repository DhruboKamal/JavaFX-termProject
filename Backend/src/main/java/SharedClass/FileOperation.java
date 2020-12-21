package SharedClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileOperation {
    private static final String INPUT_FILE_NAME = "in.txt";
    private static final String OUTPUT_FILE_NAME = "out.txt";

    public static void initialize(List<Car> lst){
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                //Line represents details of a car.
                //System.out.println(line);
                String[] CarDetails = line.split(",", -1);
                Car temp = new Car(CarDetails[0], parseInt(CarDetails[1]), CarDetails[2], CarDetails[3], CarDetails[4], CarDetails[5], CarDetails[6], parseInt(CarDetails[7]),parseInt(CarDetails[8]));
                lst.add(temp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Terminate(List<Car> lst){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for(Car x: lst){
                bw.write(String.valueOf(x));
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}