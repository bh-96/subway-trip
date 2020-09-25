# 랜덤 지하철 여행 API V1.0

&nbsp;

&nbsp;

&nbsp;

**작성자 : 김보현**

**작성일자 : 2020-09-18**

&nbsp;

&nbsp;

&nbsp;

### 1. Host

- http://34.64.124.91/

&nbsp;

&nbsp;

### 2. API

#### 2-1. 노선명 리스트

[Request]

- path : {host}/subway/line
- method : GET
- example

```
http://34.64.124.91/subway/line
```

&nbsp;

[Response]

- example

```java
[
    "01호선",
    "02호선",
    "03호선",
    "04호선",
    "05호선",
    "06호선",
    "07호선",
    "08호선",
    "09호선",
    "인천2호선",
    "경의선",
    "분당선",
    "경춘선",
    "경강선",
    "수인선",
    "인천선",
    "공항철도",
    "신분당선",
    "의정부경전철",
    "용인경전철",
    "우이신설경전철",
    "서해선",
    "김포도시철도"
]
```

&nbsp;

&nbsp;

#### 2-2. 노선별 지하철역 정보

[Request]

- path : {host}/subway/station
- method : GET
- parameters

| name     | type   | desc   | 필수값           |
| -------- | ------ | ------ | ---------------- |
| lineName | String | 노선명 | N (default = "") |

- example

```
http://34.64.124.91/subway/station?lineName=04호선
```

&nbsp;

[Response]

- parameters

| name     |             | type       | desc                 |
| -------- | ----------- | ---------- | -------------------- |
| lineName |             | Json Array | 노선별 지하철역 정보 |
|          | lineName    | String     | 노선명               |
|          | stationName | String     | 역 이름              |
|          | starRating  | double     | 별점 (기준 5개)      |
|          | stationID   | String     | 역 ID                |

- example

```json
{
    "04호선": [
        {
            "lineName": "04호선",
            "stationName": "남태령",
            "starRating": 0.0,
            "stationID": "434"
        },
      
      ...
      
        {
            "lineName": "04호선",
            "stationName": "당고개",
            "starRating": 2.5,
            "stationID": "409"
        },
      
      ...
      
    ]
}
```

&nbsp;

&nbsp;

#### 2-3. 랜덤 지하철 여행 경로

[Request]

- path : {host}/subway/random
- method : GET
- parameters

| name             | type   | desc           | 필수값           |
| ---------------- | ------ | -------------- | ---------------- |
| startStationName | String | 출발지 역 이름 | Y                |
| lineName         | String | 노선명         | N (default = "") |

- example

```
http://34.64.124.91/subway/random?startStationName=당고개&lineName=04호선
```

&nbsp;

[Response]

- parameters

| name               |              | type      | desc                        |
| ------------------ | ------------ | --------- | --------------------------- |
| globalStartName    |              | String    | 출발지 역 이름              |
| globalEndName      |              | String    | 도착지 역 이름              |
| globalTravelTime   |              | int       | 전체 운행소요시간(분)       |
| globalDistance     |              | int       | 전체 운행거리(km)           |
| globalStationCount |              | int       | 전체 정차역 수              |
| fare               |              | int       | 카드요금(성인 기준)         |
| cashFare           |              | int       | 현금요금(성인 기준)         |
| driveInfoList      |              | ArrayList | 경로 정보 리스트            |
|                    | laneID       | String    | 승차역 ID                   |
|                    | laneName     | String    | 승차역 호선명               |
|                    | startName    | String    | 승차 역명                   |
|                    | stationCount | int       | 이동 역 수                  |
|                    | wayCode      | int       | 방면코드 (1: 상행, 2: 하행) |
|                    | wayName      | String    | 방면 명                     |
| exchangeInfoList   |              | ArrayList | 환승 정보 리스트            |
|                    | laneName     | String    | 승차노선 명                 |
|                    | startName    | String    | 승차역 명                   |
|                    | exName       | String    | 환승역 명                   |
|                    | exSID        | int       | 환승역 ID                   |
|                    | fastTrain    | int       | 빠른 환승 객차 번호         |
|                    | fastDoor     | int       | 빠른 환승 객차 문 번호      |
|                    | exWalkTime   | int       | 환승소요시간(초)            |
| stationInfoList    |              | ArrayList | 역 정보 리스트              |
|                    | startName    | String    | 출발지 역 명                |
|                    | endName      | String    | 도착지 역 명                |
|                    | startID      | int       | 출발지 ID                   |
|                    | endSID       | int       | 도착지 ID                   |
|                    | travelTime   | int       | 누적 시간 (분)              |



