package es.ual.unia.ejemploSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="EjemploSoap")
public class EjemploSoap {

	@WebMethod(operationName="sumar")
	public int sumar(int a, int b)
	{
		return a+b;
		
	}
}
