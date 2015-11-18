/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.tasklist.param", function (_param) {
    switch (_param.action) {
        case "mytask":
            return {url:"/tasks/getTaskList",data:{uid:_param.uid}, target: _param.target};
            break;
    }
});
