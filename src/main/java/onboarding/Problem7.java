package onboarding;

import java.util.Collections;
import java.util.List;

public class Problem7 {
    /*
    - 문제 요구사항 정리 및 해결방법
    1. 주어진 규칙에 따라 추천 점수가 높은 순서대로 최대 5명까지 리턴(추천 점수가 0일 경우 추천하지 않음)
        - 규칙 1번(사용자와 함께 아는 친구의 수 = 10점)을 해결하기 위해, 주어진 friends 리스트를 통해 Map 객체를 만들어 Key(특정 인물), Value(특정 인물과 관계있는 인물들) 구조로 매핑
        - 만든 Map 객체를 통해서 유저가 아는 친구와 관계가 있는 인물들 점수 10점씩 추가
        - visitors중 유저와 친구 관계가 아닌 인물들의 점수 1점씩 추가
     */
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        return answer;
    }
}
