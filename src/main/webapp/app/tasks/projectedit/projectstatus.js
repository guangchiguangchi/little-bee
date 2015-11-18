/**
 * Created by smp on 2015/11/17.
 */
$f("df","tasks.projectedit.projectstatus",function(_obj){
    console.log(_obj.name  + ":" + _obj.value);
    var _data = {id:_obj.value,status:_obj.name}
    $f('SendData',_data,'tasks.projectedit.changestatusreq','tasks.projectedit.changestatusresp');
})
