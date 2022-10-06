package player;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bottomFire.BottomFire;
import enums.PlayerWay;
import frames.GameState;
import frames.Ground;
import interfaces.Attack;
import interfaces.Moveable;
import lombok.Getter;
import lombok.Setter;
import meteor.Meteor;
import playerAttackSkill.SkillImpact;

@Getter
@Setter
public class Player extends JLabel implements Attack, Moveable {

	private Player player = this;
	protected SkillImpact skillImpact;
	protected Ground groundContext;
	protected BottomFire bottomFire;

	protected ImageIcon[] playerLeftAttackMotionImg = new ImageIcon[2];
	protected ImageIcon[] playerRightAttackMotionImg = new ImageIcon[2];

	protected ImageIcon[] playerLeftSkillMotionImg = new ImageIcon[2];
	protected ImageIcon[] playerRightSkillMotionImg = new ImageIcon[2];

	protected ImageIcon[] playerLeftBeAttacked = new ImageIcon[7];
	protected ImageIcon[] playerRightBeAttacked = new ImageIcon[7];

	protected ImageIcon playerDieMotionImg;

	protected PlayerWay pWay;

	protected final int SPEED = 4;
	protected final int JUMPSPEED = 2;
	protected final int DOWNSPEED = 4;

	private final int BOSSCRASHDAMAGE = 10;
	private final int BOTTOMFIREDAMAGE = 10;

	protected int hp;
	protected int power;
	protected int x;
	protected int y;
	protected int playerWidth;
	protected int playerHeight;
	protected int state = 0;
	protected int skillCount;

	private int bossX;
	private int bossY;

	private int bossWidth;
	private int bossHeight;

	private int bressX;
	private int bressY;

	private int bressWidth;
	private int bressHeight;

	private int bressDamage;

	protected boolean left;
	protected boolean right;
	protected boolean jump;
	protected boolean down;

	protected boolean crashWallL;
	protected boolean crashWallR;
	protected boolean crashBoss;

	protected boolean beAttacked;

	protected boolean attackCoolTime;
	protected boolean skillCoolTime;

	protected String name;

	public Player(Ground groundContext, String name, int hp, int x, int y, int playerWidth, int playerHeight) {
		this.name = name;
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.playerWidth = playerWidth;
		this.playerHeight = playerHeight;
		this.groundContext = groundContext;
		this.bottomFire = new BottomFire(this);

		skillCount = 5;
		down = false;
	}

	protected void setInitLayout() {
		playerDieMotionImg = new ImageIcon("images/characterDie.png");
		setIcon(playerLeftAttackMotionImg[1]);
		setSize(playerWidth, playerHeight);
		setLocation(x, y);

		bossX = groundContext.getBoss().getX();
		bossY = groundContext.getBoss().getY();

		bossWidth = groundContext.getBoss().getWidth();
		bossHeight = groundContext.getBoss().getHeight();

	}

	@Override
	public void right() {
		pWay = (PlayerWay.RIGHT); // 오른쪽으로 보고있을때
		right = true;
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (right) {
					setIcon(playerRightAttackMotionImg[1]);
					x += SPEED;
					setLocation(x, y);
					crashBoss();
					bottomFireCheck();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void left() {
		pWay = PlayerWay.LEFT;

		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					setIcon(playerLeftAttackMotionImg[1]);
					x -= SPEED;
					setLocation(x, y);
					bottomFireCheck();
					crashBoss();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void down() {
		down = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					y += DOWNSPEED;
					setLocation(x, y);
					crashBoss();
					bottomFireCheck();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				down = false;
			}
		}).start();
	}

	@Override
	public void jump() {
		jump = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 130 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					crashBoss();
					bottomFireCheck();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				jump = false;
				down();
			}
		}).start();

	}

	@Override
	public void die() {
		new Thread(() -> {
			state = 1;
			setIcon(playerDieMotionImg);
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new GameState(state - 1);
			groundContext.setVisible(false);
		}).start();

	}

	@Override
	public void beAttacked(int damage) {

		new Thread(() -> {

			if (state == 0 && beAttacked) {
				groundContext.unitHpInfo();
				if (hp <= 0) {
					hp = 0;
					die();
				}
				hp -= damage;
				for (int i = 0; i < playerLeftBeAttacked.length; i++) {
					try {
						if (pWay == PlayerWay.LEFT) {
							setIcon(playerLeftBeAttacked[i]);
							x += 2;
							y -= 2;
							setLocation(x, y);
						} else {
							setIcon(playerRightBeAttacked[i]);
							x -= 2;
							y -= 2;
							setLocation(x, y);
						}
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			beAttacked = false;
		}).start();
	}

	public void crashBoss() {

		if ((Math.abs((x + (WIDTH / 2)) - (bossX + (bossWidth / 2))) < 200
				&& Math.abs((y + (HEIGHT / 2)) - ((bossY + bossHeight) / 2)) < 250)) {
			beAttacked = true;
			try {
				beAttacked(BOSSCRASHDAMAGE);
				Thread.sleep(500);
				System.out.println("먹나요");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		beAttacked = false;
	}

	public void bottomFireCheck() {

		if (x + WIDTH > bottomFire.getX() && y + (HEIGHT / 2) > bottomFire.getY()) {
			beAttacked = true;
			try {
				beAttacked(BOTTOMFIREDAMAGE);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		beAttacked = false;
	}
}