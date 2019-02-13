package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Node {
	private Node left;
	private Node right;
	private int symbol;

	public Node() {
		this.left = null;
		this.right = null;
		this.symbol = -1;
	}


	public boolean isLeaf() {
		if(symbol != -1) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		if(isLeaf()==true) {
			return true;
		}
		if(left == null || right == null) {
			return false;
		}
		if(left.isFull() && right.isFull()) {
			return true;
		}
		return false;
		//returns true if node is full
	}

	public void insertSymbol(int symbol,int length) {
		if(length == 0) {
			this.symbol = symbol;
			return;
		}

		{
			if(left == null) { //left child is null
				left = new Node();
				left.insertSymbol(symbol, length-1);
			}
			else if(left.isFull() == false) { //left child is not full
				left.insertSymbol(symbol, length-1);
			}
			else if(right == null) { //right child is null
				right = new Node();
				right.insertSymbol(symbol, length-1);
			}
			else if(right.isFull() == false) { //right child is not full
				right.insertSymbol(symbol, length-1);
			}
			else {
				System.out.println("error");
			}

			//left child is null
			//left child is not full
			//right child is null
			//right child is not full
			//can throw exception if 4 cases are false
		}



	}


	public static Node buildTree(List<Entry> codelist) {
		Node tree = new Node();
		for(int i = 0; i< codelist.size(); i++) {
			int x = codelist.get(i).getindex();
			int y = codelist.get(i).getlength();
			tree.insertSymbol(x,y);
		}
		return tree;
	}





	public int getSymbol() {
		return symbol;
	}

	//1. if is leaf then yes full
	//2. if left or right is null then it is not full
	//3. if left child is full and right child is full then yes full
	//4. return no not full


	//always insert left first before trying to insert right
	//leaf nodes have the symbols
	//full nodes cannot have children

	//Leaf Node - always have a symbol attached to them, never have children, are always "full"

	//need to construct tree
	//need to traverse tree and output codeword

	public static void decode(InputStreamBitSource encoded) throws InsufficientBitsLeftException, IOException
	{
		List<Entry> a = Entry.createList(encoded);
		a.sort(null);
		Node t = buildTree(a);
		int size = encoded.next(32);
		int bit = 0;
		for(int i=0; i<size; i++) {
			Node current = t;
			while(current.isLeaf() == false) {
				bit = encoded.next(1);
				if(bit == 0) {
					current = current.left;
				}
				else {
					current = current.right;
				}

			}

			System.out.print((char) current.symbol);

		}

	}

	//if 0 go left
	//if 1 go right
}
