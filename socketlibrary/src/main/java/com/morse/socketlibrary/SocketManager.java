package com.morse.socketlibrary;

import com.morse.socketlibrary.request.RequestFactory;
import com.morse.socketlibrary.request.Type;
import com.morse.socketlibrary.response.IResponse;

import java.util.Map;

/**
 * Socket管理类
 * Created by admin on 2018/3/20.
 */
public class SocketManager {

    private String mIp;
    private int mPort;
    private Map<String, String> mParams;
    private IResponse mReponse;
    private Type mType;

    private SocketManager(BuildSocket buildSocket) {
        mIp = buildSocket.mIp;
        mPort = buildSocket.mPort;
        mParams = buildSocket.mParams;
        mReponse = buildSocket.mReponse;
        mType = buildSocket.mType;
    }

    public void request() {
        RequestFactory.build(mIp, mPort, mParams, mReponse, mType);
    }

    public static class BuildSocket {
        private String mIp;
        private int mPort;
        private Map<String, String> mParams;
        private IResponse mReponse;
        private Type mType;

        public BuildSocket(String ip, int port) {
            mIp = ip;
            mPort = port;
        }

        public BuildSocket setParams(Map<String, String> params) {
            mParams = params;
            return this;
        }

        public BuildSocket setResponse(IResponse response) {
            mReponse = response;
            return this;
        }

        public BuildSocket setType(Type type) {
            mType = type;
            return this;
        }

        public SocketManager build() {
            return new SocketManager(this);
        }

    }
}
