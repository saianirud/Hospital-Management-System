import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class DoctorInfomodify extends JFrame implements ActionListener
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	JLabel lname,ladd,ltel,lspecial,ldid,ldspec,lgender,lfee;
	JTextField tfname,tftel,tfdid,tfee;
	TextArea taadd,taspecial;
	JButton bsub,bclr,bmod,bback,bdel;
	CheckboxGroup cbmf;
	Checkbox cbm,cbf;
	clsSettings settings = new clsSettings();
DoctorInfomodify()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lname=new JLabel("Name :");
	lname.setBounds(150,90,70,50);
	jframe.add(lname);
	tfname=new JTextField(30);
	tfname.setBounds(200,105,150,20);
	jframe.add(tfname);
	ladd=new JLabel("Address :");
	ladd.setBounds(135,150,70,70);
	jframe.add(ladd);
	taadd=new TextArea();
	taadd.setBounds(205,165,150,50);
	jframe.add(taadd);
	ldid=new JLabel("Doctor ID:");
	ldid.setBounds(400,90,70,50);
	jframe.add(ldid);
	tfdid=new JTextField(30);
	tfdid.setBounds(470,105,150,20);
	jframe.add(tfdid);
	ltel=new JLabel("Contact :");
	ltel.setBounds(400,150,70,50);
	jframe.add(ltel);
	tftel=new JTextField(30);
	tftel.setBounds(470,165,150,20);
	jframe.add(tftel);
	ldspec=new JLabel("Department :");
	ldspec.setBounds(110,235,100,50);
	jframe.add(ldspec);
	taspecial=new TextArea();
	taspecial.setBounds(210,250,150,50);
	jframe.add(taspecial);
	lgender=new JLabel("Gender :");
	lgender.setBounds(400,210,70,50);
	jframe.add(lgender);
	cbmf=new CheckboxGroup();
	cbm=new Checkbox("Male",cbmf,true);
	cbf=new Checkbox("Female",cbmf,false);
	cbm.setBounds(470,225,50,15);
	jframe.add(cbm);
	cbf.setBounds(530,225,60,15);
	jframe.add(cbf);
	lfee=new JLabel("Consultancy fee :");
	lfee.setBounds(400,250,150,50);
	jframe.add(lfee);
	tfee=new JTextField(30);
	tfee.setBounds(510,265,70,20);
	jframe.add(tfee);
	settings.Numvalidator(tfee);
	bsub=new JButton("SEARCH",new ImageIcon("images/search.png"));
	bsub.setBounds(50,450,110,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(180,450,100,30);
	jframe.add(bclr);
	bmod=new JButton("MODIFY",new ImageIcon("images/modify.png"));
	bmod.setBounds(310,450,100,30);
	jframe.add(bmod);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(570,450,100,30);
	jframe.add(bback);
	bdel=new JButton("DELETE",new ImageIcon("images/delete.png"));
	bdel.setBounds(440,450,100,30);
	jframe.add(bdel);
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bmod.addActionListener(new modify());
	bback.addActionListener(new back());
	bdel.addActionListener(new del());
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
	   }
	catch(Exception e)
		{	System.out.println(e);}
}
class clear implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		tfname.setText("");
		tftel.setText("");
		tfdid.setText("");
		taadd.setText("");
		taspecial.setText("");
		tfee.setText("");
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
class submit implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{			
		try{
			Statement st=cn.createStatement();
			if((tfdid.getText()).equals(""))
			{	JOptionPane.showMessageDialog(null,"Please enter the Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);}
			else{
			Integer num=Integer.parseInt(tfdid.getText());
			ResultSet rs3=st.executeQuery("SELECT no FROM Doctor WHERE no="+num);
			if(!rs3.next())
			JOptionPane.showMessageDialog(null,"No such Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			ResultSet rs=st.executeQuery("SELECT * FROM Doctor WHERE no="+num);
			while(rs.next()){
			System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7));
			tfname.setText(rs.getString(2)); taadd.setText(rs.getString(3));tftel.setText(rs.getString(4));taspecial.setText(rs.getString(5));tfee.setText(rs.getString(7));
			if((rs.getString(6)).equals("Male")){cbm.setState(true); cbf.setState(false);}
			else if((rs.getString(6)).equals("Female")){cbm.setState(false); cbf.setState(true);}
			}}}}
			catch(SQLException sq)
			{	System.out.println(sq);}
	}
}
class modify implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		try{
			String s=tfdid.getText();
			String name1=tfname.getText();
			String addr1=taadd.getText();
			String contact1=tftel.getText();
			String spec1=taspecial.getText();
			String gender1=" ";
			if(cbm.getState()==true)
			gender1="Male";
			if(cbf.getState()==true)
			gender1="Female";
			String fee1=tfee.getText();
			if(s.equals("") || name1.equals("") ||addr1.equals("") ||contact1.equals("") ||spec1.equals("") || fee1.equals(""))
			{
				throw new BlankException();
			}
			Integer num1=Integer.parseInt(tfdid.getText());
			String str="UPDATE Doctor SET name=?,address=?,contact=?,dept=?,gender=?,fee=? WHERE no="+num1;
			PreparedStatement psmt=cn.prepareStatement(str);
			psmt.setString(1,name1);
			psmt.setString(2,addr1);
			psmt.setString(3,contact1);
			psmt.setString(4,spec1);
			psmt.setString(5,gender1);
			psmt.setString(6,fee1);
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Data Modified Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
			}
		catch(SQLException sq)
		{
			String message = "Enter Valid data";
			JOptionPane.showMessageDialog(new JFrame(), message, "ERROR!",JOptionPane.ERROR_MESSAGE);
			System.out.println(sq);
		}
		catch(BlankException be)
		{
			JOptionPane.showMessageDialog(null,"Please enter All the Fields!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class del implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		try{
			if((tfdid.getText()).equals(""))
			JOptionPane.showMessageDialog(null,"Please enter Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			Integer num2=Integer.parseInt(tfdid.getText());
			Statement st1=cn.createStatement();
			ResultSet rs2=st1.executeQuery("SELECT no FROM Doctor WHERE no="+num2);
			if(!rs2.next())
			JOptionPane.showMessageDialog(null,"No such Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
			ResultSet rs1=st1.executeQuery("DELETE FROM Doctor WHERE no="+num2);
			JOptionPane.showMessageDialog(null,"Data deleted Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
			}
			tfname.setText("");
			tftel.setText("");
			tfdid.setText("");
			taadd.setText("");
			taspecial.setText("");
			tfee.setText("");
			}}
			catch(SQLException sq)
			{	System.out.println(sq);}
	}
}
public void actionPerformed(ActionEvent ae)
{}
public static void main(String[] args) 
	{
		new DoctorInfomodify();
	}
}