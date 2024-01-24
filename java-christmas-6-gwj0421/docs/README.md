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

### 도메인 설계

#### 전반적인 설계

전체적인 클래스는 아래와 같다.

```
├── main
│   └── java
│       └── christmas
│           ├── Application.java
│           ├── config
│           │   ├── AppConstant.java
│           │   └── Month2023Detail.java
│           ├── controller
│           │   └── EventPlannerController.java
│           ├── domain
│           │   ├── badge
│           │   │   └── Badge.java
│           │   ├── calender
│           │   │   ├── Date.java
│           │   │   └── EventCalender.java
│           │   ├── event
│           │   │   ├── EventType.java
│           │   │   ├── PreviewDiscountMachine.java
│           │   │   ├── PreviewResult.java
│           │   │   ├── discount
│           │   │   │   ├── ChristmasDDayDiscountEvent.java
│           │   │   │   ├── DiscountEvent.java
│           │   │   │   ├── SpecialDiscountEvent.java
│           │   │   │   ├── WeekdayDiscountEvent.java
│           │   │   │   └── WeekendDiscountEvent.java
│           │   │   └── giveaway
│           │   │       ├── GiveawayByTotalPurchaseAmountEvent.java
│           │   │       └── GiveawayEvent.java
│           │   ├── menu
│           │   │   ├── Menu.java
│           │   │   ├── MenuGroup.java
│           │   │   └── OrderedMenu.java
│           │   └── week
│           │       ├── Week.java
│           │       └── WeekDiscountRange.java
│           ├── util
│           │   ├── ConvertUtil.java
│           │   └── MapUtil.java
│           ├── validation
│           │   ├── DomainValidation.java
│           │   └── ViewValidation.java
│           └── view
│               ├── ErrorMessage.java
│               ├── InputView.java
│               └── OutputView.java
└── test
    └── java
        └── christmas
            ├── ApplicationTest.java
            ├── config
            │   └── Month2023DetailTest.java
            ├── domain
            │   ├── badge
            │   │   └── BadgeTest.java
            │   ├── calender
            │   │   ├── DateTest.java
            │   │   └── EventCalenderTest.java
            │   ├── event
            │   │   ├── EventTypeTest.java
            │   │   ├── PreviewDiscountMachineTest.java
            │   │   ├── PreviewResultTest.java
            │   │   ├── discount
            │   │   │   ├── ChristmasDDayDiscountEventTest.java
            │   │   │   ├── SpecialDiscountEventTest.java
            │   │   │   ├── WeekdayDiscountEventTest.java
            │   │   │   └── WeekendDiscountEventTest.java
            │   │   └── giveaway
            │   │       └── GiveawayByTotalPurchaseAmountEventTest.java
            │   ├── menu
            │   │   ├── MenuGroupTest.java
            │   │   ├── MenuTest.java
            │   │   └── OrderedMenuTest.java
            │   └── week
            │       ├── WeekDiscountRangeTest.java
            │       └── WeekTest.java
            ├── util
            │   ├── ConvertUtilTest.java
            └── validation
                ├── DomainValidationTest.java
                └── ViewValidationTest.java
```

#### 구조 소개

##### 입력 파트

해당 플래너의 입출력 대상 객체는 아래와 같다. 이를 토대로 순서대로 구현을 순서대로 진행할 것이다.

> 입력
> 1. 날짜
> 2. 주문할 메뉴와 개수

날짜는 Date, 주문 메뉴와 개수는 OrderedMenu 객체로 설정하였다.

기본적으로 입력할 때, Null 혹은 공백, 빈 문자열이 입력되는지 검증하였고, 입력된 값을 토대로 도메인을 만들 때는 아래와 같은 검증 과정을 거쳤다(아래의 검증 파트 참고).

아래는 대략적인 입력에 대한 검증이다.

> 날짜
> - 해당 달에 존재하는 날짜인지 검증

> 주문 메뉴 및 개수
> - 메뉴가 존재하는지 검증
> - 음료만 선택하지 않았는지 검증
> - 최대 개수를 넘지 않았는지 검증

##### 검증 파트

