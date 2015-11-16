/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.projectlist.param",function(_param){
    switch(_param.action){
        case "projectlist":
            return {url:"/projects/list",data:{},target:_param.target};
            break;
        case "myassign":
            return {url:"/tasks/getAssigneeTaskList",data:{userid:1},target:_param.target};
            break;
        case "projectchangeProjectStatus":
            return {url:"/projects/changeProjectStatus",data:_param.canshu,target:_param.target};
            break;
    }
});