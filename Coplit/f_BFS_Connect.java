package Coplit;

import java.util.LinkedList;
import java.util.Queue;

public class f_BFS_Connect {
    public static void main(String[] args) {
        int result = connectedVertices(new int[][]{
                {0, 1},
                {2, 3},
                {4, 5},
        });
        System.out.println(result);
    }

    public static int connectedVertices(int[][] edges) {
        Queue<Integer> q = new LinkedList<>();
        q.add(edges[0][0]);
        return 2;
    }
}
