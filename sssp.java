class sssp {

    private static final int NO_PARENT = -1;

    private static void dijkstra(int[][] adjacencyMatrix, int startVertex) // Function implementing Dijkstra algorithm using adjacency matrix
    {
        int nVertices = adjacencyMatrix[0].length;

        int[] shortestDistances = new int[nVertices];  // shortestDistances[i] will hold the shortest distance from src to i

        boolean[] added = new boolean[nVertices];  // added[i] will true if vertex i is included in shortest distance from src to i is finalized

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) // Initialize all distances as INFINITE and added[] as false
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0;  // Distance of 1 vertex from 1 is always 0


        int[] parents = new int[nVertices]; // Parent array to store shortest path tree


        parents[startVertex] = NO_PARENT; // The starting vertex does not have a parent


        for (int i = 1; i < nVertices; i++) // Find shortest path for all vertices
        {
            // Pick the minimum distance vertex from the set of vertices not yet processed. nearestVertex is always equal to startNode in first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            added[nearestVertex] = true; // true if already visited

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)  // Update distance value of the adjacent vertices of the picked vertex
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        printSolution(startVertex, shortestDistances, parents);
    }
    
    private static void printSolution(int startVertex, int[] distances, int[] parents) // A utility function to print the constructed distances array and shortest paths
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\t Path");

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + (startVertex+1) + " -> ");
                System.out.print((vertexIndex+1) + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Function to print shortest path from source to currentVertex using parents array
    private static void printPath(int currentVertex, int[] parents)
    {

        // Base case : Source node has been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    public static void main(String[] args)
    {
        int[][] adjacencyMatrix =
                {
                { 0, 643, 719, 0, 0, 5000 },
                { 643, 0, 0, 20, 0, 0 },
                { 719, 0, 0, 711, 0, 0 },
                { 0, 20, 711, 0, 712, 50 },
                { 0, 0, 0, 712, 0, 0},
                { 5000, 0, 0, 50, 0, 0 }
                };
        dijkstra(adjacencyMatrix, 0);
    }
}

