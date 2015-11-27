/**
 *
 * Created by kingcc on 15-11-10.
 */
$f("df","tasks.weekplanedit.combine",function(_temp,_resp,_scope){
    var render = template.compile(_temp);
    var html = render(_resp);
    $("#app-dialog").html(html);
    $('#myModal').modal();
    return;
});
