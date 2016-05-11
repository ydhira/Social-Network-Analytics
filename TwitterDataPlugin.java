package edu.cmu.qatar.cs214.hw.hw4;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;

public class TwitterDataPlugin implements DataPlugin {

	private ArrayList<Date> allTweetsDay;

	public TwitterDataPlugin() {
		allTweetsDay = new ArrayList<Date>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getDateForUser(java.lang.String)
	 */
	public ArrayList<Date> getUserActivity(String a) {
		allTweetsDay = new ArrayList<Date>();
		Date date;
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("WDfz1D78L0VhytoenU7hNbWsn",
				"QBg0qNABNWFlyY83zpGvWllGQgi90Y2LiA7nNCPSMt2hzW9Jzf");
		twitter.setOAuthAccessToken(new AccessToken(
				"709093442-yKHyDP1i03d2qDuA0mRVa6JJahRZn3LQ73JFHUlj",
				"mNLW0B8uJBZjXnNOZTDEoP2UfuwU72rlkbf7ALa1I6urg"));
		try {
			int c = 1;
			for (int i = 1; i < 3; i++) {
				Paging paging = new Paging(i, 50);
				ResponseList<Status> statuses = twitter.getUserTimeline(a,
						paging);
				for (Status b : statuses) {
					date = (Date) b.getCreatedAt();
					allTweetsDay.add(date);
					c++;
				}
			}
		} catch (Exception e) {

		}

		return allTweetsDay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getUserFriends(java.lang.String)
	 */
	@Override
	public ArrayList<String> getUserFriends(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getPersonalInfo(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPersonalInfo(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getRecommendedFriends(java.lang
	 * .String)
	 */
	@Override
	public ArrayList<String> getRecommendedFriends(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getNumberFriends(java.lang.String)
	 */
	@Override
	public int getNumberFriends(String userID) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cmu.qatar.cs214.hw.hw4.DataPlugin#getStatus(java.lang.String)
	 */
	@Override
	public ArrayList<String> getStatus(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return "Twitter";
	}

}
