package playerAttackSkill.skill;

import javax.swing.ImageIcon;

import frames.Ground;
import player.Player;
import playerAttackSkill.SkillImpact;

public class ChainFireball extends SkillImpact {

	private String[] skillRightImage = { "images/wizardSkill1.png", "images/wizardSkill2.png",
			"images/wizardSkill3.png", "images/wizardSkill4.png", "images/wizardSkill5.png", "images/wizardSkill6.png",
			"images/wizardSkill7.png", "images/wizardSkill8.png", "images/wizardSkill9.png", "images/wizardSkill10.png",
			"images/wizardSkill11.png", "images/wizardSkill12.png", "images/wizardSkill13.png",
			"images/wizardSkill14.png", "images/wizardSkill15.png", "images/wizardSkill16.png" };
	private String[] skillLeftImage = { "images/wizardSkillL1.png", "images/wizardSkillL2.png",
			"images/wizardSkillL3.png", "images/wizardSkillL4.png", "images/wizardSkillL5.png",
			"images/wizardSkillL6.png", "images/wizardSkillL7.png", "images/wizardSkillL8.png",
			"images/wizardSkillL9.png", "images/wizardSkillL10.png", "images/wizardSkillL11.png",
			"images/wizardSkillL12.png", "images/wizardSkillL13.png", "images/wizardSkillL14.png",
			"images/wizardSkillL15.png", "images/wizardSkillL16.png" };

	public ChainFireball(Ground groundContext, Player player, int x, int y, int power, int skillPower, int skillWidth,
			int skillHeight) {
		super(groundContext, player, x, y, power, skillPower, skillWidth, skillHeight);

		initData();
		setInitLayout();
	}

	public void initData() {

		sight = 30;
		skillBeforeFly = 6;
		skillBeforeDisappear = 10;
		skillDisappear = 16;

		for (int i = 0; i < skillLeftImage.length; i++) {
			skillRightImpact[i] = new ImageIcon(skillRightImage[i]);
			skillLeftImpact[i] = new ImageIcon(skillLeftImage[i]);
		}
	}
}