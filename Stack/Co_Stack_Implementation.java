package Stack;

import java.util.ArrayList;

public class Co_Stack_Implementation {
    public static void main(String[] args) {
        Solution stack = new Solution();

        stack.push(1); // [1]
        stack.push(2); // [1, 2]
        stack.push(3); // [1, 2, 3]
        stack.push(4); // [1, 2, 3, 4]
        stack.push(5); // [1, 2, 3, 4, 5]

        System.out.println(stack.show()); // [1, 2, 3, 4, 5]

        stack.pop(); // [1, 2, 3, 4]
        stack.pop(); // [1, 2, 3]

        System.out.println(stack.show()); // [1, 2, 3]
        System.out.println(stack.size());
        System.out.println(stack.peek());
    }

    public static class Solution {
        private ArrayList<Integer> listStack = new ArrayList<Integer>();

        public void push(Integer data) {
            listStack.add(data);
        }

        public Integer pop() {
            if (listStack.size() == 0) {
                return null;
            } else {
                return listStack.remove(listStack.size()-1);
            }
        }

        public int size() {
            return listStack.size();
        }

        public Integer peek() {
            if (listStack.size() == 0) {
                return null;
            } else {
                return listStack.get(listStack.size()-1);
            }
        }

        public String show() {
            return listStack.toString();
        }

        public void clear() {
            listStack.clear();
        }
    }
}
// 이건 개념이 중요한 듯 함
// 문제 자체는 크게 어렵거나 한게 없었음
// 그냥 ArrayList 구현이랑 다를게 없거든
// push() 는 그냥 리스트에 데이터 추가하는 거고
// pop() 은 마지막꺼 삭제하면서 해당 요소 리턴하는 거
// size() 는 그냥 그 리스트 크기를 묻는거고
// peek() 은 마지막에 추가한 요소 뭔지 찾는거임
// 사실 상 문제 자체는 그냥 리스트 메소드로 선입후출인 스택을 표현할 수 있느냐 였던거같음