import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

public class CalculatorTest {

    public Calculator calc = new Calculator();
    public CalculatorTest() {
    }

    @Test
    public void mult0() {
        Assert.assertEquals(6, calc.mult(2, 3));
    }

    @Test
    public void mult1() {
        Assert.assertEquals(0, calc.mult(0, 5));
    }

    @Test
    public void mult2() {
        Assert.assertEquals(0, calc.mult(2, 0));
    }

    @Test
    public void mult3() {
        Assert.assertEquals(2, calc.mult(2, 1));
    }

    @Test
    public void mult4() {
        Assert.assertEquals(12, calc.mult(4, 3));
    }

    @Test
    public void mult5() {
        Assert.assertEquals(8, calc.mult(2, 4));
    }

    @Test
    public void mult6() {
        Assert.assertEquals(5, calc.mult(5, 1));
    }

    @Test
    public void mult7() {
        Assert.assertEquals(10, calc.mult(2, 5));
    }

    @Test
    public void mult8() {
        Assert.assertEquals(0, calc.mult(5, 0));
    }

    @Test
    public void mult9() {
        Assert.assertEquals(15, calc.mult(3, 5));
    }
}

