package Game;
import java.util.Random;
public class Bonus {
	Random r = new Random();
	
	public int typeBonus() {
		int x=r.nextInt(3);	//0�� ü��ȸ��, 1�� �ڿ� ȹ�� , 2�� �ɷ�ġ ����
		return x;
	}
	
	public int heal() {
		int x=r.nextInt(200);
		return x;
	}
	
	public int mineral() {
		int x=r.nextInt(2);
		return x;
	}
	
	public float ability() {
		float x=r.nextFloat();
		return x;
	}
	
	
}
