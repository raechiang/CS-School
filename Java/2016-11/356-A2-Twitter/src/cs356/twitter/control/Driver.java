/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 * 
 * In this assignment, students are to simulate a mini, desktop Twitter with a
 * GUI using Java Swing. This assignment is meant to educate the students in
 * implementing the following four design patterns:
 *     1. Singleton - only one instance of the class may exist
 *     2. Observer - for a one-to-many dependency, so that when one object (a
 *             subject) changes, its observers are notified and updated
 *             automatically. 
 *     3. Visitor - for operating on different elements of an object hierarchy
 *     4. Composite - for treating objects uniformly by creating part-whole
 *             hierarchies
 */
package cs356.twitter.control;
import java.awt.EventQueue;

/**
 * This class just holds the driver which begins the whole program.
 */
public class Driver {

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                try
                {
                    MainGUI adminWindow = MainGUI.getInstance();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}
