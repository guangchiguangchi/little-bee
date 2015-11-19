/**
 * Created by Administrator on 2015/11/17.
 */
$f("df","tasks.projectedit.addprojectresp",function(_data,_scope) {
    if(_data.issuccess == true){
        $('#myModal').modal('hide');
        RenderPage('projectlist','app-context','projectlist');
    }else{
        return alert(_data.message);
    }
});