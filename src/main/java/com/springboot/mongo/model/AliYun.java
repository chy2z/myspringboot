package com.springboot.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AliYun implements Serializable {
	    private String factoryNo;  
	    private int eq;  
	    private double price;    
		private BigDecimal money;    
		private Date updateTime;
		public String getFactoryNo() {
			return factoryNo;
		}
		public void setFactoryNo(String factoryNo) {
			this.factoryNo = factoryNo;
		}
		public int getEq() {
			return eq;
		}
		public void setEq(int eq) {
			this.eq = eq;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public BigDecimal getMoney() {
			return money;
		}
		public void setMoney(BigDecimal money) {
			this.money = money;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}  		
}
