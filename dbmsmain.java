import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class dbmsmain extends JFrame
{
JLabel lmain,lhm,lhead,username,password;
JTextField tuser;
JPasswordField tpass;
JButton login,exit;
public dbmsmain()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lmain=new JLabel(new ImageIcon("images/mulogo.png"));
	jframe.add(lmain);
	lmain.setBounds(5,30,150,50);
	lhm=new JLabel(new ImageIcon("images/hm.png"));
	lhm.setBounds(180,300,600,300);
	jframe.add(lhm);
	lhead=new JLabel("Hospital Management System");
	jframe.add(lhead);
	lhead.setBounds(200,30,600,50);
	lhead.setFont(new Font("Arial",Font.BOLD,30));
	username=new JLabel("Username :");
	username.setBounds(50,150,70,50);
	jframe.add(username);
	tuser=new  JTextField(30);
	tuser.setBounds(120,165,150,20);
	jframe.add(tuser);
	password=new JLabel("Password :");
	password.setBounds(50,200,70,50);
	jframe.add(password);
	tpass=new  JPasswordField(30);
	tpass.setBounds(120,215,150,20);
	jframe.add(tpass);
	login=new JButton("Login",new ImageIcon("images/key.gif"));
	login.setBounds(50,270,110,30);
	jframe.add(login);	
	exit=new JButton("Exit",new ImageIcon("images/exits.png"));
	exit.setBounds(200,270,100,30);
	jframe.add(exit);
	login.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent ae)
	{
		String loginname,loginpass;
		loginname=tuser.getText();
		loginpass=tpass.getText();
		if(loginname.equals("system") && loginpass.equals("system"))
		{
			new start();
			setVisible(false);
		}
		else{
			JOptionPane.showMessageDialog(null,"Invaild Username or Password" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			tuser.setText("");
		    tpass.setText("");}
	}
	});
	exit.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent ae)
	{
		System.exit(0);
	}
	});
}
public static void main(String[] args)
	{ 
		new dbmsmain();
	}
}