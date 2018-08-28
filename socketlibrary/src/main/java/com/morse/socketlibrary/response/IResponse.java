package com.morse.socketlibrary.response;

/**
 * Socket响应接口
 * Created by admin on 2018/3/20.
 */
public interface IResponse {

    void onSuccess(String response);

    void onFailure();

}
