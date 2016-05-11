package edu.cmu.qatar.cs214.hw.hw4;

import java.awt.Dimension;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		TwitterDataPlugin TD = new TwitterDataPlugin();
		MaxUsedDayPlugin TA = new MaxUsedDayPlugin();
		MaxUsedDayPiePlugin TA1 = new MaxUsedDayPiePlugin();

		ArrayList<AnalysisPlugin> analysisPluginList = new ArrayList<AnalysisPlugin>();
		ArrayList<DataPlugin> dataPluginList = new ArrayList<DataPlugin>();
		dataPluginList.add(TD);
		analysisPluginList.add(TA);
		analysisPluginList.add(TA1);

		Framework framework = new Framework(dataPluginList, analysisPluginList);
		framework.registerDataPlugin(TD);

		JFrame mainWindow = new JFrame("Analytics");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setPreferredSize(new Dimension(500, 500));
		framework.setOpaque(true);
		mainWindow.setContentPane(framework);
		mainWindow.pack();
		mainWindow.setVisible(true);

	}

}
