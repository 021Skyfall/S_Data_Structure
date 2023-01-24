package Coplit;

public class g_re {
    public static void main(String[] args) {
        System.out.println(barcode(8));
    }
    private static String barcode(int len) {
        // 1, 2, 3 으로만 이루어진 바코드 생성
        // 인접한 수열이 달라야하고
        // 가장 작은 수를 바코드로 반환해야함 ~> 트리구조 형태로 좌식노드 부터 탐색해서 리턴
        // 유효성 검사는 새로운 메소드를 생성함

        // 생성된 바코드를 담기 위한 변수 선언 후 빈 문자열로 초기화
        return aux("",len);
    }
    private static String aux (String result, int len) {
        // 바코드는 1,2,3 으로만 이루어져야 하기 때문에 각각 charAt 으로 뜯어올 것
        String bar = "123";

        // 재귀호출 base case
        // 입력된 길이 만큼의 바코드가 생성되었으면 리턴
        if (result.length() == len) return result;

        // 가장 작은 수를 붙여야하기 때문에 bar 변수의 123 순서대로 순회
        for (int i = 0; i < bar.length(); i++) {
            // bar 변수의 123 문자열을 하나씩 뜯어서 result 에 저장
            // 왜? 1,2,3 길이만큼 순회 3회를 하면서
            // 1,2,3 중 하나를 붙여줘야하기 때문
            String cur = result + bar.charAt(i);

            // 다음으로 붙는 숫자가 유효한지 검사
            // 인접한 수열이 달라야함
            if (isValid(cur)) {
                // 유효하다면 재귀 호출로 해당 문자를 갖고가고 다음 캐릭터를 붙여줌
                // 아 여기서 재귀호출이 이어져야하기 때문에 재귀호출용 메소드 생성하는 구나
                String founded = aux(cur,len);
                // 만약 다음 숫자가 더 붙었는데 1,2,3 중 어떤게 와도 유효하지 않는다면
                // 그 이전으로 돌아가서 다시 붙여주어야함
                // 보통 1이 붙었다치면 2,3 중에 가능한 수가 있을거임
                if (founded != null) return founded;
            }
        }
        // 재귀호출 돌다가 뭘 붙어도 유효하지 않은 경우에는 null 리턴
        return null;
    }
    private static boolean isValid(String str) {
        // substring 사용 시 인덱스의 관리를 편하게 하기위해 받아온 str을 뒤집어줌
        // ~> 뒤집어서 맨 앞부터 점차적으로 비교 ~> 맨 뒤에서 비교하는거나 마찬가지
        StringBuffer sb = new StringBuffer(str);
        String reverse = sb.reverse().toString();
        // 받아온 문자열을 절반으로 자름
        int half = str.length()/2;

        // 반으로 나눈 길이 만큼 순회를 돌면서 걸러줌
        // 단 마지막 숫자까지 비교를 해야하기 때문에 <=
        // 또한 숫자가 하나만 있는 경우는 지나가야하기 때문에 i = 1
        for (int i = 1; i <= half; i++) {
            // 자른 문자열이 각각 같으면 인접한 수열이 같은 경우라 안됨
            // 이럴 땐 false 리턴해야됨
            if (reverse.substring(0,i).equals(reverse.substring(i,i+i)))
                return false;
        }
        // 나머지는 true
        return true;
    }
}
/* 흐름
* len = 8 가정
*
* aux("",8)
* if X
* for 0
* cur = "" + 1 = 1
* if (isValid(1))
*
* isValid(1)
* reverse = 1
* half = 0
* for X
* return true
*
* if O
* founded = aux(1,8)
*
* aux(1,8)
* if X
* for 0
* cur = 1 + 1
* if(isValid(11))
*
* isValid(11)
* reverse = 11
* half = 1
* for 1
* if 1 = 1? O
* return false
*
* if X
* for 1
* cur = 1 + 2
* if(isValid(12))
* ~
* 7회째 = 1213121
* 8회째
* cur = 1213121 + 1
* if(isValid(12131211))
*
* isValid(12131211)
* reverse = 11213121
* half = 4
* for 1
* if (1 = 1) return false
*
* cur = 1213121 + 2
* if(isValid(12131212))
*
* isValid(12131212)
* reverse = 21213121
* half = 4
* for 1
* if (2 == 1) X
* for 2
* if (21 == 21) return false
*
* cur = 1213121 + 3
* if(isValid(12131213))
* 이때도 isValid false
* 이 때의 재귀는 null 갖고 돌아감
* 다시 돌아오면
* 1213121 + null 의 형태
* 이때 str 혹은 founded 를 리턴하지 않기 때문에 if 로직이 작동하지 않고 다음 for 문이 돌아감
* 즉 해당 재귀 시점까지의 121312 + 1 을 갖고 다시 돌아가는 거
* 다음 for 문이면 cur = 121312 + 2 부터 다시 유효성검사, 재귀호출 이뤄지고
* 마지막으로 합쳐진 cur 이 재귀에 들어가서 len 의 길이와 같으면 그거 그대로 리턴
 */