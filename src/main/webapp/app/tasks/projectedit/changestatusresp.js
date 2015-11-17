/**
 * Created by smp on 2015/11/17.
 */
$f("df","tasks.projectedit.changestatusresp",function(_data,_scope){
    console.log(_data);
    if(_data.issuccess == true){
        RenderPage('projectlist','app-context','projectlist');
    }
});
