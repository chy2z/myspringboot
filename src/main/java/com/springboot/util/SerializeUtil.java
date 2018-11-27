package com.springboot.util;

import com.springboot.model.RequestResult;

import java.io.*;

/** 
* @Title: SerializeUtil
* @Description:
* @author chy
* @date 2018/9/4 23:03 
*/
public class SerializeUtil {

    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] Serizlize(Object object){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos != null){
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object Deserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static  void  main(String[] args){
        RequestResult requestResult=new RequestResult();
        requestResult.setException("error");
        requestResult.setCode(1);
        requestResult.setTip("tip");
        byte[] bytes=Serizlize(requestResult);
        Object object= Deserialize(bytes);
        System.out.println(object.getClass());
    }
}
