

public class Calculator {
    public int mult(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int result = a;
        for (int i = 0; i < (b - 1); i++) {
            result += b;  // should be: result += a
        }
        return result;
    }
}