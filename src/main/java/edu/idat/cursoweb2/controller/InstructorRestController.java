package edu.idat.cursoweb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import edu.idat.cursoweb2.model.Instructor;



@RestController
@RequestMapping("/instructor")
public class InstructorRestController {

	// @Autowired
	// private InstructorService instructorService;

	@Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") int id)
    {
	System.out.println("Inside getEmployee method of SpringBootRestTemplateDemo Application");
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<String>(headers);
	String url = "http://localhost:8090/rest/instructor/buscar/"+id;
	ResponseEntity<String> responseEntity = restTemplate.exchange(url , HttpMethod.GET, entity, String.class);
	return responseEntity.getBody();
    }
	


	@RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String getEmployees()
    {
	System.out.println("Inside getEmployees method of SpringBootRestTemplateDemo Application");
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<String>(headers);
	String url = "http://localhost:8090/rest/instructor/listar";
	ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	return responseEntity.getBody();
    }



    @RequestMapping(value = "/ver-obj/{id}", method = RequestMethod.GET)
    public String getEmployee2(@PathVariable("id") String id)
    {
		String url = "http://localhost:8090/rest/instructor/buscar/"+id;
		Instructor instructor = restTemplate.getForObject(url,Instructor.class);
		return instructor.toString() + "<br><br><a href=/web/instructor/>regresar</a>";
    }
	


	@RequestMapping(value = "/listar-obj", method = RequestMethod.GET)
    public String getEmployees2(){
		String url = "http://localhost:8090/rest/instructor/listar";
		Instructor[] instructores = restTemplate.getForObject(url, Instructor[].class);
		String res="";
		for (Instructor instructor : instructores){
			res += instructor.toString2() + "<br />";
		}
		return res;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String web()
    {
		String url = "http://localhost:8090/rest/instructor/listar";
		Instructor[] instructores = restTemplate.getForObject(url, Instructor[].class);
		String res="<h2>Instructores (via API REST): </h2>";
		res += "<form action=/web/instructor/generar method=POST><input type=submit value=Nuevo></form>";
		res += "<ul>";
		for (Instructor instructor : instructores){
			res += instructor.toStringWeb();
		}
		res += "</ul>";

		return res;
    }

	// [DEMO EN CLASE]
	@RequestMapping(value = "/generar", method = RequestMethod.POST)
	public ModelAndView generar()
    {
		Random rand = new Random();
		int id = rand.nextInt(1000)+1000;

		Instructor instructor = new Instructor();
		instructor.setInstructorId(0);
		instructor.setNombre("Instructor "+id);
		instructor.setApellidos("Ape Llidos");
		instructor.setPassword("pwd");
		instructor.setEmail("correo@example.com");
		instructor.setFregistro(new Date());

		restTemplate.postForObject("http://localhost:8090/rest/instructor/agregar",instructor,Instructor.class);
		return new ModelAndView("redirect:/instructor/");
    }

	// [TAREA] Crear un botón para eliminar todos los datos,
	// realizar los cambios respectivos también en el servidor REST
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ModelAndView reset()
    {
		return new ModelAndView("redirect:/instructor/");
	}

	// [TAREA] Crear un botón para eliminar un Instructor

	// [TAREA] Crear un botón para realizar una edición en un Instructor





}
