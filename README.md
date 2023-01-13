SpringBoot / React / Intelli J / Mybatis / hibernate /
마이크로서비스 3주 간 진행 ( 팀별 )
=> 클라우드 / 배포


[[ 클라우드 전문가 ]]
- 인프라 ( 서버 / 네트워크 / 클라우드 ) 인력이 없다
  => 인프라 쪽이 구인 많은 상황
  => 개발 지식이 있어야 하고 => 개발지식을 가지고 인프라로 넘어가는 시점

=> 개발 - 클라우드로 넘어가기도 함

devOps 클라우드 전문가
- 개발 지식이 깔려있어야 한다
- 적당히 지식을 쌓고 => 넘어가는 루트
- AWS 자격증
-

[ 자격증 ]
=> AWS practitional ( 기초 과정 ) 자격증 ( 패스해도 되긴 한다 )
=> AWS associate 종류 3개 (300$)
=> 아키텍트 => develop
=> 프로페셔널



[ 1일 ]

[ AWS - S3 ]
: S3(Simple,Storage, Service)

- 저렴한 비용
  ec2에 이미지, 영상등을 저장할 때 s3로 보완하여 사용
  ( ec2 스토리지만을 사용하면 비용 과다 )

- 속도 빠름
  각 지역에 맞게 선택 => 업/다운로드 시 멀티 파트 업로드를 지원 ( 지역시간 최소화 )

객체 - object, AWS는 S3에 저장된 데이터 하나 하나를 객체라고 명명하는데, 하나 하나의 파일이라고 생각하면 된다.
버킷 - bucket, 객체가 파일이라면 버킷은 연관된 객체들을 그룹핑한 최상위 디렉토리라고 할 수 있다. 버킷 단위로 지역(region)을 지정 할 수 있고, 또 버킷에 포함된 모든 객체에 대해서 일괄적으로 인증과 접속 제한을 걸 수 있다.
버전관리 - S3에 저장된 객체들의 변화를 저장. 예를들어 A라는 객체를 사용자가 삭제하거나 변경해도 각각의 변화를 모두 기록하기 때문에 실수를 만회할 수 있다.
RSS - Reduced Redundancy Storage의 약자로 일반 S3 객체에 비해서 데이터가 손실될 확률이 높은 형태의 저장 방식. 대신에 가력이 저렴하기 때문에 복원이 가능한 데이터, 이를테면 섬네일 이미지와 같은 것을 저장하는데 적합하다. 그럼에도 불구하고 물리적인 하드 디스크 대비 400배 가량 안전하다는 것이 아마존의 주장
Glacier - 영어로는 빙하라는 뜻으로 매우 저렴한 가격으로 데이터를 저장 할 수 있는 아마존의 스토리지 서비스

[ MySQL / Maria DB / Oracle ]
MySql에서 MariaDB가 갈라져 나왔기 때문에 문법이 같습니다.
그러나 Oracle DB는 지금은 MySql을 합병했지만 근본이 다른 회사였기 때문에 문법이 다릅니다.

참고로 MariaDB가 갈라져나온 이유는 MySql이 오라클에 합병된 것에 대한 반발이 크다고 알고있습니다.


[ react ]

- 개념
1. 렌더링할 페이지 UI 를 미리 작성해서 '컴포넌트' 로 만들어 둔다.
   필요에 따라 호출 됬을 때, '라우터' 가 해당 '컴포넌트'를 가져와서 UI 를 렌더링 한다
   => Single Page Application 효과

2. 서버단에서 데이터는 axios(fetch 라이브러리) 를 통해 rest api 통신으로 받아쓴다

3. 그 컴포넌트 안에 들어가는 변수들을 관리하는게 'react hook'  ex. useState

class ShoppingList extends React.Component {
render() {
return (
<div className="shopping-list">
<h1>Shopping List for {this.props.name}</h1>
<ul>
<li>Instagram</li>
<li>WhatsApp</li>
<li>Oculus</li>
</ul>
</div>
);
}
}


