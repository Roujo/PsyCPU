package roujo.cpu;

public class Instruction {
	public final OpCode opCode;
	public final int[] args;
	
	public Instruction(OpCode opCode, int...args) {
		this.opCode = opCode;
		this.args = args;
	}
}
