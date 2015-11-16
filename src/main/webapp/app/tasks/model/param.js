/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.model.param", function (_param) {
    switch (_param.action) {
        case "projectAndUser":
            return {url: "/tasks/projectAndUser", data: {}, target: _param.target};
            break;
    }
});