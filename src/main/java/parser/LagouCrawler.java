package parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;

import control.CrawlControl;
import utils.ClientUtils;
import utils.MongoDBUtils;
import utils.ResultItem;
import utils.TaskItem;
import utils.TimeUtils;

public class LagouCrawler implements ITaskCrawl {

	private static final String site = "Lagou";
	private static final String companyHomeRegex = "https://www.lagou.com/gongsi/";
	private static final Pattern companyJobListRegex = Pattern.compile("https://www.lagou.com/gongsi/j(\\d+).html");
	private static final String jobListPrefix="https://www.lagou.com/gongsi/j%s.html";
	private static final String jobUrlPrefix = "https://www.lagou.com/jobs/%s.html";
	// Pattern r = Pattern.compile(pattern)

	private static volatile LagouCrawler crawler;

	private LagouCrawler() {
	}

	public static ITaskCrawl getCrawlerInstance() {
		if (crawler == null) {
			synchronized (LagouCrawler.class) {
				if (crawler == null) {
					crawler = new LagouCrawler();
				}
			}
		}
		return crawler;
	}

	public boolean crawl(TaskItem task) {
		Matcher m;
		if (task.getUrl().equals(companyHomeRegex)) {
			try {
				crawlCompany(task);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		m = companyJobListRegex.matcher(task.getUrl());
		if (m.find()) {

		}

		return true;
	}

	private void crawlCompany(TaskItem task) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(task.getUrl());
		setRequestHeader(httpPost);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		task.getProperties().forEach((k, v) -> {
			params.add(new BasicNameValuePair(k, v));
		});
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		String resHtml = ClientUtils.execute(httpPost);
		Document doc = Jsoup.parse(resHtml);
		Elements eles = doc.getElementById("company_list").getElementsByTag("li");
		for (Element e : eles) {
			String href = e.getElementsByClass("item_title").first().attr("href");
			String cid = e.getElementsByClass("item_title").first().attr("data-lg-tj-cid");
			ResultItem ri = new ResultItem(String.format("lagou_company_%s", cid), href, site,
					TimeUtils.getStringTime(), "company");
			Map<String, String> contentMap = new HashMap<>();
			contentMap.put("name", e.getElementsByClass("item_title").first().attr("title"));
			contentMap.put("detail", e.getElementsByClass("details").first().text());
			contentMap.put("industry", e.getElementsByClass("company_state").first().child(0).attr("title"));
			contentMap.put("stage", e.getElementsByClass("company_state").first().child(2).attr("title"));
			contentMap.put("city", e.getElementsByClass("company_state").first().child(1).attr("title"));
			ri.setContent(JSON.toJSONString(contentMap));
			MongoDBUtils.getInstace().saveResultItem(ri);
			TaskItem ti=new TaskItem(String.format("lagou_jobList_%s", cid), String.format(jobListPrefix, cid), "LagouCrawler", new HashMap<String,String>(){{
				put("companyId", cid);
			}});
			CrawlControl.enqueueTask(ti);
		}
		
	}

	private void crawlCompanyJobList(TaskItem task) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(task.getUrl());
		setRequestHeader(httpPost);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		task.getProperties().forEach((k, v) -> {
			params.add(new BasicNameValuePair(k, v));
		});
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		String resHtml = ClientUtils.execute(httpPost);
		Document doc = Jsoup.parse(resHtml);
		Elements eles = doc.getElementsByClass("list-content").first().getElementsByClass("item_con_list").first()
				.getElementsByTag("li");
		for (Element e : eles) {
			TaskItem ti = new TaskItem(String.format("lagou_jobDetail_%s", e.attr("data-positionid")),
					String.format(jobUrlPrefix, e.attr("data-positionid")), "LagouCrawler",
					new HashMap<String, String>() {
						{
							put("companyId", task.getProperties().get("companyId"));
						}
					});
			CrawlControl.enqueueTask(ti);
		}
	}

	private void setRequestHeader(HttpRequest request) {
		request.setHeader("referer", "https://www.lagou.com/gongsi/");
		request.setHeader("Origin", "https://www.lagou.com");
		request.setHeader("Host", "www.lagou.com");
		request.setHeader("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
		request.setHeader("Accept", "application/json");
	}

	public static void main(String[] args) {
		String s = "https://www.lagou.com/gongsi/j147648.html";
		Matcher m = companyJobListRegex.matcher(s);
		if (m.find()) {
			System.out.println(m.group(0));
		}
		// LagouCrawler crawler = new LagouCrawler();
		// for (int i = 1; i <= 60; i++) {
		// TaskItem task = new TaskItem("https://www.lagou.com/gongsi/213-0-0",
		// "Lagou",
		// new HashMap<String, String>() {
		// {
		// put("sortField", "0");
		// put("havemark", "0");
		// }
		// });
		// task.getProperties().put("pn", String.valueOf(i));
		// try {
		// crawler.crawlerCompany(task);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

	}

}
