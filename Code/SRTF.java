import java.util.*;



public class SRTF {
    Vector<Process> processList = new Vector<>();
    Vector<Boolean> vis;
    Integer contextSwitch;

    public void addProcessToDeque(Deque<Process> deque,
                                  Process processSRTF,
                                  Comparator<Process> comparator) {
        Deque<Process> tempDeque = new ArrayDeque<>();
        while (!deque.isEmpty() && comparator.compare(deque.peekFirst(), processSRTF) < 0) {
            tempDeque.addLast(deque.pollFirst());
        }
        tempDeque.addLast(processSRTF);
        while (!deque.isEmpty()) {
            tempDeque.addLast(deque.pollFirst());
        }
        deque.addAll(tempDeque);
    }

    public void execute() {
        int sz = processList.size();
        Collections.sort(processList, Comparator.comparingInt((Process o) -> o.arrivalTime));

        int timer = 0;
//        Comparator<ProcessNonSJF> processNonSJFComparator = Comparator.
//                comparingInt(o -> o.burstTime).thenComparingInt(o -> o.arrivalTime))
        Comparator<Process> comparator = Comparator
                .comparingInt((Process o) -> o.burstTime)
                .thenComparingInt(o -> o.arrivalTime);

//        PriorityQueue<ProcessSRTF> queue = new PriorityQueue<>(comparator);

        Deque<Process> deque = new ArrayDeque<>();


        int cnt = 1, currentIndex = 0;
        while (true) {
            if (deque.isEmpty() && currentIndex < sz) {
                timer = Math.max(timer,processList.get(currentIndex).arrivalTime);
            }

            while (currentIndex < sz && processList.get(currentIndex).arrivalTime <= timer) {
                addProcessToDeque(deque, processList.get(currentIndex++), comparator);
            }

            if (deque.isEmpty()) break;

            Random rand = new Random();
            // based on random integer take largest or lowest job from queue.
            // using prime number for enhancement.

            Process currentProcess;
            if ((rand.nextInt(10000) % 7) == 0) {
                currentProcess = deque.pollLast();
                if (currentProcess.executionOrder == -1) {
                    currentProcess.executionOrder = cnt++;
                }
                currentProcess.waitingTime += timer - (currentProcess.startEndTime.isEmpty() ? currentProcess.arrivalTime:currentProcess.startEndTime.lastElement());
                currentProcess.startEndTime.add(timer);
                currentProcess.startEndTime.add(timer + currentProcess.burstTime + contextSwitch);
                timer = timer + currentProcess.burstTime + contextSwitch;
                currentProcess.burstTime = 0;
                currentProcess.turnAroundTime = currentProcess.startEndTime.lastElement() - currentProcess.arrivalTime;
                currentProcess.display();
                continue;
            }


            currentProcess = deque.pollFirst();
            if (currentProcess.executionOrder == -1) {
                currentProcess.executionOrder = cnt++;
            }
            int mnTimer = timer + currentProcess.burstTime;
            for(int i = currentIndex;i < sz;++i){
                if(currentProcess.burstTime - (processList.get(i).arrivalTime - timer) > processList.get(i).burstTime){
                    mnTimer = Math.min(mnTimer,processList.get(i).arrivalTime);
                    break;
                }
            }
            currentProcess.waitingTime += timer - (currentProcess.startEndTime.isEmpty() ? currentProcess.arrivalTime:currentProcess.startEndTime.lastElement());
            currentProcess.startEndTime.add(timer);
            currentProcess.startEndTime.add(mnTimer + contextSwitch);
            currentProcess.burstTime -= mnTimer - timer;
            currentProcess.turnAroundTime = currentProcess.startEndTime.lastElement() - currentProcess.arrivalTime;
            timer = mnTimer + contextSwitch;
            if (currentProcess.burstTime > 0) {
                addProcessToDeque(deque, currentProcess, comparator);
            }
            currentProcess.display();
        }
        Double avgTurnAround = 0.0, avgWaitingTime = 0.0;
        for (int i = 0; i < sz; ++i) {
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
        SRTF srtf = new SRTF();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Process: ");
        Integer processesNumber = scanner.nextInt();

        scanner.nextLine();
        for (int i = 0; i < processesNumber; ++i) {
            System.out.println("Process Number " + (i + 1) + " Information.");

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

            Process processSRTF = new Process(name, color, burst, arrival);
            srtf.processList.add(processSRTF);
        }
        System.out.print("Enter Context Switch: ");
        srtf.contextSwitch = scanner.nextInt();
        scanner.nextLine();

        srtf.execute();
        GUI gui = new GUI(srtf.processList);
    }
}
// Test to try!
/*
5
p1
red
5
0
p2
pink
7
0
p3
mintgreen
6
0
p4
turquoise
3
0
p5
darksalmon
2
0
0

 */