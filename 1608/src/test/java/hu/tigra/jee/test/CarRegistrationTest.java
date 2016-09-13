/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hu.tigra.jee.test;

import hu.tigra.jee.model.Car;
import hu.tigra.jee.model.Member;
import hu.tigra.jee.service.CarRegistration;
import hu.tigra.jee.service.MemberRegistration;
import hu.tigra.jee.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CarRegistrationTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Car.class, CarRegistration.class, Member.class, MemberRegistration.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private CarRegistration carRegistration;

    @Inject
    private MemberRegistration memberRegistration;

    @Inject
    private Logger log;

    private Member lester;

    @Before
    public void setup() throws Exception {
        lester = new Member();
        lester.setName("Lester Burnham");
        lester.setEmail("lester.burnham@deadman.com");
        lester.setPhoneNumber("5555551234");
        memberRegistration.register(lester);
        assertNotNull(lester.getId());
        log.info(lester.getName() + " was persisted with id " + lester.getId());
    }

    @Test
    public void testRegister() throws Exception {
        Car car = new Car();
        car.setOwner(lester);
        car.setFuel("gasoline");
        car.setType("Pontiac Firebird");
        car.setLicensePlateNumber("1GDG970");

        carRegistration.register(car);
    }
}
