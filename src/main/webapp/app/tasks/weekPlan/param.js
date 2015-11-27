/**
 * Created by huyi on 15-11-27.
 */
$f("df", "tasks.weekPlan.param", function (_param) {
    switch (_param.action) {
        case "weekPlan":
            return {url:"/tasks/queryWeekPlan",data:{uid:_param.uid}, target: _param.target};
            break;
    }
});
