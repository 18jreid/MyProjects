package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Hangman extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, 500, 500);

        // Draws head
        Circle head = new Circle(25);
        head.setCenterX(300);
        head.setCenterY(175);
        pane.getChildren().add(head);

        // Draws body
        Line body = new Line();
        body.setStartX(300);
        body.setStartY(175);
        body.setEndY(300);
        body.setEndX(300);
        pane.getChildren().add(body);

        // Draws left leg
        Line leftLeg = new Line();
        leftLeg.setStartX(300);
        leftLeg.setStartY(300);
        leftLeg.setEndX(250);
        leftLeg.setEndY(350);
        pane.getChildren().add(leftLeg);

        // Draws right leg
        Line rightLeg = new Line();
        rightLeg.setStartX(300);
        rightLeg.setStartY(300);
        rightLeg.setEndX(350);
        rightLeg.setEndY(350);
        pane.getChildren().add(rightLeg);

        // Draws left arm
        Line leftArm = new Line();
        leftArm.setStartX(300);
        leftArm.setStartY(175);
        leftArm.setEndX(250);
        leftArm.setEndY(225);
        pane.getChildren().add(leftArm);

        // Draws right arm
        Line rightArm = new Line();
        rightArm.setStartX(300);
        rightArm.setStartY(175);
        rightArm.setEndX(350);
        rightArm.setEndY(225);
        pane.getChildren().add(rightArm);

        // Draws hanger
        Line hangerP1 = new Line();
        hangerP1.setStartX(300);
        hangerP1.setStartY(175);
        hangerP1.setEndX(300);
        hangerP1.setEndY(100);
        pane.getChildren().add(hangerP1);

        Line hangerP2 = new Line();
        hangerP2.setStartX(300);
        hangerP2.setStartY(100);
        hangerP2.setEndX(200);
        hangerP2.setEndY(100);
        pane.getChildren().add(hangerP2);

        Line hangerP3 = new Line();
        hangerP3.setStartX(200);
        hangerP3.setStartY(100);
        hangerP3.setEndX(200);
        hangerP3.setEndY(375);
        pane.getChildren().add(hangerP3);

        Arc hangerArc = new Arc();
        hangerArc.setRadiusX(50);
        hangerArc.setRadiusY(25);
        hangerArc.setLength(180);
        hangerArc.setFill(null);
        hangerArc.setStroke(Color.BLACK);
        hangerArc.setCenterX(200);
        hangerArc.setCenterY(400);
        pane.getChildren().add(hangerArc);

        primaryStage.setTitle("Hangman!");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
