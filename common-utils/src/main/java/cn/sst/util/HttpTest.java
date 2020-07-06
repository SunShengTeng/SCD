package cn.sst.util;


/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/5 4:01 下午
 * @Version 1.1.0
 **/
public class HttpTest {
    public static void main(String[] args) throws Exception {
        String lhURL = "http://localhost:8080";
        String baiduURL = "https://www.baidu.com";
        /*System.out.println(HttpUtils.doGet(lhURL));*/
        System.out.println(HttpUtils.doPost(lhURL + "/post"));
    }
}
