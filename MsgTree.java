package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author gauthamsethupathy
 *
 */
/**
 * 
 * Creates a binary tree with binary codes for different char values. Uses those
 * binary codes to decode a message.
 *
 */
public class MsgTree {
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;

	/*
	 * Can use a static char idx to the tree string for recursive solution , but it
	 * is not strictly necessary
	 */
	private static int staticCharIdx = 0;

	/**
	 * Constructs a Binary Tree with using the encoding string in preorder. All char
	 * values are considered leaf nodes, unless it is ^. ^ is an internal node, no
	 * value needed for internal nodes.
	 * 
	 * @param encodingString A string that has the preorder traversal of the binary
	 *                       tree.
	 */
	public MsgTree(String encodingString) {
		MsgTree parentNode = null;
		MsgTree currentNode = parentNode;
		Stack<MsgTree> nodeStack = new Stack<MsgTree>();
		for (int i = 0; i < encodingString.length(); i++) {
			if (i == 0) {
				this.payloadChar = encodingString.charAt(i);
				this.left = null;
				this.right = null;
				parentNode = this;
				currentNode = parentNode;
				nodeStack.push(parentNode);
			} else {
				if (currentNode.left == null && currentNode.right == null) {
					currentNode.left = new MsgTree(encodingString.charAt(i));
					if (encodingString.charAt(i) == '^') {
						// find the parent ...
						nodeStack.push(currentNode.left);
						currentNode = currentNode.left;
					} else {
						// leaf node, not go back to the parent
						if (nodeStack.size() > 0) {
							currentNode = nodeStack.pop();
						} else {
							currentNode = parentNode;
						}
					}
				} else if (currentNode.left != null && currentNode.right == null) {
					currentNode.right = new MsgTree(encodingString.charAt(i));
					if (encodingString.charAt(i) == '^') {
						// find the parent ...
						nodeStack.push(currentNode.right);
						currentNode = currentNode.right;
					} else {
						// leaf node, not go back to the parent
						if (nodeStack.size() > 0) {
							currentNode = nodeStack.pop();
						} else {
							currentNode = parentNode;
						}
					}
				} else if (currentNode.left == null && currentNode.right != null) {
					// this cannot happen
				} else {
					// case of where both the left and right are not null
					// this also cannot happen
				}
			}
		}
	}

	/**
	 * Constructs a single node with null left and right, payload value is param.
	 * 
	 * @param payloadChar The payload value for the node.
	 */
	public MsgTree(char payloadChar) {
		this.left = null;
		this.right = null;
		this.payloadChar = payloadChar;
	}

	/**
	 * Prints the binary codes of all the char values in the tree.
	 * 
	 * @param root The binary tree used to find the codes.
	 * @param code String of the values we are looking for.
	 */
	public static void printCodes(MsgTree root, String code) {
		if (root.left == null && root.right == null) {
			if (root.payloadChar == '\n') {
				System.out.println("   " + "\\n" + "      " + code);
			} else if (root.payloadChar == ' ') {
				System.out.println("   " + "\\s" + "      " + code);
			} else {
				System.out.println("   " + root.payloadChar + "       " + code);
			}
		} else {
			if (root.left != null) {
				printCodes(root.left, code.concat("0"));
			}
			if (root.right != null) {
				printCodes(root.right, code.concat("1"));
			}
		}
	}

	/**
	 * Uses the Binary Tree and its binary codes to decode a message. The string is
	 * a bunch of 1's and 0's, this method decodes that and prints the message.
	 * 
	 * @param codes The binary tree we use to decode the message.
	 * @param msg   The String message that is being decoded.
	 */
	public void decode(MsgTree codes, String msg) {
		MsgTree copyOfCodes = codes;
		StringBuilder sbr = new StringBuilder();
		StringBuilder rslt = new StringBuilder();

		while (msg.length() > 0) {
			if (codes.left == null && codes.right == null) {
				rslt.append(codes.payloadChar);
				codes = copyOfCodes;
				sbr.setLength(0);
			} else {
				if (msg.charAt(0) == '0') {
					codes = codes.left;
					sbr.append(msg.charAt(0));
					msg = msg.substring(1);
				} else if (msg.charAt(0) == '1') {
					codes = codes.right;
					sbr.append(msg.charAt(0));
					msg = msg.substring(1);
				}
			}
		}
		if (codes.left == null && codes.right == null) {
			rslt.append(codes.payloadChar);
			codes = copyOfCodes;
			sbr.setLength(0);
		}
		System.out.println(rslt.toString());

	}
}
