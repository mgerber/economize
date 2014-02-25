/**
 * 
 */
package org.gerber.economize.hbci4j.wrapper.impl

import org.gerber.economize.service.HbciServiceCallback
import org.gerber.economize.service.dto.BankDTO;
import org.kapott.hbci.callback.AbstractHBCICallback
import org.kapott.hbci.callback.HBCICallback
import org.kapott.hbci.passport.HBCIPassport
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Michael Gerber
 *
 */
class HbciCallback extends AbstractHBCICallback
{
	private static final Logger LOGGER = LoggerFactory.getLogger(HbciCallback)
	
	private final BankDTO bank
	private final HbciServiceCallback hbciServiceCallback
	/**
	 * 
	 */
	public HbciCallback(final BankDTO bank, final HbciServiceCallback hbciServiceCallback)
	{
		this.bank = bank
		this.hbciServiceCallback = hbciServiceCallback
	}

	/* (non-Javadoc)
	 * @see org.kapott.hbci.callback.HBCICallback#callback(org.kapott.hbci.passport.HBCIPassport, int, java.lang.String, int, java.lang.StringBuffer)
	 */
	@Override
	public void callback(HBCIPassport arg0, int arg1, String arg2, int arg3,
			StringBuffer arg4)
	{
		switch(arg1)
		{
			case HBCICallback.NEED_CHIPCARD:
				LOGGER.info 'NEED_CHIPCARD {}', arg4
			break;
			case HBCICallback.NEED_HARDPIN:
				LOGGER.info 'NEED_HARDPIN {}', arg4
			break;
			case HBCICallback.NEED_SOFTPIN:
				LOGGER.info 'NEED_SOFTPIN {}', arg4
				arg4.replace(0,arg4.length(),"");
			break;
			case HBCICallback.HAVE_HARDPIN:
				LOGGER.info 'HAVE_HARDPIN {}', arg4
			break;
			case HBCICallback.HAVE_CHIPCARD:
				LOGGER.info 'HAVE_CHIPCARD {}', arg4
			break;
			case HBCICallback.NEED_COUNTRY:
				LOGGER.info 'NEED_COUNTRY {}', arg4
 				arg4.replace(0,arg4.length(), this.bank.country);
			break;
			case HBCICallback.NEED_BLZ:
				LOGGER.info 'NEED_BLZ {}', arg4
				arg4.replace(0,arg4.length(), this.bank.bankCode);
			break;
			case HBCICallback.NEED_HOST:
				LOGGER.info 'NEED_HOST {}', arg4
				arg4.replace(0,arg4.length(), this.bank.host);
			break;
			case HBCICallback.NEED_PORT:
				LOGGER.info 'NEED_PORT {}', arg4
				arg4.replace(0,arg4.length(), this.bank.port);
			break;
			case HBCICallback.NEED_USERID:
				LOGGER.info 'NEED_USERID {}', arg4
				arg4.replace(0,arg4.length(),"17687351");
			break;
			case HBCICallback.NEED_NEW_INST_KEYS_ACK:
				LOGGER.info 'NEED_NEW_INST_KEYS_ACK {}', arg4
			break;
			case HBCICallback.HAVE_NEW_MY_KEYS:
				LOGGER.info 'HAVE_NEW_MY_KEYS {}', arg4
			break;
			case HBCICallback.HAVE_INST_MSG:
				LOGGER.info 'HAVE_INST_MSG {}', arg4
			break;
			case HBCICallback.NEED_REMOVE_CHIPCARD:
				LOGGER.info 'NEED_REMOVE_CHIPCARD {}', arg4
			break;
			case HBCICallback.NEED_PT_PIN:
				LOGGER.info 'NEED_PT_PIN {}', arg4
				LOGGER.info 'calling password callback'
				String pin = this.hbciServiceCallback.getPassword()
				arg4.replace(0, pin.length(), pin);
			break;			
			case HBCICallback.NEED_PT_TAN:
				LOGGER.info 'NEED_PT_TAN {}', arg4
				arg4.replace(0,arg4.length(),"");
			break;
			case HBCICallback.NEED_CUSTOMERID:
				LOGGER.info 'NEED_CUSTOMERID {}', arg4
				arg4.replace(0,arg4.length(),"");
			break;
			case HBCICallback.HAVE_CRC_ERROR:
				LOGGER.info 'HAVE_CRC_ERROR {}', arg4
			break;
			case HBCICallback.HAVE_ERROR:
				LOGGER.info 'HAVE_ERROR {}', arg4
			break;
			case HBCICallback.NEED_PASSPHRASE_LOAD:
				LOGGER.info 'NEED_PASSPHRASE_LOAD {}', arg4
				arg4.replace(0,arg4.length(),"nutzer1");
			break;
			case HBCICallback.NEED_PASSPHRASE_SAVE:
				LOGGER.info 'NEED_PASSPHRASE_SAVE {}', arg4
				arg4.replace(0,arg4.length(),"nutzer1");
			break;
			case HBCICallback.NEED_SIZENTRY_SELECT:
				LOGGER.info 'NEED_SIZENTRY_SELECT {}', arg4
			break;
			case HBCICallback.NEED_CONNECTION:
				LOGGER.info 'NEED_CONNECTION {}', arg4
			break;
			case HBCICallback.CLOSE_CONNECTION:
				LOGGER.info 'CLOSE_CONNECTION {}', arg4
			break;
			case HBCICallback.NEED_FILTER:
				LOGGER.info 'NEED_FILTER {}', arg4
			break;
			case HBCICallback.NEED_PT_SECMECH:
				LOGGER.info 'NEED_PT_SECMECH {}', arg4
			break;
			case HBCICallback.NEED_PROXY_USER:
				LOGGER.info 'NEED_PROXY_USER {}', arg4
			break;
			case HBCICallback.NEED_PROXY_PASS:
				LOGGER.info 'NEED_PROXY_PASS {}', arg4
			break;
			case HBCICallback.HAVE_IBAN_ERROR:
				LOGGER.info 'HAVE_IBAN_ERROR {}', arg4
			break;
			case HBCICallback.NEED_INFOPOINT_ACK:
				LOGGER.info 'NEED_INFOPOINT_ACK {}', arg4
			break;
			case HBCICallback.NEED_PT_TANMEDIA:
				LOGGER.info 'NEED_PT_TANMEDIA {}', arg4
			break;
			case HBCICallback.WRONG_PIN:
				LOGGER.info 'WRONG_PIN {}', arg4
			break;
			case HBCICallback.USERID_CHANGED:
				LOGGER.info 'USERID_CHANGED {}', arg4
			break;
			default:
				LOGGER.info 'unknown reason {}', arg4
			break;
		}
	}

