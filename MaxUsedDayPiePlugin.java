package edu.cmu.qatar.cs214.hw.hw4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class MaxUsedDayPiePlugin implements AnalysisPlugin {
	private String userID;
	private DataModular dataModular;
	private ArrayList<String> sourcesUsed;
	private int maxUsed = 0;
	private static int[] daysN;
	private int maximum;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#registerDataModular(edu.cmu
	 * .qatar.cs214.hw.hw4.DataModular)
	 */
	@Override
	public void registerDataModular(DataModular dM) {
		sourcesUsed = new ArrayList<String>();
		this.dataModular = dM;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#getTabText()
	 */
	@Override
	public String getTabText() {
		// TODO Auto-generated method stub
		return "Pie Chart";
	}

	/**
	 * @param dataset
	 *            data to be represented
	 * @return J where J is the representation in the form of pie chart
	 */
	private JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Most Active Time", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	/**
	 * @return the sample dataset
	 */
	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Sunday", daysN[0]);
		dataset.setValue("Monday", daysN[1]);
		dataset.setValue("Tuesday", daysN[2]);
		dataset.setValue("Wednesday", daysN[3]);
		dataset.setValue("Thursday", daysN[4]);
		dataset.setValue("Friday", daysN[5]);
		dataset.setValue("Saturday", daysN[6]);
		return dataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#ChangeGui()
	 */
	@Override
	public JPanel ChangeGui() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(createDataset());
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(270, 270));
		panel.add(chartPanel, BorderLayout.CENTER);
		JLabel dayMostlyLabel = new JLabel();
		JLabel sources = new JLabel();
		String dayMostlyUSed = "";
		if (maximum == 1) {
			dayMostlyUSed = "Sunday";
		}

		if (maximum == 2) {
			dayMostlyUSed = "Monday";
		}

		if (maximum == 3) {
			dayMostlyUSed = "Tuesday";
		}

		if (maximum == 4) {
			dayMostlyUSed = "Wednesday";
		}

		if (maximum == 5) {
			dayMostlyUSed = "Thursday";
		}

		if (maximum == 6) {
			dayMostlyUSed = "Friday";
		}

		if (maximum == 7) {
			dayMostlyUSed = "Saturday";
		}

		dayMostlyLabel.setText("' " + this.userID + " '"
				+ " used Twitter most on " + dayMostlyUSed);
		dayMostlyLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(dayMostlyLabel, BorderLayout.SOUTH);

		String newS = "Sources used are ";
		for (int i = 0; i < sourcesUsed.size(); i++) {
			if (i == sourcesUsed.size() - 1) {
				newS += sourcesUsed.get(i);
			} else {
				newS += sourcesUsed.get(i) + " ,";
			}
		}

		sources.setText(newS);
		panel.add(sources, BorderLayout.NORTH);
		this.maximum = 0;
		sourcesUsed = new ArrayList<String>();
		return panel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#process(java.lang.String,
	 * int)
	 */
	@Override
	public void process(String userID, int k) {
		// TODO Auto-generated method stub
		this.userID = userID;

		HashMap<String, ArrayList<Date>> hashResult = dataModular
				.getUserActivity(userID);

		Iterator<String> keySetIterator = hashResult.keySet().iterator();
		ArrayList<Date> result = new ArrayList<Date>();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			sourcesUsed.add(key);
			result.addAll(hashResult.get(key));
		}
		Calendar cal = Calendar.getInstance();

		ArrayList<Integer> days = new ArrayList<Integer>();
		daysN = new int[7];

		for (Date date : result) {
			cal.setTime(date);
			days.add(cal.get(Calendar.DAY_OF_WEEK));
		}

		for (int i : days) {
			if (i == 1) {
				daysN[0] += 1;
			}
			if (i == 2) {
				daysN[1] += 1;
			}
			if (i == 3) {
				daysN[2] += 1;
			}
			if (i == 4) {
				daysN[3] += 1;
			}
			if (i == 5) {
				daysN[4] += 1;
			}
			if (i == 6) {
				daysN[5] += 1;
			}
			if (i == 7) {
				daysN[6] += 1;
			}
		}
		for (int j = 0; j < 7; j++) {
			if (daysN[j] > maxUsed) {
				maxUsed = daysN[j];
				maximum = j + 1;
			}
		}
		System.out.println("maximum is " + maximum);
		this.maxUsed = 0;
	}

}
