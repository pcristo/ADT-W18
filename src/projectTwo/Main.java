package projectTwo;

import java.util.ArrayList;

import projectTwo.bagDifference.BagDifference;
import projectTwo.common.Parameters;
import projectTwo.file.*;
import projectTwo.models.Tuple;
import projectTwo.multiMergeSort.*;

public class Main {

	public static void main(String[] args) throws Exception {
		TestNestedJoin();
		/*
		System.out.print("TPMMS: Sorting... ");
		long startTime = System.nanoTime();
		int sublists[] = Sort.DoSort();
		System.out.print(" Bag 1: " + sublists[0] + " sublists, Bag 2: " + sublists[1] + " sublists\n");
		
		System.out.print("TPMMS: Merging... ");
		String bag1 = MultiMerge.doMerge("bag1_", sublists[0]);
		System.out.print("Bag 1 complete... ");
		String bag2 = MultiMerge.doMerge("bag2_", sublists[1]);
		System.out.println("Bag 2 complete.");

		long endTpmmsTime = System.nanoTime();
		long ioCountTPMMS = FileManagerV2.getCounter();
		
		System.out.print("BD: Bag Difference... ");
		//long resultSize = 
		BagDifference.comparator(bag1,bag2);
		
		long endTime = System.nanoTime();
		long ioCountTotal = FileManagerV2.getCounter();
		
		// Final output
		double tpmmsTime = (endTpmmsTime - startTime) / 1000000000.0;
		double totalTime = (endTime - startTime) / 1000000000.0;
		System.out.println("");
		System.out.println("------------------");
		System.out.println("PERFORMANCE REPORT");
		System.out.println("------------------");
		System.out.println("");
		System.out.println("Input");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Total tuples: " + Sort.getTuples());
		System.out.println("Total blocks: " + Math.ceil(Sort.getTuples() / Parameters.tuplesPerBlock));
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("TPMMS");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Time taken: " + tpmmsTime + "s");
		System.out.println("I/O operations: " + ioCountTPMMS);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Total");
		System.out.println("-----");
		System.out.println(" ");
		System.out.println("Time taken: " + totalTime + "s");
		System.out.println("I/O operations: " + ioCountTotal);
		System.out.println("");
		System.out.println("--COMPLETE--");
		*/
	}
	
	public static void TestNestedJoin(){
		String fileName1 = Parameters.dataFiles[0];
		String fileName2 = Parameters.dataFiles[1];
		
		NestedJoin.DoJoin(fileName1, fileName2, "resources/NestedJoinBag.txt", "resources/NestedJoinGPABag.txt");
	}
	
	public static void TestMemoryParams(){
		System.out.println("Total Memory: " + (Parameters.getTotalMemory()/1024) + "KBs");
		System.out.println("Max Memory: " + (Parameters.getMaxMemory()/1024) + "KBs");
		System.out.println("Available Memory: " + (Parameters.getAvailableMemory()/1024) + "KBs");
		System.out.println("Maximum fitting Tuples in memory: " + Parameters.getMaxTuplesCountT1() + " Tuples");
		System.out.println("Maximum fitting Blocks in memory: " + Parameters.getMaxBlocksCountT1() + " Blocks");
	}
	
	public static void TestFileManagerV2(){
		ArrayList<Tuple> students = new ArrayList<Tuple>();
		try{
			students.add(new Tuple(11111, "John", "Smith", 111, 222, 123456789, "3340, Maisonneuve, Montreal, QC"));
			students.add(new Tuple(22222222, "John", "Doe", 333, 333, 987654321, "2100, Maisonneuve, Montreal, QC"));
			students.add(new Tuple(12345678, "Jane", "Doe", 222, 111, 0,
					"4850, Cote-des-Neiges H3V1G5 Apt. 1106, Montreal, QC34567"));
			students.add(new Tuple(88888888, "Jasonsonny", "Jackson890", 8, 0, 999999999,
					"4858, Cote-des-Neiges H3V1G8 Apt. 803, Montreal, QC345678"));
		}
		catch (Exception ex)
		{
			
		}
		
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
		
		f2.writeLines(
				new String[]{
				students.get(0).toString(), 
				students.get(1).toString(), 
				students.get(2).toString(),
				students.get(3).toString()
				});//Simply an array of lines
		String[] lines = f2.readNextLines(10);
		for(int i = 0; i < lines.length; i++)
			System.out.println(lines[i]);
		
		f3.deleteFile();
		System.out.println("Number of lines in F2.txt: " + f2.getTotalNumberOfRows(Parameters.maxTupleBytesT1));
	}
}
