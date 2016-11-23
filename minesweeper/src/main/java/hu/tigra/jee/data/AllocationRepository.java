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
package hu.tigra.jee.data;

import hu.tigra.jee.model.Allocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class AllocationRepository {

    @Inject
    private EntityManager em;

    public Allocation findById(Long id) {
        return em.find(Allocation.class, id);
    }

    public Allocation findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Allocation> criteria = cb.createQuery(Allocation.class);
        Root<Allocation> allocation = criteria.from(Allocation.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(allocation).where(cb.equal(allocation.get(Allocation_.name), email));
        criteria.select(allocation).where(cb.equal(allocation.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Allocation> findAllOrderedBySubject() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Allocation> criteria = cb.createQuery(Allocation.class);
        Root<Allocation> allocation = criteria.from(Allocation.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(allocation).orderBy(cb.asc(allocation.get(Allocation_.name)));
        criteria.select(allocation).orderBy(cb.asc(allocation.get("subject")));
        return em.createQuery(criteria).getResultList();
    }
}
