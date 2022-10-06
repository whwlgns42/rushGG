package playerAttackSkill.skill;

import javax.swing.ImageIcon;

import frames.Ground;
import player.Player;
import playerAttackSkill.SkillImpact;

public class MegaSlash extends SkillImpact {

	private String[] skillLeftImage = { "images/warriorSkill1.png", "images/warriorSkill2.png",
			"images/warriorSkill3.png", "images/warriorSkill4.png", "images/warriorSkill5.png",
			"images/warriorSkill6.png", "images/warriorSkill7.png", "images/warriorSkill8.png",
			"images/warriorSkill9.png", "images/warriorSkill10.png", "images/warriorSkill11.png" };
	private String[] skillRightImage = { "images/warriorRightSkill1.png", "images/warriorRightSkill2.png",
			"images/warriorRightSkill3.png", "images/warriorRightSkill4.png", "images/warriorRightSkill5.png",
			"images/warriorRightSkill6.png", "images/warriorRightSkill7.png", "images/warriorRightSkill8.png",
			"images/warriorRightSkill9.png", "images/warriorRightSkill10.png", "images/warriorRightSkill11.png" };

	public MegaSlash(Ground groundContext, Player player, int x, int y, int power, int skillPower, int skillWidth,
			int skillHeight) {
		super(groundContext, player, x, y, power, skillPower, skillWidth, skillHeight);

		initData();
		setInitLayout();
	}

	public void initData() {

		sight = 15;
		skillBeforeFly = 4;
		skillBeforeDisappear = 5;
		skillDisappear = 11;

		for (int i = 0; i < skillLeftImage.length; i++) {
			skillLeftImpact[i] = new ImageIcon(skillLeftImage[i]);
			skillRightImpact[i] = new ImageIcon(skillRightImage[i]);
		}
	}
}