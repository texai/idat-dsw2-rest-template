package edu.idat.cursoweb2.model;

import java.io.Serializable;
import java.util.Date;

public class Instructor implements Serializable{
    private static final long serialVersionUID=1L;

    private Integer instructorId;
	private String nombre;
	private String apellidos;
	private String password;
	private String email;
	private Date fregistro;
	
	public Instructor() {		
	}

    public String toString(){
        return this.nombre + " " + this.apellidos + " (" + this.email + ") " ;
    }
	
    public String toStringWeb(){
        String opVerLink = "<a href=http://localhost:8091/web/instructor/ver-obj/"+this.instructorId+">[ver]</a>";
        return "<li>" + this.nombre + " <b>" + this.apellidos + "</b> (" + this.email + ") "+opVerLink+"</li>" ;
    }
	
	public Instructor(Instructor instructor) 
	{
		this(instructor.getInstructorId(),instructor.getNombre(),instructor.getApellidos(),
			 instructor.getPassword(),instructor.getEmail(),instructor.getFregistro());		
	}

    public Instructor(Integer instructorId, String nombre, String apellidos, String password, 
                          String email, Date fregistro) {
		this.instructorId = instructorId;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.email = email;
		this.fregistro = fregistro;
	}

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFregistro() {
        return fregistro;
    }

    public void setFregistro(Date fregistro) {
        this.fregistro = fregistro;
    }

    


}
