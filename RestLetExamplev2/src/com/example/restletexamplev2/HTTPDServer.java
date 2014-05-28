package com.example.restletexamplev2;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HTTPDServer extends ServerResource{

	private Server server;
	private static int port;
	
	public HTTPDServer(int Mport) {
		super();
		port=Mport;
		
	}
	public void init()
	{
		
		server= new Server(Protocol.HTTP,port,HTTPDServer.class );
	}
	public void Start() throws Exception
	{
		
		this.server.start();
	}
	public void stop() throws Exception
	{
		
		this.server.stop();
	}
	@Get
	public String doget()
	{
		String html="Content-type: text/html\n\n";
		html+="<html><head><title>Hola</title></head><body><p>Hola a todos</p></body></html>";
		//String html="Hola a todos";
		return html;
	}
}
