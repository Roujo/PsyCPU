package roujo.cpu;

import java.util.Map;
import java.util.TreeMap;

public class Programmer {
	private Map<Program, Instruction[]> memory;
	
	public Programmer() {
		memory = new TreeMap<Program, Instruction[]>();
	}
	
	public Instruction[] program(Program program) {
		if(memory.containsKey(program)) {
			return memory.get(program);
		} else {
			ProgramBuilder builder = new ProgramBuilder();
			switch(program) {
			case Search:
				buildSearch(builder);
				break;
			default:
				// Looks like the programmer doesn't know how to do this.
				// TODO: Fetch program from StackOverflow
				break;
			}
			Instruction[] instructions = builder.finish();
			memory.put(program, instructions);
			return instructions;
		}
	}

	/**
	 * @param builder
	 */
	private void buildSearch(ProgramBuilder builder) {
		int reg_pointer = 0;
		int reg_number = 1;
		int reg_array_size = 2;
		int reg_array_end = 3;
		int reg_array_element = 4;
		// 0 - Load the number to look for
		builder.addInstruction(OpCode.Assign_to_Reg, reg_pointer, 0);
		builder.addInstruction(OpCode.Load, reg_pointer, reg_number);
		// 2 - Load the size of the array
		builder.addInstruction(OpCode.Increment, reg_pointer);
		builder.addInstruction(OpCode.Load, reg_pointer, reg_array_size);
		// 4 - Load the location of the array
		builder.addInstruction(OpCode.Increment, reg_pointer);
		builder.addInstruction(OpCode.Load, reg_pointer, reg_pointer);
		// 6 - Find the position of the end of the array
		builder.addInstruction(OpCode.Add, reg_pointer, reg_array_size, reg_array_end);
		// 7 - If we're out of bound, bail
		builder.addInstruction(OpCode.Jump_Eq, reg_pointer, reg_array_end, 14);
		// 8 - Otherwise, load it into memory
		builder.addInstruction(OpCode.Load, reg_pointer, reg_array_element);
		// 9 - Go to the print subroutine if a match is found
		builder.addInstruction(OpCode.Jump_Eq, reg_number, reg_array_element, 12);
		// 10 - Go to the next element
		builder.addInstruction(OpCode.Increment, reg_pointer);
		// 11 - Loop
		builder.addInstruction(OpCode.Goto, 7);
		// 12 - Print stuff
		builder.addInstruction(OpCode.Print, 0);
		// 13 - Go back to the program
		builder.addInstruction(OpCode.Goto, 10);
		// 14 - Exit gracefully
		builder.addInstruction(OpCode.Exit);		
	}
}
