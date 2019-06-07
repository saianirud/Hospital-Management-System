import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class start extends JFrame implements ActionListener
{
JButton bpat,bdoc,bbill,breport,bback,bexit;
JLabel linfo,linfo1,linfo2,linfo3,linfo4;
start()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	linfo=new JLabel("SELECT THE APPROPRIATE OPTION");
	linfo.setBounds(50,120,500,30);
	jframe.add(linfo);
	linfo.setFont(new Font("Arial",Font.BOLD,20));
	linfo1=new JLabel("1. For Inserting,  Modifying,  Retrieving Patients related Data");
	linfo1.setBounds(50,275,335,20);
	jframe.add(linfo1);
	linfo2=new JLabel("2. For Inserting,  Modifying,  Retrieving Doctors related Data");
	linfo2.setBounds(50,205,335,20);
	jframe.add(linfo2);
	linfo3=new JLabel("3. Billing   Details");
	linfo3.setBounds(50,345,150,20);
	jframe.add(linfo3);
	linfo4=new JLabel("4. Patient and Doctor related Data");
	linfo4.setBounds(50,413,250,20);
	jframe.add(linfo4);
	bpat=new JButton("Patient", new ImageIcon("images/Advances.png"));
	bpat.setBounds(430,270,180,30);
	jframe.add(bpat);
	bdoc=new JButton("Doctor",new ImageIcon("images/Advances.png"));
	bdoc.setBounds(430,200,180,30);
	jframe.add(bdoc);
	bbill=new JButton("Billing",new ImageIcon("images/Attendance.png"));
	bbill.setBounds(430,340,180,30);
	jframe.add(bbill);
	breport=new JButton("Records",new ImageIcon("images/edit.png"));
	breport.setBounds(430,408,180,30);
	jframe.add(breport);
	bback=new JButton("BACK" ,new ImageIcon("images/restore.png"));
	bback.setBounds(100,515,100,30);
	jframe.add(bback);
	bexit=new JButton("EXIT" ,new ImageIcon("images/exits.png"));
	bexit.setBounds(500,515,100,30);
	jframe.add(bexit);
	bpat.addActionListener(new patient());
	bdoc.addActionListener(new doctor());
	bbill.addActionListener(new billing());
	bexit.addActionListener(new exit());
	bback.addActionListener(new back());
	breport.addActionListener(new records());
}
public void actionPerformed(ActionEvent ae)
{}
class records implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new  Records();
		setVisible(false);
	}
}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new  dbmsmain();
		setVisible(false);
	}
}
class patient implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new patStart();
		setVisible(false);
	}
}
class doctor implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new docStart();
		setVisible(false);
	}
}
class billing implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new Billing();
		setVisible(false);
	}
}
class exit implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		System.exit(0);
	}
}
public static void main(String[] args) 
{	new start();	}
}