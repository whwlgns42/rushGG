package playerAttackSkill.attack;

import javax.swing.ImageIcon;

import frames.Ground;
import player.Player;
import playerAttackSkill.SkillImpact;

public class Slash extends SkillImpact {

	private String[] skillLeftImage = { "images/warriorAttack1.png", "images/warriorAttack2.png",
			"images/warriorAttack3.png", "images/warriorAttack4.png", "images/warriorAttack5.png",
			"images/warriorAttack6.png", "images/warriorAttack7.png" };
	private String[] skillRightImage = { "images/warriorRightAttack1.png", "images/warriorRightAttack2.png",
			"images/warriorRightAttack3.png", "images/warriorRightAttack4.png", "images/warriorRightAttack5.png",
			"images/warriorRightAttack6.png", "images/warriorRightAttack7.png" };

	public Slash(Ground groundContext, Player player, int x, int y, int power, int skillPower, int skillWidth,
			int skillHeight) {
		super(groundContext, player, x, y, power, skillPower, skillWidth, skillHeight);

		initData();
		setInitLayout();
	}

	public void initData() {

		sight = 15;

		skillBeforeFly = 3;
		skillBeforeDisappear = 5;
		skillDisappear = 7;

		for (int i = 0; i < skillLeftImage.length; i++) {
			skillLeftImpact[i] = (new ImageIcon(skillLeftImage[i]));
			skillRightImpact[i] = (new ImageIcon(skillRightImage[i]));
		}

	}

}