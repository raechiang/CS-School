/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

//-----------------------------------import-------------------------------------
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Subject in the Observer design pattern. It contains
 * a list of observers and allows the subject (when extended) to attach, detach,
 * and notify its observers.
 */
public abstract class Subject
{
//-----------------------------------field--------------------------------------
    /**
     * Essentially like a list of followers
     */
    private List<Observer> observers = new ArrayList<Observer>();
    
//-----------------------------------methods------------------------------------
    /**
     * This method adds a new observer to {@link #observers}.
     * @param observer - the new observer to add
     */
    public void attach(Observer observer)
    {
        observers.add(observer);
        observer.addToFollowing(this);
    }
    
    /**
     * This method notifies the {@link #observers} when a notable event occurs.
     * It is passed only by the {@link User} in our case. 
     */
    public void notifyObservers()
    {
        for(Observer ob : observers)
        {
            ob.update(this);
        }
    }
}
