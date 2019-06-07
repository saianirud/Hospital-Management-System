import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class PatientInfoView extends JFrame implements ActionListener
{
	static Connection cn=null;
	Statement st=null; Statement st1=null;
	ResultSet rs=null; ResultSet rs1=null;
	JLabel lpatdata;
	TextArea tpatdata;
	JButton bpatdata,bbutton;
    clsSettings settings = new clsSettings();
PatientInfoView()
{
	JFrame jframe=new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lpatdata=new JLabel("Patient Database :");
	lpatdata.setBounds(50,50,120,50);
	jframe.add(lpatdata);
	tpatdata=new TextArea();
	tpatdata.setBounds(200,50,500,200);
	jframe.add(tpatdata);
	bpatdata=new JButton("Patient Database");
	bpatdata.setBounds(200,400,150,30);
	jframe.add(bpatdata);
	bbutton=new JButton("BACK",new ImageIcon("images/restore.png"));
	bbutton.setBounds(500,400,100,30);
	jframe.add(bbutton);
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		}
	catch(Exception e)
		{System.out.println(e);}
	bpatdata.addActionListener(new data());
	bbutton.addActionListener(new back());
}
public void actionPerformed(ActionEvent ae)
{}
class back implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		new patStart();
		setVisible(false);
	}
}
class data implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	try{
		Statement st1=cn.createStatement();
		ResultSet rs1=st1.executeQuery("SELECT * FROM Patient");
		tpatdata.setText("");
		if(!rs1.next())
		JOptionPane.showMessageDialog(null,"Empty Table!!" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		else{
		Statement st=cn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM Patient");
		while(rs.next())
			{
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12));
			tpatdata.append(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12)+"\n");
			}
	}}
		catch(SQLException sq)
		{	System.out.println(sq);}
	}
}
public static void main(String[] args)
{	new PatientInfoView();	}
}