- example

```json
{
    "globalStartName": "당고개",
    "globalEndName": "안산",
    "globalTravelTime": 101,
    "globalDistance": 0,
    "globalStationCount": 44,
    "fare": 2250,
    "cashFare": 2350,
    "driveInfoList": [
        {
            "laneID": "4",
            "laneName": "4호선",
            "startName": "당고개",
            "stationCount": 44,
            "wayCode": 2,
            "wayName": "오이도"
        }
    ],
    "exchangeInfoList": [],
    "stationInfoList": [
        {
            "startName": "당고개",
            "endName": "상계",
            "startID": 409,
            "endSID": 410,
            "travelTime": 2
        },
        {
            "startName": "상계",
            "endName": "노원",
            "startID": 410,
            "endSID": 411,
            "travelTime": 4
        },
        {
            "startName": "노원",
            "endName": "창동",
            "startID": 411,
            "endSID": 412,
            "travelTime": 6
        },
        {
            "startName": "창동",
            "endName": "쌍문",
            "startID": 412,
            "endSID": 413,
            "travelTime": 8
        },
        {
            "startName": "쌍문",
            "endName": "수유(강북구청)",
            "startID": 413,
            "endSID": 414,
            "travelTime": 11
        },
        {
            "startName": "수유(강북구청)",
            "endName": "미아",
            "startID": 414,
            "endSID": 415,
            "travelTime": 13
        },
        {
            "startName": "미아",
            "endName": "미아사거리",
            "startID": 415,
            "endSID": 416,
            "travelTime": 15
        },
        {
            "startName": "미아사거리",
            "endName": "길음",
            "startID": 416,
            "endSID": 417,
            "travelTime": 17
        },
        {
            "startName": "길음",
            "endName": "성신여대입구",
            "startID": 417,
            "endSID": 418,
            "travelTime": 20
        },
        {
            "startName": "성신여대입구",
            "endName": "한성대입구",
            "startID": 418,
            "endSID": 419,
            "travelTime": 22
        },
        {
            "startName": "한성대입구",
            "endName": "혜화",
            "startID": 419,
            "endSID": 420,
            "travelTime": 24
        },
        {
            "startName": "혜화",
            "endName": "동대문",
            "startID": 420,
            "endSID": 421,
            "travelTime": 26
        },
        {
            "startName": "동대문",
            "endName": "동대문역사문화공원",
            "startID": 421,
            "endSID": 422,
            "travelTime": 28
        },
        {
            "startName": "동대문역사문화공원",
            "endName": "충무로",
            "startID": 422,
            "endSID": 423,
            "travelTime": 30
        },
        {
            "startName": "충무로",
            "endName": "명동",
            "startID": 423,
            "endSID": 424,
            "travelTime": 31
        },
        {
            "startName": "명동",
            "endName": "회현",
            "startID": 424,
            "endSID": 425,
            "travelTime": 33
        },
        {
            "startName": "회현",
            "endName": "서울역",
            "startID": 425,
            "endSID": 426,
            "travelTime": 35
        },
        {
            "startName": "서울역",
            "endName": "숙대입구",
            "startID": 426,
            "endSID": 427,
            "travelTime": 37
        },
        {
            "startName": "숙대입구",
            "endName": "삼각지",
            "startID": 427,
            "endSID": 428,
            "travelTime": 39
        },
        {
            "startName": "삼각지",
            "endName": "신용산",
            "startID": 428,
            "endSID": 429,
            "travelTime": 40
        },
        {
            "startName": "신용산",
            "endName": "이촌",
            "startID": 429,
            "endSID": 430,
            "travelTime": 42
        },
        {
            "startName": "이촌",
            "endName": "동작",
            "startID": 430,
            "endSID": 431,
            "travelTime": 46
        },
        {
            "startName": "동작",
            "endName": "총신대입구(이수)",
            "startID": 431,
            "endSID": 432,
            "travelTime": 49
        },
        {
            "startName": "총신대입구(이수)",
            "endName": "사당",
            "startID": 432,
            "endSID": 433,
            "travelTime": 51
        },
        {
            "startName": "사당",
            "endName": "남태령",
            "startID": 433,
            "endSID": 434,
            "travelTime": 52
        },
        {
            "startName": "남태령",
            "endName": "선바위",
            "startID": 434,
            "endSID": 435,
            "travelTime": 55
        },
        {
            "startName": "선바위",
            "endName": "경마공원",
            "startID": 435,
            "endSID": 436,
            "travelTime": 58
        },
        {
            "startName": "경마공원",
            "endName": "대공원",
            "startID": 436,
            "endSID": 437,
            "travelTime": 60
        },
        {
            "startName": "대공원",
            "endName": "과천",
            "startID": 437,
            "endSID": 438,
            "travelTime": 62
        },
        {
            "startName": "과천",
            "endName": "정부과천청사",
            "startID": 438,
            "endSID": 439,
            "travelTime": 64
        },
        {
            "startName": "정부과천청사",
            "endName": "인덕원",
            "startID": 439,
            "endSID": 440,
            "travelTime": 67
        },
        {
            "startName": "인덕원",
            "endName": "평촌",
            "startID": 440,
            "endSID": 441,
            "travelTime": 69
        },
        {
            "startName": "평촌",
            "endName": "범계",
            "startID": 441,
            "endSID": 442,
            "travelTime": 71
        },
        {
            "startName": "범계",
            "endName": "금정",
            "startID": 442,
            "endSID": 443,
            "travelTime": 74
        },
        {
            "startName": "금정",
            "endName": "산본",
            "startID": 443,
            "endSID": 444,
            "travelTime": 78
        },
        {
            "startName": "산본",
            "endName": "수리산",
            "startID": 444,
            "endSID": 445,
            "travelTime": 80
        },
        {
            "startName": "수리산",
            "endName": "대야미",
            "startID": 445,
            "endSID": 446,
            "travelTime": 83
        },
        {
            "startName": "대야미",
            "endName": "반월",
            "startID": 446,
            "endSID": 447,
            "travelTime": 86
        },
        {
            "startName": "반월",
            "endName": "상록수",
            "startID": 447,
            "endSID": 448,
            "travelTime": 90
        },
        {
            "startName": "상록수",
            "endName": "한대앞",
            "startID": 448,
            "endSID": 449,
            "travelTime": 92
        },
        {
            "startName": "한대앞",
            "endName": "중앙",
            "startID": 449,
            "endSID": 450,
            "travelTime": 94
        },
        {
            "startName": "중앙",
            "endName": "고잔",
            "startID": 450,
            "endSID": 451,
            "travelTime": 96
        },
        {
            "startName": "고잔",
            "endName": "초지",
            "startID": 451,
            "endSID": 452,
            "travelTime": 99
        },
        {
            "startName": "초지",
            "endName": "안산",
            "startID": 452,
            "endSID": 453,
            "travelTime": 101
        }
    ]
}
```

