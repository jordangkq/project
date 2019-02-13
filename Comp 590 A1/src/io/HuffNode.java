package io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffNode implements java.lang.Comparable<HuffNode>{
	private  HuffNode leftChild;
	private  HuffNode rightChild;
	private  int data_freq;
	private  int character;
	private static HuffNode mytree;
	private static Map<Integer, String> _code_map;

	public HuffNode( HuffNode leftChild, HuffNode rightChild, int data_freq, int character) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data_freq = data_freq;
		this.character = character;


	}

	public static PriorityQueue<HuffNode> createPQ(InputStreamBitSource decompressed_file) throws InsufficientBitsLeftException, IOException {
		final PriorityQueue<HuffNode> pq = new PriorityQueue<>();
		List<Entry> list_of_probability = Entry.list_of_Freq(decompressed_file);
		List<HuffNode> x = createHuffNodeList(list_of_probability);
		x.sort(null);
		for(int i = 0; i<256; i++) {
			HuffNode currentnode = x.get(i);
			HuffNode newnode = currentnode;
			pq.add(newnode);
			//			System.out.println("This is the frequency: " + newnode.data_freq + " Character: " + newnode.character);
		}

		return pq; //create priority queue of huffman nodes with symbols + frequency
	}

	public static List<HuffNode> createHuffNodeList(List<Entry> f){
		int p =0;
		List<HuffNode> o = new ArrayList<>();
		for(int i = 0; i<256;i++) {
			p = f.get(i).getindex();
			HuffNode n = new HuffNode(null,null,p,i);
			o.add(n);
		}

		return o; //helper method to create a list of nodes that is comparable and can be sorted for the priority queue

	}


	public static HuffNode buildHuffTree(InputStreamBitSource d) throws InsufficientBitsLeftException, IOException {
		PriorityQueue<HuffNode> sorted = createPQ(d);
		HuffNode root = null;
		if(sorted.size()==1) {
			sorted.add(new HuffNode(null,null,1,-1));
		}
		while(sorted.size()>1) {
			HuffNode left = sorted.peek();
			sorted.poll();
			HuffNode right = sorted.peek();
			sorted.poll();
			HuffNode parent = new HuffNode(left,right, left.data_freq+right.data_freq,-1);
			root=parent;
			sorted.add(parent);
			//am i resorting?

		}

		printCode(root," ");
		return sorted.poll();  //build huffman tree that will be used to find lengths for symbols
	}

	boolean isLeaf() {
		return this.leftChild == null && this.rightChild == null;
	}

	@Override
	public int compareTo(HuffNode that) {
		int freqcompare = Integer.compare( this.data_freq,  that.data_freq);
		if(freqcompare != 0) {
			return freqcompare;
		}
		return Integer.compare(this.getHeight(), that.getHeight()); //compare by shortest height to break ties
	}


	public double getnodefreq() {
		return data_freq;
	}


	public int getHeight() {
		if (isLeaf()) {
			return 0;
		}
		else if (this.leftChild == null) {
			return 1 + rightChild.getHeight();
		} else if (this.rightChild == null) {
			return 1 + leftChild.getHeight();
		} else {
			int l = leftChild.getHeight();
			int r = rightChild.getHeight();
			if (l > r) {
				return l + 1;
			}
			return r + 1;
		}
		//helper method to find height
	}


	public static void printCode(HuffNode root, String s) { 
		List<Entry> map = new ArrayList<>();
		if (root.isLeaf()) { 
			System.out.println((char) root.character + ":" + s); 
			for(int i =0; i<256;i++) {
				Entry a = new Entry(root.character,s.length());
				map.add(a);
			}
			mytree=buildTreee(map);
			System.out.println("LENGTH: " + map.get(root.character).getindex() + " CHARACTER: " + (char) root.character);
			return; 
		} 

		printCode(root.leftChild, s + "0"); 
		printCode(root.rightChild, s + "1"); //traverse through huff tree and get lengths for symbols and add to list
	} 

	public void insertSymb(int symbol,int length) {
		if(length == 0) {
			this.character = symbol;
			return;
		}

		{
			if(leftChild == null) { //left child is null
				leftChild = new HuffNode(null,rightChild,character,data_freq);
				leftChild.insertSymb(symbol, length-1);
			}
			else if(leftChild.isFull() == false) { //left child is not full
				leftChild.insertSymb(symbol, length-1);
			}
			else if(rightChild == null) { //right child is null
				rightChild = new HuffNode(leftChild,null,character,data_freq);
				rightChild.insertSymb(symbol, length-1);
			}
			else if(rightChild.isFull() == false) { //right child is not full
				rightChild.insertSymb(symbol, length-1);
			}
			else {
				System.out.println("error");
			}
		}

	}

	public static HuffNode buildTreee(List<Entry> codelist) {
		HuffNode tree = new HuffNode(null, null, 0, 0);
		for(int i = 0; i< codelist.size(); i++) {
			int x = codelist.get(i).getindex();
			int y = codelist.get(i).getlength();
			tree.insertSymb(x,y);
		}
		return tree; //helper method to build canonical tree which is "mytree"
	}



	public static void fillcodemap(InputStreamBitSource whatever) throws InsufficientBitsLeftException, IOException
	{
		_code_map = new HashMap<Integer, String>();
		int bit = 0;
		int f = whatever.next(1);
		for(int i=0; i<3486; i++) {
			HuffNode current = mytree;
			while(current.isLeaf() == false && f!= -1) {
				bit = whatever.next(1);
				if(bit == 0) {
					current = current.leftChild;
				}
				else {
					current = current.rightChild;
				}

			}
			_code_map.put(i, " " + current.character);
			//			bit_sink.write(_code_map.get(i));
			System.out.print("HERE: " + (char) current.character);

		}

	}

	public boolean isFull() {
		if(isLeaf()==true) {
			return true;
		}
		if(leftChild == null || rightChild == null) {
			return false;
		}
		if(leftChild.isFull() && rightChild.isFull()) {
			return true;
		}
		return false;
		//returns true if node is full
	}


	public static void start_encode(InputStreamBitSource s) throws InsufficientBitsLeftException, IOException { 
		HuffNode root = buildHuffTree(s);
		System.out.println("This is the tree: " + root);
		printCode(root, " ");
		fillcodemap(s);
		//1. call build hufftree
		//2. call printcode
		//3. call fillcodemap
	}

	public String getCode(int symbol) {
		return _code_map.get(symbol);
	}

	public static void encode(int symbol, OutputStreamBitSink bit_sink) throws IOException {
		bit_sink.write(_code_map.get(symbol));
		System.out.println("This is the: " + _code_map.get(symbol));
	}

}
