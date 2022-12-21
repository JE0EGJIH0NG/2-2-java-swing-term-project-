package Game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Enemy_2 extends JFrame {
	
	int x;
	int y;
	
	int siegeTank_size_w=100;
	int siegeTank_size_h=100;
	
	double hp=300;
	//int attack_Level=1;
	
	boolean survive=true;
	
	public Enemy_2(int x,int y) {
		this.x=x;
		this.y=y;

	}
	
	//int user_x,user_y;
	void check_survive(double hp) {
		if(hp<=0) {
			survive=false;
			this.hp=0.0;
			
			
			Timer m_timer = new Timer();
			TimerTask m_task = new TimerTask() {
				public void run() {
					if(survive==false) {
						System.out.println("enemy 2 return");
						refill();
						survive=true;
					}

				}
			};
			m_timer.schedule(m_task,10000);
			
		
		}
	}
	
	void refill() {
		this.hp=120;
	}
}