import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class clsSettings
{ 		
public void Numvalidator(JTextField txtField)
{
	txtField.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent e){
    char c = e.getKeyChar();
    if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
	{e.consume();}}});
}}