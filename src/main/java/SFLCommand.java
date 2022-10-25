

import picocli.CommandLine;
import java.util.List;

@CommandLine.Command(
        name = "sfl",
        mixinStandardHelpOptions = true,
        description = "Creates Spectrum-based Fault Localization\r\n",
        version = "1.0",
        sortOptions = false
)
public class SFLCommand implements Runnable{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @CommandLine.ArgGroup(heading = "\nRequired Parameters:\n", order = 1, exclusive = false, multiplicity = "1")
    RequiredParameters requiredParameters;

    static class RequiredParameters {
        @CommandLine.Option(names = {"-c", "--cov"}, description = "Path to the coverage matrix/program spectrum matrix", required = true, order = 0)
        String coverage;

        @CommandLine.Option(names = {"-o", "--out"}, description = "Path to the outcome matrix/error vector", required = true, order = 1)
        String outcome;
    }

    @CommandLine.ArgGroup(heading = "\nOptional Parameters:\n", order = 2, exclusive = false, validate = false)
    OptionalParameters optionalParameters;

    static class OptionalParameters{
        @CommandLine.Option(names = { "-s", "--simcoeff" }, description = "Similarity coefficient used for calculating the SFL. Possible values are: " +
                " ${COMPLETION-CANDIDATES}. Default = <all>"
                , required = false, order = 0)
        SFLCalculator.Coeff coeff = SFLCalculator.Coeff.all;

        @CommandLine.Option(names = { "-l", "--line" }, description = "Defines the sorting of the SFL. <1> = sorted by line number, " +
                "<0> = sorted by likelihood. Default = <1>", required = false, order = 1)
        int lineSorting = 1;

        @CommandLine.Option(names = { "-z", "--zero" }, description = "If set to <1>, all lines with likelihood == 0 " +
                "will be removed from the result. Default = <0>", required = false, order = 2)
        int removeZeroLines = 0;

        @CommandLine.Option(names = { "-v", "--values"}, description = "If set to <1>, the values for a_00, a_01, a_10 and a_11 " +
                "will be displayed. Default = <0>", required = false, order = 3)
        int showAij = 0;
    }



    @Override
    public void run() {

        // If no Option in ArgGroup is defined picocli will not initialize the class
        if (optionalParameters == null){
            optionalParameters = new OptionalParameters();
        }
        checkOptionalParameters();

        SFLCalculator sflCalculator = new SFLCalculator(
                requiredParameters.outcome,
                requiredParameters.coverage,
                optionalParameters.coeff,
                optionalParameters.lineSorting,
                optionalParameters.removeZeroLines,
                optionalParameters.showAij);
        sflCalculator.execute();
        System.out.println("Spectrum-based Fault Localization executed successfully");
    }

    private void checkOptionalParameters() {
        if (!(optionalParameters.lineSorting == 0 || optionalParameters.lineSorting == 1)){
            System.out.println(ANSI_RED + "-s, --sort=<sorting> Value must be <0> or <1>" + ANSI_RESET);
            System.exit(2);
        }
        if (!(optionalParameters.removeZeroLines == 0 || optionalParameters.removeZeroLines == 1)){
            System.out.println(ANSI_RED + "-z, --zero=<removeZeroLines> Value must be <0> or <1>" + ANSI_RESET);
            System.exit(2);
        }
        if (!(optionalParameters.showAij == 0 || optionalParameters.showAij == 1)){
            System.out.println(ANSI_RED + "-b, --aij=<showAij> Value must be <0> or <1>" + ANSI_RESET);
            System.exit(2);
        }
    }
}
