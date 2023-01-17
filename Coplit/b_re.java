package Coplit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class b_re {
    public static void main(String[] args) {
        Integer[] boxes = new Integer[]{5, 1, 4, 6};
        System.out.println(paveBox(boxes));
    }
    public static int paveBox(Integer[] boxes) {
        // 배열을 큐로 전환
        Queue<Integer> q = new LinkedList<>(Arrays.asList(boxes));

        // 비교의 기준, q의 가장 첫 번째 요소 왜? 첫번째 요소가 뒷 요소들 보다 크면 뒷요소도 못 빠져나감
        Integer first = q.poll();
        // 배열 요소가 제거 될때마다 카운트 될 변수 ~> 최소 한 개는 나가기 때문에 초기화 값 1
        int result = 1; // 끊어야 하는 요소가 여러 개 있다면 세어진 개수와 비교한 최댓값 저장해야함
        int count = 1; // 개수 카운트
        // 쉽게 말해 이전에 나간 사람들하고 이번에 나갈 사람들 중 큰 값을 구해야하기 때문에 2개 초기화임

        // 배열 반복
        while (!q.isEmpty()) { // 큐에 읽어올 값이 있는지 확인
            // first 와 비교할 다음 요소를 뽑아야함
            Integer compare = q.poll();
            // 분기 설정
            // 맨 처음 요소가 비교할 요소보다 작을 때 ~> 작은 수는 빠져나가야하니까 끊어주는 것
            if (first < compare) {
                result = Math.max(result,count); // 해당 조건이 될 때까지 first와 count에 저장된 값 중 큰 값을 리턴할 result 에 저장
                count = 1; // 다시 반복해야할 수 있으니 count 는 다시 1로 초기화
                first = compare; // 먼저 나간애들 지우고 다시 반복 시키기 위함
                // 이 단계에서 result 에 이전 횟수가 저장됨
            }
            // 맨 처음 요소가 비교할 요소보다 클 때 ~> 처음 요소가 자기보다 큰 요소를 만날때까지 못 나감
            else {
                count++; // 그 때까지의 요소의 합 ~> 일반적으로 5,3,2,1 이런 식이면 이걸로 해결됨

                // 맨 앞 요소 계속 빼면서 count++ 하다가 앞 조건에 끝까지 안걸리고 q가 비었으면
                if (q.isEmpty()) {
                    result = Math.max(result,count);
                }
                // 개수 추가까지 하고나서 빈배열이면 그 이전의 개수와 비교해서 더 큰 값을 result 에 저장 하고
                // 이제 q 가 비었으니 마지막으로 이전에 저장된 result 와 새로 카운트한 count 를 비교함
                // 그리고서 마찬가지로 while 도 읽어올 값이 없기 때문에 탈출함
            }
        }
        return result;
    }
}
// 다시 한번 기억하자 이건 전체가 나갈 때 제일 많이 나가는 경우의 수임
// 배열을 전부 순회해서 동시에 나가는 경우가 여러번 있다면 그 중에 가장 많이 나갈 때를 묻는 거임
// 그러니까 5,1,2,3,6,1,2,3,4 이런 배열일 때 5,1,2,3 / 6,1,2,3,4 이런식으로 끊어서
// 각각 요소가 제거되는 수를 구하고 그 중 가장 큰 거 리턴하는 거임

// 리스트 풀이는 Coplit_Re 프로젝트에 들어가있음