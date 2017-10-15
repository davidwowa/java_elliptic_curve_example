package console;

import java.math.BigInteger;

import model.EllipticCurve;
import model.PointOnEC;
import model.util.ECOperation;
import utils.IO;

public class ConsoleWorkflow {

	private static String message = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,";
	private static String _26 = Integer.toString(26, 2);

	// y^2=x^3+2x+2 mod 17
	private static EllipticCurve curve = new EllipticCurve(new BigInteger("0"),
			new BigInteger("2"), new BigInteger("2"), new BigInteger("1"),
			new BigInteger("0"), new BigInteger("1"), new BigInteger("17"));

	// http://en.wikipedia.org/wiki/Curve25519
	private static EllipticCurve montgomeryCurve = new EllipticCurve(
			new BigInteger("0"),
			new BigInteger("1"),
			new BigInteger("0"),
			new BigInteger("1"),
			new BigInteger("486662"),
			new BigInteger("1"),
			new BigInteger(
					"57896044618658097711785492504343953926634992332820282019728792003956564819949"));

	public static void main(String[] args) {
		ConsoleWorkflow workflow = new ConsoleWorkflow();
		// workflow.startCyclicExampleR();
		// workflow.startCyclicExample();
		// workflow.startExample();
		// workflow.exampleFromBook();
		// workflow.exampleWithText();
		// workflow.exampleRandom();
		// workflow.exampleRandomWithMontgomeryCurve();
		 workflow.ECDH();
	}

	public void startCyclicExampleR() {
		ECOperation.getInstance().findCyclicGroupOf(curve);
	}

	public void startCyclicExample() {
		System.out.println("enter public parameter as point");
		PointOnEC point = IO.getInstance().enterPoint();
		System.out.println("all points on curve ");
		ECOperation.getInstance().showAllPoints(curve, 17, point);
	}

	public void exampleDoubleAndAddBook() {
		System.out.println("message: " + _26);
		// enter point (public parameter)
		System.out.println("enter public parameter as point");
		PointOnEC point = IO.getInstance().enterPoint();
		// double and add algorithm
		System.out.println("running double and add algorithm");
		PointOnEC publicKey = ECOperation.getInstance().doubleAndAddAlgorithm(
				curve, point, _26);
		System.out.println("public key (point) = " + publicKey.toString());
	}

	// TODO
	public void exampleWithTextF() {
		System.out.println("message: " + message);
		// enter point (public parameter)
		System.out.println("enter public parameter as point");
		PointOnEC point = IO.getInstance().enterPoint();
		// double and add algorithm
		System.out.println("running double and add algorithm");
		PointOnEC publicKey = ECOperation.getInstance().doubleAndAddAlgorithm(
				curve, point, message);
		System.out.println("public key (point) = " + publicKey.toString());
	}

	public void exampleRandom() {
		// enter point (public parameter)
		System.out.println("enter public parameter as point");
		PointOnEC point = IO.getInstance().enterPoint();
		// double and add algorithm
		System.out.println("running double and add algorithm");
		PointOnEC publicKey = ECOperation.getInstance()
				.doubleAndAddAlgorithmRandom(curve, point);
		System.out.println("public key (point) = " + publicKey.toString());
	}

	public void exampleRandomWithMontgomeryCurve() {
		// enter point (public parameter)
		System.out.println("enter public parameter as point");
		PointOnEC point = IO.getInstance().enterPoint();
		// double and add algorithm
		System.out.println("running double and add algorithm");
		PointOnEC publicKey = ECOperation.getInstance()
				.doubleAndAddAlgorithmRandom(montgomeryCurve, point);
		System.out.println("public key (point) = " + publicKey.toString());
	}

	public void ECDH() {
		// choice a base point
		System.out.println("enter the base point");
		PointOnEC basePoint = IO.getInstance().enterPoint();

		System.out.println("ALICE");
		// Allice
		// choose kprA
		int a = 19;
		System.out.println("\t choosed a " + a);
		// compute kpubA
		PointOnEC publicKeyA = ECOperation.getInstance()
				.doubleAndAddAlgorithmChoosed(curve, basePoint, a);
		System.out
				.println("\t public key from allice " + publicKeyA.toString());
		int privateKeyA = ECOperation.privateKey;
		System.out.println("\t allice hops (private key)" + privateKeyA);

		System.out.println("BOB");
		// Bob
		// choose kprB
		int b = 11;
		System.out.println("\t choosed b " + b);
		// compute kpubB
		PointOnEC publicKeyB = ECOperation.getInstance()
				.doubleAndAddAlgorithmChoosed(curve, basePoint, b);
		System.out.println("\t public key from bob " + publicKeyB.toString());
		int privateKeyB = ECOperation.privateKey;
		System.out.println("\t bob hops (private key)" + privateKeyB);

		// key exchange

		// Alice
		// compute aB
		PointOnEC TAB1 = ECOperation.getInstance()
				.doubleAndAddAlgorithmChoosed(curve, publicKeyB, a);
		System.out.println("\t TAB1 " + TAB1.toString());

		// Bob
		// compute bA
		PointOnEC TAB2 = ECOperation.getInstance()
				.doubleAndAddAlgorithmChoosed(curve, publicKeyA, b);
		System.out.println("\t TAB2 " + TAB2.toString());

		// correctness
		System.out.println("correctness");
		// aB = a(bP)
		PointOnEC abP = ECOperation.getInstance().doubleAndAddAlgorithmChoosed(
				curve, publicKeyB, a);
		System.out.println("\t abP " + abP.toString());
		// bA = b(aP)
		PointOnEC baP = ECOperation.getInstance().doubleAndAddAlgorithmChoosed(
				curve, publicKeyA, b);
		System.out.println("\t baP " + baP.toString());

		// correctness
		System.out.println("correctness2");
		int ab = a * b;
		PointOnEC TAB = ECOperation.getInstance().doubleAndAddAlgorithmChoosed(
				curve, basePoint, ab);
		System.out.println("\t TAB " + TAB.toString());
	}
}