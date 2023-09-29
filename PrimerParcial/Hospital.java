package PrimerParcial;

import java.util.*;
import java.io.*;
public class Hospital {

    public static void main(String[] args) {
        int v,b;
        int dni, numero;
        String nombre,tipoDeSangre,fechaDeNacimiento;
        boolean c = true;
        String datos = "";
        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Doctor> doctores = new ArrayList<>();
        doctores.add(new Doctor(8888888,"Jhon Smith", "22/12/1985","cirujano"));
        doctores.add(new Doctor(7777777,"Jane Doe","25/05/1990","traumatologa"));
        try (Scanner s = new Scanner(new File("datos.txt"))) {
            datos = s.nextLine();
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el nombre del hotel desde el archivo: " + e.getMessage());
        }

        while (c){
            Scanner s = new Scanner(System.in);
            System.out.println(datos);
            System.out.println("Menú:");
            System.out.println("1-Listar Doctores");
            System.out.println("2-Registrar un nuevo paciente");
            System.out.println("3-Actualizar informacion personal de un paciente");
            System.out.println("4-Consultar Historial medico de un paciente");
            System.out.println("5-Nuevo historial para un paciente");
            System.out.println("6-Guardar historial de pacientes en un archivo");
            System.out.println("7-Cargar historial de un pacientes desde un archivo");
            System.out.println("8-Salir");
            v = s.nextInt();
            switch (v){
                case 1:
                    for (Doctor d:doctores) {
                        d.toString();
                    }
                    break;
                case 2:
                    System.out.println("ingrese el nombre del paciente");
                    nombre = s.nextLine();
                    System.out.println("ingrese el dni del paciente");
                    dni = s.nextInt();
                    System.out.println("ingrese el numero del paciente");
                    numero = s.nextInt();
                    System.out.println("ingrese la fecha de nacimiento del paciente con el formato dd/MM/AAAA");
                    fechaDeNacimiento = s.nextLine();
                    System.out.println("ingrese el tipo de sangre del paciente");
                    tipoDeSangre = s.nextLine();
                    pacientes.add(new Paciente(dni, nombre,fechaDeNacimiento, tipoDeSangre, numero));
                    break;
                case 3:
                    System.out.println("inngrese el dni del paciente");
                    b = s.nextInt();
                    for (Paciente p: pacientes) {
                        if (p.getDni() == b){
                            System.out.println("ingrese el nombre del paciente");
                            p.setNombre(s.nextLine());
                            System.out.println("ingrese el dni del paciente");
                            p.setDni(s.nextInt());
                            System.out.println("ingrese el numero del paciente");
                            p.setNumeroDeTelefono(s.nextInt());
                            System.out.println("ingrese la fecha de nacimiento del paciente con el formato dd/MM/AAAA");
                            p.setFechaDeNacimiento(s.nextLine());
                            System.out.println("ingrese el tipo de sangre del paciente");
                            p.setTipoDeSangre(s.nextLine());
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("inngrese el dni del paciente");
                    b = s.nextInt();
                    for (Paciente p: pacientes) {
                            if (p.getDni() == b){
                                p.verHistorialDeEventos();
                                break;
                            }
                    }
                    break;
                case 5:
                    System.out.println("inngrese el dni del paciente");
                    b = s.nextInt();
                    for (Paciente p: pacientes) {
                        if (p.getDni() == b){
                            String fecha, observaciones;
                            System.out.println("ingrese la fecha con formato dd/MM/aaaa");
                            fecha = s.nextLine();
                            System.out.println("ingrese las observaciones");
                            observaciones = s.nextLine();
                            p.agregarEvento(fecha,observaciones);
                            break;
                        }
                    }
                    break;
                case 6:

                    try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("pacientes.txt"))) {
                        salida.writeObject(pacientes);
                        System.out.println("Reservas guardadas en archivo exitosamente.");
                    } catch (IOException e) {
                        System.err.println("Error al guardar las reservas en el archivo: " + e.getMessage());
                    }
                    break;
                case 7:
                    try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("pacientes.txt"))) {
                        pacientes = (ArrayList<Paciente>) entrada.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error al cargar las reservas desde el archivo: " + e.getMessage());
                        }
                case 8:
                    c = false;
                    break;
                default:
                    System.out.println("opcion invalida reingrese");
                    break;
            }
        }

    }

}
//--------------------------------------PrimerParcial.Persona--------------------------------------------------//
abstract class Persona implements Serializable{
    protected int dni;
    protected String nombre;
    protected String fechaDeNacimiento;

    public Persona(int dni, String nombre, String fechaDeNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
//------------------------------------------PrimerParcial.Doctor------------------------------------------//
class Doctor extends Persona implements Serializable{
    private String especialidad;
    public Doctor(int dni, String nombre, String fechaDeNacimiento, String especialidad) {
        super(dni, nombre, fechaDeNacimiento);
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "PrimerParcial.Doctor{" +
                "especialidad='" + especialidad + '\n' +
                "dni=" + dni +'\n'+
                "nombre='" + nombre + '\n' +
                "fechaDeNacimiento='" + fechaDeNacimiento + '\n' +
                '}';
    }
}
//-----------------------------PrimerParcial.Informacion-------------------------------------------//
interface Informacion{
    public void verHistorialDeEventos();
}
//--------------------------------PrimerParcial.Paciente-----------------------------------------//
class Paciente extends Persona implements Serializable,Informacion {
    private String tipoDeSangre;
    private int numeroDeTelefono;
    private ArrayList<String> historial = new ArrayList<>();

    public Paciente(int dni, String nombre, String fechaDeNacimiento, String tipoDeSangre, int numeroDeTelefono) {
        super(dni, nombre, fechaDeNacimiento);
        this.tipoDeSangre = tipoDeSangre;
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public void agregarEvento(String fecha, String observaciones) {
        historial.add(fecha + " - " + observaciones);
    }

    @Override
    public void verHistorialDeEventos() {
        System.out.println("Historial Medico:");
        for (String h : historial) {
            System.out.println(h);
        }
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public int getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(int numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }


    @Override
    public int getDni() {
        return super.getDni();
    }

    @Override
    public void setDni(int dni) {
        super.setDni(dni);
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getFechaDeNacimiento() {
        return super.getFechaDeNacimiento();
    }

    @Override
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        super.setFechaDeNacimiento(fechaDeNacimiento);
    }


}