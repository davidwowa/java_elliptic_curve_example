package model;

import java.math.BigInteger;

public class EllipticCurve {

	private BigInteger y2;
	private BigInteger a;
	private BigInteger b;
	private BigInteger x3;
	private BigInteger x2;
	private BigInteger x1;
	private BigInteger p;

	public EllipticCurve(BigInteger y, BigInteger a, BigInteger b,
			BigInteger x3, BigInteger x2, BigInteger x1, BigInteger p) {
		super();
		this.y2 = y;
		this.a = a;
		this.b = b;
		this.x3 = x3;
		this.x2 = x2;
		this.x1 = x1;
		this.p = p;
	}

	public BigInteger getY() {
		return y2;
	}

	public void setY(BigInteger y) {
		this.y2 = y;
	}

	public BigInteger getA() {
		return a;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public BigInteger getB() {
		return b;
	}

	public void setB(BigInteger b) {
		this.b = b;
	}

	public BigInteger getX3() {
		return x3;
	}

	public void setX3(BigInteger x3) {
		this.x3 = x3;
	}

	public BigInteger getX2() {
		return x2;
	}

	public void setX2(BigInteger x2) {
		this.x2 = x2;
	}

	public BigInteger getX1() {
		return x1;
	}

	public void setX1(BigInteger x1) {
		this.x1 = x1;
	}

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "EllipticCurve [y2=" + y2 + ", a=" + a + ", b=" + b + ", x3="
				+ x3 + ", x2=" + x2 + ", x1=" + x1 + ", p=" + p + "]";
	}

}
