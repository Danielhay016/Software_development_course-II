package test;

import java.util.Arrays;

public class Q3good	 {



	public static boolean vote(boolean[][] votes) {
		int countFor=0;
		int size = (votes.length)*(votes.length);
		size = size>>1;
		// for and against counters
		for(int i=0;i<votes.length;i++)
			for(int j=0;j<votes.length;j++)
				countFor+=(votes[i][j]?1:0);



		return countFor>size;
	}





	/*
	public static boolean vote(boolean[][] votes) {
		int countFor=0;
		int size = (votes.length)*(votes.length);
		size = size>>1;
		// for and against counters
		for(int i=0;i<votes.length;i++)
			for(int j=0;j<votes.length;j++)
				if(votes[i][j])
					countFor++;


		return countFor>size;
	}




//ratio: 3 - אפילו שהורדתי בחישובים משמעותית הקפיצות בתנאים הן לא טובות מלא בראנצים למערכת ההפעלה
	public static boolean vote(boolean[][] votes) {
		int countFor=0;
		int size = (votes.length)*(votes.length);
		size = size>>1;
		int sizeToRun = votes.length>>1;
		// for and against counters
		for(int i=0;i<sizeToRun;i++)
			for(int j=0;j<votes.length;j++)
			{
				if(votes[i][j])
					countFor++;
				if(votes[i+sizeToRun][j])
					countFor++;
				if(countFor>size)
					return true;

			}

		return false;
	}

*/
}
