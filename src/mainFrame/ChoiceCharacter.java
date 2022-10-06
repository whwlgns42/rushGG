package mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BGM.LoginBgm;
import frames.ChoiceLevel;
import frames.Ground;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceCharacter extends JFrame implements ActionListener {

	Ground gContext;

	JLabel backgroundImage;
	JLabel wizardLabel;
	JLabel warriorLabel;

	JButton choiceWizard;
	JButton choiceWarrior;

	int choiceCount;

	LoginBgm bgm;

	public ChoiceCharacter() {
		initData();
		setInitLayout();
		addEventListener();
		bgm = new LoginBgm();
	}

	private void initData() {
		setSize(1000, 700);
		setTitle("캐릭터 선택창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundImage = new JLabel(new ImageIcon("images/choiceBackground.png"));
		setContentPane(backgroundImage);

		ImageIcon wizardImg = new ImageIcon("images/wizardChoiceImage.png");
		wizardLabel = new JLabel(wizardImg);

		ImageIcon warriordImg = new ImageIcon("images/warriorChoiceImage.png");
		warriorLabel = new JLabel(warriordImg);

		ImageIcon choiceWizardBtn = new ImageIcon("images/choiceWizard.png");
		choiceWizard = new JButton(choiceWizardBtn);

		ImageIcon choiceWarriorBtn = new ImageIcon("images/choiceWarrior.png");
		choiceWarrior = new JButton(choiceWarriorBtn);

		ImageIcon startBtnImg = new ImageIcon("images/startBtn.png");

		choiceWizard.setRolloverIcon(startBtnImg);
		choiceWarrior.setRolloverIcon(startBtnImg);

		choiceWizard.setPressedIcon(startBtnImg);
		choiceWarrior.setPressedIcon(startBtnImg);

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		add(wizardLabel);
		wizardLabel.setSize(200, 170);
		wizardLabel.setLocation(247, 370);

		add(warriorLabel);
		warriorLabel.setSize(200, 170);
		warriorLabel.setLocation(530, 370);

		add(choiceWizard);
		choiceWizard.setSize(113, 43);
		choiceWizard.setLocation(285, 537);
		choiceWizard.setBorderPainted(false);
		choiceWizard.setContentAreaFilled(false);

		add(choiceWarrior);
		choiceWarrior.setSize(113, 43);
		choiceWarrior.setLocation(576, 537);
		choiceWarrior.setBorderPainted(false);
		choiceWarrior.setContentAreaFilled(false);

	}

	private void addEventListener() {
		choiceWizard.addActionListener(this);
		choiceWarrior.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == choiceWizard) {
			setVisible(false);
			choiceCount = 1;

			ChoiceLevel choiceLevel = new ChoiceLevel();
			choiceLevel.setCharcterNumber(choiceCount);
			// wizard

		} else {
			choiceCount = 2;
			setVisible(false);
			ChoiceLevel choiceLevel = new ChoiceLevel();
			choiceLevel.setCharcterNumber(choiceCount);
		}
		bgm.clipStop();

	}

	public static void main(String[] args) {
		new ChoiceCharacter();
	}

}
