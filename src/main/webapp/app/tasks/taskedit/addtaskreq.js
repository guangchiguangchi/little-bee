/**
 * Created by byj on 2015/11/16.
 */
$f("df", "tasks.taskedit.addtaskreq", function (_param) {
    return {url: "/tasks/editTask", data: _param, target: _param.target};
});