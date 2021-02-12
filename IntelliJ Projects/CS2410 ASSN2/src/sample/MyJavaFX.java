package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Basics of the stage
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("HELLO WORLD!");

        // Create "Hello" text, set color and position
        Text hello = new Text("Hello");
        hello.setStyle("-fx-stroke: blue;");
        hello.setX(217); hello.setY(250);

        // Create "World!" text, set color and position
        Text world = new Text("World!");
        world.setStyle("-fx-stroke: green;");
        world.setX(250); world.setY(250);

        // Add texts to pane
        pane.getChildren().add(hello);
        pane.getChildren().add(world);

        // Add scene to stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