ShoppingList
- React 컴포넌트 클래스
- React 컴포넌트 타입

[ JSX ] 리액트 문법( 파일타입 )
return React.createElement('div', {className: 'shopping-list'},
React.createElement('h1', /* ... h1 children ... */),
React.createElement('ul', /* ... ul children ... */)
);


커스텀 React 컴포넌트

화살표 함수
onClick={function() { console.log('click'); }
onClick={() => console.log('click')}



[ 2일 ]
[ git ]

git clone 주소.git => 밑에 최상위폴더까지 만들어지고 그안에 clone 되고

git clone 주소.git .  => 현재 디렉에 바로 clone

[ 동기화 ] => fetch / pull
version 이 다른데서 update 되서 달라졌을 때, 무조건 pull 땡기는게 아니라
실무에선 fetch 를 먼저 땡겨본다.

git fetch origin master
=> git log --oneline --all ( all 까지 붙여주기 )
: (HEAD MASTER) 는 로컬에서 version 보여주는것이고
origin/master/HEAD 관계에서 깃헙에서의 버전을 보여준다.

=> fetch 를 먼저 땡겨서, 현재 로컬의 버전과, hub 의 버전을 확인을 먼저 하고

이후에 pull 로 땡겨서 사용하는 순서



[ branch ]
master 에는 commit 을 쌓지 않음
=> merge 만 할 뿐
branch 에서 commit 쌓고 => master 로는 merge 만 시킴

베타테스트의 master 에 merge 시키고 => 이걸 실제 운영 release 버전에 올리는 식으로 사용

[ 이전 버전으로 가보기 => checkout ]
git checkout 버전id

=> 해당 시점으로 잠깐 돌아가서 보기만 하는 것
=> 실제로 되돌리면 reset 기능을 시켜야함

[ tag 붙이기 - 의미있는 버전에 태그 ] ( Alias 개념 )
git tag "v1.1.0"
=> 다음에 해당 버전으로 가보거나, 관리할 때 tag 이름으로 커밋 id 를 대신할 수 있다.


git log --oneline --all --graph ( 그래프로 약간 간단하게 알려줌 )


- fast forward
  Master / June / Park

Master 가 June 먹을때는 fast forward 걍 바로 먹으면 됨

근데 그다음 Park 꺼 먹을 때는
Park 와 June 사이에 버전차이가 있기 때문에 fastforward 로는 안됨
=> Conflict 생김 => 직접 하나하나 confllict 잡아야함
=> 최종 add . =>  commit ( Merge commit 이라 부름 )

master 에 PR 날려서 merge 되면
june 이 쓰던 branch 는 보통 delete

그럼 june 은 일단 fetch 로 확인 하고
=> pull 땡겨서 다시 시작하면 된다.



[ Gradle ]

maven repository 에서 다운받아도 되고 ,
maven central repository 에서 다운받아도 된다.


[ intellij plugin 추천 ]
korean => 한국어버전

material => atom material : 디렉 모양
material theme UI : 테마


[ intellij community 쓸 때 한글 깨지는거 ]
UTF 설정해줄 거 해주고, ( encoding 키워드 )
추가로, gradle 키워드로 검색해서
build - gradle 설정되있는거에서 => intellij 로 바꿔주기 ( ide 재시작 )


[ intellij VCS 깃 한방 GuI ]
VCS - share Project on Github
- repository name : 새로 이름짓고
- remote : origin 별칭
- share by => add account - login via Github

=> 한방에 hub에 리포지터리 생성하고, git init~ push 까지 되긴 함

=> GUI 로 올리기 때문에, gradle / ideal / build 를 체크 해제하고 올려줄 수 있고,
( git ignore )
commit message 를 입력해 줄 수 있고.


[ final ]

entity 에서 해당 위치에서 값을 무조건 선언 + 할당 하고자 할 때 => final 선언 => 불변
ex ) 이름 / 주민등록번호 등 불변 값에 대해서.
( 반대로  level 같이 변할 수 있는 값에는 final 을 붙이면 안되고. )
=> final 주면 당장 거기서 할당까지 해줘야 컴파일 가능

