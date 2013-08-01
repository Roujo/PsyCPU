package roujo.cpu;

public class Process {
	private final int pid;
	private final Instruction[] instructions;
	
	public Process(int pid, Instruction[] instructions) {
		this.pid = pid;
		this.instructions = instructions;
	}

	public int getPid() {
		return pid;
	}

	public Instruction[] getInstructions() {
		return instructions;
	}
}
