/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.widgets;

//-----------------------------------imports------------------------------------
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * This method is a simple Button. It's really just a JButton but made so that a
 * JButton can be created in a more compact way and added to a JFrames JPanel
 * more easily.
 */
public class Button implements Widget
{
//-----------------------------------field--------------------------------------
    /**
     * The JButton.
     */
    private JButton button;
    
//---------------------------------constructor----------------------------------
    /**
     * This constructor sets up the JButton that is needed in one declaration.
     * @param name
     * @param command
     * @param listener
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Button(String name,
            String command,
            ActionListener listener,
            int x,
            int y,
            int width,
            int height)
    {
        button = new JButton(name);
        button.setActionCommand(command);
        button.addActionListener(listener);
        button.setBounds(x, y, width, height);
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This method just returns the {@link #JComponent} which in this case is 
     * simply the {@link #button}.
     */
    @Override
    public JComponent getJComponent()
    {
        return button;
    }
}
