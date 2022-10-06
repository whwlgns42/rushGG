package boss.nomalBoss;

import javax.swing.ImageIcon;

import boss.Boss;
import frames.Ground;

public class NormalBoss extends Boss {

	private String[] bossImg = { "images/boss1.png", "images/boss2.png", "images/boss3.png", "images/boss2.png" };
	private String[] bossAttackImg = { "images/bossMotion1.png", "images/bossMotion2.png", "images/bossMotion3.png",
			"images/bossMotion4.png", "images/bossMotion5.png", "images/bossMotion6.png", "images/bossMotion7.png",
			"images/bossMotion8.png", "images/bossMotion9.png", "images/bossMotion10.png", "images/bossMotion11.png",
			"images/bossMotion12.png", "images/bossMotion13.png", "images/bossMotion14.png", "images/bossMotion15.png",
			"images/bossMotion16.png", "images/bossMotion17.png", "images/bossMotion18.png",
			"images/bossMotion19.png" };
	private String[] bossDieImg = { "images/bossDie1.png", "images/bossDie2.png", "images/bossDie3.png",
			"images/bossDie4.png", "images/bossDie5.png", "images/bossDie6.png", "images/bossDie7.png",
			"images/bossDie8.png", "images/bossDie9.png", "images/bossDie10.png" };

	public NormalBoss(Ground groundContext, int hp, int power) {
		super(groundContext, hp, power);

		initData();
		setInitLayout();
		waiting();

	}

	public void initData() {
		for (int i = 0; i < bossImg.length; i++) {
			boss[i] = new ImageIcon(bossImg[i]);
		}

		for (int i = 0; i < bossAttackImg.length; i++) {
			bossAttack[i] = new ImageIcon(bossAttackImg[i]);
		}

		for (int i = 0; i < bossDieImg.length; i++) {
			bossDie[i] = new ImageIcon(bossDieImg[i]);
		}
	}
}
