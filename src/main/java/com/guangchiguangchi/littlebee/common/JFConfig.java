package com.guangchiguangchi.littlebee.common;

import com.guangchiguangchi.littlebee.controllers.*;
import com.guangchiguangchi.littlebee.models.*;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import org.beetl.ext.jfinal.BeetlRenderFactory;

/**
 * Created by jiweibo on 15/11/02.
 */
public class JFConfig extends JFinalConfig{
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setEncoding("UTF-8");
        me.setMainRenderFactory(new BeetlRenderFactory());


    }

    @Override
    public void configRoute(Routes me) {
        loadPropertyFile("a_little_config.txt");
        me.add("/", AdminController.class, "/login");
        me.add("/home", HomeController.class, "/");
        me.add("/users", UserController.class);
        me.add("/projects", ProjectController.class);
        me.add("/tasks", TasksController.class);
        me.add("/logs", LogsController.class);
    }

    @Override
    public void configPlugin(Plugins me) {

        C3p0Plugin cp = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        arp.addMapping("bee_users", UserModel.class);
        arp.addMapping("bee_tasks",TasksModel.class);
        arp.addMapping("bee_projects",ProjectModel.class);
        arp.addMapping("bee_logs",LogsModel.class);
        arp.addMapping("bee_weekplan", WeekPlanModel.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {

        me.add(new LoginInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }
}
