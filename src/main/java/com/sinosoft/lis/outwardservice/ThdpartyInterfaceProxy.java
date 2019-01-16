package com.sinosoft.lis.outwardservice;

public class ThdpartyInterfaceProxy implements com.sinosoft.lis.outwardservice.ThdpartyInterface {
  private String _endpoint = null;
  private com.sinosoft.lis.outwardservice.ThdpartyInterface thdpartyInterface = null;
  
  public ThdpartyInterfaceProxy() {
    _initThdpartyInterfaceProxy();
  }
  
  public ThdpartyInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initThdpartyInterfaceProxy();
  }
  
  private void _initThdpartyInterfaceProxy() {
    try {
      thdpartyInterface = (new com.sinosoft.lis.outwardservice.ThdpartyInterfaceServiceLocator()).getThdpartyInterface();
      if (thdpartyInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)thdpartyInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)thdpartyInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (thdpartyInterface != null)
      ((javax.xml.rpc.Stub)thdpartyInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sinosoft.lis.outwardservice.ThdpartyInterface getThdpartyInterface() {
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface;
  }
  
  public java.lang.String checkRequest(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.checkRequest(xml);
  }
  
  public java.lang.String policyRequest(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.policyRequest(xml);
  }
  
  public java.lang.String underwritingRequestttt(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.underwritingRequestttt(xml);
  }
  
  public java.lang.String addInvoiceInfPush(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.addInvoiceInfPush(xml);
  }
  
  public java.lang.String underwritingRequest(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.underwritingRequest(xml);
  }
  
  public java.lang.String cancelRequest(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.cancelRequest(xml);
  }
  
  public java.lang.String printRequest(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.printRequest(xml);
  }
  
  public java.lang.String callbackCyx(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.callbackCyx(xml);
  }
  
  public java.lang.String synPlan(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.synPlan(xml);
  }
  
  public java.lang.String callbackCertify(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.callbackCertify(xml);
  }
  
  public java.lang.String synCertify(java.lang.String xml) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    return thdpartyInterface.synCertify(xml);
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (thdpartyInterface == null)
      _initThdpartyInterfaceProxy();
    thdpartyInterface.main(args);
  }
  
  
}