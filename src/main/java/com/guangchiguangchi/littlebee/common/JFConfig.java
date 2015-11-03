package com.guangchiguangchi.littlebee.common;

import com.guangchiguangchi.littlebee.controllers.*;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;
import org.beetl.ext.jfinal.BeetlRenderFactory;

/**
 * Created by jiweibo on 15/11/02.
 */
public class JFConfig extends JFinalConfig{
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setMainRenderFactory(new BeetlRenderFactory());


    }

    @Override
    public void configRoute(Routes me) {
        loadPropertyFile("a_little_config.txt");
        me.add("/", AdminController.class, "/index");
        me.add("/users", UserController.class);
        me.add("/projects", ProjectController.class);
        me.add("/tasks", TasksController.class);
        me.add("/logs", LogsController.class);
    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
