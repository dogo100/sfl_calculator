# SFL Calculator

The SFL Calculator is a Java program used to calculate the Spectrum-based fault localization.
As input is uses the output of the [JSR - The Java Test Suite Reduction Framework](https://github.com/Lms24/JSR).

## Installation

You can either simply use the provided `sfl-1.0.jar` or build the project yourself.

For building the project yourself you need:
* Java JDK 11
* Gradle

With these requirements installed to build the project either import it into a IDE or execute the follwing command:
```shell
gradle
gradle build
```

## Usage

As prerequisite, you need to generate the SFL Matrices with JSR described [here](https://github.com/Lms24/JSR#generating-sfl-matrices-via-the-cli).
With this output you can start the SFL Calculator. 

```shell
java -jar sfl-1.0.jar --help
```
This command produces the following output:
```shell
Usage: sfl [-hV] [-l=<lineSorting>] [-s=<coeff>] [-v=<showAij>]
           [-z=<removeZeroLines>] (-c=<coverage> -o=<outcome>)
Creates Spectrum-based Fault Localization

  -h, --help                 Show this help message and exit.
  -V, --version              Print version information and exit.

Required Parameters:
  -c, --cov=<coverage>       Path to the coverage matrix/program spectrum matrix
  -o, --out=<outcome>        Path to the outcome matrix/error vector

Optional Parameters:
  -s, --simcoeff=<coeff>     Similarity coefficient used for calculating the
                               SFL. Possible values are:  jaccard, tarantula,
                               ochiai, kulczynski2, all. Default = <all>
  -l, --line=<lineSorting>   Defines the sorting of the SFL. <1> = sorted by
                               line number, <0> = sorted by likelihood. Default
                               = <1>
  -z, --zero=<removeZeroLines>
                             If set to <1>, all lines with likelihood == 0 will
                               be removed from the result. Default = <0>
  -v, --values=<showAij>     If set to <1>, the values for a_00, a_01, a_10 and
                               a_11 will be displayed. Default = <0>
```

After execution the SLF Calculator will create a .csv file with the results called `sfl.csv`.

Note, the parameters `-l, --line=<lineSorting>` and `-z, --zero=<removeZeroLines>` are best used with only 1 similarity coefficient.
Usage instructions can be found by running


### Example

Example executions with the test files provided in this repository are provided below:

`shell
java -jar sfl-1.0.jar -c test_files/mult/coverageMatrix.csv -o test_files/mult/outcomeMatrix.csv
`

`shell
java -jar sfl-1.0.jar -c test_files/fib/coverageMatrix.csv -o test_files/fib/outcomeMatrix.csv -s ochiai -l 0
`

## Adding new Similarity Coefficients

Adding new similarity coefficients was made really easy and only needs 2 steps:

1. Create a new class that is extended from `SimCoefficient` and implements the `getSimCoefficient()` function.
2. In the `SFLCalculator` class add your desired CLI parameter to the `Coeff` enum and in the `execute()` method add a call to your newly created class.

## Credits
### Libraries and Tools

* [picocli](https://picocli.info/) as the CLI framework
