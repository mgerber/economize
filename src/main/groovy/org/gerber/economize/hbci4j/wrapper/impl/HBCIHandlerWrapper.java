/**
 * 
 */
package org.gerber.economize.hbci4j.wrapper.impl;

import java.security.KeyPair;
import java.util.List;
import java.util.Properties;

import org.gerber.economize.hbci4j.wrapper.HBCIJobWrapper;
import org.kapott.hbci.manager.HBCIHandler;
import org.kapott.hbci.manager.HBCIKernel;
import org.kapott.hbci.manager.MsgGen;
import org.kapott.hbci.passport.HBCIPassport;
import org.kapott.hbci.status.HBCIDialogStatus;
import org.kapott.hbci.status.HBCIExecThreadedStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
public class HBCIHandlerWrapper {
	final HBCIHandler hbciHandler;

    public HBCIHandlerWrapper(String hbciversion,HBCIPassport passport) {
    	this.hbciHandler = new HBCIHandler(hbciversion, passport);
    }
    
    public void updateSEPAInfo() {
    	this.hbciHandler.updateSEPAInfo();
    }
    
    public void close() {
    	this.hbciHandler.close();
    }
    
    public void newMsg(final String customerId) {
    	this.hbciHandler.newMsg(customerId);
    }
    
    public void newMsg() {
    	this.hbciHandler.newMsg();
    }
    
    public HBCIJobWrapper newJob(final String jobname) {
    	return (HBCIJobWrapper) this.hbciHandler.newJob(jobname);
    }
    
    public HBCIJobWrapper newLowlevelJob(final String gvname) {
    	return (HBCIJobWrapper) this.hbciHandler.newLowlevelJob(gvname);
    }
    
    public void addJobToDialog(final String customerId, final HBCIJobWrapper job) {
    	this.hbciHandler.addJobToDialog(customerId, job);
    }

    public void createEmptyDialog(final String customerId) {
    	this.hbciHandler.createEmptyDialog(customerId);
    }
    
    public void createEmptyDialog() {
    	this.hbciHandler.createEmptyDialog();
    }
    
    public HBCIExecStatusWrapper execute() {
    	return (HBCIExecStatusWrapper) this.hbciHandler.execute();
    }
    
    public HBCIExecThreadedStatus executeThreaded() {
    	return this.hbciHandler.executeThreaded();
    }
    
    public HBCIExecThreadedStatus continueThreaded(final String retData) {
    	return this.hbciHandler.continueThreaded(retData);
    }
    
    public void lockKeys() {
    	this.hbciHandler.lockKeys();
    }
    
    public void newKeys() {
    	this.hbciHandler.newKeys();
    }
    
    public void setKeys(final KeyPair sigKey, final KeyPair encKey) {
    	this.hbciHandler.setKeys(sigKey, encKey);
    }
    
    public HBCIExecStatusWrapper verifyTAN(final String customerId) {
    	return (HBCIExecStatusWrapper) this.hbciHandler.verifyTAN(customerId);
    }
    
    public HBCIExecStatusWrapper verifyTAN() {
    	return (HBCIExecStatusWrapper) this.hbciHandler.verifyTAN();
    }

    public void reset() {
    	this.hbciHandler.reset();
    }
    
    public HBCIPassport getPassport() {
    	return this.hbciHandler.getPassport();
    }
    
    public HBCIKernel getKernel() {
    	return this.hbciHandler.getKernel();
    }
    
    public MsgGen getMsgGen() {
    	return this.hbciHandler.getMsgGen();
    }
    
    public String getHBCIVersion() {
    	return this.hbciHandler.getHBCIVersion();
    }
    
    public Properties getSupportedLowlevelJobs() {
    	return this.hbciHandler.getSupportedLowlevelJobs();
    }
    
    public List<String> getLowlevelJobParameterNames(final String gvname) {
    	return this.hbciHandler.getLowlevelJobParameterNames(gvname);
    }
    
    public List<String> getLowlevelJobResultNames(final String gvname) {
    	return this.hbciHandler.getLowlevelJobResultNames(gvname);
    }
    
    public Properties getLowlevelJobRestrictions(final String gvname) {
    	return this.hbciHandler.getLowlevelJobRestrictions(gvname);
    }

    public boolean isSupported(final String jobnameHL) {
    	return this.hbciHandler.isSupported(jobnameHL);
    }
    
    public HBCIDialogStatus refreshXPD(final int selectX)  {
    	return this.hbciHandler.refreshXPD(selectX);
    }
}
