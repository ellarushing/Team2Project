package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
            HBox spacer = new HBox();
            spacer.setPrefHeight(150);
            spacer.getStyleClass().add("spacer"); 
            
            ImageView logoImage = new ImageView(icon); // load logo image to place in login panel
            logoImage.setFitHeight(150); // resize logo
            logoImage.setLayoutY(-10);
            logoImage.setPreserveRatio(true); // keep ratio of resizable logo proportional to orginal logo
            
            Pane logoPane = new Pane(logoImage); // create image pane to stack on top of login panel
            logoPane.setPrefSize(150, 150);
            logoPane.getStyleClass().add("logoPane");
            
			Label loginInput = new Label("Welcome to YoHealth!"); // login label 
			loginInput.getStyleClass().add("loginInput"); // get font/color style from css file
			
			HBox loginPanel = new HBox(logoPane,loginInput);
			loginPanel.setAlignment(Pos.CENTER);
			loginPanel.setSpacing(100);
			loginPanel.setMaxHeight(100);
			loginPanel.getStyleClass().add("loginPanel");
			
			
			VBox topPanel = new VBox(spacer,loginPanel); // box for main login functionality, add login label to it
			topPanel.setPrefSize(1200, 400); // font/color formatted in application.css 
			topPanel.setAlignment(Pos.CENTER);
			HBox loginHBox = new HBox(topPanel); // convert final login box and spacer to HBox to add to main VBox
			loginHBox.setPrefSize(1200,400); 
			
			
			TextField usernameInput = new TextField("username");
			String username = usernameInput.getText();
			// create logic to call functions to redirect to correct portal based on username 
			
			VBox patientVisitsBox = new VBox(); // patient visits box for left side of screen, enabled upon log in
			patientVisitsBox.setSpacing(100);
			VBox messagesBox = new VBox(); // messages box for right side of screen, enabled upon log in
			messagesBox.setPrefSize(400, 600);
			
			HBox messagesVisitsBox = new HBox(patientVisitsBox,messagesBox); // add both vertical boxes to one horizontal box to format in css file
			messagesVisitsBox.getStyleClass().add("messagesVisitsBox");
			Button loginButton = new Button("Login");
			Button createAccountButton = new Button("Create Account");
			createAccountButton.setVisible(false); // disable create account. only enable it if username is not found
			
			HBox bottomPanelButtons = new HBox(loginButton,createAccountButton);
			bottomPanelButtons.setPrefSize(800, 100);
			
			
			VBox mainVBox = new VBox(loginHBox,messagesVisitsBox,bottomPanelButtons); // add all components to final vertical box 
			mainVBox.setSpacing(100);
			mainVBox.getStyleClass().add("mainVbox"); // use for padding of boxes from edges of window
			
			BorderPane root = new BorderPane();
			root.setCenter(mainVBox);

			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false); // prevent screen resize 


		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Opened Successfully");
	}
}
