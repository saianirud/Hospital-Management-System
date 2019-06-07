import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class DoctorInfoAdd extends JFrame implements ActionListener
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	JLabel ldi,lname,ladd,ltel,lspecial,ldid,ldspec,lgender,lfee;
	JTextField tfname,tftel,tfdid,tfee;
	TextArea taadd,tacur,taspecial;
	JButton bsub,bclr,bback;
	CheckboxGroup cbmf;
	Checkbox cbf,cbm;
	clsSettings settings = new clsSettings();
DoctorInfoAdd()
{
	JFrame jframe = new JFrame();
	jframe.getContentPane().setBackground(new Color(230,230,250));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(800,700);
	jframe.setVisible(true);
	jframe.setLayout(null);
	lname=new JLabel("Doctor Name :");
	lname.setBounds(100,90,100,50);
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
	tfdid.setBounds(470,105,50,20);
	jframe.add(tfdid);
	settings.Numvalidator(tfdid);
	ltel=new JLabel("Contact :");
	ltel.setBounds(400,150,70,50);
	jframe.add(ltel);
	tftel=new JTextField(30);
	tftel.setBounds(470,165,150,20);
	jframe.add(tftel);
	settings.Numvalidator(tftel);
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
 	bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
	bsub.setBounds(150,400,100,30);
	jframe.add(bsub);	
	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(300,400,100,30);
	jframe.add(bclr);
	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(450,400,100,30);
	jframe.add(bback);
	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bback.addActionListener(new back());
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
		new docStart();setVisible(false);
	}
}
class submit implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{			
	try{
		String num=tfdid.getText();
		String name=tfname.getText();
		String addr=taadd.getText();
		String contact=tftel.getText();
		String spec=taspecial.getText();
		String gender=" ";
		if(cbm.getState()==true)
		gender="Male";
		if(cbf.getState()==true)
		gender="Female";
		String fee=tfee.getText();
		if(num.equals("") || name.equals("") ||addr.equals("") ||contact.equals("") ||spec.equals("") || fee.equals(""))
		{
			throw new BlankException();
		}
		else{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
		PreparedStatement st=cn.prepareStatement("insert into Doctor values (?,?,?,?,?,?,?)");
		st.setString(1,num);
		st.setString(2,name);
		st.setString(3,addr);
		st.setString(4,contact);
		st.setString(5,spec);
		st.setString(6,gender);
		st.setString(7,fee);
		st.executeUpdate();
		JOptionPane.showMessageDialog(null,"Data Added Successfully!!" , "Done!",JOptionPane.INFORMATION_MESSAGE);
		}}
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
public void actionPerformed(ActionEvent ae)
{}
public static void main(String[] args) 
{
	new DoctorInfoAdd();
}
}