public class Dancer {

    private final String crew;
    private final String myName;


    //public Dancer(){}
    // => 여기서 무조건 값이 할당되어야 하기 때문에, 
1. 기본 생성자가 있으면 컴파일 안된다.
2. 모든 final에 대한 생성자가 있어야 함

   public Dancer(String crew, String myName) {
   this.crew = crew;
   this.myName = myName;
   }

[ final 걸고 setter 안만들기 ]
- 최종 고정값으로 만들려면 final 붙여서 선언해주고
  => setter 를 만들지 않아야 완벽한 final 값이 된다.




[ Enum ]
DTO 에서 category 값을 정해둔 column 값을 정하고 싶을 때
Enum class 로 생성해서
public enum DanceLevel {
PRO, MID, LOW, BEGINNER
}

이정도로만 써줘도, 이 4 개 값중에서만 들어올 수 있도록 제한 거는식으로 사용 가능

[ static import ]

import static java.lang.Math.*;
import static java.lang.System.*;

이렇게 static 걸어놓고 util import 해두면

Math.random();
System.out.println();
하던거
random();
out.println();
이렇게 좀 가볍게 사용할 수 있다. ( 하지만 가독성 문제가? )


이미 선언되어있는
DanceLevel.BEGINNER 같은 선언문에서도
intellij alt enter로 static import 자동선언시켜서 간단하게 쓸 수 있긴하다.


[ OOP 객체지향 ]
[ 은닉화 ]
private 걸어서 숨겨두고,
public 메소드로만 꺼내서 쓰도록 설계
=> private 변수들은 은닉화됨


[ 캡슐화 ]
=> 메소드 별 순서 정하기
: 메소드 안에 메소드 넣어서 연계해서 사용 => 순서를 정해두는 것


[ 상속 ]
상속 : 서로 다른 객체를 생성하고 ( memory 주소 다름 )
=> 둘을 연결시켜주는 개념 extends

자식 생성자는 super() 을 기본값으로 해서 부모 생성자를 먼저 실행시킴
부모생성자 실행 => 자식생성자 실행

자식이 가진 private 메소드. overriding 하면서 추가해줄 수 있음.
@Override
public void dance() {
super.dance();
wink();
}

- default 로 모든 class 는 extends Object 하고 있다.


[ 다형성 ]
: 객체지향 프로그래밍의 큰 강점! 핵심
=> 자식이 부모행세를 할 수 있는 것을 이용
=> 즉, 같은 형태의 자식객체를 가지고, 부모객체Type 으로 만들수도, 원래 자식객체 Type 으로 만들 수도,
Object Type 으로 만들 수도 있는 이러한 성격이 다형성

=> 상속해서 사용하고, 부모 형태 Type 으로 넣어두면
부모를 상속한 여러 다른 종류의 자식 객체들을 부모Type 으로 묶어서 같이 관리할 수 있다.


[ 추상화 ]
public class Dancer{ } 클래스를
public abstract class Dancer {  } 추상화 시키면
=> interface 처럼 객체를 직접 생성할 수 없게 된다.
=> 상속해서 사용하라는 interface 와 비슷한 역할을 하는 것

=> 추상 클래스를 사용하는 건 interface 처럼 무조건 override를 강요하기 위한 것이기 때문에
메소드도 abstract 메소드로 만들어서 interface 처럼 선언만 해주고
=> 상속 => override => 정의 해서 사용


abstract class VS interface

abstract class 는 결국에 class 이기 때문에,
- 필드(변수)도 가질 수 있고, 생성자도 가질 수 있다.
- 다중상속 X

- interface 는 필드도, 생성자도 가질 수 없다.
  메소드만 추상화해서 사용하는 용도
  => 메소드에 default 로 public abstract 가 붙는 것
- 다중상속 O

=> 인터페이스를 여러개 만들어서 다중 상속해서 필요에 따라 override 해서 사용!
public class IdolDancer extends Dancer implements Singer, Rapper { }

- list / set 은 iterable interface 를 상속( 양방향 자료구조 ) 하기 때문에 for each 돌려지고
  map 은 iiterable 상속 안하는 자료구조여서,
  for each 가 자체적으로는 못돌린다. ( key, value 따로 빼서는 돌릴 수 있다. )

- LinkedList<Object> list1 = new LinkedList<>();
  Queue<Object> queue = new LinkedList<>();



[ 3일 ]

[ JDK version ]
- java 8 버전 부터 많이씀, 8 / 11
- 8 : 함수형 문법 / Lambda 등장

[ Beans ]

- 'Entity' 필드 설계 할때는 외부접근을 차단할 수 있도록
  private / protected 제한자 필수 ( public default x )
  => 자체에 접근하지말고 setter 로 접근해라


[ wrapper 변수 / primative 변수 ]
: 기본값이 다른게 핵심

- primative 타입
  byte short int long => 기본값 0
  char => 기본값 ''

-Wrapper 타입
Long String Integer Character Array[]=> 기본값 null

primative <=> warpper 사이에서는 자동 형변환 된다.



[ Hash ]
- 0, 1, 2 ... index 로 순서대로 자료구조를 정하는게 아니라
  => 값이 주어지면 Hash 주소값을 주는 방식

[ 객체 끼리 비교 ]

1. equals

== 는 주소값 다르게 잡혀있어서 당연히 다르게 나오고

equals 로도 기본은 false 잡히는데 이건 override 해서
주소값은 무시하고 값만으로 비교할 수 있게 해서 사용

=> intellij 에서는 equ 정도 쳐서 generate wizard 마법사로 만들면 편함

예시 )
@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
ProductsVO that = (ProductsVO) o;
return productId.equals(that.productId) && productName.equals(that.productName) && price.equals(that.price);
}


