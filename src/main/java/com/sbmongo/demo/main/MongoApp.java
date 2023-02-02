package com.sbmongo.demo.main;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.MongoClients;
import com.sbmongo.demo.entity.Person;

public class MongoApp {
	
	private static final Log log = LogFactory.getLog(MongoApp.class);
	
	public static void main(String args[]) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoApp.class);
		
		//MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
		
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDbFactory(MongoClients.create(), "MYDB"));

		mongoOps.dropCollection(Person.class);
		Person p = new Person("Joe", 34);
		// Insert is used to initially store the object into the database.
	    mongoOps.insert(p);
	    log.info("Insert: " + p);
	    
	    //find
	    p =  mongoOps.findById(p.getId(), Person.class);
	    log.info("Found:"+p);

	    //update
	    Query query = new Query();
	    query.addCriteria(Criteria.where("name").is("Joe")); 
	    Update update = new Update();
		update.set("age", 100);
	    mongoOps.updateFirst(query, update, Person.class);
	    
	    log.info("Updated: " + p);

	    // Delete
	    mongoOps.remove(p);

	    // Check that deletion worked
	    List<Person> people =  mongoOps.findAll(Person.class);
	    log.info("Number of people = : " + people.size());

	    //mongoOperations.dropCollection(Person.class);
	}

}
