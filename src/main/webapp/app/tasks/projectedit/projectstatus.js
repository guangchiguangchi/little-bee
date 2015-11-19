/**
 * Created by smp on 2015/11/17.
 */
$f("df","tasks.projectedit.projectstatus",function(_obj){
    var _data = {id:_obj.value,status:_obj.name,uid:window.location.href.split('uid=')[1]}
    $f('SendData',_data,'tasks.projectedit.changestatusreq','tasks.projectedit.changestatusresp');
})
