
**拾花酿春 RESTful API**


**简介**：展示先做基础功能，后面再添加业务

**HOST**:localhost:9999


**联系人**:


**Version**:1.0

**接口路径**：/v2/api-docs


# class-info-controller

## list


**接口说明**:



**接口地址**:`/score/classInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeIds|   | query | false |array  | string   |
|gradeInfo.gradeId|   | query | false |string  |    |
|gradeInfo.gradeName|   | query | false |string  |    |
|gradeInfo.insertTime|   | query | false |string  |    |
|gradeInfo.remark|   | query | false |string  |    |
|gradeInfo.status|   | query | false |integer  |    |
|gradeInfo.updateTime|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentCount|   | query | false |integer  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/classInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/classInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"classId": "",
	"className": "",
	"classType": "",
	"gradeClassName": "",
	"gradeId": "",
	"gradeInfo": {
		"gradeId": "",
		"gradeName": "",
		"insertTime": "",
		"remark": "",
		"status": 0,
		"updateTime": ""
	},
	"insertTime": "",
	"remark": "",
	"status": 0,
	"studentCount": 0,
	"teacherId": "",
	"teacherName": "",
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classInfo| classInfo  | body | true |ClassInfo  | ClassInfo   |

**schema属性说明**



**ClassInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classType|   | body | false |string  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeInfo|   | body | false |GradeInfo  | GradeInfo   |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentCount|   | body | false |int32  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |

**GradeInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/classInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeIds|   | query | false |array  | string   |
|gradeInfo.gradeId|   | query | false |string  |    |
|gradeInfo.gradeName|   | query | false |string  |    |
|gradeInfo.insertTime|   | query | false |string  |    |
|gradeInfo.remark|   | query | false |string  |    |
|gradeInfo.status|   | query | false |integer  |    |
|gradeInfo.updateTime|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentCount|   | query | false |integer  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/classInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeIds|   | query | false |array  | string   |
|gradeInfo.gradeId|   | query | false |string  |    |
|gradeInfo.gradeName|   | query | false |string  |    |
|gradeInfo.insertTime|   | query | false |string  |    |
|gradeInfo.remark|   | query | false |string  |    |
|gradeInfo.status|   | query | false |integer  |    |
|gradeInfo.updateTime|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentCount|   | query | false |integer  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/classInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"classId": "",
	"className": "",
	"classType": "",
	"gradeClassName": "",
	"gradeId": "",
	"gradeInfo": {
		"gradeId": "",
		"gradeName": "",
		"insertTime": "",
		"remark": "",
		"status": 0,
		"updateTime": ""
	},
	"insertTime": "",
	"remark": "",
	"status": 0,
	"studentCount": 0,
	"teacherId": "",
	"teacherName": "",
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classInfo| classInfo  | body | true |ClassInfo  | ClassInfo   |

**schema属性说明**



**ClassInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classType|   | body | false |string  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeInfo|   | body | false |GradeInfo  | GradeInfo   |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentCount|   | body | false |int32  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |

**GradeInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/classInfo/{classId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId| classId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# conf-student-parent-controller

## list


**接口说明**:



**接口地址**:`/score/confStudentParent`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | query | false |string  |    |
|connectionType|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentIds|   | query | false |array  | string   |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/confStudentParent/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/confStudentParent/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"confId": "",
	"connectionType": "",
	"parentId": "",
	"studentId": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confStudentParent| confStudentParent  | body | true |ConfStudentParent  | ConfStudentParent   |

**schema属性说明**



**ConfStudentParent**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | body | false |string  |    |
|connectionType|   | body | false |string  |    |
|parentId|   | body | false |string  |    |
|studentId|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/confStudentParent/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | query | false |string  |    |
|connectionType|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentIds|   | query | false |array  | string   |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# conf-teacher-class-controller

## list


**接口说明**:



**接口地址**:`/score/confTeacherClass`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classType|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].confId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectName|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].insertTime|   | query | false |string  |    |
|teacherInfos[0].phoneUrl|   | query | false |string  |    |
|teacherInfos[0].remark|   | query | false |string  |    |
|teacherInfos[0].sex|   | query | false |string  |    |
|teacherInfos[0].status|   | query | false |integer  |    |
|teacherInfos[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].subjectType|   | query | false |integer  |    |
|teacherInfos[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].teacherIds|   | query | false |array  | string   |
|teacherInfos[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].teacherNum|   | query | false |string  |    |
|teacherInfos[0].telPhone|   | query | false |string  |    |
|teacherInfos[0].txQq|   | query | false |string  |    |
|teacherInfos[0].txWx|   | query | false |string  |    |
|teacherInfos[0].updateTime|   | query | false |string  |    |
|teacherInfos[0].xlWb|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## confTeacherSubject


**接口说明**:



**接口地址**:`/score/confTeacherClass/confTeacherSubject`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classType|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].confId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectName|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].insertTime|   | query | false |string  |    |
|teacherInfos[0].phoneUrl|   | query | false |string  |    |
|teacherInfos[0].remark|   | query | false |string  |    |
|teacherInfos[0].sex|   | query | false |string  |    |
|teacherInfos[0].status|   | query | false |integer  |    |
|teacherInfos[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].subjectType|   | query | false |integer  |    |
|teacherInfos[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].teacherIds|   | query | false |array  | string   |
|teacherInfos[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].teacherNum|   | query | false |string  |    |
|teacherInfos[0].telPhone|   | query | false |string  |    |
|teacherInfos[0].txQq|   | query | false |string  |    |
|teacherInfos[0].txWx|   | query | false |string  |    |
|teacherInfos[0].updateTime|   | query | false |string  |    |
|teacherInfos[0].xlWb|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/confTeacherClass/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/confTeacherClass/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"classId": "",
	"classType": "",
	"confId": "",
	"subjectId": "",
	"subjectName": "",
	"teacherDuty": "",
	"teacherId": "",
	"teacherInfos": [
		{
			"confTeacherClasses": [
				{}
			],
			"insertTime": "",
			"phoneUrl": "",
			"remark": "",
			"sex": "",
			"status": 0,
			"subjectId": "",
			"subjectType": 0,
			"teacherDuty": "",
			"teacherId": "",
			"teacherName": "",
			"teacherNum": "",
			"telPhone": "",
			"txQq": "",
			"txWx": "",
			"updateTime": "",
			"xlWb": ""
		}
	],
	"teacherName": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClass| confTeacherClass  | body | true |ConfTeacherClass  | ConfTeacherClass   |

**schema属性说明**



**ConfTeacherClass**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|classType|   | body | false |string  |    |
|confId|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherInfos|   | body | false |array  | TeacherInfo   |
|teacherName|   | body | false |string  |    |

**TeacherInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses|   | body | false |array  | ConfTeacherClass   |
|insertTime|   | body | false |date-time  |    |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|subjectId|   | body | false |string  |    |
|subjectType|   | body | false |int32  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|teacherNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/confTeacherClass/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classType|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].confId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectName|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].insertTime|   | query | false |string  |    |
|teacherInfos[0].phoneUrl|   | query | false |string  |    |
|teacherInfos[0].remark|   | query | false |string  |    |
|teacherInfos[0].sex|   | query | false |string  |    |
|teacherInfos[0].status|   | query | false |integer  |    |
|teacherInfos[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].subjectType|   | query | false |integer  |    |
|teacherInfos[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].teacherIds|   | query | false |array  | string   |
|teacherInfos[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].teacherNum|   | query | false |string  |    |
|teacherInfos[0].telPhone|   | query | false |string  |    |
|teacherInfos[0].txQq|   | query | false |string  |    |
|teacherInfos[0].txWx|   | query | false |string  |    |
|teacherInfos[0].updateTime|   | query | false |string  |    |
|teacherInfos[0].xlWb|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# conf-teacher-subject-controller

## list


**接口说明**:



**接口地址**:`/score/confTeacherSubject`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/confTeacherSubject/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/confTeacherSubject/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"confId": "",
	"subjectId": "",
	"teacherId": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherSubject| confTeacherSubject  | body | true |ConfTeacherSubject  | ConfTeacherSubject   |

**schema属性说明**



**ConfTeacherSubject**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/confTeacherSubject/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# grade-info-controller

## list


**接口说明**:



**接口地址**:`/score/gradeInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/gradeInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/gradeInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"gradeId": "",
	"gradeName": "",
	"insertTime": "",
	"remark": "",
	"status": 0,
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeInfo| gradeInfo  | body | true |GradeInfo  | GradeInfo   |

