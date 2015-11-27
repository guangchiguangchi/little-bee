/**
 * Created by Administrator on 2015/11/27.
 */

$f('df','tasks.weekplanedit.weekplanreq',function(_param){
    return {url: "/tasks/analysisTask", data: _param, target: _param.target};
});