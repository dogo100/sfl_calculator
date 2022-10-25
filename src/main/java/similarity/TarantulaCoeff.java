package similarity;

import java.util.List;

public class TarantulaCoeff extends SimCoefficient{

    public TarantulaCoeff(List<List<String>> outcome, List<List<String>> coverage) {
        super(outcome, coverage);
    }

    @Override
    public double getSimCoefficient(int a00, int a01, int a10, int a11) {
        double numerator = ((double)a11)/(a11 + a01);
        double denominator = ((double)a11)/(a11 + a01) + ((double)a10)/(a10 + a00);
        double result = numerator / denominator;
        return Double.isNaN(result) ? 0.00 : result;
    }

}
