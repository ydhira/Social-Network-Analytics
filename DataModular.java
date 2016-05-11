package edu.cmu.qatar.cs214.hw.hw4;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataModular {

	private List<DataPlugin> dataPlugin;

	public DataModular(List<DataPlugin> dataPlugin) {

		this.dataPlugin = dataPlugin;
	}

	/**
	 * @param userID
	 *            id for which to get the activity
	 * @return D where D is the dates userID was active at
	 */
	public HashMap<String, ArrayList<Date>> getUserActivity(String userID) {

		HashMap<String, ArrayList<Date>> hashResult = new HashMap<String, ArrayList<Date>>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getUserActivity(userID));
		}

		return hashResult;
	}

	/**
	 * @param userID
	 *            the id for which to get all the firends
	 * @return all the friends
	 */
	public HashMap<String, ArrayList<String>> getUserFriends(String userID) {

		HashMap<String, ArrayList<String>> hashResult = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getUserFriends(userID));
		}

		return hashResult;
	}

	/**
	 * @param userID
	 *            the id for which to get the personal info
	 * @return the personal info
	 */
	public HashMap<String, ArrayList<String>> getPersonalInfo(String userID) {
		HashMap<String, ArrayList<String>> hashResult = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getPersonalInfo(userID));
		}

		return hashResult;
	}

	/**
	 * @param userID
	 *            the id for which to get all the recom friends
	 * @return the recommended friends
	 */
	public HashMap<String, ArrayList<String>> getRecommendedFriends(
			String userID) {

		HashMap<String, ArrayList<String>> hashResult = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getRecommendedFriends(userID));
		}

		return hashResult;

	}

	/**
	 * @param userID
	 *            the id for which to get the number of friends
	 * @return the number of friends
	 */
	public HashMap<String, Integer> getNumberFriends(String userID) {

		HashMap<String, Integer> hashResult = new HashMap<String, Integer>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getNumberFriends(userID));
		}

		return hashResult;
	}

	/**
	 * @param userID
	 *            the id for which to get the status
	 * @return the status
	 */
	public HashMap<String, ArrayList<String>> getStatus(String userID) {
		HashMap<String, ArrayList<String>> hashResult = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < dataPlugin.size(); i++) {

			hashResult.put(this.dataPlugin.get(i).getSource(), this.dataPlugin
					.get(i).getStatus(userID));
		}

		return hashResult;

	}

}
