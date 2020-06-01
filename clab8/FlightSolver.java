import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private int maxPassengers = 0;
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        PriorityQueue<Flight> startMinPQ = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.startTime(), o2.startTime());
            }
        });

        PriorityQueue<Flight> endMinPQ = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.endTime(), o2.endTime());
            }
        });

        startMinPQ.addAll(flights);
        endMinPQ.addAll(flights);

        int num = 0;
        while(startMinPQ.size()!=0){
            if(startMinPQ.peek().startTime() <= endMinPQ.peek().endTime()) {
                int temp = startMinPQ.peek().startTime();
                int temp2 = endMinPQ.peek().endTime();
                num += startMinPQ.poll().passengers();
                maxPassengers = num>maxPassengers ? num : maxPassengers;
                System.out.printf("s: %d, e:%d, n:%d! ", temp, temp2, num);
            }
            else {
                num -= endMinPQ.poll().passengers(); // %  如果连这个starttime里面的都没有这个endtime，那么后面的也没有
            }
        }

        System.out.printf("\n");
    }

    public int solve() {
        /* FIX ME */
        return maxPassengers;
    }

}
