/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.projectlist.param",function(_param){
    switch(_param.action){
        case "projectlist":
            return {url:"/projects/list",data:{uid:_param.uid},target:_param.target};
            break;
    }
});