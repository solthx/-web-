package util;


import org.junit.Test;

/**
 * @author czf
 * @Date 2020/3/11 9:02 下午
 */
public class TexasHoldemComparactorTest {
    @Test
    public void compare() {
        String 同花顺 = "2C 3C 4C 5C 6C";

        String 铁支 = "2A 2C 2C 2C 6C";

        String 葫芦 = "2S 2C 2C 3S 3S";

        String 同花 = "2S 3S 4S AS 9S";

        String 顺子 = "2S 3S 4C 5S 6S";

        String 三条 = "2S 2S 2S 3S 4C";

        String 两对 = "2S 2S 3C 3C 5S";

        String 对子 = "2S 2S 6C 3C 5S";

        String 散牌 = "2S 6S 7C 3C 5S";

        TexasHoldemComparactor texasHoldemComparactor = new TexasHoldemComparactor();

        assert ( 0 > texasHoldemComparactor.compare(铁支, 同花顺) );
        assert ( 0 > texasHoldemComparactor.compare(葫芦, 铁支) );
        assert ( 0 > texasHoldemComparactor.compare(同花, 葫芦) );
        assert ( 0 > texasHoldemComparactor.compare(顺子, 同花) );
        assert ( 0 > texasHoldemComparactor.compare(三条, 顺子) );
        assert ( 0 > texasHoldemComparactor.compare(两对, 三条) );
        assert ( 0 > texasHoldemComparactor.compare(对子, 两对) );
        assert ( 0 > texasHoldemComparactor.compare(散牌, 对子) );

        /**
         * 题目的测试用例
         * 输入: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH 输出: White wins - high card: Ace
         * 输入: Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S 输出: Black wins - full house
         * 输入: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C KH 输出: Black wins - high card: 9
         * 输入: Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH 输出: Tie
         */
        String w1 = "2H 3D 5S 9C KD";
        String b1 =  "2C 3H 4S 8C AH";

        String w2 = "2H 4S 4C 2D 4H";
        String b2 =  "2S 8S AS QS 3S";

        String w3 = "2H 3D 5S 9C KD";
        String b3 =  "2C 3H 4S 8C KH";

        String w4 = "2H 3D 5S 9C KD";
        String b4 =  "2D 3H 5C 9S KH";

        //> < < ==
        assert( 0 > texasHoldemComparactor.compare(w1, b1) );
        assert( 0 < texasHoldemComparactor.compare(w2, b2) );
        assert( 0 < texasHoldemComparactor.compare(w3, b3) );
        assert( 0 == texasHoldemComparactor.compare(w4, b4) );


    }
}
