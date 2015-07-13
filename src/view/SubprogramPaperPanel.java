package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.AccountDatabase;
import model.Conference;
import model.ConferenceDatabase;
import model.Paper;
import model.Recommendation;
import model.User;
import controller.SerializableController;

/**
 * Used by subprogram chair, lists all the papers the subprogram is assigned to.
 * 
 * @author Harmeet Singh
 * 
 */
public class SubprogramPaperPanel extends JPanel {

	/**
	 * Needed for serialization
	 */
	private static final long serialVersionUID = 914570560281249544L;
	private static final Dimension SIZE = new Dimension(100, 26);
	private static String REGEX = "[.][^.]+$"; // replaces the extension of a file. Found at StackOverflow question 924394
	private SubprogramChairPanel parentPanel;
	private Paper paper;
	private User user;
	private JButton viewPaperButton;
	private JButton submitRecom;
	private AccountDatabase actDB;
	private ConferenceDatabase conDB;
	private Conference c;

	/**
	 * The constructor for SubprogramPaperPanel that intializes and gives actions to buttons that are assigned specifically
	 * to the SubProgram Chair
	 * 
	 * @param panel The Parent panel
	 * @param u the user
	 * @param p the paper
	 * @param theActDB Account database
	 * @param theConDB Conference database
	 */
	public SubprogramPaperPanel(SubprogramChairPanel panel, User u, Paper p,
			AccountDatabase theActDB, final ConferenceDatabase theConDB) {
		super();
		setOpaque(false);
		parentPanel = panel;
		user = u;
		paper = p;
		actDB = theActDB;
		conDB = theConDB;
		c = conDB.getConferences().get(u.getConference().getConferenceId());
		viewPaperButton = new JButton("View Paper");
		submitRecom = new JButton("Submit Recommendation");
		JButton viewReview = new JButton("View Review");
		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		File r = new File("papers\\" + String.valueOf((user.getConference().getConferenceId())) 
				+ "\\" + paper.getAuthor() + "\\" + fileNameNoExt + "\\reviews"); 
		 
		
		if (r.exists()) {
			viewReview.setEnabled(true);
		}
		else {
			viewReview.setEnabled(false);
		}
		viewReview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ViewReviewPanel view = new ViewReviewPanel(user, paper);
				JFrame viewFrame = new JFrame();
				viewFrame.add(view);
//				reviewFrame.pack();
				viewFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				viewFrame.setMinimumSize(new Dimension(400, 500));
				viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				viewFrame.setVisible(true);
			}
		});
		JButton assignReviewers = new JButton("Assign Reviewers");
		if (paper.getReviewers().size() < 4) {
			assignReviewers.setEnabled(true);
		} else {
			assignReviewers.setEnabled(false);
		}
		assignReviewers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<User> reviewer = c.getReviewers();
				Object[] rev = new Object[reviewer.size()];
				int i = 0;
				for (User u : reviewer) {
					if (!paper.getAuthor().equals(u.getUserName())) {
						rev[i] = u.getUserName();
						i++;
					}
				}
				Object theR = JOptionPane.showInputDialog(null,
						"Choose a reviewer for this paper",
						"Reviewer for Paper", JOptionPane.PLAIN_MESSAGE, null,
						rev, 0);
//				System.out.println(theR);
				User revU = actDB.getAccounts().get(theR)
						.getUserConfId(c.getConferenceId());
				revU.addObserver(new SerializableController());
				paper.addReviewer(revU);
				revU.addPaper(paper); // adding for review
				actDB.add(actDB.getAccounts().get(revU.getUserName()));
				actDB.update();
				conDB.update();
				parentPanel.update();
				parentPanel.validate();
			}
		});

		JLabel title = new JLabel();
		title.setForeground(Color.YELLOW);
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(120, 26));
		title.setText(p.getTitle());

		JLabel author = new JLabel();
		author.setForeground(Color.YELLOW);
		author.setHorizontalTextPosition(SwingConstants.CENTER);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setPreferredSize(SIZE);
		author.setText(p.getAuthor());

		JButton reviewer = new JButton("View Reviewer");
		reviewer.setHorizontalTextPosition(SwingConstants.CENTER);
		reviewer.setHorizontalAlignment(SwingConstants.CENTER);
		reviewer.setPreferredSize(SIZE);
		if (paper.getReviewers().size() > 0)
		{
			reviewer.setEnabled(true);
		} else {
			reviewer.setEnabled(false);			
		}
		reviewer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Paper thePaper = paper;
				 ArrayList<Paper> v = user.getConference().getPapers();
				for (Paper p : v) {
					if (p.getPaper().equals(paper.getPaper())) {
						thePaper = p;						
					}
				}
				List<User> val = thePaper.getReviewers();
				StringBuilder string = new StringBuilder("");
				for (User u : val) {
					string.append(u.getUserName());
					string.append(System.getProperty("line.separator"));
				}
				JOptionPane.showMessageDialog(null, string);
				
			}
		});		
		
		JLabel status = new JLabel();
		status.setForeground(Color.YELLOW);
		status.setHorizontalTextPosition(SwingConstants.CENTER);
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setPreferredSize(SIZE);
		Paper ppaper = paper;
		ArrayList<Paper> v = user.getConference().getPapers();
		for (Paper paperr : v) {
			if (paperr.getPaper().equals(paper.getPaper())) {
				ppaper = paperr;						
			}
		}
		status.setText(ppaper.getReviews() + " Reviews");
		

		addListeners();

		this.add(title);
		this.add(author);
		this.add(reviewer);
		this.add(status);
		this.add(viewPaperButton);
		this.add(viewReview);
		this.add(submitRecom);
		this.add(assignReviewers);

		this.setPreferredSize(new Dimension(1100, 50));
		this.setMaximumSize(new Dimension(1100, 50));
	}

	private void addListeners() {
		viewPaperButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewPaper();
			}
		});

		submitRecom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitRecomm();
			}
		});
	}

	/**
	 * The work performed in order to submit a recommendation. 
	 */
	private void submitRecomm() {

		final JFrame recommFrame = new JFrame("Recommendation");
		Recommendation rec = new Recommendation();
		if (paper.getRecommendation() != null) {
			rec = paper.getRecommendation();
		}
		final RecommendationPanel recPanel = new RecommendationPanel(rec);
		JPanel tempOuter = new JPanel(new BorderLayout());

		tempOuter.add(recPanel);
		JButton sumbitButton = new JButton("Submit Recommendation");
		sumbitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					recPanel.sumbitRec(user, paper);
					recommFrame.dispose();
			}			
		});

		tempOuter.add(sumbitButton, BorderLayout.SOUTH);
		recommFrame.getContentPane().add(tempOuter);
		// reviewFrame.pack();
		// reviewFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		recommFrame.setMinimumSize(new Dimension(400, 500));
		recommFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		recommFrame.setVisible(true);
		parentPanel.validate();

	}

	/**
	 * The work performed in order to view a Paper as a spc.
	 */
	private void viewPaper() {
		File path = paper.getPaper();
		Desktop desktoptool = Desktop.getDesktop();

		try {
			desktoptool.open(path);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error Viewing the File");
		}
	}
}
