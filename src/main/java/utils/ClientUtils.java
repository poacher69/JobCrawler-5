package utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

public class ClientUtils {

	private static CloseableHttpClient client = null;

	public static CloseableHttpClient getHttpClient() {
		if (client == null) {
			init();
		}
		return client;
	}

	private static void init() {
		CookieStore cookieStore = new BasicCookieStore();
		// 创建HttpClient上下文
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);

		client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy())
				.setDefaultCookieStore(cookieStore).build();
	}

	public synchronized static String execute(HttpRequestBase request) throws ClientProtocolException, IOException {
		if (client == null) {
			init();
		}
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String htmlString = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		return htmlString;
	}

}
