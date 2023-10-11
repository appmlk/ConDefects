import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt()+1; // TODO this is +1
        int m = sc.nextInt();

        ArrayList<int[]>[] neighbours = new ArrayList[n];


        for(int i=1; i<n; i++) {
            neighbours[i] = new ArrayList<>();
        }

        int[] starter = new int[3];
        int smallestCurrent = Integer.MAX_VALUE;

        for(int i=0; i<m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            int weight = sc.nextInt();

            int[] edge3 = new int[]{first, second, weight}; // maybe double is goofy
            neighbours[first].add(edge3);
            neighbours[second].add(edge3);

            if(weight<smallestCurrent) {
                smallestCurrent = weight;
                starter = edge3;
            }
        }
        // we start with the smallest edge and then we greedily make it bigger
        // therefore always adding the new edges and searching the one with smallest weight
        // this is PriortiyHeap

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        int[] colors = new int[n]; // this represents coloring 0 not colored -1 blue 1 red
        long[] distance = new long[n]; // has the shortest distance to the next
        int from[] = new int[n];

        colors[starter[0]] = 1;
        colors[starter[1]] = -1;
        distance[starter[0]] = starter[2];
        from[starter[0]] = starter[1];
        distance[starter[1]] = starter[2];
        from[starter[1]] = starter[0];


        queue.addAll(neighbours[starter[0]]);
        queue.addAll(neighbours[starter[1]]);

        long result = Long.MAX_VALUE;

        while(!queue.isEmpty()) {
            int[] nextEdge = queue.poll();
            int first = nextEdge[0];
            int second = nextEdge[1];
            int weight = nextEdge[2];

            if(colors[first] == 0) {
                colors[first] = -colors[second];
                distance[first] = weight;
                from[first] = second;
                result = Long.min(result, weight+distance[second]);
                if(weight < distance[second]) {
                    distance[second] = weight;
                }
                queue.addAll(neighbours[first]);
                continue;
            }
            if(colors[second] == 0) {
                colors[second] = -colors[first];
                distance[second] = weight;
                from[second] = first;
                result = Long.min(result, weight+distance[first]);
                if(weight < distance[first]) {
                    distance[first] = weight;
                }
                queue.addAll(neighbours[second]);
                continue;
            }

            if(colors[second] != colors[first]) {
                if(from[first] != second) {
                    result = Long.min(result, weight+distance[first]);
                }
                if(from[second] != first) {
                    result = Long.min(result, weight+distance[second]);
                }
                continue;
            }

            // now we have the problem
            // so we have to paint them the same way
            result = Long.min(result, weight);

        }

        System.out.println(result);

    }
}
