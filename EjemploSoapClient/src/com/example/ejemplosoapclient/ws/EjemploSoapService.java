package com.example.ejemplosoapclient.ws;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.5
//
// Date Of Creation: 5/27/2014 12:35:49 AM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import java.util.List;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

import com.example.ejemplosoapclient.ws.WS_Enums.SoapProtocolVersion;

public class EjemploSoapService {
    
    public String NAMESPACE ="http://ejemploSoap.unia.ual.es";
    public String url="http://10.0.2.2:8080/SoapProject/services/EjemploSoap?wsdl";
    public int timeOut = 60000;
    public IWsdl2CodeEvents eventHandler;
    public SoapProtocolVersion soapVersion;
    
    public EjemploSoapService(){}
    
    public EjemploSoapService(IWsdl2CodeEvents eventHandler)
    {
        this.eventHandler = eventHandler;
    }
    public EjemploSoapService(IWsdl2CodeEvents eventHandler,String url)
    {
        this.eventHandler = eventHandler;
        this.url = url;
    }
    public EjemploSoapService(IWsdl2CodeEvents eventHandler,String url,int timeOutInSeconds)
    {
        this.eventHandler = eventHandler;
        this.url = url;
        this.setTimeOut(timeOutInSeconds);
    }
    public void setTimeOut(int seconds){
        this.timeOut = seconds * 1000;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void sumarAsync(int a,int b) throws Exception{
        if (this.eventHandler == null)
            throw new Exception("Async Methods Requires IWsdl2CodeEvents");
        sumarAsync(a, b, null);
    }
    
    public void sumarAsync(final int a,final int b,final List<HeaderProperty> headers) throws Exception{
        
        new AsyncTask<Void, Void, Number>(){
            @Override
            protected void onPreExecute() {
                eventHandler.Wsdl2CodeStartedRequest();
            };
            @Override
            protected Number doInBackground(Void... params) {
                return sumar(a, b, headers);
            }
            @Override
            protected void onPostExecute(Number result)
            {
                eventHandler.Wsdl2CodeEndedRequest();
                if (result != null){
                    eventHandler.Wsdl2CodeFinished("sumar", result);
                }
            }
        }.execute();
    }
    
    public int sumar(int a,int b){
        return sumar(a, b, null);
    }
    
    public int sumar(int a,int b,List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://ejemploSoap.unia.ual.es","sumar");
        soapReq.addProperty("a",a);
        soapReq.addProperty("b",b);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://ejemploSoap.unia.ual.es/sumar", soapEnvelope,headers);
            }else{
                httpTransport.call("http://ejemploSoap.unia.ual.es/sumar", soapEnvelope);
            }
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault){
                SoapFault fault = (SoapFault)retObj;
                Exception ex = new Exception(fault.faultstring);
                if (eventHandler != null)
                    eventHandler.Wsdl2CodeFinishedWithException(ex);
            }else{
                SoapObject result=(SoapObject)retObj;
                if (result.getPropertyCount() > 0){
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                        SoapPrimitive j =(SoapPrimitive) obj;
                        int resultVariable = Integer.parseInt(j.toString());
                        return resultVariable;
                    }else if (obj!= null && obj instanceof Number){
                        int resultVariable = (Integer) obj;
                        return resultVariable;
                    }
                }
            }
        }catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
        return -1;
    }
    
}
