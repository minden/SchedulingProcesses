#Discussion of simulation results


##Aggregated results:
| Scheduling Algorithm | All processes (ms) | CPU Bounded Processes (ms) | I/O Bounded Processes (ms) |
| -------------------- | :----------------: | :------------------------: | :------------------------: |
| SRT                  |202                 | 127                        | 2187                       |
| FCFS                 |226                 | 140                        | 2292                       |
| HRRN                 |217                 | 133                        | 2174                       |
| RR10                 |256                 | 180                        | 2110                       |


##Reasons for the results:
###Shortest remaining time:
Due to the fact that the SRT Schedule is preemptive it is very agile. Thereby it can handle both the CPU and I/O bounded processes very good.
###Round Robin:
The preemptive Round Robin algorithm treats all processes the fair. Thereby the I/O bounded processes do not have any disadvantages compared to the CPU bounded processes. This is one reason why this algorithm does well in the I/O bounded processes average.
On the other hand the algorithm does not give the CPU bounded processes any advantages. Thereby the average in this case is comparatively high. This is also reflected in the overall average of the algorithm.
###Highest Response Ratio Next:
This process prefers short and long time waiting processes. With these properties there is no risk of starvation. This is also reflected in the overall average which is comparatively  low.
###First Come First Serve:
The FCFS algorithm is called a fair scheduler just like the RR algorithm. But it prefers CPU intense processes over I/O bounded processes. This explains the very bad average results regarding the I/O bounded processes.


##Conclusion:
From the table above you can see that SRT and HRRN are the best algorithms for CPU bounded processes. But if it comes to I/O bounded processes Round Robin is up front. In the overall average performance the Shortest Remaining Time algorithm is the winner.
