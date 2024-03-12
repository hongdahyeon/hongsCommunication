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
