package com.esc.calllog.response;

import java.util.List;

public class RestResponse {
    //   @ApiModelProperty(value="", required=true)
    private String responseCode;

    // @ApiModelProperty(value="", required=true)
    private String responseMessage;


    //    @JsonIgnore
    private Object data;

    private List<Error> errors;

    public RestResponse() {
    }

    public RestResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public RestResponse(String responseCode, String responseMessage, Object data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