**schema属性说明**



**GradeInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/gradeInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/gradeInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/gradeInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"gradeId": "",
	"gradeName": "",
	"insertTime": "",
	"remark": "",
	"status": 0,
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeInfo| gradeInfo  | body | true |GradeInfo  | GradeInfo   |

**schema属性说明**



**GradeInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/gradeInfo/{gradeId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|gradeId| gradeId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# index-controller

## index


**接口说明**:



**接口地址**:`/score/indexInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无


**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# parent-info-controller

## list


**接口说明**:



**接口地址**:`/score/parentInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList[0].classId|   | query | false |string  |    |
|childList[0].classIds|   | query | false |array  | string   |
|childList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].connectionType|   | query | false |string  |    |
|childList[0].parentInfoList[0].familyAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].officeAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentId|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentIds|   | query | false |array  | string   |
|childList[0].parentInfoList[0].parentName|   | query | false |string  |    |
|childList[0].parentInfoList[0].remark|   | query | false |string  |    |
|childList[0].parentInfoList[0].status|   | query | false |integer  |    |
|childList[0].parentInfoList[0].telPhone|   | query | false |string  |    |
|childList[0].parentInfoList[0].txQq|   | query | false |string  |    |
|childList[0].parentInfoList[0].txWx|   | query | false |string  |    |
|childList[0].parentInfoList[0].updateTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].xlWb|   | query | false |string  |    |
|childList[0].phoneUrl|   | query | false |string  |    |
|childList[0].remark|   | query | false |string  |    |
|childList[0].sex|   | query | false |string  |    |
|childList[0].status|   | query | false |integer  |    |
|childList[0].studentId|   | query | false |string  |    |
|childList[0].studentIds|   | query | false |array  | string   |
|childList[0].studentName|   | query | false |string  |    |
|childList[0].studentNum|   | query | false |string  |    |
|childList[0].telPhone|   | query | false |string  |    |
|childList[0].txQq|   | query | false |string  |    |
|childList[0].txWx|   | query | false |string  |    |
|childList[0].updateTime|   | query | false |string  |    |
|childList[0].xlWb|   | query | false |string  |    |
|connectionType|   | query | false |string  |    |
|familyAddress|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|officeAddress|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentIds|   | query | false |array  | string   |
|parentName|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/parentInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/parentInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"childList": [
		{
			"classId": "",
			"insertTime": "",
			"parentInfoList": [
				{}
			],
			"phoneUrl": "",
			"remark": "",
			"sex": "",
			"status": 0,
			"studentId": "",
			"studentName": "",
			"studentNum": "",
			"telPhone": "",
			"txQq": "",
			"txWx": "",
			"updateTime": "",
			"xlWb": ""
		}
	],
	"connectionType": "",
	"familyAddress": "",
	"insertTime": "",
	"officeAddress": "",
	"parentId": "",
	"parentName": "",
	"remark": "",
	"status": 0,
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|parentInfo| parentInfo  | body | true |ParentInfo  | ParentInfo   |

**schema属性说明**



**ParentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList|   | body | false |array  | StudentInfo   |
|connectionType|   | body | false |string  |    |
|familyAddress|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|officeAddress|   | body | false |string  |    |
|parentId|   | body | false |string  |    |
|parentName|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**StudentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|parentInfoList|   | body | false |array  | ParentInfo   |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/parentInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList[0].classId|   | query | false |string  |    |
|childList[0].classIds|   | query | false |array  | string   |
|childList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].connectionType|   | query | false |string  |    |
|childList[0].parentInfoList[0].familyAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].officeAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentId|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentIds|   | query | false |array  | string   |
|childList[0].parentInfoList[0].parentName|   | query | false |string  |    |
|childList[0].parentInfoList[0].remark|   | query | false |string  |    |
|childList[0].parentInfoList[0].status|   | query | false |integer  |    |
|childList[0].parentInfoList[0].telPhone|   | query | false |string  |    |
|childList[0].parentInfoList[0].txQq|   | query | false |string  |    |
|childList[0].parentInfoList[0].txWx|   | query | false |string  |    |
|childList[0].parentInfoList[0].updateTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].xlWb|   | query | false |string  |    |
|childList[0].phoneUrl|   | query | false |string  |    |
|childList[0].remark|   | query | false |string  |    |
|childList[0].sex|   | query | false |string  |    |
|childList[0].status|   | query | false |integer  |    |
|childList[0].studentId|   | query | false |string  |    |
|childList[0].studentIds|   | query | false |array  | string   |
|childList[0].studentName|   | query | false |string  |    |
|childList[0].studentNum|   | query | false |string  |    |
|childList[0].telPhone|   | query | false |string  |    |
|childList[0].txQq|   | query | false |string  |    |
|childList[0].txWx|   | query | false |string  |    |
|childList[0].updateTime|   | query | false |string  |    |
|childList[0].xlWb|   | query | false |string  |    |
|connectionType|   | query | false |string  |    |
|familyAddress|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|officeAddress|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentIds|   | query | false |array  | string   |
|parentName|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/parentInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList[0].classId|   | query | false |string  |    |
|childList[0].classIds|   | query | false |array  | string   |
|childList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].connectionType|   | query | false |string  |    |
|childList[0].parentInfoList[0].familyAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].insertTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].officeAddress|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentId|   | query | false |string  |    |
|childList[0].parentInfoList[0].parentIds|   | query | false |array  | string   |
|childList[0].parentInfoList[0].parentName|   | query | false |string  |    |
|childList[0].parentInfoList[0].remark|   | query | false |string  |    |
|childList[0].parentInfoList[0].status|   | query | false |integer  |    |
|childList[0].parentInfoList[0].telPhone|   | query | false |string  |    |
|childList[0].parentInfoList[0].txQq|   | query | false |string  |    |
|childList[0].parentInfoList[0].txWx|   | query | false |string  |    |
|childList[0].parentInfoList[0].updateTime|   | query | false |string  |    |
|childList[0].parentInfoList[0].xlWb|   | query | false |string  |    |
|childList[0].phoneUrl|   | query | false |string  |    |
|childList[0].remark|   | query | false |string  |    |
|childList[0].sex|   | query | false |string  |    |
|childList[0].status|   | query | false |integer  |    |
|childList[0].studentId|   | query | false |string  |    |
|childList[0].studentIds|   | query | false |array  | string   |
|childList[0].studentName|   | query | false |string  |    |
|childList[0].studentNum|   | query | false |string  |    |
|childList[0].telPhone|   | query | false |string  |    |
|childList[0].txQq|   | query | false |string  |    |
|childList[0].txWx|   | query | false |string  |    |
|childList[0].updateTime|   | query | false |string  |    |
|childList[0].xlWb|   | query | false |string  |    |
|connectionType|   | query | false |string  |    |
|familyAddress|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|officeAddress|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|parentId|   | query | false |string  |    |
|parentIds|   | query | false |array  | string   |
|parentName|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/parentInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"childList": [
		{
			"classId": "",
			"insertTime": "",
			"parentInfoList": [
				{}
			],
			"phoneUrl": "",
			"remark": "",
			"sex": "",
			"status": 0,
			"studentId": "",
			"studentName": "",
			"studentNum": "",
			"telPhone": "",
			"txQq": "",
			"txWx": "",
			"updateTime": "",
			"xlWb": ""
		}
	],
	"connectionType": "",
	"familyAddress": "",
	"insertTime": "",
	"officeAddress": "",
	"parentId": "",
	"parentName": "",
	"remark": "",
	"status": 0,
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|parentInfo| parentInfo  | body | true |ParentInfo  | ParentInfo   |

