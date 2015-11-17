/**
 * Created by byj on 2015/11/16.
 */
$f("df","tasks.taskedit.addtaskresp",function(_data,_scope) {
    console.log(_data);
    if(_data.issuccess == true){
        $('#myModal').modal('hide');
        RenderPage('mytask','app-context','tasklist');
    }
});