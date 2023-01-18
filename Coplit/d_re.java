package Coplit;

import java.util.Arrays;

public class d_re {
    public static void main(String[] args) {
        int[][] x = new int[][] {
                {0, 3, 0},
                {0, 2, 0},
                {1, 3, 0},
                {2, 1, 0}
        };
        System.out.println(Arrays.deepToString(createMatrix(x)));
    }
    public static int[][] createMatrix(int[][] edges) {
        // 0 , 3 , 0 에서
        // 0 번 인덱스 = 출발 버텍스
        // 1 번 인덱스 = 도착 버텍스
        // 2 번 인덱스 = 쌍방인지 일방인지
        // 각 안쪽 배열 요소 중 가장 큰 값까지 버텍스 존재

        // 1. 그래프 크기 지정
        // 버텍스 값 저장할 변수 선언 후 0으로 초기화
        int vMax = 0;
        // 안쪽 배열 순회하면서 가장 큰 버텍스 요소 빼와서 vMax 에 저장
        for (int[] i : edges) {
            for (int j : i) {
                if (vMax < j) vMax = j;
            }
        }
        // 1-1. 표로 표현할 이중배열 하나 더 생성해서 해당 크기는 표 형태이니 가장 큰 요소의 크기 * 크기 만큼 크기 지정
        int[][] graph = new int[vMax + 1][vMax + 1];

        // 2. 인자로 받아온 배열의 바깥 배열 순회하면서 고정된 안쪽 배열 값을 뽑아옴
        // 이 작업은 안쪽 배열의 각 인덱스 마다 특정한 조건이 있기 때문에 가능함 (맨 위에 작성)
        for (int[] i : edges) {
            // 출발 버텍스
            int from = i[0];
            // 도착 버텍스
            int to = i[1];
            // 쌍방인지 일방인지
            int dir = i[2];

            // 통행의 조건으로 분기
            // 가중치는 따로 설정되어 있지 않기 때문에 0과 1로 저장함
            // int 배열은 기본 저장값이 0이라 0 안 넣어줘도 됨

            // 일방통행일 경우
            if (dir == 0) {
                // 일방 통행이기 때문에 길이 하나 뿐임
                graph[from][to] = 1;
                // 쌍방통행일 경우
            } else if (dir == 1) {
                // 쌍방 통행이라 길이 각각 하나씩 있음
                graph[from][to] = 1;
                graph[to][from] = 1;
            }
        }
        return graph;
    }
}
