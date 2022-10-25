package similarity;

import java.util.List;

public class JaccardCoeff extends SimCoefficient{

    public JaccardCoeff(List<List<String>> outcome, List<List<String>> coverage) {
        super(outcome, coverage);
    }

    @Override
    public double getSimCoefficient(int a00, int a01, int a10, int a11) {
        double result = ((double) a11)/(a11 + a01 + a10);
        return Double.isNaN(result) ? 0.00 : result;
    }
}
