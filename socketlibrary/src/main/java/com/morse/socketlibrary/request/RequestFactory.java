package com.morse.socketlibrary.request;

import com.morse.socketlibrary.response.IResponse;

import java.util.Map;

/**
 * Request请求工厂
 */
public class RequestFactory {

    public static void build(String host, int port, Map<String, String> params, IResponse response, Type type) {
        if (Type.TCP.equals(type.getType())) {
            // TODO: 2018/8/29 tcp请求
            new Request().excute();
        } else if (Type.UDP.equals(type.getType())) {
            // TODO: 2018/8/29 udp请求
            new Request().excute();
        }
    }

}
