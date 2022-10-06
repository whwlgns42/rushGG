package BackgroundService;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class BackgroundService implements Runnable {

	private Player player;

	protected BufferedImage playerService;

	public BackgroundService(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		while (true) {
			Color playerBottomLeftColor = new Color(playerService.getRGB(player.getX() + 30, player.getY() + 92));
			Color playerBottomRightColor = new Color(playerService.getRGB(player.getX() + 116, player.getY() + 92));

			int playerBottomLeft = playerService.getRGB(player.getX() + 50, player.getY() + 92);
			int playerBottomRIght = playerService.getRGB(player.getX() + 76, player.getY() + 92);

			// 마법사
			if (playerBottomLeft + playerBottomRIght != -2) {
				player.setDown(false);
			} else {
				if (!player.isJump() && !player.isDown() && !player.isBeAttacked()) {
					player.down();
				}
			}

			if (playerBottomLeftColor.getRed() == 254 && playerBottomLeftColor.getGreen() == 0
					&& playerBottomLeftColor.getBlue() == 0) {
				player.setCrashWallL(true);
				player.setLeft(false);
				if (playerBottomLeft + playerBottomRIght != -2) {
					player.setDown(false);
				}
			} else if (playerBottomRightColor.getRed() == 254 && playerBottomRightColor.getGreen() == 0
					&& playerBottomRightColor.getBlue() == 0) {
				player.setCrashWallR(true);
				player.setRight(false);
				if (playerBottomLeft + playerBottomRIght != -2) {
					player.setDown(false);
				}
			} else {
				player.setCrashWallL(false);
				player.setCrashWallR(false);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
