package boss;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import frames.GameState;
import frames.Ground;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Boss extends JLabel {

	Ground groundContext;

	protected ImageIcon[] boss = new ImageIcon[4];

	protected ImageIcon[] bossAttack = new ImageIcon[19];

	protected ImageIcon[] bossDie = new ImageIcon[10];

	private final int BOSS_WIDTH = 293;
	private final int BOSS_HEIGHT = 590;

	private final int X = 650;
	private final int Y = 40;

	private int hp = 600;
	private int power;

	private int state;

	private int count;

	private int damage;

	private boolean waiting;
	private boolean attacking;
	private boolean beAttacked;

	public Boss(Ground groundContext, int hp, int power) {
		this.groundContext = groundContext;
		this.hp = hp;
		this.power = power;

		setInitLayout();
	}

	public void setInitLayout() {
		setIcon(boss[0]);
		setSize(BOSS_WIDTH, BOSS_HEIGHT);
		setLocation(X, Y);

	}

	public void waiting() {
		new Thread(() -> {
			waiting = true;
			while (waiting && state == 0) {

				for (int i = 0; i < boss.length; i++) {
					try {
						setIcon(boss[i]);
						Thread.sleep(130);
					} catch (InterruptedException e) {
						System.err.println("보스에서 웨이팅에서 에러남");
					}
				}
				count++;
				if (count % 10 == 0) {
					waiting = false;
					attack();
				}
			}
		}).start();
	}

	public void attack() {
		new Thread(() -> {

			if (!waiting && state == 0) {

				for (int i = 0; i < bossAttack.length; i++) {
					try {
						setIcon(bossAttack[i]);
						Thread.sleep(150);
					} catch (InterruptedException e) {
						System.err.println("보스 어택에서 에러");
					}
					if (bossAttack[i] == bossAttack[13]) {
						attacking = true;
					}
				}
				attacking = false;
				waiting = true;
				waiting();
			}
		}).start();
	}

	public void die() {
		state = 1;
		for (int i = 0; i < bossDie.length; i++) {
			setIcon(bossDie[i]);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new GameState(state);
		groundContext.setVisible(false);
	}

	public void beAttacked(int damage) {

		new Thread(() -> {
			if (state == 0) {
				hp -= damage;
				beAttacked = true;
				groundContext.bossInfo();
				if (hp <= 0) {
					hp = 0;
					die();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			beAttacked = false;

		}).start();
	}
}
