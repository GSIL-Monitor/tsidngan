/**
 * ThdpartyInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinosoft.lis.outwardservice;

public interface ThdpartyInterface extends java.rmi.Remote {
    public java.lang.String checkRequest(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String policyRequest(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String underwritingRequestttt(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String addInvoiceInfPush(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String underwritingRequest(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String cancelRequest(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String printRequest(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String callbackCyx(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String synPlan(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String callbackCertify(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String synCertify(java.lang.String xml) throws java.rmi.RemoteException;
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
}
