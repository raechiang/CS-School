/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

/**
 * The observer interface. An observer must be updated. In this assignment, a
 * list of followers must also be saved.
 */
public interface Observer
{
//-----------------------------------methods------------------------------------
    public void update(Subject subject);
    public void addToFollowing(Subject subject);
}
