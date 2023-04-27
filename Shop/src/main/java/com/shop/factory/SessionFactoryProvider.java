package com.shop.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryProvider {
	  public SessionFactory provideSessionFactory()
	    {
	        Configuration config=new Configuration();
	        config.configure("hibernate.cfg.xml");
	        return config.buildSessionFactory();
	    }
}
