package Coplit;

public class g_DFS_Barcode {
    public static void main(String[] args) {
        System.out.println(barcode(7));
    }
    public static String barcode(int len) {
        // 1, 2, 3 으로만 이루어진 바코드
        // 인접한 두 개의 수열이 달라야함
        // 만들 수 있는 바코드 중 가장 작은 수를 반환
        // 트리 구조로 표현 가능 ~> 좌측 부터 순회해서 값을 받아오면 가장 작은 수를 반환 조건에 부합
        return aux("",len);
    }
    // 유효성 검사 메소드 ~> 인접한 두 개의 수열이 달라야함 // 아래의 합쳐진 바코드를 매개변수로 받음
    public static boolean isValid(String str) {
        // index 관리를 편하게 하기 위해 reverse 함
        StringBuffer sb = new StringBuffer(str);
        String reverse = sb.reverse().toString();

        // 인접한 두 개의 부분 수열이 동일한지 확인해야함
        // 최대 길이의 절반 만큼만 두 개의 부분 수열이 가능
        int half = str.length()/2;

        // 반으로 나눈 길이 만큼 순회를 돌면서
        // 걸러줄 조건 정의
        // 숫자가 하나만 있을 경우 true 이기 때문에 반복 돌아갈 i = 1 로 설정 / 절반 길이"까지" 돌아가야함 ~> 절반으로 잘린 문자열 전체 필요
        for (int i = 1; i <= half; i++) {
            // 조건은 받아온 인자를 뒤집은 문자열 중 절반 자른 앞뒤 비교해서 같으면 반복 안되는 조건이니
            // false 리턴
            // ex) 2121 -> 21/21 -> 앞 : 21 뒤 : 21 -> X
            // ex) 11 -> 1 / 1 -> 앞 : 1 뒤 : 1 -> X
            // 이런 경우 외에 212121 이런 경우는 애초부터 들어올 수가 없음 왜?
            // 제작 과정 중에서 매번 현재 메소드로 들어와서 비교하기 때문에
            if (reverse.substring(0,i).equals(reverse.substring(i,i+i)))
                return false;
        }
        return true;
    }
    // 바코드 생성 메소드 ~> 기본적으로 1,2,3으로만 생성이 가능하기 때문에 "123" 을 갖고 반복해서 더해줌 / 재귀 사용
    public static String aux(String str, int len) {
        // 바코드 표본
        String bar = "123";
        // 생성된 바코드의 길이와 매개변수 len (길이) 가 같아지면 Base Case
        if (str.length() == len) return str;
        //가장 작은 수를 찾고 있으므로 표본에서 1,2,3 순서대로 검토후 합체
        for (int i = 0; i < bar.length(); i++) {
            // 1,2,3 하나씩 뽑아서 합체 시킴
            String cur = str + bar.charAt(i);
            // 합체된 객체가 인접한 두 개의 수열이 다른지 체크
            if (isValid(cur)) {
                // 만약 유효하다면 넘기고서 재귀호출로 다음 길이의 객체 붙여줌
                // 재귀를 호출하기 때문에 낮은 값부터 붙여줄 수 있음
                String founded = aux(cur,len);
                // 7 길이를 가진 바코드는 123121 형태로 나오고
                // 여기서 8 길이를 가진 바코드를 생성하려면 1,2,3 뭘 갖다 붙여도 유효하지 않은 값이 되어버림
                // 그렇기 때문에 이전 숫자로 돌아가서 다시 그 다음 숫자부터 재귀를 돌려서 다음으로 유효한 값을 붙여주어야 함
                if (founded != null) return founded;
            }
        }
        // 재귀호출 중 현재 문자열에서 1,2,3 뭘 붙여도 유효하지 않은 경우
        return null;
    }
}
/*
* 트리 형태로 표현하면
*                                       1
*                             2                     3
*                       1           3        1             2
*               2           3   1       22      3       1       3
*  이런식으로 표현 가능하고
*  위에 작성한 것과 같은 로직이 좌측부터 검색하고서 값을 붙여주는 것 ~> 좌측부터? 작은 값부터 ~> 트리 구조는 좌식 노드가 작은 값
*  이런 탐색 법이 DFS 임
 */
