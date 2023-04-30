
# 네이버 책 검색 api 를 통한 책 검색 앱
## 📌 Project Explanation 
<b>책 검색</b>: 책 검색 기능이 존재합니다.<br>
<b>북마크</b> : 마음에 드는 책을 북마크 목록에 저장 및 삭제할 수 있습니다.<br>
<b>최근 검색어</b> : 최근 검색어가 저장되어 최대 10개까지 사용자에게 보여집니다.<br>
<b>책 검색 페이징</b> : 페이징 처리 기능이 정상적으로 동작되지 않습니다. 모든 item들이 보이나, 스크롤이 매끄럽게 이루어지지 않고 있습니다.<br>
<b>etc</b> : 로딩 상태에 프로그레스바, api 에러상황에 대해서 다이얼로그 처리가 되어있습니다.<br>

## 📌 Git Flow
<b>1.</b> feature, fix, refact, hotfix 등 이슈를 등록합니다.<br>
<b>2.</b> [feature, fix, refact/기능] : 해결해야할 기능을 토대로 브랜치를 생성합니다.<br>
<b>3.</b> 작업 후 커밋하여 푸시합니다.<br>
<b>4.</b> 작업이 완료가 되었으면 PR을 요청합니다.<br>
<b>5.</b> PR확인 후 꼼꼼히 살펴본뒤 자체 코드리뷰를 진행합니다.<br>
<b>6.</b> PR까지 완료가 되면 main에 병합한 뒤 테스트가 끝나면 생성했던 브랜치를 삭제합니다.<br>
<p align="center">
<img src="https://user-images.githubusercontent.com/83231344/235337550-c65ab939-47fb-4f82-a7a5-d580e842564a.png" width="30%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337552-27c899ab-0e0d-4c10-9477-34b3f7f69c03.png" width="30%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337554-05fa83f4-81d1-483f-b669-43ba92593469.png" width="30%" height="30%">
</p>

## 📌 Screen Shot
<p align="center">
<img src="https://user-images.githubusercontent.com/83231344/235337087-8966fe7b-dfac-48c4-b42b-9289e6d3def1.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337097-e22315e2-db40-4502-a089-3606dc6d738d.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337104-32e0a404-152c-4397-a9a9-96d26e3ab3ad.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337107-5f1a78ba-1d84-4636-9e38-42dad9cc3afa.png" width="18%" height="30%">
<img src="https://user-images.githubusercontent.com/83231344/235337111-26780afd-5e5f-4948-9372-be057d88223a.png" width="18%" height="30%">
</p>

## 📌 App Architecture
![스크린샷 2023-04-13 오전 9 50 09](https://user-images.githubusercontent.com/83231344/231617438-0c5375d9-03dd-4b52-b97d-e8c0d83b339b.png)

## 📌 Project Structure
<img width="228" alt="스크린샷 2023-05-01 오전 2 46 34" src="https://user-images.githubusercontent.com/83231344/235368205-d7548300-2f8c-42d4-985c-462bd55a7dbe.png">

## 📌 기술 스택(Tech Skill)
 | Category                                                   | Stack                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| **Architecture**| MVVM        |
| **Android Jetpack**|  ViewModel, LiveData, Navigation, Room, View Binding |
| **Dependency Injection**| Hilt       |
| **Networking** | Retrofit2, OkHttp3           |
| **Asynchronous**                 | Coroutine, Flow, StateFlow |
| **Local DB**                 | Room |

## 📌 Version
 | Category                                                   | Version                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| **AndroidStudio**| Electric Eel 2022.1.1        |
| **JavaVersion**|  1.8 |
| **targetSdk**| 33       |
| **minSdk**                 | 24 |

