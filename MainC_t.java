package Game;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.Color;

public class MainC_t extends JFrame implements KeyListener,Runnable {
	
	CrushCheck check_darkTemplar;
	CrushCheck check_terran_devourer;
	CrushCheck check_siegeTank;
	
	CrushCheck skill_darkTemplar;
	CrushCheck skill_siegeTank;
	CrushCheck skill_devourer;
	
	int terran_start_x=400;//x��ǥ
	int terran_start_y=500;//y��ǥ
	int enemy_1_start_x=800;
	int enemy_1_start_y=200;
	int enemy_2_start_x=1130;
	int enemy_2_start_y=450;
	int enemy_3_start_x=100;
	int enemy_3_start_y=100;
	
	

	
	Terran terran = new Terran(terran_start_x,terran_start_y);
	Image terran_45 = new ImageIcon("m_R.gif").getImage();
	Image terran_L = new ImageIcon("m_L.gif").getImage();
	Image terran_attack_R = new ImageIcon("m_R_attack.gif").getImage();
	Image terran_attack_L = new ImageIcon("m_L_attack.gif").getImage();
	Image templar_R = new ImageIcon("fire_R.gif").getImage();
	Image templar_L = new ImageIcon("fire_L.gif").getImage();
	Image terran_attack = new ImageIcon("fire_R_attack_img.gif").getImage();
	Image terran_attack_L_ap = new ImageIcon("fire_L_attack_img.gif").getImage();
	
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
	
	Image skill = new ImageIcon("t_skill_b.gif").getImage();
	Image skill_crush = new ImageIcon("t_skill_a.gif").getImage();
//CrushCheck crushCheck = new CrushCheck(get_X() , get_Y() , terran.terran_size_w , terran.terran_size_h , get_X_E1(), get_Y_E1() , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);	
	
//___________________________________________________________	
	
	static String Name;
	public MainC_t(String saveMyName){
		Name=saveMyName;
		this.addKeyListener(this);
		setTitle("2019 Main C");
		setBounds(0,0,1250,665);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Thread thread = new Thread(this);
		thread.start();

	}
	
	//int get_hp() {
		//int returnX=terran.hp;
		//System.out.println(terran.x);
		//return returnX;
//	}
	//int get_Y_E1() {
	//	int returnY=darkTemplar.y;
	//	return returnY;
//	}	
	
//_____________________________________________________________________________
	
