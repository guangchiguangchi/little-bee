/**
 *
 * Created by zhangchen on 15-11-16.
 */

$f('df','tasks.task.task',function(_obj){
    console.log("aaa"+_obj);
    $f('SendData',{name:_obj.name},'tasks.task.addtaskreq','tasks.task.addtaskresp');
});
