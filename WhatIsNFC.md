# nfc에 대한 이해
작성일 : 21.01.13

안드로이드에서 NFC태그를 사용할 프로젝트가 생겼다.
따라서 해당하는 문서를 보고 필요한 내용을 정리해본다.

[developer 문서 가이드 페이지](https://developer.android.com/guide/topics/connectivity/nfc?hl=ko)

---
## NFC(근거리 무선통신) 개요

NFC : Near Field Communication

  NFC는 단거리 무선 기술로서, 일반적으로 연결을 시작하기 위해서는 거리가 4cm 이하가 되어야 한다. 
  태그에 저장되는 데이터를 다양한 형식으로 작성할 수도 있지만, 다수의 Android 프레임워크 API는 NDEF(NFC Data Exchange Format)라는 NFC Forum 표준을 기반한다.

NFC 사용 가능 Android 기기 에서는 다음 세 가지 기본 작업 모드를 지원한다.

1. **읽기/쓰기 모드** : NFC 기기가 수동 NFC 태그 및 스티커를 읽고 쓸 수 있다.
2. **P2P 모드** : NFC 기기가 다른 NFC 피어와 데이터를 교환할 수 있다. Android Beam에서 이러한 작업 모드 사용
3. **카드 에뮬레이션 모드** : NFC 기기 자체가 NFC 카드 자체로 작동하는 모드. NFC POS 단말과 같은 외부 NFC 판독기에서 애뮬레이션된 NFC 카드에 액세스 할 수 있다.


여기서 내가 사용하려고 하는 NFC 모드는 태그/스티커를 사용할 것이기 때문에 ::**읽기/쓰기 모드**::를 사용할 것이다.

NDEF는 jpg,png와 같은 확장자처럼 이해할 수 있을 것 같다. NFC에서는 NDEF 방식으로 데이터를 저장한다.

<img width="606" alt="스크린샷 2021-01-13 오후 6 17 05" src="https://user-images.githubusercontent.com/61059893/119166486-a4a56f00-ba99-11eb-99fb-7fd138dba749.png">

NDEF는 위와 같이 구성되어있다. 
[안드로이드 NFC 읽기/쓰기 : 네이버 블로그](https://m.blog.naver.com/ninace/80211294933)
---
## 안드로이드 NFC
앱에서 NFC를 사용하기 위해서는 manifest에서
```
<uses-feature
    android:name="android.hardware.nfc"
    android:required="false"/> 
<uses-permission android:name="android.permission.NFC"/>
```
이 코드를 작성해 주어야한다. 

Android 단말에서는 주로 NDEF 메시지를 Tag로 부터 읽어들이거나 NDEF 메시지를 다른 단말로 전송하는데 사용한다.
---
## NDEF 메시지
* NFC에서 데이터를 교환할 때 사용되는 데이터 Format
* NDEF Message는 여러개의 NDEF Record로 구성되어있다.
* NDEF Record는 Type, ID, Payload(Data)를 담을 수 있는 구조로 되어 있다.
* 하나의 데이터는 하나의 NDEF Message로 만들어 지며, 여러 개의 NDEF Record로 분할되어 전송 가능 하다.

---
## NFC 수신
* 단말이 NFC Tag를 수신하면 NDEF 메시지인지 확인하여 NDEF 메시지인 경우 NDEF_DISCOVERED를 가진 Activity중 처리할 Activity를 찾아 전달한다.
* NDEF 메시지가 아니거나 처리할 Activity 없으면, TECH_DISCOVERED를 가진 Activity 중 처리할 Activity를 찾아 전달한다.
* 처리할 Activity가 없으면, TAG_DISCOVERED를 가지고 있는 Activity로 TAG를 전달한다.

<img width="620" alt="스크린샷 2021-01-13 오후 8 50 04" src="https://user-images.githubusercontent.com/61059893/119166526-af600400-ba99-11eb-84d5-f0c9389bd8ea.png">


