package com.fishgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 鱼对象类
 */
class Fish extends Thread { // 将鱼作为一个独立线程

 BufferedImage image; // 鱼显示的当前图片
 int width; // 鱼图片的宽度
 int height; // 鱼图片的高度
 int x; // 鱼的x坐标
 int y; // 鱼的y坐标
 int step; // 鱼游动的速度
 int index; // 鱼图片轮转
 boolean catched; // 鱼是否被捕获
 String name;
 BufferedImage catch01;
 BufferedImage catch02;

 /** Fish类的构造方法，用来给属性赋值 */
 public Fish(String name) throws Exception {
  // 创建鱼图片数组
  image =  ImageIO.read(new File("images/"+name+".png"));
  this.name = name;
  width = (int)(image.getWidth()/1.3); // 获取当前图片的宽度
  height = (int)(image.getHeight()/1.3); // 获取当前图片的高度
  Random r = new Random();
  x = r.nextInt(800 - width); // 给鱼的x坐标赋值
  y = r.nextInt(260 - height)+290;// 给鱼的y坐标赋值
  step = r.nextInt(13) + 1; // 初始化鱼的速度(1~5)
  catched = false;

 }

 /** 鱼游动的方法 */
 public void move() {
  
  x -= step; // 让鱼从右向左游动
  if (x < -width) { // 当鱼游出界面,重新从右边进入
   x = 800;
   Random r = new Random();
   y = r.nextInt(260 - height)+290;
   step = r.nextInt(20) + 1;
  }
 }

 // 线程方法，系统自动调用
 public void run() {
  while (true) {
   try {
    // 如果鱼被捕获
    if (catched) {
     catched = false; // 2、catched=false;
     getOut(); // 3、鱼消失
    } else {
     move(); // 否则鱼继续游动
     Thread.sleep(50);
    }
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
 }


 /** 鱼滚出界面 */
 public void getOut() {
  x = 800;
  Random r = new Random();
  y = r.nextInt(260 - height)+290;
  step = r.nextInt(20) + 1;
 }
}