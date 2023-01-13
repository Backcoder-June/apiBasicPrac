[ Spring Boot API ]   
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















