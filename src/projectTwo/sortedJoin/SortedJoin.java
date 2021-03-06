package projectTwo.sortedJoin;

import projectTwo.file.FileManagerV3;
import projectTwo.file.IFileManager;
import projectTwo.common.GPAFile;
import projectTwo.common.MarkUtils;

public class SortedJoin {
	/***
	 * Does a sorted join operation, returns the number of tuples in the output 
	 */
	public static long DoJoin(String filenameT1, String filenameT2, String outputFilename, String gpaFilename) {
		IFileManager T1 = new FileManagerV3(filenameT1);
		IFileManager T2 = new FileManagerV3(filenameT2);
		IFileManager output = new FileManagerV3(outputFilename);
		IFileManager gpa = new FileManagerV3(gpaFilename);
		
		String nextT1 = T1.readNextLine();
		String nextT2 = T2.readNextLine();
		
		long numOutputTuples = 0;
		int currentCredits = 0;
		float currentPoints = 0;
		
		while (nextT1 != null && nextT2 != null) {			
			int idT1 = Integer.parseInt(nextT1.substring(0, 8));
			int idT2 = Integer.parseInt(nextT2.substring(0, 8));
			
			if (idT1 < idT2) {
				if (currentCredits > 0) {
					GPAFile.SaveGpaRecord(idT1, currentCredits, currentPoints, gpa);
					currentCredits = 0;
					currentPoints = 0;
				}
				
				nextT1 = T1.readNextLine();
			}
			else if (idT1 > idT2) {
				nextT2 = T2.readNextLine();
			}
			else {
				String joinedString = nextT1 + nextT2.substring(8);
				output.writeLine(joinedString);
				numOutputTuples++;
				
				float thisCredits = MarkUtils.ExtractCreditsFromTuple(nextT2);
				currentCredits += thisCredits;
				currentPoints += MarkUtils.ExtractGradeFromTuple(nextT2) * thisCredits;
				
				nextT2 = T2.readNextLine();
			}
		}
		
		T1.finalize();
		T2.finalize();
		output.finalize();
		
		return numOutputTuples;
	}
}
