package Game;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.Color;
public class MainC extends JFrame implements KeyListener,Runnable {
	
	CrushCheck check_darkTemplar;
	CrushCheck check_zealot_devourer;
	CrushCheck check_siegeTank;
	
	CrushCheck skill_darkTemplar;
	CrushCheck skill_siegeTank;
	CrushCheck skill_devourer;
	
	int zealot_start_x=400;//x좌표
	int zealot_start_y=500;//y좌표
	int enemy_1_start_x=800;
	int enemy_1_start_y=200;
	int enemy_2_start_x=1130;
	int enemy_2_start_y=450;
	int enemy_3_start_x=100;
	int enemy_3_start_y=100;
	
	

	
	Zealot zealot = new Zealot(zealot_start_x,zealot_start_y);
	Image zealot_45 = new ImageIcon("zealot_45.gif").getImage();
	Image zealot_L = new ImageIcon("zealot_L.gif").getImage();
	Image zealot_attack_R = new ImageIcon("zealot_attack_R.gif").getImage();
	Image zealot_attack_L = new ImageIcon("zealot_attack_L.gif").getImage();
	Image templar_R = new ImageIcon("templar_R.gif").getImage();
	Image templar_L = new ImageIcon("templar_L.gif").getImage();
	Image templat_attack = new ImageIcon("templar_attack_배경제거.gif").getImage();
	
	Enemy_1 darkTemplar = new Enemy_1( enemy_1_start_x, enemy_1_start_y);
	Image darkTemplar_R = new ImageIcon("dark_templar_R.gif").getImage();
	Image darkTemplar_L = new ImageIcon("dark_templar_L.gif").getImage();

	Enemy_2 siegeTank = new Enemy_2(enemy_2_start_x , enemy_2_start_y);
	Image siegeTank_no_attack = new ImageIcon("tank_siege_no_attack.png").getImage();
	Image siegeTank_attack = new ImageIcon("tank_siege_attackMode.gif").getImage();
	Image siegeTank_attackEffect = new ImageIcon("tank_attack_effect.gif").getImage();
	
	Enemy_3 devourer = new Enemy_3( enemy_3_start_x ,  enemy_3_start_y);
	Image devourer_R = new ImageIcon("devourer_R.gif").getImage();
	Image devourer_L = new ImageIcon("devourer_L.gif").getImage();
	
	Image skill = new ImageIcon("skill.gif").getImage();
	Image skill_crush = new ImageIcon("skill_crush.gif").getImage();
	
//__________________________________________________________________________________________________________________
	
	static String Name;//mainB에서 내 이름 전달 받을 예정
	