입력된 값을 도메인으로 넘겨주기 전, 검증을 두 영역으로 나눠 검증을 진행하였다.

프론트엔드에서는 가벼운 검증, 백엔드에서는 세밀한 검증을 하는 것처럼 ViewValidation과 DomainValidation으로 나눴다.

ViewValidation은 비교적 가벼운 검증, DomainValidation은 세밀한 검증을 아래와 같이 진행하였다.

> ViewValidation
> - 입력된 값이 Null이나 공백, 빈 문자열이 아닌지 검증
> - 입력된 메뉴들이 중복이 아닌지 검증
> - 입력된 개수가 양의 정수인지 검증
> - 입력된 메뉴, 개수가 "음식-개수" 형태로 입력되는지 검증
> - 입력된 날짜 및 개수가 숫자인지 검증

> DomainValidation
> - 날짜가 타당한지 검증
> - 메뉴가 존재하는 메뉴인지 검증
> - 입력된 메뉴 전체가 음료 메뉴가 아닌지 검증
> - 입력된 메뉴들의 총 개수가 제한 개수를 넘지 않았는지 검증
> - 코드에서 달을 다룰 때, 1~12월을 범위로 다루는지 검증
> - 실제 결제 금액이 음수가 아닌지 검증

##### Enum 활용 파트

입력된 메뉴를 효과적으로 다루기 위해 Menu라는 Enum 클래스를 만들었다.

이벤트에서 메뉴의 카테고리를 다루기 때문에 MenuGroup이라는 Enum 클래스를 만들어서 Menu를 카테고리화 하였다.

이런 방식을 통해서 기존보다 카테고리 분별에 대한 가독성이 매우 높아졌고 Menu에 대한 독립성이 높아졌다.

평일 이벤트와 주말 이벤트를 다루기 위해 Week Enum클래스를 만들었다.

MenuGroup과 마찬가지로 WeekDiscountRange Enum 클래스를 만들어, 평일 이벤트와 주말 이벤트의 기간을 카테고리화 하여 Week에 대한 독립성과 가독성이 높아졌다.

이벤트도 종류에 따라 EventType Enum클래스를 만들어서 효율적으로 관리하였다.

그리고 뱃지 또한 종류가 정해져있기 때문에 Enum클래스로 다뤘다.

공통적으로 이전 프로젝트의 피드백(객체에 대한 로직은 해당 객체의 메서드로 구현하여 독립성 높힘)를 아래에 예시처럼 구현하였다.

> - OrderedMenu의 총 가격을 리턴하는 기능
> - MenuGroup을 찾는 기능
> - WeekDiscountRange를 찾는 기능
> - 등등

##### 이벤트 파트

주어진 이벤트의 종류는 크게 할인 이벤트와 증정 이벤트로 나눌수 있다.

할인 이벤트는 공통적으로 방문 날짜를 사용하고 이벤트기간인지 아닌지 확인하는 부분이다.

추가적으로 요일, 주문 메뉴등의 추가적인 요인으로 적용된다.

이러한 공통 부분을 DiscountEvent라는 인터페이스를 선언하여 가독성을 높혔다.

구조는 아래와 같다.

> DiscountEvent interface
> - ChristmasDDAyDiscountEvent
> - SpecialDiscountEvent
> - WeekdayDiscountEvent
> - WeekendDiscountEvent

각각 이벤트 조건에 맞게 apply 함수를 오버라이딩을 통해 구현하였다.

기본적으로 canApply 함수를 인터페이스에서 지정했기 때문에, 공통 부분에 대한 중복이 확연히 줄었다.

증정 이벤트는 공통적인 부분은 아마 가격에 따른 증정품와 증정품 개수일 것이다.

이러한 구조에서 자식 클래스에서 부모 영역의 필드를 재정의할 수 있는 추상클래스를 사용하였다.

그래서 자식 클래스에서 특정 가격과 가격에 따른 증정품, 증정품 개수를 생성자를 통해 넣게 된다면 중복이 확연히 줄 것이다.

그래서 apply 함수를 추상 메서드로 두고 자식 클래스에서 해당 이벤트의 조건에 맞게 동작하도록 오버라이딩 하였다.

