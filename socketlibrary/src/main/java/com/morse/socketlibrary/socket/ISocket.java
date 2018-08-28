package com.morse.socketlibrary.socket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface ISocket {

    String getParams();

    void send(String host, int port, Map<String, String> params);

    void receivce(InputStream inputStream) throws IOException;

}
