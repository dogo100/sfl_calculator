package similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class SimCoefficient {

    public final String PRECISION = "%.2f";
    public List<List<String>> outcome;
    public List<List<String>> coverage;

    public SimCoefficient(List<List<String>> outcome, List<List<String>> coverage) {
        this.coverage = coverage;
        this.outcome = outcome;
    }

    abstract public double getSimCoefficient(int a00, int a01, int a10, int a11) ;
    
    static class CounterValues{
        public int a00;
        public int a01;
        public int a10;
        public int a11;
    }

    private List<String> getColumn(List<List<String>> array, int index){
        List<String> column = new ArrayList<>();
        for (List<String> strings : array) {
            column.add(strings.get(index));
        }
        return column;
    }

    public List<List<String>> calcSimilarity(List<List<String>> similarityOld) {
        return calcSimilarity(similarityOld, false);
    }

    public List<List<String>> calcSimilarity(List<List<String>> similarityOld, boolean showAij){

        boolean firstRun = similarityOld.isEmpty();
        List<List<String>> similarity = new ArrayList<>();
        List<String> column_out = getColumn(outcome, 1);
        CounterValues counterValues = new CounterValues();
        for (int i = 1; i < coverage.get(0).size(); i++) {
            List<String> column_cov = getColumn(coverage, i);
            counterValues.a00 = 0;
            counterValues.a01 = 0;
            counterValues.a10 = 0;
            counterValues.a11 = 0;
            for (int j = 1; j < column_cov.size(); j++) {
                int val = Integer.parseInt(column_cov.get(j));
                // xor 1 to change 1 -> 0 and 0 -> 1
                // because we need an error vector (1 = fail, 0 = pass/no fail)
                int out = Integer.parseInt(column_out.get(j)) ^ 1;
                switch (val){
                    case 0:
                        if (out == 0)
                        {
                            counterValues.a00++;
                        }else
                        {
                            counterValues.a01++;
                        }
                        break;
                    case 1:
                        if (out == 0)
                        {
                            counterValues.a10++;
                        }else
                        {
                            counterValues.a11++;
                        }
                        break;
                    default:
                        System.out.println("ERROR!!!");
                        System.exit(1);
                }
            }
            similarity.add(addSimilarity(similarityOld, showAij, firstRun, counterValues, i, column_cov));
        }
        return similarity;
    }

    private List<String> addSimilarity(List<List<String>> similarityOld, boolean showAij, boolean firstRun, CounterValues counterValues, int i, List<String> column_cov) {
        if (firstRun) {
            if (showAij){
                return Arrays.asList(column_cov.get(0),
                        String.format(PRECISION, getSimCoefficient(counterValues.a00, counterValues.a01, counterValues.a10, counterValues.a11)),
                        String.valueOf(counterValues.a00),
                        String.valueOf(counterValues.a01),
                        String.valueOf(counterValues.a10),
                        String.valueOf(counterValues.a11));
            }else{
                return Arrays.asList(column_cov.get(0),
                        String.format(PRECISION, getSimCoefficient(counterValues.a00, counterValues.a01, counterValues.a10, counterValues.a11)));
            }
        } else{
            List<String> temp = new ArrayList<>(similarityOld.get(i -1));
            if (showAij) {
                temp.add(String.format(PRECISION, getSimCoefficient(counterValues.a00, counterValues.a01, counterValues.a10, counterValues.a11)));
                temp.add(String.valueOf(counterValues.a00));
                temp.add(String.valueOf(counterValues.a01));
                temp.add(String.valueOf(counterValues.a10));
                temp.add(String.valueOf(counterValues.a11));
            }else {
                temp.add(String.format(PRECISION, getSimCoefficient(counterValues.a00, counterValues.a01, counterValues.a10, counterValues.a11)));
            }
            return temp;
        }
    }

}
