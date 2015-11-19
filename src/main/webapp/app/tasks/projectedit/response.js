/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.projectedit.response",function(_data,_scope){
    if(_data.issuccess) {
        return _data.data;
    }else{
        return alert(_data.message);
    }
});