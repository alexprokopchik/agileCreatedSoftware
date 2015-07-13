package view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.AccountDatabase;
import model.ConferenceDatabase;
import model.Paper;
import model.User;

/**
 * The column labels within the Program Chair Panel.
 * @author Harmeet Singh
 * 
 */
public class ProgramChairPanel extends JPanel {
	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = -5588315012617577354L;
	private static final Dimension SIZE = new Dimension(100, 26);
	private User user;
	private AccountDatabase actDB;
	private ConferenceDatabase conDB;

	public ProgramChairPanel(final User theU, final AccountDatabase theActDB,
			final ConferenceDatabase theConDB, SubprogramChairPanel spcPanel) {
		super();
		user = theU;
		actDB = theActDB;
		conDB = theConDB;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		addAllComponents();
	}

	/**
	 * Sets up the title panel. Represents title for each column.
	 * 
	 * @return The title panel.
	 */
	private JPanel titlePanel() {
		JPanel tpanel = new JPanel();
		tpanel.setOpaque(false);
		tpanel.setPreferredSize(new Dimension(1000, 50));
		tpanel.setMaximumSize(new Dimension(1000, 50));

		JButton ptitle = new JButton("Paper Title");
		ptitle.setEnabled(false);
		ptitle.setOpaque(false);
		ptitle.setPreferredSize(new Dimension(120, 26));

		JButton author = new JButton("Author");
		author.setPreferredSize(SIZE);
		author.setEnabled(false);
		author.setOpaque(false);

		JButton view = new JButton("View Paper");
		view.setPreferredSize(SIZE);
		view.setEnabled(false);
		view.setOpaque(false);
		
		JButton revwer = new JButton("Reviewer");
		revwer.setPreferredSize(SIZE);
		revwer.setEnabled(false);
		revwer.setOpaque(false);

		JButton getreview = new JButton("vReview/vRecomm");
		getreview.setPreferredSize(new Dimension(125, 26));
		getreview.setEnabled(false);
		getreview.setOpaque(false);
		
		JButton acceptReject = new JButton("Accept/Reject");
		acceptReject.setPreferredSize(new Dimension(125, 26));
		acceptReject.setEnabled(false);
		acceptReject.setOpaque(false);

		JButton assignSubPC = new JButton("Assign Subprogram Chair");
		assignSubPC.setPreferredSize(new Dimension(125, 26));
		assignSubPC.setEnabled(false);
		assignSubPC.setOpaque(false);

		tpanel.add(ptitle);
		tpanel.add(author);		
		tpanel.add(view);
		tpanel.add(revwer);
		tpanel.add(getreview);
		tpanel.add(acceptReject);
		tpanel.add(assignSubPC);

		tpanel.setAlignmentX(CENTER_ALIGNMENT);
		return tpanel;
	}
	
	/**
	 * Adds all the components the panel.
	 */
	public void addAllComponents() {
		this.removeAll();

		this.add(titlePanel());
		
		ArrayList<Paper> allPapers = user.getConference().getPapers();
		for (int i = 0; i < allPapers.size(); i++) {
			Paper p = allPapers.get(i);
			PCPaperPanel panel = new PCPaperPanel(this, user, p, actDB, conDB);
			panel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(panel);
		}

//			Collection<Paper> allPapers = user.getConference().getPapers();
//			Iterator<Paper> itr = allPapers.iterator();
//			while (itr.hasNext()) {
//				System.out.println("Hello");
//				Paper p = itr.next();
//				PCPaperPanel panel = new PCPaperPanel(this, user, p, actDB, conDB);
//				panel.setAlignmentX(CENTER_ALIGNMENT);
//				this.add(panel);
//			}		
		
		this.validate();
		this.repaint();
	}
}
