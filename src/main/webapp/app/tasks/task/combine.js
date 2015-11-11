/**
 *
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.task.combine",function(_temp,_resp,_scope){
    console.log(_temp);
    console.log(_resp);
    console.log(_scope);

    var render = template.compile(_temp);
    var html = render(_resp);

    document.getElementById(_scope.target).innerHTML = html;
    return;
});
