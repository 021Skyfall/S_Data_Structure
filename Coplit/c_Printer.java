package Coplit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class c_Printer {
    public static void main(String[] args) {
        int bufferSize = 2;
        int capacities = 10;
        int[] documents = new int[]{7, 4, 5, 6};
        System.out.println(queuePrinter(bufferSize,capacities,documents));
    }
    public static int queuePrinter(int bufferSize, int capacities, int[] documents) {
        // 결과를 담을 변수 선언 초기화
        int second = 0;
        // 큐 선언
        Queue<Integer> q = new LinkedList<>();
        //선언한 큐를 bufferSize 만큼 크기 조정 ~> 그 만큼 0을 채워준다고 보면 됨
        for (int i = 0; i < bufferSize; i++) {
            q.add(0);
        }
        // 첫 1초를 기준으로 작성, 배열의 0번 인덱스 요소를 넣어줌
        q.poll();
        q.add(documents[0]);
        // 큐가 가져간 배열 요소를 배열에서 지워줌
        documents = Arrays.copyOfRange(documents,1,documents.length);
        // 문서가 들어 간 것이니 1초가 지남
        second++;

        // 맨 처음 빼고는 반복시켜야함
        // 조건은 큐에 담긴 게 없고, 배열도 비어 있어야 끝나도록
        // 단, 큐에 마지막에 남는건 [0,0] 임 즉, 큐 안의 요소를 전부 더했을때 0이면 false 리턴
        while (q.stream().mapToInt(Integer::intValue).sum() != 0 || documents.length != 0) {
            // 배열에 문서가 남은 경우
            if (documents.length != 0) {
                // 작업중인 문서와 남은 문서 중 가장 처음 문서의 크기가 capacities 보다 큰 경우
                if (q.stream().mapToInt(Integer::intValue).sum() + documents[0] > capacities) {
                    q.poll();
                    // 문서가 제거되고, 남은 대기열의 첫번째 문서를 포함했을 때 수용 가능하면
                    if (q.stream().mapToInt(Integer::intValue).sum() + documents[0] <= capacities) {
                        q.add(documents[0]);
                        documents = Arrays.copyOfRange(documents,1,documents.length);
                        second++;
                    } else { // 문서가 제거되고, 남은 대기열의 첫번째 문서을 포함했을 때 수용이 불가능하면
                        q.add(0);
                        second++;
                    }
                } else { // 작업중인 문서와 남은 문서 중 가장 처음 문서의 크기가 capacities 보다 작은 경우
                    q.poll();
                    q.add(documents[0]);
                    documents = Arrays.copyOfRange(documents,1,documents.length);
                    second++;
                }
            } else { // 대기 중인 문서가 없을 경우
                q.poll();
                q.add(0);
                second++;
            }
        }
        return second;
    }
}
// 몇 초가 걸리는지 return
// 1초에 문서 하나 들어감
// 1초에 앞으로 이동, 다음 작업물 추가 ~> 이때 그 다음 용량에 따라 들어갈 수도 있고 못 들어갈 수도 있고
// 1초에 출력 ~> 이전에 못들어 갔다면 작업물 추가
// 1초에 앞으로 이동, 다음 작업물 추가
// 반복 후
// 배열이 비었을 경우 ~> 초 로 리턴
// 여기서 bufferSize 는 배열의 요소가 들어갈 수 있는 크기
// 각 요소의 수는 문서의 크기
// capacities 는 문서 총 수용량임
// 그러면 queue 선언하고 용량을 정해주는게 좋을듯