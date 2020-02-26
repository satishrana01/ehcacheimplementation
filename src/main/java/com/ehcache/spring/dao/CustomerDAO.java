package com.ehcache.spring.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import com.ehcache.spring.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

	Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	private final String CACHE1= "fraudCriteriaCache";
	private final String CACHE2= "fraudCriteriaCache12";

	// Dummy database. Initialize with some dummy values.
	private static List<Customer> customers;
	{
		customers = new ArrayList();
		customers.add(new Customer(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
		customers.add(new Customer(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		customers.add(new Customer(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
		customers.add(new Customer(System.currentTimeMillis(), "Viral", "Patel", "vpatel@gmail.com", "356-758-8736"));
	}

	/**
	 * Returns list of customers from dummy database.
	 * 
	 * @return list of customers
	 */
	public List list() {
		return customers;
	}

	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	@Cacheable(value=CACHE1, key="#id")
	public StringBuilder get(Long id) {

		System.out.println("cache 1  stored" + id);
		/*for (Customer c : customers) {
			if (c.getId().equals(id)) {
				return c;
			}
		}*/
		System.out.println("reading 10mb file");

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get("C:\\10mbfile\\10mb.txt"), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder;

	}

	@Cacheable(value=CACHE2, key="#id")
	public StringBuilder get2(Long id) {

		System.out.println("cache 2 stored" + id);
		/*for (Customer c : customers) {
			if (c.getId().equals(id)) {
				return c;
			}
		}*/
		System.out.println("reading 10mb file");

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get("C:\\10mbfile\\10mb.txt"), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder;

	}

	public String getFileName() {

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get("C:\\10mbfile\\10mb.txt"), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();


	}

	@CacheEvict( value = {CACHE1,CACHE2}, allEntries = true)
	public void clearCache() {

		System.out.println("All cache cleared !!!!!!!!!!!!!!");
	}

	/**
	 * Create new customer in dummy database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	public Customer create(Customer customer) {
		customer.setId(System.currentTimeMillis());
		customers.add(customer);
		return customer;
	}

	/**
	 * Delete the customer object from dummy database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	public Long delete(Long id) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customers.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the customer object for given id in dummy database. If customer
	 * not exists, returns null
	 * 
	 * @param id
	 * @param customer
	 * @return customer object with id
	 */
	public Customer update(Long id, Customer customer) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customer.setId(c.getId());
				customers.remove(c);
				customers.add(customer);
				return customer;
			}
		}

		return null;
	}

}