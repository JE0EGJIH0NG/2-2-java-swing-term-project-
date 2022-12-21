package Game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
public class Main extends JFrame implements ActionListener {


	Image img = new ImageIcon("start.png").getImage();//뒤에 배경
	JButton startBtn;
	
	ImageIcon startBtnImage=new ImageIcon("startButton.jpg");
	ImageIcon startBtnImageOn=new ImageIcon("startButtonOn.jpg");
	
	Main() {
		
		setTitle("2019");
		setBounds(0,0,1250,660);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		startBtn=new JButton(startBtnImage);
		startBtn.setBounds(0,570,1250,60);
		startBtn.setRolloverIcon(startBtnImageOn);//마우스가 올라가면 이미지 변경
		startBtn.addActionListener(this);//리스너 달아줌
		this.add(startBtn);//버튼을 달아줌
		
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) { 
		JButton btn = (JButton)e.getSource();
		if(btn==startBtn) {
			dispose();//현 프레임 종료
			MainB mainB=new MainB();//mainB 
		}
		
	}
	/*뒤에 배경 그리기*/
	public void paint(Graphics g) {
		//Image img = Toolkit.getDefaultToolkit().getImage("start.jpg");
	
		g.drawImage(img,0,0,1250,625,this);
		
	}
}
	
	
