package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.ProjectModel;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by zhangchen on 15-11-2.
 */
public class ProjectController  extends Controller {
    /**
     * 新增
     * 项目名称：projectname
     * 项目描述：content
     * 项目状态：status
     */
    public void add(){
            String projectname = getPara("projectname");
            String content = getPara("content");

            if (projectname==null||content==null||projectname.trim().isEmpty()||content.trim().isEmpty()) {
                renderJson(Uitls.Ajax.failure("项目名称和内容不能为空",""));
                return;
            }
            ProjectModel project = new ProjectModel();
            project.set("projectname",projectname);
            project.set("content",content);
            project.set("status",0);

            project.save();
            renderJson(Uitls.Ajax.success("添加成功",""));

    }

    /**
     * 修改
     *项目id： projectid
     *项目名称： projectname
     * 项目描述：content
     */
    public void update(){
            String projectid = getPara("projectid");
            String projectname = getPara("projectname");
            String content = getPara("content");

            if (projectname==null||content==null||projectname.trim().isEmpty()||content.trim().isEmpty()) {
                renderJson(Uitls.Ajax.failure("项目名称和内容不能为空", ""));
                return;
            }
            ProjectModel project = new ProjectModel();
            project.set("id",projectid);
            project.set("projectname",projectname);
            project.set("content",content);


            project.update();
            renderJson(Uitls.Ajax.success("修改成功",""));

    }
    /**
     * 修改 project 状态
     *
     * 项目id：projectid
     * 项目状态：status
     */
    public void changeProjectStatus(){
            String projectid = getPara("projectid");
            String statusStr = getPara("status");
            ProjectModel project =ProjectModel.me.findById(projectid);

            int status = 0;
            switch (statusStr){
                case "开始":
                    status=1;
                    break;
                case "完成":
                    status=2;
                    break;
                case "撤销":
                    status=3;
                    break;
            }
            project.set("status",status);
            project.update();
            renderJson(Uitls.Ajax.success("修改状态成功",""));

    }

    /**
     * 删除
     *
     *项目id： projectid
     */
    public void delete(){
            String projectid=getPara("projectid");
            ProjectModel project =ProjectModel.me.findById(projectid);


            project.delete();
            renderJson(Uitls.Ajax.success("删除成功",""));

    }

    /**
     * 查询得到所有project
     *
     * 列表
     */
    public void list(){
            List<ProjectModel> projectList =null;
            projectList = ProjectModel.me.find("select * from bee_projects");
            int listSize = 0;
            listSize = projectList.size();
            JSONObject objJson = new JSONObject();
            JSONArray projectArrJson = new JSONArray();
            for(int i = 0;i<listSize;i++){
                projectArrJson.add(projectList.get(i));
            }
            objJson.put("project",projectArrJson);
            renderJson(Uitls.Ajax.success("成功",objJson));

    }
}
