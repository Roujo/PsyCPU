package roujo.cpu;

import java.util.ArrayList;
import java.util.List;

public class ProgramBuilder {
	private List<Instruction> instructions;
	
	public ProgramBuilder() {
		this.instructions = new ArrayList<Instruction>();
	}
	
	public ProgramBuilder addInstruction(OpCode opCode, int...args) {
		instructions.add(new Instruction(opCode, args));
		return this;
	}
	
	public Instruction[] finish() {
		return instructions.toArray(new Instruction[0]);
	}
	
	public void clear() {
		instructions.clear();
	}
}
