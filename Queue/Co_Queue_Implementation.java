package Queue;

import java.util.ArrayList;

public class Co_Queue_Implementation {
    public static void main(String[] args) {
        Solution q = new Solution();

        q.add(1); // [1]
        q.add(2); // [1, 2]
        q.add(3); // [1, 2, 3]
        q.add(4); // [1, 2, 3, 4]
        q.add(5); // [1, 2, 3, 4, 5]

        System.out.println(q.show()); // [1, 2, 3, 4, 5]

        q.poll(); // [2, 3, 4, 5]
        q.poll(); // [3, 4, 5]

        System.out.println(q.show()); // [3, 4, 5];
    }
}
class Solution {
    private ArrayList<Integer> listQueue = new ArrayList<Integer>();

    public void add(Integer data) {
        listQueue.add(data);
    }

    public Integer poll() {
        if(listQueue.size() == 0) {
            return null;
        }else {
            return listQueue.remove(0);
        }
    }

    public int size() {
        return listQueue.size();
    }

    public Integer peek() {
        if(listQueue.size()==0) {
            return null;
        }else {
            return listQueue.get(0);
        }
    }

    public String show() {
        return listQueue.toString();
    }

    public void clear() {
        listQueue.clear();
    }
}
// 이것도 Stack 코플릿 문제와 마찬가지로 개념이 더 중요한 것임
// 비교적 익숙한 list 들로 구현을 해보고 어떤 식이냐를 따지는 것
// 애는 선입선출 개념인 거 유의