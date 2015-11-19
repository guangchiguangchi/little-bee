/**
 * Created by smp on 2015/11/17.
 */
$f("df","tasks.projectedit.changestatusresp",function(_data,_scope){
    if(_data.issuccess == true){
        RenderPage('projectlist','app-context','projectlist');
    }
});
