package error;


/**
 * 自定义异常错误接口
 *
 * @author czf
 * @Date 2020/3/11 8:03 下午
 */
public interface BaseError {
    public int getErrCode();
    public String getErrMsg();
    public BaseError setErrMsg(String errMsg);
}
