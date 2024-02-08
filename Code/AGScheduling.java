import java.util.*;


public class AGScheduling {

    Vector<Process> processList = new Vector<>();
    Integer contextSwitch;



    public void execute(){
        int sz = processList.size();
        Collections.sort(processList, Comparator.comparingInt((Process o) -> o.arrivalTime));
        int timer = 0;
        Vector<Process> queue = new Vector<>();
        Random random = new Random();
        for(int i = 0; i < sz; ++i){
            Process current = processList.get(i);
            Integer rand = random.nextInt(20);
//            switch (i) {
//                case 0 -> processList.get(i).agFactor = 20;
//                case 1 -> processList.get(i).agFactor = 17;
//                case 2 -> processList.get(i).agFactor = 16;
//                case 3 -> processList.get(i).agFactor = 43;
//            }
            if(rand == 10){
                processList.get(i).agFactor = current.priority + current.arrivalTime + current.burstTime;
            }
            else if(rand > 10){
                processList.get(i).agFactor = 10 + current.arrivalTime + current.burstTime;
            }
            else{
                processList.get(i).agFactor = rand + current.arrivalTime + current.burstTime;
            }
        }
        boolean lastProcessDone = false;
        int cnt = 1,currentIndex = 0,sumOfQuantum = 0;
        while(true){

            if(queue.isEmpty() && currentIndex < sz){
                timer = Math.max(timer,processList.get(currentIndex).arrivalTime);
            }

            while (currentIndex < sz && processList.get(currentIndex).arrivalTime <= timer){
                queue.add(processList.get(currentIndex++));
                sumOfQuantum += queue.lastElement().quantum;
            }


            if(queue.isEmpty())break;

            Process currentProcess = null;
            int n = queue.size();
            if(!lastProcessDone) {
                int mini = 1000000000,bestIndex = -1;
                for (int i = 0; i < n; ++i) {
                    Process temp = queue.get(i);
                    if (mini > temp.agFactor) {
                        mini = temp.agFactor;
                        currentProcess = temp;
                        bestIndex = i;
                    }
                }
                queue.remove(bestIndex);
            }
            else {
                currentProcess = queue.getFirst();
                queue.removeFirst();
            }
            lastProcessDone = false;


            if(currentProcess.executionOrder == -1) currentProcess.executionOrder = cnt++;


            Integer workTime = Math.min(currentProcess.quantum,currentProcess.burstTime);
            for(int i = 0;i < queue.size();++i){
                if(queue.get(i).agFactor < currentProcess.agFactor){
                    workTime = 0;
                    break;
                }
            }
            for(int i = currentIndex;i < sz;++i){
                if(processList.get(i).agFactor < currentProcess.agFactor){
                    workTime = Math.min(workTime,processList.get(i).arrivalTime - timer);
                }
            }

            Integer currentQuantum = currentProcess.quantum;
            Integer timeNeeded = Math.min(currentProcess.burstTime,(int)Math.ceil(currentQuantum/2.0));
            workTime = Math.max(workTime,timeNeeded);


            currentProcess.waitingTime += timer - (currentProcess.startEndTime.isEmpty() ? currentProcess.arrivalTime:currentProcess.startEndTime.lastElement());

            currentProcess.startEndTime.add(timer); // add first start
            currentProcess.startEndTime.add(timer + workTime); // add first start

            currentProcess.burstTime -= workTime;
            currentProcess.turnAroundTime = currentProcess.startEndTime.lastElement() - currentProcess.arrivalTime;
            timer += workTime;


            if(currentProcess.burstTime == 0){
                // add to die list
                sumOfQuantum -= currentQuantum;
                currentProcess.quantum = 0;
                lastProcessDone = true;
            }
            else if(workTime.equals(currentQuantum)){
                // update my quantum and add to end of queue
                int extraQuantum = (int)Math.ceil((double)sumOfQuantum / (queue.size() + 1) / 10);

                sumOfQuantum -= currentProcess.quantum;
                currentProcess.quantum += extraQuantum;
                sumOfQuantum += currentProcess.quantum;

                lastProcessDone = true;
                queue.add(currentProcess);
            }
            else{
                sumOfQuantum -= currentProcess.quantum;
                currentProcess.quantum += currentQuantum - workTime;
                sumOfQuantum += currentProcess.quantum;
                queue.add(currentProcess);
            }
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
        AGScheduling agScheduling = new AGScheduling();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Process: ");
        Integer processesNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Round Robin Quantum: ");
        Integer quantum = scanner.nextInt();
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

            Process processAG = new Process(name,color,burst,arrival,priority,quantum);
            agScheduling.processList.add(processAG);
        }
        System.out.print("Enter Context Switch: ");
        agScheduling.contextSwitch = scanner.nextInt();
        scanner.nextLine();

        agScheduling.execute();
        GUI gui = new GUI(agScheduling.processList);
    }
}
// Test to try!
//number name color burst arrival priority quantum,
/*
4
4
p1
darksalmon
17
0
4
p2
chartreuse
6
3
9
p3
MediumAquaMarine
10
4
3
p4
darkkhaki
4
29
8
0

 */