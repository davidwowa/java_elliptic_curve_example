package utils;

import java.math.BigInteger;
import java.util.Scanner;

import model.EllipticCurve;
import model.PointOnEC;

public class IO {

	private static IO instance;

	private Scanner scanner;

	private IO() {
		scanner = new Scanner(System.in);
	}

	public BigInteger readNumber() {
		System.out.println("number : ");
		return scanner.nextBigInteger();
	}

	public EllipticCurve enterEC() {
		System.out.println("enter P : ");
		BigInteger P = readNumber();
		System.out.println("enter A : ");
		BigInteger A = readNumber();
		System.out.println("enter B : ");
		BigInteger B = readNumber();
		System.out.println("enter X3 : ");
		BigInteger X3 = readNumber();
		System.out.println("enter X2 : ");
		BigInteger X2 = readNumber();
		System.out.println("enter X1 : ");
		BigInteger X1 = readNumber();
		System.out.println("enter Y2 : ");
		BigInteger Y2 = readNumber();
		EllipticCurve curve = new EllipticCurve(Y2, A, B, X3, X2, X1, P);
		return curve;
	}

	public PointOnEC enterPoint() {
		System.out.println("enter X : ");
		BigInteger X = readNumber();
		System.out.println("enter Y : ");
		BigInteger Y = readNumber();
		PointOnEC point = new PointOnEC(X, Y);
		return point;
	}

	public static IO getInstance() {
		if (instance == null) {
			instance = new IO();
		}
		return instance;
	}
}