**schema属性说明**



**ParentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList|   | body | false |array  | StudentInfo   |
|connectionType|   | body | false |string  |    |
|familyAddress|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|officeAddress|   | body | false |string  |    |
|parentId|   | body | false |string  |    |
|parentName|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**StudentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|parentInfoList|   | body | false |array  | ParentInfo   |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/parentInfo/{parentId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|parentId| parentId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# school-info-controller

## list


**接口说明**:



**接口地址**:`/score/schoolInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|name|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scId|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/schoolInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/schoolInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"insertTime": "",
	"name": "",
	"remark": "",
	"scId": "",
	"status": 0,
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|schoolInfo| schoolInfo  | body | true |SchoolInfo  | SchoolInfo   |

**schema属性说明**



**SchoolInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|name|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|scId|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/schoolInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|name|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scId|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/schoolInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|name|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|scId|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/schoolInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"insertTime": "",
	"name": "",
	"remark": "",
	"scId": "",
	"status": 0,
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|schoolInfo| schoolInfo  | body | true |SchoolInfo  | SchoolInfo   |

**schema属性说明**



**SchoolInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|name|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|scId|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/schoolInfo/{scId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scId| scId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# score-info-controller

## list


**接口说明**:



**接口地址**:`/score/oldScoreInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreId|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|sumScore|   | query | false |number  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/oldScoreInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## exportScoreTemplate


**接口说明**:



**接口地址**:`/score/oldScoreInfo/exportScoreTemplate`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses[0].classId|   | query | false |string  |    |
|confTeacherClasses[0].classType|   | query | false |string  |    |
|confTeacherClasses[0].confId|   | query | false |string  |    |
|confTeacherClasses[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].subjectName|   | query | false |string  |    |
|confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].insertTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].phoneUrl|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].remark|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].sex|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].status|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].subjectType|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherIds|   | query | false |array  | string   |
|confTeacherClasses[0].teacherInfos[0].teacherName|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherNum|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].telPhone|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txQq|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txWx|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].updateTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].xlWb|   | query | false |string  |    |
|confTeacherClasses[0].teacherName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectType|   | query | false |integer  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherIds|   | query | false |array  | string   |
|teacherName|   | query | false |string  |    |
|teacherNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## importScoreByFile


**接口说明**:



**接口地址**:`/score/oldScoreInfo/importScore`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreFile| scoreFile  | formData | false |file  |    |
|scoreId|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|sumScore|   | query | false |number  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/oldScoreInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"avgScore": 0,
	"classId": "",
	"className": "",
	"classRanking": 0,
	"classType": "",
	"gradeAvgScore": 0,
	"gradeClassName": "",
	"gradeId": "",
	"gradeMaxScore": 0,
	"gradeMinScore": 0,
	"gradeName": "",
	"gradeRanking": 0,
	"insertTime": "",
	"maxScore": 0,
	"minScore": 0,
	"remark": "",
	"scoreId": "",
	"scoreNumber": 0,
	"semesterId": "",
	"semesterName": "",
	"status": 0,
	"studentId": "",
	"studentName": "",
	"subjectId": "",
	"subjectName": "",
	"sumScore": 0,
	"teacherId": "",
	"teacherName": "",
	"updateTime": "",
	"yearId": "",
	"yearName": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreInfo| scoreInfo  | body | true |ScoreInfo  | ScoreInfo   |

**schema属性说明**



**ScoreInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | body | false |double  |    |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classRanking|   | body | false |int32  |    |
|classType|   | body | false |string  |    |
|gradeAvgScore|   | body | false |double  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeMaxScore|   | body | false |double  |    |
|gradeMinScore|   | body | false |double  |    |
|gradeName|   | body | false |string  |    |
|gradeRanking|   | body | false |int32  |    |
|insertTime|   | body | false |date-time  |    |
|maxScore|   | body | false |double  |    |
|minScore|   | body | false |double  |    |
|remark|   | body | false |string  |    |
|scoreId|   | body | false |string  |    |
|scoreNumber|   | body | false |double  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|sumScore|   | body | false |double  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/oldScoreInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreId|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|sumScore|   | query | false |number  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## listByClass


**接口说明**:



**接口地址**:`/score/oldScoreInfo/listByClass`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreId|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|sumScore|   | query | false |number  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/oldScoreInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|scoreId|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|sumScore|   | query | false |number  |    |
|teacherId|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## listByParent


**接口说明**:



**接口地址**:`/score/oldScoreInfo/listByParent`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentName|   | query | false |string  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/oldScoreInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"avgScore": 0,
	"classId": "",
	"className": "",
	"classRanking": 0,
	"classType": "",
	"gradeAvgScore": 0,
	"gradeClassName": "",
	"gradeId": "",
	"gradeMaxScore": 0,
	"gradeMinScore": 0,
	"gradeName": "",
	"gradeRanking": 0,
	"insertTime": "",
	"maxScore": 0,
	"minScore": 0,
	"remark": "",
	"scoreId": "",
	"scoreNumber": 0,
	"semesterId": "",
	"semesterName": "",
	"status": 0,
	"studentId": "",
	"studentName": "",
	"subjectId": "",
	"subjectName": "",
	"sumScore": 0,
	"teacherId": "",
	"teacherName": "",
	"updateTime": "",
	"yearId": "",
	"yearName": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreInfo| scoreInfo  | body | true |ScoreInfo  | ScoreInfo   |

**schema属性说明**



**ScoreInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgScore|   | body | false |double  |    |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classRanking|   | body | false |int32  |    |
|classType|   | body | false |string  |    |
|gradeAvgScore|   | body | false |double  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeMaxScore|   | body | false |double  |    |
|gradeMinScore|   | body | false |double  |    |
|gradeName|   | body | false |string  |    |
|gradeRanking|   | body | false |int32  |    |
|insertTime|   | body | false |date-time  |    |
|maxScore|   | body | false |double  |    |
|minScore|   | body | false |double  |    |
|remark|   | body | false |string  |    |
|scoreId|   | body | false |string  |    |
|scoreNumber|   | body | false |double  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|sumScore|   | body | false |double  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/oldScoreInfo/{scoreId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreId| scoreId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# score-user-info-controller

## list


**接口说明**:



**接口地址**:`/score/user`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|ipAddress|   | query | false |string  |    |
|loginCount|   | query | false |integer  |    |
|loginName|   | query | false |string  |    |
|password|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |string  |    |
|userType|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/user/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/user/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
[
	{
		"firstLogin": "",
		"insertTime": "",
		"ipAddress": "",
		"loginCount": 0,
		"loginName": "",
		"password": "",
		"status": 0,
		"updateTime": "",
		"userId": "",
		"userType": ""
	}
]
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreUserInfos| scoreUserInfos  | body | true |array  | ScoreUserInfo   |

**schema属性说明**



**ScoreUserInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | body | false |date-time  |    |
|insertTime|   | body | false |date-time  |    |
|ipAddress|   | body | false |string  |    |
|loginCount|   | body | false |int32  |    |
|loginName|   | body | false |string  |    |
|password|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|userId|   | body | false |string  |    |
|userType|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/user/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|ipAddress|   | query | false |string  |    |
|loginCount|   | query | false |integer  |    |
|loginName|   | query | false |string  |    |
|password|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |string  |    |
|userType|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/user/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|ipAddress|   | query | false |string  |    |
|loginCount|   | query | false |integer  |    |
|loginName|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|password|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|userId|   | query | false |string  |    |
|userType|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## userLogin


**接口说明**:



**接口地址**:`/score/user/login`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"firstLogin": "",
	"insertTime": "",
	"ipAddress": "",
	"loginCount": 0,
	"loginName": "",
	"password": "",
	"status": 0,
	"updateTime": "",
	"userId": "",
	"userType": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|userInfo| userInfo  | body | true |ScoreUserInfo  | ScoreUserInfo   |

**schema属性说明**



**ScoreUserInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | body | false |date-time  |    |
|insertTime|   | body | false |date-time  |    |
|ipAddress|   | body | false |string  |    |
|loginCount|   | body | false |int32  |    |
|loginName|   | body | false |string  |    |
|password|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|userId|   | body | false |string  |    |
|userType|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## userLogout


**接口说明**:



**接口地址**:`/score/user/logout`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无


**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## resetPassword


**接口说明**:



**接口地址**:`/score/user/resetPassword`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"newPassword": "",
	"oldPassword": "",
	"userId": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreUser| scoreUser  | body | true |ScoreUser  | ScoreUser   |

**schema属性说明**



**ScoreUser**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|newPassword|   | body | false |string  |    |
|oldPassword|   | body | false |string  |    |
|userId|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/user/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
[
	{
		"firstLogin": "",
		"insertTime": "",
		"ipAddress": "",
		"loginCount": 0,
		"loginName": "",
		"password": "",
		"status": 0,
		"updateTime": "",
		"userId": "",
		"userType": ""
	}
]
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreUserInfos| scoreUserInfos  | body | true |array  | ScoreUserInfo   |

**schema属性说明**



**ScoreUserInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|firstLogin|   | body | false |date-time  |    |
|insertTime|   | body | false |date-time  |    |
|ipAddress|   | body | false |string  |    |
|loginCount|   | body | false |int32  |    |
|loginName|   | body | false |string  |    |
|password|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|userId|   | body | false |string  |    |
|userType|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/user/{subjectId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectId| subjectId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# semester-info-controller

## list


**接口说明**:



**接口地址**:`/score/semesterInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|examTime|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreBeginDeadline|   | query | false |string  |    |
|scoreEndDeadline|   | query | false |string  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearIds|   | query | false |array  | string   |
|yearInfo.insertTime|   | query | false |string  |    |
|yearInfo.remark|   | query | false |string  |    |
|yearInfo.status|   | query | false |integer  |    |
|yearInfo.updateTime|   | query | false |string  |    |
|yearInfo.yearId|   | query | false |string  |    |
|yearInfo.yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/semesterInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/semesterInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"examTime": "",
	"gradeId": "",
	"gradeName": "",
	"insertTime": "",
	"remark": "",
	"scoreBeginDeadline": "",
	"scoreEndDeadline": "",
	"semesterId": "",
	"semesterName": "",
	"status": 0,
	"updateTime": "",
	"yearId": "",
	"yearInfo": {
		"insertTime": "",
		"remark": "",
		"status": 0,
		"updateTime": "",
		"yearId": "",
		"yearName": ""
	}
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|semesterInfo| semesterInfo  | body | true |SemesterInfo  | SemesterInfo   |

**schema属性说明**



**SemesterInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|examTime|   | body | false |date-time  |    |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|scoreBeginDeadline|   | body | false |date-time  |    |
|scoreEndDeadline|   | body | false |date-time  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearInfo|   | body | false |YearInfo  | YearInfo   |

**YearInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/semesterInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|examTime|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|scoreBeginDeadline|   | query | false |string  |    |
|scoreEndDeadline|   | query | false |string  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearIds|   | query | false |array  | string   |
|yearInfo.insertTime|   | query | false |string  |    |
|yearInfo.remark|   | query | false |string  |    |
|yearInfo.status|   | query | false |integer  |    |
|yearInfo.updateTime|   | query | false |string  |    |
|yearInfo.yearId|   | query | false |string  |    |
|yearInfo.yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/semesterInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|examTime|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|scoreBeginDeadline|   | query | false |string  |    |
|scoreEndDeadline|   | query | false |string  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearIds|   | query | false |array  | string   |
|yearInfo.insertTime|   | query | false |string  |    |
|yearInfo.remark|   | query | false |string  |    |
|yearInfo.status|   | query | false |integer  |    |
|yearInfo.updateTime|   | query | false |string  |    |
|yearInfo.yearId|   | query | false |string  |    |
|yearInfo.yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/semesterInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"examTime": "",
	"gradeId": "",
	"gradeName": "",
	"insertTime": "",
	"remark": "",
	"scoreBeginDeadline": "",
	"scoreEndDeadline": "",
	"semesterId": "",
	"semesterName": "",
	"status": 0,
	"updateTime": "",
	"yearId": "",
	"yearInfo": {
		"insertTime": "",
		"remark": "",
		"status": 0,
		"updateTime": "",
		"yearId": "",
		"yearName": ""
	}
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|semesterInfo| semesterInfo  | body | true |SemesterInfo  | SemesterInfo   |

**schema属性说明**



**SemesterInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|examTime|   | body | false |date-time  |    |
|gradeId|   | body | false |string  |    |
|gradeName|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|scoreBeginDeadline|   | body | false |date-time  |    |
|scoreEndDeadline|   | body | false |date-time  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearInfo|   | body | false |YearInfo  | YearInfo   |

**YearInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/semesterInfo/{semesterId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|semesterId| semesterId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# student-info-controller

## list


**接口说明**:



**接口地址**:`/score/studentInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classIds|   | query | false |array  | string   |
|insertTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].classId|   | query | false |string  |    |
|parentInfoList[0].childList[0].classIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].phoneUrl|   | query | false |string  |    |
|parentInfoList[0].childList[0].remark|   | query | false |string  |    |
|parentInfoList[0].childList[0].sex|   | query | false |string  |    |
|parentInfoList[0].childList[0].status|   | query | false |integer  |    |
|parentInfoList[0].childList[0].studentId|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].studentName|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentNum|   | query | false |string  |    |
|parentInfoList[0].childList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].childList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].childList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].childList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].xlWb|   | query | false |string  |    |
|parentInfoList[0].connectionType|   | query | false |string  |    |
|parentInfoList[0].familyAddress|   | query | false |string  |    |
|parentInfoList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].officeAddress|   | query | false |string  |    |
|parentInfoList[0].parentId|   | query | false |string  |    |
|parentInfoList[0].parentIds|   | query | false |array  | string   |
|parentInfoList[0].parentName|   | query | false |string  |    |
|parentInfoList[0].remark|   | query | false |string  |    |
|parentInfoList[0].status|   | query | false |integer  |    |
|parentInfoList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].xlWb|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/studentInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/studentInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"classId": "",
	"insertTime": "",
	"parentInfoList": [
		{
			"childList": [
				{}
			],
			"connectionType": "",
			"familyAddress": "",
			"insertTime": "",
			"officeAddress": "",
			"parentId": "",
			"parentName": "",
			"remark": "",
			"status": 0,
			"telPhone": "",
			"txQq": "",
			"txWx": "",
			"updateTime": "",
			"xlWb": ""
		}
	],
	"phoneUrl": "",
	"remark": "",
	"sex": "",
	"status": 0,
	"studentId": "",
	"studentName": "",
	"studentNum": "",
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|studentInfo| studentInfo  | body | true |StudentInfo  | StudentInfo   |

