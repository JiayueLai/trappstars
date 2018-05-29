package com.fishgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * �������
 */
class Fish extends Thread { // ������Ϊһ�������߳�

 BufferedImage image; // ����ʾ�ĵ�ǰͼƬ
 int width; // ��ͼƬ�Ŀ��
 int height; // ��ͼƬ�ĸ߶�
 int x; // ���x����
 int y; // ���y����
 int step; // ���ζ����ٶ�
 int index; // ��ͼƬ��ת
 boolean catched; // ���Ƿ񱻲���
 String name;
 BufferedImage catch01;
 BufferedImage catch02;

 /** Fish��Ĺ��췽�������������Ը�ֵ */
 public Fish(String name) throws Exception {
  // ������ͼƬ����
  image =  ImageIO.read(new File("images/"+name+".png"));
  this.name = name;
  width = (int)(image.getWidth()/1.3); // ��ȡ��ǰͼƬ�Ŀ��
  height = (int)(image.getHeight()/1.3); // ��ȡ��ǰͼƬ�ĸ߶�
  Random r = new Random();
  x = r.nextInt(800 - width); // �����x���긳ֵ
  y = r.nextInt(260 - height)+290;// �����y���긳ֵ
  step = r.nextInt(13) + 1; // ��ʼ������ٶ�(1~5)
  catched = false;

 }

 /** ���ζ��ķ��� */
 public void move() {
  
  x -= step; // ������������ζ�
  if (x < -width) { // �����γ�����,���´��ұ߽���
   x = 800;
   Random r = new Random();
   y = r.nextInt(260 - height)+290;
   step = r.nextInt(20) + 1;
  }
 }

 // �̷߳�����ϵͳ�Զ�����
 public void run() {
  while (true) {
   try {
    // ����㱻����
    if (catched) {
     catched = false; // 2��catched=false;
     getOut(); // 3������ʧ
    } else {
     move(); // ����������ζ�
     Thread.sleep(50);
    }
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
 }


 /** ��������� */
 public void getOut() {
  x = 800;
  Random r = new Random();
  y = r.nextInt(260 - height)+290;
  step = r.nextInt(20) + 1;
 }
}