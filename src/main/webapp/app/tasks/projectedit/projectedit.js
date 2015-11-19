/**
 * Created by smp on 2015/11/17.
 */
$f('df','tasks.projectedit.projectedit',function(_obj){

    var hiddenid = $("#projecthiddenid").val();
    var projectname = $("#project_name").val();
    var content = $("#project_content").val();
    var _data = {id:hiddenid,projectname:projectname,content:content};
    $f('SendData',_data,'tasks.projectedit.addprojectreq','tasks.projectedit.addprojectresp');
});