package similarity;

import java.util.List;

public class Kulczynski2 extends SimCoefficient{

    public Kulczynski2(List<List<String>> outcome, List<List<String>> coverage) {
        super(outcome, coverage);
    }

    @Override
    public double getSimCoefficient(int a00, int a01, int a10, int a11) {
        double firstTerm = (double)a11 / (a11 + a01);
        double secondTerm = (double)a11 / (a11 + a10);
        double result = (1.0/2.0) * (firstTerm + secondTerm);
        return Double.isNaN(result) ? 0.00 : result;
    }

}
