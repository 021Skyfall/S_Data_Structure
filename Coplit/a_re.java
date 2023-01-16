package Coplit;

import java.util.ArrayList;
import java.util.Stack;

public class a_re {
    public static void main(String[] args) {
        String[] actions = new String[]{"B", "C", "-1", "D", "A", "-1", "1", "-1", "-1"};
        String start = "A";
        System.out.println(browserStack(actions,start));
    }
    public static ArrayList<Stack> browserStack(String[] actions, String start) {
        Stack<String> prevStack = new Stack<>();
        Stack<String> nextStack = new Stack<>();
        Stack<String> current = new Stack<>();
        ArrayList<Stack> result = new ArrayList<>();
        // 현재 페이지 설정
        current.push(start);

        for (String str : actions) {
            // -1 이 걸린 경우 && 이전 스택에 값이 있는 경우 ~> 뒤로가기 위해서
            if (str.equals("-1") && !prevStack.empty()) {
                // next 에 현재 페이지 push
                nextStack.push(current.pop());
                // prev 에 있는 페이지를 현재 페이지로 설정
                current.push(prevStack.pop());
            }
            // 1 이 걸린 경우 && 다음 스택에 값이 있는 경우 ~> 앞으로가기 위해서
            else if (str.equals("1") && !nextStack.empty()) {
                prevStack.push(current.pop());
                current.push(nextStack.pop());
                // -1 과 로직 같음
            }
            // 위에서 각 스택이 비었지만 -1 혹은 1 이 걸린 경우 ~> 해당 기능이 비활성화 된 것
            else if (str.equals("-1") || str.equals("1")) {}
            // 알파뱃, 즉, 페이지가 들어온 경우 ~> 새 페이지에 들어갔다고 가정
            // 새 페이지에 들어갔으면 현재 페이지는 이전 페이지가 되고
            // 다음 페이지는 비어있을 것
            else {
                prevStack.push(current.pop());
                current.push(str);
                nextStack.clear();
            }
        }
        // for 문 탈출 후 합쳐줄 스택 타입 어레이리스트 ~> result 사용
        result.add(prevStack);
        result.add(current);
        result.add(nextStack);
        return result;
    }
}
