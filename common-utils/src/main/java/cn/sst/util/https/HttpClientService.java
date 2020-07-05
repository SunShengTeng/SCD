package cn.sst.util.https;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengtengsun
 * @Description Http、Https
 * @Date 2020/7/3 8:13 下午
 * @Version 1.1.0
 **/
public class HttpClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    private static int SUCCESS_CODE = 200;

    /**
     * GET
     *
     * @return java.lang.Object
     * @author shengtengsun
     * @Date 2020/7/5 3:51 下午
     * @Param [url, params]
     **/
    public static Object sendGet(String url, List<NameValuePair> params) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(params);

            HttpGet get = new HttpGet(uriBuilder.build());
            get.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            get.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));

            response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == code) {
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity, "UTF-8");
                try {
                    jsonObject = JSONObject.parseObject(res);
                    return jsonObject;
                } catch (Exception e) {
                    return res;
                }
            }
            LOGGER.error("HttpClientService-line: {}, errorMsg{}", 97, "GET请求失败！");
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception: {}", 100, e);
        } finally {
            client.close();
            response.close();
        }
        return null;
    }

    /**
     * POST
     *
     * @return void
     * @author shengtengsun
     * @Date 2020/7/5 3:52 下午
     * @Param [url, params]
     **/
    public static Object sendPost(String url, List<NameValuePair> params) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();

            HttpPost post = new HttpPost(url);
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            post.setEntity(entity);

            response = client.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String res = EntityUtils.toString(response.getEntity(), "UTF-8");
                try {
                    jsonObject = JSONObject.parseObject(res);
                    return jsonObject;
                } catch (Exception e) {
                    return res;
                }
            }
            LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception: {}", 100, e);
        } finally {
            client.close();
            response.close();
        }
        return null;
    }

    /**
     * 组织请求参数{参数名和参数值下标保持一致}
     *
     * @param params 参数名数组
     * @param values 参数值数组
     * @return 参数对象
     */
    public static List<NameValuePair> getParams(Object[] params, Object[] values) {
        /**
         * 校验参数合法性
         */
        boolean flag = params.length > 0 && values.length > 0 && params.length == values.length;
        if (flag) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                nameValuePairList.add(new BasicNameValuePair(params[i].toString(), values[i].toString()));
            }
            return nameValuePairList;
        } else {
            LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 197, "请求参数为空且参数长度不一致");
        }
        return null;
    }

}
