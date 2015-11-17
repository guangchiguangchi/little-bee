/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.taskedit.param", function (_param) {
    switch (_param.action) {
        case "projectAndUser":
            return {url: "/tasks/projectAndUser?userid=" + _param.canshu, data:{}, target: _param.target};
            break;
        case "changeTaskStatus":
            return {url: "/tasks/changeTaskStatus", data:{taskid:_param.canshu,status:_param.status}, target: _param.target};
    }
});