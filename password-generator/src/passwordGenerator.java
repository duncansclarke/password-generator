import java.awt.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

public class passwordGenerator {
	
	public static boolean containsNumbers;
	public static boolean containsLowercase;
	public static boolean containsUppercase;
	public static boolean containsSymbols;
	public static boolean containsOthers;
	public static boolean somethingSelected;
	public static int passwordLength;
	
	public static void main(String [] args) {
		//run gui
		createWindow();
	}
	
	public static String generatePassword() {
		
		//declare arrays containing each subset of character
		char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
		char[] lowercase = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] uppercase = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] symbols = {'!','@','#','$','%','^','&','*'};
		char[] others = {'{','}','[',']','(',')','/','\'','\"','~',',',';',':','.','<','>'};
		
		//create arraylist that will contain all the characters of the types the user desires
		ArrayList<Character> possibleCharacters = new ArrayList<>();
		
		//adds each desired subset of characters to arraylist
		if(containsNumbers) {
			for(char i : numbers) {
				possibleCharacters.add(i);
			}
		}
		if(containsLowercase) {
			for(char i : lowercase) {
				possibleCharacters.add(i);
			}
		}
		if(containsUppercase) {
			for(char i : uppercase) {
				possibleCharacters.add(i);
			}
		}
		if(containsSymbols) {
			for(char i : symbols) {
				possibleCharacters.add(i);
			}
		}
		if(containsOthers) {
			for(char i : others) {
				possibleCharacters.add(i);
			}
		}
		
		//declares password variable
		String password = "";
		
		//appends the password string with a randomly chosen character from the arraylist of possible characters, and repeats until the desired password length is reached
		for(int i=1; i<=passwordLength; i++) {
			Random rand = new Random();
			password += possibleCharacters.get(rand.nextInt(possibleCharacters.size()));
		}
		
		return password;
	}
	
	public static void createWindow() {
		
		
		//JFrame instantiation
		JFrame frame = new JFrame("Password Generator - By Duncan Clarke");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1));
		
		
		
		//Check Boxes (Password Customization)
		
		//create check boxes with labels
		JCheckBox numbers = new JCheckBox("  Include Numbers (123456...)");
		JCheckBox lowercase = new JCheckBox("  Include Lowercase Characters (abcdefgh...)");
		JCheckBox uppercase = new JCheckBox("  Include Uppercase Characters (ABCDEFGH...)");
		JCheckBox symbols = new JCheckBox("  Include Common Symbols (@ # $ %...)");
		JCheckBox others = new JCheckBox("  Include Uncommon Symbols ({ } [ ] ( ) \" ` ~ , ; : . < > )");		
		
		//add check boxes to JFrame
		frame.add(numbers);
		frame.add(lowercase);
		frame.add(uppercase);
		frame.add(symbols);
		frame.add(others);
		
		
		
		//Drop-Down Menu (Password Length)
		
		//create JPanel and label
		JPanel length = new JPanel();
		length.setLayout(new GridLayout(1, 2));
		JLabel pLength = new JLabel("         Password Length");
		length.add(pLength);		
		
		//create array of numbers from 1-100 (possible password lengths user might want)
		Integer [] pLengths = new Integer[100];
		for(int i=1; i<100; i++) {
			pLengths[i]=i;
		}		
		
		//add drop-down menu to JPanel and 
		final JComboBox<Integer> cb = new JComboBox<>(pLengths);
		cb.setVisible(true);
	    length.add(cb);	    
	    
	    //add JPanel to JFrame
		frame.add(length);
		
		
		
		//Generate Password Button and Password Text Field
		
		//create "Generate Password" button
		JButton btn = new JButton("Generate Password");
		frame.add(btn);
		
		//create JPanel and label
		JPanel yourPassword = new JPanel();
		yourPassword.setLayout(new GridLayout(1,2));
		JLabel label = new JLabel("         Your New Password");
		yourPassword.add(label);
		
		//create text field that will contain the generated password
		JTextField passwordText = new JTextField();
		yourPassword.add(passwordText);
		frame.add(yourPassword);
		
		//generate password on button press
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//makes sure user has checked at least one kind of character to use and that they have selected a password length
				if(cb.getSelectedItem() != null && 
						(numbers.isSelected() || lowercase.isSelected() || uppercase.isSelected() || symbols.isSelected() || others.isSelected())) {
					//update instance variables with user selections
					containsNumbers = numbers.isSelected();
					containsLowercase = lowercase.isSelected();
					containsUppercase = uppercase.isSelected();
					containsSymbols = symbols.isSelected();
					containsOthers = others.isSelected();
					passwordLength = (int)cb.getSelectedItem();
					//set password text to generated password
					passwordText.setText(generatePassword());
				}
			}
		});
		
		
		
	    //Instantiate GUI
		
	    frame.pack();
	    frame.setSize(frame.getWidth()+50, frame.getHeight()+100);
	    frame.setVisible(true);
	}
}