2. HashCode

   : equals 를 override 해서 사용할 때는 세트로
   hashcode 도 같이 override 해줘야
   객체도 필드값이 같은 객체를 동일 객체로 비교한다.

equals 만 해주면 동일 필드값은 객체는 hashcode 가 달라서
다른 객체로 인식

예시 )
@Override
public int hashCode() {
return Objects.hash(productId, productName, price);
}


=> 필드값이 같으면 equals 날렸을 때 동일 객체로 인식




[ 동작의 Parameter 화 ]  : 추상적 조건의 필터링
- 색깔 필터링 하는 동작
- 무게 필터링 하는 동작
  이 각각의 동작코드를 필요에 따라 파라미터로 넣어서 사용하자
  => 람다식
  => 엄청 짧게 써서 실시간으로 조건을 바꿔주는 느낌으로
  사용할 수 있음 ( 클래스를 만들고 어쩌고 하는 과정을
  람다 한줄로 하니깐 )


- JS 에서는 함수를 함수로 전달할 수 있는데
  Java 에서는 그게 안된다.
  => 함수를 Class 에 담아서 전달하는 방식으로 사용


[ Lambda ]
@FunctionalInterface // 람다 사용 가능 검증
public interface Applepredicate {
// 필터 조건을 test 라는 함수를 쓰고싶으니  interface로 감싸서 사용
// 추상메소드가 딱 하나 test 있으니까, Lambda 를 써도 어짜피 이거 하나만 가르키므로 사용 가능
// 두 개 이상일 시, lambda 사용 불가
boolean test(Apple apple);
}

- 그래서, 추상메소드 ( body 부 없는 ) 는 하나만 만들고,
  그냥 쓰는 일반 메소드는 default void 메소드(){ }
  이렇게 해서 default 걸어두고 override 강제를 없애두고
  나중에 천천히 처리하도록 하는 실무용 기술


- Generic 까지 적용해서 Lambda 쓰면 더 범용적으로 사용가능

