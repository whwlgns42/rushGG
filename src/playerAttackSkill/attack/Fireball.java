package playerAttackSkill.attack;

import javax.swing.ImageIcon;

import frames.Ground;
import player.Player;
import playerAttackSkill.SkillImpact;

public class Fireball extends SkillImpact {

	private String[] skillImage = { "images/wizardAttack1.png", "images/wizardAttack2.png", "images/wizardAttack3.png",
			"images/wizardAttack4.png", "images/wizardAttack5.png", "images/wizardAttack6.png",
			"images/wizardAttack7.png", "images/wizardAttack8.png", "images/wizardAttack9.png",
			"images/wizardAttack10.png", "images/wizardAttack11.png", "images/wizardAttack12.png" };

	public Fireball(Ground groundContext, Player player, int x, int y, int power, int skillPower, int skillWidth,
			int skillHeight) {
		super(groundContext, player, x, y, power, skillPower, skillWidth, skillHeight);

		initData();
		setInitLayout();
	}

	public void initData() {

		sight = 10;

		skillBeforeDisappear = 10;
		skillDisappear = 10;

		for (int i = 0; i < skillImage.length; i++) {
			skillRightImpact[i] = new ImageIcon(skillImage[i]);
			skillLeftImpact[i] = new ImageIcon(skillImage[i]);
		}
	}
}