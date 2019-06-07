import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class docStart extends JFrame implements ActionListener
{
JButton badd,bmod,bview,bback,bexit;
JLabel linfo,linfo1,linfo2,linfo3,linfo4;

docStart()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	linfo=new JLabel("Doctor's   Information");
	linfo.setBounds(200,30,500,50);
	jframe.add(linfo);
	linfo.setFont(new Font("Arial",Font.BOLD,30));
	linfo1=new JLabel("1.  Add  Doctors  Information");
	linfo1.setBounds(200,150,210,20);
	jframe.add(linfo1);
	badd=new JButton("Add Data",new ImageIcon("images/add.gif"));
	badd.setBounds(350,180,180,30);
	jframe.add(badd);
	linfo2=new JLabel("2.  Modify  Doctors  Information");
	linfo2.setBounds(200,250,210,20);
	jframe.add(linfo2);
	bmod=new JButton("Modify Data",new ImageIcon("images/bModify.png"));
	bmod.setBounds(350,280,180,30);
	jframe.add(bmod);
	linfo3=new JLabel("3. View  Doctors  Information");
	linfo3.setBounds(200,350,210,20);
	jframe.add(linfo3);
	bview=new JButton("View Data",new ImageIcon("images/search.png"));
	bview.setBounds(350,380,180,30);
	jframe.add(bview);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(300,545,100,30);
	jframe.add(bback);
	badd.addActionListener(new add());
	bmod.addActionListener(new mod());
	bview.addActionListener(new view());
	bback.addActionListener(new back());
}
public void actionPerformed(ActionEvent ae)
{}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new  start();
		setVisible(false);
	}
}
class add implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new	DoctorInfoAdd();
		setVisible(false);
	}
}
class mod implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new DoctorInfomodify();
		setVisible(false);
	}
}
class view implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new DoctorInfoView();
		setVisible(false);
	}
}
public static void main(String[] args) 
{
	new docStart();
}
}