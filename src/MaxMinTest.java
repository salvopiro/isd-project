public class MaxMinTest {
	private int max;
	private int min;

	public MaxMinTest() {
		this.max = Integer.MIN_VALUE;
		this.min = Integer.MAX_VALUE;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "Max = " + max + "Min = " + min;
	}
}
