import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class DoctorInfoView extends JFrame implements ActionListener
{
	static Connection cn=null;static Connection cn1=null;
	Statement st=null;Statement st1=null;
	ResultSet rs=null;ResultSet rs1=null;
	JLabel ldid,ldocdata,lpatlist;
	TextArea docdata,tapatlist;
	JTextField tfdid;
	JButton bsub,bclr,bback,bdocdata;
	clsSettings settings = new clsSettings();
DoctorInfoView()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	ldid=new JLabel("Doctor ID:");
	ldid.setBounds(170,280,70,50);
	jframe.add(ldid);
	tfdid=new JTextField(30);
	tfdid.setBounds(250,295,150,20);
	jframe.add(tfdid);
	settings.Numvalidator(tfdid);
	ldocdata=new JLabel("Doctor Database:");
	ldocdata.setBounds(120,50,100,50);
	jframe.add(ldocdata);
	docdata=new TextArea();
	docdata.setBounds(250,50,400,150);
	jframe.add(docdata);
	lpatlist=new JLabel("Patient List:");
	lpatlist.setBounds(150,350,70,50);
	jframe.add(lpatlist);
	tapatlist=new TextArea();
	tapatlist.setBounds(250,350,250,100);
	jframe.add(tapatlist);
	bdocdata=new JButton("Doctor Database");
	bdocdata.setBounds(250,220,150,30);
	jframe.add(bdocdata);
	bsub=new JButton("SEARCH",new ImageIcon("images/search.png"));
	bsub.setBounds(100,550,150,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(300,550,150,30);
	jframe.add(bclr);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(500,550,150,30);
	jframe.add(bback);
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		}
	catch(Exception e)
		{System.out.println(e);}
	bdocdata.addActionListener(new data());
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bback.addActionListener(new back());
}
class data implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		try{
			Statement st3=cn1.createStatement();
			ResultSet rs3=st3.executeQuery("SELECT * FROM Doctor");
			docdata.setText("");
			if(!rs3.next())
			JOptionPane.showMessageDialog(null,"Empty Table!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			Statement st4=cn1.createStatement();
			ResultSet rs4=st4.executeQuery("SELECT * FROM Doctor");
			while(rs4.next()){
			System.out.println(rs4.getString(1)+"   "+rs4.getString(2)+"   "+rs4.getString(3)+"   "+rs4.getString(4)+"   "+rs4.getString(5)+"   "+rs4.getString(6)+"   "+rs4.getString(7));
			docdata.append(rs4.getString(1)+"   "+rs4.getString(2)+"   "+rs4.getString(3)+"   "+rs4.getString(4)+"   "+rs4.getString(5)+"   "+rs4.getString(6)+"   "+rs4.getString(7)+"\n");
		}
		}}
		catch(SQLException sq)
		{	System.out.println(sq);}
	}
}
class clear implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		tfdid.setText("");
		tapatlist.setText("");
		docdata.setText("");
	}
}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new docStart();
		setVisible(false);
	}
}
public void actionPerformed(ActionEvent ae)
{}
class patinfo implements ActionListener
{public void actionPerformed(ActionEvent ae){ }}
class submit implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{			
		try{
			if((tfdid.getText()).equals(""))
			JOptionPane.showMessageDialog(null,"Please enter Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			Integer num=Integer.parseInt(tfdid.getText());
			Statement st1=cn.createStatement();
		    ResultSet rs1=st1.executeQuery("SELECT no FROM Doctor WHERE no="+num);
			if(!rs1.next())
			JOptionPane.showMessageDialog(null,"No such Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			System.out.println("\nPatient list of Doctor Id="+num);
			Statement st=cn.createStatement();
		    ResultSet rs=st.executeQuery("SELECT p_id FROM Patient WHERE d_id="+num);
			tapatlist.setText("");
			if(!rs.next())
			JOptionPane.showMessageDialog(null,"No Patient" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			Statement st2=cn.createStatement();
		    ResultSet rs2=st2.executeQuery("SELECT * FROM Patient WHERE d_id="+num);
			while(rs2.next())
			{
				System.out.println(rs2.getString(1)+"   "+rs2.getString(2));
				tapatlist.append(rs2.getString(1)+"   "+rs2.getString(2)+"\n");
			}
			}}}}
		   catch(SQLException sq)
		   {	System.out.println(sq);}
	}
}
public static void main(String[] args) 
{
	new DoctorInfoView();
}
}