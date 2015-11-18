/**
 * Created by byj on 2015/11/17.
 */
$f("df","tasks.taskassigneeedit.changstatusresp",function(_data,_scope) {
    console.log(_data);
    if(_data.issuccess == true){
        RenderPage('myassign','app-context','taskassigneelist');
    }else{
        return _data.message;
    }
});