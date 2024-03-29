# 미션 - 숫자 야구
---

## 🚀 기능 요구 사항 정리

기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임
아래의 1번부터 6번순으로 애플리케이션 진행
1. “숫자 야구 게임을 시작합니다.” 출력
2. 랜덤한 3자리의 수(상대방) 생성
3. 사용자의 수 입력
    - 만약, 사용자가 잘못된 값을 입력할 경우, `IllegalArgumentException`
      을 발생시킨 후 애플리케이션은 종료
4. 상대방(컴퓨터)의 수와 비교해서 볼, 스트라이크의 수 반환
5. 반환된 볼, 스트라이크 수에 따라 아래와 같이 진행
    - 0볼, 0 스트라이크인 경우 : “낫싱” 출력 후 3번부터 다시 진행
    - m볼, n 스트라이크(m과 n은 0~2의 값, m과 n이 모두 0이 아닐 경우)인 경우 : 아래의 조건중 맞는 경우로 진행
        - m이 0이고 n이 0이 아닌 경우 : “n스트라이크” 출력 후 3번부터 다시 진행
        - m이 0이 아니고 n이 0이 경우 : “m볼” 출력 후 3번부터 다시 진행
        - m과 n이 0이 아닌 경우 : "m볼 n스트라이크" 출력 후 3번부터 다시 진행
    - 3 스트라이크인 경우 : “3스트라이크” 출력 후, “3개의 숫자를 모두 맞히셨습니다! 게임종료” 출력
6. 입력된 값에 따라 애플리케이션을 끝낼건지, 2번부터 다시 실행할 건지 선택
    - 1 입력 : 2번부터 다시 실행
    - 2 입력 : "게임 종료" 출력 후, 애플리케이션 종료
---
## 예외사항 처리
- 예외사항
  - 입력된 수가 정수가 아닐 때
  - 입력된 수가 비어있을 때
  - 입력된 수가 정해진 자리수를 넘어갈 때
  - 입력된 수가 정해진 범위를 넘을 때
- 이러한 예외사항 발생시 IllegalArgumentException 발생

## 대략적인 코딩 방법
- 이번 주차에서는 문제 자체보다는 문제를 어떻게 이해한 것을 정리하고 이를 수립하는 과정에 초점을 뒀음
- 먼저, 기능 요구사항을 상세히 정리한 다음 순서에 맞게 메서드를 정의하였음
- 그리고, 이러한 메서드를 선언할 때 네이밍에 대해 시간을 많이 사용하였음
- 이러한 방식으로 진행하니 문제들의 기능사항들 간의 혼동이 많이 줄었음
- 함수를 작성할 때, 가능하면 하나의 함수가 하나의 기능을 하도록 코딩하였음
- 이러한 방식을 통해서, 테스트할 때 매우 편하였음

## 그밖의 코딩 관련
- 코드를 작성할 때, 자주 사용하는 변수명을 보다 직관적이고 주석이 없어도 될정도로 설명이 될 수 있는 이름오로 작성하였음
- 확실히, 다양한 메서드를 다루는 입장에서 이름을 의도가 분명하도록 지으면, 나중에 혼동하기 쉽지 않아서 좋았음
- 메서드 이름은 동사/목적어(혹은 전치사)로 주로 지어짐
- 클래스의 첫글자는 대문자, 메서드나 변수명의 첫글자는 소문자로 시작
- 이러한 방식으로 함수의 이름 작명에 시간을 많이 사용하였음

---
## 네이밍할 때 자주 사용되는 반의어
* get / set 받다/ 받다
* add / remove 추가/제거
* create / destroy 창조/파괴하다
* start / stop 시동/정지
* insert delete 삽입/삭제
* increment / decrement 증가/감소
* old / new 구/신
* begin / end 시작/끝
* girst / last 긴/마지막
* up / down 위/아래
* min / max 최소/최대
* next / previous 다음/이전
* open /close 열다/ 닫다
* show /hide 보이다/ 숨기다
* suspend /resume 일시 정지/재개하다
* parent / child 부모/아이


## 커밋 메세지 컨벤션
- 기본구조
  - type : subject (필수사항)
  - body(선택사항)
  - footer(선택사항)
- type 예시
  - feat : 새로운 기능 추가
  - fix : 버그 수정
  - docs : 문서 수정
  - style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
  - refactor : 코드 리펙토링
  - test : 테스트 코드, 리펙토링 테스트 코드 추가
  - chore : 빌드 업무 수정, 패키지 매니저 수정

- subject 규칙
  - 최대 50글자, 영문표기시 첫 글자는 대문자로 표기
  - 개조식 구문으로 작성

- body 규칙
  - 한 줄당 72자 내로 작성
  - 양에 구애받지 않고 상세히 작성
  
- gitmogji
  - 아래와 같이 emogji를 사용하면 의미를 강조할 수 있지만, 아직 적용하기에는 경험이 적어 나중에 적용 예정

| Emogi | Description         |
|------|---------------------|
| 🎨   | 코드의 형식 / 구조를 개선 할 때 |
|  📰  | 새 파일을 만들 때 |
|   📝 |         사소한 코드 또는 언어를 변경할 때     |
|   📚 |          문서를 쓸 때 |
|   🐎 |          성능을 향상시킬 때    |
|   🐛 |          버그 reporting할 때, @FIXME 주석 태그 삽입    |
|   🚑 |          버그를 고칠 때    |
|   🔥 |          코드 또는 파일 제거할 때 , @CHANGED주석 태그와 함께    |
|   🚜 |          파일 구조를 변경할 때 . 🎨과 함께 사용   |
|   🔨 |          코드를 리팩토링 할 때    |
|   💄 |          UI / style 개선시    |
|   ♿️ |          접근성을 향상시킬 때    |
|   🚧 |          WIP (진행중인 작업)에 커밋, @REVIEW주석 태그와 함께 사용    |
|   💎 |          New Release    |
|   🔖 |          버전 태그      |
|   ✨  |          새로운 기능을 소개 할 때    |
|   ⚡  |          도입 할 때 이전 버전과 호환되지 않는 특징, @CHANGED주석 태그 사용    |
|   💡 |          새로운 아이디어, @IDEA주석 태그    |
|   🚀 |          배포 / 개발 작업 과 관련된 모든 것    |

## 리팩토링
* 변수의 쓸데없는 중첩사용이 있음, 이를 해결
```java
 private boolean check_PlayerNum(String num, int maxLength) {
     // 변경 전
  String[] check_array = num.split("");
  HashSet<String> check_set = new HashSet<>(Arrays.asList(check_array));
  if (!num.isEmpty() && num.matches("^[1-9]*$") && num.length() == maxLength
    && check_set.size() == num.length()) {
     return false;
  } else {
     return true;
  }
}
```
```java
private boolean check_PlayerNum(String num, int maxLength) {
    // 변경 후
  String[] check_array = num.split("");
  HashSet<String> check_set = new HashSet<>(Arrays.asList(check_array));
  return !num.isEmpty() && num.matches("^[1-9]*$") && num.length() == maxLength
  && check_set.size() == num.length();
  }
```





