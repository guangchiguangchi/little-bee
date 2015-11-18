/**
 * Created by shaomingpeng on 2015/11/16.
 */
$f('df','tasks.project.project',function(_obj){
    console.log("aaa"+_obj);
    $f('SendData',{name:_obj.name},'tasks.project.addprojectreq','tasks.project.addprojectresp');
});