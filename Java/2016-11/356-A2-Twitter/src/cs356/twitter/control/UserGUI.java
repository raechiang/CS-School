/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.control;

//-----------------------------------import-------------------------------------
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs356.twitter.userinfo.*;
import cs356.twitter.widgets.*;

/**
 * This class represents the user view window. There are four functions:
 *     1. The current user can follow another existing user.
 *     2. The current user can post a new Tweet to their own and follwers' news
 *         feeds.
 *     3. The window displays the list of users that the current user is 
 *         following.
 *     4. The window displays the news feed as a list. The news feed contains
 *         the current user's own tweets and the tweets from their followers.
 */
public class UserGUI implements ActionListener
{
//-----------------------------------fields-------------------------------------
    /**
     * This field is the main user view window, which is instantiated and used
     * only in the {@link #UserGUI(User, UsersTree, int, TextArea)} constructor.
     */
    private JFrame frame;
    
    /**
     * This holds the contents/widgets of the window. 
     */
    private JPanel contentPane;
    
    /**
     * This field holds a reference to the user whose view is currently open.
     */
    private User user;
    
    /**
     * This field holds a reference to the tree.
     */
    private UsersTree usersTree;
    
    /**
     * This field holds a reference to the {@link MainGUI}'s statsTextArea.
     * It is mostly for printing out error messages and there is one line for a
     * successful follow.
     */
    private TextArea mainTextArea;
    
    /**
     * Similar to in the {@link MainGUI}, this windows' widgets are contained
     * in a list.
     */
    private List<Widget> widgets;
    
    /**
     * This field represents the text field input box for following a new user.
     */
    private TextField addFollowerTextField;
    
    /**
     * This field is the text field input box for the new tweet to post if the
     * button "Post Tweet" is clicked.
     */
    private TextField messageTextField;
    
    /**
     * This is a list view of the users the {@link #user} is following.
     */
    private JList<User> following;
    
