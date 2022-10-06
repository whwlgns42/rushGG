package player.wizard;

import javax.swing.ImageIcon;

import enums.PlayerWay;
import frames.Ground;
import player.Player;
import playerAttackSkill.attack.Fireball;
import playerAttackSkill.skill.ChainFireball;

public class Wizard extends Player {

	Ground groundContext;

	private String[] wizardLeftAttackMotion = { "images/wizardAttackMotionL.png", "images/wizardWaitingMotionL.png" };
	private String[] wizardRightAttackMotion = { "images/wizardAttackMotionR.png", "images/wizardWaitingMotionR.png" };
	private String[] wizardLeftSkillMotion = { "images/wizardSkillMotionL.png", "images/wizardWaitingMotionL.png" };
	private String[] wizardRightSkillMotion = { "images/wizardSkillMotionR.png", "images/wizardWaitingMotionR.png" };

	private String[] wizardLeftBeAttackedMotion = { "images/wizardWaitingMotionL.png",
			"images/wizardLBeAttackedMotionL.png", "images/wizardWaitingMotionL.png",
			"images/wizardLBeAttackedMotionL.png", "images/wizardWaitingMotionL.png",
			"images/wizardrLBeAttackedMotionL.png", "images/wizardWaitingMotionL.png" };
	private String[] wizardRightBeAttackedMotion = { "images/wizardWaitingMotionR.png",
			"images/wizardRBeAttackedMotionR.png", "images/wizardWaitingMotionR.png",
			"images/wizardRBeAttackedMotionR.png", "images/wizardWaitingMotionR.png",
			"images/wizardRBeAttackedMotionR.png", "images/wizardWaitingMotionR.png", };

	public Wizard(Ground groundContext, String name, int hp, int x, int y, int playerWidth, int playerHeight) {
		super(groundContext, name, hp, x, y, playerWidth, playerHeight);
		this.groundContext = groundContext;

		initData();
		setInitLayout();

	}

	public void initData() {
		for (int i = 0; i < wizardLeftAttackMotion.length; i++) {
			playerLeftAttackMotionImg[i] = new ImageIcon(wizardLeftAttackMotion[i]);
		}
		for (int i = 0; i < wizardRightAttackMotion.length; i++) {
			playerRightAttackMotionImg[i] = new ImageIcon(wizardRightAttackMotion[i]);
		}
		for (int i = 0; i < wizardLeftSkillMotion.length; i++) {
			playerLeftSkillMotionImg[i] = new ImageIcon(wizardLeftSkillMotion[i]);
		}
		for (int i = 0; i < wizardRightSkillMotion.length; i++) {
			playerRightSkillMotionImg[i] = new ImageIcon(wizardRightSkillMotion[i]);
		}
		for (int i = 0; i < playerLeftBeAttacked.length; i++) {
			playerLeftBeAttacked[i] = new ImageIcon(wizardLeftBeAttackedMotion[i]);
		}
		for (int i = 0; i < playerRightBeAttacked.length; i++) {
			playerRightBeAttacked[i] = new ImageIcon(wizardRightBeAttackedMotion[i]);
		}
	}

	@Override
	public void attack() {

		if (!attackCoolTime) {

			new Thread(() -> {
				try {
					attackCoolTime = true;
					if (getPWay() == PlayerWay.LEFT) {
						for (int i = 0; i < wizardLeftAttackMotion.length; i++) {
							setIcon(getPlayerLeftAttackMotionImg()[i]);
							try {
								Thread.sleep(200);
							} catch (Exception e) {
							}
						}
						skillImpact = new Fireball(groundContext, this, x, y, 30, 50, 100, 100);
						skillImpact.skillsLeftFly();
					} else if (getPWay() == PlayerWay.RIGHT) {
						for (int i = 0; i < wizardRightAttackMotion.length; i++) {
							setIcon(getPlayerRightAttackMotionImg()[i]);
							try {
								Thread.sleep(200);
							} catch (Exception e) {
							}
						}
						skillImpact = new Fireball(groundContext, this, x, y, 30, 50, 100, 100); // 스킬 다른걸로 바꿔야함
						skillImpact.skillsRightFly();
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				attackCoolTime = false;
			}).start();

		}
	}

	@Override
	public void skill() {

		if (!skillCoolTime && skillCount > 0) {

			new Thread(() -> {

				skillCoolTime = true;
				skillCount--;

				groundContext.unitSkillCountInfo();

				if (getPWay() == PlayerWay.LEFT) {
					for (int i = 0; i < wizardLeftSkillMotion.length; i++) {
						setIcon(getPlayerLeftSkillMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
						}
					}
					skillImpact = new ChainFireball(groundContext, this, x, y, 50, 100, 200, 170);
					skillImpact.skillsLeftFly();
				} else {
					for (int i = 0; i < wizardRightSkillMotion.length; i++) {
						setIcon(getPlayerRightSkillMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
						}
					}
					skillImpact = new ChainFireball(groundContext, this, x, y, 30, 50, 200, 170);
					skillImpact.skillsRightFly();
				}
				skillCoolTime = false;
			}).start();
		}
	}
}