	int get_X() {
		int returnX=terran.x;
		return returnX;
	}
	int get_Y() {
		int returnY=terran.y;
		return returnY;
	}
	

//___________________________________________________________________________________________	
	public void run() {
		
		try {
			while(true) {
				darkTemplar.enemy_1_move(get_X(), get_Y());//�׳� �������� ������ �ʱ� ���� 100,400���� �����Ǹ� getx,gety�� ���� �缱�� ���ϸ� �״��100,400����
				devourer.enemy_3_move(get_X(), get_Y());
				
				check_darkTemplar = new CrushCheck(terran.x , terran.y , terran.terran_size_w , terran.terran_size_h , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
				if(check_darkTemplar.check==true && darkTemplar.survive==true) {
					terran.hp-=10;					
				}
				
				check_terran_devourer = new CrushCheck(terran.x , terran.y , terran.terran_size_w , terran.terran_size_h , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h );
				if(check_terran_devourer.check==true && devourer.survive==true) {
					terran.hp-=10;
				}
				
				check_siegeTank = new CrushCheck(terran.x , terran.y , terran.terran_size_w , terran.terran_size_h , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h);
				
				skill_darkTemplar = new CrushCheck(terran.x-150 , terran.y-150 , 400 , 400 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_h , darkTemplar.dark_templar_size_w);
				if(skill_darkTemplar.check==true && darkTemplar.survive==true && terran.attack_skill==1) {
					darkTemplar.hp-=20;
				}
							
				skill_siegeTank = new CrushCheck(terran.x-150 , terran.y-150 , 400 , 400 , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_h , siegeTank.siegeTank_size_w);
				if(skill_siegeTank.check==true && siegeTank.survive==true && terran.attack_skill==1) {
					siegeTank.hp-=20;
				}
				
				skill_devourer = new CrushCheck(terran.x-150 , terran.y-150 , 400 , 400 , devourer.x , devourer.y , devourer.devourer_size_h , devourer.devourer_size_w);
				if(skill_devourer.check==true && devourer.survive==true && terran.attack_skill==1) {
					devourer.hp-=20;
				}
				if(terran.hp<=0) {
					dispose();
					Main m = new Main();
					terran.hp=1250;
				}
				Thread.sleep(500);
				
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
		g.drawString(Name + "�� ü��:"+terran.hp,10,50);
		g.drawString("�ڿ�: "+terran.mineral,10,62);
		g.drawString("���� ���ݷ�:" + String.format("%.3f",terran.attack_Level_ad) , 10 ,74);
		g.drawString("���Ÿ� ���ݷ�:" + String.format("%.3f",terran.attack_Level_ap), 10 , 86);
		g.drawString("óġ:" + terran.kill , 10, 98);
		
		g.setColor(Color.magenta);
		g.drawRect(1100,0,150,100);
		g.drawString("DarkTemplar HP:"+darkTemplar.hp,1110,50);
		g.drawString("SiegeTank HP:"+siegeTank.hp,1110,62);
		g.drawString("Devourer HP:"+devourer.hp,1110,74);
		
		g.setColor(Color.black);
		g.drawRect(575,360,220,200);//[575-795][360-560]//������
		
		
		g.setColor(Color.red);
		g.fill3DRect(0,650,terran.hp,10,true);// a boolean value that determines whether the rectangle appears to be raised above the surface or etched into the surface.
		
//___________________________________________terran______________________________________________________
		
		//��ų ���� 
		if(terran.attack_skill==1 && skill_darkTemplar.check==true && darkTemplar.survive==true) {
			g.drawImage(skill , terran.x-150 , terran.y-150 , 400 , 400, this);
			darkTemplar.check_survive(darkTemplar.hp);
			g.drawImage(skill_crush  , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}	
		}
		
		if(terran.attack_skill==1 && skill_siegeTank.check==true && siegeTank.survive==true ) {
			g.drawImage(skill , terran.x-150 , terran.y-150 , 400 , 400, this);		
			siegeTank.check_survive(siegeTank.hp);
			g.drawImage(skill_crush  , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
			if(siegeTank.hp==0.0) {
					terran.kill++;
					terran.getBonus();			
				}
			
		}
		
		if(terran.attack_skill==1 && skill_devourer.check==true && devourer.survive==true ) {
			g.drawImage(skill , terran.x-150 , terran.y-150 , 400 , 400, this);		
			devourer.check_survive(devourer.hp);
			g.drawImage(skill_crush  , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
			if(devourer.hp==0.0) {
				terran.kill++;
				terran.getBonus();			
				}
			
		}
		
		if(terran.right_left==1 && terran.attack_now==0 &&terran.attack_type==1) {//������ R, ������ x, �ٰŸ�
			g.drawImage(terran_45 , terran.x , terran.y , terran.terran_size_w , terran.terran_size_h , this);
		}
		else if(terran.right_left==2 &&terran.attack_now==0 && terran.attack_type==1) {//������ L, ������ x, �ٰŸ�
			g.drawImage(terran_L , terran.x , terran.y , terran.terran_size_w , terran.terran_size_h , this);
		}
	
		
		
		else if(terran.right_left==1 &&terran.attack_now==1 && terran.attack_type==1) {//������ R, ������ O, �ٰŸ�
			g.drawImage(terran_attack_R , terran.x ,terran.y ,terran.terran_size_w , terran.terran_size_h , this);
			
			if(check_darkTemplar.check==true && darkTemplar.survive==true) {//������ ��ũ���÷��� ���� ����
				darkTemplar.hp-=terran.attack_Level_ad;//��ũ ���÷��� ü���� ������ ���ݷ¸�ŭ ���ҽ�Ų��.
				darkTemplar.check_survive(darkTemplar.hp);//��ũ ���÷��� �����ߴ��� üũ�Ѵ�.
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			if(check_siegeTank.check==true && siegeTank.survive==true) {
				siegeTank.hp-=terran.attack_Level_ad;
				siegeTank.check_survive(siegeTank.hp);
				if(siegeTank.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			//w
			
		}
	
		
		else if(terran.right_left==2 && terran.attack_now==1 && terran.attack_type==1) {//������ L, ������ O, �ٰŸ�
			g.drawImage(terran_attack_L , terran.x , terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			
			if(check_darkTemplar.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=terran.attack_Level_ad;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			if(check_siegeTank.check==true && siegeTank.survive==true) {
				siegeTank.hp-=terran.attack_Level_ad;
				siegeTank.check_survive(siegeTank.hp);
				if(siegeTank.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
		
			
		}
		
		
		
		//���⼭���� ���Ÿ� ��� ������ x
		else if(terran.right_left==1 && terran.attack_now==0 && terran.attack_type==2) {//������ R, ������ X, ���Ÿ�
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
		}
		else if(terran.right_left==2 && terran.attack_now==0 && terran.attack_type==2) {//������ L, ������ X, ���Ÿ�
			g.drawImage(templar_L , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
		}
		
		
		
		
		
		//���⼭���� ���Ÿ� ��� ���ݽ� ó�� �߰� ���� ���� o
		else if(terran.right_left==1 && terran.attack_now==1 && terran.attack_type==2 && terran.up_down==0) {//������ R, ������ O, ���Ÿ�, ���������� ���� ����Ʈ(up_down=0)
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack , terran.x+50 ,terran.y-100 ,200 ,200 , this);//���� ����Ʈ ������
			
			/*���Ÿ� ����, ���� ����Ʈ�� ��ũ ���÷� �浹==true , ���ݷ¸�ŭ ��ũ ���÷� ü��--  */
			CrushCheck check_templar_attack = new CrushCheck(terran.x+50 , terran.y-100 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {//���� ����Ʈ�� ���÷��� �⵿ , ��ũ ���÷� ������
				darkTemplar.hp-=terran.attack_Level_ap;//���ݷ¸�ŭ ��ũ ���÷� ü�� ����
				darkTemplar.check_survive(darkTemplar.hp);	//��ũ ���÷��� ü���� üũ�Ͽ� hp 0�̸��� survive���� false
				if(darkTemplar.hp==0.0) {//��ũ ���÷� óġ��
					terran.kill++;	//������ ų 1����
					terran.getBonus();
				}
			}
			/*���Ÿ� ����, ���� ����Ʈ�� ��ٿ췯 �浹==true , ���ݷ¸�ŭ ��ٿ췯 ü��--  */
			CrushCheck check_devourer_attack = new CrushCheck( terran.x+50 , terran.y-100 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				System.out.println("������");
				devourer.hp-=terran.attack_Level_ap;
				System.out.println("check");
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
		}
		
		else if(terran.right_left==2 && terran.attack_now==1 && terran.attack_type==2  && terran.up_down==0) {//������ L, ������ O, ���Ÿ�, �������� ���� ����Ʈ(up_down=0)
			g.drawImage(templar_L , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack_L_ap  , terran.x-150 ,terran.y-100 ,200 ,200 , this);//���� ����Ʈ ���� 	
			
			CrushCheck check_templar_attack = new CrushCheck(terran.x-150 , terran.y-100 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=terran.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( terran.x-150 , terran.y-100 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=terran.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
		 }
		
		else if(terran.right_left==1 && terran.attack_now==1 && terran.attack_type==2 && terran.up_down==1) {//������ R, ������ O, ���Ÿ�, �������� ���� ����Ʈ(up_down=1)
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack , terran.x-50 ,terran.y-200 ,200 ,200 , this);//���� ����Ʈ 	����
			
			CrushCheck check_templar_attack = new CrushCheck(terran.x-50 , terran.y-200 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=terran.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( terran.x-50 , terran.y-200 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=terran.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
		}
		
		else if(terran.right_left==1 && terran.attack_now==1 && terran.attack_type==2 && terran.up_down==2) {//������ R, ������ O, ���Ÿ�, �Ʒ������� ���� ����Ʈ(up_down=2)
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack , terran.x-50 ,terran.y+50 ,200 ,200 , this);//���� ����Ʈ �Ʒ��� 	
			
			CrushCheck check_templar_attack = new CrushCheck(terran.x-50 , terran.y+50 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true && darkTemplar.survive==true) {
				darkTemplar.hp-=terran.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( terran.x-50 , terran.y+50 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=terran.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
		}
		
		else if(terran.right_left==2 && terran.attack_now==1 && terran.attack_type==2 && terran.up_down==1) {//������ L, ������ O, ���Ÿ�, �������� ���� ����Ʈ(up_down=1)
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack_L_ap  , terran.x-50 ,terran.y-200 ,200 ,200 , this);//���� ����Ʈ 	����
			
			CrushCheck check_templar_attack = new CrushCheck(terran.x-50 , terran.y-200 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true) {
				darkTemplar.hp-=terran.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( terran.x-50 , terran.y-200 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=terran.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
		}
		
		else if(terran.right_left==2 && terran.attack_now==1 && terran.attack_type==2 && terran.up_down==2) {//������ L, ������ O, ���Ÿ�, �Ʒ������� ���� ����Ʈ(up_down=2)
			g.drawImage(templar_R , terran.x ,terran.y ,terran.terran_size_w ,terran.terran_size_h , this);
			g.drawImage(terran_attack_L_ap  , terran.x-50 ,terran.y+50 ,200 ,200 , this);//���� ����Ʈ �Ʒ��� 	
			
			CrushCheck check_templar_attack = new CrushCheck(terran.x-50 , terran.y+50 , 200 , 200 , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h);
			if(check_templar_attack.check==true) {
				darkTemplar.hp-=terran.attack_Level_ap;
				darkTemplar.check_survive(darkTemplar.hp);
				if(darkTemplar.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
			
			CrushCheck check_devourer_attack = new CrushCheck( terran.x-50 , terran.y+50 , 200 , 200 , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h);
			if(check_devourer_attack.check==true && devourer.survive==true) {
				devourer.hp-=terran.attack_Level_ap;
				devourer.check_survive(devourer.hp);
				if(devourer.hp==0.0) {
					terran.kill++;
					terran.getBonus();
				}
			}
		}
		
	//___________________________________________darkTemplar______________________________________________________		
		if(darkTemplar.survive==true) {
			
			if(darkTemplar.x >= terran.x) {
				g.drawImage(darkTemplar_L , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
				}
			else if( darkTemplar.x < terran.x) {
				g.drawImage(darkTemplar_R , darkTemplar.x , darkTemplar.y , darkTemplar.dark_templar_size_w , darkTemplar.dark_templar_size_h , this);
			}
		
		
		}
		
		
	//______________________________________________siegeTank______________________________________________________________
		if(siegeTank.survive==true) {
			if(terran.x>600 && terran.x<1000 && terran.y >300 ) {//������ũ ���ݸ��
				g.drawImage(siegeTank_attack , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
				g.drawImage(siegeTank_attackEffect , terran.x-50 , terran.y-70 , 150 , 150 , this );
				terran.hp-=1;
			}
			else {
			g.drawImage(siegeTank_no_attack , siegeTank.x , siegeTank.y , siegeTank.siegeTank_size_w , siegeTank.siegeTank_size_h , this);
			}
		}
		
	//___________________________________________________devourer_________________________________________________
		if(devourer.survive==true) {
			if(terran.x >=devourer.x ) {
				g.drawImage(devourer_R , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
				}
			else if( terran.x <devourer.x) {
				g.drawImage(devourer_L , devourer.x , devourer.y , devourer.devourer_size_w , devourer.devourer_size_h , this);
			}
		}
		
	}

//___________________________________________________________________________________________		
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		terran.keyTyped(e);
		//System.out.println("mainC�� keytyped");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		terran.keyPressed(e);
		//System.out.println("mainC�� keypressed");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		terran.keyReleased(e);
		//System.out.println("mainC�� ������");
	}
	
}
