package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BGM.GroundBGM;
import BackgroundService.BackgroundService;
import BackgroundService.HellMode.BackgroundServiceHell;
import BackgroundService.NormalMode.BackgroundServiceNormal;
import boss.Boss;
import boss.hellBoss.HellBoss;
import boss.nomalBoss.NormalBoss;
import bottomFire.BottomFire;
import lombok.Getter;
import lombok.Setter;
import meteor.Meteor;
import player.Player;
import player.warrior.Warrior;
import player.wizard.Wizard;

@Getter
@Setter
public class Ground extends JFrame {

	private Ground groundContext = this;

	private Boss boss;
	private Player player;
	private BottomFire bottomFire;
	private BackgroundService backgroundService;

	private List<Meteor> meteorList;

	private JLabel[] characterSkillCounts = new JLabel[5];
	private String[] skillCounts = { " ● ", " ● ", " ● ", " ● ", " ● " };

	private JLabel backgroundHellImage;
	private JLabel backgroundNormalImage;

	private JLabel bossHpBox;
	private JLabel bossHpText;
	private JLabel bossHpBgBox;

	private JLabel modeLevel;

	private JLabel hpTitle;
	private JLabel skillTitle;
	private JPanel characterInfoBox;
	private JLabel characterHp;
	private JLabel characterName;

	private JPanel manualBox;
	private JLabel manualKeyInfo;
	private JLabel manualKeyU;
	private JLabel manualKeyLR;
	private JLabel manualKeyAtaack;

	private boolean attacking;
	private boolean skillIng;
	private boolean flag;

	private int bossHpWidth;
	private int characterHpWidth;
	private int modeCount;
	private int charcterNumber;
	private int skillCount;

	private String name;

	private GroundBGM groundBGM;

	public Ground(int modeCount, int charcterNumber) {
		this.modeCount = modeCount;
		this.charcterNumber = charcterNumber;
		this.groundBGM = new GroundBGM();

		if (modeCount == 1) {

			boss = new NormalBoss(groundContext, 800, 50);
			player = new Wizard(groundContext, "위자드", 300, 116, 92, 116, 92);
			name = "▶ ▷ " + player.getName() + " ◁ ◀";
			modeLevel = new JLabel("◆  N O R M A L  ◆");
		}

		if (modeCount == 2) {
			boss = new NormalBoss(groundContext, 800, 50);
			player = new Warrior(groundContext, "워리어", 500, 116, 92, 135, 92);
			name = "▶ ▷ " + player.getName() + " ◁ ◀";
			modeLevel = new JLabel("◆  N O R M A L  ◆");

		} else if (modeCount == 3) {

			boss = new HellBoss(groundContext, 800, 100);
			player = new Wizard(groundContext, "위자드", 300, 116, 92, 116, 92);
			name = "▶ ▷ " + player.getName() + " ◁ ◀";
			modeLevel = new JLabel("◆ ◇ H E L L ◇ ◆");

		} else if (modeCount == 4) {

			boss = new HellBoss(groundContext, 800, 100);
			player = new Warrior(groundContext, "전사", 300, 116, 92, 135, 92);
			name = "▶ ▷ " + player.getName() + " ◁ ◀";
			modeLevel = new JLabel("◆ ◇ H E L L ◇ ◆");
		}
		initData();
		setInitLayout();
		addEventListener();

	}

	private void initData() {
		setSize(1000, 700);
		setTitle("ManyongCrush");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (modeCount == 1 || modeCount == 2) {

			new Thread(backgroundService = new BackgroundServiceNormal(player)).start();
			meteorList = new ArrayList<Meteor>();
			for (int i = 0; i < 5; i++) {
				meteorList.add(new Meteor(groundContext));
			}
			meteorStart(modeCount);

		} else {
			new Thread(backgroundService = new BackgroundServiceHell(player)).start();
			meteorList = new ArrayList<Meteor>();
			for (int i = 0; i < 7; i++) {
				meteorList.add(new Meteor(groundContext));
			}
			meteorStart(modeCount);
		}

		if (modeCount == 1) {
			// 마법사 / 노말
			backgroundNormalImage = new JLabel(new ImageIcon("images/bossBackgroundMap.jpg"));
			setContentPane(backgroundNormalImage);
			add(player);
			add(boss);

		} else if (modeCount == 2) {
			// 전사 / 노말
			backgroundNormalImage = new JLabel(new ImageIcon("images/bossBackgroundMap.jpg"));
			setContentPane(backgroundNormalImage);
			add(player);
			add(boss);

		} else if (modeCount == 3) {
			// 마법사 / 헬
			backgroundHellImage = new JLabel(new ImageIcon("images/bossBackgroundMapHell.jpg"));
			setContentPane(backgroundHellImage);
			add(player);
			add(boss);

		} else if (modeCount == 4) {
			// 전사 / 헬
			backgroundHellImage = new JLabel(new ImageIcon("images/bossBackgroundMapHell.jpg"));
			setContentPane(backgroundHellImage);
			add(player);
			add(boss);

		}

		bossHpBgBox = new JLabel();
		bossHpBox = new JLabel();

		characterInfoBox = new JPanel();
		manualBox = new JPanel();
		characterHp = new JLabel("");
		characterName = new JLabel(name);
		hpTitle = new JLabel("HP");
		skillTitle = new JLabel("스킬");

		manualKeyInfo = new JLabel("● ◎  조작 방법  ◎ ●");
		manualKeyU = new JLabel("『↑ : 점프 ");
		manualKeyLR = new JLabel("←  → : 좌우 이동  ");
		manualKeyAtaack = new JLabel("     Q : 일반 공격, W : 스킬 』");

		for (int i = 0; i < characterSkillCounts.length; i++) {
			characterSkillCounts[i] = new JLabel(skillCounts[i]);
		}

		bottomFire = new BottomFire(groundContext.player);

	} // end of initData

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		meteorList.forEach((m) -> {
			add(m);
		});

