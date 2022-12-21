package Game;

import java.awt.event.*;
import javax.swing.JFrame;
public class Terran extends JFrame implements KeyListener{
	Bonus b = new Bonus();
	boolean up=false;
	boolean down=false;
	boolean left=false;
	boolean right=false;
	boolean attack=false;
	
	int right_left=1;	//1�� ������ 2�� ����
	int up_down=0;//0�� �¿� , 1�� �� 2�� �ٿ�
	int x;//x��ǥ
	int y;//y��ǥ

	
	int attack_skill=0;	//1�� ��Ʈ�� ��ų ���
	int attack_now=0;//1�� ��Ʈ�� ������
	int attack_type=1; //1�� �ٰŸ� 2�� ���Ÿ�
	
	int terran_size_w=70;
	int terran_size_h=70;

	int hp=1250;//ȭ�������
	int mineral=3;
	double attack_Level_ad=1;
	double attack_Level_ap=0.5;
	int kill=0;
	
	public Terran(int x,int y) {
		this.x=x;
		this.y=y;
		//Thread thread2 = new Thread(this);
		//thread2.start();
	}
	
	public void getBonus() {
		int bonusNum=b.typeBonus();
		
		if (bonusNum==0){
			int heal=b.heal();
			this.hp+=heal;
		}
		else if (bonusNum==1) {
			int getMineral=b.mineral();
			this.mineral+=getMineral;
		}
		else if (bonusNum==2) {
			float level=b.ability();
			this.attack_Level_ad+=level;
			this.attack_Level_ap+=(level/2);
		}
		
		
	}
	
	
	//public void run() {
		//try {
		//	this.x=x;
			//this.y=y;
			//Thread.sleep(1000);
		//}catch(Exception e) {
			
		//}
//}
	
	@Override
	public void keyTyped(KeyEvent e) {	
		if(e.getKeyChar()=='p') {
				if(attack_type==1) {
					attack_type=2;
					//System.out.println("attacktype");
				}
				else if(attack_type==2) {
					attack_type=1;
					//System.out.println("attacktype111111111111111");
				}
		}
		
		if(e.getKeyChar()=='m' && this.mineral>0 && attack_skill==0) {
			mineral-=1;
			attack_skill=1;
		}
		
	}
	//L�� ���� ���� ���¿����� �����̴µ� 
	//L�� ���߿� ������ ���� �ȵ�
	

	
	public void keyPressed(KeyEvent e) {

		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_D ://���������� ���� ���

			
			attack=false;attack_now=0;
			
			right_left=1;	//ĳ���Ͱ� �������� ���� ������ 1�� ��Ʈ�Ѵ�.
			up_down=0;
			if(y>300  && y<560 && x>505 && x<795) {//�̵� ���� ����//������
				x-=3;
				break;
			}
			if(x<1200) {	//x���� â�� ������ �Ѿ�� �ʴ� ���

				if(up==true) {	
					y-=1;
				}
				if(down==true) {
					y+=1;
				}
				//~~~~test
				//if(up==true && attack_now==1) {
					//y-=1;
				
				//}
				right=true; 
				x+=1; 
				repaint();
			}
			break;
			
		case KeyEvent.VK_A://�������� ���� ���
			attack=false;attack_now=0;
			right_left=2;
			up_down=0;
			
			if(y>300  && y<560 && x>505 && x<795) {//�̵� ���� ����//������
				x+=3;
				break;
			}
			if(x>0) {

				if(up==true) {
					y-=1;
				}
				if(down==true) {
					y+=1;
				}
				
				left=true;
				x-=1;
				repaint();
			}
			break;
			
		case KeyEvent.VK_W://���� ���� ���
			attack=false;attack_now=0;
			up_down=1;
			if(y>300  && y<560  && x>505 && x<795) {//�̵� ���� ����//������
				y+=3;//�Ʒ��� �̵����Ѽ� keypressed�� ���� break�� ������ �Ʒ� ���� ���� �ȵǰ� ��ǥ�� �״�� �����Ǵ� ���� ����
				break;
			}
			
			if(y>37) {

				if(right==true) {
					x+=1;
				}
				if(left==true) {
					x-=1;
				}
				
				up=true; y-=1; repaint();
			}
			
			break;
			
		case KeyEvent.VK_S:
			attack=false;attack_now=0;
			up_down=2;
			if( y>300  && y<560 && x>505 && x<795) {//�̵� ���� ����//������
				y-=3;
				break;
			}
			if(y<600) {

				if(right==true) {
					x+=1;
				}
				if(left==true) {
					x-=1;
				}
				
				down=true; y+=1; repaint();
			}
			break;
		
		case KeyEvent.VK_L:
		//	System.out.println("attack now pressed "+attack_now);
			attack_now=1;
			repaint();
			break;			
			
	//	case KeyEvent.VK_M:
			//if(this.mineral>0) {
				//mineral-=1;
			//	attack_skill=1;
		//	}
			
			
		default:
			break;
		}
	
	
	}

	//keyreleased�� �Ͼ�� ���ݸ�� ������
	@Override
	public void keyReleased(KeyEvent e) {
		up=false;
		right=false;
		left=false;
		down=false;
		attack=false;
		attack_now=0;
		attack_skill=0;
	}
	

	
}


