package edu.cmu.qatar.cs214.hw.hw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MaxUsedDayPlugin implements AnalysisPlugin {

	private int maxUsed = 0;
	private int[] daysN;
	private int maximum;
	private String userID;
	private DataModular dataModular;
	private ArrayList<String> sourcesUsed;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#registerFramework(edu.cmu.qatar
	 * .cs214.hw.hw4.Framework)
	 */
	@Override
	public void registerDataModular(DataModular dM) {
		sourcesUsed = new ArrayList<String>();
		this.dataModular = dM;

	}

	/**
	 * @param dataset
	 *            the data to be represented in the graph
	 * @return the chart
	 */
	private JFreeChart createChart(final CategoryDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createBarChart(
				"Most Active Time", // chart title
				"Day of Week", // domain axis label
				"Number of Posts", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, true, false);

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
				0.0f, 0.0f, Color.lightGray);

		renderer.setSeriesPaint(0, gp0);
		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));
		return chart;

	}

	/**
	 * Returns a sample dataset.
	 * 
	 * @return The dataset.
	 */
	private CategoryDataset createDataset() {
		// row keys...
		final String series1 = "First";

		// column keys...
		final String category1 = "Sunday";
		final String category2 = "Monday";
		final String category3 = "Tuesday";
		final String category4 = "Wednesday";
		final String category5 = "Thursday";
		final String category6 = "Friday";
		final String category7 = "Saturday";

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(daysN[0], series1, category1);
		dataset.addValue(daysN[1], series1, category2);
		dataset.addValue(daysN[2], series1, category3);
		dataset.addValue(daysN[3], series1, category4);
		dataset.addValue(daysN[4], series1, category5);
		dataset.addValue(daysN[5], series1, category6);
		dataset.addValue(daysN[6], series1, category7);

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

		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
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
	 * @see edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#process(java.lang.String)
	 */
	public void process(String userID, int k) {
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
		this.maxUsed = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cmu.qatar.cs214.hw.hw4.AnalysisPlugin#getTabText()
	 */
	@Override
	public String getTabText() {
		return "Most Active on Twitter";
	}

}