	public MainC(String saveMyName){
		Name=saveMyName;//내 이름
		this.addKeyListener(this);
		setTitle("2019 Main C");
		setBounds(0,0,1250,665);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Thread thread = new Thread(this);
		thread.start();

	}
	
//_____________________________________________________________________________________________________________________
	//계속 변하는 질럿 위치 받으려고
	int get_X() {
		int returnX=zealot.x;
		return returnX;
	}
	int get_Y() {
		int returnY=zealot.y;
		return returnY;
	}
	

//___________________________________________________________________________________________	
	public void run() {
		
		try {
			while(true) {
				
				darkTemplar.enemy_1_move(get_X(), get_Y());//그냥 변수값을 넣으면 초기 세팅 100,400값만 지정되며 getx,gety도 변수 재선언 안하면 그대로100,400나옴
				devourer.enemy_3_move(get_X(), get_Y());
				
				//나랑 닼템 충롤하면 내 체력 깍기
				check_darkTemplar = new CrushCheck(zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
				if(check_darkTemplar.check==true && darkTemplar.survive==true) {
					zealot.hp-=10;					
				}
				
				//나랑 디바우러 출돌하면 내 체력 깍기
				check_zealot_devourer = new CrushCheck(zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h );
				if(check_zealot_devourer.check==true && devourer.survive==true) {
					zealot.hp-=10;
				}
				
				//탱크는 x
				check_siegeTank = new CrushCheck(zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h);
				
				//스킬 사용시 스킬 이미지 구역 vs 닼템 이미지 구역
				skill_darkTemplar = new CrushCheck(zealot.x-150 , zealot.y-150 , 400 , 400 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_h , darkTemplar.dark_templar_size_w);
				if(skill_darkTemplar.check==true && darkTemplar.survive==true && zealot.attack_skill==1) {
					darkTemplar.hp-=20;
				}
					
				//스킬 이미지 구역 vs 탱크 구역
				skill_siegeTank = new CrushCheck(zealot.x-150 , zealot.y-150 , 400 , 400 , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_h , siegeTank.siegeTank_size_w);
				if(skill_siegeTank.check==true && siegeTank.survive==true && zealot.attack_skill==1) {
					siegeTank.hp-=20;
				}
				
				//스킬 이미지 구역 vs 디바우러 구역
				skill_devourer = new CrushCheck(zealot.x-150 , zealot.y-150 , 400 , 400 , devourer.x , devourer.y , devourer.devourer_size_h , devourer.devourer_size_w);
				if(skill_devourer.check==true && devourer.survive==true && zealot.attack_skill==1) {
					devourer.hp-=20;
				}
				if(zealot.hp<=0) {
					dispose();
					Main m = new Main();
					zealot.hp=1250;
				}
				Thread.sleep(500);//0.5초
				
			}
			
		}catch(Exception e) {
			
		}
	}
	
	
	
	
//___________________________________________________________________________________________		
	public void paint(Graphics g) {
		
		Image img = new ImageIcon("mapA.jpg").getImage();
		g.drawImage(img, 0,0,1250,660,this);
		
		g.setColor(Color.green);
		g.drawRect(0,0,150,100);
		g.drawString(Name + "의 체력:"+zealot.hp,10,50);
		g.drawString("자원: "+zealot.mineral,10,62);
		g.drawString("근접 공격력:" + String.format("%.3f",zealot.attack_Level_ad) , 10 ,74);//나중에 보너스때문에 소수점이 많이나오니 string.format사용
		g.drawString("원거리 공격력:" + String.format("%.3f",zealot.attack_Level_ap), 10 , 86);
		g.drawString("처치:" + zealot.kill , 10, 98);
		
		g.setColor(Color.magenta);
		g.drawRect(1100,0,150,100);
		g.drawString("DarkTemplar HP:"+darkTemplar.hp,1110,50);
		g.drawString("SiegeTank HP:"+siegeTank.hp,1110,62);
		g.drawString("Devourer HP:"+devourer.hp,1110,74);
		
		g.setColor(Color.black);
		g.drawRect(575,360,220,200);//[575-795][360-560]//꼭짓점
		
		
		g.setColor(Color.red);
		g.fill3DRect(0,650,zealot.hp,10,true);// true볼록 false오목 
		
//___________________________________________zealot______________________________________________________
		
		//스킬 사용시 나 vs 닼템
		if(zealot.attack_skill==1 && skill_darkTemplar.check==true && darkTemplar.survive==true) {
			g.drawImage(skill , zealot.x-150 , zealot.y-150 , 400 , 400, this);
			darkTemplar.check_survive(darkTemplar.hp);//지금 닼템 체력 넣어서 살았는지 아닌지 확인 겸 체력 음수로 남으면 0.0으로 만들고 false로 바꾸기
			g.drawImage(skill_crush  , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}	
		}
		
		//skill vs tank
		if(zealot.attack_skill==1 && skill_siegeTank.check==true && siegeTank.survive==true ) {
			g.drawImage(skill , zealot.x-150 , zealot.y-150 , 400 , 400, this);		
			siegeTank.check_survive(siegeTank.hp);
			g.drawImage(skill_crush  , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
			if(siegeTank.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();			
				}
			
		}
		
		//skill vs devourer
		if(zealot.attack_skill==1 && skill_devourer.check==true && devourer.survive==true ) {
			g.drawImage(skill , zealot.x-150 , zealot.y-150 , 400 , 400, this);		
			devourer.check_survive(devourer.hp);
			g.drawImage(skill_crush  , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
			if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();			
				}
			
		}
		
		//유저가 R, 공격은 x, 근거리
		if(zealot.right_left==1 && zealot.attack_now==0 && zealot.attack_type==1) {
			g.drawImage(zealot_45 , zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , this);
		}
		
		//유저가 L, 공격은 x, 근거리
		else if(zealot.right_left==2 && zealot.attack_now==0 && zealot.attack_type==1) {
			g.drawImage(zealot_L , zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , this);
		}
		
		//유저가 R, 공격은 O, 근거리
		else if(zealot.right_left==1 && zealot.attack_now==1 && zealot.attack_type==1) {
			g.drawImage(zealot_attack_R , zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , this);
			
			if(check_darkTemplar.check==true && darkTemplar.survive==true) {//유저와 다크템플러가 서로 공격
				darkTemplar.hp-=zealot.attack_Level_ad;//다크 템플러의 체력을 유저의 공격력만큼 감소시킨다.
				darkTemplar.check_survive(darkTemplar.hp);//다크 템플러가 생존했는지 체크한다.
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			if(check_siegeTank.check==true && siegeTank.survive==true) {
				siegeTank.hp-=zealot.attack_Level_ad;
				siegeTank.check_survive(siegeTank.hp);
				if(siegeTank.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}	
		}
	
		//유저가 L, 공격은 O, 근거리
		else if(zealot.right_left==2 && zealot.attack_now==1 && zealot.attack_type==1) {
			g.drawImage(zealot_attack_L , zealot.x , zealot.y , zealot.zealot_size_w , zealot.zealot_size_h , this);
			
			if(check_darkTemplar.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=zealot.attack_Level_ad;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			if(check_siegeTank.check==true && siegeTank.survive==true) {
				siegeTank.hp-=zealot.attack_Level_ad;
				siegeTank.check_survive(siegeTank.hp);
				if(siegeTank.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
		}
		
		
		
		//여기서부터 원거리 모드 공격은 x
		//유저가 R, 공격은 X, 원거리
		else if(zealot.right_left==1 && zealot.attack_now==0 && zealot.attack_type==2) {
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
		}
		//유저가 L, 공격은 X, 원거리
		else if(zealot.right_left==2 && zealot.attack_now==0 && zealot.attack_type==2) {
			g.drawImage(templar_L , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
		}
		
		//여기서부터 원거리 모드 공격시 
		//유저가 R, 공격은 O, 원거리, 오른쪽으로 공격 이팩트(up_down=0)
		else if(zealot.right_left==1 && zealot.attack_now==1 && zealot.attack_type==2 && zealot.up_down==0) {
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x+50 ,zealot.y-100 ,200 ,200 , this);//공격 이팩트 오른쪽
			
			/*원거리 공격, 공격 이펙트와 다크 템플러 충돌==true , 공격력만큼 다크 템플러 체력--  */
			CrushCheck check_templar_attack = new CrushCheck(zealot.x+50 , zealot.y-100 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {//공격 이팩트와 템플러가 출동 , 다크 템플러 생존시
				darkTemplar.hp-=zealot.attack_Level_ap;//공격력만큼 다크 템플러 체력 감소
				darkTemplar.check_survive(darkTemplar.hp);	//다크 템플러의 체력을 체크하여 hp 0미만시 survive변수 false
				if(darkTemplar.hp==0.0) {//다크 템플러 처치시
					zealot.kill++;	//유저의 킬 1증가
					zealot.getBonus();
				}
			}
			/*원거리 공격, 공격 이펙트와 디바우러 충돌==true , 공격력만큼 디바우러 체력--  */
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x+50 , zealot.y-100 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				//System.out.println("오른쪽");
				devourer.hp-=zealot.attack_Level_ap;
				//System.out.println("check");
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
		}
		
		//유저가 L, 공격은 O, 원거리, 왼쪽으로 공격 이팩트(up_down=0)
		else if(zealot.right_left==2 && zealot.attack_now==1 && zealot.attack_type==2  && zealot.up_down==0) {
			g.drawImage(templar_L , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x-150 ,zealot.y-100 ,200 ,200 , this);//공격 이팩트 왼쪽 	
			
			CrushCheck check_templar_attack = new CrushCheck(zealot.x-150 , zealot.y-100 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=zealot.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x-150 , zealot.y-100 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=zealot.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
		 }
		
		else if(zealot.right_left==1 && zealot.attack_now==1 && zealot.attack_type==2 && zealot.up_down==1) {//유저가 R, 공격은 O, 원거리, 위쪽으로 공격 이팩트(up_down=1)
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x-50 ,zealot.y-200 ,200 ,200 , this);//공격 이팩트 	위쪽
			
			CrushCheck check_templar_attack = new CrushCheck(zealot.x-50 , zealot.y-200 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=zealot.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x-50 , zealot.y-200 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=zealot.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
		}
		
		else if(zealot.right_left==1 && zealot.attack_now==1 && zealot.attack_type==2 && zealot.up_down==2) {//유저가 R, 공격은 O, 원거리, 아래쪽으로 공격 이팩트(up_down=2)
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x-50 ,zealot.y+50 ,200 ,200 , this);//공격 이팩트 아래쪽 	
			
			CrushCheck check_templar_attack = new CrushCheck(zealot.x-50 , zealot.y+50 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=zealot.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x-50 , zealot.y+50 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=zealot.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
		}
		
		else if(zealot.right_left==2 && zealot.attack_now==1 && zealot.attack_type==2 && zealot.up_down==1) {//유저가 L, 공격은 O, 원거리, 위쪽으로 공격 이팩트(up_down=1)
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x-50 ,zealot.y-200 ,200 ,200 , this);//공격 이팩트 	위쪽
			
			CrushCheck check_templar_attack = new CrushCheck(zealot.x-50 , zealot.y-200 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true) {
				darkTemplar.hp-=zealot.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x-50 , zealot.y-200 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=zealot.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
		}
		
		else if(zealot.right_left==2 && zealot.attack_now==1 && zealot.attack_type==2 && zealot.up_down==2) {//유저가 L, 공격은 O, 원거리, 아래쪽으로 공격 이팩트(up_down=2)
			g.drawImage(templar_R , zealot.x ,zealot.y ,zealot.zealot_size_w ,zealot.zealot_size_h , this);
			g.drawImage(templat_attack , zealot.x-50 ,zealot.y+50 ,200 ,200 , this);//공격 이팩트 아래쪽 	
			
			CrushCheck check_templar_attack = new CrushCheck(zealot.x-50 , zealot.y+50 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true) {
				darkTemplar.hp-=zealot.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( zealot.x-50 , zealot.y+50 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=zealot.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					zealot.kill++;
					zealot.getBonus();
				}
			}
		}
		
	//___________________________________________darkTemplar______________________________________________________		
		if(darkTemplar.survive==true) {
			
			if(darkTemplar.x >= zealot.x) {
				g.drawImage(darkTemplar_L , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
				}
			else if( darkTemplar.x < zealot.x) {
				g.drawImage(darkTemplar_R , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
			}
		
		
		}
		
		
	//______________________________________________siegeTank______________________________________________________________
		if(siegeTank.survive==true) {
			if(zealot.x>600 && zealot.x<1000 && zealot.y >300 ) {//시즈탱크 공격모드
				g.drawImage(siegeTank_attack , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
				g.drawImage(siegeTank_attackEffect , zealot.x-50 , zealot.y-70 , 150 , 150 , this );
				zealot.hp-=1;
			}
			else {
			g.drawImage(siegeTank_no_attack , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
			}
		}
		
	//___________________________________________________devourer_________________________________________________
		if(devourer.survive==true) {
			if(zealot.x >=devourer.x ) {
				g.drawImage(devourer_R , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
				}
			else if( zealot.x <devourer.x) {
				g.drawImage(devourer_L , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
			}
		}
		
	}

//___________________________________________________________________________________________		
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		zealot.keyTyped(e);
		//System.out.println("mainC의 keytyped");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		zealot.keyPressed(e);
		//System.out.println("mainC의 keypressed");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		zealot.keyReleased(e);
		//System.out.println("mainC의 릴리스");
	}
	
}
