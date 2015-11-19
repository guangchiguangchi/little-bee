/**
 *
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.projectedit.combine",function(_temp,_resp,_scope){
    var render = template.compile(_temp);
    var html = render(_resp);
    $("#" + _scope.target)[0].innerHTML=html;
    $('#myModal').modal();
    return;
});
