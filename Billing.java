import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.text.*;
class Billing extends JFrame implements ActionListener
{	
static Connection cn=null;static Connection cn1=null;
Statement st=null;
ResultSet rs=null;
JLabel lmain,lbill,lpno,lrent,lamt,ldateadd,ldatedis,lfee,lmed;
JTextField tno,tbill,trent,tamt,tdateadd,tdatedis,tfee,tmed;
JButton bsub,bclr,bback,bdel,badd;
clsSettings settings = new clsSettings();
Billing()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lmain=new JLabel("Billing Information");
	lmain.setBounds(200,30,500,50);
	jframe.add(lmain);
	lmain.setFont(new Font("Arial",Font.BOLD,30));
	lpno=new JLabel("Patient No :");
	lpno.setBounds(120,100,100,50);
	jframe.add(lpno);
	tno=new JTextField(30);
	tno.setBounds(200,115,70,20);
	jframe.add(tno);
	settings.Numvalidator(tno);
	lbill=new JLabel("Bill ID :");
	lbill.setBounds(400,100,70,50);
	jframe.add(lbill);
	tbill=new JTextField(30);
	tbill.setBounds(470,115,70,20);
	jframe.add(tbill);
	settings.Numvalidator(tbill);
	lrent=new JLabel("Room rent / day : ");
	lrent.setBounds(100,150,100,50);
	jframe.add(lrent);
	trent=new JTextField(30);
	trent.setBounds(200,165,70,20);
	jframe.add(trent);
	settings.Numvalidator(trent);
	lamt=new JLabel("Total Amount :");
	lamt.setBounds(380,350,85,50);
	jframe.add(lamt);
	tamt=new JTextField(20);
	tamt.setBounds(480,365,70,20);
	jframe.add(tamt);
	settings.Numvalidator(tamt);
	ldateadd=new JLabel("Date Of Admission :");
	ldateadd.setBounds(80,300,150,50);
	jframe.add(ldateadd);
	tdateadd=new JTextField(30);
	tdateadd.setBounds(210,315,90,20);
	jframe.add(tdateadd);
	ldatedis=new JLabel("Date Of Discharge :");
	ldatedis.setBounds(350,300,150,50);
	jframe.add(ldatedis);
	tdatedis=new JTextField(30);
	tdatedis.setBounds(480,315,90,20);
	jframe.add(tdatedis);
	lfee=new JLabel("Doctor Fee :");
	lfee.setBounds(120,350,150,50);
	jframe.add(lfee);
	tfee=new JTextField(30);
	tfee.setBounds(210,365,70,20);
	jframe.add(tfee);
	settings.Numvalidator(tfee);
	lmed=new JLabel("Medical Charges :");
	lmed.setBounds(350,150,150,50);
	jframe.add(lmed);
	tmed=new JTextField(30);
	tmed.setBounds(480,165,70,20);
	jframe.add(tmed);
	settings.Numvalidator(tmed);
	bsub=new JButton("SEARCH" ,new ImageIcon("images/search.png"));
	bsub.setBounds(180,500,110,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(350,500,100,30);
	jframe.add(bclr);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(650,500,100,30);
	jframe.add(bback);
	bdel=new JButton("DELETE",new ImageIcon("images/delete.png"));
	bdel.setBounds(500,500,100,30);
	jframe.add(bdel);
	badd=new JButton("ADD",new ImageIcon("images/add.gif"));
	badd.setBounds(50,500,100,30);
	jframe.add(badd);
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}	
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bback.addActionListener(new back());
	bdel.addActionListener(new del());
	badd.addActionListener(new adddata());
}
public void actionPerformed(ActionEvent ae)
{}
class clear implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		tbill.setText("");
		tno.setText("");
		trent.setText("");
		tamt.setText("");
		tdateadd.setText("");
		tdatedis.setText("");
		tfee.setText("");
		tmed.setText("");
	}
}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new start();
		setVisible(false);
	}
}
class adddata extends Frame implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{	
	try {
		if((tbill.getText()).equals("") || (tno.getText()).equals("") || (trent.getText()).equals("") || (tmed.getText()).equals(""))
		JOptionPane.showMessageDialog(null,"Please enter all the Fields!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		Integer num=Integer.parseInt(tno.getText());
		long rent=Long.parseLong(trent.getText());
		long med=Long.parseLong(tmed.getText());
		Statement st=cn.createStatement();
		ResultSet rs=st.executeQuery("select * from Patient where p_id="+num);
		Integer num1=0;
		while(rs.next())
		num1=Integer.parseInt(rs.getString(11));
		Statement st1=cn.createStatement();
		ResultSet rs1=st1.executeQuery("select * from Doctor where no="+num1);
		long fee=0;
		while(rs1.next()){
		fee=Long.parseLong(rs1.getString(7));
		tfee.setText(rs1.getString(7));}
		Statement st2=cn.createStatement();
		ResultSet rs2=st2.executeQuery("select * from Patient where p_id="+num);
		String doa="";String dod="";
		while(rs2.next()){
		doa=rs2.getString(7);dod=rs2.getString(12);
		tdateadd.setText(rs2.getString(7));tdatedis.setText(rs2.getString(12));}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setLenient(false);  
		java.util.Date date=formatter.parse(doa);
		java.util.Date date1=formatter.parse(dod);
		System.out.println(date);
		long diff=(date1.getTime()-date.getTime())/86400000;
		long tot=(diff*rent)+fee+med;
		String totamt=Long.toString(tot);
		tamt.setText(totamt);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		String bill=tbill.getText();
		String id=tno.getText();
		String rnt=trent.getText();
		String amt=tamt.getText();
		String doadd=tdateadd.getText();
		String dodis=tdatedis.getText();
		String docfee=tfee.getText();
		String medch=tmed.getText();
		PreparedStatement stt=cn1.prepareStatement("insert into Billing values (?,?,?,?,?,?,?,?)");
		stt.setString(1,bill);
		stt.setString(2,id);
		stt.setString(3,rnt);
		stt.setString(4,amt);
		stt.setString(5,doadd);
		stt.setString(6,dodis);
		stt.setString(7,docfee);
		stt.setString(8,medch);
		stt.executeUpdate();
		JOptionPane.showMessageDialog(null,"Data Added Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
		}}
	catch (SQLException sq)
		{
			System.out.println(sq);
		}
	catch (ParseException e)
		{
			System.out.println(e);
		}
	catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class submit implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	try{
		Statement st2=cn.createStatement();
		if((tbill.getText()).equals(""))
		{	JOptionPane.showMessageDialog(null,"Please enter the Bill ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);}
		else{
			Integer n=Integer.parseInt(tbill.getText());
			ResultSet rs2=st2.executeQuery("SELECT bill_id FROM Billing WHERE bill_id="+n);
			if(!rs2.next())
			JOptionPane.showMessageDialog(null,"No such Bill ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			else{
				ResultSet r=st2.executeQuery("SELECT * FROM Billing WHERE bill_id="+n);
				while(r.next()){
				System.out.println(r.getString(1)+"   "+r.getString(2)+"   "+r.getString(3)+"   "+r.getString(4)+"   "+r.getString(5)+"   "+r.getString(6)+"   "+r.getString(7)+"  "+r.getString(8));
				tbill.setText(r.getString(1));tno.setText(r.getString(2));trent.setText(r.getString(3));tamt.setText(r.getString(4));tdateadd.setText(r.getString(5));tdatedis.setText(r.getString(6));tfee.setText(r.getString(7));tmed.setText(r.getString(8));
			}}}}
			catch(SQLException sq)
			{	System.out.println(sq);}
	}
}
class del implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	try{
		if((tbill.getText()).equals(""))
		JOptionPane.showMessageDialog(null,"Please enter Bill ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		Integer a=Integer.parseInt(tbill.getText());
		Statement s=cn.createStatement();
		ResultSet r=s.executeQuery("select bill_id from Billing where bill_id="+a);
		if(!r.next())
		JOptionPane.showMessageDialog(null,"No such Bill ID" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
			ResultSet q=s.executeQuery("delete from Billing where bill_id="+a);
			JOptionPane.showMessageDialog(null,"Data deleted Successfully!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			}
			tbill.setText("");
			tno.setText("");
			trent.setText("");
			tamt.setText("");
			tdateadd.setText("");
			tdatedis.setText("");
			tfee.setText("");
			tmed.setText("");
		}}
		catch(SQLException sq)
			{	System.out.println(sq);}
	}
}
public static void main(String[] args) 
{	new Billing();	}
}