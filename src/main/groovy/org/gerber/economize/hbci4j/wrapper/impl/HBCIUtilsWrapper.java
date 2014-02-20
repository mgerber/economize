/**
 * 
 */
package org.gerber.economize.hbci4j.wrapper.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.kapott.hbci.GV_Result.GVRKUms;
import org.kapott.hbci.callback.HBCICallback;
import org.kapott.hbci.manager.HBCIUtils;
import org.kapott.hbci.structures.Konto;
import org.springframework.stereotype.Component;

/**
 * @author Michael Gerber
 *
 */
@Component
public class HBCIUtilsWrapper {
	public Properties loadPropertiesFile(final ClassLoader cl, final String configfile) {
		return HBCIUtils.loadPropertiesFile(cl, configfile);
	}

	public void init(final Properties props, final HBCICallback callback) {
		HBCIUtils.init(props, callback);
	}

    public void initThread(final Properties props, final HBCICallback callback) {
    	HBCIUtils.initThread(props, callback);
    }

    public void doneThread() {
    	HBCIUtils.doneThread();
    }

    public void done() {
    	HBCIUtils.done();
    }

    public void initLocale() {
    	HBCIUtils.initLocale();
    }

    public Locale getLocale() {
    	return HBCIUtils.getLocale();
    }

    public String getParam(final String st, final String def) {
    	return HBCIUtils.getParam(st, def);
    }
    
    public Properties getParams() {
    	return HBCIUtils.getParams();
    }

    public String getParam(final String st) {
    	return HBCIUtils.getParam(st);
    }

    public String getNameForBLZ(final String blz) {
    	return HBCIUtils.getNameForBLZ(blz);
    }
    
    public String getBICForBLZ(final String blz) {
    	return HBCIUtils.getBICForBLZ(blz);
    }
    
    public String getIBANForKonto(final Konto k) {
    	return HBCIUtils.getIBANForKonto(k);
    }
    
    public String getHBCIHostForBLZ(final String blz) {
    	return HBCIUtils.getHBCIHostForBLZ(blz);
    }

    public String getPinTanURLForBLZ(final String blz) {
    	return HBCIUtils.getPinTanURLForBLZ(blz);
    }
    
    public String getHBCIVersionForBLZ(final String blz) {
    	return HBCIUtils.getHBCIVersionForBLZ(blz);
    }

    public String getPinTanVersionForBLZ(final String blz) {
    	return HBCIUtils.getPinTanVersionForBLZ(blz);
    }

    public void setParam(final String key, final String value) {
    	HBCIUtils.setParam(key, value);
    }

    public void log(final String st, final int level) {
    	HBCIUtils.log(st, level);
    }
    
    public void log(final Exception e) {
    	HBCIUtils.log(e);
    }
    
    public String exception2String(final Exception e) {
    	return HBCIUtils.exception2String(e);
    }
    
    public String exception2StringShort(final Exception e)  {
    	return HBCIUtils.exception2StringShort(e);
    }
    
    public void log(final Exception e, final int level) {
    	HBCIUtils.log(e, level);
    }

    public String data2hex(final byte[] data) {
    	return HBCIUtils.data2hex(data);
    }

    public String date2StringLocal(final Date date) {
    	return HBCIUtils.date2StringLocal(date);
    }
    
    public Date string2DateLocal(final String date) {
    	return HBCIUtils.string2DateLocal(date);
    }

    public String time2StringLocal(final Date date) {
    	return HBCIUtils.time2StringLocal(date);
    }
    
    public Date string2TimeLocal(final String date) {
    	return HBCIUtils.string2TimeLocal(date);
    }

    public String datetime2StringLocal(final Date date) {
    	return HBCIUtils.datetime2StringLocal(date);
    }
    
    public Date strings2DateTimeLocal(final String date, final String time) {
    	return HBCIUtils.strings2DateTimeLocal(date, time);
    }
    
    public String date2StringISO(final Date date) {
    	return HBCIUtils.date2StringISO(date);
    }
    
    public Date string2DateISO(final String st) {
    	return HBCIUtils.string2DateISO(st);
    }
    
    public String time2StringISO(final Date date) {
    	return HBCIUtils.time2StringISO(date);
    }
    
    public Date string2TimeISO(final String st) {
    	return HBCIUtils.string2TimeISO(st);
    }
    
    public String datetime2StringISO(final Date date) {
    	return HBCIUtils.datetime2StringISO(date);
    }
    
    public Date strings2DateTimeISO(final String date, final String time) {
    	return HBCIUtils.strings2DateTimeISO(date, time);
    }
    
    public String encodeBase64(final byte[] x) {
    	return HBCIUtils.encodeBase64(x);
    }

    public byte[] decodeBase64(final String st) {
    	return HBCIUtils.decodeBase64(st);
    }
    
    public boolean canCheckAccountCRC(final String blz) {
    	return HBCIUtils.canCheckAccountCRC(blz);
    }
    
    public boolean checkAccountCRC(final String blz, final String number) {
    	return HBCIUtils.checkAccountCRC(blz, number);
    }
        
    public boolean checkAccountCRCByAlg(final String alg, final String blz, final String number) {
    	return HBCIUtils.checkAccountCRCByAlg(alg, blz, number);
    }
    
    public boolean checkIBANCRC(final String iban) {
    	return HBCIUtils.checkIBANCRC(iban);
    }
    
    public void refreshBLZList(final InputStream in)
        throws IOException {
    	HBCIUtils.refreshBLZList(in);
    }

    public double string2Value(final String st) {
    	return HBCIUtils.string2Value(st);
    }

    public String value2String(final double value) {
    	return HBCIUtils.value2String(value);
    }
    
    public String version() {
    	return HBCIUtils.version();
    }
    
    public GVRKUms parseMT940(final String mt940) {
    	return HBCIUtils.parseMT940(mt940);
    }
}