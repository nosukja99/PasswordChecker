import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

/**
 * This class is for password check 
 * graphical user interface
 * @author hyejeongkim
 *
 */
public class PasswordChecker extends Application{
    Button checkP, checkFile, exitBtn;
	TextField password = new TextField();
	TextField retypeP = new TextField();
	Label text1 = new Label("Password");//all
	Label text2 = new Label("Re-type\nPassword");
	
	Label direc=new Label("Use the following rules when creating your passwords: \n\t1. Length must be greater than 6"
			+ "; a strong password will contain at least 10 characters\n\t2. Must containat least one upper case alpha character"
			+ "\n\t3. Must contain at least one olwer case alpha character\n\t4. Must contain at least one numeric character"
			+"\n\t5. May not have more than 2 of the same character in sequence");
	
	VBox vboxPosition = new VBox(); 
	
	VBox vboxTextPane = new VBox();
	HBox passwordT1 = new HBox();
	HBox passwordT2 = new HBox();

	HBox buttonBox = new HBox();
	
	Stage gstage=null;
	//stage composition
	@Override
	/**
	 * For GUI stage with three buttons and two textfield and direction
	 */
	public void start(Stage Stage) throws Exception {
		//add new button
		checkP=new Button("Check Password");
		checkFile=new Button("Check Passwords in File");
		exitBtn=new Button("Exit");
		
		// set Tool tip for button
		checkP.setTooltip(new Tooltip("Check Password"));
		checkFile.setTooltip(new Tooltip("Check Passwords in File"));
		exitBtn.setTooltip(new Tooltip("Exit"));

		// add event handler to buttons
		checkP.setOnAction(new ButtonEventHandler());
		checkFile.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());
			
		//set button mnemonic
		checkP.setMnemonicParsing(true);
		checkP.setText("_Check Password");
		checkFile.setMnemonicParsing(true);
		checkFile.setText("Check Passwords of _File");
		exitBtn.setMnemonicParsing(true);
		exitBtn.setText("_Exit");
		
		
		gstage = Stage;
		BorderPane MainPane = new BorderPane();
		Insets insets=new Insets(10);
		HBox.setMargin(direc, insets);
		//direction
		HBox direcPane= new HBox(direc);
		
		//input password
		passwordT1.getChildren().addAll(text1,password);
		passwordT1.setAlignment(Pos.CENTER);
		passwordT2.getChildren().addAll(text2,retypeP);
		passwordT2.setAlignment(Pos.CENTER);
		vboxTextPane.getChildren().addAll(passwordT1,passwordT2);
		vboxTextPane.setAlignment(Pos.CENTER);
		
		//Buttons
		buttonBox.getChildren().addAll(checkP,checkFile,exitBtn);
		buttonBox.setAlignment(Pos.CENTER);
	
		//Main Pane
		MainPane.setTop(direcPane);
		MainPane.setBottom(buttonBox);
		MainPane.setCenter(vboxTextPane);
		MainPane.setMargin(buttonBox,insets);
		
        Scene scene = new Scene(MainPane, 800, 300); 
		
		Stage.setScene(scene);
		Stage.setTitle("Password Checker");
		Stage.show();
	}
	
	//read file 
	/**
	 * this method will choose file by filechooser and make the file to arraylist.
	 * and also check the validation of each passwords and display the list of invalid passwords. 
	 * @throws FileNotFoundException - thrown if no file found
	 */
	public void readFile() throws FileNotFoundException 
	{
		ArrayList<String> passwords=new ArrayList<String>();
		ArrayList<String> invalidPasswords=new ArrayList<String>();
		String display="";
		File selectedFile;
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose a file to read passwords");
		if ((selectedFile = chooser.showOpenDialog(null)) != null) 
		{
			Scanner S=new Scanner(selectedFile);
			String line;
			while (S.hasNextLine())
			{
					line = S.nextLine().trim();
					passwords.add(line);					
			}
			invalidPasswords=PasswordCheckerUtility.validPasswords(passwords);
			for(int i=0; i<invalidPasswords.size(); i++)
			{
			   display = display + "\n" + invalidPasswords.get(i);
			}
			JOptionPane.showMessageDialog(null, display, "Illegel Passwords", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	//Button handler
	private class ButtonEventHandler implements EventHandler<ActionEvent> 
	{

		@Override
		/**
		 * for event of button
		 * depend on the users choice this method work on each button
		 * it will check the equality of the first and second input
		 * and in equal case, this will check the validation of password and check the exceptions. 
		 * If the user want to choose the check the file button it will call the readFile function.
		 * exit button will quit. 
		 */
		public void handle(ActionEvent e) 
		{
			if (e.getSource() ==checkP)
			{
				String password1 =password.getText();
				String password2 =retypeP.getText();
				if (password1.equals(password2))
				{
					try {
						boolean isValid;
					    isValid=PasswordCheckerUtility.isValidPassword(password1);
						if(isValid==true)
						{
							boolean isWeak;
							isWeak=PasswordCheckerUtility.isWeakPassword(password1);
							if(isWeak==true)
							{
								JOptionPane.showMessageDialog(null, "Password is "
										+ "OK but weak","Password Status", JOptionPane.PLAIN_MESSAGE);
							}
							else
							JOptionPane.showMessageDialog(null, "Password is valid","Password Status", JOptionPane.PLAIN_MESSAGE);	
						}
					} 
					catch (LengthException e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
					catch(NoDigitException e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
					catch(NoUpperAlphaException e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
					catch(NoLowerAlphaException e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
					catch(InvalidSequenceException e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
				}
				else
				{
					try {
						throw new UnmatchedException("The passwords do not match");
					} 
					catch (UnmatchedException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Password Status", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			else if (e.getSource()==checkFile)
			{
				try {
					readFile();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == exitBtn)
			{
				System.exit(0);
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}