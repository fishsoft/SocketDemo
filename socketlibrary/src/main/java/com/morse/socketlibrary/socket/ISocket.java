package com.morse.socketlibrary.socket;

import java.io.IOException;
import java.io.InputStream;

public interface ISocket {

    String getParams();

    void send();

    void receivce(InputStream inputStream) throws IOException;

}
