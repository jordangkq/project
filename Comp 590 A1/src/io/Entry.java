package io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Entry implements java.lang.Comparable<Entry>{
	//symbol = index
	private int _codewordlength;
	private int _symbol;
	public int filesize;

	public Entry(int codeword_length, int symbol) {
		_codewordlength = codeword_length; //freq
		_symbol = symbol;                  //letter
		this.filesize=0;
	}


	public static List<Entry> createList(InputStreamBitSource file) throws InsufficientBitsLeftException, IOException {
		List<Entry> list = new ArrayList<>();
		int length=0;
		int symbol=0;
		for(int i = 0 ; i <256; i++) {
			length = file.next(8);
			symbol = i;
			Entry item = new Entry(length,symbol);
			list.add(item);
		}
		return list;
	}

	public int getlength() {
		return _codewordlength;
	}

	public int getindex() {
		return _symbol;
	}

	@Override
	public int compareTo(Entry entry) {
		int a = this._codewordlength;
		int b = entry._codewordlength;
		int diff = a-b; 
		int c = this._symbol;
		int d = entry._symbol;

		if(diff ==0) {
			return c-d;
		}
		return a-b;
	}


	///For the Encoder---------------------------------------------------------------------------------------------------------

	public static List<Entry> list_of_Freq(InputStreamBitSource file) throws InsufficientBitsLeftException, IOException{
		int freq=0; //length
		int letter = 0; //index
		List<Entry> newfreqlist = new ArrayList<>();
		List<Entry> freqlist = new ArrayList<>(256); //go through file and count frequency up everytime character is at index
		for(int i=0; i<256;i++) {
			Entry hist = new Entry(freq,i);
			freqlist.add(hist);
			//			System.out.println("This is the Letter: " + hist.getindex() + " This is the Frequency: " + hist.getlength());
		}
		int character=file.next(8);
		int j=0;
		while(character != -1) {
			freqlist.get(character).observeSymbol();
			j++; //
			try { character = file.next(8);
			}catch(InsufficientBitsLeftException e) {
				character = -1;
			}

			System.out.println("This is the number of symbols: " + j);
		}
		int d=0;
		for(int g = 0; g< freqlist.size(); g++) {
			d = freqlist.get(g).getlength();
			//			d = freqlist.get(g).turntofreq(j);
			Entry s = new Entry(g,d);
			newfreqlist.add(s);      
		}
		return newfreqlist;

	}

	public void observeSymbol() {
		_codewordlength++;
	}

	public double turntofreq(int numsymbols) {
		double e= (double) _codewordlength/numsymbols;
		return  e;
	}

	public void getfilesize() {
		filesize++;
		System.out.println("This is the file size: " + filesize);
	}


}
