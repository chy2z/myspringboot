package com.springboot.test;

import java.math.BigDecimal;

/**
 * Created by chy on 2018/5/9.
 */
public class BigDecimalTest {

   public static void main(String[] arg){

       BigDecimal Cost=new BigDecimal("2.51");

       BigDecimal pCost=new BigDecimal("0");

       BigDecimal money=pCost.add(Cost);

       System.out.println(Cost);

       System.out.println(money);
   }

}
