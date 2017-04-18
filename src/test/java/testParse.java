import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import utils.DriverFactory2;
import utils.MyWebDriver;

public class testParse {

	public testParse() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
        try {
        	List<NameValuePair> params = new ArrayList<NameValuePair>();  
            params.add(new BasicNameValuePair("pn", "2"));  
            params.add(new BasicNameValuePair("sortField", "0"));  
            params.add(new BasicNameValuePair("havemark", "0"));   
        	
        	
            //HttpGet httpGet = new HttpGet("http://test.laizi.cn:8080/114/bm/accountLogin");
            String url = "https://www.lagou.com/gongsi/2-0-0.json";
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            httpPost.setHeader("referer", "https://www.lagou.com/gongsi/");
            httpPost.setHeader("Origin", "https://www.lagou.com");
            httpPost.setHeader("Host","www.lagou.com");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            httpPost.setHeader("Accept","application/json");
            CloseableHttpResponse response = httpclient.execute(httpPost);
            
            if(response.getStatusLine().getStatusCode()==302){  
                Header[] hs = response.getHeaders("Location");    
                      if(hs.length>0){    
                    	  
//                       return sendPost(httpclient,  hs[0].toString(), headers, params, encoding, cookie);  
                      }    
           }  
//            httpclient.s
//            response.getStatusLine();
            try {
                HttpEntity entity = response.getEntity();
                		
                //打印目标网站输出内容
                System.out.println(EntityUtils.toString(entity,Charset.forName("utf-8")));
                EntityUtils.consume(entity);   
//                Header redirect = response.getResponseHeader("location");
//                String url = redirect.getValue();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
		
//		MyWebDriver driver=DriverFactory2.getDriverTextOnly(true);
//		driver.get("http://www.baidu.com");
//		driver.quit();
	}

}
