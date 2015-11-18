/**
 * Created by byj on 2015/11/16.
 */
$f('df','tasks.taskedit.task',function(_obj){
    console.log("aaa"+_obj);

    var project = $("#task_project  option:selected").val();
    var username = $("#task_assignee option:selected").val();
    var title = $("#task_title").val();
    var content = $("#task_content").val();
    var speedtime = $("#task_speedtime").val();
    var hiddenid = $("#taskhiddenid").val();
    var _data = {
        uid:window.location.href.split('uid=')[1],
        'project':project,
        'assignee':username,
        'title':title,
        'content':content,
        'spendtime':speedtime,
        'taskid':hiddenid
    };
    $f('SendData',_data,'tasks.taskedit.addtaskreq','tasks.taskedit.addtaskresp');
});