package swarm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Class representing a Swarm of Bees. The bees are drawn as lines, and update 
 * their direction and location towards a series of fixed points on the screen.
 * @author mmarder
 */
public class Swarm extends DrawingPane {

    public static final int NUM_BEES = 400;
    private Bee[] bees = new Bee[NUM_BEES];
    private static final int MILLISEC = 200; // the animation frame rate, in 
    // seconds. 1000 is 1 second per update.

    /**
     * The constructor sets the animation frame rate in milliseconds, and 
     * initializes a set of bees.
     */
    public Swarm() {
        super(MILLISEC, Color.ANTIQUEWHITE);
        initializeBees();

    }
    
    /**
     * Initializes NUM_BEES bees at random locations on the screen, with 
     * random first motion vectors. The maximum motion in either dimension is
     * 12.
     */
    
    private void initializeBees() {
        Random random = new Random();

        for (int i = 0; i < bees.length; i++) {
            bees[i] = new Bee(this, new Point2D(random.nextInt(xDim()), random.nextInt(yDim())), 
                new Point2D(random.nextInt(12), random.nextInt(12)));
        }
    }

    /**
     * Called whenever the animation updates. This causes each Bee to update 
     * (you will fill in the code for update in your Bees).
     */
    public void update() {
        for (int i = 0; i < bees.length; i++) {
            bees[i].update();
        }
    }

    /**
     * get the size of the screen in the x dimension.
     * @return the width of the screen.
     */
    public int xDim() {
        return (int) pane.getWidth();
    }

    /**
     * get the size of the screen in the y dimension.
     * @return the height of the screen.
     */
    public int yDim() {
        return (int) pane.getHeight();
    }

    /**
     * Adds a Line to the drawing. If the line's properties are updated (end
     * points, color, etc) the line on the screen will update automatically.
     * Each Bee should add one line and then update its properties over time.
     * @param line the line being added.
     */
    public void addLine(Line line) {
        pane.getChildren().add(line);
    }

    /**
     * Opens the window in which the bees are displayed. Do not change this code!
     * @param args 
     */
    public static void main(String args[]) {
        Application.launch(args);
    }

}
