package com.levm.rest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levm.rest.entity.Factura;
import com.levm.rest.repository.FacturaRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
//@Sql(scripts={ "/schem.sql", "/data.sql" },executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RestApplicationTests {

	@Autowired
	FacturaRepository repositorio;

	@Test
	void buscarTodosTest() {

		List<Factura> lista = repositorio.buscarTodas();
		assertThat(lista, hasItem(new Factura(2)));
	}

	@Test
	void buscarTodosRESTTest() {

		get("/facturas").then().body("numero", hasItem(3));
	}

	@Test
	void buscarUnaRESTTest() {

		get("/facturas/2").then().body("numero", equalTo(2));
	}
	@Test
	void eliminarUnaRESTTest() {

		given().pathParam("numero",1)
		.delete("/facturas/{numero}").then().statusCode(200);
	}
	@Test
	void insertarUnaRESTTest() throws JsonProcessingException {

		Factura f = new Factura(3,"auricular",200);
		ObjectMapper mapeador = new ObjectMapper();
		String json= mapeador.writeValueAsString(f);
		
		given()
		.header("Content-type","application/json")
		.and().body(json)
		.post("/facturas").then().statusCode(200);
		
		get("facturas/3").then().body("numero",equalTo(3));
	}
	@Test
	void actualizarUnaRESTTest() throws JsonProcessingException {

		Factura f = new Factura(2,"televisor2",400);
		ObjectMapper mapeador = new ObjectMapper();
		String json= mapeador.writeValueAsString(f);
		
		given()
		.header("Content-type","application/json")
		.and().body(json)
		.pathParam("numero",2)
		.put("/facturas/{numero}").then().statusCode(200);
		
		get("facturas/2").then().body("concepto",equalTo("televisor2"));
	}
}
