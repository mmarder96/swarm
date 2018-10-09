package swarm;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Bee {

    Line line = new Line();
    Point2D currentLocation;
    Point2D currentDirection;
    Point2D[] targetArray = new Point2D[] {new Point2D(80,80), new Point2D(520, 520), new Point2D(300,520)};
    int currentTarget = 0;
   

    /**
     * Create a bee at the given location, drawing a line that gets its x and y
     * length from the "firstMotion" parameter.
     *
     * @param sw the swarm window in which the bee should be drawn
     * @param oldLocation x and y location at which the bee's line starts
     * @param firstMotion the length of the line, specified as an x length and y
     * length
     */
    Bee(Swarm sw, Point2D oldLocation, Point2D firstMotion) {
        line.setStartX(oldLocation.getX());
        line.setStartY(oldLocation.getY());
        line.setEndX(oldLocation.getX());
        line.setEndY(oldLocation.getY());
        sw.addLine(line);
        currentLocation = oldLocation;
        this.move(firstMotion);
    }

    /**
     * Get the bee's current location.
     *
     * @return a point in 2D space representing the Bee's location on the
     * screen.
     */
    public Point2D getLocation() {

        return currentLocation;
    }

    /**
     * Get the bee's most recent direction, as a normalized (length = 1) vector.
     *
     * @return A normalized vector that points in the direction the bee most
     * recently moved.
     */
    public Point2D getDirection() {

        return currentDirection;
    }

    /**
     * This method should move the bee, treating the parameter passed in as a
     * vector. The vector records both the amount the bee must move in the x
     * direction (use getX()), and in the y direction (use getY()).
     *
     * @param vector amount to move the bee in the x and y dimensions
     */
    public void move(Point2D vector) {
        currentLocation = currentLocation.add(vector.multiply(this.calculateVelocity()));
        line.setStartX(currentLocation.getX());
        line.setStartY(currentLocation.getY());
        currentDirection = new Point2D(vector.getX(), vector.getY()).normalize();
        line.setEndX(currentLocation.getX() - (this.calculateVelocity() * (currentDirection.getX())));
        line.setEndY(currentLocation.getY() - (this.calculateVelocity() * (currentDirection.getY())));
    }

    public double calculateVelocity() {
        double velocity = (currentLocation.distance(targetArray[currentTarget])) / (40);
        if (velocity >= 15) {
            velocity = 15;
        } else if (velocity <= 3) {
            velocity = 3;
        }
        return velocity;
    }

    /**
     * Gets the target location towards which the bee is currently moving. This
     * should be the target used for the most recent move.
     *
     * @return a point in 2D space representing the target location.
     */
    public Point2D getCurrentTarget() {
        return targetArray[currentTarget];
    }

    public Point2D calculateMoveVector() {
        if (this.getLocation().distance(this.targetArray[currentTarget]) < 10 && currentTarget < 2) {
            currentTarget ++;
        }else if(this.getLocation().distance(this.targetArray[currentTarget]) < 10 && currentTarget == 2) {
            currentTarget = 0;
        }
        double MAX = 0.3;
        double p = Math.random() * MAX;
        Point2D moveVector = ((this.getDirection().multiply(1 - p)).add((this.getCurrentTarget().subtract(currentLocation)).normalize().multiply(p)));
        return moveVector.normalize();

    }

    /**
     * Updates the Bee. This method is called whenever the animation frame
     * updates, and should make your bee swarm across the screen.
     */
    void update() {
        this.move(this.calculateMoveVector());
    }
}
