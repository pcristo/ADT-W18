package projectTwo.common;

public class Parameters {
	
	public static String dataFiles[] = {"resources/T1.txt", "resources/T2.txt"};
	
	public static int blockSize = 4096;
	
	public static int tuplesPerBlockT1 = 40;//It is calculated by block size of 4096 bytes.
	public static int tuplesPerBlockT2 = 135;//TODO: It's wrong. it must be (146) if the block size is 4096 bytes.
	
	public static int maxTupleBytesT1 = 101;
	public static int maxTupleBytesT2 = 28; 
	
	public static long getAvailableMemory(){
		return Runtime.getRuntime().freeMemory();
	}
	
	public static long getMaxMemory(){
		return Runtime.getRuntime().maxMemory();
	}
	
	public static long getTotalMemory(){
		return Runtime.getRuntime().totalMemory();
	}
	
	public static long getMaxTuplesCountT1(){
		return (long)Math.floor((double)getAvailableMemory()/(double)maxTupleBytesT1);
	}
	
	public static long getMaxTuplesCountT2(){
		return (long)Math.floor((double)getAvailableMemory()/(double)maxTupleBytesT2);
	}
	
	public static long getMaxBlocksCountT1(){
		return (long)Math.ceil((double)getAvailableMemory()/(double)(tuplesPerBlockT1 * maxTupleBytesT1));
	}
	
	public static long getMaxBlocksCountT2(){
		return (long)Math.ceil((double)getAvailableMemory()/(double)(tuplesPerBlockT2 * maxTupleBytesT2));
	}
}
