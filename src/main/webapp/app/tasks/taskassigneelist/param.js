/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.taskassigneelist.param", function (_param) {
            return {url: "/tasks/getAssigneeTaskList", data: {userid: 1}, target: _param.target};
});