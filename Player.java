import java.util.*;
public class Player {
	Hand hand;
	int value;
	double money;
	
	public Player() {
		this.hand = new Hand();
		this.value = 0;
		this.money = 0;
	}
	
	public Player(double money) {
		this.hand = new Hand();
		this.value = 0;
		this.money = money;
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void addValue(int x) {
		this.value+=x;
	}
	
}
