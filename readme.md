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

- http(s)://34.64.142.55

&nbsp;

&nbsp;

### 2. API

#### 2-1. 노선명 리스트

[Request]

- path : {host}/subway/line
- method : GET
- example

```
http://34.64.142.55/subway/line
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
http://34.64.142.55/subway/station?lineName=09호선
```

&nbsp;

[Response]

- parameters

| name     |             | type       | desc                 |
| -------- | ----------- | ---------- | -------------------- |
| lineName |             | Json Array | 노선별 지하철역 정보 |
|          | lineName    | String     | 노선명               |
|          | stationName | String     | 역 이름              |
|          | stationID   | String     | 역 ID                |

- example

```json
{
    "09호선": [
        {
            "lineName": "09호선",
            "stationName": "석촌고분",
            "stationID": "932"
        },
        {
            "lineName": "09호선",
            "stationName": "석촌",
            "stationID": "933"
        },
      
      ...
      
        {
            "lineName": "09호선",
            "stationName": "언주",
            "stationID": "926"
        }
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
http://34.64.142.55/subway/random?startStationName=당고개&lineName=04호선
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