&nbsp;

&nbsp;

#### 2-4. 아이디 중복 체크

[Request]

- path : {host}/user/duplicated/account
- method : GET
- parameters

| name    | type   | desc   | 필수값 |
| ------- | ------ | ------ | ------ |
| account | String | 아이디 | Y      |

- example

```
http://34.64.124.91/user/duplicated/account?account=bh
```

&nbsp;

[Response]

- type : boolean
- desc : 중복 - true, 중복 아님 - false

- example

```
false
```

&nbsp;

&nbsp;

#### 2-5. 이메일 중복 체크

[Request]

- path : {host}/user/duplicated/email
- method : GET
- parameters

| name  | type   | desc   | 필수값 |
| ----- | ------ | ------ | ------ |
| email | String | 이메일 | Y      |

- example

```
http://34.64.124.91/user/duplicated/email?email=96bohyun@naver.com
```

&nbsp;

[Response]

- type : boolean
- desc : 중복 - true, 중복 아님 - false

- example

```
false
```

&nbsp;

&nbsp;

#### 2-6. 이메일 인증코드 메일 전송

[Request]

- path : {host}/user/certification
- method : GET
- parameters

| name  | type   | desc   | 필수값 |
| ----- | ------ | ------ | ------ |
| email | String | 이메일 | Y      |

- example

```
{
    "email":"96bohyun@naver.com"
}
```

&nbsp;

[Response]

- type : boolean
- desc : 메일 전송 성공 - true, 실패 - false

- example

```
true
```

&nbsp;

&nbsp;

#### 2-7. 인증

[Request]

- path : {host}/user/check/certcode
- method : GET
- parameters

| name     | type   | desc     | 필수값 |
| -------- | ------ | -------- | ------ |
| email    | String | 이메일   | Y      |
| certCode | String | 인증코드 | Y      |

- example

```
http://34.64.124.91/user/check/certcode?email=96bohyun@naver.com&certCode=kcJ9Dxks
```

&nbsp;

[Response]

- type : boolean
- desc : 인증성공 - true, 인증실패 - false

