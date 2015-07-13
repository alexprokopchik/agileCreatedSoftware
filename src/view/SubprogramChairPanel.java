/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.AccountDatabase;
import model.ConferenceDatabase;
import model.Paper;
import model.User;
import controller.DatabaseConstructor;

/**
 * The subprogram chair panel tab.
 * 
 * @author Harmeet Singh
 * 
 */
public class SubprogramChairPanel extends JPanel {
	/**
	 * Needed for serialization.
	 */
	private static final long serialVersionUID = 5642412904860430670L;
	private static final Dimension SIZE = new Dimension(100, 26);
	private User user;
	private AccountDatabase actDatabase;
	ConferenceDatabase confDatabase;

	/**
	 * The constructor for the SubProgramChairPanel
	 * @param theUser the current User
	 * @param dbcon The Database Constructor
	 */
	public SubprogramChairPanel(final User theUser,
			final DatabaseConstructor dbcon) {
		super();
		user = theUser;
		actDatabase = dbcon.actDatabase;
		confDatabase = dbcon.conDatabase;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		update();
	}
	
	/**
	 * Updates the Subprogram Chair Panel and includes the column titles.
	 */
	public void update() {
		this.removeAll();
		JPanel tpanel = new JPanel();
		tpanel.setOpaque(false);
		tpanel.setPreferredSize(new Dimension(1000, 50));
		tpanel.setMaximumSize(new Dimension(1000, 50));

		JButton ptitle = new JButton("Paper Title\t");
		ptitle.setEnabled(false);
		ptitle.setOpaque(false);
		ptitle.setForeground(Color.BLACK);
		ptitle.setPreferredSize(new Dimension(120, 26));
		
		JButton author = new JButton("Author");
		author.setPreferredSize(SIZE);
		author.setEnabled(false);
		author.setOpaque(false);
		
		JButton reviewer = new JButton("Reviewer");
		reviewer.setPreferredSize(SIZE);
		reviewer.setEnabled(false);
		reviewer.setOpaque(false);
		
		JButton status = new JButton("Status");
		status.setPreferredSize(SIZE);
		status.setEnabled(false);
		status.setOpaque(false);
		
		JButton view = new JButton("View Paper");
		view.setPreferredSize(SIZE);
		view.setEnabled(false);
		view.setOpaque(false);
		
		JButton getreview = new JButton("View Review");
		getreview.setPreferredSize(new Dimension(125, 26));
		getreview.setEnabled(false);
		getreview.setOpaque(false);
		
		JButton srev = new JButton("Submit Recommendation");
		srev.setPreferredSize(new Dimension(150, 26));
		srev.setEnabled(false);
		srev.setOpaque(false);
		
		JButton assignRev = new JButton("Assign Reviewers");
		assignRev.setPreferredSize(new Dimension(125, 26));
		assignRev.setEnabled(false);
		assignRev.setOpaque(false);

		tpanel.add(ptitle);
		tpanel.add(author);
		tpanel.add(reviewer);
		tpanel.add(status);
		tpanel.add(view);
		tpanel.add(getreview);
		tpanel.add(srev);
		tpanel.add(assignRev);

		tpanel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(tpanel);

		if (user != null) {
			ArrayList<Paper> papers = user.getConference().getPapers();
			for (Paper p : papers) {
				if (!p.getAuthor().equals(user.getUserName())) { 
					SubprogramPaperPanel panel = new SubprogramPaperPanel(this,
							user, p, actDatabase, confDatabase);
					panel.setAlignmentX(CENTER_ALIGNMENT);
					this.add(panel);
				}
			}
		}
		this.validate();
		this.repaint();
	}
}