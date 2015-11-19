/**
 * Created by byj on 2015/11/17.
 */
$f("df","tasks.taskedit.changstatusresp",function(_data,_scope) {
    if(_data.issuccess == true){
        RenderPage('mytask','app-context','tasklist');
    }else{
        return _data.message;
    }
});