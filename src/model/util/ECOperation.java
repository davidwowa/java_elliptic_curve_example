package model.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.EllipticCurve;
import model.PointOnEC;
import utils.ConvertUtils;

public class ECOperation {

	private static ECOperation instance;
	public static int privateKey = 0;

	private ECOperation() {
	}

	public static ECOperation getInstance() {
		if (instance == null) {
			instance = new ECOperation();
		}
		return instance;
	}

	private boolean isEllipticCurve(EllipticCurve curve) {
		// 4a^3 + 27b^2 != 0
		BigInteger _0 = BigInteger.ZERO;
		BigInteger _4 = new BigInteger("4");
		BigInteger _27 = new BigInteger("27");
		BigInteger a3 = curve.getA().pow(3);
		BigInteger b2 = curve.getB().pow(2);
		BigInteger _4a3 = _4.multiply(a3);
		BigInteger _27b2 = _27.multiply(b2);
		BigInteger _4a3__27b2 = _4a3.add(_27b2);

		int compare = _0.compareTo(_4a3__27b2);

		if (compare != 0) {
			return true;
		}
		return false;
	}

	public void findCyclicGroupOf(EllipticCurve curve){
		List<PointOnEC> pointOnEC = new ArrayList<PointOnEC>();
		List<PointOnEC> pointNotOnEC = new ArrayList<PointOnEC>();
		for (int i = 0; i < curve.getP().intValue(); i++) {
			for (int j = 0; j < curve.getP().intValue(); j++) {
				PointOnEC point = new PointOnEC(new BigInteger(i+""), new BigInteger(j+""));
				if (isPointOnEC(curve, point)) {
					pointOnEC.add(point);
				}else{
					pointNotOnEC.add(point);
				}
			}
		}
		if (!pointOnEC.isEmpty()) {
			System.out.println("points on ec");
			for (PointOnEC point : pointOnEC) {
				System.out.println(point.toString());
			}
		}else {
			System.out.println("no points on ec");
		}
		
		if (!pointNotOnEC.isEmpty()) {
			System.out.println("points not on ec");
			for (PointOnEC point : pointNotOnEC) {
				System.out.println(point.toString());
			}
		}else {
			System.out.println("all points on ec");
		}
	}
	
	public PointOnEC doubleAndAddAlgorithmChoosed(EllipticCurve curve,
			PointOnEC P, int number) {
		int d = number;
		System.out.println("d " + d);
		String binaryRepresentation = Integer.toString(d, 2);
		System.out.println("binary representation : " + binaryRepresentation);
		List<BigInteger> list = ConvertUtils.getInstance().getBinaryAsIntegers(
				binaryRepresentation);
		// Initialisation
		PointOnEC T = P;
		// private key(number of hopps)
		privateKey = 0;
		// Algorithm
		for (BigInteger i : list) {
			T = computeR(curve, T, T);
			if (!isNull(i)) {
				T = computeR(curve, T, P);
				privateKey++;
			}
			privateKey++;
		}
		System.out.println("private key (hops on curve) : " + privateKey);
		return T;
	}

	public PointOnEC doubleAndAddAlgorithmRandom(EllipticCurve curve,
			PointOnEC P) {
		Random random = new Random();
		// All 2^32 possible int values
		int d = random.nextInt();
		System.out.println("random d " + d);
		String binaryRepresentation = Integer.toString(d, 2);
		System.out.println("binary representation : " + binaryRepresentation);
		List<BigInteger> list = ConvertUtils.getInstance().getBinaryAsIntegers(
				binaryRepresentation);
		// Initialisation
		PointOnEC T = P;
		// private key(number of hopps)
		privateKey = 0;
		// Algorithm
		for (BigInteger i : list) {
			T = computeR(curve, T, T);
			System.out.println("hop " + privateKey);
			if (!isNull(i)) {
				T = computeR(curve, T, P);
				privateKey++;
			}
			privateKey++;
		}
		System.out.println("private key (hops on curve) : " + privateKey);
		return T;
	}

	public PointOnEC doubleAndAddAlgorithm(EllipticCurve curve, PointOnEC P,
			String text) {
		List<BigInteger> binaryRepresentationOfText = ConvertUtils
				.getInstance().getBinaryAsIntegers(text);
		// Initialisation
		PointOnEC T = P;
		// private key(number of hopps)
		privateKey = 0;
		// Algorithm
		for (BigInteger i : binaryRepresentationOfText) {
			T = computeR(curve, T, T);
			if (!isNull(i)) {
				T = computeR(curve, T, P);
				privateKey++;
			}
			privateKey++;
		}
		System.out.println("private key (hops on curve) : " + privateKey);
		return T;
	}

