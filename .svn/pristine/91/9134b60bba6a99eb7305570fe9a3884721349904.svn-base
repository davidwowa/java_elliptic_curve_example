package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

	private static ConvertUtils instance;

	private ConvertUtils() {
	}

	public List<Byte> getTextBytes(String text) {
		byte[] infoBin = null;
		List<Byte> bytes = new ArrayList<Byte>();
		try {
			infoBin = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("error on coverting text to binary array");
			e.printStackTrace();
		}
		for (byte b : infoBin) {
			bytes.add(b);
			// System.out.println((char) b + "-> " + Integer.toBinaryString(b));
			// System.out.println((char) b + "-> " + b);
		}
		return bytes;
	}

	public List<BigInteger> getBinaryAsIntegers(String text) {
		List<BigInteger> list = new ArrayList<BigInteger>();
		String[] array = text.split("");
		// if (array.length >= 2) {
		// list.add(new BigInteger("0"));
		// list.add(new BigInteger("0"));
		// }
		for (String string : array) {
			list.add(new BigInteger(string));
		}
		return list;
	}

	public List<BigInteger> getTextAsBinary(String text) {
		byte[] infoBin = null;
		List<BigInteger> bytes = new ArrayList<BigInteger>();
		try {
			infoBin = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("error on coverting text to binary array");
			e.printStackTrace();
		}
		for (byte b : infoBin) {
			String binaryArray = Integer.toBinaryString(b);

			String[] binarySizeArray = binaryArray.split("");
			for (int i = 0; i < binarySizeArray.length - 1; i++) {
				BigInteger b2 = new BigInteger(binarySizeArray[i]);
				bytes.add(b2);
			}
		}
		return bytes;
	}

	public static ConvertUtils getInstance() {
		if (instance == null) {
			instance = new ConvertUtils();
		}
		return instance;
	}
}