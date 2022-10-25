

public class Calculator {
    public int fib(int a){
        if (a == 0 || a == 1){
            return a;
        }
        int num1 = 0;
        int num2 = 1;
        for (int i = 0; i < a; ++i) {
            int sumOfPrevTwo = num1 + num1; // should be: num1+num2
            num1 = num2;
            num2 = sumOfPrevTwo;
        }
        return num1;
    }
}