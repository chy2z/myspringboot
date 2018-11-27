package com.springboot.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
* @Title: TransferTo
* @Description: 零拷贝传输文件
* @author chy
* @date 2018/8/2 11:17
*/
public class TransferTo {
    public static void main(String[] args) throws IOException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                TransferToServer dns = new TransferToServer();
                dns.mySetup();
                dns.readData();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    TransferToClient sfc = new TransferToClient();
                    sfc.testSendfile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class  TransferToClient {

        public void testSendfile() throws IOException {
            String host = "localhost";
            int port = 9026;
            SocketAddress sad = new InetSocketAddress(host, port);
            SocketChannel sc = SocketChannel.open();
            sc.connect(sad);
            sc.configureBlocking(true);

            String fname = "src/main/resources/zerocopy/a.txt";
            FileChannel fc = new FileInputStream(fname).getChannel();
            long start = System.nanoTime();
            long nsent = 0, curnset = 0;
            curnset = fc.transferTo(0, fc.size(), sc);
            System.out.println("发送的总字节数:" + curnset
                    + " 耗时(ns):"
                    + (System.nanoTime() - start));
            try {
                sc.close();
                fc.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    static class TransferToServer {
        ServerSocketChannel listener = null;

        protected void mySetup() {
            InetSocketAddress listenAddr = new InetSocketAddress(9026);

            try {
                listener = ServerSocketChannel.open();
                ServerSocket ss = listener.socket();
                ss.setReuseAddress(true);
                ss.bind(listenAddr);
                System.out.println("监听的端口:" + listenAddr.toString());
            } catch (IOException e) {
                System.out.println("端口绑定失败 : "
                        + listenAddr.toString() + " 端口可能已经被使用,出错原因: "
                        + e.getMessage());
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            TransferToServer dns = new TransferToServer();
            dns.mySetup();
            dns.readData();
        }

        private void readData() {
            ByteBuffer dst = ByteBuffer.allocate(4096);
            try {
                while (true) {
                    SocketChannel conn = listener.accept();
                    System.out.println("创建的连接: " + conn);
                    conn.configureBlocking(true);
                    int nread = 0;
                    while (nread != -1) {
                        try {
                            nread = conn.read(dst);
                        } catch (IOException e) {
                            e.printStackTrace();
                            nread = -1;
                        }
                        dst.rewind();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
