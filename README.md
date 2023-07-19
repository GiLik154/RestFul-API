<details>
<summary>직원 API 문서</summary>

## 1. GET /employee/info/{employeeId}

- **설명**: 특정 직원의 프로필 정보를 조회합니다.
- **URL**: `/employee/info/{employeeId}`
- **메소드**: GET
- **파라미터**:
    - `employeeId` (Long): 조회할 직원의 고유 식별자입니다.
- **응답**:
    - 성공: 직원 프로필 정보를 JSON 형식으로 반환합니다.
      ```json
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "123-456-7890",
        "hireDate": "2023-07-19",
        "jobId": "MANAGER",
        "salary": 50000,
        "commissionPct": 0.1,
        "managerId": 2,
        "departmentId": 3
      }
      ```
    - 실패: 해당 직원이 없는 경우 view단에서 error msg를 반환합니다.

## 2. GET /employee/history/{employeeId}

- **설명**: 특정 직원의 직무 이력을 조회합니다.
- **URL**: `/employee/history/{employeeId}`
- **메소드**: GET
- **파라미터**:
    - `employeeId` (Long): 조회할 직원의 고유 식별자입니다.
- **응답**:
    - 성공: 직무 이력 목록을 JSON 배열 형식으로 반환합니다.
      ```json
      [
        {
          "id": 1,
          "startDate": "2023-01-01",
          "endDate": "2023-06-30",
          "jobId": "MANAGER",
          "departmentId": 3
        },
        {
          "id": 2,
          "startDate": "2023-07-01",
          "endDate": null,
          "jobId": "DEVELOPER",
          "departmentId": 3
        }
      ]
      ```
    - 실패: 해당 직원의 직무 이력이 없는 경우 view단에서 error msg를 반환합니다.

## 3. PUT /employee/update

- **설명**: 특정 부서 직원들의 급여를 일괄적으로 업데이트합니다.
- **URL**: `/employee/update`
- **메소드**: PUT
- **파라미터**:
    - `departmentId` (Long): 업데이트 대상 부서의 고유 식별자입니다.
    - `percent` (int): 급여 인상률을 백분율로 표현한 값입니다.
- **응답**:
    - 성공: 부서 직원들의 급여가 업데이트됩니다.

</details>



<details>
<summary>부서 API 문서</summary>

## 1. 부서 정보 조회 API

### GET /department/info/{departmentId}

**설명**
특정 부서의 정보를 조회합니다.

**URL**
`/department/info/{departmentId}`

**메소드**
GET

**파라미터**
- `departmentId` (Long): 조회할 부서의 고유 식별자입니다.

**응답**
성공: 부서 정보를 JSON 형식으로 반환합니다.

   ```json
   {
     "id": 1,
     "name": "인사부",
     "location": "서울"
   }
   ```

- 실패: 해당 부서가 없을 경우 view단에서 error msg를 반환합니다.

</details>



<details>
   <summary>날씨 정보 조회 API</summary>

- **설명**: 특정 지역의 날씨 정보를 조회합니다.
- **URL**: `/api`
- **메소드**: GET
- **파라미터**:
    - `nx` (int): 예보지점의 X 좌표값
    - `ny` (int): 예보지점의 Y 좌표값
- **응답**:
    - 성공: 날씨 정보를 JSON 형식으로 반환합니다.
      ```json
      {
        "response": {
          "header": {
            "resultCode": "00",
            "resultMsg": "NORMAL_SERVICE"
          },
          "body": {
            "items": {
              "item": [
                {
                  "baseDate": "20230718",
                  "baseTime": "0600",
                  "category": "T1H",
                  "nx": 55,
                  "ny": 127,
                  "obsrValue": "28.2"
                },
                {
                  "baseDate": "20230718",
                  "baseTime": "0600",
                  "category": "REH",
                  "nx": 55,
                  "ny": 127,
                  "obsrValue": "60.4"
                },
                {
                  "baseDate": "20230718",
                  "baseTime": "0600",
                  "category": "UUU",
                  "nx": 55,
                  "ny": 127,
                  "obsrValue": "2.4"
                },
                {
                  "baseDate": "20230718",
                  "baseTime": "0600",
                  "category": "VVV",
                  "nx": 55,
                  "ny": 127,
                  "obsrValue": "3.2"
                }
              ]
            }
          }
        }
      }
      ```
    - 실패: API 서버가 응답하지 않을 경우, view단에서 error msg를 반환합니다.

</details>
