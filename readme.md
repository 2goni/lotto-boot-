외부 웹 주소 :http://lucky-lotto.site/<br>
로또번호를 사용자의 입력 정보에 따라 추천해주는 프로젝트입니다. <br>
이 프로젝트를 만들 당시 황금비율에 빠져있어서 황금비를 이용한 로또 추천 알고리즘을 적용 했습니다. <br>
사람마다 가지고있는 특성으로 로또번호를 추천 받을수 있으면 재밌을거 같아 제작했습니다.<br>
이 프로젝트는 JAVA, Spring Boot, mariaDB, mustache, aws, rds를 이용해 만들어졌습니다.<br>

<h1> 로또 추천 알고리즘</h1>
랜덤 시드값에서 값하나 저장하고 1.618 곱해서 시드값 초기화 <br>
반복하다 중복 값나오면 중복값 저장 하지않고 저장값이 6개가 될때 까지 반복<br>
  <h3> 초기 </h3>
  로또 랜덤 시드값으로 서버 날짜와 사용자 입력 값(생년월일)을 전부 더한후 1.618(황금비) 곱하기 <br>
  - 문제점 : 2월 4일생과 4월 2일생 같은 로또번호 추천받음 <br>
  <h3> 사용자 이름 받기</h3>
  사용자 이름 아스키 값으로 변환해서 시드값에 더하기<br>
  - 초기 문제점 해결<br>
  - 문제점 : 작성값이 개인정보라서 예민할수 있음<br>
  <h3> 수동값 입력 받기</h3>
  사용자가 원하는 번호 수동 값으로 입력 받아 남은번호 추천 받기<br>
  - 수동 값 먼저 저장 후 추천 번호 메소드 실행 <br>
  - 수동 값 먼저 저장 후 실행시 이상없음 <br>
  - 뷰에서 수동 기능 추가, 컨트롤러 파라미터 추가, VO 추가, Service 변경 완료 <br>

<h1> 로또 DB </h1>
  <h3>lotto_info</3>
  로또 번호와 생성된 날짜, 로또번호들의 중복여부를 확인할 코드, 실행한 브라우저가 저장됨
  <h3>lotto_info</3>
  사용자의 가입 날짜, 아이디, 이메일, 플랫폼이 
