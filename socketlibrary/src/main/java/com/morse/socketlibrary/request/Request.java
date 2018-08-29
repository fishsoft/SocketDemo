package com.morse.socketlibrary.request;

import com.morse.socketlibrary.ThreadPoolManager;
import com.morse.socketlibrary.response.IResponse;
import com.morse.socketlibrary.socket.TCPSocket;

import java.util.Map;

public class Request implements IRequest {

    private String mIp;
    private int mPort;
    private Map<String, String> mParamter;
    private IResponse mResponse;

    @Override
    public void setUrl(String ip, int port) {
        mIp = ip;
        mPort = port;
    }

    @Override
    public void setParamter(Map<String, String> paramter) {
        mParamter = paramter;
    }

    @Override
    public void setCallback(IResponse response) {
        mResponse = response;
    }

    @Override
    public void excute() {
        ThreadPoolManager.getInstance().execute(new TCPSocket(mIp, mPort, mParamter, mResponse));
    }
}
