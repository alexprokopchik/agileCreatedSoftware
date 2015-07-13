package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.SerializableController;
import model.AccountDatabase;
import model.Conference;
import model.ConferenceDatabase;
import model.Paper;
import model.Recommendation;
import model.Review;
import model.User;

import java.awt.Color;

/**
 * Used by subprogram chair, lists all the papers the subprogram is assigned to.
 * 
 * @author Harmeet Singh
 * 
 */
public class PCPaperPanel extends JPanel {

	
	private static final long serialVersionUID = 7753693743712038724L;
	private static final Dimension SIZE = new Dimension(100, 26);
	private static String REGEX = "[.][^.]+$"; // replaces the extension of a file. Found at StackOverflow question 924394
	private Paper paper;
	private User user;	
	private AccountDatabase actDB;
	private ConferenceDatabase conDB;
	private Conference conference;
	
	private JButton assignSubPC;
	private JButton viewReviewRecomm;
	private JButton viewPaperButton;
	private JButton acceptReject;
	/**
	 * The constructor for the program chair paper panel.
	 * 
	 * @param programChairPanel the program chair panel
	 * @param u The User
	 * @param p The Paper
	 * @param theActDB Account Database
	 * @param theConDB Conference Database
	 */
	public PCPaperPanel(ProgramChairPanel programChairPanel, User u, Paper p, AccountDatabase theActDB, final ConferenceDatabase theConDB) {
		super();
		setOpaque(false);
		user = u;
		paper = p;
		actDB = theActDB;
		conDB = theConDB;

		conference = conDB.getConferences().get(u.getConference().getConferenceId());

		viewPaperButton = new JButton("View Paper");
		acceptReject = new JButton("Accept/Reject");
		assignSubPC = new JButton("Assign SubProgram Chair");
		viewReviewRecomm = new JButton("View Review/Recomm");			

		this.setPreferredSize(new Dimension(1000, 50));
		this.setMaximumSize(new Dimension(1000, 50));
		
		addComponents();
	}

	private void addComponents() {
		JLabel title = new JLabel();
		title.setForeground(Color.YELLOW);
		title.setPreferredSize(new Dimension(120, 26));
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setText(paper.getTitle());

		JLabel author = new JLabel();
		author.setForeground(Color.YELLOW);
		author.setHorizontalTextPosition(SwingConstants.CENTER);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setPreferredSize(SIZE);
		author.setText(paper.getAuthor());

		JButton reviewer = new JButton("View Reviewer");
		reviewer.setHorizontalTextPosition(SwingConstants.CENTER);
		reviewer.setHorizontalAlignment(SwingConstants.CENTER);
		reviewer.setPreferredSize(SIZE);
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

		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		File r = new File("papers\\" + String.valueOf((user.getConference().getConferenceId())) 
				+ "\\" + paper.getAuthor() + "\\" + fileNameNoExt + "\\reviews"); 		 
		
		if (r.exists()) {
			viewReviewRecomm.setEnabled(true);
			acceptReject.setEnabled(true);
			
		}
		else {
			viewReviewRecomm.setEnabled(false);
			acceptReject.setEnabled(false);
		}
		addListeners();

		this.add(title);
		this.add(author);
		this.add(viewPaperButton);
//		if (!(paper.getReviewers() == null)) {
//			String rev = "";
//			List<User> pp = paper.getReviewers();
//			for (int i = 0; i < pp.size(); i++) {
//				rev += pp.get(i).getUserName();
//			}
//			reviewer.setText(rev);
//		}
		this.add(reviewer);
		this.add(viewReviewRecomm);
		this.add(acceptReject);
		if (!(paper.getSubProgramChair() == null)) {
			assignSubPC.setText(paper.getSubProgramChair());
			assignSubPC.setEnabled(false);
		}
		this.add(assignSubPC);
	}

	/**
	 * Action listener for view button and submit reccomendation button
	 */
	private void addListeners() {
		viewPaperButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewPaper();
			}
		});

		acceptReject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				acceptReject();
			}
		});
		
		viewReviewRecomm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			ViewReviewPanel view = new ViewReviewPanel(user, paper);
			JFrame viewFrame = new JFrame();
			viewFrame.add(view);
//			reviewFrame.pack();
			viewFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			viewFrame.setMinimumSize(new Dimension(400, 500));
			viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			viewFrame.setVisible(true);}
		});
		
		assignSubPC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<User> subPC = conference.getSubprogramChairs();
				Object[] allSubPC = new Object[subPC.size()];
				int i = 0;
				for (User u : subPC) {
					if (!paper.getAuthor().equals(u.getUserName())) { 
						ArrayList<Paper> UPapers = (ArrayList<Paper>) u.getPaper();
						int size = 0;
						for (Paper p : UPapers) {
							if (!p.getAuthor().equals(u.getUserName())) {
								size++;
							}
						}
						if (size <= 4) {
							allSubPC[i] = u.getUserName(); // added to the
															// possible sub PC
							i++;
						}
					}
				}
				Object theR = JOptionPane.showInputDialog(null,
						"Choose a reviewer for this paper",
						"Reviewer for Paper", JOptionPane.PLAIN_MESSAGE, null,
						allSubPC, 0);
				System.out.println(theR);
				User revU = actDB.getAccounts().get(theR)
						.getUserConfId(conference.getConferenceId());
				revU.addObserver(new SerializableController());
				paper.setSubProgramChair(revU.getUserName());
				revU.addPaper(paper); // adding for review
				actDB.add(actDB.getAccounts().get(revU.getUserName()));
				actDB.update();
				conDB.update();
				// spc.update();
			}
		});
	}

	/**
	 * Actual work performed in order to submit a recommendation
	 */
	private void acceptReject() {
		final JFrame accRejFrame = new JFrame("Acceptance");
		final AcceptancePanel accRejPanel = new AcceptancePanel(accRejFrame, user, paper);
		JPanel tempOuter = new JPanel(new BorderLayout());

		tempOuter.add(accRejPanel);
		accRejFrame.getContentPane().add(tempOuter);
		accRejFrame.setMinimumSize(new Dimension(400, 500));
		accRejFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		accRejFrame.setVisible(true);
	}

	/**
	 * The actual work performed in order to view a paper
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
