package com.zpf.service.httpService;

import java.io.InputStream;
import org.apache.http.HttpException;

/**
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2018-05-12 http相关请求
 */
public interface IHttpService {

    /**
     * 发起http请求获取返回结果
     *
     * @param req_url 请求地址
     */
    String httpRequest(String req_url);

    /**
     * 发送http请求取得返回的输入流
     *
     * @param requestUrl 请求地址
     * @return InputStream
     */
    public InputStream httpRequestIO(String requestUrl);

    String sendGet(String url, String param);

    String sendPost(String url, String param);
}
