/**
 * ThdpartyInterfaceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinosoft.lis.outwardservice;

public class ThdpartyInterfaceServiceLocator extends org.apache.axis.client.Service implements com.sinosoft.lis.outwardservice.ThdpartyInterfaceService {

    public ThdpartyInterfaceServiceLocator() {
    }


    public ThdpartyInterfaceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ThdpartyInterfaceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ThdpartyInterface
    private java.lang.String ThdpartyInterface_address = "https://10.1.150.122:9080/ter/services/ThdpartyInterface";

    public java.lang.String getThdpartyInterfaceAddress() {
        return ThdpartyInterface_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ThdpartyInterfaceWSDDServiceName = "ThdpartyInterface";

    public java.lang.String getThdpartyInterfaceWSDDServiceName() {
        return ThdpartyInterfaceWSDDServiceName;
    }

    public void setThdpartyInterfaceWSDDServiceName(java.lang.String name) {
        ThdpartyInterfaceWSDDServiceName = name;
    }

    public com.sinosoft.lis.outwardservice.ThdpartyInterface getThdpartyInterface() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ThdpartyInterface_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getThdpartyInterface(endpoint);
    }

    public com.sinosoft.lis.outwardservice.ThdpartyInterface getThdpartyInterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sinosoft.lis.outwardservice.ThdpartyInterfaceSoapBindingStub _stub = new com.sinosoft.lis.outwardservice.ThdpartyInterfaceSoapBindingStub(portAddress, this);
            _stub.setPortName(getThdpartyInterfaceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setThdpartyInterfaceEndpointAddress(java.lang.String address) {
        ThdpartyInterface_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sinosoft.lis.outwardservice.ThdpartyInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sinosoft.lis.outwardservice.ThdpartyInterfaceSoapBindingStub _stub = new com.sinosoft.lis.outwardservice.ThdpartyInterfaceSoapBindingStub(new java.net.URL(ThdpartyInterface_address), this);
                _stub.setPortName(getThdpartyInterfaceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ThdpartyInterface".equals(inputPortName)) {
            return getThdpartyInterface();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://outwardservice.lis.sinosoft.com", "ThdpartyInterfaceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://outwardservice.lis.sinosoft.com", "ThdpartyInterface"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ThdpartyInterface".equals(portName)) {
            setThdpartyInterfaceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
