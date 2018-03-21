//package test;
import java.io.*;
import java.util.*;
class anagrams {
	static int noofChars = 256;
	private static Scanner x;
	private static Scanner p;
	
	static String anagram(String strin, String filename) {
		long epochIni = System.currentTimeMillis();
		String FinalOut = "";
		int Wordcnt = 0;
		char[] str1 = strin.toCharArray();
		int[] cnt1 = new int [noofChars];
		Arrays.fill(cnt1, 0);
		for(int i = 0; i<str1.length;i++) {
			cnt1[str1[i]]++;
		}
		try {
			x=new Scanner(new File(filename));
		}
		catch (Exception e){
			return "No File";
		}
		while (x.hasNext()) {
			String strTemp = x.next();
			int Tempcnt = 0;
			char[] str2 = strTemp.toCharArray();
			if (str2.length == str1.length) {
				int[] cnt2 = new int [noofChars];
				Arrays.fill(cnt2, 0);
				for(int i = 0; i < str2.length; i++) {
					cnt2[str2[i]]++;
				}
				for(int j = 0; j < noofChars; j++) {
					if (cnt1[j]==cnt2[j]) {
						Tempcnt=Tempcnt+1;
					}
					else {
						break;
					}
				}
				if (Tempcnt==noofChars) {
					Wordcnt+=1;
					if (Wordcnt == 1) {
						FinalOut = FinalOut + strTemp;
					}
					else {
						FinalOut = FinalOut + "," + strTemp;
					}
				}
			}
		}
		x.close();
		long epochEnd = System.currentTimeMillis();
		long totTime = epochEnd-epochIni;
		if(Wordcnt>0) {
			System.out.printf("%d Anagrams found for %s in %sms %n", Wordcnt, new String(str1),String.valueOf(totTime));
			return FinalOut;
		}
		else {
			return "No anagrams found for "+strin +" in "+String.valueOf(totTime)+"ms";
		}
	}
	public static void main(String args[]) {
		String filename = args[0];
		System.out.println("Welcome to Anagram Finder");
		System.out.println("-------------------------");
//		long Loadstartepoch = System.currentTimeMillis();
//		String brk = "";
		try {
			p=new Scanner(new File(filename));
		}
		catch (Exception e){
			System.out.println("No File, please type 'exit' to quit program execution and run the program again with the right file(name) as input");
		}
//		while (p.hasNext()) {
//			String Tempv = p.next();
//		}
//		x.close();
//		long Loadendepoch = System.currentTimeMillis();
//		long TotloadTime = Loadendepoch-Loadstartepoch;
//		System.out.printf("Dictionary loaded in %sms",String.valueOf(TotloadTime));
		Scanner inStr = new Scanner(System.in);
		String str1 = inStr.nextLine();
		while (!str1.equals("exit")){
			String outP = anagram(str1,filename);
			System.out.printf(outP+"%n");
			str1 = inStr.nextLine();
		}
		inStr.close();
	}
}
