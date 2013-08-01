package roujo.cpu;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		Programmer roujo = new Programmer();
		Process searchProcess = new Process(0, roujo.program(Program.Search));
		CPU p4 = new CPU(8, 1000);
		int[] memory = new int[1000];
		memory[0] = 15;
		memory[1] = 4;
		memory[2] = 3;
		memory[3] = 10;
		memory[4] = 15;
		memory[5] = 50;
		memory[6] = 15;
		p4.setMemory(memory);
		p4.execute(searchProcess);
	}

}
