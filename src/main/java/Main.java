import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new SFLCommand()).execute(args);
        System.exit(exitCode);
    }
}
