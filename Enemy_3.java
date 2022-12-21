package Game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Enemy_3 extends JFrame {
	
	int x;
	int y;
	
	int devourer_size_w=100;
	int devourer_size_h=100;
	
	double hp=300;
	//int attack_Level=1;
	
	boolean survive=true;
	
	public Enemy_3(int x,int y) {
		this.x=x;
		this.y=y;

	}
	
	//int user_x,user_y;
/*__________스레드로 내 위치 받고 디바우러도 그거 기준으로 좌표 이동 이건 느리게 속도 5___________________________________________________________________*/	
	void enemy_3_move(int user_x,int user_y) {
		if(user_x<this.x && user_y<this.y) {
			this.x=this.x-5;	this.y=this.y-5;
		}
		else if(user_x>this.x && user_y<this.y) {
			this.x=this.x+5;	this.y=this.y-5;
		}
		else if(user_x>this.x && user_y>this.y) {
			this.x=this.x+5;	this.y=this.y+5;
		}
		else if(user_x<this.x && user_y>this.y) {
			this.x=this.x-5;	this.y=this.y+5;
		}
		//System.out.println(user_x+" |||||"+user_y);
		
	}
	
	void check_survive(double hp) {
		if(hp<=0) {
			survive=false;
			this.hp=0.0;
			
			
			Timer m_timer = new Timer();
			TimerTask m_task = new TimerTask() {
				public void run() {
					if(survive==false) {
						System.out.println("enemy 3 return");
						refill();
						survive=true;
					}

				}
			};
			m_timer.schedule(m_task,10000);
			
		
		}
	}
/*______부활 체력 리필______________________________*/
	void refill() {
		this.hp=300;
		this.x=100;
		this.y=10;
	}
}