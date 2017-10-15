package model;

import java.math.BigInteger;

public class PointOnEC {

	private BigInteger x;
	private BigInteger y;

	public PointOnEC(BigInteger x, BigInteger y) {
		this.x = x;
		this.y = y;
	}

	public BigInteger getX() {
		return x;
	}

	public void setX(BigInteger x) {
		this.x = x;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "PointOnEC [x=" + x + ", y=" + y + "]";
	}
}