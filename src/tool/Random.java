package tool;

public final class Random {
	private final static java.util.Random RAN = new java.util.Random();

	public static java.util.Random get() {
		return RAN;
	}

}
