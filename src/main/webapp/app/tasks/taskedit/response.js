/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.taskedit.response",function(_data,_scope){
    if(_data.issuccess){
        console.log(_data);
        return _data;
    }else{
        return _data.message;
    }
    /*return {
        data:{
            projects:[],
            users:[],
            taskId:1
        }
    };*/
    /*return {
        "issuccess": true,
        "data": {
        "assigee": [
            {
                "projectname": "工作日志管理",
                "stop_time": "",
                "create_time": "2015年11月03日11:03:26",
                "spendtime": 30,
                "title": "创建项目",
                "content": "创建项目，提交git，不要加一些奇奇怪怪的文件",
                "start_time": "",
                "project_id": 1,
                "creator_id": 1,
                "id": 1,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": "",
                "create_time": "2015年11月03日11:05:42",
                "spendtime": 30,
                "title": "搭建环境",
                "content": "搭建JFinal环境",
                "start_time": "",
                "project_id": 1,
                "creator_id": 1,
                "id": 2,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": null,
                "create_time": "16:13:17.919",
                "spendtime": 1,
                "title": "Qq",
                "content": "qqqq",
                "start_time": null,
                "project_id": 1,
                "creator_id": 1,
                "id": 3,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": null,
                "create_time": "16:23:28.691",
                "spendtime": 0,
                "title": "",
                "content": "",
                "start_time": null,
                "project_id": 1,
                "creator_id": 1,
                "id": 4,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            }
        ],
            "creator": [
            {
                "projectname": "工作日志管理",
                "stop_time": "",
                "create_time": "2015年11月03日11:03:26",
                "spendtime": 30,
                "title": "创建项目",
                "content": "创建项目，提交git，不要加一些奇奇怪怪的文件",
                "start_time": "",
                "project_id": 1,
                "creator_id": 1,
                "id": 1,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": "",
                "create_time": "2015年11月03日11:05:42",
                "spendtime": 30,
                "title": "搭建环境",
                "content": "搭建JFinal环境",
                "start_time": "",
                "project_id": 1,
                "creator_id": 1,
                "id": 2,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": null,
                "create_time": "16:13:17.919",
                "spendtime": 1,
                "title": "Qq",
                "content": "qqqq",
                "start_time": null,
                "project_id": 1,
                "creator_id": 1,
                "id": 3,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            },
            {
                "projectname": "工作日志管理",
                "stop_time": null,
                "create_time": "16:23:28.691",
                "spendtime": 0,
                "title": "",
                "content": "",
                "start_time": null,
                "project_id": 1,
                "creator_id": 1,
                "id": 4,
                "assignee_id": 1,
                "status": 0,
                "username": "tudou"
            }
        ]
    },
        "message": "成功"
    }*/

});