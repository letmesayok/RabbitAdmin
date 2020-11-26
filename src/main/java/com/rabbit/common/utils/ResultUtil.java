package com.rabbit.common.utils;

import cn.hutool.json.JSONUtil;
import com.rabbit.common.domain.ApiResponse;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 返回结果工具类
 *
 * @author wangql
 */
public class ResultUtil {

    private ResultUtil() {
    }

    /**
     * 使用 response 输出 json
     * @param response servlet
     * @param apiResponse 通用返回
     */
    public static void responseJson(ServletResponse response, ApiResponse apiResponse) {
        PrintWriter writer = null;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            writer.println(JSONUtil.toJsonStr(apiResponse));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
