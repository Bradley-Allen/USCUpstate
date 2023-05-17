/**
 * @author Bradley Allen
 * Project 3 -- DrawRectangles.java
 * Creates a stage for the user to draw rectangles on
 * using the mouse click and drag.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;


public class DrawRectangles extends Application {
    private Rectangle currentRectangle;
    private Group root;
    Random rng = new Random();
    double startx, starty, endx, endy;

    /**
     * Creates a scene for drawing rectangles.
     * @param primaryStage - Stage to hold scene to draw rectangles on
     */
    public void start(Stage primaryStage) {
        root = new Group();

        Scene scene = new Scene(root, 500, 300, Color.BLACK);

        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);

        primaryStage.setTitle("Drawing Rectangles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** 
     * Adds a rectangle when mouse is pressed.
     * @param event
     */
    public void processMousePress(MouseEvent event) {
    	startx = event.getX();
    	starty = event.getY();
    	
    	currentRectangle = new Rectangle(0, 0, 0, 0);
    	currentRectangle.setFill(Color.color(rng.nextDouble(),
    			rng.nextDouble(), rng.nextDouble()));
    	currentRectangle.setX(startx);
    	currentRectangle.setY(starty);
    	
        root.getChildren().add(currentRectangle);
    }

    /**
     * Animates rectangle changing as mouse is dragged.
     * @param event
     */
    public void processMouseDrag(MouseEvent event) {
    	endx = event.getX();
    	endy = event.getY();
    	double deltaX = endx - startx;
        double deltaY = endy - starty;
        
        /** If delta is less than, the rectangle
         *  is in a negative dimension */
        if(deltaX < 0) {
        	currentRectangle.setX(endx);
        	currentRectangle.setWidth(-deltaX);
        }
        else {
        	currentRectangle.setX(startx);
        	currentRectangle.setWidth(deltaX);
        }

        if(deltaY < 0) {
        	currentRectangle.setY(endy);
        	currentRectangle.setHeight(-deltaY);
        }
        else {
        	currentRectangle.setY(starty);
        	currentRectangle.setHeight(deltaY);
        }
    }

    /**
     * Launches the program
     * @param args - minus the pirates
     */
    public static void main(String[] args) {
        launch(args);
    }
}