package indi.wuyue.batch.util;

public class Result<K> {

    private Boolean           success;

    private String            errorCode;

    private String            desc;

    private transient K       data;

    /**
     * 构造成功响应
     *
     * @param data
     * @param <K>
     * @return Result
     */
    public static <K> Result<K> successResult(K data) {
        Result<K> result = new Result<>();
        result.success = Boolean.TRUE;
        result.data = data;
        return result;
    }

    /**
     * 构造成功响应
     *
     * @param <K>
     * @return Result
     */
    public static <K> Result<K> successResult() {
        Result<K> result = new Result<>();
        result.success = Boolean.TRUE;
        return result;
    }

    /**
     * 构造失败响应
     *
     * @param message
     * @param errorCode
     * @return Result
     */
    public static <K> Result<K> failResult(String message, String errorCode) {
        Result<K> result = new Result<>();
        result.success = Boolean.FALSE;
        result.desc = message;
        result.errorCode = errorCode;
        return result;
    }

    /**
     * 构造失败响应
     *
     * @param errorCode
     * @param e
     * @return Result
     */
    public static <K> Result<K> failResult(String errorCode, Throwable e) {
        Result<K> result = new Result<>();
        result.success = Boolean.FALSE;
        result.desc = e.getMessage();
        result.errorCode = errorCode;
        return result;
    }

    /**
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * desc.
     *
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * data.
     *
     * @return the data
     */
    public K getData() {
        return data;
    }

    /**
     * data.
     *
     * @param data the data to set
     */
    public void setData(K data) {
        this.data = data;
    }

    /**
     * errorCode.
     *
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * errorCode.
     *
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
