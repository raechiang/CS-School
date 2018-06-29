/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------imports------------------------------------
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * This method is a simple Button. It's really just a JButton but made so that a
 * JScrollPane can be created in a more compact way and added to a JFrames JPanel
 * more easily.
 */
public class ScrollPane implements Widget
{
//-----------------------------------field--------------------------------------
    /**
     * This is simply the {@link #scrollPane}
     */
    JScrollPane scrollPane;
    
//---------------------------------constructor----------------------------------
    /**
     * As with the rest, of the {@link Widget}s, this constructor allows the
     * creation of the widget to be done in one declaration.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param view
     */
    public ScrollPane(int x, int y, int width, int height, Component view)
    {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setViewportView(view);
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This simply returns the {@link #scrollPane}
     */
    @Override
    public JComponent getJComponent()
    {
        return scrollPane;
    }

}
