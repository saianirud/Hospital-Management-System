import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
class DoctorTableFromDatabase extends JFrame
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
DoctorTableFromDatabase()
{
    Vector columnNames = new Vector();
    Vector data = new Vector();
	try
    {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","saianirud");
			}
		catch(Exception e)
		{	System.out.println(e);	}
		String sql = "Select * from Doctor";
		Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery( sql );
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++)
        {
			columnNames.addElement(md.getColumnName(i));
        }
		while (rs.next())
        {
            Vector row = new Vector(columns);
			for (int i = 1; i <= columns; i++)
            {
			row.addElement(rs.getObject(i));
			}
		data.addElement( row );
        }
	}
    catch(Exception e)
    {	System.out.println( e );	}
	JTable table = new JTable(data, columnNames);
	JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add( scrollPane );
	JPanel buttonPanel = new JPanel();
	getContentPane().add( buttonPanel, BorderLayout.SOUTH );
    }
public static void main(String[] args)
{
DoctorTableFromDatabase frame = new DoctorTableFromDatabase();
frame.setDefaultCloseOperation( EXIT_ON_CLOSE);
frame.pack();
frame.setVisible(true);
}
}