package com.jxl.jcrawler.model;

import com.jxl.jcrawler.util.common.JsonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class User {
    private String name;//姓名
    private String account;//账户
    private String pwd;//密码
    private String pwd2;//密码2 其他密码, 如查询密码
    private String email;//邮件
    private String phone;//手机
    private String idCardNum;//证件号
    private String idCardType;//证件类型
    private String token; //token
    private String website;//网站
    private String errMsg;//保留字段
    private Object remark;//扩展字段 除以上字段外,其他字段以kv形式存储到remark中
    private String reportTaskToken; // 任务唯一指令

    public static void main(String[] args) {
        User user = new User();
        user.setToken("amosli111");
        user.setWebsite("qoo10");
        Map<String, String> map = new HashMap<>();
        map.put("code", "1302");
        map.put("phoneCode", "86");
        user.setPhone("13167081006");
        user.setPwd("123456");
        user.setRemark(map);

        System.out.printf(JsonUtil.toString(user));
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", token='" + token + '\'' +
                ", website='" + website + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", remark=" + remark +
                '}';
    }
}