package com.prowings.second_level_cache_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {

	public static void main(String[] args) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	Student s1 = session.get(Student.class, 1L);
	System.out.println(s1);
	
	Student s2 = session.get(Student.class, 1L );
	System.out.println(s2);
	
	session.close();
	
	System.out.println("......Opening another session and getting same object....");
	Session session2 = HibernateUtil.getSessionFactory().openSession();
	
	Student s3 = session2.get(Student.class, 1L);
	s3.setFirstName("Raj");
	s3.setLastName("Patil");
	s3.setContactNo("9011948084");
	
	System.out.println(s3);
	
	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	System.out.println("...Removing student object from 1st and 2nd level cache...");
	session2.evict(s3);
	sf.getCache().evict(Student.class);
	
	Student s4 = session2.get(Student.class, 1L);
	System.out.println(s4);
	HibernateUtil.close();

	}

}
