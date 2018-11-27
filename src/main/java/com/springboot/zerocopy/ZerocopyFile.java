package com.springboot.zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
* @Title: ZerocopyFile
* @Description: 零拷贝文件
* @author chy
* @date 2018/8/2 10:45
*/
public class ZerocopyFile {

    /**
     * 方式1
     * @param from
     * @param to
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static void transferToDemo(String from, String to) throws IOException {
        FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
        FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
        fromChannel.close();
        toChannel.close();
    }

    /**
     * 方式2
     * @param from
     * @param to
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static void transferFromDemo(String from, String to) throws IOException {
        FileChannel fromChannel = new FileInputStream(from).getChannel();
        FileChannel toChannel = new FileOutputStream(to).getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.close();
        toChannel.close();
    }

    public static void main(String[] args) throws IOException {
        String from = "src/main/resources/zerocopy/a.txt";
        String to = "src/main/resources/zerocopy/b.txt";
        transferToDemo(from,to);
        //transferFromDemo(from, to);
    }
}
