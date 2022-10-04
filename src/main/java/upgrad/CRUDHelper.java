package upgrad;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import java.util.Arrays;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;

public class CRUDHelper {

	/**
	 * Display ALl products
	 * 
	 * @param collection
	 */
	public static void displayAllProducts(MongoCollection<Document> collection) {
		System.out.println("------ Displaying All Products ------");
		// Call printSingleCommonAttributes to display the attributes on the Screen
		for (Document doc : collection.find())
			PrintHelper.printSingleCommonAttributes(doc);
	}

	/**
	 * Display top 5 Mobiles
	 * 
	 * @param collection
	 */
	public static void displayTop5Mobiles(MongoCollection<Document> collection) {
		System.out.println("------ Displaying Top 5 Mobiles ------");
		// Call printAllAttributes to display the attributes on the Screen
		for (Document doc : collection.find(eq("Category", "Mobiles")).limit(5))
			PrintHelper.printAllAttributes(doc);
	}

	/**
	 * Display products ordered by their categories in Descending order without auto
	 * generated Id
	 * 
	 * @param collection
	 */
	public static void displayCategoryOrderedProductsDescending(MongoCollection<Document> collection) {
		System.out.println("------ Displaying Products ordered by categories ------");
		// Call printAllAttributes to display the attributes on the Screen
		for (Document doc : collection.find().projection(Projections.fields(Projections.excludeId()))
				.sort(descending("Category")))
			PrintHelper.printAllAttributes(doc);
	}

	/**
	 * Display number of products in each group
	 * 
	 * @param collection
	 */
	public static void displayProductCountByCategory(MongoCollection<Document> collection) {
		System.out.println("------ Displaying Product Count by categories ------");
		// Call printProductCountInCategory to display the attributes on the Screen
		for (Document doc : collection
				.aggregate(Arrays.asList(Aggregates.group("$Category", Accumulators.sum("Count", 1)))))
			PrintHelper.printProductCountInCategory(doc);
	}

	/**
	 * Display Wired Headphones
	 * 
	 * @param collection
	 */
	public static void displayWiredHeadphones(MongoCollection<Document> collection) {
		System.out.println("------ Displaying Wired headphones ------");
		// Call printAllAttributes to display the attributes on the Screen
		for (Document doc : collection.find(and(eq("Category", "Headphones"), eq("ConnectorType", "Wired"))))
			PrintHelper.printAllAttributes(doc);
	}
}