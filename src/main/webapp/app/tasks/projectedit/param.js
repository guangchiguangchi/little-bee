/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.projectedit.param",function(_param){
    switch(_param.action){
        case "projectlist":
            return {url:"/projects/list",data:{},target:_param.target};
            break;
        case "projectAddUpdate":
            return {url:"/projects/addAndUpdate",data:{id:_param.canshu},target:_param.target}
            break;
    }
});