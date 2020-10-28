import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClient {
    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void main(String[] args) {
        // 访问请求
        System.out.println(doGet("http://localhost:8801/", null));
    }

    public static String doGet(String url, Map<String, String> header) {
        if (StringUtils.isEmpty(url)) {
            return "url 不能为空!";
        }
        // 创建httpGet请求对象
        HttpGet httpGet = new HttpGet(url);
        if (header != null) {
            // 设置请求头
            header.forEach((key, value) -> {
                httpGet.setHeader(key, value);
            });
        }
        // 设置超市时间等参数
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000) // 链接超时时间
                .setConnectionRequestTimeout(1000) // 连接请求超时时间
                .setSocketTimeout(1000) // 套接字超时时间
                .build();
        httpGet.setConfig(config);
        // 执行请求
        String result = null;
        try {
            result = client.execute(httpGet, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    return EntityUtils.toString(httpResponse.getEntity());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
