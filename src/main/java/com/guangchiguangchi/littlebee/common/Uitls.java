package com.guangchiguangchi.littlebee.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类，用于存放常用的方法
 * Created by jiweibo on 15/11/3.
 */
public class Uitls {

    /**
     * 解析
     */
    public static class Ajax {

        /**
         * 返回请求成功的标准格式数据
         * @param message 要返回给前台的信息
         * @param data 要返回的数据
         * @return map key-value格式的数据
         */
        public static Map<String, Object> success(Object message, Object data) {
            Map<String, Object> map = new HashMap<>();
            map.put("issuccess", true);
            map.put("message", message);
            map.put("data", data);
            return map;
        }

        /**
         * 返回请求失败的标准格式数据
         * @param message 要返回给前台的信息
         * @param data 要返回的数据
         * @return map类型的key-value格式的数据
         */
        public static Map<String, Object> failure(Object message, Object data) {
            Map<String, Object> map = new HashMap<>();
            map.put("issuccess", false);
            map.put("message", message);
            map.put("data", data);
            return map;
        }
    }




}
