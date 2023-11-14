# 미션 - 크리스마스 프로모션

---

## 💡 기능 요구 사항

날짜와 주문할 메뉴, 개수를 입력하면 주문 메뉴와 할인 전 총주문 금액, 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지를 출력한다.

### 📝 **핵심 알고리즘 및 구현할 기능 요구사항**

### 피드백 정리

1. 메서드 15라인 제한
2. 꼼꼼한 예외 처리 필요
3. 비지니스 로직과 UI로직 분리(ex. 입력하는 기능이 비지니스 로직에 있으면 안됨)
4. static final 대신 enum 활용
5. 변수가 불변하다면 final 사용
6. 필드 접근 제어자는 private을 사용
7. 객체에 대한 로직은 해당 객체의 메서드로 구현하여 독립성 높힘(ex. A객체에서 B객체의 필드를 get해서 구현하기보다 B객체에서 메서드로 구현한 후 리턴을 A객체에게 주는 것이 좋음)
8. 필드의 수를 줄여야 함
9. 성공 케이스 뿐만 아니라 예외 케이스도 테스트
10. 테스트 코드의 중복을 줄여야함
11. 테스트를 위해 구현 코드가 좌우되면 안됨
12. private 함수 테스트를 위해 클래스 분리를 고려해봐야 함

### 진행 방향

## 도메인 설계

해당 플래너의 입출력 대상 객체는 아래와 같다. 이를 토대로 순서대로 구현을 순서대로 진행할 것이다.

> 입력
> 1. 날짜
> 2. 주문할 메뉴와 개수

날짜는 자주 사용되기 때문에 format을 Date 객체로 미리 지정한 다음 사용하였다.

주문 메뉴와 개수 또한 format을 OrderedMenu 객체로 설정하였다.



> 출력
> 3. 주문 메뉴
> 4. 할인 전 총주문 금액
> 5. 증정 메뉴
> 6. 혜택 내역
> 7. 총혜택 금액
> 8. 할인 후 예상 결제 금액
> 9. 12월 이벤트 배지

그럼으로 도메인은 크게 아래와 같이 설계하였다.
1. Calender
2. Menu
3. Customer
4. Event
5. EventBadge

Customer객체를 둔 이유는 이벤트의 목표인 1월 이벤트에 재참여시 재사용성을 위해 객체를 설정하였다.

추가로 재사용할 확률이 높은 Calender와 Event를 인터페이스화하여 구현한다.

그리고 예외 처리 사항은 아래와 같다.

| 경우                                  | ExceptionClass | 에러 메세지                                     | 후처리 |
|-------------------------------------|---------------|--------------------------------------------|--------|
| 날짜가 1이상 31 이하의 숫자가 아닌 경우            | IllegalArgumentException | "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."       | 다시 입력 |
| 날짜가 숫자가 아닌 경우                       | NumberFormatException | "[ERROR] 유효하지 않은 숫자입니다. 다시 입력해 주세요."       | 다시 입력 |
| 날짜가 공백 혹은 빈 문자열이 입력됬을 경우            | IllegalArgumentException | "[ERROR] 공백 혹은 빈 문자가 입력되었습니다. 다시 입력해 주세요." | 다시 입력 |
| 날짜에 null이 입력됬을 경우                   | NullPointerException | "[ERROR] Null이 입력되었습니다. 다시 입력해 주세요."       | 다시 입력 |
| 입력된 메뉴 혹은 숫자가 공백 혹은 빈 문자열이 입력됬을 경우  | IllegalArgumentException | "[ERROR] 공백 혹은 빈 문자가 입력되었습니다. 다시 입력해 주세요." | 다시 입력 |
| 입력된 메뉴 혹은 숫자에 null이 입력됬을 경우         | NullPointerException | "[ERROR] Null이 입력되었습니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴판에 없는 메뉴 입력할  경우                  | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴의 개수가 숫자가 아닌 경우                   | NumberFormatException | "[ERROR] 유효하지 않은 숫자입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴의 개수가 0이하 숫자인 경우                  | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴 형식(음식1-숫자,음식2-숫자)에 맞지 않는 경우      | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 중복 메뉴 입력할 경우                        | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 한번에 21개 이상의 메뉴가 입력됬을 경우             | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |


---

## 👓 프로그래밍 요구 사항 정리

- JDK 17 버전에서 실행 가능해야 한다. **JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [Java 코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java) 가이드를 준수하며 프로그래밍한다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그램 구현이 완료되면 `ApplicationTest`의 모든 테스트가 성공해야 한다. **테스트가 실패할 경우 0점 처리한다.**
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- else 예약어를 쓰지 않는다.
    - 힌트: if 조건절에서 값을 return 하는 방식으로 구현하면 else를 사용하지 않아도 된다.
    - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.
- 아래 있는 `InputView`, `OutputView` 클래스를 참고하여 입출력 클래스를 구현한다.
    - 입력과 출력을 담당하는 클래스를 별도로 구현한다.
    - 해당 클래스의 패키지, 클래스명, 메서드의 반환 타입과 시그니처는 자유롭게 구현할 수 있다.
  ```java
  public class InputView {
      public int readDate() {
          System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
          String input = Console.readLine();    
          // ...
      }
      // ...
  }
  ```
  ```java
  public class OutputView {
      public void printMenu() {
          System.out.println("<주문 메뉴>");
          // ...
      }
      // ...
  }
  ```
- `camp.nextstep.edu.missionutils`에서 제공하는 `Console` API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

## 💻 과제 진행 요구 사항

- 미션은 [java-christmas-6](https://github.com/woowacourse-precourse/java-christmas-6) 저장소를 비공개 저장소로 생성해 시작한다.
- **기능을 구현하기 전 `docs/README.md`에 구현할 기능 목록을 정리**해 추가한다.
- **Git의 커밋 단위는 앞 단계에서 `docs/README.md`에 정리한 기능 목록 단위**로 추가한다.
    - [커밋 메시지 컨벤션](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 가이드를 참고해 커밋 메시지를 작성한다.
- 과제 진행 및 제출
  방법은 [프리코스 과제 제출](https://docs.google.com/document/d/1cmg0VpPkuvdaetxwp4hnyyFC_G-1f2Gr8nIDYIWcKC8/edit?usp=sharing) 문서를
  참고한다.
