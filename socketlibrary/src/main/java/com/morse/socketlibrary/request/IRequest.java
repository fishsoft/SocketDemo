package com.morse.socketlibrary.request;

import com.morse.socketlibrary.response.IResponse;

import java.util.Map;

/**
 * Socket请求接口
 * Created by admin on 2018/3/20.
 */
public interface IRequest {

    /**
     * 设置请求IP和端口
     *
     * @param ip
     * @param port
     */
    void setUrl(String ip, int port);

    /**
     * 设置请求参数
     *
     * @param paramter
     */
    void setParamter(Map<String, String> paramter);

    /**
     * 设置请求回掉
     *
     * @param response
     */
    void setCallback(IResponse response);

    /**
     * 执行请求
     */
    void excute();

}
