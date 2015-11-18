/**
 * Created by byj on 2015/11/17.
 */
$f('df','tasks.taskassigneeedit.taskstatus',function(_obj){
    console.log(_obj.name  + ":" + _obj.value);
            var _data = {uid:window.location.href.split('uid=')[1],taskid:_obj.value,status:_obj.name}
    $f('SendData',_data,'tasks.taskassigneeedit.changstatusreq','tasks.taskassigneeedit.changstatusresp');
});