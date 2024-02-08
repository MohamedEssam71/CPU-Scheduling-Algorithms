import java.util.*;

public class NonSJF {
    Vector<Process> processList = new Vector<>();
    Vector<Boolean> vis;
    Integer contextSwitch;

    public void execute(){
        int sz = processList.size();
        Collections.sort(processList,Comparator.comparingInt((Process o) -> o.arrivalTime));

        int timer = 0;

        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator
                .comparingInt((Process o) -> o.burstTime)
                .thenComparingInt(o -> o.arrivalTime));

        int cnt = 1,currentIndex = 0;
        while(true){
            if(queue.isEmpty() && currentIndex < sz){
                timer = Math.max(timer,processList.get(currentIndex).arrivalTime);
            }

            while (currentIndex < sz && processList.get(currentIndex).arrivalTime <= timer){
                queue.add(processList.get(currentIndex++));
            }


            if(queue.isEmpty())break;

            Process currentProcess = queue.poll();
            currentProcess.executionOrder = cnt++;
            currentProcess.startEndTime.add(timer);
            currentProcess.startEndTime.add(timer + currentProcess.burstTime + contextSwitch);



            currentProcess.turnAroundTime = currentProcess.startEndTime.lastElement() - currentProcess.arrivalTime;
            currentProcess.waitingTime = timer - currentProcess.arrivalTime;
            timer = currentProcess.startEndTime.lastElement();
            currentProcess.display();
        }
        Double avgTurnAround = 0.0, avgWaitingTime = 0.0;
        for(int i = 0; i < sz; ++i){
            avgTurnAround += processList.get(i).turnAroundTime;
            avgWaitingTime += processList.get(i).waitingTime;
        }
        avgTurnAround /= sz;
        avgWaitingTime /= sz;
        System.out.println();
        System.out.println("Average Turn Around Time: " + avgTurnAround);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }

    public static void main(String[] args) {
        NonSJF nonSJF = new NonSJF();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Process: ");
        Integer processesNumber = scanner.nextInt();

        scanner.nextLine();
        for(int i = 0; i < processesNumber; ++i){
            System.out.println("Process Number " + (i+1) + " Information.");

            System.out.print("Process Name: ");
            String name = scanner.nextLine();

            System.out.print("Process Color: ");
            String color = scanner.nextLine();

            System.out.print("Process Burst Time: ");
            Integer burst = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Process Arrival Time: ");
            Integer arrival = scanner.nextInt();
            scanner.nextLine();

            Process processNonSJF = new Process(name,color,burst,arrival);
            nonSJF.processList.add(processNonSJF);
        }
        System.out.print("Enter Context Switch: ");
        nonSJF.contextSwitch = scanner.nextInt();
        scanner.nextLine();

        nonSJF.execute();
        GUI gui = new GUI(nonSJF.processList);
    }
}
// Test to try!
// number name color burst arrival context
/*
4
p1
red
7
0
p2
pink
4
2
p3
mintgreen
1
4
p4
turquoise
4
5
1

*/
