package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import mainFrame.ChoiceCharacter;

public class GameState extends JFrame implements ActionListener {

	JLabel backgroundOverImage;
	JLabel backgroundClearImage;

	JButton yesBtn;
	JButton noBtn;

	int stateCount;

	public GameState(int stateCount) {
		this.stateCount = stateCount;
		initData();
		setInitLayout();
		addEventListenter();
	}

	private void initData() {
		setSize(1000, 700);
		setTitle("마뇽크러쉬 엔딩");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (stateCount == 1) {
			backgroundClearImage = new JLabel(new ImageIcon("images/gameClearBackground.jpg"));
			setContentPane(backgroundClearImage);

		} else if (stateCount == 0) {
			backgroundOverImage = new JLabel(new ImageIcon("images/gameOverBackground.jpg"));
			setContentPane(backgroundOverImage);
		}

		ImageIcon yesBtnImage = new ImageIcon("images/gameOverReStartYesBtn.png");
		yesBtn = new JButton(yesBtnImage);

		ImageIcon noBtnImage = new ImageIcon("images/gameOverReStartNoBtn.png");
		noBtn = new JButton(noBtnImage);

		ImageIcon yesBtnImageHover = new ImageIcon("images/gameOverReStartYesHoverBtn.png");
		ImageIcon noBtnImageHover = new ImageIcon("images/gameOverReStartNoHoverBtn.png");

		yesBtn.setRolloverIcon(yesBtnImageHover);
		noBtn.setRolloverIcon(noBtnImageHover);

		yesBtn.setPressedIcon(yesBtnImageHover);
		noBtn.setPressedIcon(noBtnImageHover);

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		add(yesBtn);
		yesBtn.setSize(188, 77);
		yesBtn.setLocation(240, 500);
		yesBtn.setBorderPainted(false);
		yesBtn.setContentAreaFilled(false);

		add(noBtn);
		noBtn.setSize(188, 77);
		noBtn.setLocation(570, 500);
		noBtn.setBorderPainted(false);
		noBtn.setContentAreaFilled(false);

	}

	private void addEventListenter() {
		yesBtn.addActionListener(this);
		noBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == yesBtn) {
			setVisible(false);
			new ChoiceCharacter();
		} else if (e.getSource() == noBtn) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new GameState(1); // 테스트 코드
	}

}