[ 제너릭 ]
: T 를 파라미터로 넣어서 R 을 리턴받는다.
public interface GenericPrediacte2<T, R> {
// T, R 제너릭 타입 두개로 잡아두기
//T : 소비될 객체 타입 ( parameter )
//R : return 객체 타입
R test(T t);
}


[ 람다 ]
- anonymous <=> Lambda <=> ::메소드참조 문법

오며가며 사용하는 거 intellij 에서 alt enter로
바꿔가면서 익숙해져라


[ 정렬 - Compare ]
- 정렬을 할 때 원본으로 하면 자료의 내부구조가 바뀌므로
  복사본 만들어놓고 거기다 정렬 시키는 방법도 안전을 위해
  많이 쓰인다.


- 오름차 / 내림차 : 기본적으로 비교를 해야 함

- Collections.sort(pList); 객체 자체로는 기준이 없어서 정렬할 수 없다.
  => Comparable< > 인터페이스 구현해서 사용

public class Person implements Comparable<Person> {

@Override
public int compareTo(Person o) { //compareTo 메소드 : 비교대상 객체 2개를 전달받음 ( this / o )
return this.age - o.getAge(); // this 랑 비교해서 양 > 같 = 음 <
// 이걸 계속 하나하나 써서 Comparable 하게 만들어서 => 정렬을 시키는 것
}

[ this ]
모든 메소드에는 파라미터로
( Type this ) 가 default 로 깔려있는 것

public int compareTo(Person this, Person o ) { }

=> compareTo 를 오버라이드 해서 해당 객체를 정렬할 때
어떤 걸 기준으로 할 지 미리 정해두는 것 ( ex. age)

=> 내림차순 으로 하려면 compare to 할 비교대상 순서를 바꿔서 넣으면 됨

=> DTO 에서 compareTo 매번 override 하지 않고
쓰이는 곳에서도 직접 compare 에서 override 해서 사용할 수 있음
( compareTo 씹히고 compare 로 정렬 )

=> 문자열 정렬할 때는 - 빼기가 아니라 compareTo 라는 문법 사용하면 됨
return o1.getName().compareTo(o2.getName());

=> 람다식 적용 가능 ( Comparable 인터페이스 )

// 람다 한방 식
pList.sort(comparing(person -> person.getAge()));
//T 를 주면 R 을 내놔라
pList.sort(comparing(Person::getName));
pList.sort(comparing(Person::getName).reversed());


람다 => 나중에 코드 관리하기가 되게 편하다.


[ Stream ]
@AllArgsConstructor
@RequiredArgsConstructor => final 붙은 필드들만 골라서
생성자 만들어줌

- .filter ( 거르기 ) : 10개 중 3개를 필터링
- .map ( 특정 필드값만 가져오기 : 10개 중 10개를 특정값 만 )
  => a -> a.getName( )  이렇게 특정 필드값 가져오는 용도로 사용할 수도 있고
  => a -> a / 100  이렇게 데이터를 조작해서 리턴시키는 요도로 사용할 때도 map 사용

-.mapToInt
: 기존 map 에 추가기능 붙음
int 로 확실히 mappign 해줬기 때문에
.sum / .average /.max 등 int 다루는 기능들 제공

=> 최소/ 최대값 등에 대한 객체 정보 전체를 가져올 때는
바로 .stream().min() 땡기고 이후에 Comparator 로 비교값 줘서 가져와서 사용

- .sorted ( 정렬 ) : String / 숫자는 자동정렬, Comparator.comparing( a-> a.비교대상 )
- .distinct ( 중복제거 )
- .limit ( 순서대로 몇개 )
- .collect ( 최종 자료구조로 모으기 )
- .forEach ( 하나하나 흩뿌려서 작업 )



[ 4일 ]

[ optional ]
=> 자바 진영에서 자동 null 체크

[ .ifPresent ] Stream 문법
=> 존재하면 진행시키고 / 존재안하면 안함


[[ Match - boolean ]]
[ anyMatch ]
: 주어진 Stream 에서 적어도 한 요소라도 일치하는게 있는지 확인

[ allMatch ]
: 모두 조건에 통과하는가

[ nonMatch ]
: 하나도 없는가

- filter 등 돌리고, 거기서 match 찾는 식으로 사용


[ Builder 패턴 ] - 디자인 패턴

-Entity 만들 때, not null 값이 있다고 가정
=> setter 로 객체 생성하면 not null 값 놓칠 수 있음.
=> 생성자로 생성하면 null 값도 하나하나 null 로 넣어줘야함 ( 귀찮 )

귀찮고 불합리해 => Builder 디자인 패턴이 나옴
똑같은데 그냥 순서 상관없고 편하게 객체 생성할 수 있게 해주는 것

Entity에 @Builder 어노테이션으로 사용해주겠다 선언하면 되고
.builder() 메소드로 시작
.build(); 로 최종 빌드

해당 엔티티클래스
Board.builder()
.id(1L)
.title("title")
.content("contents")
.writer("writer")
.build();






=========================
[[ HTTP ]]

[ HTTP 프로토콜 ]
IP ( Internet Protocol ) 주소
패킷에 담아서 보낸다.


[ stateless ] 사용 이유
=> 스케일 아웃 ( 서버 증설 확장에 유리 )
어짜피 무상태성이라서 기억 못하니까
서버 수평확장 용이
( stateful 한 상태면 서버증설 시, 기존 정보가 날아감 )
- 아무 서버나 이용 가능
- 서버 장애 시, 다른 서버 사용 가능

=> 로그인 등 유지가 필요한 경우에는 세션, 쿠키 사용


[ 비 연결성 ]
- HTTP 는 한번 요청 - 응답 을 한 후에는 TCP/IP 연결을 끊음
  => 동시에 처리하는 연결 수는 적게 유지 ( 서버자원 효율적 사용 )

- 한계 => 3 way handshake 시간 ( TCP / IP 연결 새로 맺어야함 )

- http 지속연결 persistent connections 기능 사용해서 해결 중


[ URI 규칙 ]
- 대문자 쓰지마라
- 언더바 쓰지마라 ( _ )
- 동사 쓰지마라 ( create read delete )
  => 명사로 써줘야함 ( 웬 만 하 면 )

=> HTML form 태그 는 GET / POST 만 지원하기때문에
어 쩔 수 없 이 /edit  /delete 등  URI 에 붙여서 사용
: 컨트롤 URI 라 부른다.
( 실무에서는 규칙이라 그냥 쓰기도 함 )

=> Rest API 에서는 PATCH 등 지원하니까 그걸로 구분하는게
' 좋 음 ' ( 규칙일 뿐 )

=> 포커스는 회원 ( 리소스 )

예를들어
회원 조회 /등록 /수정 /삭제 시
'회원' 에 포커스가 맞춰져야 하므로
모든 URI 에는 기본적으로 members 가 들어가야 한다.

- 행위는 HTTP 요청 Method 종류로 구분해라
  GET / POST / PATCH / DELETE

GET : URI 에 데이터 전달
=> 조회
POST : HTTP BODY 부에 데이터 전달
=> 새 리소스 생성
=> 프로세스 처리
=> 보안처리

PUT : 통째로 갈아 끼운다.
=> 리소스를 완전히 대체 ( 싹 갈아 엎음 )
=> force push 느낌 ( 위험. 잘 안씀 )

PATCH : 부분변경
DELETE : 삭제
=> HTTP 요청 시 Body 가 없다
=> 데이터 넘기고 싶으면 URI 로 넘겨야 함


[ 멱등 idempotent ]
GET 은 조회라 멱등 메소드 f(f(x)) = f(x)
100번 조회해도 같은 결과
PUT DELETE 도 마찬가지.

POST 는 멱등이 아니다!
100번 POST 하면 100번 프로세스가 진행됨
=> 중복해서 발생

=> POST 요청을 중복해서 요청해도 되는가
판단해야함 , 자동복구 메커니즘

=> POST 페이지에서 새로고침 시,
Redirect 걸어서 Get 방식 URL 로 안전하게 보내야됨
( 중복 방지 / 데이터 유지 방지 )




[ 캐시 ]
응답 결과 리소스를 캐시 메모리에 저장해두고 가져다 사용
=> GET 요청에 대해 캐시로 사용
( POST 등은 잘 사용하지 않음 )



[ API 전송 == JSON 전송 == Rest API 통신 ]
웹 HTML 에서 => 모바일 환경으로 데이터 보낼 때
HTML 형식으로는 못받아
=> API 로 데이터만 전송

자바에서 파이썬으로 보내는 등 다른 개발환경으로 보낼 때도
=> API 로, Server to Server 로 전송



Json : JavaScript Object Notation

[ HTTP contentType ]
multipart/form-data : 여러 종류의 data 타입 보낼 때
( ex. file / input text ... )
application/json : 제이슨 rest API 통신타입




[ 200번대 - OK ]
[ 300번대 - redirect ]
[ 400번대 - 클라 잘못 ]

400 - bad request => 일부로 발생시켜두고 안내
( 많이 씀 400 )

401 - unauthorized => 인증 Authentication 안됨
: 이름은 authorized 인가 인데 사실 인증 에러임
403 - forbidden => 요청은 이해 / 승인거부
( authentication 는 통과  / authorized 불통 )
Token / JWT 없이 옴

404 - 주기싫은거 /
405 - HTTP 메소드 종류 틀림 ( GET / POST / .. )

[ 500번대 - 서버 잘못 ]
: Spring 에서 컴파일, 런타입 exception 으로 내리는 것
- 아님 애매한거도 500으로

503 - 서버 일시적 이용불가


- 개발자 입장에서는 200 / 400 번대 에러를 미리 만들어두고 안내




[ Request Header ]

=> referer :  바로 이전 페이지 정보

=> redirect 시킬 때, 이 정보 가져와서 바로 이전 페이지로
돌려보내 준다.
ex ) 로그인 한 시점에서 referer  로 돌려보내기






