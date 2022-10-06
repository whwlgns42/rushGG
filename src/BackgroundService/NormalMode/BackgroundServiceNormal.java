package BackgroundService.NormalMode;

import java.io.File;

import javax.imageio.ImageIO;

import BackgroundService.BackgroundService;
import player.Player;

public class BackgroundServiceNormal extends BackgroundService {

	private Player player;

	public BackgroundServiceNormal(Player player) {
		super(player);
		this.player = player;
		try {
			playerService = ImageIO.read(new File("images/bossBackgroundMapService.jpg"));
		} catch (Exception e) {
			System.err.println("이미지 경로 에러 ");
		}

	}
}
