import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Calculator extends JFrame{
	private LayoutManager borderLayout;
	
	private JPanel emptyPanel1; //North
	private JPanel emptyPanel2; //West
	private JPanel emptyPanel3;	//East
	
	//THE MAIN PANERL
	private JPanel main; //Center; layout: Flow; size cannot be changed; aligned right
	private LayoutManager mainFlowLayout;
	
	//LABLES FOR TEXT FIELDS
	private JLabel LFnum;  
	private JLabel LSnum;
	private JLabel Lresult;
	
	private JTextField Fnum;
	private JTextField Snum;
	private JTextField result;
	
	private JButton add;
	private JButton sub;
	private JButton clear;
	
	//THE EXIT PANEL
	private JPanel Pexit; //South; layout: Flow; aligned right
	private LayoutManager exitFlowLayout;
	
	private JButton exit;


	public Calculator() {
		frameConstructor();
		exitConstructor();
		mainConstructor();
		
		this.pack();
		this.setVisible(true);
	}
	
	private void frameConstructor() {
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		this. setPreferredSize( new Dimension( 400, 400 ) );
		
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		emptyPanel1 = new JPanel();
		emptyPanel2 = new JPanel();
		emptyPanel3 = new JPanel();
		
		emptyPanel1.setPreferredSize(new Dimension(10, 10));
		emptyPanel2.setPreferredSize(new Dimension(10, 10));
		emptyPanel3.setPreferredSize(new Dimension(10, 10));
		
		this.add(emptyPanel1, BorderLayout.EAST);
		this.add(emptyPanel2, BorderLayout.WEST);
		this.add(emptyPanel3, BorderLayout.NORTH);
		
		this.setResizable(false);
	}
	
	private void exitConstructor() {
		
		exitFlowLayout = new FlowLayout(FlowLayout.RIGHT, 10, 10);
		Pexit = new JPanel(exitFlowLayout);
		Pexit.setSize(10, 10);
		
		exit = new JButton("EXIT");
		exit.setSize(10, 10);
		
		Pexit.add(exit);
		this.add(Pexit, BorderLayout.SOUTH);
	}
	
	private void mainConstructor() {
		
		mainFlowLayout = new FlowLayout(FlowLayout.RIGHT, 50, 50);
		main = new JPanel(mainFlowLayout);
		main.setSize(300,300);
		Border redBorder = BorderFactory.createLineBorder(Color.red, 5);
		main.setBorder(redBorder);
		
		Border blueBorder = BorderFactory.createLineBorder(Color.blue, 2);
		
		LFnum = new JLabel("First number:");
		main.add(LFnum);
		Fnum = new JTextField();
		Fnum.setPreferredSize(new Dimension(120, 20));
		Fnum.setBorder(blueBorder);
		main.add(Fnum);
		
		LSnum = new JLabel("Second number:");
		main.add(LSnum);
		Snum = new JTextField();
		Snum.setPreferredSize(new Dimension(120, 20));
		Snum.setBorder(blueBorder);
		main.add(Snum);
		
		Lresult = new JLabel("Result:");
		main.add(Lresult);
		result = new JTextField();
		result.setPreferredSize(new Dimension(120, 20));
		result.setBorder(blueBorder);
		main.add(result);
		
		add = new JButton("add");
		main.add(add);
		sub = new JButton("sub");
		main.add(sub);
		clear = new JButton("clear");
		main.add(clear);
		
		this.add(main, BorderLayout.CENTER);
	}
}
