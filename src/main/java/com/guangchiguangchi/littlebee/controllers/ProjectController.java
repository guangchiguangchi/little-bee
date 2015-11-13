package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.ProjectModel;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * 项目控制类，用于控制项目的增删改查
 * Created by zhangchen on 15-11-2.
 *
 */
public class ProjectController extends Controller {
    /**
     * 新增项目
     *
     * 接口： /projects/add
     *
     * 参数：
     *       projectname   项目名称
     *       content       项目内容
     * 返回： json
     */
    public void add() {
        String projectname = getPara("projectname");
        String content = getPara("content");

        if (projectname == null || content == null || projectname.trim().isEmpty() || content.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("项目名称和内容不能为空", ""));
            return;
        }
        ProjectModel project = new ProjectModel();
        project.set("projectname", projectname);
        project.set("content", content);
        project.set("status", 0);

        boolean flag = project.save();
        if (flag) {
            renderJson(Uitls.Ajax.success("添加成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("添加失败", ""));
        }

    }

    /**
     * 修改项目
     *
     * 接口:  /projects/update
     *
     * 参数：
     *      projectid     项目id
     *      projectname   项目名称
     *      content       项目内容
     * 返回： json
     */
    public void update() {
        String projectid = getPara("projectid");
        String projectname = getPara("projectname");
        String content = getPara("content");

        if (projectname == null || content == null || projectname.trim().isEmpty() || content.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("项目名称和内容不能为空", ""));
            return;
        }
        ProjectModel project = new ProjectModel();
        project.set("id", projectid);
        project.set("projectname", projectname);
        project.set("content", content);


        boolean flag = project.update();
        if (flag) {
            renderJson(Uitls.Ajax.success("修改成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("修改失败", ""));
        }

    }

    /**
     * 修改项目的状态
     *
     * 接口:  /projects/changeProjectStatus
     *
     * 参数：
     *      projectid     项目id
     *      status        项目状态
     * 返回： json
     */
    public void changeProjectStatus() {
        String projectid = getPara("projectid");
        String statusStr = getPara("status");
        ProjectModel project = ProjectModel.me.findById(projectid);

        int status = 0;
        switch (statusStr) {
            case "开始":
                status = 1;
                break;
            case "完成":
                status = 2;
                break;
            case "撤销":
                status = 3;
                break;
        }
        project.set("status", status);
        boolean flag = project.update();
        if (flag) {
            renderJson(Uitls.Ajax.success("修改状态成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("修改状态失败", ""));
        }

    }

    /**
     * 删除项目
     *
     * 接口:  /projects/delete
     *
     * 参数：
     *        projectid    要删除的项目的id
     *
     * 返回： json
     */
    public void delete() {
        String projectid = getPara("projectid");
        ProjectModel project = ProjectModel.me.findById(projectid);


        boolean flag = project.delete();
        if (flag) {
            renderJson(Uitls.Ajax.success("删除成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("删除失败", ""));
        }

    }

    /**
     * 查询得到所有项目列表
     *
     * 接口:  /projects/list
     *
     * 返回： json
     */
    public void list() {
        List<ProjectModel> projectList = null;
        projectList = ProjectModel.me.find("select * from bee_projects");
        int listSize = 0;
        listSize = projectList.size();
        JSONObject objJson = new JSONObject();
        JSONArray projectArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            projectArrJson.add(projectList.get(i));
        }
        objJson.put("project", projectArrJson);
        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
