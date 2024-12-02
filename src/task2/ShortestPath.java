package task2;

import java.util.*;

public class ShortestPath {


    public static class Edge {
        int target; // node index
        int weight; //transportation cost


        Edge(int target, int weight) {   // Constructor for initializing the edge
            this.target = target;
            this.weight = weight;
        }
    }

    // Method for executing Dijkstra's algorithm
    public static int dijkstra(Map<String, List<Edge>> graph, Map<String, Integer> cityIndices, String start, String end) {
        int n = graph.size(); // Number of nodes in the graph
        int[] distances = new int[n]; // Array to store minimum distances to each node
        Arrays.fill(distances, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n]; // Array to keep track of visited nodes

        // Priority queue for processing nodes by minimum distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, cityIndices.get(start)});
        distances[cityIndices.get(start)] = 0;


        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDistance = current[0];
            int currentNode = current[1];

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            if (currentNode == cityIndices.get(end)) return currentDistance; // If we reach the destination, return the result

            // Processing neighbors of the current node
            for (Edge edge : graph.getOrDefault(getCityName(cityIndices, currentNode), new ArrayList<>())) {
                if (!visited[edge.target]) {
                    int newDistance = currentDistance + edge.weight; // Calculate new distance through current node
                    if (newDistance < distances[edge.target]) {  // If a shorter path is found
                        distances[edge.target] = newDistance;
                        pq.add(new int[]{newDistance, edge.target});
                    }
                }
            }
        }
        return -1; // If the path is unreachable, return -1
    }

    // Method for getting the city name by its index
    public static String getCityName(Map<String, Integer> cityIndices, int index) {
        for (Map.Entry<String, Integer> entry : cityIndices.entrySet()) {
            if (entry.getValue() == index) return entry.getKey(); // Find the record with the desired index
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt(); //number of tests
        scanner.nextLine();


        while (testCases-- > 0) {
            int n = scanner.nextInt(); // Number of cities
            scanner.nextLine();
            Map<String, List<Edge>> graph = new HashMap<>();
            Map<String, Integer> cityIndices = new HashMap<>();

            // Read cities and their neighbors
            for (int i = 0; i < n; i++) {
                String cityName = scanner.nextLine();
                cityIndices.put(cityName, i);
                int p = scanner.nextInt();
                List<ShortestPath.Edge> edges = new ArrayList<>();
                for (int j = 0; j < p; j++) {
                    int neighborIndex = scanner.nextInt() - 1;
                    int cost = scanner.nextInt(); // Transportation cost
                    if (neighborIndex >= 0 && neighborIndex < n) {
                        edges.add(new ShortestPath.Edge(neighborIndex, cost));
                    } else {
                        System.err.println("Incorrect neighboring city index: " + neighborIndex);
                    }
                }
                scanner.nextLine();
                graph.put(cityName, edges);
            }

            int r = scanner.nextInt(); // Number of city pairs
            scanner.nextLine();

            // Processing each pair of cities
            for (int i = 0; i < r; i++) {
                String[] cities = scanner.nextLine().split(" "); // Read a pair of cities
                String city1 = cities[0];
                String city2 = cities[1];
                int result = dijkstra(graph, cityIndices, city1, city2); // We find the shortest path
                System.out.println(result);
            }

            if (testCases > 0) scanner.nextLine();
        }

        scanner.close();
    }
}
