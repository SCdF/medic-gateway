package medic.gateway;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class WoListActivity extends Activity {
	private static final int MAX_WO_MESSAGES = 100;

	private static final String[] WO_LIST_FROM = {
		"to",
		"status",
		"content",
		"lastAction",
	};
	private static final int[] WO_LIST_TO = {
		R.id.txtWoTo,
		R.id.txtWoStatus,
		R.id.txtWoContent,
		R.id.txtWoLastAction,
	};

	private Db db;
	private ListView list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_list_wo);

		db = Db.getInstance(this);
		list = (ListView) findViewById(R.id.lstWoMessages);

		((Button) findViewById(R.id.btnRefreshWoMessageList))
				.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) { refreshList(); }
		});

		refreshList();
	}

	private void refreshList() {
		list.setAdapter(new SimpleAdapter(this,
				db.getWoMessages(MAX_WO_MESSAGES),
				R.layout.wo_list_item,
				WO_LIST_FROM,
				WO_LIST_TO));
	}
}
