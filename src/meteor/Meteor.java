package meteor;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import frames.ChoiceLevel;
import frames.Ground;
import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class Meteor extends JLabel {

	private Random random = new Random();

	private ChoiceLevel choiceLevel;
	private Player player;

	private ImageIcon lavaMeteorImage;
	private ImageIcon lavaBoomMeteorImage;

	private final int POWER = 20;
	private final int LAVA_METEOR_WIDTH = 57;
	private final int LAVA_METEOR_HEIGHT = 123;

	private int x;
	private int y;
	private int lavaMeteors;

	private boolean lavaMeteorFalling;// true : 떨어짐
	private boolean hited; // 플레이어가 맞았을때

	public Meteor(Ground groundContext) {
		this.player = groundContext.getPlayer();

		initData();
		setInitLayout();
	}

	private void initData() {
		lavaMeteorImage = new ImageIcon("images/meteor.png");
		lavaBoomMeteorImage = new ImageIcon("images/boomMeteor2.png");
		lavaMeteors = random.nextInt(700) + 200; // x좌표값 랜덤설정 x좌표 200부터시작
	}

	private void setInitLayout() {
		x = lavaMeteors; // x값 랜덤으로 받아옴
		y = -500; // 프레임 밖에서 떨어짐
		setLocation(x, y);
		setIcon(lavaMeteorImage);
		setSize(LAVA_METEOR_WIDTH, LAVA_METEOR_HEIGHT);
	}

	public void down(int mode) {

		new Thread(() -> {
			int downSpeed = 2;
			while (true) {
				if (!lavaMeteorFalling || x >= -300) {
					setIcon(lavaMeteorImage);
					if (mode == 1 || mode == 2) {
						downSpeed = 2;
					} else {
						downSpeed = 4;
					}
					y += downSpeed;
					setLocation(x, y);
					attack();

				}
				try {
					Thread.sleep(random.nextInt(8) + 5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (y >= 800) {
					x = random.nextInt(700) + 30;
					y = -200;
					setLocation(x, y);
					setIcon(lavaMeteorImage);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public void attack() {

		if (!player.isBeAttacked()) { // 안맞았을때
			if ((Math.abs(x - player.getX()) - 20 < 70 // 플레이어 좌표 감지
					&& Math.abs(y - player.getY() + 60) < 40)) {
				player.setBeAttacked(true);
				try {
					setIcon(lavaBoomMeteorImage);
					setLocation(x - 50, y);
					setSize(148, 125);
					player.beAttacked(POWER);
					player.setJump(false);
					player.setLeft(false);
					player.setRight(false);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				y = -200;
				setLocation(x, y);
			}
			player.setBeAttacked(false);
		}
	}
}
