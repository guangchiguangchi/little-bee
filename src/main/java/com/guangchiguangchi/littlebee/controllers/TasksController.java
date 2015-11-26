package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.*;
import com.jfinal.core.Controller;
import com.sun.deploy.uitoolkit.impl.fx.Utils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by zhangchen on 15-11-2.
 */
public class TasksController extends Controller {

    public void projectAndUser() {
        String taskid = getPara("taskid");
        JSONObject objJson = new JSONObject();
        if (StringUtils.isNotBlank(taskid) && !("null".equals(taskid))) {
            TasksModel task = TasksModel.me.findById(taskid);
            Integer taskId = task.get("id");
            objJson.put("taskId", taskId);
            String taskTitle = task.get("title");
            objJson.put("taskTitle", taskTitle);
            setAttr("taskTitle", taskTitle);
            String taskContent = task.get("content");
            objJson.put("taskContent", taskContent);
            float taskSpendTime = task.get("spendtime");
            objJson.put("taskSpendTime", taskSpendTime);
            objJson.put("project_id", task.get("project_id"));
            objJson.put("assignee_id", task.get("assignee_id"));
        }
        List<UserModel> userList = null;
        userList = UserModel.me.find("select id,username from bee_users");

        List<ProjectModel> projectList = null;
        projectList = ProjectModel.me.find("select * from bee_projects");

        int listSize;

        JSONArray userArr = new JSONArray();
        listSize = userList.size();
        for (int i = 0; i < listSize; i++) {
            userArr.add(userList.get(i));
        }
        JSONArray projectArr = new JSONArray();
        listSize = projectList.size();
        for (int i = 0; i < listSize; i++) {
            projectArr.add(projectList.get(i));
        }
        objJson.put("users", userArr);
        objJson.put("projects", projectArr);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 任务添加
     * 接口：/tasks/editTask
     * 参数：      uid
     * project  项目ID
     * userid   用户ID
     * assignee 指派人ID
     * title    标题
     * content  内容
     * spendtime    花费时间
     * 返回值：json
     */
    public void editTask() {
        /*String uid = getPara("uid");*/
        Integer cid = getAttr("userid");
        /*if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
        TasksModel task = null;
        LogsModel log = new LogsModel();
        String taskid = getPara("taskid");
        if (StringUtils.isNoneBlank(taskid)) {
            task = TasksModel.me.findById(taskid);
        } else {
            task = new TasksModel();
        }

        String projectid = getPara("project");
        String assigneeid = getPara("assignee");
        String title = getPara("title");
        String content = getPara("content");
        String spendtimeStr = getPara("spendtime");

        if (StringUtils.isNoneBlank(title)) {
            if (title.trim().isEmpty()) {
                renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
                return;
            }
        } else {
            renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
            return;
        }
        if (StringUtils.isEmpty(spendtimeStr)) {
            renderJson(Uitls.Ajax.failure("项目花费的时间不能为空", ""));
            return;
        }

        float spendTime = 0f;
        try {
            spendTime = Float.valueOf(spendtimeStr);

        } catch (Exception ex) {
            renderJson(Uitls.Ajax.failure("系统异常，类型转换失败", ""));
            return;
        }
        if (spendTime < 0 || spendTime > 99) {
            renderJson(Uitls.Ajax.failure("用时不合理.", ""));
            return;
        }
        task.set("title", title);
        task.set("content", content);
        task.set("project_id", projectid);
        task.set("creator_id", cid);
        task.set("assignee_id", assigneeid);
        task.set("spendtime", spendTime);
        task.set("status", 0);
        task.set("create_time", Uitls.currentTime());

        boolean flag = false;
        if (StringUtils.isNoneBlank(taskid)) {
            log.set("log_time", Uitls.currentTime());
            log.set("log", "修改操作");
            log.set("taskid", taskid);
            log.save();
            flag = task.update();
        } else {
            flag = task.save();
        }
        if (flag) {
            renderJson(Uitls.Ajax.success("添加任务成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("添加任务失败", ""));
        }
        /*}*/
    }

    /**
     * 修改任务状态状态
     * 接口：/tasks/changeTaskStatus
     * 参数：      uid
     * taskid：  任务ID
     * status：  任务状态
     */
    public void changeTaskStatus() {
        String taskid = getPara("taskid");
        String statusStr = getPara("status");
        Integer status = Integer.parseInt(statusStr);
        TasksModel task = TasksModel.me.findById(taskid);
        LogsModel log = new LogsModel();
        switch (status) {
            case 0:
                status = 1;
                task.set("start_time", Uitls.currentTime());
                log.set("log", "开始任务");
                break;
            case 1:
                status = 2;
                task.set("stop_time", Uitls.currentTime());
                log.set("log", "完成任务");
                break;
            case 2:
                status = 3;
                task.set("stop_time", Uitls.currentTime());
                log.set("log", "撤销任务");
                break;
            case 3:
                status = 4;
                task.set("stop_time", Uitls.currentTime());
                log.set("log", "删除任务");
                break;
            default:
                renderJson(Uitls.Ajax.failure("状态不存在", ""));
                return;
        }
        log.set("taskid", taskid);
        log.set("log_time", Uitls.currentTime());
        log.save();
        task.set("status", status);
        boolean flag = task.update();

        if (flag) {
            renderJson(Uitls.Ajax.success("操作成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("操作失败", ""));
        }
        /*}*/
    }

    /**
     * 删除
     */
    public void delete() {

    }

    /**
     * 列表
     */
    /*public void list() {
        String uid = getPara("uid");
        String cid = getPara("userid");
        if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {
            List<UserModel> grouperList = null;
            //指派人列表
            if (Integer.parseInt(usermap.get("workgroup").toString()) < 10) {
                grouperList = UserModel.me.find("select * from bee_users");
            } else {
                grouperList = UserModel.me.find("select * from bee_users where bee_users.workgroup between ? and ?", usermap.get("workgroup"), Integer.parseInt(usermap.get("workgroup").toString()) + 9);
            }
            //产品列表
            List<ProjectModel> projectList = ProjectModel.me.find("select * from bee_projects");

            int listSize;

            List<TasksModel> creatorList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username from bee_tasks join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.assignee_id=bee_users.id where creator_id = ?", cid);
            List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username  from bee_tasks  join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.creator_id=bee_users.id where status < 2 and assignee_id = ?", cid);

            setAttr("groupusers", grouperList);
            setAttr("projectlist", projectList);
            setAttr("creatortasks", creatorList);
            setAttr("assigneetasks", assigneeList);

            setAttr("group", usermap.get("workgroup"));

            setAttr("islogin", 1);
            setAttr("rtnMsg", getAttr("rtnMsg"));
            render("task.html");
        }
    }*/

    /**
     * 获取用户所有的任务列表
     * 接口：/tasks/getTaskList
     * 参数：
     * userid 用户ID
     * 返回值：json
     */
    public void getAssigneeTaskList() {
        Integer cid = getAttr("userid");
        int listSize;

        List<TasksModel> creatorList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,users.username as creatorname, " +
                " user.username as assigneename " +
                " from bee_tasks" +
                " join bee_projects on bee_tasks.project_id = bee_projects.id " +
                " join bee_users as users on bee_tasks.creator_id = users.id " +
                " join bee_users as user on bee_tasks.assignee_id = user.id " +
                " where creator_id = ? and bee_tasks.status < 4 order by bee_tasks.id desc", cid);

        JSONObject objJson = new JSONObject();

        JSONArray creatorArrJson = new JSONArray();
        listSize = creatorList.size();
        for (int i = 0; i < listSize; i++) {
            creatorArrJson.add(creatorList.get(i));
        }
        objJson.put("data", creatorArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
    /**
     *对weekplan表进行增加操作
     * 接口：/models/WeekPlanModel
     */
    public void addWeekPlan() {
        String content = getPara("content");

        String workCompletedStr = getPara("workCompleted");
        Integer workCompleted = Integer.parseInt(workCompletedStr);

        String workUnfinishedStr = getPara("workUnfinished");
        Integer workUnfinished = Integer.parseInt(workUnfinishedStr);

        String workTimeStr = getPara("workTime");
        Float workTime = Float.parseFloat(workTimeStr);

        String workUndoStr = getPara("workUndo");
        Integer workUndo = Integer.parseInt(workUndoStr);

        String personName = getPara("personName");
        WeekPlanModel log = new WeekPlanModel();
        log.set("content",content);
        log.set("work_completed",workCompleted);
        log.set("work_unfinishedStr",workUnfinished);
        log.set("work_time",workTime);
        log.set("work_undo",workUndo);
        log.set("person_name",personName);
        log.save();

    }

    /**
     * 获取用户需要做的任务列表
     * 接口：/tasks/getAssigneeTaskList
     * 参数:
     * 用户ID  userid
     * 返回值：json
     */
    public void getTaskList() {
        Integer cid = getAttr("userid");
        int listSize;
        List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,users.username as creatorname, " +
                " user.username as assigneename" +
                " from bee_tasks  " +
                " join bee_projects on bee_tasks.project_id = bee_projects.id " +
                " join bee_users as users on bee_tasks.creator_id = users.id " +
                " join bee_users as user on bee_tasks.assignee_id = user.id " +
                " where bee_tasks.status < 2 and assignee_id = ? order by bee_tasks.id desc", cid);
        listSize = assigneeList.size();

        JSONObject objJson = new JSONObject();

        JSONArray assigneeArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("data", assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 获取个人任务完成状态
     * 接口：/tasks/analysisTask
     * 参数:
     * 用户ID  id
     * 开始时间 starttime
     * 结束时间 endtime
     * 返回值：json
     */

    public void analysisTask() {
        String id = getPara("id");
        String starttime = getPara("starttime");
        String endtime = getPara("endtime");
        int listSize;
        List<TasksModel> tasksList = TasksModel.me.find("select bee_tasks.*,bee_users.username as person_name from bee_tasks join bee_users on bee_tasks.assignee_id = bee_users.id where bee_tasks.start_time>=? and bee_tasks.start_time<? and bee_users.id=?", starttime, endtime, id);
        listSize = tasksList.size();
        JSONObject objJson = new JSONObject();
        JSONArray tasksArrJson = new JSONArray();

        int wwc = 0, wc = 0, cx = 0;
        float time = 0;
        for (int i = 0; i < listSize; i++) {

            int status = tasksList.get(i).getInt("status");
            switch (status) {
                case 0:
                    wwc++;
                    break;
                case 1:
                    wwc++;
                    break;
                case 2:
                    wc++;
                    time = time + tasksList.get(i).getFloat("spendtime");
                    ;
                    break;
                case 3:
                    cx++;
                    break;
                case 4:
                    break;
            }
        }
        WeekPlanModel wpm = new WeekPlanModel();
        wpm.set("content", "qusiba");
        wpm.set("work_completed", wc);
        wpm.set("work_unfinished", wwc);
        wpm.set("work_undo", cx);
        wpm.set("work_time", time);
        wpm.set("person_name", tasksList.get(0).getStr("person_name"));
        wpm.save();
        tasksArrJson.add("姓名:" + tasksList.get(0).getStr("person_name") + ",未完成任务：" + wwc + "个,已完成任务：" + wc + "个,共用时：" + time + "小时,撤销任务：" + cx + "个");
        objJson.put("weekplan", tasksArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
    /**
     * 查询周报
     * 接口：/tasks/queryWeekPlan
     * 用户ID  userid
     * 返回值：json
     */
    public void queryWeekPlan(){
        int listSize;
        List<WeekPlanModel> assigneeList = WeekPlanModel.me.find("select * from bee-weekplan");
        listSize = assigneeList.size();

        JSONObject objJson = new JSONObject();

        JSONArray assigneeArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("data", assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));

    }
}


