/**
 * Created by byj on 2015/11/16.
 */
$f("df","tasks.taskedit.addtaskresp",function(_data,_scope) {
    if(_data.issuccess == true){
        $('#myModal').modal('hide');
        RenderPage('mytask','app-context','tasklist');
    }else{
        return _data.message;
    }
});