아래는 증정품 이벤트 클래스 구조이다.

> GiveawayEvent 추상 클래스
> - GiveawayByTotalPurchaseAmountEvent

##### 유틸 파트 및 기타 파트

ConvertUtil은 주로 아래의 기능을 구현하였다.

> - 문자열을 숫자로 변경
> - 입력된 오더(예를 들어, "타파스-1,제로콜라-1")를 Map<String,Integer>로 변경
> - Map<String,Integer>의 주문 내역을 Map<Enum,Integer>과 같이 EnumMap으로 변경

우선, 헤딩 월 순서와 Date객체, 스타 위치가 담긴 Set을 통해 EventCalender를 구현하였다.

EventCalender를 만든 이유는 단순히 12월에만 사용하는 것이 아니라, 다른 달에도 사용될 염두하고 설계하였다.

만약, 이벤트 목표인 1월 새해 이벤트에도 재사용한다면 해당 클래스에 달 정보와 최신화된 스타 위치를 삽입하면 해당 날짜가 스타 위치인지 간편하게 알 수 있다.

종합적으로 이벤트들을 교통정리 해주는 클래스는 PreviewDiscountMachine 클래스이다.

해당 클래스를 통해 지금껏 주어진 데이터를 통해 사용자에게 알려줄 여러 데이터들을 가진 PreviewResult 객체를 리턴해줄 수 있다.

##### 출력 파트

출력의 대략적인 순서는 아래와 같다.

> 출력
> 1. 주문 메뉴
> 2. 할인 전 총주문 금액
> 3. 증정 메뉴
> 4. 혜택 내역
> 5. 총혜택 금액
> 6. 할인 후 예상 결제 금액
> 7. 12월 이벤트 배지

##### 예외 사항 처리

그리고 예외 처리 사항은 아래와 같다.

| 경우                                 | ExceptionClass | 에러 메세지                                     | 후처리   |
|------------------------------------|---------------|--------------------------------------------|-------|
| 날짜가 1이상 31 이하의 숫자가 아닌 경우           | IllegalArgumentException | "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."       | 다시 입력 |
| 날짜가 숫자가 아닌 경우                      | NumberFormatException | "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."       | 다시 입력 |
| 날짜가 공백 혹은 빈 문자열이 입력됬을 경우           | IllegalArgumentException | "[ERROR] 공백 혹은 빈 문자가 입력되었습니다. 다시 입력해 주세요." | 다시 입력 |
| 날짜에 null이 입력됬을 경우                  | NullPointerException | "[ERROR] Null이 입력되었습니다. 다시 입력해 주세요."       | 다시 입력 |
| 입력된 메뉴 혹은 숫자가 공백 혹은 빈 문자열이 입력됬을 경우 | IllegalArgumentException | "[ERROR] 공백 혹은 빈 문자가 입력되었습니다. 다시 입력해 주세요." | 다시 입력 |
| 입력된 메뉴 혹은 숫자에 null이 입력됬을 경우        | NullPointerException | "[ERROR] Null이 입력되었습니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴판에 없는 메뉴 입력할 경우                  | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴의 개수가 숫자가 아닌 경우                  | NumberFormatException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴의 개수가 0이하 숫자인 경우                 | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 메뉴 형식(음식1-숫자,음식2-숫자)에 맞지 않는 경우     | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 중복 메뉴 입력할 경우                       | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 입력된 메뉴가 음료 메뉴로만 이뤄져 있을 경우          | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| 입력된 메뉴의 개수가 1이상 20이하가 아닐 경우        | IllegalArgumentException | "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."       | 다시 입력 |
| (디버깅용) 입력된 달이 잘못되었을 경우             | IllegalArgumentException | "[ERROR] 코드 에러. 잘못된 파라미터를 입력받았습니다."       | 예외 발생 |
| (디버깅용) 할인되어 결제 금액이 음수가 되었을 경우      | IllegalArgumentException | "[ERROR] 코드 에러. 잘못된 파라미터를 입력받았습니다."       | 예외 발생 |


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