		Color blackOp = new Color(0, 0, 0, 200);
		Color bloodRed = new Color(157, 0, 0);

		add(bossHpBox);
		add(bossHpBgBox);
		add(modeLevel);

		modeLevel.setSize(200, 40);
		modeLevel.setLocation(100, 0);
		modeLevel.setOpaque(true);
		modeLevel.setBackground(blackOp);
		modeLevel.setForeground(bloodRed);
		modeLevel.setFont(new Font("SanSerif", Font.BOLD, 20));
		modeLevel.setHorizontalAlignment(JLabel.CENTER);

		bossHpBgBox.setSize(800, 40);
		bossHpBgBox.setLocation(100, 50);
		bossHpBgBox.setOpaque(true);
		bossHpBgBox.setBackground(Color.LIGHT_GRAY);

		bossHpWidth = boss.getHp();
		bossHpBox.setSize(bossHpWidth, 40);
		bossHpBox.setLocation(100, 50);
		bossHpBox.setOpaque(true);
		bossHpBox.setBackground(bloodRed);

		add(characterHp);
		add(characterName);
		add(hpTitle);
		add(skillTitle);

		hpTitle.setSize(50, 20);
		hpTitle.setLocation(30, 580);
		hpTitle.setForeground(Color.WHITE);
		hpTitle.setFont(new Font("SanSerif", Font.BOLD, 16));

		skillTitle.setSize(50, 20);
		skillTitle.setLocation(30, 605);
		skillTitle.setForeground(Color.WHITE);
		skillTitle.setFont(new Font("SanSerif", Font.BOLD, 12));

		characterName.setSize(145, 20);
		characterName.setLocation(55, 550);
		characterName.setForeground(Color.WHITE);
		characterName.setFont(new Font("SanSerif", Font.BOLD, 18));

		for (int i = 0; i < 5; i++) {
			add(characterSkillCounts[i]);
		}
		for (int i = 0; i < characterSkillCounts.length; i++) {
			characterSkillCounts[i].setSize(200 / 2, 20);
			characterSkillCounts[i].setLocation(60 + (i * 20), 605);
			characterSkillCounts[i].setFont(new Font("SanSerif", Font.BOLD, 18));
			characterSkillCounts[i].setForeground(Color.WHITE);
		}

		characterHpWidth = player.getHp();
		characterHp.setSize(characterHpWidth / 2, 20);
		characterHp.setOpaque(true);
		characterHp.setBackground(bloodRed);
		characterHp.setLocation(60, 580);

		add(characterInfoBox);
		characterInfoBox.setSize(200, 100);
		characterInfoBox.setLocation(20, 540);
		characterInfoBox.setBackground(blackOp);

		add(manualKeyInfo);
		add(manualKeyU);
		add(manualKeyLR);
		add(manualKeyAtaack);
		manualKeyInfo.setSize(180, 50);
		manualKeyInfo.setLocation(750, 540);
		manualKeyInfo.setFont(new Font("SanSerif", Font.BOLD, 18));
		manualKeyInfo.setForeground(Color.WHITE);

		manualKeyU.setSize(180, 20);
		manualKeyU.setLocation(730, 580);
		manualKeyU.setFont(new Font("SanSerif", Font.BOLD, 16));
		manualKeyU.setForeground(Color.WHITE);

		manualKeyLR.setSize(180, 20);
		manualKeyLR.setLocation(805, 580);
		manualKeyLR.setFont(new Font("SanSerif", Font.BOLD, 16));
		manualKeyLR.setForeground(Color.WHITE);

		manualKeyAtaack.setSize(230, 20);
		manualKeyAtaack.setLocation(730, 605);
		manualKeyAtaack.setFont(new Font("SanSerif", Font.BOLD, 16));
		manualKeyAtaack.setForeground(Color.WHITE);

		add(manualBox);
		manualBox.setSize(250, 100);
		manualBox.setLocation(710, 540);
		manualBox.setBackground(blackOp);

		add(bottomFire);
	} // end of setInitLayout

	protected void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public synchronized void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (player.getState() == 0 && !player.isBeAttacked()) {
					if (!player.isCrashWallL() && !player.isLeft() && keyCode == KeyEvent.VK_LEFT) {
						player.left();
					} else if (!player.isCrashWallR() && !player.isRight() && keyCode == KeyEvent.VK_RIGHT) {
						player.right();
					} else if (!player.isDown() && !player.isJump() && keyCode == KeyEvent.VK_UP) {
						player.jump();
					} else if (keyCode == KeyEvent.VK_Q && !player.isBeAttacked()) {
						player.attack();
					} else if (keyCode == KeyEvent.VK_W) {
						player.skill();
					}
				}
			}

			@Override
			public synchronized void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT) {
					player.setLeft(false);
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					player.setRight(false);
				}
			}
		});
	} // end of addEventListenter

	private void meteorStart(int mode) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < meteorList.size(); i++) {
					meteorList.get(i).down(mode);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void unitHpInfo() {
		if (player.isBeAttacked()) {
			characterHpWidth = player.getHp();
			characterHp.setSize(characterHpWidth / 2, 20);
		}
	}

	public void unitSkillCountInfo() {
		skillCount = player.getSkillCount();
		if (skillCount >= 0) {
			remove(characterSkillCounts[skillCount]);
			repaint();
		}
	}

	public void bossInfo() {
		if (boss.isBeAttacked()) {
			bossHpWidth = boss.getHp();
			bossHpBox.setSize(bossHpWidth, 40);
		}
	}

}