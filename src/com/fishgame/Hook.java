package com.fishgame;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

class Hook extends Thread{
	BufferedImage image;
	int width;
	int height;
	int x;
	int y;
	boolean touch;
	boolean trying;
	
	public Hook() throws Exception {
		image = ImageIO.read( // ��������ͼƬ
				new File("images/hook.png"));
		width = 10; // ��ȡͼƬ�Ŀ��
		height = 20; // ��ȡͼƬ�ĸ߶�
		x = 520;
		y = 215;
		touch = trying = false;
		
	}
	
	public boolean catchThe(Fish fish) {
		  int dx = x- fish.x;
		  int dy =  fish.y- y;
		  boolean r = trying &&dx>0 && dy>0 && dx < fish.width && dy < fish.height;
		  if(r) {
		   touch = true;
		   trying = false;
		  }
		  return r;
		 }
	
	public void run() {
		while (true) {
			try {
				if(y>560) {
					touch = true;
					trying = false;
				}
				
				if (!touch&&trying) {
					y += 3;
				}else if (touch && !trying) {
					if(y>225)
					y -= 10;
				}
				if(y<215) {
					touch = false;
					trying = false;
				}
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

