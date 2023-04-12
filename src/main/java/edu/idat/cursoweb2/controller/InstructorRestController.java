package edu.idat.cursoweb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import java.util.Collection;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
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
    public String getEmployee2(@PathVariable("id") int id)
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

	@RequestMapping(value = "/generar", method = RequestMethod.POST)
	public ModelAndView generar()
    {
		MultiValueMap<String,Object> values=new LinkedMultiValueMap<String,Object>();
		values.add("instructorId","1000");
		values.add("nombre","Instructor");
		values.add("apellidos","Ape Llidos");
		values.add("password","pwd");
		values.add("email","correo@example.com");
		values.add("fregistro","2023-02-02T02:02:02.000");
		restTemplate.postForObject("http://localhost:8090/rest/instructor/agregar",values,Instructor.class);
		return new ModelAndView("redirect:/instructor/");
    }

}
