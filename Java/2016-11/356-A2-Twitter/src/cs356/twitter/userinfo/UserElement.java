/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 2: Mini Twitter
 */
package cs356.twitter.userinfo;

/**
 * This is the UserElement interface which is implemented by {@link User} and
 * {@link #UserGroup}. They only have two methods in common.
 */
public interface UserElement
{
//-----------------------------------methods------------------------------------
    public String toString();
    public void accept(UserElementVisitor visitor);
}
