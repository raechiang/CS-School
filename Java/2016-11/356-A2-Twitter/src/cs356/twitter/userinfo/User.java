/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

//-----------------------------------imports------------------------------------
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

/**
 * This class represents a User, which is a {@link Subject}, {@link Observer],
 * and a {@link UserElement}. The user has many functions, which it inherits 
 * from the Subject class and which it must implement from the UserElement and
 * Observer classes.
 */
public class User extends Subject implements UserElement, Observer
{
//-----------------------------------fields-------------------------------------
    /**
     * This field is the username, which must be unique and is enforced in the
     * {@link MainGUI}. It is instantiated in {@link #User(String)} and can be
     * retrieved via {@link #toString()}.
     */
    private String username;
    
    /**
     * This field holds the user's most recently submitted message. It is
     * instantiated in {@link #User(String)}, can be modified in {@link 
     * #postMessage(String)}, and can be retrieved in {@link 
     * #getPostedMessage()}.
     */
    private String message;
    
    /**
     * This field is a list of strings which are the user's messages. It is
     * instantiated in {@link #User(String)} and is added to in {@link 
     * #postMessage(String)}. This is necessary because the visitor needs to
     * count how many total messages there are across all users and to find
     * the positive messages percentage. It may also come in handy to have a
     * history of all of a user's posted tweets.
     */
    private List<String> userMessages;
    
    /**
     * This field is for the JList display and holds tweets from users that this
     * user is following. It is instantiated in {@link #User(String)} and added
     * to through {@link #update(Subject)} and {@link #postMessage(String)}. It
     * can be accessed outside of the class by {@link #getDashboardMessages()}.
     */
    private DefaultListModel<String> newsFeed;
    
    /**
     * This field is for the JList display of the users that this user is 
     * following. It is instantiated in {@link #User(String)} and can be added
     * to in {@link #addToFollowing(Subject)}. It can be retrieved outside of
     * this class through {@link #getFollowing()}.
     */
    private DefaultListModel<User> following;
    
//---------------------------------constructor----------------------------------
    /**
     * This is the constructor and it simply instantiates some fields: the
     * {@link #username} with the passed String argument username, the {@link
     * #userMessages} as an empty new String ArrayList, the {@link #newsFeed} as
     * an empty new DefaultListModel, and the {@link #following} as an empty new
     * DefaultListModel.
     * @param username - string to be saved in {@link #username}. It is checked
     *                      to be unique by {@link MainGUI}.
     */
    public User(String username)
    {
        this.username = username;
        message = "";
        userMessages = new ArrayList<String>();
        newsFeed = new DefaultListModel<String>();
        following = new DefaultListModel<User>();
    }
    
//-----------------------------------methods------------------------------------
    /**
     * This is the method to override from the {@link Observer} interface. The
     * update(Subject) method is called by an observer in {@link Subject} when
     * some event occurs (in this case, when the subject being observed posts a
     * new tweet). When this method is called, it adds a new String to {@link 
     * #newsFeed} which is displayed in the JList in {@link 
     * cs356.twitter.control.UserGUI} with the help of {@link 
     * #getDashboardMessages()}.
     */
    @Override
    public void update(Subject subject)
    {
        newsFeed.addElement(((User) subject).toString() + ": " + 
                            ((User) subject).getPostedMessage());
    }
    
    /**
     * This is the method to override from the {@link Observer} interface. When
     * a new observer is attached/added to a subject, the observer's {@link 
     * #following} must be updated, which is why this method exists. The 
     * {@link #following} is displayed in the JList in
     * {@link cs356.twitter.control.UserGUI} with the help of {@link 
     * #getFollowing()}.
     */
    @Override
    public void addToFollowing(Subject subject)
    {
        following.addElement((User) subject);
    }
    
    /**
     * This is the method to override from {@link UserElement} and handles
     * visiting. A visitor may visit this user or its {@link #userMessages}.
     */
    @Override
    public void accept(UserElementVisitor visitor)
    {
        visitor.visitUser(this);
        visitor.visitMessage(userMessages);
        visitor.visitPosMessage(userMessages);
    }
    
    /**
     * This is a method to override from {@link UserElement}. It is a simple
     * getter for the {@link #username} but posed as a toString() override 
     * because of how the UsersTree display works.
     */
    @Override
    public String toString()
    {
        return username;
    }
    
    /**
     * This method is called outside of this class in {@link 
     * cs356.twitter.control.UserGUI} when a new message is being posted by this
     * user. It updates the {@link #userMessages} String list, the {@link 
     * #message}, and the {@link #newsFeed}, and tells its observers through 
     * {@link #notifyObservers()} that something they should watch out for has
     * changed (and the observers subsequently will update their own {@link 
     * #newsFeed}s.
     */
    public void postMessage(String message)
    {
        userMessages.add(message);
        this.message = message;
        newsFeed.addElement(this.toString() + ": " + message);
        notifyObservers();
    }
    
    /**
     * This is a simple getter for this user's {@link #message}.
     */
    private String getPostedMessage()
    {
        return message;
    }
    
    /**
     * This is also a simple getter for this user's {@link #newsFeed}.
     */
    public DefaultListModel<String> getDashboardMessages()
    {
        return newsFeed;
    }
    
    /**
     * This is a simple getter for this user's {@link #following}.
     */
    public DefaultListModel<User> getFollowing()
    {
        return following;
    }
}
