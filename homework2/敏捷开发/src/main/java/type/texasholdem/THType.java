package type.texasholdem;

/**
 * 德州扑克的手牌类型
 *
 * Straight Flush ＞ Iron Branch ＞ Gourds ＞ Flush Straight ＞ Three Strips ＞ Two Pairs ＞ Pairs ＞ Scatter
 * 同花顺＞铁支＞葫芦＞同花＞顺子＞三条＞两对＞对子＞散牌
 * @author czf
 * @Date 2020/3/11 7:44 下午
 */
public enum THType {
    同花顺(9),
    铁支(8),
    葫芦(7),
    同花(6),
    顺子(5),
    三条(4),
    两对(3),
    对子(2),
    散牌(1)
    ;

    public Integer getPriority() {
        return priority;
    }

    private final Integer priority;

    private THType(Integer priority) {
        this.priority = priority;
    }
}
