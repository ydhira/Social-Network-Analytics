/**
 * 
 */
package edu.cmu.qatar.cs214.hw.hw4;

import javax.swing.JPanel;

/**
 * @author hira.yasin
 *
 */
public interface AnalysisPlugin {

	/**
	 * @return the JPanel with all the required information
	 */
	public JPanel ChangeGui();

	/**
	 * @param f
	 *            registers f as the Framework the plugin is associated to
	 */
	public void registerDataModular(DataModular dM);

	/**
	 * @return the tab text
	 */
	public String getTabText();

	/**
	 * @param userID
	 *            the userid to be represented
	 */
	public void process(String userID, int i);
}
