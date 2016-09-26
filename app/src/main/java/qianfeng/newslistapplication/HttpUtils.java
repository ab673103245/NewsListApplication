package qianfeng.newslistapplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class HttpUtils {

    public static byte[] getHttp(String httpUrl)
    {
        HttpURLConnection con = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 是因为这里没有初始化啊！！！！
        InputStream is = null;
        try {
            URL url = new URL(httpUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5 * 1000);
            con.connect();
            if (con.getResponseCode() == 200) {
                int len = 0;
                byte[] buf = new byte[1024];
                is = con.getInputStream();
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                    baos.flush();
                }
                return baos.toByteArray();
            }
            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}
