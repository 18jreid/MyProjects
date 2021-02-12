import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;

public class TwoRectangles extends Application {
    @Override
    public void start(Stage stage) {
        // GET ALL USER INPUT HERE FOR RECTANGLES
        System.out.println("Please enter in first rectangles xCord: ");
        Scanner userInput = new Scanner(System.in);
        int xCord1 = userInput.nextInt();

        System.out.println("Please enter in first rectangles yCord: ");
        int yCord1 = userInput.nextInt();

        System.out.println("Please enter in the width of the first rectangle: ");
        int rec1Width = userInput.nextInt();

        System.out.println("Please enter in the height of the first rectangle: ");
        int rec1Height = userInput.nextInt();

        System.out.println("Please enter in second rectangles xCord: ");
        int xCord2 = userInput.nextInt();

        System.out.println("Please enter in second rectangles yCord: ");
        int yCord2 = userInput.nextInt();

        System.out.println("Please enter in the width of the second rectangle: ");
        int rec2Width = userInput.nextInt();

        System.out.println("Please enter in the height of the second rectangle: ");
        int rec2Height = userInput.nextInt();

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, 500, 500);

        // Place rectangles on gui
        Rectangle rec1 = new Rectangle();
        rec1.setX(xCord1);
        rec1.setY(yCord1);
        rec1.setWidth(rec1Width);
        rec1.setHeight(rec1Height);
        rec1.setStroke(Color.BLACK);
        rec1.setFill(null);
        pane.getChildren().add(rec1);

        Rectangle rec2 = new Rectangle();
        rec2.setX(xCord2);
        rec2.setY(yCord2);
        rec2.setWidth(rec2Width);
        rec2.setHeight(rec2Height);
        rec2.setStroke(Color.BLACK);
        rec2.setFill(null);
        pane.getChildren().add(rec2);

        Text rectangleText = new Text();

        // Calculate whether the rectangles are touching
        rectangleText.setText(checkRectangles(xCord1, yCord1, rec1Width, rec1Height, xCord2, yCord2, rec2Width, rec2Height));
        rectangleText.setX(250);
        rectangleText.setY(400);
        pane.getChildren().add(rectangleText);

        stage.setTitle("Rectangles!");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private String checkRectangles(int x1, int y1, int rec1Width, int rec1Height, int x2, int y2, int rec2Width, int rec2Height) {
        boolean isOverlapping = false;
        boolean isInsideOf = false;
        String text = "";
        if (x1 + rec1Width >= x2) {
            if (y1 + rec1Height >= y2) {
                isOverlapping = true;
            }
        }

        if (x1 + rec1Width >= x2 && x1 + rec1Width <= x2 + rec2Width) {
            if (y1 + rec1Height >= y2 && y1 + rec1Height <= y2 + rec2Height) {
                isInsideOf = true;
            }
        }

        if (isOverlapping && isInsideOf) {
            text = "One of the rectangles is inside the other!";
        }

        else if (isOverlapping) {
            text = "The rectangles are overlapping!";
        }
        else {
            text = "The rectangles don't touch!";
        }

        return text;
    }
}
