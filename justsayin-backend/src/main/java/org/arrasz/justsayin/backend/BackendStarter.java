/*
 * Copyright (C) 2016 Pivotal Software, Inc..
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.arrasz.justsayin.backend;

import org.apache.log4j.Logger;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author Joachim Arrasz <arrasz@synyx.de>
 */

@ComponentScan("org.arrasz.justsayin")
@SpringBootApplication
public class BackendStarter {

    static Logger logger = Logger.getLogger(BackendStarter.class);

    /**
     *
     */
    public BackendStarter() {
    }

    public static void main(String[] args) {

        logger.info("SyTim starting");

        ApplicationContext ctx = SpringApplication.run(BackendStarter.class, args);
        
    }

    // separately, as plain text credentials
    //@Bean
    public Session getSessionByJavaConfiguration() {

        Configuration configuration = new Configuration();
        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI("bolt:///neo4j:neo4j@localhost")
                .setEncryptionLevel("NONE");
//                .setTrustStrategy("TRUST_ON_FIRST_USE")
//                .setTrustCertFile("/tmp/cert");

        return new SessionFactory(configuration, "org.arrasz.justsayin.backend", "BOOT-INF/classes/org.arrasz.justsayin.backend").openSession();
    }
    
    //@Bean
    public Session getSessionByDeclaration() {
        SessionFactory sessionFactory = new SessionFactory("org.arrasz.justsayin.backend");
        return sessionFactory.openSession();
    }

}
