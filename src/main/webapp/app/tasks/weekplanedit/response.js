/**
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.weekplanedit.response",function(_data,_scope){
    if(_data.issuccess){
        return _data;
    }else{
        return _data.message;
    }

});