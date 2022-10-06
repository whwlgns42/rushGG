package playerAttackSkill;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import frames.Ground;
import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class SkillImpact extends JLabel {

	protected Ground groundContext;
	protected Player player;
	protected SkillImpact skillImpact;

	protected ImageIcon[] skillLeftImpact = new ImageIcon[16];
	protected ImageIcon[] skillRightImpact = new ImageIcon[16];

	protected int leftX;
	protected int rightX;

	protected int y;

	protected int power;
	protected int skillPower;
	protected int skillCount;

	protected int skillWidth;
	protected int skillHeight;

	protected int state;

	protected int bossX;
	protected int bossY;

	protected int bossWidt;
	protected int bossHeight;

	protected int skillBeforeFly;
	protected int skillBeforeDisappear;
	protected int skillDisappear;

	protected int sight;

	private int changeMotion;

	private boolean checkBoss;

	public SkillImpact(Ground groundContext, Player player, int x, int y, int power, int skillPower, int skillWidth,
			int skillHeight) {
		this.leftX = x - 100;
		this.rightX = x + 100;
		this.y -= 25 - y;
		this.power = power;
		this.skillPower = skillPower;
		this.skillWidth = skillWidth;
		this.skillHeight = skillHeight;
		this.player = player;
		this.groundContext = groundContext;

		bossX = groundContext.getBoss().getX();
		bossY = groundContext.getBoss().getY();

		bossWidt = groundContext.getBoss().getWidth();
		bossHeight = groundContext.getBoss().getHeight();

		groundContext.add(this);

	}

	public void setInitLayout() {
		setSize(skillWidth, skillHeight);
	}

	public void skillsLeftFly() {

		new Thread(() -> {

			for (changeMotion = 0; changeMotion < skillBeforeFly; changeMotion++) {
				try {
					setIcon(skillLeftImpact[changeMotion]);
					setLocation(leftX, y);
					checkBoss();
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < sight; i++) {
				for (changeMotion = skillBeforeDisappear; changeMotion > skillBeforeFly; changeMotion--) {
					try {
						setIcon(skillLeftImpact[changeMotion]);
						leftX--;
						setLocation(leftX, y);
						checkBoss();
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (changeMotion = skillBeforeFly; changeMotion < skillBeforeDisappear; changeMotion++) {
					try {
						setIcon(skillLeftImpact[changeMotion]);
						leftX--;
						setLocation(leftX, y);
						checkBoss();
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			skillsDisappear();
		}).start();
	}

	public void skillsRightFly() {

		new Thread(() -> {

			for (changeMotion = 0; changeMotion < skillBeforeFly; changeMotion++) {
				try {
					setIcon(skillRightImpact[changeMotion]);
					setLocation(rightX, y);
					checkBoss();
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < sight && !checkBoss; i++) {
				for (changeMotion = skillBeforeDisappear; changeMotion > skillBeforeFly; changeMotion--) {
					try {
						setIcon(skillRightImpact[changeMotion]);
						rightX++;
						setLocation(rightX, y);
						checkBoss();
						Thread.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (changeMotion = skillBeforeFly; changeMotion < skillBeforeDisappear; changeMotion++) {
					try {
						setIcon(skillRightImpact[changeMotion]);
						rightX++;
						setLocation(rightX, y);
						checkBoss();
						Thread.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			skillsDisappear();
		}).start();
	}

	public void checkBoss() {

		new Thread(() -> {

			if (Math.abs((rightX + (skillWidth / 2)) - bossX) < 1 || Math.abs((rightX + skillWidth) - bossX) < 1
					|| Math.abs(rightX - bossX) < 1
							&& Math.abs(((y + skillHeight) / 2) - (bossY + bossHeight) / 2) < 280) {
				if (Math.abs((leftX + (skillWidth / 2)) - bossX) < 1 || Math.abs((leftX + skillWidth) - bossX) < 1
						|| Math.abs(leftX - bossX) < 1
								&& Math.abs(((y + skillHeight) / 2) - (bossY + bossHeight) / 2) < 280) {
				}
				checkBoss = true;
				groundContext.getBoss().beAttacked(power);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				checkBoss = false;
			}
		}).start();
	}

	public void skillsDisappear() {

		for (changeMotion = skillBeforeDisappear; changeMotion < skillDisappear; changeMotion++) {
			try {
				setIcon(skillRightImpact[changeMotion]);
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setLocation(2000, 0);
	}
}