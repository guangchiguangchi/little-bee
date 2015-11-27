/**
 * Created by huyi on 15-11-27.
 */
$f("df","tasks.weekPlan.response",function(_data,_scope){
    if(_data.issuccess){
        return _data.data;
    }else{
        return _data.message;
    }
});