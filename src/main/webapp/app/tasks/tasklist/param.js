/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.tasklist.param",function(_param){
    switch(_param.action){
        case "mytask":
            return {url:"/tasklist",data:{},target:_param.target};
            break;
        case "myassign":
            return {url:"/tasklist",data:{},target:_param.target};
            break;
    }
});