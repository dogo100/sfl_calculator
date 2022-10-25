import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CSVHandler {

    public CSVHandler() {
    }

    public List<List<String>> readCSV(String file){

        List<List<String>> outcome = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                outcome.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outcome;

    }

    public void writeCSV(List<List<String>> content, String header, boolean removeZeroLines, boolean lineSorting){


        if (lineSorting){
            content.sort(comparator_line_nr);
        }else {
            content.sort(comparator_sim_score);
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter("sfl.csv");
            StringBuilder sb = new StringBuilder();
            sb.append("Statement," + header +"\n");
            for(List<String> row : content) {
                if(removeZeroLines && (Math.abs(Double.parseDouble(row.get(1)) - 0) < 0.0000001)){
                    continue;
                }
                sb.append(String.join(",", row)).append("\n");

            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static final Comparator<List<String>> comparator_line_nr = new Comparator<List<String>>() {
        public int compare(List<String> pList1, List<String> pList2) {
            return extractInt(pList1.get(0))-(extractInt(pList2.get(0)));
        }
    };
    public static final Comparator<List<String>> comparator_sim_score = new Comparator<List<String>>() {
        public int compare(List<String> pList1, List<String> pList2) {
            return pList2.get(1).compareTo(pList1.get(1));
        }
    };
    public static int extractInt(String s) {
        String num = s.replaceAll("\\D", "");
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }

}
