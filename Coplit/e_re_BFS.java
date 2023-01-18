package Coplit;

import java.util.LinkedList;
import java.util.Queue;

public class e_re_BFS {
    public static void main(String[] args) {
        boolean result = getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0,
                2);
        System.out.println(result);
    }
    public static boolean getDirections(int[][] matrix, int from, int to) {
        // 이 문제는 특정 버텍스 from 에서 to 까지 가는 길이 있는가를 판단하는 문제임
        // 엣지가 쭉 연결되서 이동했을 때 도달점이 to 면 true 고 끝까지 갔는데 없으면 false
        // BFS 와 DFS 탐색이 가능함
        // 보통 BFS 는 간 길을 체크하고 이전 노드는 탐색하지 않기 때문에 큐로 유동적인 탐색을 보통함
        // 반대로 DFS 는 전체를 다 탐색하기 때문에 재귀로 보통 표현함
        // 현재는 BFS 를 사용

        // 1. BFS 를 위한 큐 생성
        Queue<Integer> q = new LinkedList<>();

        // 2. Q 에 탐색의 기준점이 될 시작점 add
        q.add(from);

        // 3. 방문 체크를 위한 1차원 배열 생성
        // 3-1. 크기는 당연히 탐색할 그래프 크기만큼
        boolean[] visit = new boolean[matrix.length];

        // 4. 가장 먼저 들어감 from 은 탐색하지 않음
        // 왜?
        visit[from] = true;

        // 기본적으로 큐의 객체를 전부 소모할 때 까지 반복
        while (!q.isEmpty()) {
            // 안쪽 배열을 순회하고 바깥 배열의 인덱스를 옮겨야 하기 때문에
            // 큐의 객체를 변수에 저장해서 사용해야함
            // 왜? 큐가 계속 바뀌어야 하니까
            int cur = q.poll();

            // 분기를 설정
            // 안쪽 배열을 순회
            for (int i = 0; i < matrix[cur].length; i++) {
                // 해당 자리의 값이 1이고 안쪽 배열 각 요소를 방문하지 않았을 경우
                // 왜? 값이 1이면 다음으로 이동 가능한 거 , 이미 방문한데는 없었겟지? 그러니까 패스
                // 이 경우 1이면 다음 으로 넘어가서 순회 & 해당 인덱스 방문 체크 해야함
                if (matrix[cur][i] == 1 && !visit[i]) {
                    q.add(i);
                    visit[i] = true;
                }
            }

            // 순회를 마치고 cur 과 to 가 같아지면 도달 가능한 것
            if (cur == to) return true;

        }
        return false;
    }
}
// 예를 들어 from = 0 이고 to = 2 임
// visit 은 [true,false,false,false] 일 거임
// while 들어가서 cur = 0
// for 순회는 4번임 0번째 안쪽 배열 크기가 4니까
// 이 때 matrix 의 0 번째 배열 부터 순회함
// if 조건에 안걸림
// 다시 for 순회 matrix 0번째 인덱스 배열의 1번째 인덱스는 1임
// 그리고 이때 visit 은 [true,false,false,false] 임  <<<<<<<<< 이거 중요
// 조건에 부합함
// 안의 조건 들어감 ~> 조건값 적용
// 근데 아직 for 문을 탈출 한건 아니기 때문에
// 일단 q 에 1 들어가고 visit [true,true,false,false] 로만 바뀌고
// 나머지 두번 반복함
// 끝나고 다시 cur 자리에 1 들어감
// for 문 돌아가서 matrix 의 1번째 인덱스 배열 순회
// 근데 여기서 조건에 matrix 1번째 인덱스 안의 0번째, 1번째 요소는 쳐다도 안봄
// 왜? visit 이 [true,true,false,false] 니까
// 일단 돌아는 가는데 아무일도 안일어남
// 그러니까 결국 이전에 방문한 0번, 1번 인덱스는 아예 패스해버리고
// 바로 2번째 인덱스 부터 탐색하는 거임
// 이게 바로 BFS 다

// 애초에 그래프로 표현하면 다음 길은 다음 인덱스에 표시 될 거니까 가능하네