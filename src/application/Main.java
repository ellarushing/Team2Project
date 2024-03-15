/* Team 2 Prototype Code for Main UI/login functionality
@authors Carlee Miller, Sophia Neumann, Ella Rushing, Kiernan Wyatt, Joshua Alon
@version 1.0
*/

package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("YoHealth");
            // Load the image using FileInputSteam()
			FileInputStream logoIcon = new FileInputStream("YoHealth.png");
            Image icon = new Image(logoIcon); // place YoHealth logo next to window title
            // Set the application icon
            primaryStage.getIcons().add(icon);

         // Create an empty HBox to act as a spacer for label
            HBox spacer1 = new HBox();
            spacer1.setMinHeight(125);
            HBox spacer2 = new HBox();
            spacer2.setMinHeight(125);
            spacer2.setVisible(false);
            
            ImageView logoImage = new ImageView(icon); // load logo image to place in login panel
            logoImage.setFitHeight(225); // resize logo
            logoImage.setLayoutY(-55);
            logoImage.setLayoutX(50);
            logoImage.setPreserveRatio(true); // keep ratio of resizable logo proportional to original logo
            
            Pane logoPane = new Pane(logoImage); // create image pane to stack on top of login panel
            logoPane.setPrefSize(150, 150);
            logoPane.getStyleClass().add("logoPane");
            
			Label loginInput = new Label("Welcome to YoHealth!"); // login label 
			loginInput.getStyleClass().add("loginInput"); // get font/color style from css file
			
			HBox loginPanel = new HBox(logoPane,loginInput);
			loginPanel.setAlignment(Pos.CENTER_LEFT);
			loginPanel.setSpacing(275); // space logo and label
			loginPanel.setMaxHeight(110);
			loginPanel.setMinHeight(110);
			loginPanel.getStyleClass().add("loginPanel"); // get css style for login panel
			
			
			VBox topPanel = new VBox(spacer1,loginPanel); // box for main login functionality, add login label to it
			topPanel.setPrefSize(1400, 400); // font/color formatted in application.css 
			topPanel.setAlignment(Pos.CENTER);
			HBox loginHBox = new HBox(topPanel); // convert final login box and spacer to HBox to add to main VBox
			loginHBox.setPrefSize(1400,400); 
			
			
			VBox patientVisitsBox = new VBox(); // patient visits box for left side of screen, enabled upon log in
			patientVisitsBox.setSpacing(50);
			patientVisitsBox.setPrefSize(475,500);
			patientVisitsBox.getStyleClass().add("regularbox");
			
			
			VBox messagesBox = new VBox(); // messages box for right side of screen, enabled upon log in
			messagesBox.setSpacing(50);
			messagesBox.setPrefSize(475, 500);
			messagesBox.getStyleClass().add("regularbox");
			
			
			HBox messagesVisitsBox = new HBox(patientVisitsBox,messagesBox); // add both vertical boxes to one horizontal box to format in css file
			messagesVisitsBox.getStyleClass().add("messagesVisitsBox");
			messagesVisitsBox.setSpacing(200);
			
			VBox mainUIVBox = new VBox(messagesVisitsBox,spacer2);
			HBox mainUI = new HBox(mainUIVBox);
			mainUI.setAlignment(Pos.CENTER);
			mainUI.setVisible(false); // disable messages/visits boxes until login is completed
			
			
			
			Label loginLabel = new Label ("Login to your YoHealth Account");
			loginLabel.getStyleClass().add("loginLabel");
			
			TextField usernameInput = new TextField("username");
			usernameInput.setMaxWidth(450); 
			usernameInput.setMinHeight(80);
			usernameInput.getStyleClass().add("usernameInput");
			
			Button loginButton = new Button("Login");
			loginButton.setPrefSize(200, 60);
			loginButton.getStyleClass().add("loginButton"); // style login button in css file
			
			Button createAccountButton = new Button("Create Account");
			createAccountButton.setVisible(false); // disable create account. only enable it if username is not found

			HBox bottomPanelButtons = new HBox(loginButton,createAccountButton); // group buttons in horizontal box 
			bottomPanelButtons.setMinHeight(60);
			bottomPanelButtons.setMinWidth(500);
			bottomPanelButtons.setAlignment(Pos.CENTER);
			bottomPanelButtons.setPadding(new Insets(0, 0, 0, 125)); // Add 125 pixels of padding to the left to center button under text field

			Label trouble = new Label ("Trouble logging in? Call us at (123) 456-7890");
			trouble.getStyleClass().add("trouble");
			VBox usernameEntry = new VBox(loginLabel,usernameInput,bottomPanelButtons,trouble); // group username input and buttons together
			usernameEntry.setSpacing(50);
			usernameEntry.setAlignment(Pos.CENTER);
			usernameEntry.setPrefSize(1400,300);
			HBox usernameBox = new HBox(usernameEntry); // encapsulate usernamEntry VBox in HBox to put in main VBox
			usernameBox.setPadding(new Insets(100, 0, 0, 0)); // pad username entry box another 100 px from top panel

			
			VBox mainVBox = new VBox(loginHBox,usernameBox,mainUI); // add all components to final vertical box 
			mainVBox.setSpacing(100); // space components in main box 100 px apart from each other
			mainVBox.setAlignment(Pos.CENTER);
			mainVBox.getStyleClass().add("mainVbox"); // use for padding of boxes from edges of window
			

			
			BorderPane root = new BorderPane();
			root.setCenter(mainVBox);

			Scene scene = new Scene(root,1400,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false); // prevent screen resize 
			
			// create logic to call functions to redirect to correct portal based on username 
			loginButton.setOnAction(e -> {
				String username = usernameInput.getText();
			    // create a user object 
				User user = new User(username);
				int searchResult = user.searchUser(username); // call search user method of User class and set result to results variable
				if(searchResult == 0) 
				{
					user.createAccount(username); // if user does not exist, call create account method with username as parameter
				} // only log in functionality has been created, we still need to implement create account functionality
				
				else
				{
					mainVBox.getChildren().remove(usernameBox); // remove username log in box 
					spacer2.setVisible(true);
					mainUI.setVisible(true); // make messages/visits box visible
					
				}

			});
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Opened Successfully");
	}
}
