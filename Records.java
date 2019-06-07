import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class Records extends JFrame implements ActionListener
{
	static Connection cn=null;
	static Connection cn2=null;
	Statement st=null;
	Statement st2=null;
	ResultSet rs=null;
	ResultSet rs2=null;
	JButton bpat,bdoc,bback;
	JLabel lpat,ldoc,linfo;
Records()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	linfo=new JLabel("Records");
	linfo.setBounds(300,50,500,50);
	jframe.add(linfo);
	linfo.setFont(new Font("Arial",Font.BOLD,30));
	lpat=new JLabel("For Patient's Record Click Here :");
	lpat.setBounds(100,200,200,30);
	jframe.add(lpat);
	ldoc=new JLabel("For Doctor's Record Click Here :");
	ldoc.setBounds(100,300,200,30);
	jframe.add(ldoc);
	bpat=new JButton("Display Patient's Record",new ImageIcon("images/emp.png"));
	bpat.setBounds(350,200,250,30);
	jframe.add(bpat);
	bdoc=new JButton("Display Doctor's Record",new ImageIcon("images/users.png"));
	bdoc.setBounds(350,300,250,30);
	jframe.add(bdoc);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(300,450,100,30);
	jframe.add(bback);
	bpat.addActionListener(new patrecord());
	bdoc.addActionListener(new docrecord());
	bback.addActionListener(this);
	}
public void actionPerformed(ActionEvent ae)
{
	if (ae.getSource()==bback)
	{
		new start();
		setVisible(false);
	}
}
class patrecord implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		PatientTableFromDatabase frame=new PatientTableFromDatabase();
		frame.setDefaultCloseOperation(1);
        frame.pack();
        frame.setVisible(true);
	}
}
class docrecord implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new DoctorTableFromDatabase();
		DoctorTableFromDatabase frame=new DoctorTableFromDatabase();
		frame.setDefaultCloseOperation(1);
        frame.pack();
        frame.setVisible(true);
	}
}
public static void main(String[] args) 
{	new Records();	}
}