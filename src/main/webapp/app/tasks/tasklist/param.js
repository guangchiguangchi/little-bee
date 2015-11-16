/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.tasklist.param", function (_param) {
    switch (_param.action) {
        case "mytask":
            return {url: "/tasks/getTaskList", data: {userid: 1}, target: _param.target};
            break;
        case "myassign":
            return {url: "/tasks/getAssigneeTaskList", data: {userid: 1}, target: _param.target};
            break;
        case "projectAndUser":
            return {url: "/tasks/projectAndUser", data: {}, target: _param.target};
            break;
        case  "add":
            return {url: "/tasks/add", data: {}, target: _param.target};
            break;
    }
});