**schema属性说明**



**StudentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|parentInfoList|   | body | false |array  | ParentInfo   |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**ParentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList|   | body | false |array  | StudentInfo   |
|connectionType|   | body | false |string  |    |
|familyAddress|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|officeAddress|   | body | false |string  |    |
|parentId|   | body | false |string  |    |
|parentName|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/studentInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classIds|   | query | false |array  | string   |
|insertTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].classId|   | query | false |string  |    |
|parentInfoList[0].childList[0].classIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].phoneUrl|   | query | false |string  |    |
|parentInfoList[0].childList[0].remark|   | query | false |string  |    |
|parentInfoList[0].childList[0].sex|   | query | false |string  |    |
|parentInfoList[0].childList[0].status|   | query | false |integer  |    |
|parentInfoList[0].childList[0].studentId|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].studentName|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentNum|   | query | false |string  |    |
|parentInfoList[0].childList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].childList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].childList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].childList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].xlWb|   | query | false |string  |    |
|parentInfoList[0].connectionType|   | query | false |string  |    |
|parentInfoList[0].familyAddress|   | query | false |string  |    |
|parentInfoList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].officeAddress|   | query | false |string  |    |
|parentInfoList[0].parentId|   | query | false |string  |    |
|parentInfoList[0].parentIds|   | query | false |array  | string   |
|parentInfoList[0].parentName|   | query | false |string  |    |
|parentInfoList[0].remark|   | query | false |string  |    |
|parentInfoList[0].status|   | query | false |integer  |    |
|parentInfoList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].xlWb|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/studentInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classIds|   | query | false |array  | string   |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|parentInfoList[0].childList[0].classId|   | query | false |string  |    |
|parentInfoList[0].childList[0].classIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].phoneUrl|   | query | false |string  |    |
|parentInfoList[0].childList[0].remark|   | query | false |string  |    |
|parentInfoList[0].childList[0].sex|   | query | false |string  |    |
|parentInfoList[0].childList[0].status|   | query | false |integer  |    |
|parentInfoList[0].childList[0].studentId|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentIds|   | query | false |array  | string   |
|parentInfoList[0].childList[0].studentName|   | query | false |string  |    |
|parentInfoList[0].childList[0].studentNum|   | query | false |string  |    |
|parentInfoList[0].childList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].childList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].childList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].childList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].childList[0].xlWb|   | query | false |string  |    |
|parentInfoList[0].connectionType|   | query | false |string  |    |
|parentInfoList[0].familyAddress|   | query | false |string  |    |
|parentInfoList[0].insertTime|   | query | false |string  |    |
|parentInfoList[0].officeAddress|   | query | false |string  |    |
|parentInfoList[0].parentId|   | query | false |string  |    |
|parentInfoList[0].parentIds|   | query | false |array  | string   |
|parentInfoList[0].parentName|   | query | false |string  |    |
|parentInfoList[0].remark|   | query | false |string  |    |
|parentInfoList[0].status|   | query | false |integer  |    |
|parentInfoList[0].telPhone|   | query | false |string  |    |
|parentInfoList[0].txQq|   | query | false |string  |    |
|parentInfoList[0].txWx|   | query | false |string  |    |
|parentInfoList[0].updateTime|   | query | false |string  |    |
|parentInfoList[0].xlWb|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/studentInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"classId": "",
	"insertTime": "",
	"parentInfoList": [
		{
			"childList": [
				{}
			],
			"connectionType": "",
			"familyAddress": "",
			"insertTime": "",
			"officeAddress": "",
			"parentId": "",
			"parentName": "",
			"remark": "",
			"status": 0,
			"telPhone": "",
			"txQq": "",
			"txWx": "",
			"updateTime": "",
			"xlWb": ""
		}
	],
	"phoneUrl": "",
	"remark": "",
	"sex": "",
	"status": 0,
	"studentId": "",
	"studentName": "",
	"studentNum": "",
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|studentInfo| studentInfo  | body | true |StudentInfo  | StudentInfo   |

**schema属性说明**



**StudentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|parentInfoList|   | body | false |array  | ParentInfo   |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**ParentInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|childList|   | body | false |array  | StudentInfo   |
|connectionType|   | body | false |string  |    |
|familyAddress|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|officeAddress|   | body | false |string  |    |
|parentId|   | body | false |string  |    |
|parentName|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/studentInfo/{studentId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|studentId| studentId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# subject-info-controller

## list


**接口说明**:



**接口地址**:`/score/subjectInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectIds|   | query | false |array  | string   |
|subjectName|   | query | false |string  |    |
|subjectOrd|   | query | false |integer  |    |
|subjectType|   | query | false |integer  |    |
|subjectTypes|   | query | false |array  | integer   |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/subjectInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/subjectInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"insertTime": "",
	"remark": "",
	"status": 0,
	"subjectId": "",
	"subjectName": "",
	"subjectOrd": 0,
	"subjectType": 0,
	"updateTime": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectInfo| subjectInfo  | body | true |SubjectInfo  | SubjectInfo   |

**schema属性说明**



**SubjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|subjectOrd|   | body | false |int32  |    |
|subjectType|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/subjectInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectIds|   | query | false |array  | string   |
|subjectName|   | query | false |string  |    |
|subjectOrd|   | query | false |integer  |    |
|subjectType|   | query | false |integer  |    |
|subjectTypes|   | query | false |array  | integer   |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/subjectInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectIds|   | query | false |array  | string   |
|subjectName|   | query | false |string  |    |
|subjectOrd|   | query | false |integer  |    |
|subjectType|   | query | false |integer  |    |
|subjectTypes|   | query | false |array  | integer   |
|updateTime|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/subjectInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
[
	{
		"insertTime": "",
		"remark": "",
		"status": 0,
		"subjectId": "",
		"subjectName": "",
		"subjectOrd": 0,
		"subjectType": 0,
		"updateTime": ""
	}
]
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectInfos| subjectInfos  | body | true |array  | SubjectInfo   |

**schema属性说明**



**SubjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|subjectOrd|   | body | false |int32  |    |
|subjectType|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/subjectInfo/{subjectId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectId| subjectId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# subject-score-info-controller

## list


**接口说明**:



**接口地址**:`/score/scoreInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | query | false |number  |    |
|avgBiological|   | query | false |number  |    |
|avgChemistry|   | query | false |number  |    |
|avgComputer|   | query | false |number  |    |
|avgEnglish|   | query | false |number  |    |
|avgGeography|   | query | false |number  |    |
|avgHistory|   | query | false |number  |    |
|avgLanguage|   | query | false |number  |    |
|avgMathematics|   | query | false |number  |    |
|avgMusic|   | query | false |number  |    |
|avgPhysical|   | query | false |number  |    |
|avgPolitical|   | query | false |number  |    |
|avgScore|   | query | false |number  |    |
|avgScoreSum|   | query | false |number  |    |
|avgSports|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|examTime|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|id|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|subArt|   | query | false |number  |    |
|subBiological|   | query | false |number  |    |
|subChemistry|   | query | false |number  |    |
|subComputer|   | query | false |number  |    |
|subEnglish|   | query | false |number  |    |
|subGeography|   | query | false |number  |    |
|subHistory|   | query | false |number  |    |
|subLanguage|   | query | false |number  |    |
|subMathematics|   | query | false |number  |    |
|subMusic|   | query | false |number  |    |
|subPhysical|   | query | false |number  |    |
|subPolitical|   | query | false |number  |    |
|subScoreSum|   | query | false |number  |    |
|subSports|   | query | false |number  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/scoreInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## exportScoreTemplate


**接口说明**:



**接口地址**:`/score/scoreInfo/exportScoreTemplate`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|classType|   | query | false |string  |    |
|confId|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].classType|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].confId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].subjectName|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].confTeacherClasses[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].insertTime|   | query | false |string  |    |
|teacherInfos[0].phoneUrl|   | query | false |string  |    |
|teacherInfos[0].remark|   | query | false |string  |    |
|teacherInfos[0].sex|   | query | false |string  |    |
|teacherInfos[0].status|   | query | false |integer  |    |
|teacherInfos[0].subjectId|   | query | false |string  |    |
|teacherInfos[0].subjectType|   | query | false |integer  |    |
|teacherInfos[0].teacherDuty|   | query | false |string  |    |
|teacherInfos[0].teacherId|   | query | false |string  |    |
|teacherInfos[0].teacherIds|   | query | false |array  | string   |
|teacherInfos[0].teacherName|   | query | false |string  |    |
|teacherInfos[0].teacherNum|   | query | false |string  |    |
|teacherInfos[0].telPhone|   | query | false |string  |    |
|teacherInfos[0].txQq|   | query | false |string  |    |
|teacherInfos[0].txWx|   | query | false |string  |    |
|teacherInfos[0].updateTime|   | query | false |string  |    |
|teacherInfos[0].xlWb|   | query | false |string  |    |
|teacherName|   | query | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## importScoreByFile


