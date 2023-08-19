# Memory_Management

This program simulates memory partition management. It consists of two main components:

# Partition Class: 
This class defines memory partitions with attributes like status (free/allocated), size, addresses, process ID, and internal fragmentation.

# Main Class: 
The program's entry point. It prompts the user for the number of partitions and initializes them. Users then select an allocation strategy (First-fit, Best-fit, Worst-fit).

Option 1 (Allocation): Users allocate memory by providing a process ID and size. Allocation depends on the chosen strategy:
First-Fit: Assigns the first available partition that fits the process/data.
Best-Fit: Assigns the smallest partition that accommodates the process/data.
Worst-Fit: Assigns the largest partition available for the process/data.

Option 2 (Deallocate): Users deallocate by entering a process ID. The program marks the corresponding partition as free.

Option 3 (Report): Displays partition details (status, size, addresses, process ID, fragmentation) and writes them to "Report.txt."

In essence, this program models how memory is allocated and released using different strategies.
