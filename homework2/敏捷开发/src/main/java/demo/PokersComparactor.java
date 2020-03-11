package demo;

/**
 * @author czf
 * @Date 2020/3/11 4:29 下午
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 同花顺＞铁支＞葫芦＞同花＞顺子＞三条＞两对＞对子＞散牌
 *
 * 相同type之后的判赢逻辑：
 *
 * 按一定规则对手牌降序排，然后比较字典序大小
 *
 * 1. 降序字典序列 （散牌，顺子，同花，同花顺
 *
 * 先取出相同的牌组，然后降序存放，然后降序存放剩余的牌
 *
 * 2. 降序对子 + 剩余降序子典序列 （对子，两对
 * 3. 比较三张相同的牌的大小（三条，葫芦
 * 4. 比较四张相同的牌的大小（铁支
 *
 * 因此，如果是{散牌，顺子，同花，同花顺}:
 *
 *
 */
public class PokersComparactor {
    private static HashMap<String, Integer> rule;
    private static HashMap<Character, Integer> encode;

    static {
        rule = new HashMap<String, Integer>();
        rule.put("sp", 1);
        rule.put("dz", 2);
        rule.put("ld", 3);
        rule.put("st", 4);
        rule.put("sz", 5);
        rule.put("th", 6);
        rule.put("hl", 7);
        rule.put("tz", 8);
        rule.put("ths",9);

        encode = new HashMap<Character, Integer>();
        encode.put('2', 1);
        encode.put('3', 2);
        encode.put('4', 3);
        encode.put('5', 4);
        encode.put('6', 5);
        encode.put('7', 6);
        encode.put('8', 7);
        encode.put('9', 8);
        encode.put('T', 9);
        encode.put('J', 10);
        encode.put('Q', 11);
        encode.put('K', 12);
        encode.put('A', 13);
    }

    /**
     * 返回0则说明a大，
     * 返回1则说明b大,
     * 返回-1则说明输入不合法
     * @param a
     * @param b
     * @return
     */
    public static int compare( String a, String b ){
        String typeA = decodeCard(a);
        String typeB = decodeCard(b);
        if ( rule.containsKey(typeA) && rule.containsKey(typeB) ){
            if ( rule.get(typeA)>rule.get(typeB) )
                return 0;
            else if (rule.get(typeA) < rule.get(typeB))
                return 1;
            else
                return -1; // 同种的实现方式
        }
        return -1; // 不合法情况
    }

    private static String decodeCard(String s) {
        /**
         * 1. 排序
         * 2. 填充变量：
         *      1. 是否是同一种花色 (5张)
         *      2. 是否是顺子 (5张相连)
         *      3. 有几个条子
         *      4. 有几个对子
         *      5. 有几个(四张相同大小的牌)
         * 3. 根据上面的信息，来以优先级递减的顺序判断类型
         */

        String [] cards = s.split(" ");
        if (cards.length<5) return "";
        // 根据大小排序
        Arrays.sort(cards, (a,b)->{
            return encode.get(a.charAt(0)) - encode.get(b.charAt(0));
        });
        boolean isFlush = false; // 同花
        boolean isStraight = false; // 顺子
        int FourCardsSame = 0, ThreeCardsSame=0, TwoCardsSame=0;

        Map<Character, Integer> colorT = new HashMap<>(); // 每个花色的出现次数
        Map<Character, Integer> cardsT = new HashMap<>(); // 每种牌的出现次数

        // 开始填充信息
        for ( String card:cards ){
            if (card.length()<2) return ""; // 非法
            char color = card.charAt(1);
            char digit = card.charAt(0);
            colorT.put(color, colorT.getOrDefault(color,0)+1);
            cardsT.put(digit, cardsT.getOrDefault(digit, 0)+1);
        }
        // 扫描花色
        for ( Map.Entry<Character, Integer> entry:colorT.entrySet() ){
            if ( entry.getValue()>=5 )
                isFlush = true;
        }

        // 扫描数字大小
        for( Map.Entry<Character, Integer> entry:cardsT.entrySet() ){
            if ( entry.getValue()>=4 )
                ++FourCardsSame;
            else if (entry.getValue()>=3)
                ++ThreeCardsSame;
            else if (entry.getValue()>=2)
                ++TwoCardsSame;
        }

        // 判断是否是顺子
        int cur, i=1, pre = encode.get(cards[0].charAt(0));
        while(i<cards.length){
            cur = encode.get(cards[i].charAt(0));
            if ( pre + 1 != cur )
                break;
            else pre = cur;
            ++i;
        }
        isStraight = i>=cards.length;

        // 判断类型
        if ( isFlush && isStraight )
            return "ths"; //同花顺
        else if (FourCardsSame>0)
            return "tz"; // 铁支
        else if (ThreeCardsSame>0 && TwoCardsSame>0 )
            return "hl"; //葫芦
        else if ( isFlush )
            return "th"; //同花
        else if ( isStraight )
            return "sz"; // 顺子
        else if ( ThreeCardsSame>0 )
            return "st"; // 三条
        else if ( TwoCardsSame>1 )
            return "ld"; // 两对
        else if ( TwoCardsSame>0 )
            return "dz"; // 对子
        return "sp"; //散牌


    }


}
