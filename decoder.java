package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author gauthamsethupathy
 *
 */
/**
 * 
 * Runs the algorithm. Uses the methods to decode a file that contains a
 * preorder traversal of the tree and the encoded message.
 *
 */
public class decoder {
	/**
	 * 
	 * Runs the algorithm. Uses the methods to decode a file that contains a
	 * preorder traversal of the tree and the encoded message.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		String filename = input.next();
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		StringBuilder sb = new StringBuilder();
		sb.append(sc.nextLine());
		String x = sc.nextLine();
		String msg;
		if (x.charAt(0) != '1' && x.charAt(0) != '0') {
			sb.append('\n');
			sb.append(x);
			msg = sc.nextLine();
		} else {
			msg = x;
		}
		MsgTree m = new MsgTree(sb.toString());
		String code = new String();
		System.out.println("character code");
		System.out.println("-------------------------");
		MsgTree.printCodes(m, code);
		System.out.println();
		System.out.println("MESSAGE:");
		m.decode(m, msg);
	}

}
