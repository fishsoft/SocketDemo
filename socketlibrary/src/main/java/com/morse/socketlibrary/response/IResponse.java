package com.morse.socketlibrary.response;

import java.io.InputStream;

/**
 * Socket响应接口
 * Created by admin on 2018/3/20.
 */
public interface IResponse {

    void onSuccess(InputStream inputStream);

    void onFailure();

}
