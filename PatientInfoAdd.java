import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;
class NameEx extends Exception{}
class BlankException extends Exception{}
class PatientInfoAdd extends JFrame implements ActionListener
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	JLabel lname,ladd,ltel,lbg,lage,lcur,lpno,lroom,ldateadd,lgender,ldtip,ldocid,ldatedis,ldtip1;
	JTextField tfname,tftel,tfage,tfpno,tfroom,tfdateadd,tfdocid,tfdatedis;
	TextArea taadd,tacur;
	Choice chbg;
	CheckboxGroup cbmf;
	Checkbox cbm,cbf;
	JButton bsub,bclr,bback;
    clsSettings settings = new clsSettings();
PatientInfoAdd()
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
	bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
	bsub.setBounds(150,550,100,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(350,550,100,30);
	jframe.add(bclr);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(550,550,100,30);
	jframe.add(bback);
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bback.addActionListener(new back());
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
class submit implements ActionListener,ItemListener
{
	public void itemStateChanged(ItemEvent ie)
	{}
	public void actionPerformed(ActionEvent ae)
	{			
	try{
		String num=tfpno.getText();
		String name=tfname.getText();
		String addr=taadd.getText();
		String contact=tftel.getText();
		String blgr=chbg.getSelectedItem();
		String age=tfage.getText();
		String current=tacur.getText();
		String room=tfroom.getText();
		String dateadd=tfdateadd.getText();
		String datedis=tfdatedis.getText();
		String docid=tfdocid.getText();
		String gender=" ";
		if(cbm.getState()==true)
		gender="Male";
		if(cbf.getState()==true)
		gender="Female";
		if(num.equals("") || name.equals("") || contact.equals("") || age.equals("") || current.equals("") || dateadd.equals("") || docid.equals("") || datedis.equals(""))
		{	throw new BlankException();	}
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
		df.setLenient(false);  
		java.util.Date myDate = df.parse(dateadd);
		java.util.Date myDate1 = df.parse(datedis);
		Integer num1=Integer.parseInt(docid);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		Statement s=cn.createStatement();
		ResultSet rs=s.executeQuery("select no from Doctor where no="+num1);
		if(!rs.next())
		JOptionPane.showMessageDialog(null,"No such Doctor ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		PreparedStatement st=cn.prepareStatement("insert into Patient values (?,?,?,?,?,?,?,?,?,?,?,?)");
		st.setString(1,num);
		st.setString(2,name);
		st.setString(3,room);
		st.setString(4,addr);
		st.setString(5,blgr);
		st.setString(6,gender);
		st.setString(7,dateadd);
		st.setString(8,contact);
		st.setString(9,age);
		st.setString(10,current);
		st.setString(11,docid);
		st.setString(12,datedis);
		st.executeUpdate();
		JOptionPane.showMessageDialog(null,"Data Added Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
		}}
		catch (ParseException e)
		{
			System.out.println(e);
		}
		catch(BlankException be)
		{
			JOptionPane.showMessageDialog(null,"Please enter All the Fields!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException sq)
		{
			System.out.println(sq);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
public static void main(String[] args)
{	new PatientInfoAdd();	}
}