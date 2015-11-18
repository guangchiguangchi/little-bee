/**
 * Created by smp on 2015/11/17.
 */
$f("df","tasks.projectedit.changestatusreq",function(_param){
    return {url: "/projects/changeProjectStatus", data: _param, target: _param.target};
});