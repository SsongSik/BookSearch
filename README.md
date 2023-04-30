
# 네이버 책 검색 api 를 통한 책 검색 앱
## 📌 Project Explanation 
<b>책 검색</b>: 책 검색 기능이 존재합니다.<br>
<b>북마크</b> : 마음에 드는 책을 북마크 목록에 저장 및 삭제할 수 있습니다.<br>
<b>최근 검색어</b> : 최근 검색어가 저장되어 최대 10개까지 사용자에게 보여집니다.<br>
<b>etx</b> : 로딩 상태에 프로그레스바, api 에러상황에 대해서 다이얼로그 처리가 되어있습니다.<br>
<b>책 검색 페이징</b> : 페이징 처리 기능이 정상적으로 동작되지 않습니다. 모든 item들이 보이나, 스크롤이 매끄럽게 이루어지지 않고 있습니다.<br>

## 📌 Screen Shot
<p align="center">
<img src="https://user-images.githubusercontent.com/83231344/224467941-e221991d-b2f4-47a1-a78b-9de11878a8ac.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/224467946-90a93ae6-9e9b-48c0-abb2-998e73cd257b.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/224467948-fd4ad661-f7e0-434e-9f9b-20b9ba8bd25b.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/224467953-62daf11d-cb3c-484d-8411-e67c3bf8951d.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/224467960-f747fb78-9b05-4c82-b831-3a2c15297a1e.png" width="18%" height="30%">
</p>

## 📌 Wiki(명식이 Android 팀의 기록)
[[Architecture] Clean Architecture 도입의 고민](https://github.com/MYONGSIK/Android/wiki/%5BArchitecture%5D-Clean-Architecture-%EB%8F%84%EC%9E%85%EC%9D%98-%EA%B3%A0%EB%AF%BC)

## 📌 App Architecture
![스크린샷 2023-04-13 오전 9 50 09](https://user-images.githubusercontent.com/83231344/231617438-0c5375d9-03dd-4b52-b97d-e8c0d83b339b.png)


## 📌 History

✔️ <b>2022년 10월 24일 Ver.1 (인문캠퍼스 식단제공 및 평가기능)</b><br>
✔️ <b>2022년 11월 7일 Ver.2 (주변 식당 검색 및 추천, 찜꽁리스트기능)</b><br>
✔️ <b>2023년 3월 6일 Ver.3 (자연캠퍼스 식당 4곳 추가, 리뷰기능)</b><br>
✔️ <b>2023년 3월 27일 Ver.4 (홈화면 개편, 식당 찜꽁리스트 순위 기능 개발, 크래시대응)</b><br>
✔️ <b>2023년 4월 24일 Ver.5 (거리순, 랭킹순 식당조회, 맛집 지도, 위젯으로 식단 조회 기능 개발, 크래시대응)</b><br>
✔️ <b>기능 추가 및 유지보수 예정</b><br>

## 📌 기술 스택(Tech Skill)
 | Category                                                   | Stack                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| **Architecture**| MVVM        |
| **Android Jetpack**|  ViewModel, LiveData, Navigation, Room, Paging, View Binding, ViewPager2 |
| **Dependency Injection**| Hilt       |
| **Networking** | Retrofit2, OkHttp3           |
| **Asynchronous**                 | Coroutine, Flow, StateFlow |
| **Local DB**                 | Room, DataStore |
| **Background**                 | AlarmManager, WorkManager |
| **Firebase**                 | Crashlytics |


