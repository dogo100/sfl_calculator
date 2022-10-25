import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

public class FibonacciTest {

  public Calculator calc = new Calculator();
  public int[] fib_seq = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377};
  public FibonacciTest() {
  }

  @Test
  public void fib0() {
    Assert.assertEquals(fib_seq[0], calc.fib(0));
  }

  @Test
  public void fib1() {
    Assert.assertEquals(fib_seq[1], calc.fib(1));
  }

  @Test
  public void fib2() {
    Assert.assertEquals(fib_seq[2], calc.fib(2));
  }

  @Test
  public void fib3() {
    Assert.assertEquals(fib_seq[3], calc.fib(3));
  }

  @Test
  public void fib4() {
    Assert.assertEquals(fib_seq[4], calc.fib(4));
  }

  @Test
  public void fib5() {
    Assert.assertEquals(fib_seq[5], calc.fib(5));
  }

  @Test
  public void fib6() {
    Assert.assertEquals(fib_seq[6], calc.fib(6));
  }

  @Test
  public void fib7() {
    Assert.assertEquals(fib_seq[7], calc.fib(7));
  }

  @Test
  public void fib8() {
    Assert.assertEquals(fib_seq[8], calc.fib(8));
  }

  @Test
  public void fib9() {
    Assert.assertEquals(fib_seq[9], calc.fib(9));
  }


}