/*case KeyEvent.VK_RIGHT:
x+=1;
repaint();
System.out.println("terran�� keypressed");
break;
	//����
case KeyEvent.VK_LEFT:
x-=1;
repaint();
break;
case KeyEvent.VK_UP&&KeyEvent.VK_RIGHT:
y-=1;
repaint();
break;*/

/*//right
if(e.getKeyCode()==KeyEvent.VK_RIGHT && e.getKeyCode()!=KeyEvent.VK_LEFT && e.getKeyCode()!=KeyEvent.VK_UP && e.getKeyCode()!=KeyEvent.VK_DOWN) {
	x+=1;repaint();
}
//left
else if(e.getKeyCode()!=KeyEvent.VK_RIGHT && e.getKeyCode()==KeyEvent.VK_LEFT && e.getKeyCode()!=KeyEvent.VK_UP && e.getKeyCode()!=KeyEvent.VK_DOWN) {
	x-=1;repaint();
}
//up
else if(e.getKeyCode()!=KeyEvent.VK_RIGHT && e.getKeyCode()!=KeyEvent.VK_LEFT && e.getKeyCode()==KeyEvent.VK_UP && e.getKeyCode()!=KeyEvent.VK_DOWN) {
	y-=1;repaint();
}
//down
else if(e.getKeyCode()!=KeyEvent.VK_RIGHT && e.getKeyCode()!=KeyEvent.VK_LEFT && e.getKeyCode()!=KeyEvent.VK_UP && e.getKeyCode()==KeyEvent.VK_DOWN) {
	y+=1;repaint();
}
//right n up
else if(e.getKeyCode()==KeyEvent.VK_RIGHT && e.getKeyCode()!=KeyEvent.VK_LEFT && e.getKeyCode()==KeyEvent.VK_UP && e.getKeyCode()!=KeyEvent.VK_DOWN) {
	y-=1;x+=1;repaint();
}*/

/*
public void move(boolean right,boolean left,boolean up,boolean down) {
System.out.println("move1"+right);
if(right==true && left==false && up==false && down==false) {
	x+=1;
	System.out.println("x+=1test");
}

else if(right==true && left==false && up==true && down==false) {
	x+=1;y-=1;
	System.out.println("x+=1 n y-= 1 test");
}
}*/
/*
if (right==true && left==false && up==false && down==false ) {
x+=1;
//right=false;
}
else if (right==false && left==true && up==false && down==false ) {
x-=1;
//left=false;
}
else if (right==false && left==false && up==true && down==false ) {
y-=1;
//up=false;
}
else if (right==false && left==false && up==false && down==true ) {
y+=1;
//down=false;
}
else if (right==true && left==false && up==true && down==false ) {
x+=1;	y-=1;
//right=false;	up=false;
}
else if (right==true && left==false && up==false && down==true ) {
x+=1;	y+=1;
//right=false;	down=false;
}
else if (right==false && left==true && up==true && down==false ) {
x-=1;	y-=1;
//left=false;		up=false;
}
else if (right==false && left==true && up==false && down==true ) {
x-=1;	y+=1;
//left=false; 	down=false;
}
*/