package com.journaldev.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.springhibernate.model.Person;

public class TestBean {
	
	public static void main(String[] args) {
		
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:WebContent/WEB-INF/applicationContext.xml");
		
		PersonService svc = (PersonService)ctx.getBean("personService");
		Person p = svc.getPerson(1);
		System.out.println(p.getName());
		
	}

}
