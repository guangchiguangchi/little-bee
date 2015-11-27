/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.weekplanedit.param", function (_param) {
    switch (_param.action) {
        case "username_list":
            return {url:"/users/list", data:{uid:_param.uid}, target: _param.target};
            break;
    }
});