- example

```
true
```

&nbsp;

&nbsp;

#### 2-8. 회원가입

[Request]

- path : {host}/user
- method : POST
- parameters

| name     | type   | desc                   | 필수값              |
| -------- | ------ | ---------------------- | ------------------- |
| account  | String | 아이디                 | Y                   |
| password | String | 비밀번호 (15자리 이내) | Y                   |
| name     | String | 이름 (닉네임)          | N                   |
| email    | String | 이메일                 | Y                   |
| role     | String | 권한                   | N (관리자일 경우 Y) |

- example

```
{
    "account":"bh",
    "password":"bh1234",
    "name":"김보현",
    "email":"96bohyun@naver.com",
    "role":"ROLE_ADMIN"
}
```

&nbsp;

[Response]

- type : UserDTO
- desc : 회원가입 성공 - UserDTO (200), 필수 파라미터 오류 - (400), 내부작업오류 - null (200)
- parameters

| name     | type   | desc              |
| -------- | ------ | ----------------- |
| id       | int    | 인덱스            |
| account  | String | 아이디            |
| password | String | 암호화된 비밀번호 |
| email    | String | 이메일            |
| role     | String | 권한              |
| regDt    | String | 회원가입 시간     |

- example

```
{
    "id": 1,
    "account": "bh",
    "password": "U67iWLU6jCIl2d5bibkZfQ==",
    "name": "김보현",
    "email": "96bohyun@naver.com",
    "role": "ROLE_ADMIN",
    "regDt": "20200923 1435"
}
```

&nbsp;

&nbsp;

#### 2-9. 로그인

[Request]

- path : {host}/user/login
- method : POST
- parameters

| name     | type   | desc     | 필수값 |
| -------- | ------ | -------- | ------ |
| account  | String | 아이디   | Y      |
| password | String | 비밀번호 | Y      |

- example

```
{
    "account":"bh",
    "password":"bh1234"
}
```

&nbsp;

[Response]

- type : UserDTO
- desc : 로그인 성공 - UserDTO (200), 로그인 실패 - null (200)
- parameters

| name     | type   | desc              |
| -------- | ------ | ----------------- |
| id       | int    | 인덱스            |
| account  | String | 아이디            |
| password | String | 암호화된 비밀번호 |
| email    | String | 이메일            |
| role     | String | 권한              |
| regDt    | String | 회원가입 시간     |

- example

```
{
    "id": 1,
    "account": "bh",
    "password": "U67iWLU6jCIl2d5bibkZfQ==",
    "name": "김보현",
    "email": "96bohyun@naver.com",
    "role": "ROLE_ADMIN",
    "regDt": "20200923 1435"
}
```

&nbsp;

&nbsp;

#### 2-10. 회원정보 수정

[Request]

- path : {host}/user
- method : PATCH
- parameters

| name     | type   | desc                    | 필수값 |
| -------- | ------ | ----------------------- | ------ |
| id       | int    | 인덱스                  | Y      |
| name     | String | 이름                    | N      |
| password | String | 비밀번호                | N      |
| email    | String | 이메일 (재인증 해야 함) | N      |

- example

```
{
    "id":1,
    "password":"bhbh"
}
```

&nbsp;

[Response]

- type : UserDTO
- desc : 회원정보 수정 성공 - UserDTO (200), 필수 파라미터 오류 - null (400), 내부작업오류 - null (200)
- parameters

| name     | type   | desc              |
| -------- | ------ | ----------------- |
| id       | int    | 인덱스            |
| account  | String | 아이디            |
| password | String | 암호화된 비밀번호 |
| email    | String | 이메일            |
| role     | String | 권한              |
| regDt    | String | 회원가입 시간     |

- example

```
{
    "id": 1,
    "account": "bh",
    "password": "D6SYIAATAkqUWoxmmDriTw==",
    "name": "김보현",
    "email": "96bohyun@naver.com",
    "role": "ROLE_ADMIN",
    "regDt": "20200923 1435"
}
```

&nbsp;

&nbsp;

#### 2-11. 회원정보 조회

[Request]

- path : {host}/user/{id}
- method : GET
- parameters

| name | type | desc   | 필수값 |
| ---- | ---- | ------ | ------ |
| id   | int  | 인덱스 | Y      |

- example

```
http://34.64.124.91/user/1
```

&nbsp;

[Response]

- type : UserDTO
- desc : 회원정보 조회 성공 - UserDTO (200), 내부작업오류 - null (200)
- parameters

