import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class PatientInfomodify extends JFrame implements ActionListener
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	JLabel lname,ladd,ltel,lbg,lage,lcur,lpno,lroom,ldateadd,lgender,ldtip,ldtip1,ldocid,ldatedis;
	JTextField tfname,tftel,tfage,tfpno,tfroom,tfdateadd,tfdocid,tfdatedis;
	TextArea taadd,tacur;
	Choice chbg;
	CheckboxGroup cbmf;
	Checkbox cbm,cbf;
	JButton bsub,bclr,bback,bmod,bdel;
    clsSettings settings = new clsSettings();
PatientInfomodify()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lname=new JLabel("Patient Name :");
	lname.setBounds(100,90,100,50);
	jframe.add(lname);
	tfname=new JTextField(30);
	tfname.setBounds(200,105,150,20);
	jframe.add(tfname);
	ladd=new JLabel("Address :");
	ladd.setBounds(130,150,70,70);
	jframe.add(ladd);
	taadd=new TextArea();
	taadd.setBounds(200,165,150,50);
	jframe.add(taadd);
	lpno=new JLabel("Patient No:");
	lpno.setBounds(400,90,70,50);
	jframe.add(lpno);
	tfpno=new JTextField(30);
	tfpno.setBounds(480,105,50,20);
	jframe.add(tfpno);
	settings.Numvalidator(tfpno);
	ltel=new JLabel("Contact No:");
	ltel.setBounds(400,230,70,50);
	jframe.add(ltel);
	tftel=new JTextField(30);
	tftel.setBounds(480,245,130,20);
	jframe.add(tftel);
	settings.Numvalidator(tftel);
	lroom=new JLabel("Room No:");
	lroom.setBounds(550,90,60,50);
	jframe.add(lroom);
	tfroom=new JTextField(30);
	tfroom.setBounds(630,105,50,20);
	jframe.add(tfroom);
	settings.Numvalidator(tfroom);
	lbg=new JLabel("Blood Group :");
	lbg.setBounds(400,150,80,50);
	jframe.add(lbg);
	chbg=new Choice();
	chbg.setBounds(480,163,60,20);
	chbg.addItem("A -ve");chbg.addItem("A +ve");chbg.addItem("B -ve");chbg.addItem("B +ve");chbg.addItem("AB -ve");chbg.addItem("AB +ve");chbg.addItem("O +ve");chbg.addItem("O -ve");
	jframe.add(chbg);
	lage=new JLabel("Age :");
	lage.setBounds(650,230,50,50);
	jframe.add(lage);
	tfage=new JTextField(15);
	tfage.setBounds(700,245,30,20);
	jframe.add(tfage);
    settings.Numvalidator(tfage);
	lcur=new JLabel("Current Problem :");
	lcur.setBounds(80,300,100,50);
	jframe.add(lcur);
	tacur=new TextArea();
	tacur.setBounds(200,310,150,40);
	jframe.add(tacur);
	ldocid=new JLabel("Attending Doctor ID:");
	ldocid.setBounds(400,300,150,50);
	jframe.add(ldocid);
	tfdocid=new JTextField(100);
	tfdocid.setBounds(530,315,60,20);
	jframe.add(tfdocid);
	settings.Numvalidator(tfdocid);
	ldateadd=new JLabel("Date Of Admission :");
	ldateadd.setBounds(80,230,120,50);
	jframe.add(ldateadd);
	tfdateadd=new JTextField(40);
	tfdateadd.setBounds(200,245,100,20);
	jframe.add(tfdateadd);
	ldtip=new JLabel("(dd-mm-yyyy)");
	ldtip.setBounds(305,243,100,20);
	jframe.add(ldtip);
	lgender=new JLabel("Gender :");
	lgender.setBounds(570,150,60,50);
	jframe.add(lgender);
	cbmf=new CheckboxGroup();
	cbm=new Checkbox("Male",cbmf,true);
	cbf=new Checkbox("Female",cbmf,false);
	cbm.setBounds(630,168,50,15);
	jframe.add(cbm);
	cbf.setBounds(680,168,60,15);
	jframe.add(cbf);
	ldatedis=new JLabel("Date Of Discharge :");
	ldatedis.setBounds(80,400,120,50);
	jframe.add(ldatedis);
	tfdatedis=new JTextField(30);
	tfdatedis.setBounds(200,415,100,20);
	jframe.add(tfdatedis);
	ldtip1=new JLabel("(dd-mm-yyyy)");
	ldtip1.setBounds(305,413,100,20);
	jframe.add(ldtip1);
	bsub=new JButton("SEARCH",new ImageIcon("images/search.png"));
	bsub.setBounds(50,450,120,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(200,450,100,30);
	jframe.add(bclr);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(650,450,100,30);
	jframe.add(bback);
	bmod=new JButton("MODIFY",new ImageIcon("images/modify.png"));
	bmod.setBounds(350,450,100,30);
	jframe.add(bmod);
	bdel=new JButton("DELETE",new ImageIcon("images/delete.png"));
	bdel.setBounds(500,450,100,30);
	jframe.add(bdel);
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
	}