**接口说明**:



**接口地址**:`/score/scoreInfo/importScore`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无


**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/scoreInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"avgArt": 0,
	"avgBiological": 0,
	"avgChemistry": 0,
	"avgComputer": 0,
	"avgEnglish": 0,
	"avgGeography": 0,
	"avgHistory": 0,
	"avgLanguage": 0,
	"avgMathematics": 0,
	"avgMusic": 0,
	"avgPhysical": 0,
	"avgPolitical": 0,
	"avgScore": 0,
	"avgScoreSum": 0,
	"avgSports": 0,
	"classId": "",
	"className": "",
	"classRanking": 0,
	"classType": "",
	"examTime": "",
	"gradeAvgScore": 0,
	"gradeClassName": "",
	"gradeId": "",
	"gradeMaxScore": 0,
	"gradeMinScore": 0,
	"gradeName": "",
	"gradeRanking": 0,
	"id": "",
	"insertTime": "",
	"maxScore": 0,
	"minScore": 0,
	"semesterId": "",
	"semesterName": "",
	"studentId": "",
	"studentName": "",
	"studentNum": "",
	"subArt": 0,
	"subBiological": 0,
	"subChemistry": 0,
	"subComputer": 0,
	"subEnglish": 0,
	"subGeography": 0,
	"subHistory": 0,
	"subLanguage": 0,
	"subMathematics": 0,
	"subMusic": 0,
	"subPhysical": 0,
	"subPolitical": 0,
	"subScoreSum": 0,
	"subSports": 0,
	"teacherId": "",
	"updateTime": "",
	"yearId": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectScoreInfo| subjectScoreInfo  | body | true |SubjectScoreInfo  | SubjectScoreInfo   |

**schema属性说明**



**SubjectScoreInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | body | false |double  |    |
|avgBiological|   | body | false |double  |    |
|avgChemistry|   | body | false |double  |    |
|avgComputer|   | body | false |double  |    |
|avgEnglish|   | body | false |double  |    |
|avgGeography|   | body | false |double  |    |
|avgHistory|   | body | false |double  |    |
|avgLanguage|   | body | false |double  |    |
|avgMathematics|   | body | false |double  |    |
|avgMusic|   | body | false |double  |    |
|avgPhysical|   | body | false |double  |    |
|avgPolitical|   | body | false |double  |    |
|avgScore|   | body | false |double  |    |
|avgScoreSum|   | body | false |double  |    |
|avgSports|   | body | false |double  |    |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classRanking|   | body | false |int32  |    |
|classType|   | body | false |string  |    |
|examTime|   | body | false |date-time  |    |
|gradeAvgScore|   | body | false |double  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeMaxScore|   | body | false |double  |    |
|gradeMinScore|   | body | false |double  |    |
|gradeName|   | body | false |string  |    |
|gradeRanking|   | body | false |int32  |    |
|id|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|maxScore|   | body | false |double  |    |
|minScore|   | body | false |double  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|subArt|   | body | false |double  |    |
|subBiological|   | body | false |double  |    |
|subChemistry|   | body | false |double  |    |
|subComputer|   | body | false |double  |    |
|subEnglish|   | body | false |double  |    |
|subGeography|   | body | false |double  |    |
|subHistory|   | body | false |double  |    |
|subLanguage|   | body | false |double  |    |
|subMathematics|   | body | false |double  |    |
|subMusic|   | body | false |double  |    |
|subPhysical|   | body | false |double  |    |
|subPolitical|   | body | false |double  |    |
|subScoreSum|   | body | false |double  |    |
|subSports|   | body | false |double  |    |
|teacherId|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/scoreInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | query | false |number  |    |
|avgBiological|   | query | false |number  |    |
|avgChemistry|   | query | false |number  |    |
|avgComputer|   | query | false |number  |    |
|avgEnglish|   | query | false |number  |    |
|avgGeography|   | query | false |number  |    |
|avgHistory|   | query | false |number  |    |
|avgLanguage|   | query | false |number  |    |
|avgMathematics|   | query | false |number  |    |
|avgMusic|   | query | false |number  |    |
|avgPhysical|   | query | false |number  |    |
|avgPolitical|   | query | false |number  |    |
|avgScore|   | query | false |number  |    |
|avgScoreSum|   | query | false |number  |    |
|avgSports|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|examTime|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|id|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|subArt|   | query | false |number  |    |
|subBiological|   | query | false |number  |    |
|subChemistry|   | query | false |number  |    |
|subComputer|   | query | false |number  |    |
|subEnglish|   | query | false |number  |    |
|subGeography|   | query | false |number  |    |
|subHistory|   | query | false |number  |    |
|subLanguage|   | query | false |number  |    |
|subMathematics|   | query | false |number  |    |
|subMusic|   | query | false |number  |    |
|subPhysical|   | query | false |number  |    |
|subPolitical|   | query | false |number  |    |
|subScoreSum|   | query | false |number  |    |
|subSports|   | query | false |number  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## listByClass


**接口说明**:



