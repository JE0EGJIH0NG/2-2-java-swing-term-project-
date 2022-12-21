package Game;
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainB extends JFrame{
	
	String protoss="�����佺"; 
	String zerg="����";
	String terran="�׶�";
	ImageIcon protoss_select=new ImageIcon("protossSelct.png");
	ImageIcon zerg_select=new ImageIcon("zergSelct.png");
	ImageIcon terran_select=new ImageIcon("terranSelct.png");
	
	JButton okeyBtn; 
	JButton cancelBtn; 
	JButton nextBtn; 
	
	int okeyCnt=0;//1�� ���� ����ȭ��, 2�� ���� ���������� �Ѿ������ if�� ����
	int nextCnt=1;//1�� �׶�,2�� ����,3�� �����佺
	
	ImageIcon okeyBtnImgOld = new ImageIcon("okBtn.png");
	Image img1=okeyBtnImgOld.getImage();
	Image img2=img1.getScaledInstance(120,40,java.awt.Image.SCALE_SMOOTH);
	ImageIcon okeyBtnImg=new ImageIcon(img2);
	
	ImageIcon cancelBtnImgOld = new ImageIcon("cancelBtn.png");
	Image img11=cancelBtnImgOld.getImage();
	Image img22=img11.getScaledInstance(120,40,java.awt.Image.SCALE_SMOOTH);
	ImageIcon cancelBtnImg=new ImageIcon(img22);
	
	ImageIcon nextBtnImgOld = new ImageIcon("nextBtn.png");
	Image img111=nextBtnImgOld.getImage();
	Image img222=img111.getScaledInstance(240,80,java.awt.Image.SCALE_SMOOTH);
	ImageIcon nextBtnImg=new ImageIcon(img222);
	
	
	JTextField myName;//�Է�â
	String saveMyName;//������ �̸�
	
/*---------------------------------------------------------------------------------------------*/	
	MainB(){
		showMainB();	
		setTitle("2019 Main B");
		setBounds(0,0,1250,660);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/*---------------------------------------------------------------------------------------------*/		
	public void showMainB() {

		okeyBtn=new JButton(okeyBtnImg);
		nextBtn=new JButton(nextBtnImg);
		cancelBtn=new JButton(cancelBtnImg);
		myName=new JTextField("�г��� ����");
		
		okeyBtn.setSize(120,40);
		cancelBtn.setSize(120,40);
		okeyBtn.setLocation(520,345);
		cancelBtn.setLocation(640,345);
		myName.setBounds(515,283,253,40);
		myName.setBackground(Color.BLUE);
		
		setLayout(null);
		add(okeyBtn);
		add(cancelBtn);
		
//Ȯ�ι�ư �׼Ǹ�����-------------------------------------------------------
		okeyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				okeyCnt+=1;
				repaint();
	
				if(okeyCnt==2) {		
					dispose();//�� ������ ���� �� ���� ����ȭ������ �Ѿ
					
					if(nextCnt==3) {
						MainC c= new MainC(saveMyName);
						System.out.println(saveMyName);
					}
					
					else if(nextCnt==2) {
						//�߰� �ؾ��� (����)
						MainC_z c_z = new MainC_z(saveMyName);
					}
					
					else if(nextCnt==1) {
						//�߰��ؾ��� (�׶�)
						MainC_t c_t = new MainC_t(saveMyName);
						
					}
					
				}
			}
		});
		
//��ҹ�ư �׼Ǹ�����------------------------------------------------------------------
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
//���������Ҷ� ����� �ؽ�Ʈ��ư �׼Ǹ�����--------------------------------------------------------
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextCnt+=1;
				repaint();
				if(nextCnt>3) {
					nextCnt=1;
				}
			}
		});
	}

//--------------------------------------------------------------------------------------------------------
	public  void paint(Graphics g) {
		Image img =new ImageIcon("backgroundSelect.jpg").getImage();
		g.drawImage(img,0,0,1250,660,this);
		
	
		nextBtn.setSize(240,80);
		nextBtn.setLocation(520,390);

		//ó�� ȭ��
		if(okeyCnt==0) {
			add(myName);
			Image img2=new ImageIcon("mainB_okeyCnt_0_A.jpg").getImage();
			g.drawImage(img2,400,200,500,300,this);
		}
		//���� ȭ��
		else if(okeyCnt==1) {
			
			add(nextBtn);//�ؽ�Ʈ��ư �߰�
			saveMyName=myName.getText();//�ִٰ� ������ ���� �Է��� �̸�
			remove(myName);
			Image imgB = new ImageIcon("backgroundSelectB.jpg").getImage();
			g.drawImage(imgB,0,0,1250,660,this);
		
			if(nextCnt==1) {
				Image terranSelectImg = new ImageIcon("terranSelect.png").getImage();
				g.drawImage(terranSelectImg,500,80,300,300,this);
				
				Font stringFont=new Font("terran",Font.BOLD,60);
				g.setFont(stringFont);
				g.drawString(terran,585,90);
			
				okeyBtn.setLocation(520,338);
				cancelBtn.setLocation(640,338);
				okeyBtn.setSize(120,47);
				cancelBtn.setSize(120,47);
				nextBtn.setLocation(520,387);
				nextBtn.setSize(240,83);
				
			}
			else if(nextCnt==2) {
				Image zergSelectImg =new ImageIcon("zergSelect.png").getImage();
				g.drawImage(zergSelectImg,500,80,300,300,this);
				
				Font stringFont=new Font("zerg",Font.BOLD,60);
				g.setFont(stringFont);
				g.drawString(zerg,585,90);
				
				okeyBtn.setLocation(520,338);
				cancelBtn.setLocation(640,338);
				okeyBtn.setSize(120,47);
				cancelBtn.setSize(120,47);
				nextBtn.setLocation(520,387);
				nextBtn.setSize(240,83);
			}
			else if(nextCnt==3) {
				Image protossSelectImg =new ImageIcon("protossSelect.png").getImage();
				g.drawImage(protossSelectImg,500,80,300,300,this);
				
				Font stringFont=new Font("protoss",Font.BOLD,60);
				g.setFont(stringFont);
				g.drawString(protoss,510,90);
				
				okeyBtn.setLocation(520,338);
				cancelBtn.setLocation(640,338);
				okeyBtn.setSize(120,47);
				cancelBtn.setSize(120,47);
				nextBtn.setLocation(520,387);
				nextBtn.setSize(240,83);
			}
		}
	}
}
