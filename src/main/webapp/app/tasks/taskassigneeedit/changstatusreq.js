/**
 * Created by byj on 2015/11/17.
 */
$f("df", "tasks.taskassigneeedit.changstatusreq", function (_param) {
    return {url: "/tasks/changeTaskStatus", data: _param, target: _param.target};
});