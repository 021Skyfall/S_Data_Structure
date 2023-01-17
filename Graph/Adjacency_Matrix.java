package Graph;

import java.util.Arrays;

public class Adjacency_Matrix {
    public static void main(String[] args) {
        Solution adjMatrix = new Solution();
        System.out.println(Arrays.deepToString(adjMatrix.getGraph()));
    }

    static class Solution {
        private int[][] graph;

        public void setGraph(int size) {
            graph = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    graph[i][j] = 0;
                }
            }
        }

        public int[][] getGraph() {
            return graph;
        }

        public void addEdge(int from, int to) {
            if (from >= graph.length || to >= graph.length) return;
            graph[from][to] = 1;
        }

        public boolean hasEdge(int from, int to) {
            if (from >= graph.length || to >= graph.length) return false;
            else if (graph[from][to] == 1) return true;
            else return false;
        }

        public void removeEdge(int from, int to) {
            if (from >= graph.length || to >= graph.length) return;
            graph[from][to] = 0;
        }
    }
}