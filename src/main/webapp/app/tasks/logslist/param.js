/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.logslist.param", function (_param) {
    switch (_param.action) {
        case "logslist":
            return {url: "logs/list", data: {"uid":_param.uid}, target: _param.target};
            break;
    }
});