/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.weekplanlist2.param", function (_param) {
    switch (_param.action) {
        case "weekplanlist2":
            return {url:"/tasks/queryWeekPlan",data:{uid:_param.uid}, target: _param.target};
            break;
    }
});
