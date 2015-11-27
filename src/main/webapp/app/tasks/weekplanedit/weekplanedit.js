/**
 * Created by byj on 2015/11/16.
 */
$f('df','tasks.weekplanedit.weekplanedit',function(_obj){

    var username = $("#weekplan_id").val();
    var starttime = $("#weekplan_starttime").val();
    var endtime = $("#weekplan_endtime").val();
    var _data = {
        uid:window.location.href.split('uid=')[1],
        'username':username,
        'starttime':starttime,
        'endtime':endtime
    };
    $f('SendData',_data,'tasks.weekplanedit.weekplanreq','tasks.weekplanedit.weekplanresp');
});