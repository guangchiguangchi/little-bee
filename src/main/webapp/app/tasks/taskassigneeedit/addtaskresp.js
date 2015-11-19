/**
 * Created by byj on 2015/11/16.
 */
$f("df","tasks.taskassigneeedit.addtaskresp",function(_data,_scope) {
    if(_data.issuccess == true){
        $('#myModal').modal('hide');
        RenderPage('myassign','app-context','taskassigneelist');
    }else{
        return _data.message;
    }
});