**接口地址**:`/score/scoreInfo/listByClass`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | query | false |number  |    |
|avgBiological|   | query | false |number  |    |
|avgChemistry|   | query | false |number  |    |
|avgComputer|   | query | false |number  |    |
|avgEnglish|   | query | false |number  |    |
|avgGeography|   | query | false |number  |    |
|avgHistory|   | query | false |number  |    |
|avgLanguage|   | query | false |number  |    |
|avgMathematics|   | query | false |number  |    |
|avgMusic|   | query | false |number  |    |
|avgPhysical|   | query | false |number  |    |
|avgPolitical|   | query | false |number  |    |
|avgScore|   | query | false |number  |    |
|avgScoreSum|   | query | false |number  |    |
|avgSports|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|examTime|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|id|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|subArt|   | query | false |number  |    |
|subBiological|   | query | false |number  |    |
|subChemistry|   | query | false |number  |    |
|subComputer|   | query | false |number  |    |
|subEnglish|   | query | false |number  |    |
|subGeography|   | query | false |number  |    |
|subHistory|   | query | false |number  |    |
|subLanguage|   | query | false |number  |    |
|subMathematics|   | query | false |number  |    |
|subMusic|   | query | false |number  |    |
|subPhysical|   | query | false |number  |    |
|subPolitical|   | query | false |number  |    |
|subScoreSum|   | query | false |number  |    |
|subSports|   | query | false |number  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/scoreInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | query | false |number  |    |
|avgBiological|   | query | false |number  |    |
|avgChemistry|   | query | false |number  |    |
|avgComputer|   | query | false |number  |    |
|avgEnglish|   | query | false |number  |    |
|avgGeography|   | query | false |number  |    |
|avgHistory|   | query | false |number  |    |
|avgLanguage|   | query | false |number  |    |
|avgMathematics|   | query | false |number  |    |
|avgMusic|   | query | false |number  |    |
|avgPhysical|   | query | false |number  |    |
|avgPolitical|   | query | false |number  |    |
|avgScore|   | query | false |number  |    |
|avgScoreSum|   | query | false |number  |    |
|avgSports|   | query | false |number  |    |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|classRanking|   | query | false |integer  |    |
|classType|   | query | false |string  |    |
|examTime|   | query | false |string  |    |
|gradeAvgScore|   | query | false |number  |    |
|gradeClassName|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeMaxScore|   | query | false |number  |    |
|gradeMinScore|   | query | false |number  |    |
|gradeName|   | query | false |string  |    |
|gradeRanking|   | query | false |integer  |    |
|id|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|maxScore|   | query | false |number  |    |
|minScore|   | query | false |number  |    |
|orderColumn|   | query | false |string  |    |
|orderDirection|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|scoreNumber|   | query | false |number  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentIds|   | query | false |array  | string   |
|studentName|   | query | false |string  |    |
|studentNum|   | query | false |string  |    |
|subArt|   | query | false |number  |    |
|subBiological|   | query | false |number  |    |
|subChemistry|   | query | false |number  |    |
|subComputer|   | query | false |number  |    |
|subEnglish|   | query | false |number  |    |
|subGeography|   | query | false |number  |    |
|subHistory|   | query | false |number  |    |
|subLanguage|   | query | false |number  |    |
|subMathematics|   | query | false |number  |    |
|subMusic|   | query | false |number  |    |
|subPhysical|   | query | false |number  |    |
|subPolitical|   | query | false |number  |    |
|subScoreSum|   | query | false |number  |    |
|subSports|   | query | false |number  |    |
|subjectId|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## listByParent


**接口说明**:



**接口地址**:`/score/scoreInfo/listByParent`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | query | false |string  |    |
|className|   | query | false |string  |    |
|gradeId|   | query | false |string  |    |
|gradeName|   | query | false |string  |    |
|parentId|   | query | false |string  |    |
|parentName|   | query | false |string  |    |
|semesterId|   | query | false |string  |    |
|semesterName|   | query | false |string  |    |
|studentId|   | query | false |string  |    |
|studentName|   | query | false |string  |    |
|subjectId|   | query | false |string  |    |
|subjectName|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/scoreInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"avgArt": 0,
	"avgBiological": 0,
	"avgChemistry": 0,
	"avgComputer": 0,
	"avgEnglish": 0,
	"avgGeography": 0,
	"avgHistory": 0,
	"avgLanguage": 0,
	"avgMathematics": 0,
	"avgMusic": 0,
	"avgPhysical": 0,
	"avgPolitical": 0,
	"avgScore": 0,
	"avgScoreSum": 0,
	"avgSports": 0,
	"classId": "",
	"className": "",
	"classRanking": 0,
	"classType": "",
	"examTime": "",
	"gradeAvgScore": 0,
	"gradeClassName": "",
	"gradeId": "",
	"gradeMaxScore": 0,
	"gradeMinScore": 0,
	"gradeName": "",
	"gradeRanking": 0,
	"id": "",
	"insertTime": "",
	"maxScore": 0,
	"minScore": 0,
	"semesterId": "",
	"semesterName": "",
	"studentId": "",
	"studentName": "",
	"studentNum": "",
	"subArt": 0,
	"subBiological": 0,
	"subChemistry": 0,
	"subComputer": 0,
	"subEnglish": 0,
	"subGeography": 0,
	"subHistory": 0,
	"subLanguage": 0,
	"subMathematics": 0,
	"subMusic": 0,
	"subPhysical": 0,
	"subPolitical": 0,
	"subScoreSum": 0,
	"subSports": 0,
	"teacherId": "",
	"updateTime": "",
	"yearId": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|subjectScoreInfo| subjectScoreInfo  | body | true |SubjectScoreInfo  | SubjectScoreInfo   |

**schema属性说明**



**SubjectScoreInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|avgArt|   | body | false |double  |    |
|avgBiological|   | body | false |double  |    |
|avgChemistry|   | body | false |double  |    |
|avgComputer|   | body | false |double  |    |
|avgEnglish|   | body | false |double  |    |
|avgGeography|   | body | false |double  |    |
|avgHistory|   | body | false |double  |    |
|avgLanguage|   | body | false |double  |    |
|avgMathematics|   | body | false |double  |    |
|avgMusic|   | body | false |double  |    |
|avgPhysical|   | body | false |double  |    |
|avgPolitical|   | body | false |double  |    |
|avgScore|   | body | false |double  |    |
|avgScoreSum|   | body | false |double  |    |
|avgSports|   | body | false |double  |    |
|classId|   | body | false |string  |    |
|className|   | body | false |string  |    |
|classRanking|   | body | false |int32  |    |
|classType|   | body | false |string  |    |
|examTime|   | body | false |date-time  |    |
|gradeAvgScore|   | body | false |double  |    |
|gradeClassName|   | body | false |string  |    |
|gradeId|   | body | false |string  |    |
|gradeMaxScore|   | body | false |double  |    |
|gradeMinScore|   | body | false |double  |    |
|gradeName|   | body | false |string  |    |
|gradeRanking|   | body | false |int32  |    |
|id|   | body | false |string  |    |
|insertTime|   | body | false |date-time  |    |
|maxScore|   | body | false |double  |    |
|minScore|   | body | false |double  |    |
|semesterId|   | body | false |string  |    |
|semesterName|   | body | false |string  |    |
|studentId|   | body | false |string  |    |
|studentName|   | body | false |string  |    |
|studentNum|   | body | false |string  |    |
|subArt|   | body | false |double  |    |
|subBiological|   | body | false |double  |    |
|subChemistry|   | body | false |double  |    |
|subComputer|   | body | false |double  |    |
|subEnglish|   | body | false |double  |    |
|subGeography|   | body | false |double  |    |
|subHistory|   | body | false |double  |    |
|subLanguage|   | body | false |double  |    |
|subMathematics|   | body | false |double  |    |
|subMusic|   | body | false |double  |    |
|subPhysical|   | body | false |double  |    |
|subPolitical|   | body | false |double  |    |
|subScoreSum|   | body | false |double  |    |
|subSports|   | body | false |double  |    |
|teacherId|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/scoreInfo/{scoreId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|scoreId| scoreId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# teacher-info-controller

## list


**接口说明**:



**接口地址**:`/score/teacherInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses[0].classId|   | query | false |string  |    |
|confTeacherClasses[0].classType|   | query | false |string  |    |
|confTeacherClasses[0].confId|   | query | false |string  |    |
|confTeacherClasses[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].subjectName|   | query | false |string  |    |
|confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].insertTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].phoneUrl|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].remark|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].sex|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].status|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].subjectType|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherIds|   | query | false |array  | string   |
|confTeacherClasses[0].teacherInfos[0].teacherName|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherNum|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].telPhone|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txQq|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txWx|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].updateTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].xlWb|   | query | false |string  |    |
|confTeacherClasses[0].teacherName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectType|   | query | false |integer  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherIds|   | query | false |array  | string   |
|teacherName|   | query | false |string  |    |
|teacherNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/teacherInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/teacherInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"confTeacherClasses": [
		{
			"classId": "",
			"classType": "",
			"confId": "",
			"subjectId": "",
			"subjectName": "",
			"teacherDuty": "",
			"teacherId": "",
			"teacherInfos": [
				{}
			],
			"teacherName": ""
		}
	],
	"insertTime": "",
	"phoneUrl": "",
	"remark": "",
	"sex": "",
	"status": 0,
	"subjectId": "",
	"subjectType": 0,
	"teacherDuty": "",
	"teacherId": "",
	"teacherName": "",
	"teacherNum": "",
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|teacherInfo| teacherInfo  | body | true |TeacherInfo  | TeacherInfo   |

**schema属性说明**



**TeacherInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses|   | body | false |array  | ConfTeacherClass   |
|insertTime|   | body | false |date-time  |    |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|subjectId|   | body | false |string  |    |
|subjectType|   | body | false |int32  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|teacherNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**ConfTeacherClass**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|classType|   | body | false |string  |    |
|confId|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherInfos|   | body | false |array  | TeacherInfo   |
|teacherName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/teacherInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses[0].classId|   | query | false |string  |    |
|confTeacherClasses[0].classType|   | query | false |string  |    |
|confTeacherClasses[0].confId|   | query | false |string  |    |
|confTeacherClasses[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].subjectName|   | query | false |string  |    |
|confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].insertTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].phoneUrl|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].remark|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].sex|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].status|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].subjectType|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherIds|   | query | false |array  | string   |
|confTeacherClasses[0].teacherInfos[0].teacherName|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherNum|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].telPhone|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txQq|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txWx|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].updateTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].xlWb|   | query | false |string  |    |
|confTeacherClasses[0].teacherName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectType|   | query | false |integer  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherIds|   | query | false |array  | string   |
|teacherName|   | query | false |string  |    |
|teacherNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/teacherInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses[0].classId|   | query | false |string  |    |
|confTeacherClasses[0].classType|   | query | false |string  |    |
|confTeacherClasses[0].confId|   | query | false |string  |    |
|confTeacherClasses[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].subjectName|   | query | false |string  |    |
|confTeacherClasses[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].insertTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].phoneUrl|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].remark|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].sex|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].status|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].subjectId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].subjectType|   | query | false |integer  |    |
|confTeacherClasses[0].teacherInfos[0].teacherDuty|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherId|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherIds|   | query | false |array  | string   |
|confTeacherClasses[0].teacherInfos[0].teacherName|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].teacherNum|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].telPhone|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txQq|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].txWx|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].updateTime|   | query | false |string  |    |
|confTeacherClasses[0].teacherInfos[0].xlWb|   | query | false |string  |    |
|confTeacherClasses[0].teacherName|   | query | false |string  |    |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|phoneUrl|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|sex|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|subjectId|   | query | false |string  |    |
|subjectType|   | query | false |integer  |    |
|teacherDuty|   | query | false |string  |    |
|teacherId|   | query | false |string  |    |
|teacherIds|   | query | false |array  | string   |
|teacherName|   | query | false |string  |    |
|teacherNum|   | query | false |string  |    |
|telPhone|   | query | false |string  |    |
|txQq|   | query | false |string  |    |
|txWx|   | query | false |string  |    |
|updateTime|   | query | false |string  |    |
|xlWb|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/teacherInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"confTeacherClasses": [
		{
			"classId": "",
			"classType": "",
			"confId": "",
			"subjectId": "",
			"subjectName": "",
			"teacherDuty": "",
			"teacherId": "",
			"teacherInfos": [
				{}
			],
			"teacherName": ""
		}
	],
	"insertTime": "",
	"phoneUrl": "",
	"remark": "",
	"sex": "",
	"status": 0,
	"subjectId": "",
	"subjectType": 0,
	"teacherDuty": "",
	"teacherId": "",
	"teacherName": "",
	"teacherNum": "",
	"telPhone": "",
	"txQq": "",
	"txWx": "",
	"updateTime": "",
	"xlWb": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|teacherInfo| teacherInfo  | body | true |TeacherInfo  | TeacherInfo   |

**schema属性说明**



**TeacherInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|confTeacherClasses|   | body | false |array  | ConfTeacherClass   |
|insertTime|   | body | false |date-time  |    |
|phoneUrl|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|sex|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|subjectId|   | body | false |string  |    |
|subjectType|   | body | false |int32  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherName|   | body | false |string  |    |
|teacherNum|   | body | false |string  |    |
|telPhone|   | body | false |string  |    |
|txQq|   | body | false |string  |    |
|txWx|   | body | false |string  |    |
|updateTime|   | body | false |date-time  |    |
|xlWb|   | body | false |string  |    |

**ConfTeacherClass**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|classId|   | body | false |string  |    |
|classType|   | body | false |string  |    |
|confId|   | body | false |string  |    |
|subjectId|   | body | false |string  |    |
|subjectName|   | body | false |string  |    |
|teacherDuty|   | body | false |string  |    |
|teacherId|   | body | false |string  |    |
|teacherInfos|   | body | false |array  | TeacherInfo   |
|teacherName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/teacherInfo/{teacherId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|teacherId| teacherId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# year-info-controller

## list


**接口说明**:



**接口地址**:`/score/yearInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/yearInfo/deleteByIds`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"ids": []
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|objectInfo| objectInfo  | body | true |ObjectInfo  | ObjectInfo   |

**schema属性说明**



**ObjectInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|ids|   | body | false |array  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## insert


**接口说明**:



**接口地址**:`/score/yearInfo/insert`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"insertTime": "",
	"remark": "",
	"status": 0,
	"updateTime": "",
	"yearId": "",
	"yearName": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|yearInfo| yearInfo  | body | true |YearInfo  | YearInfo   |

**schema属性说明**



**YearInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/yearInfo/list`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## list


**接口说明**:



**接口地址**:`/score/yearInfo/listByPage`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | query | false |string  |    |
|pageNum| pageNum  | query | false |integer  |    |
|pageSize| pageSize  | query | false |integer  |    |
|remark|   | query | false |string  |    |
|status|   | query | false |integer  |    |
|updateTime|   | query | false |string  |    |
|yearId|   | query | false |string  |    |
|yearName|   | query | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## update


**接口说明**:



**接口地址**:`/score/yearInfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"insertTime": "",
	"remark": "",
	"status": 0,
	"updateTime": "",
	"yearId": "",
	"yearName": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|yearInfo| yearInfo  | body | true |YearInfo  | YearInfo   |

**schema属性说明**



**YearInfo**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|insertTime|   | body | false |date-time  |    |
|remark|   | body | false |string  |    |
|status|   | body | false |int32  |    |
|updateTime|   | body | false |date-time  |    |
|yearId|   | body | false |string  |    |
|yearName|   | body | false |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/score/yearInfo/{yearId}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|yearId| yearId  | path | true |string  |    |

**响应数据**:

```json
{
	"data": [],
	"message": "",
	"pageable": true,
	"success": true,
	"total": 0,
	"totalTime": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |array  | array   |
|message|   |string  |    |
|pageable|   |boolean  |    |
|success|   |boolean  |    |
|total|   |int64  |    |
|totalTime|   |int64  |    |




**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResultEntity|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
