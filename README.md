## commit messages


(0) 24-02-28: 세팅 및 mybatis를 위한 테스트 코드 작성


(1) 24-02-29: 공통 템플릿 및 css,js 세팅

-  헤더, 왼쪽 사이드 메뉴, 콘텐츠 파트로 구성


(2) 24-02-29: bootstrap css 변경


(3) 24-02-29 

- swagger 추가
- summernote 구현
- css, js 일단 풀어서 추가 (추후 min.js로 묶기)

```js
// 추후 view 화면
// html : <div class="form-control" rows="10" style="height: 500px;" wrap="soft" id="view-content" name="view-content" readonly></div>
$("#view-content").html(code)
```


(4) 24-02-29: util.js 추가


(5) 24-03-04
1. tus & uppy 기능 추가
2. 파일 첨부 기능 file.js로 구현
3. 파일 첨부 및 저장 로직 샘플 구현
4. 생성자, 수정자 컬럼 -> 공통으로 빼기


(6) 24-03-05 : dto 및 vo 주석 설명 주석 추가


(7) 24-03-05
1. tus 와 uppy를 이용한 파일 업로드 추가 수정 구현
2. 파일 다운로드 구현
3. 파일 업로드를 이용한 기본 c,r,u 구현


(8) 24-03-05 :  tus, uppy를 통한 파일 업로드 오류 수정


(9) 24-03-06
1. 기존에는 file_uid 0으로 임시저장 후 최종 저장의 로직으로 구현
  - 이번에는 마지막 저장 시점에 한번에 파일 정보를 저장
  - 이유: 만약 최종 저장을 안하게 될 경우 쓰레기 데이터가 너무 많이 쌓임
  - 이로 인해 temp file에 대해 db에서 삭제 로직 삭제
  - 한번에 최종저장을 위해 파일 업로드 완료 시점에 fileId를 header을 통해 넘겨줌
3. tus, common file 영역 확실히 구분


(10) 24-03-06
1. 이메일 구현
2. response 더 세분화


(11) 24-03-06
1. mybatis config 파일 구현
2. sqlSessionFactory 빈 등록
3. mybatis 로그 출력 구현


(12) 24-03-07 
1. /error 호출되는 문제 해결
  - css, js 파일이 없어서 생겼던 문제



(13) 24-03-08 : @value 경로 변경



(14) 24-03-12 : regid, mdfrid를 위한 aspect 추가



(15) 24-03-12
1. xssfilter 적용
   - post, put : requestBody
   - get, delete : queryString
   - & < >  에 대해 인코딩 적용

  

(16) 24-03-13
1. XSSFilter : '/api' uri에 대해서만 진행
2. mybatis log 정보 추가\



(17) 24-03-13
1. security 추가
   - 로그인이전시점: /api/front/**
   - ADMIN: /api/admin/**
   - SUPER: /api/admin/**, /api/super/**
2. 같은 쿼리 2번씩 저장되는 문제 해결 : MybatisInterceptor에서 @Signature->method 문제인듯



(18) 24-03-13 : mybatis-config 파일과 MyBatisConfig 파일 합침



(19) 24-03-13
- 쿼리 2번 수행 문제 해결 : invocation.proceed() 메서드 2번 수행의 문제였음 -> 한번만 수행되도록 해결
- @Signature 메서드를 prepare 로 하게 될 경우 결과값에 대한 반환에 대해 출력이 불가능해짐


(20) 24-03-14 : csrf 비활성화


(21) 24-03-14 : csrf 활성화 -> 토큰으로 관리



(22) 24-03-15 
1. 리퍼러 정책 : same-origin 추가
2. cors 정책 추가
3. xss 공격 : 기본 xss protection 적용


(23) 24-03-15 : error html


(23) 24-03-18 : 기본 로그인 성공 및 실패 핸들링 기초 만들기


(24) 24-03-19 : 로그인 실패 처리


(25) 24-03-19 : fullCalendar 모듈 추가 (현재 작업중)


(26) 24-04-18 : fullCalendar랑 db랑 연결


(27) 24-04-19 : fullCalendar 정리


(28) 24-04-19 : fullCalendar css 수정


(29) 24-04-22 : menu principaldetail


(30) 24-04-22 : 소셜 로그인 (카카오, 네이버, 구글)


(31) 24-04-23 : jstree 기능 추가 및 해당 트리를 이용해 메뉴 관리 화면 구현


(32) 24-04-24 : 로그인 스타일 적용 및 프로젝트 컬러 #ff69b4 로 지정
- 소셜 로그인 이미지 버튼 변경


(33) 24-04-24 : swagger 태그 추가 및 로그찍는곳에 params 추가


(34) 24-04-24 : yml 파일 암호화


(35) 24-04-24 : 아이디 찾기, 비밀번호 초기화


(36) 24-04-26 
- 휴먼계정풀기 (이메일 인증)
- 비밀번호 만료 (90일 연장, 비밀번호 변경)


(37) 24-04-29
- 회원가입
- 소셜 회원가입시, 이메일 중복 체크


(38) 24-04-30
- 템플릿 css 변경
- before layout 추가


(39) 24-04-30 : 에러 화면


(40) 24-05-02 : 메뉴접기 버튼 추가


(41) 24-05-02 : bootstrap 색상 변경


(42) 24-05-03
* user -> front/after로 나눔
* 회원정보 변경 로직 구성


(43) 24-05-08
1. 사용자 관리 화면 일단 구성
2. tabulator 적용
3. 소셜 로그인 성공시, 비번 변경날짜 업데이트
4. 기본 화면 바디 title 조각 추가


(44) 24-05-08
1. 사용자 관리 : 비활성화/활성화, 계정잠금풀기
2. 소셜 로그인에 대해서도 실패 exception 터트리기
3. 소셜 로그인에 대해서 성공핸들러에서 id값얻어서 비번 변경날짜 초기화
4. 소셜 로그인에 대해서도 메뉴 함께 로딩해오기
5. OAuth2ErrorCustom 추가 변경 필요



(45) 24-05-09
1. 사용자 관리 : 비번 만료 / 휴먼 계정 사용자에게 이메일 전송
2. OAuth2ErrorCustom 적용
3. 쇼셜 로그인 성공시 비번 변경일자 오늘로부터 90일뒤로 변경


(46) 24-05-10
1. 코드 관리 : 상위 코드 추가/삭제/수정
2. $.ajax 정리
3. aspect deletMapping 추가


(47) 24-05-10 : menu active


(48-49) 24-05-10 : 자식 코드 추가/수정/삭제


(50) 24-05-10 : 1. ckeditor 변경
2. summernote view 용 test 추가 (추후 사용시 summernote.disable() 및 summernote 추가 style적용도 함께 사용해야함)
