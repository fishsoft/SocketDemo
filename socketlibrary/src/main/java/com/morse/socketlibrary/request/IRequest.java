package com.morse.socketlibrary.request;

import com.morse.socketlibrary.response.IResponse;

import java.util.Map;

/**
 * Socket请求接口
 * Created by admin on 2018/3/20.
 */
public interface IRequest {

    void setUrl(String ip, int port);

    void setParamter(Map<String, String> paramter);

    void setCallback(IResponse response);

    void excute();

}
