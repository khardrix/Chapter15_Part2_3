/********************************************************************************************************************
 ********************************************************************************************************************
 *****                                    Chapter 15, Part 2: Problem 3                                         *****
 *****__________________________________________________________________________________________________________*****
 *****                  3. Write a program in which, when the user presses the mouse button,                    *****
 *****                 the x,y coordinate is remembered and when the mouse button is released,                  *****
 *****                  a PathTransition is created from the first x,y to this x,y and a Text                   *****
 *****                            (whatever Text you want) is placed on that path.                              *****
 *****                     It continues until the user does a new mouse press and release.                      *****
 *****                      You will have to import java.animation.*; for PathTransition.                       *****
 *****                                 Import java.scene.text.*; for Text and                                   *****
 *****                   javafx.util.*; for Duration (for the Duration of the PathTransition).                  *****
 *****----------------------------------------------------------------------------------------------------------*****
 *****         I added a Random number generator to give the PathTransition a random Duration (speed).          *****
 ********************************************************************************************************************
 ********************************************************************************************************************/

// IMPORTS of needed tools and plug-ins
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.text.*;
import javafx.util.Duration;
import java.util.Random;


public class Chapter15_Part2_3 extends Application {

    // CLASS VARIABLE(s) declaration(s)
    private Pane pane;
    private Text text;
    private PathTransition pathTransition;
    private Line line;
    private double pressedX, pressedY, releasedX, releasedY;
    private int speed;
    private Random random;


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage){
        // Initialize the Pane
        pane = new Pane();

        // Initialize and Set the Text
        text = new Text("Huffman");

        // MousePressed EventHandler using a lambda expression
        pane.setOnMousePressed(e -> {

            // Get and store the x/y coordinates of the mouse press
            pressedX = e.getSceneX();
            pressedY = e.getSceneY();
        });

        // MouseReleased EventHandler using a lambda expression
        pane.setOnMouseReleased(e -> {

            // Get and store the x/y coordinates of the mouse release
            releasedX = e.getSceneX();
            releasedY = e.getSceneY();

            // If a Line and Text already exists in the Pane, Remove them (clear the Pane)
            if(line != null){
                pane.getChildren().removeAll(line, text);
            }

            // Create a Line from the MousePressed and MouseReleased x/y coordinates
            line = new Line(pressedX, pressedY, releasedX, releasedY);

            // Initialize the Random variable, get a Random number and assign it to the int variable speed
            random = new Random();
            speed = (random.nextInt(4000) + 100);

            // Initialize the PathTransition
            pathTransition = new PathTransition();

            // Set the Path of the PathTransition to the Line created from the MousePressed and MouseReleased
                // x/y coordinates
            pathTransition.setPath(line);

            // Set the already created Text object as the node on the Path Transition
            pathTransition.setNode(text);

            // Set the CycleCount (how long the animation lasts for) to INDEFINITE (never stops)
            pathTransition.setCycleCount(Timeline.INDEFINITE);

            // Set the AutoReverse to true (once the PathTransition Node reaches the end of the Path,
                // it reverses and goes in the other direction)
            pathTransition.setAutoReverse(true);

            // Set the Duration of the PathTransition (how long it takes to complete the Path. Basically, this
                // sets the speed)
            pathTransition.setDuration(Duration.millis(speed));

            // Play the PathTransition
            pathTransition.play();

            // Adding the Line and Text to the Pane
            pane.getChildren().addAll(line, text);
        });

        // Create the Scene, Set the Title, Set the Scene to the Stage and Show the Stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Chapter 15, Part 2: Problem 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
