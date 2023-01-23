package Coplit;

import java.util.Arrays;
import java.util.Comparator;
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
        // 연결된 버텍스를 하나의 묶음으로 해당 묶음은 총 몇개냐는 문제임
        // 최대 버택스 찾기
        int[] big = {0};
        Arrays.stream(edges).forEach(data -> {int cbig = Arrays.stream(data).boxed()
                .max(Comparator.comparing(i -> i)).orElse(0);
        if (big[0] < cbig) big[0] = cbig;});
        int[][] adj = new int[big[0]+1][big[0]+1];
        // Graph Matrix 의 그래프 크기 지정과 같은 로직임

        // edges 를 순회하면서, 간선을 연결해줌 ~> 무향이기 때문에 쌍방으로 연결해야함
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from][to] = 1;
            adj[to][from] = 1;
        }

        // 방문할 버텍스를 담을 배열 선언
        boolean[] visit = new boolean[big[0]+1];
        // 컴포넌트가 몇 개 인지 카운트할 변수를 선언 ~> 이어진 길 = 컴포넌트
        int count = 0;

        // 방문 여부를 확인한 배열 모두 순회
        for (int vertex = 0; vertex < visit.length; vertex++) {
            if (!visit[vertex]) {
                count++;
                visit = bfs_array(adj,vertex,visit);
            }
        }
        return count;
    }
    // bfs 활용
    public static boolean[] bfs_array(int[][] adj, int vertex, boolean[] visit) {
        // bfs 니까 큐 사용
        Queue<Integer> q = new LinkedList<>();
        // 시작지점을 큐에 넣고, 해당 버텍스의 방문 여부 변경
        q.add(vertex);
        visit[vertex] = true;
        // 큐에 더이상 방문할 요소가 없을 경우까지 반복
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < adj[cur].length; i++) {
                if (adj[cur][i] == 1 && !visit[i]) {
                    q.add(i);
                    visit[i] = true;
                }
            }
        }
        // 방문 여부 리턴
        return visit;
    }
}
// 영상 다 보고 나니까 흐름이랑 왜 어떻게 돌아가는지는 알겠는데
// 이걸 계획하고서 짠다는게 진짜...
// 일단 이 글 다시보면 내가 직접 흐름 작성 ㄱㄱ