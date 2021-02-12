package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private Circle[][] circles = new Circle[6][7];
    private boolean player1Went = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect Four!");
        VBox vBox = new VBox();
        FlowPane flow = new FlowPane();
        Text connectFourBorder = new Text("______________________________");
        Image connectImg = new Image("connect4.png");
        ImageView connectFourImg = new ImageView(connectImg);
        vBox.getChildren().add(flow);
        vBox.getChildren().add(connectFourBorder);
        vBox.getChildren().add(connectFourImg);

        Scene scene = new Scene(vBox, 616, 680, Color.ROYALBLUE);

        connectFourBorder.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, FontPosture.REGULAR, 70));
        connectFourBorder.setFill(Color.PURPLE);
        connectFourBorder.setTranslateY(-60);
        connectFourImg.setScaleX(.65);
        connectFourImg.setScaleY(.6);
        connectFourImg.setTranslateX(-75);
        connectFourImg.setTranslateY(-115);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // Set pieces white and borders purple, add them to the flow pane
                circles[i][j] = new Circle(40);
                circles[i][j].setFill(Color.WHITE);
                circles[i][j].setStrokeWidth(8);
                setCircleBorderPurple(i, j);
                flow.getChildren().add(circles[i][j]);

                int row = i;
                int column = j;

                // Change stroke color when mouse has entered or exited the circle
                circles[i][j].setOnMouseEntered(e -> {
                    int newRow = findAvailableSpace(column);

                    if (newRow == 0 && circles[newRow][column].getFill() != Color.WHITE) {
                       setCircleBorderPurple(newRow, column);
                    }
                    else {
                        setCircleBorderLightBlue(newRow, column);
                    }
                });

                circles[i][j].setOnMouseExited(e -> {
                    int newRow = findAvailableSpace(column);
                    setCircleBorderPurple(newRow, column);
                });

                // Place red and yellow pieces alternating to feel like two players
                circles[i][j].setOnMouseClicked(e -> {
                    boolean canPlace = true;
                    int newRow = findAvailableSpace(column);

                    if (newRow == 0 && circles[newRow][column].getFill() != Color.WHITE) {
                        canPlace = false;
                    }

                    if (canPlace) {
                        if (!player1Went) {
                            setCircleYellow(newRow, column);
                            setCircleBorderPurple(newRow, column);
                            player1Went = true;
                        } else {
                            setCircleRed(newRow, column);
                            setCircleBorderPurple(newRow, column);
                            player1Went = false;
                        }
                    }
                });
            }
        }

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private int findAvailableSpace(int column) {
        int temp = 0;
        for (int i = 0; i <= 5; i++) {
            if (circles[i][column].getFill() == Color.WHITE) {
                temp = i;
            }
            else if (circles[i][column].getFill() == Color.YELLOW || circles[i][column].getFill() == Color.RED) {
                continue;
            }
        }
        return temp;
    }

    private void setCircleRed(int row, int column) {
        circles[row][column].setFill(Color.RED);
    }

    private void setCircleYellow(int row, int column) {
        circles[row][column].setFill(Color.YELLOW);
    }

    private void setCircleBorderPurple (int row, int column) {
        circles[row][column].setStroke(Color.PURPLE);
    }

    private void setCircleBorderLightBlue (int row, int column) {
        circles[row][column].setStroke(Color.LIGHTBLUE);
    }

    private boolean circleIsRed (int row, int column) {
        return circles[row][column].getFill() == Color.RED;
    }

    private boolean circleIsYellow (int row, int column) {
        return circles[row][column].getFill() == Color.YELLOW;
    }

    public static void main(String[] args) {
        launch(args);
    }
}