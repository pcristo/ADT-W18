package projectOne;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import projectOne.file.*;
import projectOne.models.*;
import projectOne.multiMergeSort.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("INIT: Deleting old temporary files...");
		ClearTempFiles();
		
		System.out.println("TPMMS: Sorting...");
		long startTime = System.nanoTime();
		int sublists[] = Sort.DoSort();
		
		System.out.println("TPMMS: Merging...");
		MultiMerge.doMerge("T1", sublists[0]);
		MultiMerge.doMerge("T2", sublists[1]);

		long endTpmmsTime = System.nanoTime();
		long ioCountTPMMS = FileManagerV2.getCounter();
		
		System.out.println("BD: Bag Difference...");
		// TODO: Bag Difference
		
		long endTime = System.nanoTime();
		long ioCountTotal = FileManagerV2.getCounter();
		
		// Final output
		long tpmmsTime = (endTpmmsTime - startTime) / 10^6;
		long totalTime = (endTime - startTime) / 10^6;
		System.out.println("Input");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Total tuples: TBD");
		System.out.println("Total blocks: TBD");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("TPMMS");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Time taken: " + tpmmsTime + "ms");
		System.out.println("I/O operations: " + ioCountTPMMS);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Total");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Time taken: " + totalTime + "ms");
		System.out.println("I/O operations: " + ioCountTotal);
	}
	
	public static void TestFileManagerV2(){
		IFileManager f1 = new FileManagerV2("F1.txt");
		IFileManager f2 = new FileManagerV2("F2.txt");
		IFileManager f3 = new FileManagerV2("F3.txt");
		
		
		f1.cleanFile();
		f2.cleanFile();
		f3.cleanFile();
		
		
		System.out.println("---Testing F1---");
		f1.writeLine("Hi");
		System.out.println(f1.readNextLine());
		f1.writeLine("How are you?");
		f1.writeLine("I'm well thanks.");
		System.out.println(f1.readNextLine());
		System.out.println(f1.readNextLine());
		
		System.out.println("\n---Testing F2---");
		f2.writeLine("Second file");
		f2.writeLines(new String[]{"Line1", "Line2", "Line3"});//Simply an array of lines
		String[] lines = f2.readNextLines(10);
		for(int i = 0; i < lines.length; i++)
			System.out.println(lines[i]);
		
		f3.deleteFile();
	}

	public static void TestFileAndTuple() {
		final FileManager fm = FileManager.getCurrent();
		
		// FileManager test
		// System.out.println("Write file result: " + fm.appendFile("Hi\r\nHow're you?",
		// fileName));
		// ArrayList<String> lines = fm.readFile(fileName,20);
		// System.out.println("Lines: " + lines.size());
		// System.out.println("Last line: " + lines.get(lines.size()-1));

		// Tuple model test
		// Filling a new list of Tuples
		ArrayList<Tuple> students = new ArrayList<Tuple>();
		try {
			students.add(new Tuple(11111, "John", "Smith", 111, 222, 123456789, "3340, Maisonneuve, Montreal, QC"));
			students.add(new Tuple(22222222, "John", "Doe", 333, 333, 987654321, "2100, Maisonneuve, Montreal, QC"));
			students.add(new Tuple(12345678, "Jane", "Doe", 222, 111, 0,
					"4850, Cote-des-Neiges H3V1G5 Apt. 1106, Montreal, QC34567"));
			students.add(new Tuple(88888888, "Jasonsonny", "Jackson890", 8, 0, 999999999,
					"4858, Cote-des-Neiges H3V1G8 Apt. 803, Montreal, QC345678"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Converting the list of Tuples to string and printing
		ArrayList<String> studentStrings = new ArrayList<String>();
		for (Iterator<Tuple> studentsIterator = students.iterator(); studentsIterator.hasNext();) {
			Tuple student = studentsIterator.next();
			String stdString = student.toString();
			studentStrings.add(stdString);
			System.out.println(stdString);
		}

		// Parsing back the string list to Tuple
		ArrayList<Tuple> reparsedStudents = new ArrayList<Tuple>();
		for (Iterator<String> studentsIterator = studentStrings.iterator(); studentsIterator.hasNext();) {
			String stdString = studentsIterator.next();
			//1st option
			Tuple t = new Tuple(stdString);
			//2nd option
			Tuple t2 = new Tuple();
			t2.parse(stdString);
			//3rd option
			Tuple t3 = Tuple.Parse(stdString);
			
			reparsedStudents.add(t);
		}

		// Printing the parsed list again
		for (Iterator<Tuple> studentsIterator = reparsedStudents.iterator(); studentsIterator.hasNext();) {
			Tuple student = studentsIterator.next();
			System.out.println(student.toString());
		}
	}
	
	private static void ClearTempFiles() {
		// TODO: Delete any existing text files (besides T1.txt and T2.txt) before starting
	}
}
