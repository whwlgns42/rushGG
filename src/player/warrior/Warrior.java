package player.warrior;

import javax.swing.ImageIcon;

import enums.PlayerWay;
import frames.Ground;
import player.Player;
import playerAttackSkill.SkillImpact;
import playerAttackSkill.attack.Slash;
import playerAttackSkill.skill.MegaSlash;

public class Warrior extends Player {

	private Ground groundContext;

	private SkillImpact skillImpact;
	
	private String[] warriorLeftAttackMotion = { "images/warriorAttackMotionL.png",
			"images/warriorWaitingMotionL.png" };
	private String[] warriorRightAttackMotion = { "images/warriorAttackMotionR.png",
			"images/warriorWaitingMotionR.png" };
	private String[] warriorLeftSkillMotion = { "images/warriorSkillMotionL.png", "images/warriorWaitingMotionL.png" };
	private String[] warriorRightSkillMotion = { "images/warriorSkillMotionR.png", "images/warriorWaitingMotionR.png" };

	private String[] warriorLeftBeAttackedMotion = { "images/warriorWaitingMotionL.png",
			"images/warriorLBeAttackedMotion.png", "images/warriorWaitingMotionL.png",
			"images/warriorLBeAttackedMotion.png", "images/warriorWaitingMotionL.png",
			"images/warriorLBeAttackedMotion.png", "images/warriorWaitingMotionL.png" };
	private String[] warriorRightBeAttackedMotion = { "images/warriorWaitingMotionR.png",
			"images/warriorRBeAttackedMotion.png", "images/warriorWaitingMotionR.png",
			"images/warriorRBeAttackedMotion.png", "images/warriorWaitingMotionR.png",
			"images/warriorRBeAttackedMotion.png", "images/warriorWaitingMotionR.png", };

	public Warrior(Ground groundContext, String name, int hp, int x, int y, int playerWidth, int playerHeight) {
		super(groundContext, name, hp, x, y, playerWidth, playerHeight);
		this.groundContext = groundContext;
		initData();
		setInitLayout();
	}

	public void initData() {
		for (int i = 0; i < warriorLeftAttackMotion.length; i++) {
			playerLeftAttackMotionImg[i] = new ImageIcon(warriorLeftAttackMotion[i]);
		}
		for (int i = 0; i < warriorRightAttackMotion.length; i++) {
			playerRightAttackMotionImg[i] = new ImageIcon(warriorRightAttackMotion[i]);
		}
		for (int i = 0; i < warriorLeftSkillMotion.length; i++) {
			playerLeftSkillMotionImg[i] = new ImageIcon(warriorLeftSkillMotion[i]);
		}
		for (int i = 0; i < warriorRightSkillMotion.length; i++) {
			playerRightSkillMotionImg[i] = new ImageIcon(warriorRightSkillMotion[i]);
		}
		for (int i = 0; i < warriorLeftBeAttackedMotion.length; i++) {
			playerLeftBeAttacked[i] = new ImageIcon(warriorLeftBeAttackedMotion[i]);
		}
		for (int i = 0; i < warriorRightBeAttackedMotion.length; i++) {
			playerRightBeAttacked[i] = new ImageIcon(warriorRightBeAttackedMotion[i]);
		}

	}

	@Override
	public void attack() {

		if (!attackCoolTime) {

			new Thread(() -> {

				attackCoolTime = true;

				if (getPWay() == PlayerWay.LEFT) {
					for (int i = 0; i < warriorLeftAttackMotion.length; i++) {
						setIcon(getPlayerLeftAttackMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							System.out.println("워리어 왼쪽 어택");
						}
					}
					skillImpact = new Slash(groundContext, this, getX(), getY(), 30, 50, 150, 150);
					skillImpact.skillsLeftFly();
				} else if (getPWay() == PlayerWay.RIGHT) {
					for (int i = 0; i < warriorRightAttackMotion.length; i++) {
						setIcon(getPlayerRightAttackMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							System.out.println("워리어 오른쪽 어택");
						}
					}
					skillImpact = new Slash(groundContext, this, getX(), getY(), 30, 50, 150, 150);
					skillImpact.skillsRightFly();
				}
				attackCoolTime = false;
			}).start();
		}
	}

	@Override
	public void skill() {

		if (!skillCoolTime && skillCount > 0) {

			skillCoolTime = true;
			skillCount--;

			new Thread(() -> {

				groundContext.unitSkillCountInfo();

				if (pWay == PlayerWay.LEFT) {
					for (int i = 0; i < warriorLeftSkillMotion.length; i++) {
						setIcon(getPlayerLeftSkillMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							System.out.println("워리어 왼쪽 스킬");
						}
					}
					skillImpact = new MegaSlash(groundContext, this, getX(), getY(), 300, 70, 150, 170);
					skillImpact.skillsLeftFly();
				} else {
					for (int i = 0; i < warriorRightSkillMotion.length; i++) {
						setIcon(getPlayerRightSkillMotionImg()[i]);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							System.out.println("워리어 오른쪽 스킬");
						}
					}
					skillImpact = new MegaSlash(groundContext, this, getX(), getY(), 300, 50, 150, 170);
					skillImpact.skillsRightFly();
				}
				skillCoolTime = false;
			}).start();
		}
	}
}