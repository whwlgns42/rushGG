package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BGM.SelectBGM;
import lombok.Getter;
import lombok.Setter;
import mainFrame.ChoiceCharacter;

@Getter
@Setter
public class ChoiceLevel extends JFrame implements ActionListener {

	ChoiceCharacter choiceCharacter;

	JLabel backgroundImage;

	JButton choiceNormal;
	JButton choiceHell;

	SelectBGM selectBGM;

	int charcterNumber;
	int flagCount;

	public ChoiceLevel() {
		initData();
		setInitLayout();
		addEventListener();
		selectBGM = new SelectBGM();
	}

	private void initData() {
		setSize(1000, 700);
		setTitle("던전 선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundImage = new JLabel(new ImageIcon("Images/choiceLevelBakcground.jpg"));
		setContentPane(backgroundImage);

		ImageIcon choiceNormalBtn = new ImageIcon("Images/choiceLevelNormal.png");
		choiceNormal = new JButton(choiceNormalBtn);

		ImageIcon choiceHellBtn = new ImageIcon("Images/choiceLevelHell.png");
		choiceHell = new JButton(choiceHellBtn);

		ImageIcon nomalHover = new ImageIcon("Images/choiceLevelNormalHover.png");
		ImageIcon hellHover = new ImageIcon("Images/choiceLevelHellHover.png");

		choiceNormal.setRolloverIcon(nomalHover);
		choiceHell.setRolloverIcon(hellHover);

		choiceNormal.setPressedIcon(nomalHover);
		choiceHell.setPressedIcon(hellHover);

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		add(choiceNormal);
		choiceNormal.setSize(262, 310);
		choiceNormal.setLocation(158, 248);
		choiceNormal.setBorderPainted(false);
		choiceNormal.setContentAreaFilled(false);

		add(choiceHell);
		choiceHell.setSize(262, 310);
		choiceHell.setLocation(563, 248);
		choiceHell.setBorderPainted(false);
		choiceHell.setContentAreaFilled(false);
	}

	private void addEventListener() {
		choiceNormal.addActionListener(this);
		choiceHell.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == choiceNormal && charcterNumber == 1) {

			// 마법사 노말 선택
			setVisible(false);
			flagCount = 1;
			new Ground(flagCount, charcterNumber);

			// 전사 노말 선택
		} else if (e.getSource() == choiceNormal && charcterNumber == 2) {

			setVisible(false);
			flagCount = 2;

			new Ground(flagCount, charcterNumber);

			// 마법사 헬 선택
		} else if (e.getSource() == choiceHell && charcterNumber == 1) {

			setVisible(false);

			flagCount = 3;
			new Ground(flagCount, charcterNumber);

			// 전사 헬 선택
		} else if (e.getSource() == choiceHell && charcterNumber == 2) {

			setVisible(false);

			flagCount = 4;
			new Ground(flagCount, charcterNumber);
		}

		selectBGM.clipStop(); // 선택후 음악끄기
	}

}