[ 캐쉬 ]
cache 설정을 해두면 서버에서 받아오는게 아니라
캐쉬 메모리에서 가져와서 사용 ( 지정해둔 시간 안에 )

시간 초과시 :
last modified 확인해서 동일하면
서버는 해당 데이터를 보내는게 아니라 (body)
header 만 보냄 (304) not modified
=> 캐쉬를 다시 그대로 사용
( 바디부를 보내는게 아니라 헤더만 보내서
훨씬 효율적인 리소스 관리 )

=> last modified 가 변경됬을 시,
200 모든 데이터 전송 (body포함)


- 근데 last modified 날짜로만 비교하면
  이것 저것 비효율 적인 부분들이 존재
  => tag 값으로 버전으로 관리
  Etag "v1.0"

버전이 같은가 에 if match 처리


[ cache-Control ]
max-age : 캐쉬 유효시간  ( 0 으로 주면 쓰지말라 )

[ Proxy 서버 ]
: 중계서버
=> 원 서버(original) 데이터를
Proxy 캐시 서버에 저장해둠

no-cache : 캐시 서버는 사용해라.
=> 하지만 original 서버에 검증하고 사용해라
no-store : 저장은 하지마라 ( 민감한 정보 )
must-revalidate : 캐시 만료후 다시 original 서버에 검증 필요
pragma : no-cache =>cache-control 에 흡수됨

