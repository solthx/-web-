package TDD101;

import java.util.ArrayList;
import java.util.List;

/**
 * @author czf
 * @Date 2020/3/11 2:49 下午
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        res.add("");
        for(int i=1; i<=n; ++i)
            res.add(helper(i));
        return res;
    }

    private String helper(int i){
        if ( check(i,3) && check(i,5) )
            return "FizzBuzz";
        else if (check(i,3))
            return "Fizz";
        else if(check(i,5))
            return "Buzz";
        return String.valueOf(i);
    }

    // 检查i是否包含target或被target整除
    private boolean check(int i, int target){
        if ( i%target==0 )
            return true;
        if ( String.valueOf(i).indexOf(String.valueOf(target))>=0 )
            return true;
        return false;
    }
}
