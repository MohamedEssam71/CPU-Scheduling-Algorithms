import java.util.*;

public class PriorityScheduling {
    Vector<Process> processList = new Vector<>();
    Integer contextSwitch;

    public void execute(){
        int sz = processList.size();
        Collections.sort(processList,Comparator.comparingInt((Process o) -> o.arrivalTime));

        int timer = 0;
//        Comparator<ProcessNonSJF> processNonSJFComparator = Comparator.
//                comparingInt(o -> o.burstTime).thenComparingInt(o -> o.arrivalTime))
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator
                .comparingInt((Process o) -> o.priority)
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
            Random rand = new Random();
            if((rand.nextInt(10000) % 7) == 0){
                queue = reArrange(queue);
            }
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
    public PriorityQueue<Process> reArrange(PriorityQueue<Process> queue){
        PriorityQueue<Process> processPriorities = new PriorityQueue<>(Comparator
                .comparingInt((Process o) -> o.priority)
                .thenComparingInt(o -> o.arrivalTime));

        while(!queue.isEmpty()){
            Process process = queue.poll();
            process.priority--;
            if(process.priority <= 0) process.priority = 1;
            processPriorities.add(process);
        }
        return processPriorities;
    }
    public static void main(String[] args) {
        PriorityScheduling priorityScheduling = new PriorityScheduling();

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

            System.out.print("Process Priority Time: ");
            Integer priority = scanner.nextInt();
            scanner.nextLine();

            Process processPriority = new Process(name,color,burst,arrival,priority);
            priorityScheduling.processList.add(processPriority);
        }
        System.out.print("Enter Context Switch: ");
        priorityScheduling.contextSwitch = scanner.nextInt();
        scanner.nextLine();

        priorityScheduling.execute();
        GUI gui = new GUI(priorityScheduling.processList);
    }
}
// Test to try!
/*
5
p1
darksalmon
10
0
3
p2
chartreuse
1
2
1
p3
MediumAquaMarine
2
14
4
p4
darkkhaki
1
17
5
p5
indianred
5
18
2
0

 */