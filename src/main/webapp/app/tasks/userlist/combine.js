/**
 *
 * Created by zhangchen on 15-11-25.
 */

$f("df","tasks.userlist.combine",function(_temp,_resp,_scope){
    var render = template.compile(_temp);
    var html = render(_resp);

    document.getElementById(_scope.target).innerHTML = html;
    return;
});
