package Coplit;

import java.util.*;

public class b_Boxed {
    public static void main(String[] args) {
        Integer[] boxes = new Integer[]{5,1,2,3,4,3,2,3,4,2,1,2,6};
        System.out.println(paveBox(boxes));
    }

    public static int paveBox(Integer[] boxes) {
//        LinkedList<Integer> arr = new LinkedList<Integer>(Arrays.asList(boxes));
//        Integer x = arr.remove(0);
//        int count = 0;
//
//        for (int i : arr) {
//            count++;
//            if (x <  i) {
//                break;
//            }
//        }
//        return count;

//            ArrayList<Integer> answer = new ArrayList<>();
//            List<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(boxes));
//
//            while (arrayList.size() > 0) { // 만약 찾았다면, 해당 key를 answer에 넣고, ArrayList에서 그만큼 제외합니다.
//                for(int i = 0; i < arrayList.size(); i++) {
//                    if(arrayList.get(i) > arrayList.get(0)) {
//                        answer.add(i);
//                        arrayList = arrayList.subList(i , arrayList.size());
//                        break;
//                    }
//                    // 만약 찾지 못했다면 answer에 arrayList 크기를 넣은 후, arrayList 내부의 요소를 전부 삭제합니다.
//                    if(i == arrayList.size() - 1) {
//                        answer.add(arrayList.size());
//                        arrayList.clear();
//                    }
//                }
//            }
//            return answer.stream().max(Integer::compare).orElse(-1);


           Queue<Integer> queue = new LinkedList<>(Arrays.asList(boxes));

           Integer first = queue.poll();
           int result = 1;
           int count = 1;

           while (queue.peek() != null) {
               Integer compare = queue.poll();
               if (first < compare) {
                   result = Math.max(result,count);
                   count = 1;
                   first = compare;
               } else {
                   count++;

                   if (queue.isEmpty()) {
                       result = Math.max(result,count);
                   }
               }
           }
           return result;
    }
}
// 루프해서 요소 하나씩 계속 빼주고
// 맨 처음에 뺀거랑 다음에 빠지는 것들이랑 비교해서
// 작으면 새로운 리스트에 담고
// 크면 그냥 break 해서 나오고
// 해당  리스트의 크기 return 어떰
// 해봤는데 테스트 통과가 안되네
// 안될 이유가 없는데...
// 결국 몇시간 보다가 리퍼런스 긁어옴 ...

// 일단 뜯어보면
// boxes 를 받아온 리스트가 빈 리스트가 될 떄까지 반복문 열고
// 중첩 for 문으로 각 요소 순회
// 만약 0번 째 인덱스 값보다 큰 요소가 있다면
// answer 에 해당 인덱스 를 넣어줌
// 그리고서 sublist 로 그 뒷부분 날리고 for 문 닫고 size 가 0이 되니 그대로 while 도 종료
// 만약 다 끝까지 다 돌아갔으면
// 전체 다 동시에 나간다는 뜻이니
// answer 에 들어온 배열 사이즈를 넣어주면 됨
// 이후 arrayList 싹 지워주고 while 문도 종료
// 마지막으로 answer 의

// 도저히 모르겠다 스트림으로 뭘 비교하는지도 모르겠고 왜 orElse 로 -1 들어갔는지
// 그리고 첫번째 if 문에서 subList 로 날리면 그대로 남아서 또 while 안에서 계속 돌텐데
// 두 번째 if 문에서 끝까지 돌렸을 때 안나오면 해당 리스트 사이즈가 값이 되는건 알겠는데
// 돌아버리겠네 진짜

// 아 문제 수십번 다시 읽어보고 출력 값 보고 왜 저게 안되지 생각하고 하다가
// 결국 queue 로 푼 문제 보고 깨닳음
// 이거 앞이든 뒤든 결국 나갈 수 있는 인원 중에서 최대로 몇명 까지 나가냐는 소리였음 하...
// 난 무조건 앞에 나가는 인원이 몇명인지 물어보는 건 줄 알았는데 그게 아니라
// 그냥 한꺼번에 나갈 때 값 중 최대 값 찾는 문제였다 와 진짜 화나네
// 몇시간 동안 고민하면서 왜 안되지 왜 안되지 이러고 있었는데 아
