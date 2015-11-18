/**
 * Created by smp on 2015/11/17.
 */
$f("df", "tasks.projectedit.addprojectreq", function (_param) {
    return {url: "/projects/editProject", data: _param, target: _param.target};
});