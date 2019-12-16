/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Your name (e.g. Nguyen Van Minh)
  ID: Your student id (e.g. 1234567)
  Created  date: dd/mm/yyyy (e.g. 31/03/2019)
  Last modified: dd/mm/yyyy (e.g. 05/04/2019)
  Acknowledgement:

*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main extends Application {

    // window resizing - for reference it is calc(100% - 100px) if it were CSS
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth() - 250;
    int height = gd.getDisplayMode().getHeight() - 100;


    // the folder names containing the players

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }




}


