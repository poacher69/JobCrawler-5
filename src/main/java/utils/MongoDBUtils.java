package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtils {

	private static volatile MongoDBUtils dbu;
	private MongoClient mongoClient;
	private MongoDatabase database;

	private MongoDBUtils(String databaseName) {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase(databaseName);
	}

	public void saveResultItem(ResultItem item) {
		MongoCollection<Document> collection = database.getCollection(item.getSite());
		Document doc;
		try {
			doc = new Document(introspect(item));
			collection.insertOne(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, Object> introspect(Object obj) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BeanInfo info = Introspector.getBeanInfo(obj.getClass());
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			Method reader = pd.getReadMethod();
			if (reader != null)
				result.put(pd.getName(), reader.invoke(obj));
		}
		return result;
	}

	public static MongoDBUtils getInstace() {
		if (dbu == null) {
			synchronized (MongoDBUtils.class) {
				if (dbu == null) {
					dbu = new MongoDBUtils("jobCrawler");
				}

			}
		}
		return dbu;
	}

}
