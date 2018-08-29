package com.morse.socketlibrary.socket;

import android.text.TextUtils;

import com.morse.socketlibrary.response.IResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * Created by admin on 2018/3/20.
 */
public class TCPSocket extends AbstractSocket {

    public TCPSocket(String host, int port, Map<String, String> params, IResponse response) {
        super(host, port, params, response);
    }

    @Override
    public void send() {
        if (TextUtils.isEmpty(mHost)) {
            throw new NullPointerException("ip is null");
        }
        Socket socket = null;
        OutputStream os = null;
        PrintWriter pw = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            //客户端
            //1、创建客户端Socket，指定服务器地址和端口
            socket = new Socket(mHost, mPort);
            //2、获取输出流，向服务器端发送信息
            os = socket.getOutputStream();//字节输出流
            pw = new PrintWriter(os);//将输出流包装成打印流
            pw.write(getParams());
            pw.flush();
            socket.shutdownOutput();

            receivce(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、关闭资源
            try {
                br.close();
                is.close();
                pw.close();
                os.close();
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
