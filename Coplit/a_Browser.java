package Coplit;

import java.util.ArrayList;
import java.util.Stack;

public class a_Browser {
    public static void main(String[] args) {
        String[] actions = new String[]{"B", "C", "-1", "D", "A", "-1", "1", "-1", "-1"};
        String start = "A";

        System.out.println(browserStack(actions, start));
    }

    static public ArrayList<Stack> browserStack(String[] actions, String start) {
        Stack<String> prevStack = new Stack<>();
        Stack<String> nextStack = new Stack<>();
        Stack<String> current = new Stack<>();
        ArrayList<Stack> result = new ArrayList<>();

        current.push(start); // current = [A]

        for (int i = 0; i < actions.length; i++) {
            if (actions[i].equals("-1") && !prevStack.empty()) {  // 뒤로가기
                nextStack.push(current.pop());
                current.push(prevStack.pop());
            } else if (actions[i].equals("1") && !nextStack.empty()) { // 앞으로가기
                prevStack.push(current.pop());
                current.push(nextStack.pop());
            } else if (actions[i].equals("1") || actions[i].equals("-1")) { // 1과 -1은 걸렸는데 prev 나 next 가 비어있을 경우 ~> 비활성화
            }else { // 알파뱃이 걸렸을 경우 처리
                prevStack.push(current.pop());
                current.push(actions[i]);
                nextStack.clear(); //새 페이지에 들어왔을 경우라 지워주는게 맞음 ~> 우리가 새페이지에 들어가면 앞선 페이지가 없음 원래
            }
        }
        result.add(prevStack); // 마지막 출력 결과를 스택 타입의 어레이리스트로
        result.add(current);
        result.add(nextStack);
        return result;
    }
}
// 스택이 있음
// 선입 후출
// 기본적으로 브라우저의 앞으로가기 뒤로가기 기능을 배열로 만들어보는 것
// 예를들어 A 를 현재 페이지라고 가정함
// A 기준으로 이전 페이지는 B 다음 페이지는 D 라고 가정함
// 그러면 스택에 들어간 형태는 B A D 일거임
// 그럼 여기서 B를 보고싶다하면 D 는 이미 현재 A 페이지 이기 때문에
// 없는 상태일거고 왜? pop 되서 날아갔다고 봐야함
// 목적은 B를 보는 거니까 A 를 pop 해서 날리고 B 에 도달하면됨
// 근데 B 를 기준으로 다시 앞으로 가고싶다면 pop 된 A가 출력되야함
// 그럼 이걸 또 다시 넣어줘야하는데 그럴 필요 없이
// 또 다른 스택에 저장하면됨
// 예를 들어 우리가 B A D 에서 D 현재 D 페이지에 있고
// 이전으로 돌아가기 해서 B 로 도달하고 싶으면
// pop 해서 A D 를 날려야함
// 그럼 또 다른 스택에는 페이지 하나를 날릴 때 마다 날아간 페이지를 저장해놓아야 다시 돌아갈 수 있음
// Stack 1 = B A D
// Stack 1 = B A    /   Stack 2 = D
// Stack 1 = B      /   Stack 2 = D A
// 이런 로직으로 하되 B 에서 앞으로 가기를 하게되면 Stack 2 에서 다시 후출 해야함
// Stack 2 = D A    / Stack 1 = B
// Stack 2 = D      / Stack 1 = B A
// 이걸 배열로 구현해야하는데
// 현재 페이지는 start 라는 페이지 임
// 이 현재 페이지는 current 스택에 저장해둠
// 배열을 보면 1은 앞으로가기 -1은 뒤로가기임
// 그럼 걍 배열 순회해서 앞으로 가기 뒤로가기 분리해주면됨
// 거기에 만약 앞으로가려는 스택이나 뒤로가려는 스택이 비어있으면 버튼이 비활성화 된거니까 아무일도 일어나면 안됨 ~> 엣지 케이스
// 또한 문제에서 배열의 요소가 1이나 -1 이 아닌 알파뱃이 입력되면 해당 알파뱃을 새 페이지로 인식하라했음
// 즉? 다음 페이지가 있을 수가 없음
// 우리가 새페이지에 들어가면 다음페이지가 있는경우가 어디있음
// 다 한다음에 마지막에 스택에 저장이 어떻게 되었는지 출력하라는 얘기임