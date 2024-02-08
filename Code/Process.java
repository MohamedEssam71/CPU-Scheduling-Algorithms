import java.util.Vector;

public class Process {
    String name, color;
    Integer burstTime,arrivalTime;
    Integer turnAroundTime, waitingTime;
    Vector<Integer> startEndTime = new Vector<>();
    Integer executionOrder, priority = null, quantum = null, agFactor = null;


    public Process(String name, String color, Integer burstTime, Integer arrivalTime) {
        this.name = name;
        this.color = color;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        waitingTime = turnAroundTime = 0;
        executionOrder = -1;
    }

    public Process(String name, String color, Integer burstTime, Integer arrivalTime, Integer priority) {
        this(name,color,burstTime,arrivalTime);
        this.priority = priority;
    }
    public Process(String name, String color, Integer burstTime, Integer arrivalTime, Integer priority, Integer quantum) {
        this(name,color,burstTime,arrivalTime,priority);
        this.quantum = quantum;
    }


    public void display() {
        System.out.print(name + "\n\t" + "Execution Order: " + executionOrder
                + "\n\t" + "Starting & Ending Time: ");
        for (int i = 1; i < startEndTime.size(); i += 2) {
            System.out.print("("+startEndTime.get(i - 1) + "-->" + startEndTime.get(i) + ")");
            if (i + 2 < startEndTime.size()) {
                System.out.print(", ");
            }
        }
        if(quantum != null){
            System.out.print("\n\t" + "Quantum: " + quantum);
        }
        System.out.print(
                "\n\t" + "Waiting Time: " + waitingTime + "\n\t" +
                        "Turn Around Time: " + turnAroundTime + "\n");
    }
}
