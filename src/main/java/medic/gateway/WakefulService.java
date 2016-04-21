package medic.gateway;

import android.app.*;
import android.content.*;
import android.os.*;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import static medic.gateway.GatewayLog.*;

public class WakefulService extends WakefulIntentService {
	public WakefulService() {
		super("WakefulService");
	}

	public void doWakefulWork(Intent intent) {
		logEvent(this, "WakefulService.doWakefulWork()");

		try {
			new SmsSender(this).sendUnsentSmses();
		} catch(Exception ex) {
			logException(this, ex, "Exception caught trying to send SMSes: " + ex.getMessage());
		}
		try {
			new WebappPoller(this).pollWebapp();
		} catch(Exception ex) {
			logException(this, ex, "Exception caught trying to poll webapp: " + ex.getMessage());
		}
	}
}
