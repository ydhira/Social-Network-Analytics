package edu.cmu.qatar.cs214.hw.hw4;

import java.util.ArrayList;
import java.util.Date;

public interface DataPlugin {

	/**
	 * @param userID
	 *            the data to be collected for this user
	 * @return D where D is the list of the days the user was active
	 */
	public ArrayList<Date> getUserActivity(String userID);

	/**
	 * @param userID
	 *            the id for which to get all the firends
	 * @return D where D is the arraylist of all the firends
	 */
	public ArrayList<String> getUserFriends(String userID);

	/**
	 * @param userID
	 *            the id for which to get all the info
	 * @return the info in string format
	 */
	public ArrayList<String> getPersonalInfo(String userID);

	/**
	 * @param userID
	 *            the id for which to get all the recommended friends
	 * @return the recommended friends
	 */
	public ArrayList<String> getRecommendedFriends(String userID);

	/**
	 * @param userID
	 *            the id for which to get all the number of firneds
	 * @return number of friends
	 */
	public int getNumberFriends(String userID);

	/**
	 * @param userID
	 *            the id for which to get the status
	 * @return the status for userID
	 */
	public ArrayList<String> getStatus(String userID);

	public String getSource();

}
