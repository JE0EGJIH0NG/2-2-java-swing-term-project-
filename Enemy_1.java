package Game;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
public class Enemy_1 extends JFrame {
	
	int x;
	int y;
	
	int dark_templar_size_w=70;
	int dark_templar_size_h=70;
	
	double hp=500;
	
	boolean survive=true;
	
	public Enemy_1(int x,int y) {
		this.x=x;
		this.y=y;

	}
	
	
/*___________나의 위치를 스레도로 계속 받고 닼템도 그 좌표기준으로 이동______________________________________________________________________________*/
	void enemy_1_move(int user_x,int user_y) {
		if(user_x<this.x && user_y<this.y) {
			this.x=this.x-10;	this.y=this.y-10;
		}
		else if(user_x>this.x && user_y<this.y) {
			this.x=this.x+10;	this.y=this.y-10;
		}
		else if(user_x>this.x && user_y>this.y) {
			this.x=this.x+10;	this.y=this.y+10;
		}
		else if(user_x<this.x && user_y>this.y) {
			this.x=this.x-10;	this.y=this.y+10;
		}
		//System.out.println(user_x+" |||||"+user_y);
		
	}
	
	/*__________살아있는지 체크______________________________________________________*/
	void check_survive(double hp) {//기본값 true
		
		/*죽을 떄 실행. */
		if(hp<=0) {
			
			survive=false;//survive를 false로
			this.hp=0.0;	//음수로 남은경우가 있으니 0.0으로 만들고
			
			Timer timer = new Timer();//Creates a new timer
			TimerTask task = new TimerTask() {//Creates a new timer task
				
				public void run() {//The action to be performed by this timer task
						//System.out.println("enemy 1 return");
						refill();//refill hp
						survive=true;//set survive true
				}
				
			};
			
			timer.schedule(task,10000);//(task to be scheduled,delay in milliseconds before task is to be executed)Schedules the specified task for execution after the specified delay
		}
		
	}
/*_____________hp refill_____________________________________________________*/
	void refill() {
		this.hp=500;
		this.x=800;
		this.y=200;
	}
	
	
}
