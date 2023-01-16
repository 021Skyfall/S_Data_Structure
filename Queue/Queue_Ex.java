package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Queue_Ex {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        System.out.printf("%s 번 문서 출력 대기중\n",q);
        q.add(2);
        System.out.printf("%s 번 문서 출력 대기중\n",q);
        q.add(3);
        System.out.printf("%s 번 문서 출력 대기중\n",q);
        q.add(4);
        System.out.printf("%s 번 문서 출력 대기중\n",q);

        System.out.println("-".repeat(50));
        System.out.println("출력 시작");
        System.out.println("-".repeat(50));

        System.out.printf("%s 번 문서 출력 완료\n",q.poll());
        System.out.printf("%s 번 문서 대기중\n",q);
        System.out.printf("%s 번 문서 출력 완료\n",q.poll());
        System.out.printf("%s 번 문서 대기중\n",q);
        System.out.printf("%s 번 문서 출력 완료\n",q.poll());
        System.out.printf("%s 번 문서 대기중\n",q);
        System.out.printf("%s 번 문서 출력 완료\n",q.poll());
        System.out.printf("%s 번 문서 대기중\n",q);

        System.out.println("-".repeat(50));
        System.out.println("출력 종료");
        System.out.println("-".repeat(50));
    }
}
// Queue 형 자료구조를 프린트 형식으로 구현해봄