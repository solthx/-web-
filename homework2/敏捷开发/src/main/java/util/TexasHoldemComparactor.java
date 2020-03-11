package util;

import error.EmGameError;
import error.EmGameException;
import poker.Poker;
import poker.TexasHoldem;
import type.texasholdem.THType;

import java.util.*;

/**
 * 手牌比较器
 * @author czf
 * @Date 2020/3/11 7:32 下午
 */
public class TexasHoldemComparactor implements Comparator<String> {

    @Override
    public int compare(String playerA, String playerB) {
        List<TexasHoldem> PokersA = convertTHListFromTHString(playerA);
        List<TexasHoldem> PokersB = convertTHListFromTHString(playerB);
        THType typeA = null;
        THType typeB = null;
        try {
            typeB = checkType(PokersB);
            typeA = checkType(PokersA);
        } catch (EmGameException e) {
            e.printStackTrace();
            System.out.println(e.getErrMsg());
        }
        if ( typeA.getPriority() != typeB.getPriority() )
            return typeA.getPriority() - typeB.getPriority();
        return compare(  PokersA, PokersB );
    }

    int compare( List<TexasHoldem> pokersA, List<TexasHoldem> pokersB ){
        List<Integer> codeA = cardDescSort(pokersA);
        List<Integer> codeB = cardDescSort(pokersB);
        int i=codeA.size()-1;
        int j=codeB.size()-1;
        while(i>=0 && j>=0){
            if ( codeA.get(i) == codeB.get(j) ){
                --i;
                --j;
            }else if (codeA.get(i)>codeB.get(j))
                return 1;
            else return -1;
        }
        if (i<0 && j<0) return 0;
        return i>=0?1:-1;
    }

    private List<Integer> cardDescSort(List<TexasHoldem> pokers) {
        List<Integer> res = new ArrayList<>();
        for (Poker poker:pokers)
            res.add(THPriorityDecoder.decode.get(poker.getDigit()));
        Collections.sort(res);
        return res;
    }
    

    private THType checkType(List<TexasHoldem> pokersA) throws EmGameException {
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
        // 根据大小排序
        try {
            Collections.sort(pokersA, (a,b)->{
                return THPriorityDecoder.decode.get(a.getDigit()) - THPriorityDecoder.decode.get(b.getDigit());
            });
        }catch(NullPointerException e){
            e.printStackTrace();
            throw new EmGameException(EmGameError.WRONG_INPUT_FORMAT, "参数错误. 手牌大小范围必须在{2~9,T,J,Q,K,A}的范围内！");
        }

        boolean isFlush = false; // 同花
        boolean isStraight = false; // 顺子
        int FourCardsSame = 0, ThreeCardsSame=0, TwoCardsSame=0;

        Map<Character, Integer> colorT = new HashMap<>(); // 每个花色的出现次数
        Map<Character, Integer> cardsT = new HashMap<>(); // 每种牌的出现次数

        // 开始填充信息
        for (Poker poker:pokersA){
            char color = poker.getColor();
            char digit = poker.getDigit();
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
        int cur, i=1, pre = THPriorityDecoder.decode.get(pokersA.get(0).getDigit());
        while(i<pokersA.size()){
            cur = THPriorityDecoder.decode.get(pokersA.get(i).getDigit());
            if ( pre + 1 != cur )
                break;
            else pre = cur;
            ++i;
        }

        isStraight = i>=pokersA.size();

        // 判断类型
        if ( isFlush && isStraight )
            return THType.同花顺;
        else if (FourCardsSame>0)
            return THType.铁支;
        else if (ThreeCardsSame>0 && TwoCardsSame>0 )
            return THType.葫芦;
        else if ( isFlush )
            return THType.同花;
        else if ( isStraight )
            return THType.顺子;
        else if ( ThreeCardsSame>0 )
            return THType.三条;
        else if ( TwoCardsSame>1 )
            return THType.两对;
        else if ( TwoCardsSame>0 )
            return THType.对子;
        return THType.散牌;
    }


    private List<TexasHoldem> convertTHListFromTHString(String playerA) {
        String [] pokers = playerA.split(" ");
        List<TexasHoldem> texasHoldemList = new ArrayList<>();
        for(String poke:pokers){
            try {
                if (poke.length() != 2)
                    throw new EmGameException(EmGameError.WRONG_INPUT_FORMAT, "手牌格式错误！正确格式应为<大小><花色>");
            }catch (EmGameException ex) {
                ex.printStackTrace();
                System.out.println(ex.getErrMsg());
                continue;
            }
            TexasHoldem texasHoldem = new TexasHoldem();
            texasHoldem.setDigit(poke.charAt(0));
            texasHoldem.setColor(poke.charAt(1));
            texasHoldemList.add(texasHoldem);
        }
        return texasHoldemList;
    }
}

