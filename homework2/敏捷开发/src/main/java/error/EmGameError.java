package error;

/**
 * 德州扑克游戏错误信息
 * @author czf
 * @Date 2020/3/11 8:04 下午
 */
public enum EmGameError implements BaseError {
    WRONG_INPUT_FORMAT("输入格式错误", 10001);

    private String errMsg;
    private final Integer errCode;

    private EmGameError(String errMsg, Integer errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public BaseError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
