package roujo.cpu;

public class CPU {
	private int[] registers;
	private int programCounter;
	private int quantumCounter;
	private boolean ioFlag;
	private int exitCode;

	private int memory[];

	private final int PROGRAM_COUNTER_OUT_OF_BOUNDS = 1;
	private final int INVALID_OPCODE = 2;

	public CPU(int nbOfRegisters, int memorySize) {
		registers = new int[nbOfRegisters];
		programCounter = 0;
		quantumCounter = 0;
		ioFlag = false;
		memory = new int[memorySize];
	}

	public int[] getRegisters() {
		return registers;
	}

	public void setRegisters(int[] registers) {
		this.registers = registers;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}

	public int getQuantumCounter() {
		return quantumCounter;
	}

	public void setQuantumCounter(int quantumCounter) {
		this.quantumCounter = quantumCounter;
	}

	public boolean hasIoFlag() {
		return ioFlag;
	}

	public void setIoFlag(boolean ioFlag) {
		this.ioFlag = ioFlag;
	}
	
	public int[] getMemory() {
		return memory;
	}

	public void setMemory(int[] memory) {
		this.memory = memory;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void execute(Process process) {
		Instruction[] instructions = process.getInstructions();
		boolean shouldExit = false;

		// TODO: Use the Quantum Counter once the scheduler is made
		while (!shouldExit /*&& quantumCounter > 0*/) {
			if (programCounter >= instructions.length) {
				shouldExit = true;
				exitCode = PROGRAM_COUNTER_OUT_OF_BOUNDS;
			} else {
				Instruction inst = instructions[programCounter];
				++programCounter;
				--quantumCounter;
				switch (inst.opCode) {
				case Add:
					registers[inst.args[2]] = registers[inst.args[0]]
							+ registers[inst.args[1]];
					break;
				case Assign_from_Reg:
					registers[inst.args[0]] = registers[inst.args[1]];
					break;
				case Assign_to_Reg:
					registers[inst.args[0]] = inst.args[1];
					break;
				case Decrement:
					--registers[inst.args[0]];
					break;
				case Exit:
					shouldExit = true;
					exitCode = 0;
					break;
				case Goto:
					programCounter = inst.args[0];
					break;
				case Increment:
					++registers[inst.args[0]];
					break;
				case Jump_Eq:
					if (registers[inst.args[0]] == registers[inst.args[1]])
						programCounter = inst.args[2];
					break;
				case Load:
					registers[inst.args[1]] = memory[registers[inst.args[0]]];
					break;
				case Print:
					// TODO: Format the string according to the arguments
					System.out.println(Message.get(inst.args[0]));
					break;
				case Store:
					memory[registers[inst.args[0]]] = registers[inst.args[1]];
					break;
				case Sub:
					registers[inst.args[2]] = registers[inst.args[0]]
							- registers[inst.args[1]];
					break;
				default:
					shouldExit = true;
					exitCode = INVALID_OPCODE;
					break;
				}
			}
		}
		return;
	}
}
