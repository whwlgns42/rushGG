package boss.hellBoss;

import javax.swing.ImageIcon;

import boss.Boss;
import frames.Ground;

public class HellBoss extends Boss {

	private String[] bossImg = { "images/boss1Hell.png", "images/boss3Hell.png", "images/boss2Hell.png",
			"images/boss3Hell.png" };
	private String[] bossAttackImg = { "images/bossMotion1Hell.png", "images/bossMotion2Hell.png",
			"images/bossMotion3Hell.png", "images/bossMotion4Hell.png", "images/bossMotion5Hell.png",
			"images/bossMotion6Hell.png", "images/bossMotion7Hell.png", "images/bossMotion8Hell.png",
			"images/bossMotion9Hell.png", "images/bossMotion10Hell.png", "images/bossMotion11Hell.png",
			"images/bossMotion12Hell.png", "images/bossMotion13Hell.png", "images/bossMotion14Hell.png",
			"images/bossMotion15Hell.png", "images/bossMotion16Hell.png", "images/bossMotion17Hell.png",
			"images/bossMotion18Hell.png", "images/bossMotion19Hell.png" };
	private String[] bossDieImg = { "images/bossDie1.png", "images/bossDie2.png", "images/bossDie3.png",
			"images/bossDie4.png", "images/bossDie5.png", "images/bossDie6.png", "images/bossDie7.png",
			"images/bossDie8.png", "images/bossDie9.png", "images/bossDie10.png" };

	public HellBoss(Ground groundContext, int hp, int power) {
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