=> no-cache / no-store / must-revalidate / max-age=0
다 해둬야 안전한 proxy 캐시 서버 사용





[ 5일차 ]  => Spring Boot API
- spring io 압축파일 풀 때 하위폴더 생성 체크 해제
  ( 경로 두번 들어가면 관리하기 불편하다 )

- build.gradle
  group - 그룹 이름
  version - 회사 버전 양식에 맞춰서

- spring boot autoconfigure :
  서버설정 로그설정 그런게 다 자동으로 잡혀있어서
  => application.properties 에 xml 설정들 모아서 할 수 있다.

- 기본적으로 멀티쓰레드 지원 ( 스레드 풀 )

-  No active profile set, falling back to 1 default profile: "default"
   : application.properties 기본 디폴트 사용


- JS : tap 2칸
- Java : tap 4칸
- yml : 2칸


자동 리로드
spring boot devtools 설치

설정 - build - compiler - build project automatically
설정 -고급설정 - allow auto-make to start

[ 로그 찍기 ]
( 로그백 많이 씀 )

클래스에 달아주고
@Slf4j
log.info("sfad");
log.info("메세지 {}", request.getMethod());
{} 안에 변수값 넣어줌

로그레벨
: 패키지 별로 설정해 줄 수 있음
1. trace : 코드 추적. 실시간으로 까지. 계속 로그 찍음
2. debug : 코드 블록의 문제를 상세히 점검 진단
( 개발 단계에서는 거의 debug로 찍어서 사용 )
3. info : 표준 로그 레벨 / 서비스 시작 종료 등
( 운영 단계에서는 거의 info 단계로 찍어서 사용 )
4. warn : 에러는 아닌데 수상한 정보
5. error : 심각한. 어플 동작하지 않을 정도의 정보
6. fatal : 장애수준. 걍 망한 수준



