package com.db;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * @author DB
 * @title: SocketDemo
 * @projectName javaSocket
 * @description: TODO
 * @date 2022/4/8  15:26
 */
public class SocketDemo {
    public static void main(String[] args) {
        try {
            // 创建 Scanner 对象
            Scanner input = new Scanner(System.in);
            // 创建 Socket 对象
            Socket socket = new Socket("127.0.0.1", 9999);
            // 通过 Socket 对象获得 InputStream 和 OutputStream
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            // 借助于 InputStream 和 OutputStream 获得 BufferedReader 和 BufferedWriter
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(osw);

            // 为了是聊天循环进行下去，那么使用 while 循环
            while (true) {
                // 接收来自服务端的信息
                System.out.println("来自【服务端】的信息 (" + new Date() + ") ：" + br.readLine());
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
