package com.tanpham.playground.javaee.thorntailplayground.rest.lob.resource;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("lob")
@Stateless
public class LobResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LobEntity> getAll() {
        return em.createQuery("SELECT le FROM LobEntity le").getResultList();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public LobBasicEntity updateLongText() {
        LobBasicEntity lbe = em.find(LobBasicEntity.class, 1l);
        lbe.setLongText(null);
        em.flush();
        lbe = em.find(LobBasicEntity.class, 1l);
        lbe.setLongText(randomAlphabetic(1000000));
        lbe.setBytearray(randomAlphabetic(1000000).getBytes());
        return lbe;
    }
}
