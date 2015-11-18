/**
 *
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.taskedit.combine",function(_temp,_resp,_scope){
    var render = template.compile(_temp);
    $("#myModal").remove();
    var html = render(_resp);
    $("#" + _scope.target).append(html);
    $('#myModal').modal();
    return;
});
