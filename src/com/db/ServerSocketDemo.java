package com.db;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * @author DB
 * @title: ServerSocketDemo
 * @projectName javaSocket
 * @description: TODO
 * @date 2022/4/8  15:24
 */
public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
            // 创建 Scanner 对象
            Scanner input = new Scanner(System.in);
            // 创建 ServerSocket 对象
            ServerSocket serverSocket = new ServerSocket(9999);
            // 等待客户端的连接
            System.out.println("等待客户端的连接...");
            // 当客户端连接成功后，会得到 Socket 对象
            Socket socket = serverSocket.accept();
            // 获得当前客户端的 IP 地址
            String clientIP = socket.getInetAddress().getHostAddress();
            System.out.println("【" + clientIP + "】连接成功..." + new Date());
            // 通过 Socket 对象获得 InputStream 和 OutputStream
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            // 借助于 InputStream 和 OutputStream 获得 BufferedReader 和 BufferedWriter
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(osw);

            // 通过 BufferedWriter 向客户端发送连接成功信息
            bw.write("服务端连接成功..." + new Date());
            bw.newLine();
            bw.flush();

            // 循环接收和发送信息
            while (true) {
                // 接收来自服务端的信息
                System.out.println("来自【" + clientIP + "】的信息 (" + new Date() + ") ：" + br.readLine());
                // 通过控制台输入信息并且进行发送
                bw.write(input.nextLine());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