	public PointOnEC doubleAndAddAlgorithmF(EllipticCurve curve, String text,
			PointOnEC P) {
		List<Byte> scalar = ConvertUtils.getInstance().getTextBytes(text);
		PointOnEC T = P;
		int hops = 0;
		for (int i = scalar.size() - 1; i >= 0; i--) {
			System.out.println("double");
			T = computeR(curve, T, T);
			System.out.println(scalar.get(i));
			hops++;
			if (scalar.get(i) != 0) {
				System.out.println("add");
				T = computeR(curve, T, P);
				hops++;
			}
		}
		System.out.println("T " + T.toString());
		System.out.println("hops " + hops);
		return T;
	}

	public PointOnEC doubleAndAddAlgorithmF2(EllipticCurve curve, PointOnEC P,
			BigInteger scalar) {
		PointOnEC Q = P;
		PointOnEC R = new PointOnEC(new BigInteger("5"), new BigInteger("16"));
		int n = scalar.intValue();
		while (n > 0) {
			BigInteger tmp = new BigInteger(n + "");
			BigInteger t = tmp.modPow(BigInteger.ONE, new BigInteger("2"));
			if (isEqual(t, BigInteger.ONE)) {
				R = computeR(curve, Q, R);
				System.out.println("add n " + n);
			}
			Q = computeR(curve, Q, Q);
			n = n / 2;
			System.out.println("double n " + n);
		}
		System.out.println(n);
		System.out.println(R.toString());
		return R;
	}

	public PointOnEC doubleAndAddAlgorithmF3(EllipticCurve curve, PointOnEC P,
			String text) {
		PointOnEC Q = P;
		PointOnEC R = new PointOnEC(new BigInteger("5"), new BigInteger("16"));

		List<Byte> bytes = ConvertUtils.getInstance().getTextBytes(text);
		int n = bytes.size();
		while (n > 0) {
			BigInteger tmp = new BigInteger(n + "");
			BigInteger t = tmp.modPow(BigInteger.ONE, new BigInteger("2"));
			if (isEqual(t, BigInteger.ONE)) {
				R = computeR(curve, Q, R);
				System.out.println("add n " + n);
			}
			Q = computeR(curve, Q, Q);
			n = n / 2;
			System.out.println("double n " + n);
		}
		System.out.println(n);
		System.out.println(R.toString());
		return R;
	}

	public PointOnEC scalarMult(EllipticCurve curve, PointOnEC point,
			BigInteger scalar) {
		int s = scalar.intValue();
		PointOnEC ec = point;
		for (int i = 0; i <= s; i++) {
			ec = computeR(curve, point, ec);
		}
		return ec;
	}

	// zyklische Gruppe
	public void showAllPoints(EllipticCurve curve, int to, PointOnEC startPoint) {
		PointOnEC point = computeR(curve, startPoint, startPoint);
		System.out.println(point.toString());
		for (int i = 2; i <= to; i++) {
			point = computeR(curve, point, startPoint);
			System.out.println(point.toString());
		}
	}

	private PointOnEC computeR(EllipticCurve curve, PointOnEC P, PointOnEC Q) {

		if (!isEllipticCurve(curve)) {
			System.out.println("curve is not elliptic");
			return null;
		}

		if (!isPointOnEC(curve, P) || !isPointOnEC(curve, Q)) {
			System.out.println("point P or Q is not on curve");
			return null;
		}

		BigInteger x3 = comupteX3(curve, P, Q);
		BigInteger y3 = computeY3(curve, P, Q);

		PointOnEC calculatedPoint = new PointOnEC(x3, y3);

		if (isPointOnEC(curve, calculatedPoint)) {
			return calculatedPoint;
		}
		System.out.println("calculated point is not on curve");
		return null;
	}