catch(Exception e)
	{	System.out.println(e);	}	
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bmod.addActionListener(new modify());
	bback.addActionListener(new back());
	bdel.addActionListener(new del());
}
public void actionPerformed(ActionEvent ae)
{}
class clear implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		tfname.setText("");
		tftel.setText("");
		tfage.setText("");
		taadd.setText("");
		tacur.setText("");
		tfpno.setText("");
		tfroom.setText("");
		tfdateadd.setText("");
		tfdocid.setText("");
		tfdatedis.setText("");
	}
}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new patStart();
		setVisible(false);
	}
}
class modify implements ActionListener,ItemListener
{
	public void itemStateChanged(ItemEvent ie)
	{}
	public void actionPerformed(ActionEvent ae)
	{
	try{
		String s=tfpno.getText();
		String name1=tfname.getText();
		String addr1=taadd.getText();
		String contact1=tftel.getText();
		String blgr1=chbg.getSelectedItem();
		String age1=tfage.getText();
		String current1=tacur.getText();
		String roomno1=tfroom.getText();
		String dateadd1=tfdateadd.getText();
		String datedis1=tfdatedis.getText();
		String gender1="";
		String docid1=tfdocid.getText();
		if(s.equals("") || name1.equals("") || contact1.equals("") || age1.equals("") || current1.equals("") || dateadd1.equals("") || docid1.equals("") || datedis1.equals(""))
		{	throw new BlankException();	}
		if(cbm.getState()==true)
		{
			gender1="Male";
		}
		if(cbf.getState()==true)
		{
			gender1="Female";
		}
		Integer num1=Integer.parseInt(tfpno.getText());
		String str="UPDATE Patient SET name=?,room_no=?,address=?,bloodgrp=?,gender=?,doa=?,ph_no=?,age=?,cur_pblm=?,d_id=?,dod=? WHERE p_id="+num1;
		PreparedStatement psmt=cn.prepareStatement(str);
		psmt.setString(1,name1);
		psmt.setString(2,roomno1);
		psmt.setString(3,addr1);
		psmt.setString(4,blgr1);
		psmt.setString(5,gender1);
		psmt.setString(6,dateadd1);
		psmt.setString(7,contact1);
		psmt.setString(8,age1);
		psmt.setString(9,current1);
		psmt.setString(10,docid1);
		psmt.setString(11,datedis1);
		psmt.executeUpdate();
		JOptionPane.showMessageDialog(null,"Data Modified Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(BlankException be)
		{	JOptionPane.showMessageDialog(null,"Please enter All the Fields!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);	}
		catch(SQLException sq)
		{	System.out.println(sq);	}
	}
}
class submit implements ActionListener,ItemListener
{
	public void itemStateChanged(ItemEvent ie)
	{}
	public void actionPerformed(ActionEvent ae)
	{			
	try{
		Statement st=cn.createStatement();
		if((tfpno.getText()).equals(""))
		{	JOptionPane.showMessageDialog(null,"Please enter the Patient ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);}
		else{
		Integer num=Integer.parseInt(tfpno.getText());
		ResultSet rs1=st.executeQuery("SELECT p_id FROM Patient WHERE p_id="+num);
		if(!rs1.next())
		JOptionPane.showMessageDialog(null,"No such Patient ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
			ResultSet rs=st.executeQuery("SELECT * FROM Patient WHERE p_id="+num);
			while(rs.next())
			{
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12));
			tfname.setText(rs.getString(2));tfroom.setText(rs.getString(3));taadd.setText(rs.getString(4));tfdateadd.setText(rs.getString(7));tftel.setText(rs.getString(8));tfage.setText(rs.getString(9));tacur.setText(rs.getString(10));tfdocid.setText(rs.getString(11));tfdatedis.setText(rs.getString(12));
			if((rs.getString(6)).equals("Male")){cbm.setState(true); cbf.setState(false);}
			if((rs.getString(6)).equals("Female")){cbm.setState(false); cbf.setState(true);}
			if((rs.getString(5)).equals("A -ve")) chbg.select("A -ve");
			if((rs.getString(5)).equals("A +ve")) chbg.select("A +ve");
			if((rs.getString(5)).equals("B -ve")) chbg.select("B -ve");
			if((rs.getString(5)).equals("B +ve")) chbg.select("B +ve");
			if((rs.getString(5)).equals("AB -ve")) chbg.select("AB -ve");
			if((rs.getString(5)).equals("AB +ve")) chbg.select("AB +ve");
			if((rs.getString(5)).equals("O -ve")) chbg.select("O -ve");
			if((rs.getString(5)).equals("O +ve")) chbg.select("O +ve");
			}}}}
		catch(SQLException sq)
		{	System.out.println(sq);	}
	}
}
class del implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	try{
		if((tfpno.getText()).equals(""))
		JOptionPane.showMessageDialog(null,"Please enter Patient ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		Integer num1=Integer.parseInt(tfpno.getText());
		Statement st1=cn.createStatement();
		ResultSet rs2=st1.executeQuery("SELECT p_id FROM Patient WHERE p_id="+num1);
		if(!rs2.next())
		JOptionPane.showMessageDialog(null,"No such Patient ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		ResultSet rs1=st1.executeQuery("DELETE FROM Patient WHERE p_id="+num1);
		JOptionPane.showMessageDialog(null,"Data deleted Successfully!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		}
		tfname.setText("");
		tftel.setText("");
		tfage.setText("");
		taadd.setText("");
		tacur.setText("");
		tfpno.setText("");
		tfroom.setText("");
		tfdateadd.setText("");
		tfdocid.setText("");
		tfdatedis.setText("");
		}}
		catch(SQLException sq)
		{	System.out.println(sq);}
}}
public static void main(String[] args)
{	new PatientInfomodify();	}
}