| name     | type   | desc              |
| -------- | ------ | ----------------- |
| id       | int    | 인덱스            |
| account  | String | 아이디            |
| password | String | 암호화된 비밀번호 |
| email    | String | 이메일            |
| role     | String | 권한              |
| regDt    | String | 회원가입 시간     |

- example

```
{
    "id": 1,
    "account": "bh",
    "password": "D6SYIAATAkqUWoxmmDriTw==",
    "name": "김보현",
    "email": "96bohyun@naver.com",
    "role": "ROLE_ADMIN",
    "regDt": "20200923 1435"
}
```

&nbsp;

&nbsp;

#### 2-12. 회원탈퇴

[Request]

- path : {host}/user/{id}/{password}
- method : DELETE
- parameters

| name     | type   | desc     | 필수값 |
| -------- | ------ | -------- | ------ |
| id       | int    | 인덱스   | Y      |
| password | String | 비밀번호 | Y      |

- example

```
http://34.64.124.91/user/1/bh1234
```

&nbsp;

[Response]

- type : boolean
- desc : 회원정보 삭제 성공 - true, 회원정보 삭제 실패 - false

- example

```
true
```

&nbsp;

&nbsp;

#### 2-13. 회원정보 리스트 조회 (관리자)

[Request]

- path : {host}/user
- method : GET
- example

```
http://34.64.124.91/user
```

&nbsp;

[Response]

- type : List<UserDTO>
- desc : 회원정보 리스트 조회 성공 - List<UserDTO>, 회원정보 리스트 조회 실패 - null
- parameters

| name     | type   | desc              |
| -------- | ------ | ----------------- |
| id       | int    | 인덱스            |
| account  | String | 아이디            |
| password | String | 암호화된 비밀번호 |
| email    | String | 이메일            |
| role     | String | 권한              |
| regDt    | String | 회원가입 시간     |

- example

```
[
    {
        "id": 2,
        "account": "bh",
        "password": "U67iWLU6jCIl2d5bibkZfQ==",
        "name": "김보현",
        "email": "96bohyun@naver.com",
        "role": "ROLE_ADMIN",
        "regDt": "20200923 1449"
    }
]
```

&nbsp;

&nbsp;

#### 2-14. 권한 변경 (관리자)

[Request]

- path : {host}/user/role
- method : PATCH
- parameters

| name          | type   | desc            | 필수값 |
| ------------- | ------ | --------------- | ------ |
| adminId       | int    | 관리자 인덱스   | Y      |
| userId        | int    | 사용자 인덱스   | Y      |
| adminPassword | String | 관리자 비밀번호 | Y      |

- example

```
http://34.64.124.91/user/role?adminId=2&userId=3&adminPassword=bh1234
```

&nbsp;

[Response]

- type : boolean
- desc : 회원권한 변경 성공 - true, 회원권한 변경 실패 - false

- example

```
true
```

&nbsp;

&nbsp;

#### 2-15. 리뷰 파일 등록

[Request]

- path : {host}/review/img
- method : POST
- parameters

| name | type          | desc             | 필수값 |
| ---- | ------------- | ---------------- | ------ |
| file | MultipartFile | 리뷰 이미지 파일 | Y      |

&nbsp;

[Response]

- type : String
- desc : 저장된 파일 경로 및 이름 (리턴된 파일명을 ReviewDTO reviewImage 에 써 주면 된다.)
- example

```
20200924/5.png
```

&nbsp;

&nbsp;

#### 2-16. 리뷰 등록

[Request]

- path : {host}/review/{userId}
- method : POST
- parameters

| name        | type   | desc                                         | 필수값 |
| ----------- | ------ | -------------------------------------------- | ------ |
| userId      | int    | 리뷰 작성자 (사용자) 인덱스                  | Y      |
| lineName    | String | 노선명                                       | Y      |
| stationName | String | 지하철역 명                                  | Y      |
| title       | String | 제목                                         | Y      |
| content     | String | 내용                                         | Y      |
| reviewImage | String | 리뷰 이미지 (2-15. 리뷰파일등록 리턴 파일명) | N      |
| star        | int    | 별점 (default : 0 -> 0~5)                    | N      |

- example

```
{
    "lineName":"01호선",
    "stationName":"부천",
    "title":"만두가게",
    "content":"존맛탱",
    "reviewImage":"20200924/5.png"
}
```

&nbsp;

[Response]

- type : ReviewDTO
- desc : 리뷰 저장 성공 - ReviewDTO (200), 필수 파라미터 오류 - (400), 내부작업오류 - null (200)
- parameters

