package interfaces;

public interface Moveable {

	// 오른쪽
	public abstract void right();

	// 왼쪽
	public abstract void left();

	// 아래
	public abstract void down();

	// 점프
	public abstract void jump();

	// 죽음
	public abstract void die();

	// 피격
	public abstract void beAttacked(int damage);

}
