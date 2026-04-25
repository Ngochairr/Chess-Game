package Game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import java.awt.Font;
import javax.swing.JTextField;

public class ChessGame {
	private String letters = "ABCDEFGH";
	private String numbers = "12345678";
	private JFrame frame;
	private String image;
	private int j[];
	private Piece selected=null;
	private boolean white=true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action_1 = new SwingAction_1();
	private final Action action = new SwingAction();
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessGame window = new ChessGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChessGame() {
		initialize();
	}
	
	public void eat(Piece p1,Piece p2) {
		Piece p3 = new Piece(p2.GetColor(),p2.GetType());
		p2.SetType(p1.GetType());
		p2.SetColor(p1.GetColor());
		p1.SetType(null);
		p1.SetColor(null);
		
	}
	public void switchpieces(Piece p1,Piece p2) {
		Piece p3 = new Piece(p1.GetColor(),p1.GetType());
		p1.SetType(p2.GetType());
		p1.SetColor(p2.GetColor());
		p2.SetType(p3.GetType());
		p2.SetColor(p3.GetColor());
	}
	public void move(Piece p1,Piece p2) {
		switch(p2.GetTri()) {
		case "Green":
			switchpieces( p1, p2);
			break;
		case "Red":
			eat(p1,p2);
		}
		p1.SetTri(null);
		p2.SetTri(null);
		
	}
	public void resetboardcolors(Piece r[]) {
		for (int i=0; i < 64; i++)
		{
		
		if (i%8==0) {
		white = !white;
		}		
		if (white==true) {
			r[i].setBackground(Color.white);
				white=false;
			}
			else {
				r[i].setBackground(Color.gray);
				white=true;
			}
		r[i].SetImage();
		r[i].SetTri(null);
		
		}
		}
	
 	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 631, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Chess");
		JPanel  Pieces = new JPanel();
		
		Pieces.setBackground(new Color(0,0,0,0));

		Pieces.setBounds(74, 95, 512, 449);
		 
		frame.getContentPane().add(Pieces);
		Pieces.setLayout(new GridLayout(8,8));
		
		JPanel ChessBoard = new JPanel();
		ChessBoard.setBounds(10, 39, 576, 507);
		
		ChessBoard.setLayout(new GridLayout(9,9));
		int totalJLs = 81;
		JLabel arrayJLs[] = new JLabel[81];
		Piece arrayPs[] = new Piece[64];
		//painting the board
		for (int i=0; i < totalJLs; i++)
		{
		arrayJLs[i] = new JLabel("",JLabel.CENTER);
		ChessBoard.add(arrayJLs[i]);
		}
		for (int i=0; i < 8; i++)
		{
			arrayJLs[i+1].setText(String.valueOf(letters.charAt(i)));
			
		}
		for (int i=9; i<81; i=i+9)
		{
			arrayJLs[i].setText(String.valueOf(numbers.charAt(i/9-1)));
	}
		for (int i=10;i<81;i++) {
			if (arrayJLs[i].getText()=="") {
				arrayJLs[i].setOpaque(true);
			
			}
				if (white==true) {
					arrayJLs[i].setBackground(Color.white);
					arrayJLs[i].setOpaque(true);
					white=false;
				}
				else {
					arrayJLs[i].setBackground(Color.black);
					
					white=true;
				}
			
		}
		frame.getContentPane().add(ChessBoard);
		