| name        |          | type    | desc               |
| ----------- | -------- | ------- | ------------------ |
| id          |          | int     | 리뷰 인덱스        |
| user        |          | UserDTO | 사용자 정보        |
|             | id       | int     | 사용자 인덱스      |
|             | account  | String  | 아이디             |
|             | password | String  | 비밀번호           |
|             | name     | String  | 이름               |
|             | email    | String  | 이메일             |
|             | role     | String  | 권한               |
|             | regDt    | String  | 회원가입 일시      |
| lineName    |          | String  | 노선명             |
| stationName |          | String  | 지하철역 명        |
| title       |          | String  | 제목               |
| content     |          | String  | 내용               |
| reviewImage |          | String  | 리뷰 이미지        |
| star        |          | int     | 별점 (default : 0) |
| regDt       |          | String  | 등록시간           |
| modDt       |          | String  | 수정시간           |



- example

```
{
    "id": 1,
    "user": {
        "id": 2,
        "account": "bh",
        "password": "U67iWLU6jCIl2d5bibkZfQ==",
        "name": "김보현",
        "email": "96bohyun@naver.com",
        "role": "ROLE_ADMIN",
        "regDt": "20200923 1449"
    },
    "lineName": "01호선",
    "stationName": "부천역",
    "title": "만두가게",
    "content": "존맛탱",
    "reviewImage": "20200924/5.png",
    "star": 0,
    "regDt": "20200925 0803",
    "modDt": null
}
```

&nbsp;

&nbsp;

#### 2-17. 리뷰 조회

[Request]

- path : {host}/review/{reviewId}
- method : GET
- parameters

| name     | type | desc        | 필수값 |
| -------- | ---- | ----------- | ------ |
| reviewId | int  | 리뷰 인덱스 | Y      |

- example

```
http://34.64.124.91/review/1
```

&nbsp;

[Response]

- type : ReviewDTO
- desc : 리뷰 조회 성공 - ReviewDTO (200), 필수 파라미터 오류 - (400), 내부작업오류 - null (200)
- parameters

| name        |          | type    | desc               |
| ----------- | -------- | ------- | ------------------ |
| id          |          | int     | 리뷰 인덱스        |
| user        |          | UserDTO | 사용자 정보        |
|             | id       | int     | 사용자 인덱스      |
|             | account  | String  | 아이디             |
|             | password | String  | 비밀번호           |
|             | name     | String  | 이름               |
|             | email    | String  | 이메일             |
|             | role     | String  | 권한               |
|             | regDt    | String  | 회원가입 일시      |
| lineName    |          | String  | 노선명             |
| stationName |          | String  | 지하철역 명        |
| title       |          | String  | 제목               |
| content     |          | String  | 내용               |
| reviewImage |          | String  | 리뷰 이미지        |
| star        |          | int     | 별점 (default : 0) |
| regDt       |          | String  | 등록시간           |
| modDt       |          | String  | 수정시간           |



- example

```
{
    "id": 1,
    "user": {
        "id": 2,
        "account": "bh",
        "password": "U67iWLU6jCIl2d5bibkZfQ==",
        "name": "김보현",
        "email": "96bohyun@naver.com",
        "role": "ROLE_ADMIN",
        "regDt": "20200923 1449"
    },
    "lineName": "01호선",
    "stationName": "부천역",
    "title": "만두가게",
    "content": "존맛탱",
    "reviewImage": "20200924/5.png",
    "star": 0,
    "regDt": "20200925 0803",
    "modDt": null
}
```

&nbsp;

&nbsp;

#### 2-18. 리뷰 업데이트

[Request]

- path : {host}/review/{userId}
- method : PATCH
- parameters

| name        | type   | desc                                         | 필수값 |
| ----------- | ------ | -------------------------------------------- | ------ |
| userId      | int    | 리뷰 작성자 (사용자) 인덱스                  | Y      |
| id          | int    | 리뷰 인덱스                                  | Y      |
| lineName    | String | 노선명                                       | N      |
| stationName | String | 지하철역 명                                  | N      |
| title       | String | 제목                                         | N      |
| content     | String | 내용                                         | N      |
| reviewImage | String | 리뷰 이미지 (2-15. 리뷰파일등록 리턴 파일명) | N      |
| star        | int    | 별점 (default : 0)                           | N      |

- example

```
http://34.64.124.91/review/2

{
    "id":1,
    "title":"맛집만두"
}
```

&nbsp;

[Response]

- type : ReviewDTO
- desc : 리뷰 업데이트 성공 - ReviewDTO (200), 필수 파라미터 오류 - (400), 내부작업오류 - null (200)
- parameters

