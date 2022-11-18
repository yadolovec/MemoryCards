
package PetProject;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class QuizCardBuilder{
	
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;
	
	public static void main(String[] args){
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
		
	}
	
	public void go(){
		frame = new JFrame("Quiz Card Builder");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("NEXT CARD");
		nextButton.addActionListener(new NextCardListener());
		
		cardList = new ArrayList<QuizCard>();
		
		JLabel qLabel = new JLabel("QUESTION: ");
		JLabel aLabel = new JLabel("ANSWER: ");
		
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
		
	}
	
	public class NextCardListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);	
			clearCard();
			
		}
	} 
	
	public class SaveMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
				QuizCard card = new QuizCard(question.getText(), answer.getText());
				cardList.add(card);
				
				JFileChooser fileSave = new JFileChooser();
				fileSave.showSaveDialog(frame);
				saveFile(fileSave.getSelectedFile());
		}
	}
	
	public class NewMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
				cardList.clear();
				clearCard();
		}
	}
	
	
	
	
	private void clearCard(){
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void saveFile(File file){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for(QuizCard card:cardList){
				writer.write(card.getQuestion()+"/");
				writer.write(card.getAnswer()+"\n");
			}
			
			writer.close();
			
		}catch(IOException e)
		{
			System.out.println("couldnt write the cradList out");
			e.printStackTrace();
		}
		
	}
	
	
	
	
}