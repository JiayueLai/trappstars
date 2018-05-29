package com.fishgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class Pool extends JPanel{
	Graphics pic;
	BufferedImage bg; // ����ͼ
	BufferedImage boat;
	BufferedImage hook;
	Hook gouzi;
	Fish[] fishes; // ����һ���������
	Fish[] bad_fishes;
	int count; // ����
	int score; // �Ʒ�
	int life=3;
	boolean run=true;
	
	public Pool() {
		try {
			bg = ImageIO.read(new File("images/bg.jpg")); // ���ر���ͼƬ
			boat = ImageIO.read(new File("images/boat.png"));
			
			fishes = new Fish[6]; // ����������,����9
			bad_fishes = new Fish[8];
			for (int i = 0; i < 6; i++) { // ���������,����������
				fishes[i] = new Fish("good");
				
			}
			for(int i = 0; i< 8;i++) {
				bad_fishes[i] = new Fish("bad");
			}

			
			gouzi = new Hook(); 
			
		}catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		
		
		g.drawImage(bg, 0, -20,800,570, null);
		
		for (int i = 0; i < fishes.length; i++) { // �������������е���
			g.drawImage(fishes[i].image, fishes[i].x, fishes[i].y,fishes[i].width,fishes[i].height, null);
		}
		for (int i = 0; i < bad_fishes.length; i++) { // �������������е���
			g.drawImage(bad_fishes[i].image, bad_fishes[i].x, bad_fishes[i].y,bad_fishes[i].width,bad_fishes[i].height, null);
		}
		
		g.drawImage(boat,400,70,190,150,null);
		g.drawLine(528, 215, 528, gouzi.y);
		g.drawImage(gouzi.image, gouzi.x, gouzi.y, 10,20,null);

		Font f = new Font("", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("count:" + count, 720, 20);
		g.drawString("score:" + score, 720, 40);
		g.drawString("life:"+life, 720, 60);

		if(!run) g.drawString("Game Over",300,300);

	}

	/** ��Pool�������Ӳ��㷽�� */
	public void catchFish() {
		
		for (int i = fishes.length - 1; i >= 0; i--) {
			/** �ж����Ƿ񱻲��� */
			
			if (life==0)  {
				run=false;
			return;}
			else if (gouzi.catchThe(fishes[i])) {
				
				if(fishes[i].name.equals("good") ){
					count++; // ����
					if (fishes[i].width > 100)  // �Ʒ�
						score += 15;
					else 
						score += fishes[i].width / 10;
				
					fishes[i].catched = true; // 
					break; // �˳�ѭ��
				}
				else if(fishes[i].name.equals("bad")) life--;
			}
		}
		for (int i = bad_fishes.length - 1; i >= 0; i--) {
			/** �ж����Ƿ񱻲��� */
			
			if (life==0)  {
				run=false;
			return;}
			else if (gouzi.catchThe(bad_fishes[i])) {
				
				if(bad_fishes[i].name.equals("bad")) life--;
			}
		}
			
		
	}
	
	
	/** �������ζ��ķ��� */
	public void action() {
		// ����¼���
		MouseAdapter ma = new MouseAdapter() {
			// ��갴��
			@Override
			public void mousePressed(MouseEvent e) {
				//catchFish();// ��Pool�����Ӳ��㷽��
				gouzi.trying=true;
				gouzi.touch = false;
			}
		};
		
		/** ����������¼� */
		this.addMouseListener(ma);// ����¼�
		this.addMouseMotionListener(ma);// �϶��¼�
		
		for (int i = 0; i < fishes.length; i++) {
			fishes[i].start();
		}
		for (int i = 0; i < bad_fishes.length; i++) {
			bad_fishes[i].start();
		}
		
		// �����߳�
		gouzi.start();
		
		while (run) {
			catchFish();
			repaint();// ���»��ƽ���
			try {
				Thread.sleep(20); // �߳�����20ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}













