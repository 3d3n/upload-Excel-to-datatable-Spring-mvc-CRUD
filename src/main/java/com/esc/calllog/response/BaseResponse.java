package com.esc.calllog.response;

import java.io.InputStream;
import java.util.List;

public class BaseResponse {
    private String responsecode;
    private String responsemessage;
    private InputStream fileInputStream;
    private List<String[]> data;


    public BaseResponse() {
    }

    public BaseResponse(String responsecode, String responsemessage, InputStream fileInputStream) {
        this.responsecode = responsecode;
        this.responsemessage = responsemessage;
        this.fileInputStream = fileInputStream;
    }

    public BaseResponse(String responsecode, String responsemessage, List<String[]> data) {
        this.responsecode = responsecode;
        this.responsemessage = responsemessage;
        this.data = data;
    }

    public BaseResponse(String responsecode, String responsemessage) {
        this.responsecode = responsecode;
        this.responsemessage = responsemessage;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
