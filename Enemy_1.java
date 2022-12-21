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
	
	
/*___________���� ��ġ�� �������� ��� �ް� ���۵� �� ��ǥ�������� �̵�______________________________________________________________________________*/
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
	
	/*__________����ִ��� üũ______________________________________________________*/
	void check_survive(double hp) {//�⺻�� true
		
		/*���� �� ����. */
		if(hp<=0) {
			
			survive=false;//survive�� false��
			this.hp=0.0;	//������ ������찡 ������ 0.0���� �����
			
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
