package com.morse.socketlibrary.socket;

import android.os.Handler;
import android.os.Looper;

import com.morse.socketlibrary.response.IResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class AbstractSocket implements ISocket, Runnable {

    protected String mHost;
    protected int mPort;
    protected Map<String, String> mParams;
    protected IResponse mResponse;

    public AbstractSocket(String host, int port, Map<String, String> params, IResponse response) {
        mHost = host;
        mPort = port;
        mParams = params;
        mResponse = response;
    }

    @Override
    public String getParams() {
        if (null == mParams || mParams.isEmpty()) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (String key : mParams.keySet()) {
            buffer.append(key).append("=").append(mParams.get(key)).append("&");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    @Override
    public void receivce(final InputStream inputStream) throws IOException {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (null != mResponse) {
                    mResponse.onSuccess(inputStream);
                }
            }
        });
    }

    @Override
    public void run() {
        send();
    }
}
