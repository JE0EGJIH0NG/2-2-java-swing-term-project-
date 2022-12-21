package Game;

import java.awt.event.*;

import javax.swing.JFrame;
public class Zerg extends JFrame implements KeyListener{
	Bonus b = new Bonus();
	boolean up=false;
	boolean down=false;
	boolean left=false;
	boolean right=false;
	boolean attack=false;
	
	int right_left=1;	//1은 오른쪽 2는 왼쪽 paint에서 사용
	int up_down=0;//0은 좌우 , 1은 업 2는 다운
	int x;//x좌표
	int y;//y좌표

	
	int attack_skill=0;	//1로 세트시 스킬 사용
	int attack_now=0;//1로 세트시 공격중
	int attack_type=1; //1은 근거리 2는 원거리
	
	int zerg_size_w=70;
	int zerg_size_h=70;

	int hp=1250;//화면사이즈
	int mineral=3;//마지막에 0으로 수정 test용으로 3개 줌
	double attack_Level_ad=1;//근접 공격력
	double attack_Level_ap=0.5;//원거리 공격력
	int kill=0;
	
	public Zerg(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
/*__________________처치 후 보상______________________________________________________*/
	public void getBonus() {
		int bonusNum=b.typeBonus();//0은 체력회복, 1은 자원 획득 , 2는 능력치 증가
		
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
	//____________keyTyped____________________________________________________________________
	public void keyTyped(KeyEvent e) {
		
		//p누르면 공격 모드 전환 1:근거리 , 2:원거리
		if(e.getKeyChar()=='p') {
				//ad to ap
				if(attack_type==1) {
					attack_type=2;
					//System.out.println("attack type");
				}
				//ap to ad
				else if(attack_type==2) {
					attack_type=1;
					//System.out.println("attacktype111111111111111");
				}
			}

		//m 누르면 스킬 사용
		if(e.getKeyChar()=='m' && this.mineral>0 && attack_skill==0) {
			mineral-=1;
			attack_skill=1;
		}
		
	}

//_______________________keyPressed____________________________________________________________	
	public void keyPressed(KeyEvent e) {

		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_D ://오른쪽으로 가는 경우

			attack=false;	
			attack_now=0;	
			right_left=1;	//캐릭터가 오른쪽을 보게 변수를 1로 세트한다.
			up_down=0;	//하이템플러 공격 이펙트 그릴 방향 위해서 
			
			if(y>300  && y<560 && x>505 && x<795) {//이동 제한 구간//꼭짓점
				x-=3;//없으면 못 움직임
				break;
			}
			
			if(x<1200) {	//x값이 창의 범위를 넘어가지 않는 경우

				if(up==true) {	//W키를 누르고 바로 D를 누른 경우
					y-=1;	//오른쪽 위로 대각선 이동해야하니 y좌표도 수정
				}
				if(down==true) {	//S키를 누르고 바로 D를 누른 경우
					y+=1;	//오른쪽 아래로 대각선 이동해야하니 y좌표도 수정
				}
			 
				right=true;//아래 대각선 이동을 위해서 
				x+=1; //이동이동이동
				repaint();
			}
			break;
			
		case KeyEvent.VK_A://왼쪽으로 가는 경우
			attack=false;
			attack_now=0;
			right_left=2;	//왼쪽을 보게 변수를 2로 세트
			up_down=0;
			
			if(y>300  && y<560 && x>505 && x<795) {//이동 제한 구간//꼭짓점
				x+=3;//없으면 못 움직임
				break;
			}
			
			if(x>0) {//창을 인벗어나면

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
			
		case KeyEvent.VK_W://위로 가는 경우
			attack=false;
			attack_now=0;
			up_down=1;//하이템플러 공격이펙트 위로 그리기 변수
			
			if(y>300  && y<560  && x>505 && x<795) {//이동 제한 구간//꼭짓점
				y+=3;//아래로 이동시켜서 keypressed때 여기 break로 빠져서 아래 문장 실행 안되고 좌표가 그대로 고정되는 현상 수정
				break;
			}
			
			if(y>37) {

				if(right==true) {
					x+=1;
				}
				if(left==true) {
					x-=1;
				}
				
				up=true; 
				y-=1; 
				repaint();
			}
			
			break;
			
		case KeyEvent.VK_S: //아래로
			attack=false;
			attack_now=0;
			up_down=2;//하이템플러 공격 이미지 아래로
			
			if( y>300  && y<560 && x>505 && x<795) {//이동 제한 구간//꼭짓점
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
				
				down=true; 
				y+=1; 
				repaint();
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

	//keyreleased가 일어나야 공격모션 없어짐
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
System.out.println("zealot의 keypressed");
break;
	//왼쪽
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