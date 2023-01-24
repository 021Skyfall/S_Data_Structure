package Coplit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class f_re {
    public static void main(String[] args) {
        int result = connectedVertices(new int[][]{
                {0, 1},
                {2, 3},
                {4, 5},
        });
        System.out.println(result);
    }
    private static int connectedVertices(int[][] edges) {
        // 최대 버텍스 찾기
        int max = 0;
        // 바깥 배열을 돌면서
        for (int i = 0; i < edges.length; i++) {
            // 각 바깥 배열의 인덱스 안의 안쪽 배열의 인덱스 순회
            for (int j = 0; j < edges[i].length; j++) {
                // 만약 max 가 안쪽 배열을 하나씩 순회했을 때의 요소보다 작다면
                // max 를 해당 값으로 초기화
                if (max < edges[i][j]) max = edges[i][j];
            }
        }
        // 최대 버택스의 크기 +1 만큼의 그래프 생성 ~> 0 부터 시작
        int[][] graph = new int[max+1][max+1];
        // 그래프 그리기 ~> 원본 배열에서 연결된 정점을 표현
        // 입력 받은 배열의 크기 만큼 돌면서
        for (int i = 0; i < edges.length; i++) {
            // 출발 버텍스를 담고
            int from = edges[i][0];
            // 도착 버텍스를 담음
            int to = edges[i][1];
            // 해당 문제는 무향이라는 조건이 있어서 무향을 표현
            // 해당 버텍스에 맞는 인덱스에 무가중치 1을 입력
            graph[from][to] = 1;
            graph[to][from] = 1;
        }
        // 도출된 그래프
//        [[0, 1, 0, 0, 0, 0],
//        [1, 0, 0, 0, 0, 0],
//        [0, 0, 0, 1, 0, 0],
//        [0, 0, 1, 0, 0, 0],
//        [0, 0, 0, 0, 0, 1],
//        [0, 0, 0, 0, 1, 0]]

        // bfs 범위 우선 탐색을 실행하기 위해
        // 방문 여부를 체크하고 담을 배열 생성
        boolean[] visit = new boolean[max+1];
        // 생성된 시점에서 visit 배열은 초기값 [false,false,false,false,false,false]
        // 컴포넌트(연결된 정점)의 개수를 파악하기 위한 변수 선언
        int count = 0;
//        // 시작 지점을 큐에 넣고 방문할때마다 시작지점이 달라지게 설정
//        Queue<Integer> q = new LinkedList<>();
//        // 가장 처음 순회할 0 입력
//        q.add(0);
//        // 방문여부 배열의 0번째 인덱스는 이미 현재 있는 위치이기 때문에 방문 확인해줌
//        visit[0] = true;
        // 끝어진 길 이후에도 계속 탐색해야하기 때문에 메소드를 하나 더 생성하겠음

        // 방문 여부 배열의 끝까지 순회
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]){
                count++;
                // 해당 메소드의 로직을 따라 visit 배열이 바뀜
                visit = bfs(graph,i,visit);
            }
        }
        return count;
    }
    // 그래프를 그대로 입력 받고, 버택스는 visit 을 순회하면 0~5 니까 이게 각각의 버택스가 됨
    // 그리고 방문 여부 배열은 현재 메소드가 돌아가면서 값이 바뀌고
    // 해당 작업이 위의 방문 여부 배열 순회하면서 계속 실행됨
    private static boolean[] bfs (int[][] graph, int vertex, boolean[] visit) {
        // 다시 큐를 선언하고 시작 지점 삽입 후
        // 반복될때마다 시작 버택스 변경하기 위함
        Queue<Integer> q = new LinkedList<>();
        q.add(vertex);
        // 큐가 빌 때까지 반복 ~> 방문할 버텍스가 더이상 없는 경우임
        while (!q.isEmpty()) {
            // q에 들어있는 시작 버택스를 담기 위한 변수 선언
            int cur = q.poll();
            // 받아온 그래프의 시작 버택스 인덱스에 해당하는 안쪽 배열을 순회
            // 예를 들어 순회는 0~5 회 반복이 될 것이기 때문에
            // 6*6 인 그래프의 바깥배열 도달, 안쪽 배열 순회 이런식임
            // [0, 1, 0, 0, 0, 0] -> 바깥 0번째 인덱스
            // 각 요소의 인덱스는 0~5 까지 -> 이 요소들을 각각 순회
            for (int i = 0; i < graph[cur].length; i++){
                // 만약 방문하지 않았거나 graph[cur] 의 안쪽 배열의 i 번째 요소가 1이라면
                // 해당 i 번째 요소에서 다음 요소로 가는 길이 있는 것이기 때문에
                // q에 도착한 버텍스를 저장하고 방문 여부를 true로 체크
                if (!visit[i] && graph[cur][i] == 1) {
                    q.add(i);
                    visit[i] = true;
                }
            }
        }
        // 이후 방문 여부 리턴
        return visit;
    }
}
/* 흐름
*          {0, 1},
           {2, 3},
           {4, 5},

        [0, 1, 0, 0, 0, 0],
        [1, 0, 0, 0, 0, 0],
        [0, 0, 0, 1, 0, 0],
        [0, 0, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1],
        [0, 0, 0, 0, 1, 0]
* 위의 배열을 받아와서
* 아래의 그래프가 만들어짐
*
* visit [false,false,false,false,false,false]
* count = 0
*
* for 0
* if true
* count = 1
* visit = bfs
*
* bfs
* q = 0
* visit [true,false,false,false,false,false]
* while
* cur = 0 / q = []
* for 0
* if X
* for 1
* if O
* q = [1]
* visit [true,true,false,false,false,false]
* for 2 ~ 5
* if X
* while
* cur = 1 / q = []
* for 0~5
* if X
* q = [] while X
* return [true,true,false,false,false,false]
*
* 돌아가서
* visit = [true,true,false,false,false,false]
* for 1
* if X
* for 2
* if O
* count = 2
* visit = bfs
*
* bfs
* q = 2
* visit [true,true,true,false,false,false]
* while 부터 반복
 */