    /**
     * This is also a list view but instead it is of the news feed, so it will
     * display both the {@link #user}'s own messages and the messages of the
     * users that the current user is {@link #following}.
     */
    private JList<String> newsFeed;
    
//---------------------------------constructor----------------------------------
    /**
     * The constructor. It creates a new window with {@link #frame} and {@link
     * #contentPane} and sets up the remaining fields and the {@link #widgets}
     * list is created using the help of {@link #makeWidgets()}.
     * @param user - the user currently being viewed. Instantiates {@link #user}
     * @param tree - the tree of users and groups from the {@link #MainGUI}.
     *         Instantiates {@link #usersTree}
     * @param winOffset - the QOL window offset. When you open the window, it
     *         would be nice if the windows did not all stack at the same xy-
     *         coordinate.
     * @param textArea - the statsTextArea in the {@link #MainGUI} is saved here
     *         as {@link #mainTextArea} and displays error and action messages.
     */
    public UserGUI(User user, UsersTree tree, int winOffset, TextArea textArea)
    {
        // the existing objects from the MainGUI
        this.user = user;
        this.usersTree = tree;
        this.mainTextArea = textArea;
        
        // the frame and contentPane are set up
        frame = new JFrame("Twitter: " + user.toString());
        frame.setBounds(100 + winOffset, 100 + winOffset, 388, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        frame.setVisible(true);
        
        // the three lists are set up
        following = new JList<User>(user.getFollowing());
        newsFeed = new JList<String>(user.getDashboardMessages());
        widgets = new ArrayList<Widget>();
        makeWidgets();
        for (Widget w : widgets)
        {
            contentPane.add(w.getJComponent());
        }
    }
    
//-----------------------------------methods------------------------------------
    /**
     * The same idea as {@link #MainGUI}'s {@link #makeWidgets()}... It creates
     * widgets and fills the {@link #widgets} list so all the widgets can
     * be added easily with {@link #contentPane}'s add function.
     */
    private void makeWidgets()
    {
        addFollowerTextField = new TextField("(username)", 10, 11, 230, 30);
        widgets.add(addFollowerTextField);
        widgets.add(new Button("Add Follower", "follow",
                    this, 250, 11, 114, 30));
        messageTextField = new TextField("(message)", 10, 203, 230, 30);
        widgets.add(messageTextField);
        widgets.add(new Button("Post Tweet", "post", this, 250, 203, 114, 30));
        
        widgets.add(new ScrollPane(10, 89, 354, 100, following));
        widgets.add(new ScrollPane(10, 285, 352, 100, newsFeed));
        
        widgets.add(new CenteredLabel("News Feed", 10, 244, 354, 30));
        widgets.add(new CenteredLabel("Following", 10, 48, 354, 30));
    }
    
    /**
     * The override for ActionListener. It processes all the button clicked
     * events:
     *     1. Following a new user, uses {@link #followGivenUser()}
     *     2. Posting a tweet, uses {@link #postTweet()}.
     * When following a new user, the username should appear in the
     * {@link #following} list. When tweeting a new message or when a user that
     * the current {@link #user} is following tweets a new message, the 
     * {@link #newsFeed} should be updated. This is handled by {@link User}.
     * @param arg0 - the button clicked
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        String command = arg0.getActionCommand();
        
        if (command.equals("follow"))
        {
            // follow a new user
            followGivenUser();
        }
        else if (command.equals("post"))
        {
            // post tweet
            postTweet();
        }
    }
    
    /**
     * This is used by {@link #actionPerformed(ActionEvent)} mainly for
     * organization's sake. It makes sure that the user can add the specified
     * user (in {@link #addFollowerTextField} to the {@link #following} list
     * by checking that (1) the {@link #addFollowerTextField} is nonempty, (2)
     * the specified user exists in the {@link #usersTree}, (3) the specified
     * user is indeed a {@link User} and not a {@link UserGroup}, and (4) the
     * user is not attempting to follow themselves. After passing all of those
     * conditions, it can finally add the user to the following list, which
     * is handled in the {@link User} class.
     * Maybe this could look neater if I had a separate boolean check or several
     * separate boolean check methods.
     */
    private void followGivenUser()
    {
        if (!(addFollowerTextField.getTFText().equals("")))
        {
            // the addFollowerTextField is not empty
            
            // find the user that the current user wants to follow from the tree
            UserElement theOneToFollow =
                    usersTree.getUser(addFollowerTextField.getTFText());
            
            if (theOneToFollow != null)
            {
                // if that user exists
                if (theOneToFollow instanceof User)
                {
                    // the corresponding node is a User
                    if (user != theOneToFollow)
                    {
                        // the user did not decide to try to follow themselves
                        if (!(user.getFollowing().contains(theOneToFollow)))
                        {
                            // the user is not already following the given user
                            
                            // passed all the checks, so we can finally add
                            mainTextArea.addText(user.toString() 
                                            + " is now following " 
                                                + theOneToFollow.toString() 
                                                        + "!");
                            ((User) theOneToFollow).attach(user);
                            addFollowerTextField.clear();
                        }
                        else
                        {
                            // the user is already following the given user
                            mainTextArea.addText("ERROR: " + user.toString()
                                            + " is already following "
                                                + theOneToFollow.toString()
                                                        + ".");
                        }
                    }
                    else
                    {
                        // the user tried to follow themselves
                        mainTextArea.addText("ERROR: Users cannot follow themselves.");
                    }
                }
                else
                {
                    // the node is a Group
                    mainTextArea.addText("ERROR: Groups cannot be followed.");
                }
            }
            else
            {
                // the specified user does not exist
                mainTextArea.addText("ERROR: No user named "
                            + addFollowerTextField.getTFText() + " exists.");
            }
        }
        else
        {
            // the text field is empty
            mainTextArea.addText("ERROR: No user specified.");
        }
    }
    
    /**
     * This method is called by {@link #actionPerformed(ActionEvent)} and simply
     * adds a new tweet.
     */
    private void postTweet()
    {
        if (!(messageTextField.getTFText().equals("")))
        {
            user.postMessage(messageTextField.getTFText());
            messageTextField.clear();
        }
    }
}
