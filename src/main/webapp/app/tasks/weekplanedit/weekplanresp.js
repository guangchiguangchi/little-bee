/**
 * Created by Administrator on 2015/11/27.
 */
$f('df','tasks.weekplanedit.weekplanresp',function(_data,_scope){
    if(_data.issuccess == true){
        $('#myModal').modal('hide');
        RenderPage('weekplanlist2','app-context','weekplanlist2');
    }else{
        return alert(_data.message);
    }
});