		textField = new JTextField();
		textField.setBounds(249, 16, 150, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(100, 13, 139, 25);
		frame.getContentPane().add(btnUpgrade);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(10, 16, 80, 24);
		frame.getContentPane().add(btnReset);
		btnReset.setAction(action);
		btnUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				String textFieldValue = textField.getText();
				
				
					switch(textFieldValue) {
						case "Queen":
							selected.SetType("Queen");
							
							break;
						case "Rook":
							selected.SetType("Rook");
							
							break;
						case "Knight":
							selected.SetType("Knight");
							
							break;
						case "Bishop":
							selected.SetType("Bishop");
							break;
					
				
				}
					resetboardcolors(arrayPs);	
				}
			
		});
		btnReset.setAction(action);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				for (int i=0; i < 64; i++)
				{
				arrayPs[i].SetColor(null);
				arrayPs[i].SetType(null);
				arrayPs[i].SetTri(null);
				}
				for (int i=0; i < 16; i++)
				{
				arrayPs[i].SetColor("B");
				
				}
				for (int i=48; i < 64; i++)
				{
				arrayPs[i].SetColor("W");
				
				}
				//setting up pieces
				for (int i=8; i < 16; i++)
				{
				arrayPs[i].SetType("Pawn");
				arrayPs[i].SetImage();
				}
				for (int i=48; i < 56; i++)
				{
				arrayPs[i].SetType("Pawn");
				arrayPs[i].SetImage();
				}
				arrayPs[0].SetType("Rook");
				arrayPs[0].SetImage();
				arrayPs[7].SetType("Rook");
				arrayPs[7].SetImage();
				arrayPs[63].SetType("Rook");
				arrayPs[63].SetImage();
				arrayPs[56].SetType("Rook");
				arrayPs[56].SetImage();
				arrayPs[1].SetType("Knight");
				arrayPs[1].SetImage();
				arrayPs[6].SetType("Knight");
				arrayPs[6].SetImage();
				arrayPs[62].SetType("Knight");
				arrayPs[62].SetImage();
				arrayPs[57].SetType("Knight");
				arrayPs[57].SetImage();
				arrayPs[2].SetType("Bishop");
				arrayPs[2].SetImage();
				arrayPs[5].SetType("Bishop");
				arrayPs[5].SetImage();
				arrayPs[61].SetType("Bishop");
				arrayPs[61].SetImage();
				arrayPs[58].SetType("Bishop");
				arrayPs[58].SetImage();
				arrayPs[3].SetType("Queen");
				arrayPs[3].SetImage();
				arrayPs[59].SetType("Queen");
				arrayPs[59].SetImage();
				arrayPs[4].SetType("King");
				arrayPs[4].SetImage();
				arrayPs[60].SetType("King");
				arrayPs[60].SetImage();
				for(int i=0;i<8;i++) {
					arrayPs[i].SetNBorder(true);
					
				}
				resetboardcolors(arrayPs);	
				}
			
		});
		//adding the pieces
		for (int i=0; i < 64; i++)
		{
		arrayPs[i] = new Piece();
		
		final Integer inneri = new Integer(i);
		arrayPs[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
					if(arrayPs[inneri].GetTri()=="") {
						resetboardcolors(arrayPs);
						selected = null;
					}
					
					else if(selected!=null&&arrayPs[inneri].GetTri()!="") {
						switch(arrayPs[inneri].GetTri()) {
						case "":
							
							break;
						case "Green":
							move(selected,arrayPs[inneri]);
							
							break;
						case "Red":
							move(selected,arrayPs[inneri]);
							break;
						}
						}
						
					
					resetboardcolors(arrayPs);
					switch(arrayPs[inneri].GetType()) {
					case "":
						break;
					case "Pawn":
						selected = arrayPs[inneri];
						switch(arrayPs[inneri].GetColor()) {
						case "B":
						if (inneri<57) {
							if(arrayPs[inneri+8].GetType()=="") {
								arrayPs[inneri+8].SetTri("Green");
								arrayPs[inneri+8].setBackground(Color.green);
								}
						}
						if (inneri<56)	{
						if (arrayPs[inneri+9].GetType()==""||arrayPs[inneri+9].GetColor()=="B") {
							
						}
						else if(arrayPs[inneri].GetEBorder()==true||arrayPs[inneri].GetSBorder()==true||arrayPs[inneri].GetNBorder()==true) {}
						else {
							arrayPs[inneri+9].SetTri("Red");
							arrayPs[inneri+9].setBackground(Color.red);
						}
						}
						if (inneri<57) {
						if (arrayPs[inneri+7].GetType()==""||arrayPs[inneri+7].GetColor()=="B") {
							
						}
						else if(arrayPs[inneri].GetWBorder()==true||arrayPs[inneri].GetSBorder()==true||arrayPs[inneri].GetNBorder()==true) {}
						else {
							arrayPs[inneri+7].SetTri("Red");
							arrayPs[inneri+7].setBackground(Color.red);
						}
						}
						break;
						case "W":
						if (inneri>7) {
							if(arrayPs[inneri-8].GetType()=="") {
							arrayPs[inneri-8].SetTri("Green");
							arrayPs[inneri-8].setBackground(Color.green);
							}
						}
						if (inneri>8) {
							if (arrayPs[inneri-9].GetType()==""||arrayPs[inneri-9].GetColor()=="W") {
								
							}
							else if(arrayPs[inneri].GetWBorder()==true||arrayPs[inneri].GetSBorder()==true||arrayPs[inneri].GetNBorder()==true||arrayPs[inneri].GetEBorder()==true) {}
							else {
								
								arrayPs[inneri-9].SetTri("Red");
								arrayPs[inneri-9].setBackground(Color.red);
							}
						}
						if (inneri>6) {
							if (arrayPs[inneri-7].GetType()==""||arrayPs[inneri-7].GetColor()=="W") {
								
							}
							else if(arrayPs[inneri].GetSBorder()==true||arrayPs[inneri].GetNBorder()==true||arrayPs[inneri].GetEBorder()==true) {}
							else {
								arrayPs[inneri-7].SetTri("Red");
								arrayPs[inneri-7].setBackground(Color.red);
							}
						}
							break;
						}
						break;
					case "Bishop":
						selected = arrayPs[inneri];
						for (int i=inneri;i<64&&i>=0;i=i+9) {
							if (arrayPs[inneri].GetEBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
									}
									break;
								}
							
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i-9) {
							if (arrayPs[inneri].GetWBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i+7) {
							
							if (arrayPs[inneri].GetWBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i-7) {
							if (arrayPs[inneri].GetEBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
							arrayPs[i].SetTri("Green");
							arrayPs[i].setBackground(Color.green);
						}
							else {
								if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
								else {
								arrayPs[i].SetTri("Red");
								arrayPs[i].setBackground(Color.red);
								}
								break;
							}
							if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
							
						}
						break;
					case "Rook":
						selected = arrayPs[inneri];
						for(int i=inneri;i<64&&i>=0;i=i+8) {
							//System.out.println("Here");
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetSBorder()==true) {
								//System.out.println("Here");
								break;
							}
						}
						for(int i=inneri;i<64&&i>=0;i=i+1) {
							//System.out.println("Here");
							if (arrayPs[inneri].GetEBorder()==true) {
								//System.out.println("Here");
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetEBorder()==true) {
								//System.out.println("Here");
								break;
							}
							
						}
						for(int i=inneri;i<64&&i>=0;i=i-8) {
							//System.out.println("Here");
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
								if (arrayPs[i].GetNBorder()==true) {
								
								break;
							}
						}
						for(int i=inneri;i<64&&i>=0;i=i-1) {
							//System.out.println("Here");
							if (arrayPs[inneri].GetWBorder()==true) {
								//System.out.println("Here");
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetWBorder()==true) {
								//System.out.println("Here");
								break;
							}
						}
						break;
					case "Queen":
						selected = arrayPs[inneri];
						for(int i=inneri;i<64&&i>=0;i=i+8) {
							//System.out.println("Here");
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetSBorder()==true) {
								//System.out.println("Here");
								break;
							}
						}
						for(int i=inneri;i<64&&i>=0;i=i+1) {
							//System.out.println("Here");
							if (arrayPs[inneri].GetEBorder()==true) {
								//System.out.println("Here");
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetEBorder()==true) {
								//System.out.println("Here");
								break;
							}
							
						}
						for(int i=inneri;i<64&&i>=0;i=i-8) {
							//System.out.println("Here");
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
								if (arrayPs[i].GetNBorder()==true) {
								
								break;
							}
						}
						for(int i=inneri;i<64&&i>=0;i=i-1) {
							//System.out.println("Here");
							if (arrayPs[inneri].GetWBorder()==true) {
								//System.out.println("Here");
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
							else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							if (arrayPs[i].GetWBorder()==true) {
								//System.out.println("Here");
								break;
							}
							
							
						}
						for (int i=inneri;i<64&&i>=0;i=i+9) {
							if (arrayPs[inneri].GetEBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
									}
									break;
								}
							
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i-9) {
							if (arrayPs[inneri].GetWBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i+7) {
							
							if (arrayPs[inneri].GetWBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							
							if (arrayPs[i].GetType()=="") {
								arrayPs[i].SetTri("Green");
								arrayPs[i].setBackground(Color.green);
							}
								else {
									if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
									else {
									arrayPs[i].SetTri("Red");
									arrayPs[i].setBackground(Color.red);
									}
									break;
								}
							 if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
						}
						for (int i=inneri;i<64&&i>=0;i=i-7) {
							if (arrayPs[inneri].GetEBorder()==true) {
								break;
							}
							if (i==inneri) {
								continue;
							}
							if (arrayPs[i].GetType()=="") {
							arrayPs[i].SetTri("Green");
							arrayPs[i].setBackground(Color.green);
						}
							else {
								if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
								else {
								arrayPs[i].SetTri("Red");
								arrayPs[i].setBackground(Color.red);
								}
								break;
							}
							if(arrayPs[i].GetWBorder()==true||arrayPs[i].GetSBorder()==true||arrayPs[i].GetNBorder()==true||arrayPs[i].GetEBorder()==true) {
								break;
							}
							
							
						}
						break;
					case "Knight":
						selected = arrayPs[inneri];
						if(inneri>8) {
							int i =inneri;
							i=i-8;
							i=i-1;
							if(arrayPs[i].GetWBorder()==true) {
							}
							else {
								i--;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri<55) {
							int i =inneri;
							i=i+8;
							i++;
							
							if(arrayPs[i].GetEBorder()==true) {
							}
							else {
								i++;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri>22) {
							int i =inneri;
							i=i-8;
							i=i-8;
							
							if(arrayPs[i].GetWBorder()==true) {
							}
							else {
								i++;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri>15) {
							int i =inneri;
							i=i-8;
							i=i-8;
							
							if(arrayPs[i].GetWBorder()==true) {
							}
							else {
								i--;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri<48) {
							int i =inneri;
							i=i+8;
							i=i+8;
							if(arrayPs[i].GetEBorder()==true) {
							}
							else {
								i++;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri>22) {
							int i =inneri;
							i=i-8;
							i=i-8;
							
							if(arrayPs[i].GetEBorder()==true) {
							}
							else {
								i--;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri>7) {
							int i =inneri;
							i=i-8;
							i=i+1;
							
							if(arrayPs[i].GetEBorder()==true) {
							}
							else {
								i++;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri>9) {
							int i =inneri;
							i=i-1;
							i=i-8;
							
							if(arrayPs[i].GetNBorder()==true) {
							}
							else {
								i=i-1;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						if(inneri<47) {
							int i =inneri;
							i=i+8;
							i=i+8;
							
							if(arrayPs[i].GetWBorder()==true) {
							}
							else {
								i=i-1;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
						}
						break;
					case "King":
						selected = arrayPs[inneri];
							int i =inneri;
							
							if(arrayPs[i].GetWBorder()==true) {
							}
							else {
								i=i-1;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
							i=inneri;
							if(arrayPs[i].GetEBorder()==true) {
							}
							
							else {
								i=i+1;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
							}
							i=inneri;
							if(arrayPs[i].GetNBorder()==true) {
							}
							
							else {
								int r=i;
								i=r-8;
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
								
								
								i=r-7;
								if(arrayPs[i].GetWBorder()==true) {}
								else {
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
									}
								
									}
								i=r-9;
								if(arrayPs[i].GetEBorder()==true) {}
								else {
								
								
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
								
							}
							}
	
							i=inneri;
							if(arrayPs[i].GetSBorder()==true) {
							}
							
							else {
								int r=i;
								i=r+8;
								
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
								
								i=r+9;
								if(arrayPs[i].GetWBorder()==true) {}
								else {
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
								}
								i=r+7;
								if(arrayPs[i].GetEBorder()==true) {}
								else {
								if (arrayPs[i].GetType()=="") {
									arrayPs[i].SetTri("Green");
									arrayPs[i].setBackground(Color.green);
								}
									else {
										if (arrayPs[i].GetColor()==arrayPs[inneri].GetColor()) {}
										else {
										arrayPs[i].SetTri("Red");
										arrayPs[i].setBackground(Color.red);
										}
										
									}
								}
							}
							
					break;
					}
					
					
					
			}
		});
		
		Pieces.add(arrayPs[i]);
		}
		for (int i=0; i < 16; i++)
		{
		arrayPs[i].SetColor("B");
		
		}
		for (int i=48; i < 64; i++)
		{
		arrayPs[i].SetColor("W");
		
		}
		//setting up pieces
		for (int i=8; i < 16; i++)
		{
		arrayPs[i].SetType("Pawn");
		arrayPs[i].SetImage();
		}
		for (int i=48; i < 56; i++)
		{
		arrayPs[i].SetType("Pawn");
		arrayPs[i].SetImage();
		}
		arrayPs[0].SetType("Rook");
		arrayPs[0].SetImage();
		arrayPs[7].SetType("Rook");
		arrayPs[7].SetImage();
		arrayPs[63].SetType("Rook");
		arrayPs[63].SetImage();
		arrayPs[56].SetType("Rook");
		arrayPs[56].SetImage();
		arrayPs[1].SetType("Knight");
		arrayPs[1].SetImage();
		arrayPs[6].SetType("Knight");
		arrayPs[6].SetImage();
		arrayPs[62].SetType("Knight");
		arrayPs[62].SetImage();
		arrayPs[57].SetType("Knight");
		arrayPs[57].SetImage();
		arrayPs[2].SetType("Bishop");
		arrayPs[2].SetImage();
		arrayPs[5].SetType("Bishop");
		arrayPs[5].SetImage();
		arrayPs[61].SetType("Bishop");
		arrayPs[61].SetImage();
		arrayPs[58].SetType("Bishop");
		arrayPs[58].SetImage();
		arrayPs[3].SetType("Queen");
		arrayPs[3].SetImage();
		arrayPs[59].SetType("Queen");
		arrayPs[59].SetImage();
		arrayPs[4].SetType("King");
		arrayPs[4].SetImage();
		arrayPs[60].SetType("King");
		arrayPs[60].SetImage();
		for(int i=0;i<8;i++) {
			arrayPs[i].SetNBorder(true);
			
		}
		for(int i=0;i<64;i=i+8) {
			arrayPs[i].SetWBorder(true);
			
		}
		for(int i=7;i<64;i=i+8) {
			arrayPs[i].SetEBorder(true);
			
			
		}
		for(int i=56;i<64;i=i+1) {
			
			arrayPs[i].SetSBorder(true);
			
		}
		//moving pieces
		resetboardcolors(arrayPs);
		
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Reset");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Reset");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("YEAH");
		}
	}
}
