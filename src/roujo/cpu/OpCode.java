package roujo.cpu;

public enum OpCode {
	// TODO: Clean up the documentation
	/**
	 * reg(2) = reg(0) + reg(1)
	 */
	Add,
	/**
	 * reg(2) = reg(0) - reg(1)
	 */
	Sub,
	/**
	 * reg(0) += "1"
	 */
	Increment,
	/**
	 * reg(0) -= "1"
	 */
	Decrement,
	/**
	 * reg(1) = mem(reg(0))
	 */
	Load,
	/**
	 * mem(reg(0)) = reg(1)
	 */
	Store,
	/**
	 * reg(0) = 1
	 */
	Assign_to_Reg,
	/**
	 * reg(0) = reg(1)
	 */
	Assign_from_Reg,
	/**
	 * prog_counter = 1
	 */
	Goto,
	/**
	 * if(reg(0) = reg(1)) then prog_counter = 2;
	 */
	Jump_Eq,
	/**
	 * Print out message number 1
	 */
	Print,
	/**
	 * Exit the process normally
	 */
	Exit;
}
