package Coplit;

import java.util.LinkedList;
import java.util.Queue;

public class e_Graph_Direction {
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
        // 일단 계속 빼고 추가하고 빼고 추가하고 해야하니 큐로 시작
        Queue<Integer> q = new LinkedList<>();

        // q에 시작점 저장
        q.add(from);

        // from 이 들어갔었는지 안갔었는지 판단하기 위한 1차원 배열 생성
        // 크기는 버텍스 만큼 / 기본 false
        boolean[] visit = new boolean[matrix.length];
        // from 자리는 무조건 true
        // 현재 첫번째 버텍스에 있다는거랑 마찬가지
        visit[from] = true; // 이거 근데 셀프 루프 하는 애가 있으면 이게 들어가면 안됨
        // 근데 문제에는 안써있네 그냥 없다고 가정을 하는건가
        // 아 이게 DFS 때문이라 설정을 해주어야하는거고
        // 맨처음은 순회를 안하겠다는 의미임
        // 이 부분 다시

        // q가 전부 순회했을 때 즉, q가 빌때까지 반복
        while (!q.isEmpty()) {
            // 최근 들른 위치를 current 로 선언하고 q의 가장 처음 요소로 초기화
            int current = q.poll();
            // 버텍스의 각 요소 순회 방문을 하지 않았고, 해당 자리의 값이 1 이면 해당 인덱스 저장 후 재순회
            for (int i = 0; i < matrix[current].length; i++) {
            if (matrix[current][i] == 1 && !visit[i]) {
                    q.add(i);
                    visit[i] = true;
                }
            }
            // current 가 to 와 같아지면 도달했다는 얘기니까 true 리턴
            if (current == to) return true;
        }
        return false;
    }
}
// 이 문제는 그냥 갈 수 있는지 물어보는 거임
// 직통 엣지가 있는지 묻는게 아니라
// 그러니까 예를들어 0 버텍스에서 3으로 가는 길이 있느냐 했을 때
// 현재 메인 메소드 에 있는 인접행렬로 판단하면
// 0 -> 1 가는길 / 1 -> 2 가는길 / 2 -> 3 가는길이 있기 때문에
// 결국 합쳐보면 0 -> 3 가는 길이 있어서 true 임
// 그럼 문제 풀이는
// from 과 to 를 받아서
// from 에서 시작해서 순회 돌리고 도달할 수 있는 버텍스 저장
// 저장된 버텍스에서 다음 버텍스로 가본 후 저장
// 저장된 버텍스에서 또 다음 버텍스로 가본 후 저장
// 이렇게 쭉해서 다음 버텍스 = to 가 된다면 true
// 끝까지 돌렸는데 to에 해당하는 버텍스가 없다면 false

// 나는 이거 그냥 스위치 넣어서 본데는 안보고 더 효율적으로 하려는건줄 알았는데
// 이게 BFS 였네
// 들른데 안들르는거