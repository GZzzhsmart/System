package SQLup;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

public class TipUi extends JFrame{
	public TipUi() {
		setUndecorated(true);
		setSize(257, 100);
		
		int width=getToolkit().getScreenSize().width;
		int height=getToolkit().getScreenSize().height-30;
		width = width-this.getWidth();
		Container c = getContentPane();
		
		JLabel text=new JLabel("好友发来了离线消息！");
		c.add(text);
		text.setBounds(80, 40, 160, 20);
		text.setFont(new Font("΢黑体",Font.PLAIN,12));
		
		JLabel bgimg=new JLabel(new ImageIcon("head/notic.png"));
		c.add(bgimg);
		
		JLabel head = new JLabel(new ImageIcon());
		head.setBounds(20, 20, 60, 60);
		bgimg.add(head);
		
		this.addMouseListener(new MyMouseListener());
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try{
			
			
			
			for(int i=1;i<=75;i++){
				setLocation(width, height-(int)(i*1.5));
				Thread.sleep(10);
			}
			
			for(int i=100;i>=1;i--){
				
				AWTUtilities.setWindowOpacity(this, 0.01f*i);
				Thread.sleep(150);
			}
		
			dispose();
		}catch(Exception e){
			
		}
	}
	

	class MyMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e) {
		
			dispose();
		}
	}
	
	public static void main(String[] args) {
		new TipUi();
	}
}

