/**
 * Created by kingcc on 15-11-10.
 */
$f("df", "tasks.weekplanedit.param", function (_param) {
    switch (_param.action) {
        case "weekplananalysis":
            return {url: "/tasks/analysisTask", data:{uid:_param.uid}, target: _param.target};
            break;
    }
});