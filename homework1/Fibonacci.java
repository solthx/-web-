public class Fibonacci {
    public static int of( int n ){
        if (n<=1) return n;
        int p=1, pp=0,sav;
        for(int i=2; i<=n; ++i ){
            sav = pp + p;
            pp = p;
            p = sav;
        }
        return p;
    }
    public static void main(String[] args) {
        int i=0, val;
        while( (val=Fibonacci.of(i++))<=200 )
            System.out.println(val);
    }
}