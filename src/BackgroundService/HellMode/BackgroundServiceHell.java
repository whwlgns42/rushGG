package BackgroundService.HellMode;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import BackgroundService.BackgroundService;
import player.Player;

public class BackgroundServiceHell extends BackgroundService {

	private Player player;

	public BackgroundServiceHell(Player player) {
		super(player);

		try {
			playerService = ImageIO.read(new File("images/bossBackgroundMapServiceHell.jpg"));
		} catch (IOException e) {
			System.out.println("헬 이미지 경로 에러 ");
		}
	}
}