| name        |          | type    | desc               |
| ----------- | -------- | ------- | ------------------ |
| id          |          | int     | 리뷰 인덱스        |
| user        |          | UserDTO | 사용자 정보        |
|             | id       | int     | 사용자 인덱스      |
|             | account  | String  | 아이디             |
|             | password | String  | 비밀번호           |
|             | name     | String  | 이름               |
|             | email    | String  | 이메일             |
|             | role     | String  | 권한               |
|             | regDt    | String  | 회원가입 일시      |
| lineName    |          | String  | 노선명             |
| stationName |          | String  | 지하철역 명        |
| title       |          | String  | 제목               |
| content     |          | String  | 내용               |
| reviewImage |          | String  | 리뷰 이미지        |
| star        |          | int     | 별점 (default : 0) |
| regDt       |          | String  | 등록시간           |
| modDt       |          | String  | 수정시간           |



- example

```
{
    "id": 1,
    "user": {
        "id": 2,
        "account": "bh",
        "password": "U67iWLU6jCIl2d5bibkZfQ==",
        "name": "김보현",
        "email": "96bohyun@naver.com",
        "role": "ROLE_ADMIN",
        "regDt": "20200923 1449"
    },
    "lineName": "01호선",
    "stationName": "부천역",
    "title": "맛집만두",
    "content": "존맛탱",
    "reviewImage": "20200924/5.png",
    "star": 0,
    "regDt": "20200925 0803",
    "modDt": "20200925 0917"
}
```

&nbsp;

&nbsp;

#### 2-19. 리뷰 삭제

[Request]

- path : {host}/review/{userId}/{reviewId}
- method : DELETE
- parameters

| name     | type | desc                        | 필수값 |
| -------- | ---- | --------------------------- | ------ |
| userId   | int  | 리뷰 작성자 (사용자) 인덱스 | Y      |
| reviewId | int  | 리뷰 인덱스                 | Y      |

- example

```
http://34.64.124.91/review/2/1
```

&nbsp;

[Response]

- type : boolean
- desc : 리뷰 삭제 성공 - true, 실패 - false
- example

```
true
```

&nbsp;

&nbsp;

#### 2-20. 지하철역 명으로 리뷰 리스트 조회

[Request]

- path : {host}/review/list/station
- method : GET
- parameters

| name        | type   | desc                                                | 필수값 |
| ----------- | ------ | --------------------------------------------------- | ------ |
| stationName | String | 지하철역 명                                         | Y      |
| sortParam   | String | 정렬 파라미터 명 (별점 순 : star, default : 최신순) | N      |

- example

```
http://34.64.124.91/review/list/station?stationName=당고개
http://34.64.124.91/review/list/station?stationName=당고개&sortParam=star
```

&nbsp;

[Response]

- type : List<ReviewDTO>
- desc : 리뷰 리스트 조회 성공 - ReviewDTO (200), 없거나 내부작업오류 - null (200)
- parameters

| name        |          | type    | desc               |
| ----------- | -------- | ------- | ------------------ |
| id          |          | int     | 리뷰 인덱스        |
| user        |          | UserDTO | 사용자 정보        |
|             | id       | int     | 사용자 인덱스      |
|             | account  | String  | 아이디             |
|             | password | String  | 비밀번호           |
|             | name     | String  | 이름               |
|             | email    | String  | 이메일             |
|             | role     | String  | 권한               |
|             | regDt    | String  | 회원가입 일시      |
| lineName    |          | String  | 노선명             |
| stationName |          | String  | 지하철역 명        |
| title       |          | String  | 제목               |
| content     |          | String  | 내용               |
| reviewImage |          | String  | 리뷰 이미지        |
| star        |          | int     | 별점 (default : 0) |
| regDt       |          | String  | 등록시간           |
| modDt       |          | String  | 수정시간           |



- example

```
[
    {
        "id": 5,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "옷가게",
        "content": "다양하지 않다.",
        "reviewImage": null,
        "star": 1,
        "regDt": "20200925 0932",
        "modDt": null
    },
    {
        "id": 4,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "옛날통닭",
        "content": "별로",
        "reviewImage": null,
        "star": 1,
        "regDt": "20200925 0931",
        "modDt": null
    },
    {
        "id": 3,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "토스트",
        "content": "최고",
        "reviewImage": null,
        "star": 4,
        "regDt": "20200925 0931",
        "modDt": null
    }
]
```

&nbsp;

&nbsp;

