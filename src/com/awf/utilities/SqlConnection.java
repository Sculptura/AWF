package com.awf.utilities;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class SqlConnection {

	private static SqlSessionFactory sessionFactory = null;

	private static SqlSessionFactory mailSessionFactory = null;

	private SqlConnection() {

	}

	public static SqlSessionFactory getInstance() throws IOException {

		Logger log = Logger.getLogger(SqlConnection.class);

		try {

			if (sessionFactory == null) {

				String resource = "mybatis.conf.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				log.info("Resource File, Sucessfully Loaded!  ");

				sessionFactory = new SqlSessionFactoryBuilder().build(reader);

				log.info("Resource File, Sucessfully Read! ");
			}
		} catch (NullPointerException e) {

			log.info(e.toString());
		}

		return sessionFactory;
	}

	
	public static SqlSessionFactory getIMSession() {

		String resource = null;
		Reader reader = null;
		Logger log = Logger.getLogger(SqlConnection.class);

		try {

			resource = "mybatis.conf.xml";
			reader = Resources.getResourceAsReader(resource);
			mailSessionFactory = new SqlSessionFactoryBuilder().build(reader, "IM");

		} catch (NullPointerException | IOException e) {

			log.info(e.toString());
		}
		return mailSessionFactory;
	}

}