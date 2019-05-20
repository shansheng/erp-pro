package com.pt.common.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回值
 *
 * @author yanyx
 * @Date 2017-12-18
 */
@ApiModel(value = "ServiceResult")
public class ServiceResult<T> implements Serializable {

    @ApiModelProperty(value = "调用成功/失败标识")
    private Boolean success;
    @ApiModelProperty(value = "错误码")
    private Integer errCode;
    @ApiModelProperty(value = "错误信息")
    private String errMsg;
    @ApiModelProperty(value = "实体对象")
    private T resultBody;

    private List<T> list;

    private Map<String, Object> pagination = new LinkedHashMap<>();

    public ServiceResult() {
        super();
    }

    public ServiceResult(Boolean success, T resultBody, String errMsg, Integer errCode) {
        this.success = success;
        this.resultBody = resultBody;
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public ServiceResult(Boolean success, T resultBody) {
        this.success = success;
        this.resultBody = resultBody;
    }

    public ServiceResult success() {
        return success(Boolean.TRUE);
    }

    public ServiceResult success(Object resultBody) {
        return new ServiceResult(true, resultBody);
    }

    public ServiceResult failed(Integer errCode, String errMsg) {
        return new ServiceResult(false, null, errMsg, errCode);
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public T getResultBody() {
        return resultBody;
    }
    public Boolean getSuccess() {

        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, Object> getPagination() {
        return pagination;
    }

    public void setPagination(Map<String, Object> pagination) {
        this.pagination = pagination;
    }
}