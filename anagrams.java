import java.io.*;
import java.util.*;

class anagrams {
	
	static int noofChars = 256;
	private static Scanner p;
	
	static String anagram(String strin, ArrayList<String> list) {
		long epochIni = System.currentTimeMillis();
		String FinalOut = "";
		int Wordcnt = 0;
		char[] str1 = strin.toCharArray();
		int[] cnt1 = new int [noofChars];
		Arrays.fill(cnt1, 0);
		for(int i = 0; i<str1.length;i++) {
			cnt1[str1[i]]++;
		}
		for(String word:list) {
			String strTemp = word;
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
		try {
			if (!args[0].isEmpty() && args.length==1) {
				String filename = args[0];
				ArrayList<String> words = new ArrayList<String>();
				System.out.printf("%n%nWelcome to Anagram Finder%n");
				System.out.println("-------------------------");
				try {
						long Loadstartepoch = System.currentTimeMillis();
						p=new Scanner(new File(filename));
						while (p.hasNext()) {
							words.add(p.next());
						}
						p.close();
						long Loadendepoch = System.currentTimeMillis();
						long TotloadTime = Loadendepoch-Loadstartepoch;
						System.out.printf("Dictionary loaded in %sms %n%n",String.valueOf(TotloadTime));
				}
				catch (Exception e){
					System.out.println("No File, please type 'exit' to quit program execution and run the program again with the right file(name) as input");
				}
				Scanner inStr = new Scanner(System.in);
				System.out.println("AnagramFinder>Enter Word: ");
				String str1 = inStr.nextLine();
				while (!str1.equals("exit")){
					if (!words.isEmpty()) {
						String outP = anagram(str1,words);
						System.out.printf(outP+"%n%n");
						System.out.println("AnagramFinder>Enter Word: ");
						str1 = inStr.nextLine();
						}
					else {
						System.out.printf("No words in file to search or filename given is wrong >>> Exiting...%n");
						str1 = "exit";
					}
				}
				inStr.close();
			}
			else {
					System.out.printf("Please enter single filename >>> Exiting...%n");
			}
		}
		catch (Exception e) {
			System.out.printf("Please enter single filename >>> Exiting...%n");
		}
	}
}