	/* (non-Javadoc)
	 * @see org.kapott.hbci.callback.HBCICallback#log(java.lang.String, int, java.util.Date, java.lang.StackTraceElement)
	 */
	@Override
	public void log(String arg0, int arg1, Date arg2, StackTraceElement arg3)
	{
		//super.log(arg0, arg1, arg2, arg3);
		LOGGER.info '{} {} {} {}',arg0, arg1, arg2, arg3
	}

	/* (non-Javadoc)
	 * @see org.kapott.hbci.callback.HBCICallback#status(org.kapott.hbci.passport.HBCIPassport, int, java.lang.Object[])
	 */
	@Override
	public void status(HBCIPassport arg0, int arg1, Object[] arg2)
	{
		switch(arg1)
		{
			case HBCICallback.STATUS_SEND_TASK:
				LOGGER.info 'Status STATUS_SEND_TASK'
			break;
			case HBCICallback.STATUS_SEND_TASK_DONE:
				LOGGER.info 'Status STATUS_SEND_TASK_DONE'
			break;
			case HBCICallback.STATUS_INST_BPD_INIT:
				LOGGER.info 'Status STATUS_INST_BPD_INIT'
			break;
			case HBCICallback.STATUS_INST_BPD_INIT_DONE:
				LOGGER.info 'Status STATUS_INST_BPD_INIT_DONE'
			break;
			case HBCICallback.STATUS_INST_GET_KEYS:
				LOGGER.info 'Status STATUS_INST_GET_KEYS'
			break;
			case HBCICallback.STATUS_INST_GET_KEYS_DONE:
				LOGGER.info 'Status STATUS_INST_GET_KEYS_DONE'
			break;
			case HBCICallback.STATUS_SEND_KEYS:
				LOGGER.info 'Status STATUS_SEND_KEYS'
			break;
			case HBCICallback.STATUS_SEND_KEYS_DONE:
				LOGGER.info 'Status STATUS_SEND_KEYS_DONE'
			break;
			case HBCICallback.STATUS_INIT_SYSID:
				LOGGER.info 'Status STATUS_INIT_SYSID'
			break;
			case HBCICallback.STATUS_INIT_SYSID_DONE:
				LOGGER.info 'Status STATUS_INIT_SYSID_DONE'
			break;
			case HBCICallback.STATUS_INIT_UPD:
				LOGGER.info 'Status STATUS_INIT_UPD'
			break;
			case HBCICallback.STATUS_INIT_UPD_DONE:
				LOGGER.info 'Status STATUS_INIT_UPD_DONE'
			break;
			case HBCICallback.STATUS_LOCK_KEYS:
				LOGGER.info 'Status STATUS_LOCK_KEYS'
			break;
			case HBCICallback.STATUS_LOCK_KEYS_DONE:
				LOGGER.info 'Status STATUS_LOCK_KEYS_DONE'
			break;
			case HBCICallback.STATUS_INIT_SIGID:
				LOGGER.info 'Status STATUS_INIT_SIGID'
			break;
			case HBCICallback.STATUS_INIT_SIGID_DONE:
				LOGGER.info 'Status STATUS_INIT_SIGID_DONE'
			break;
			case HBCICallback.STATUS_DIALOG_INIT:
				LOGGER.info 'Status STATUS_DIALOG_INIT'
			break;
			case HBCICallback.STATUS_DIALOG_INIT_DONE:
				LOGGER.info 'Status STATUS_DIALOG_INIT_DONE'
			break;
			case HBCICallback.STATUS_DIALOG_END:
				LOGGER.info 'Status STATUS_DIALOG_END'
			break;
			case HBCICallback.STATUS_DIALOG_END_DONE:
				LOGGER.info 'Status STATUS_DIALOG_END_DONE'
			break;
			case HBCICallback.STATUS_MSG_CREATE:
				LOGGER.info 'Status STATUS_MSG_CREATE'
			break;
			case HBCICallback.STATUS_MSG_SIGN:
				LOGGER.info 'Status STATUS_MSG_SIGN'
			break;
			case HBCICallback.STATUS_MSG_CRYPT:
				LOGGER.info 'Status STATUS_MSG_CRYPT'
			break;
			case HBCICallback.STATUS_MSG_SEND:
				LOGGER.info 'Status STATUS_MSG_SEND'
			break;
			case HBCICallback.STATUS_MSG_DECRYPT:
				LOGGER.info 'Status STATUS_MSG_DECRYPT'
			break;
			case HBCICallback.STATUS_MSG_VERIFY:
				LOGGER.info 'Status STATUS_MSG_VERIFY'
			break;
			case HBCICallback.STATUS_MSG_RECV:
				LOGGER.info 'Status STATUS_MSG_RECV'
			break;
			case HBCICallback.STATUS_MSG_PARSE:
				LOGGER.info 'Status STATUS_MSG_PARSE'
			break;
			case HBCICallback.STATUS_SEND_INFOPOINT_DATA:
				LOGGER.info 'Status STATUS_SEND_INFOPOINT_DATA'
			break;
			default:
				LOGGER.info 'Status unknown'
			break;
		}
	}

}
