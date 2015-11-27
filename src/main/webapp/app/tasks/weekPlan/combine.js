/**
 *
 * Created by huyi on 15-11-27.
 */
$f("df","tasks.weekPlan.combine",function(_temp,_resp,_scope){
    var render = template.compile(_temp);
    var html = render(_resp);

    document.getElementById(_scope.target).innerHTML = html;
    return;
});
