package edu.cmu.qatar.cs214.hw.hw4;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Framework extends JPanel {

	private List<DataPlugin> dataPlugin;
	private List<AnalysisPlugin> analysisPlugin;
	private ArrayList<JPanel> jps;
	private ArrayList<JPanel> analysedPanelList;
	private JTabbedPane jtp;
	private JPanel selectedTab = new JPanel();
	private String userID = "";
	private DataModular dataModular;

	public Framework(List<DataPlugin> dataPlugin,
			List<AnalysisPlugin> analysisPlugin) {
		this.dataPlugin = dataPlugin;
		this.dataModular = new DataModular(this.dataPlugin);
		this.analysisPlugin = analysisPlugin;
		for (AnalysisPlugin aP : analysisPlugin) {
			aP.registerDataModular(dataModular);
		}

		analysedPanelList = new ArrayList<JPanel>();
		jps = new ArrayList<JPanel>();
		initGui();
	}

	/**
	 * initializes the mainFrame
	 */
	private void initGui() {
		setLayout(new BorderLayout());
		jtp = new JTabbedPane();

		for (int i = 0; i < analysisPlugin.size(); i++) {
			analysedPanelList.add(i, new JPanel());
		}

		for (int i = 0; i < analysisPlugin.size(); i++) {
			JPanel jp1 = new JPanel();
			jp1.setLayout(new BorderLayout());
			jp1.add(createCenterFrame(i), BorderLayout.NORTH);
			jp1.add(analysedPanelList.get(i), BorderLayout.SOUTH);
			jps.add(jp1);
			jtp.addTab(analysisPlugin.get(0).getTabText(), jp1);
		}
		add(jtp);
	}

	/**
	 * @param user
	 *            sets the user name to user
	 */
	private void setUserName(String user) {
		this.userID = user;
	}

	/**
	 * @return the jpanel with the textbox and the button
	 */
	private JPanel createCenterFrame(int i) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextField textfieldResult = new JTextField("Add User Name");
		JButton processButton = new JButton();
		processButton.setText("Process");
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String user = textfieldResult.getText();
				System.out.println("user is '" + user + "'");
				if (user.equals("") || user.equals("Add User Name")) {
					JOptionPane.showMessageDialog(panel, "Add a Player's Name");
				} else {
					setUserName(user);
					processMsg(i);
					changeGui(i);
				}
			}
		});
		textfieldResult.setText("");
		textfieldResult.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				textfieldResult.getMinimumSize().height));
		panel.add(textfieldResult);
		panel.add(processButton);
		return panel;
	}

	/**
	 * @param D
	 *            adds dataplugin D
	 */
	public void registerDataPlugin(DataPlugin D) {
		this.dataPlugin.add(D);
	}

	/**
	 * @param A
	 *            adds Analysis plugin A
	 */
	public void registerAnalysisPlugin(AnalysisPlugin A) {
		this.analysisPlugin.add(A);
	}

	/**
	 * processes the Msg for the user id
	 */
	public void processMsg(int i) {
		analysisPlugin.get(i).process(this.userID, i);
	}

	/**
	 * @param panel
	 *            changes the mainframe according to the panel rturned by the
	 *            analysis plugin
	 */
	public void changeGui(int k) {
		JPanel panel = analysisPlugin.get(k).ChangeGui();
		System.out.println("selected tab is " + selectedTab);
		jps.get(k).remove(analysedPanelList.get(k));
		analysedPanelList.set(k, panel);
		jps.get(k).add(analysedPanelList.get(k));
		System.out.println("added the panel to the main window");
	}

}
