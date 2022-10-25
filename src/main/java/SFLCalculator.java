import similarity.JaccardCoeff;
import similarity.Kulczynski2;
import similarity.OchiaiCoeff;
import similarity.TarantulaCoeff;

import java.util.ArrayList;
import java.util.List;

public class SFLCalculator {

    // Input from the CLI has to match the enum value
    enum Coeff {
        jaccard,
        tarantula,
        ochiai,
        kulczynski2,
        all
    }

    public List<List<String>> outcome;
    public List<List<String>> coverage;
    public List<List<String>> sfl_result;
    public Coeff algorithm;
    public boolean lineSorting;
    public boolean removeZeroLines;
    public CSVHandler csvHandler;
    public boolean showAij;


    SFLCalculator(String outcomeMatrix, String coverageMatrix, Coeff algorithm, int lineSorting, int removeZeroLines, int showAij){
        this.csvHandler = new CSVHandler();
        this.outcome = csvHandler.readCSV(outcomeMatrix);
        this.coverage = csvHandler.readCSV(coverageMatrix);
        this.algorithm = algorithm;
        this.lineSorting = lineSorting != 0;
        this.removeZeroLines = removeZeroLines != 0;
        this.showAij = showAij != 0;
        this.sfl_result = new ArrayList<>();
    }

    public void execute(){

        String header = "";

        switch (this.algorithm) {
            case jaccard:
                sfl_result = new JaccardCoeff(outcome, coverage).calcSimilarity(sfl_result, showAij);
                header += Coeff.jaccard.toString();
                break;
            case tarantula:
                sfl_result = new TarantulaCoeff(outcome, coverage).calcSimilarity(sfl_result, showAij);
                header += Coeff.tarantula.toString();
                break;
            case ochiai:
                sfl_result = new OchiaiCoeff(outcome, coverage).calcSimilarity(sfl_result, showAij);
                header += Coeff.ochiai.toString();
                break;
            case kulczynski2:
                sfl_result = new Kulczynski2(outcome, coverage).calcSimilarity(sfl_result, showAij);
                header += Coeff.kulczynski2.toString();
                break;
            case all:
                sfl_result = new JaccardCoeff(outcome, coverage).calcSimilarity(sfl_result);
                sfl_result = new TarantulaCoeff(outcome, coverage).calcSimilarity(sfl_result);
                sfl_result = new OchiaiCoeff(outcome, coverage).calcSimilarity(sfl_result);
                // getting the values for aij is only necessary during the last Coefficient calculation
                sfl_result = new Kulczynski2(outcome, coverage).calcSimilarity(sfl_result, showAij);
                header += getStringOfAllCoeff();
                break;
            default:
                System.out.println("ERROR");
                System.exit(2);
        }

        if (showAij){
            header += ",a00,a01,a10,a11";
        }

        csvHandler.writeCSV(sfl_result, header, removeZeroLines, lineSorting);
    }

    public String getStringOfAllCoeff(){
        StringBuilder result = new StringBuilder();
        String prefix = "";
        for (SFLCalculator.Coeff coeff : SFLCalculator.Coeff.values()) {
            if (coeff.toString().equals("all")){
                break;
            }
            result.append(prefix);
            prefix = ",";
            result.append(coeff);
        }
        return result.toString();
    }

}
