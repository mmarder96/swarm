/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swarm;

import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author mmarder
 *
 */
public class BeeTest {
    
    public BeeTest() {
    }

    /**
     * Test of getLocation method, of class Bee.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        
        Point2D location = new Point2D(10, 30);
        Swarm sw = new Swarm();
        Point2D motion = new Point2D(10, 10);
        Bee bee = new Bee(sw, location, motion);
        Point2D result = bee.getLocation();
        
        // the bee's location at the end of the constructor
        // should be the old location + the passed in first
        // motion.
        assertEquals(location.add(motion), result);

    }
    
    /**
     * Test of move method, of class Bee.
     */
    @Test
    public void testMove() {
        Swarm sw = new Swarm();
        Point2D motion = new Point2D(-1.0, 1.0);
        
        Point2D location = new Point2D(10, 30);
        Bee bee = new Bee(sw, location, new Point2D(10, 10));       
        Point2D oldLoc = bee.getLocation();
        bee.move(motion);
        Point2D newLoc = bee.getLocation();
        
        // the new location should be the old location + the motion
        assertEquals(motion, newLoc.subtract(oldLoc));
        
    }
    
    /**
     * Test of getDirection method, of class Bee.
     */
    @Test
    public void testGetDirection() {
        Swarm sw = new Swarm();
        Point2D newVec = new Point2D(-1.0, 1.0);
        
        Point2D location = new Point2D(10, 30);
        Bee bee = new Bee(sw, location, new Point2D(10, 10));       
        bee.move(newVec);
        
        // the vector should match the latest move, normalized
        assertEquals(newVec.normalize(), bee.getDirection());
    }
    
    /**
     * Test of update method, of class Bee.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Swarm sw = new Swarm();

        Point2D location = new Point2D(10, 30);
        Bee bee = new Bee(sw, location, new Point2D(10, 10));

        for (int i = 0; i < 1000; i++) {
            
            // get the Bee's old state
            Point2D oldLoc = bee.getLocation();
            Point2D oldDirection = bee.getDirection();
            Point2D oldTarget = bee.getCurrentTarget();
            
            bee.update();
            
            // check the new target
            Point2D newTarget = bee.getCurrentTarget();

            // check to be sure the target changes when the Bee is close to the old
            // target
            if (oldLoc.distance(oldTarget) < 10) {
                System.out.println("target changed");
                assertNotSame(oldTarget, newTarget);
            }

            // check to be sure the Bee goes in a better direction than it did 
            // before.
            Point2D newLoc = bee.getLocation(); // the location the bee is in
            // after updating using its new vector.
            double velocity = newLoc.distance(oldLoc);
            Point2D oldNewLoc = oldLoc.add(oldDirection.multiply(velocity)); // the location the
            // bee would be in if it had moved according to its old vector.
            if (oldLoc.distance(newTarget) > 0.00001) {
            
                // a start on debugging output, if you need it.
                // System.out.println("Update number: " + i);
                // System.out.println(oldNewLoc.distance(newTarget));
                // System.out.println(newLoc.distance(newTarget));
                
                assert (oldNewLoc.distance(newTarget) > newLoc.distance(newTarget));
            }
        }
    }
}
