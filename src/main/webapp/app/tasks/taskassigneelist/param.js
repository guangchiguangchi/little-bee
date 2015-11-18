/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.taskassigneelist.param", function (_param) {
            return {url: "/tasks/getAssigneeTaskList", data: {uid:_param.uid}, target: _param.target};
});