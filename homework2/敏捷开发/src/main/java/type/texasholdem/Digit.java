package type.texasholdem;

/**
 * @author czf
 * @Date 2020/3/11 9:47 下午
 */
public enum Digit {
    ;
    private Integer digit;
    private Integer priority;

    Digit(Integer digit, Integer priority) {
        this.digit = digit;
        this.priority = priority;
    }

    public Integer getDigit() {
        return digit;
    }

    public Integer getPriority() {
        return priority;
    }
}
