package Coplit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class c_re {
    public static void main(String[] args) {
        int bufferSize = 2;
        int capacities = 10;
        int[] documents = new int[]{7, 4, 5, 6};
        System.out.println(queuePrinter(bufferSize,capacities,documents));
    }
    public static int queuePrinter(int bufferSize, int capacities, int[] documents) {
        //가장 먼저 큐 생성 ~> 큐는 추상클래스라 보통 LinkedList 로 참조해서 생성함
        Queue<Integer> q = new LinkedList<>();
        // 결과를 담을 변수 선언 후 0으로 초기화
        int second = 0;
        // 큐에 bufferSize 만큼 자리를 만들어 줘야함 ~> 들어가는 값은 0을 할당
        // 왜? 배열 요소가 들어가서 bufferSize 만큼 앞으로 간다음 빠져나가는 과정을 1초라고 계산 할거니까
        for (int i = 0; i < bufferSize; i ++) {
            q.add(0);
        }

        // 첫 1초를 작성해줌 ~> 돌리기 전 기준을 정하기 위함
        q.poll(); // 원래 들어있던 0을 빼내고
        q.add(documents[0]); // 배열의 0번째 인덱스 추가
        // 배열에서 가져올 값이 중복되지 않도록 배열의 값을 지워줌
        documents = Arrays.copyOfRange(documents,1,documents.length);
        // 배열의 요소가 큐에 들어가는 작업은 1초를 소모함
        second++;
        // 이 일련의 과정을 조건을 분기해 다음 요소가 들어갈 수 있는지 없는지 판단 후 반복
        // ~> 첫번째 요소는 무조건 들어가지만 다음은 그렇지 않을 수 있음
        // q 요소들을 순회해서 요소들의 합이 0 이거나 배열이 비어버리면 빠져나와야함 ~> 더이상 돌릴값이 없는 경우임
        while (q.stream().reduce(0,Integer::sum) != 0 || documents.length != 0) {
            // 배열에 문서가 남은 경우
            if (documents.length != 0) {
                // 작업 중인 문서와 배열에서 얻어올 문서의 합이 capacities 보다 큰 경우
                if (q.stream().reduce(0,Integer::sum) + documents[0] > capacities) {
                    q.poll();
                    // 작업 중인 문서가 빠져나가고 나서 다음 배열 요소가 들어올 수 있다면
                    if (q.stream().reduce(0,Integer::sum) + documents[0] <= capacities) {
                        q.add(documents[0]);
                        documents = Arrays.copyOfRange(documents,1,documents.length);
                        second++;
                    }
                    // 작업 중인 문서가 빠져나가고 나서 다음 배열 요소가 들어올 수 없다면
                    else{
                        q.add(0);
                        second++;
                    }
                }
                // 작업 중인 문서와 배열에서 얻어올 문서의 합이 capacities 보다 작은 경우
                else {
                    q.poll();
                    q.add(documents[0]);
                    documents = Arrays.copyOfRange(documents,1,documents.length);
                    second++;
                }
            }
            // 배열에 문서가 없는 경우
            else {
                q.poll(); // 마지막 문서 작업 끝
                q.add(0); // 다시 0 넣어줌
                second++; // 마지막 작업 소요 시간
            }
        }
        return second;
    }
}
// 맨 처음에 bufferSize 만큼 큐의 용량을 설정해려면 각 자리에 0을 넣어야함
// 0을 넣는 이유는 문서를 받아와서 앞으로 옮기고 하는 과정에서 count 해야하기 때문임
// 그 다음 각 배열 요소의 숫자는 capacities 를 잡아먹음
// 이전에 큐에 들어간 배열의 요소와 다음에 들어올 배열 요소의 크기를 더해서 capacities 보다 크면
// 다음 요소가 이전 요소 출력될 때 까지 들어가지 못함
// 그 외에는 들어갈 수 있음
// 각 과정에서 count++ 하고
// 마지막에 count 를 리턴하면 처리에 걸리는 시간이 됨