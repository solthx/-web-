package TDD101;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author czf
 * @Date 2020/3/11 2:51 下午
 */
public class FizzBuzzTest {
    @Test
    public void fizzBuzzTest(){
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> lst = fizzBuzz.fizzBuzz(100);
        System.out.println(lst);
        assertEquals("Fizz",lst.get(13));
        assertEquals("Fizz",lst.get(6));
        assertEquals("Buzz",lst.get(59));
        assertEquals("Buzz",lst.get(10));
        assertEquals("FizzBuzz",lst.get(35));
        assertEquals("FizzBuzz",lst.get(53));
    }
}
