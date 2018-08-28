package com.morse.socketlibrary.socket;

import android.os.Handler;
import android.os.Looper;

import com.morse.socketlibrary.response.IResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * Created by admin on 2018/3/20.
 */
public class TCPSocket implements ISocket {

    private String mHost;
    private int mPort;
    private Map<String, String> mParams;
    private IResponse mResponse;

    public TCPSocket(String host, int port, Map<String, String> params, IResponse response) {
        mHost = host;
        mPort = port;
        mParams = params;
        mResponse = response;
    }

    public void tcp() {
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
            //3、获取输入流，并读取服务器端的响应信息
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            final StringBuffer buffer = new StringBuffer();
            String info = null;
            while ((info = br.readLine()) != null) {
                buffer.append(info);
//                System.out.println("我是客户端，服务器说：" + info);
            }

            //切换到主线程
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (null != mResponse) {
                        mResponse.onSuccess(buffer.toString());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、关闭资源
            try {
                br.close();
                is.close();
                pw.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取请求参数
     *
     * @return
     */
    @Override
    public String getParams() {
        StringBuffer buffer = new StringBuffer();
        for (String key : mParams.keySet()) {
            buffer.append(key).append("=").append(mParams.get(key)).append("&");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    @Override
    public void send(String host, int port, Map<String, String> params) {
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
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receivce(InputStream inputStream) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuffer buffer = new StringBuffer();
        String info = null;
        while ((info = br.readLine()) != null) {
            buffer.append(info);
        }

        //切换到主线程运行
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (null != mResponse) {
                    mResponse.onSuccess(buffer.toString());
                }
            }
        });
    }

}
