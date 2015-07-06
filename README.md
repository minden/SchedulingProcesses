# Documentation: Scheduling Processes


## Code Structure:
![alt text](UMLDiagram.png)


##Program flow
1. The main method calls the read() method from the CSVReader class to receive a List of processes.
- The main method calls each generateProcessSchedule() function of the SchedulingAlgorithm classes each returning a schedule object
- The main method walks through all processes and prints the processID, the algorithm name and the turnAroundTime from each schedule.


##The SchedulingAlgorithms

Each scheduling algorithm walks through the simulation time (ct = current time), adds processes to the process queue and works the queue off. Each time a process is finished or taken off the CPU the algorithm adds a schedule item to the schedule. Once there are no processes left the algorithm returns the created schedule to the main method.

### Non preemptive
- First Come First Serve (FCFS): The process with the earliest arrival time is processed next.
- Highest Response Ratio Next (HHRN): The process with the highest response ratio  is processed next. Response ratio = (waiting time + processing time) / processing time

### Preemptive
- Round Robin (RR): Each process gets a time slice (quantum) on the CPU.
- ShortestRemaining Time (SRT): The process with the shortest processing time wether a process is running or not is processed next.


## Schedule and Schedule Items
A schedule contains schedule items which state when a process started running on the CPU and when it ended. Furthermore it contains the information whether the process is now finished or not.

##How to use the SchedulingProcesses tool
Please run the jar file in a terminal like following
```sh
java -jar SchedulingProcesses.jar PathToCSVFile
```




##Discussion
For further discussions regarding the comparison of the algorithms in terms of average turn around time please see the [discussion file](./discussion.md).
