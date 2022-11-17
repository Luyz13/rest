package com.levm.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.levm.rest.entity.*;

@Repository
public class FacturaRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Factura> buscarTodas()
	{
		return em.createQuery("SELECT f FROM Factura f",Factura.class).getResultList();
	}

	public Factura buscarUna(int numero)
	{
		return em.find(Factura.class, numero);
	}
	@Transactional
	public void eliminarUna(Factura factura)
	{
		em.remove(em.merge(factura));
	}
	@Transactional 
	public void agregarUna(Factura factura)
	{
		 em.persist(factura);
	}
	@Transactional 
	public void actualizarUna(Factura factura)
	{
		 em.merge(factura);
	}
}
