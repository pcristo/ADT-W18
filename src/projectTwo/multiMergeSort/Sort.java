package projectTwo.multiMergeSort;

import java.util.Arrays;

import projectTwo.common.Parameters;
import projectTwo.file.FileManagerV3;

public class Sort {
	private static long _tuples = 0;

	public static long getTuples() {
		return _tuples;
	}

	public static int[] DoSort() {
		int[] sublists = new int[2];
		System.gc();
		sublists[0] = Sort.ReadSortFile(Parameters.dataFiles[0],Parameters.getMaxTuplesCountT1(),Parameters.maxTupleBytesT1, 3.0 );
		System.gc();
		sublists[1] = Sort.ReadSortFile(Parameters.dataFiles[1],Parameters.getMaxTuplesCountT2(), Parameters.maxTupleBytesT2, 3.6);
		System.gc();
		return sublists;
	}

	private static int ReadSortFile(String FileName,long getMaxTuplesCount, int maxTupleBytes, double overheadFactor) {
		int noOfSubFiles = 0;
		try {
			FileManagerV3 inputFile = new FileManagerV3(FileName);
			int noOfLines =  (int)Math.floor((double)getMaxTuplesCount / overheadFactor);
			long totalNoOfRows = inputFile.getTotalNumberOfRows(maxTupleBytes);
			_tuples = _tuples + totalNoOfRows; 
			String subFileName = "";

			FileManagerV3 subFile;
			while (true) {
				noOfSubFiles++;
				// creation of file
				subFileName = FileName.substring(FileName.lastIndexOf('/') + 1, FileName.lastIndexOf('.')) + "_" + noOfSubFiles + ".txt";
				subFile = new FileManagerV3(subFileName);
				// read lines
				String[] lines1 = inputFile.readNextLines(noOfLines);
				if (lines1.length == 0) {
					noOfSubFiles--;
					subFile.finalize();
					break;
				}
				// sort files
				Arrays.sort(lines1);
				// write
				for (String Test : lines1) {
					subFile.writeLine(Test);
				}				
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return noOfSubFiles;
	}
}
