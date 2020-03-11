package util;

import type.texasholdem.THType;

import java.util.HashMap;
import java.util.Map;

/**
 * 牌面大小 -> 实际优先级大小  的映射
 * @author czf
 * @Date 2020/3/11 8:38 下午
 */
public class THPriorityDecoder {
    public static final Map<Character, Integer> decode;
    static{
        decode = new HashMap<>();
        decode.put('2', 1);
        decode.put('3', 2);
        decode.put('4', 3);
        decode.put('5', 4);
        decode.put('6', 5);
        decode.put('7', 6);
        decode.put('8', 7);
        decode.put('9', 8);
        decode.put('T', 9);
        decode.put('J', 10);
        decode.put('Q', 11);
        decode.put('K', 12);
        decode.put('A', 13);
    }

}