	private boolean isPointOnEC(EllipticCurve curve, PointOnEC point) {

		// y2 = x^3 + x^2 + ax + b
		BigInteger y2 = point.getY().pow(2);
		BigInteger y2mod = y2.mod(curve.getP());

		BigInteger x3 = new BigInteger("0");
		if (!isNull(curve.getX3())) {
			x3 = point.getX().pow(3);
		}
		BigInteger x2 = new BigInteger("0");
		if (!isNull(curve.getX2())) {
			x2 = point.getX().pow(2);
		}
		BigInteger x1 = new BigInteger("0");
		if (!isNull(curve.getX1())) {
			x1 = point.getX();
		}

		BigInteger ax = curve.getA().multiply(x1);
		BigInteger axmod = ax.mod(curve.getP());
		BigInteger axb = axmod.add(curve.getB());
		BigInteger axbmod = axb.mod(curve.getP());
		BigInteger x2x1axb = axbmod.add(x2);
		BigInteger x2x1axbmod = x2x1axb.mod(curve.getP());
		BigInteger x3x2x1axb = x2x1axbmod.add(x3);
		BigInteger x3x2x1axbmod = x3x2x1axb.mod(curve.getP());

		int compare = x3x2x1axbmod.compareTo(y2mod);

		if (compare == 0) {
			return true;
		}
		return false;
	}

	private boolean isPequalQ(PointOnEC P, PointOnEC Q) {
		int x = P.getX().compareTo(Q.getX());
		int y = P.getY().compareTo(Q.getY());
		if (x == 0 && y == 0) {
			return true;
		}
		return false;
	}

	private BigInteger comupteX3(EllipticCurve curve, PointOnEC P, PointOnEC Q) {
		BigInteger s = comupteS(curve, P, Q);
		BigInteger s_2 = s.pow(2);
		BigInteger s_2mod = s_2.mod(curve.getP());
		BigInteger s_2mod_x1 = s_2mod.subtract(P.getX());
		BigInteger s_2mod_x1mod = s_2mod_x1.mod(curve.getP());
		BigInteger s_2mod_x1mod_x2 = s_2mod_x1mod.subtract(Q.getX());
		BigInteger s_2mod_x1mod_x2mod = s_2mod_x1mod_x2.mod(curve.getP());
		return s_2mod_x1mod_x2mod;
	}

	private BigInteger computeY3(EllipticCurve curve, PointOnEC P, PointOnEC Q) {
		BigInteger s = comupteS(curve, P, Q);
		BigInteger x3 = comupteX3(curve, P, Q);
		BigInteger x1_x3 = P.getX().subtract(x3);
		BigInteger x1_x3mod = x1_x3.mod(curve.getP());
		BigInteger sx1_x3mod = s.multiply(x1_x3mod);
		BigInteger sx1_x3modmod = sx1_x3mod.mod(curve.getP());
		BigInteger sx1_x3modmod_y1 = sx1_x3modmod.subtract(P.getY());
		BigInteger sx1_x3modmod_y1mod = sx1_x3modmod_y1.mod(curve.getP());
		return sx1_x3modmod_y1mod;
	}

	// steigung der Gerade
	private BigInteger comupteS(EllipticCurve curve, PointOnEC P, PointOnEC Q) {
		if (isPequalQ(P, Q)) {
			BigInteger _3 = new BigInteger("3");
			BigInteger _2 = new BigInteger("2");
			BigInteger x2 = P.getX().pow(2);
			BigInteger x2mod = x2.mod(curve.getP());
			BigInteger x2mod_3 = _3.multiply(x2mod);
			BigInteger x2mod_3mod = x2mod_3.mod(curve.getP());
			BigInteger x2mod_3modplusA = x2mod_3mod.add(curve.getA());
			BigInteger x2mod_3modplusAmod = x2mod_3modplusA.mod(curve.getP());
			BigInteger _2y = _2.multiply(P.getY());
			BigInteger _2ymod = _2y.mod(curve.getP());
			// inverse element
			BigInteger inverse = _2ymod.modInverse(curve.getP());
			BigInteger result = inverse.multiply(x2mod_3modplusAmod);
			BigInteger resultMod = result.mod(curve.getP());
			return resultMod;
		}
		BigInteger y2_y1 = Q.getY().subtract(P.getY());
		BigInteger y2_y1mod = y2_y1.mod(curve.getP());
		BigInteger x2_x1 = Q.getX().subtract(P.getX());
		BigInteger x2_x1mod = x2_x1.mod(curve.getP());
		// inverse element ((error if BigInteger not invertible))
		BigInteger inverse = x2_x1mod.modInverse(curve.getP());
		BigInteger result = y2_y1mod.multiply(inverse);
		BigInteger resultMod = result.mod(curve.getP());
		return resultMod;
	}

	private boolean isNull(BigInteger number) {
		BigInteger _0 = new BigInteger("0");

		int compare = _0.compareTo(number);

		if (compare == 0) {
			return true;
		}
		return false;
	}

	private boolean isEqual(BigInteger number1, BigInteger number2) {
		int compare = number1.compareTo(number2);

		if (compare == 0) {
			return true;
		}
		return false;
	}
}