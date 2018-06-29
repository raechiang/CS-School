/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------import-------------------------------------
import javax.swing.JComponent;

/**
 * This is the interface that is extended by all the JComponents that this 
 * Twitter app will be using. It does not make ALL JComponents, just ones that 
 * are frequently used.
 */
public interface Widget
{
//-----------------------------------methods------------------------------------
    /**
     * Return the JComponent
     * @return
     */
    public JComponent getJComponent();
}