[ RequestMapping ]
@RequestMapping(value = "/welcome",
method = {RequestMethod.GET, RequestMethod.POST})
: 중괄호로 Request 방식 allow 여러 개 설정해 줄 수 있다.

( 즉, 여러개 쓰고 싶을 땐 GetMapping 같이 못쓰고 이거 써야함 )


[ URL 접두사 매칭 ]
@RequestMapping("/backapi")
public class testController {

클래스 레벨에 @RequestMapping url 붙여두면
기본적으로 저 url 부터 시작한다

/backapi/각mapping URL


[ postman ]
request Body 읽기 ( get - param 으로 안보내고 body 에 숨겨서 보낼 떄
=> postman 실험할 때 body - raw - json 으로 보내자
{
a : a
}


[ dispatcher-servlet ]
: 모든 컨트롤러의 대장역할하는 front controller 대문
=> 하위 Controller 들을 찾음 ( handler Mapping )
=> handler adapter 로 return type 맞춤
=> 응답용 view template 찾음 ( JSP / thymeleaf )
=> 컴파일해서 응답 ( test.html )


[ @RestController ]
== @Controller + @ResponseBody
=> JSon 형식으로 만 쓰는 Controller

[ ResponseEntity<?> ]
응답 return 타입 을 순수 JAVA 타입
예를들어 List<ProductDTO> 로 잡아두면
그 타입 외에 Header 정보나 오류처리를 보낼 수가 없다.

=> ResponseEntity 타입을 사용
=> 오류 처리/ header 보내주기 / 등 처리하고 나서 원하는 데이터를 전송할 수 있다.

1. 오류처리하고, 통과했을 시에 데이터 전송
   @GetMapping("/hi")
   public ResponseEntity<?> hello(String nickname) {
   if (nickname ==null || nickname.equals("")){
   return ResponseEntity.
   badRequest().
   build();
   }
   return ResponseEntity.
   ok()
   .body(nickname + "님 성공");
   }


2. 헤더에 정보담아 보내기
- 응답을 헤더에 커스텀해서 담아서 보낼 수 있음
  => 클라쪽 개발자가 보고 사용

  @GetMapping("/hi")
  public ResponseEntity<?> hello(String nickname) {
  if (nickname ==null || nickname.equals("")){
  return ResponseEntity.
  //                    status(400).
  badRequest().
  body("이름을 입력해야 합니다.");
  //                    build();
  }

        // ResponseEntity객체 사용하면 응답시에 헤더로 키밸류값 줄 수도 있음
        HttpHeaders headers = new HttpHeaders();
        headers.set("jsoninhead", "value1");
        headers.set("secondjson", "value2");


        return ResponseEntity.
                ok()
                .headers(headers)
                .body(nickname + "님 성공!");
  }















