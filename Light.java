package com.porsche.control.type;

import lombok.Data;

@Data
public class Light {
	private int x,y;                  //灯在画布上的位置
	private boolean status;           //灯的状态
	public Light(int x,int y,boolean isLight) {
		this.x=x;                
		this.y=y;
		this.status=isLight;
	}
}
