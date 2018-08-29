package com.morse.socketlibrary.socket;

import com.morse.socketlibrary.response.IResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by admin on 2018/3/20.
 */
public class UDPSocket extends AbstractSocket {

    public UDPSocket(String host, int port, Map<String, String> params, IResponse response) {
        super(host, port, params, response);
    }

    @Override
    public void send() {
        DatagramSocket socket = null;
        try {
            //客户端
            //1、定义服务器的地址、端口号、数据
            InetAddress address = InetAddress.getByName(mHost);
            byte[] data = getParams().getBytes();
            //2、创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, mPort);
            //3、创建DatagramSocket对象
            socket = new DatagramSocket();
            //4、向服务器发送数据
            socket.send(packet);


            //接受服务器端响应数据
            //======================================
            //1、创建数据报，用于接受服务器端响应数据
            byte[] data2 = new byte[1024];
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
            //2、接受服务器响应的数据
            socket.receive(packet2);
//            String reply = new String(data2, 0, packet2.getLength());
            receivce(new ByteArrayInputStream(data2));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!socket.isClosed()) {
                socket.close();
            }
        }
    }
}
