package error;

/**
 * 德州扑克游戏的异常类实现
 *
 * @author czf
 * @Date 2020/3/11 8:00 下午
 */
public class EmGameException extends Exception implements BaseError {

    BaseError baseError;

    public EmGameException(BaseError baseError, String errMsg){
        super();
        baseError.setErrMsg(errMsg);
        this.baseError = baseError;
    }

    public EmGameException(BaseError baseError){
        super();
        this.baseError = baseError;
    }

    @Override
    public int getErrCode() {
        return this.baseError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.baseError.getErrMsg();
    }

    @Override
    public BaseError setErrMsg(String errMsg) {
        this.baseError.setErrMsg(errMsg);
        return this;
    }
}