#### 2-21. 사용자별 리뷰 리스트 조회

[Request]

- path : {host}/review/list/user
- method : GET
- parameters

| name        | type   | desc                                                | 필수값 |
| ----------- | ------ | --------------------------------------------------- | ------ |
| stationName | String | 지하철역 명                                         | Y      |
| sortParam   | String | 정렬 파라미터 명 (별점 순 : star, default : 최신순) | N      |

- example

```
http://34.64.124.91/review/list/user?userId=2
http://34.64.124.91/review/list/user?userId=2&sortParam=star
```

&nbsp;

[Response]

- type : List<ReviewDTO>
- desc : 리뷰 리스트 조회 성공 - ReviewDTO (200), 없거나 내부작업오류 - null (200)
- parameters

| name        |          | type    | desc               |
| ----------- | -------- | ------- | ------------------ |
| id          |          | int     | 리뷰 인덱스        |
| user        |          | UserDTO | 사용자 정보        |
|             | id       | int     | 사용자 인덱스      |
|             | account  | String  | 아이디             |
|             | password | String  | 비밀번호           |
|             | name     | String  | 이름               |
|             | email    | String  | 이메일             |
|             | role     | String  | 권한               |
|             | regDt    | String  | 회원가입 일시      |
| lineName    |          | String  | 노선명             |
| stationName |          | String  | 지하철역 명        |
| title       |          | String  | 제목               |
| content     |          | String  | 내용               |
| reviewImage |          | String  | 리뷰 이미지        |
| star        |          | int     | 별점 (default : 0) |
| regDt       |          | String  | 등록시간           |
| modDt       |          | String  | 수정시간           |



- example

```
[
    {
        "id": 5,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "옷가게",
        "content": "다양하지 않다.",
        "reviewImage": null,
        "star": 1,
        "regDt": "20200925 0932",
        "modDt": null
    },
    {
        "id": 4,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "옛날통닭",
        "content": "별로",
        "reviewImage": null,
        "star": 1,
        "regDt": "20200925 0931",
        "modDt": null
    },
    {
        "id": 3,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "04호선",
        "stationName": "당고개",
        "title": "토스트",
        "content": "최고",
        "reviewImage": null,
        "star": 4,
        "regDt": "20200925 0931",
        "modDt": null
    },
    {
        "id": 2,
        "user": {
            "id": 2,
            "account": "bh",
            "password": "U67iWLU6jCIl2d5bibkZfQ==",
            "name": "김보현",
            "email": "96bohyun@naver.com",
            "role": "ROLE_ADMIN",
            "regDt": "20200923 1449"
        },
        "lineName": "01호선",
        "stationName": "부천",
        "title": "만두가게",
        "content": "존맛탱",
        "reviewImage": null,
        "star": 3,
        "regDt": "20200925 0931",
        "modDt": "20200925 0933"
    }
]
```

&nbsp;

&nbsp;

#### 2-22. 인기있는  지하철역 추천

[Request]

- path : {host}/recommendation
- method : GET
- parameters

| name     | type   | desc                                 | 필수값          |
| -------- | ------ | ------------------------------------ | --------------- |
| type     | int    | 필터 (0 : 리뷰 많은 순, 1 : 별점 순) | N (default : 0) |
| lineName | String | 노선명                               | N               |

- example

```
localhost/recommendation?type=0
```

&nbsp;

[Response]

- type : List<AboutReviewCount>, List<AboutStarRating>
- desc : 인기 지하철역 리스트 조회 성공 - List<RecommendationDTO> (200), 없거나 내부작업오류 - null (200)

&nbsp;

**[type = 0 리뷰 많은 순]**

- parameters

| name        | type   | desc        |
| ----------- | ------ | ----------- |
| lineName    | String | 노선명      |
| stationName | String | 지하철역 명 |
| count       | int    | 리뷰 수     |

- example

```
[
    {
        "lineName": "04호선",
        "stationName": "당고개",
        "count": 5
    },
    {
        "lineName": "01호선",
        "stationName": "부천",
        "count": 2
    }
]
```

&nbsp;

**[type = 2 별점 순]**

- parameters

| name        | type   | desc            |
| ----------- | ------ | --------------- |
| lineName    | String | 노선명          |
| stationName | String | 지하철역 명     |
| avgStar     | double | 별점 (5개 기준) |

- example

```
[
    {
        "lineName": "04호선",
        "stationName": "당고개",
        "avgStar": 2.5
    },
    {
        "lineName": "01호선",
        "stationName": "부천",
        "avgStar": 2.0
    }
]
```

