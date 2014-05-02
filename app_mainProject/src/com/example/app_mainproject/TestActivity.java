package com.example.app_mainproject;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		LoadContacts();
		addListenerButtons();
	}

	private void addListenerButtons() {
		addListenerAddButton();
		addListenerRemoveAllButton();
	}

	private TestActivity self() {
		return this;
	}

	private void addListenerAddButton() {
		((Button) findViewById(R.id.add))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(self(),
								AddEditContactActivity.class);
						startActivityForResult(i,
								ADD_EDIT_CONTACT_ACTIVITY_REQUEST_CODE);
					}
				});
	}

	private void addListenerRemoveAllButton() {
		((Button) findViewById(R.id.rem))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DataStorage.Clear();
						TableLayout tl = (TableLayout) findViewById(R.id.table);
						tl.removeAllViews();
						mapRow.clear();
					}
				});
	}

	private HashMap<Integer, TableRow> mapRow = new HashMap<>();

	private void LoadContacts() {
		List<Contact> l = DataStorage.GetAllContacts();
		TableLayout tl = (TableLayout) findViewById(R.id.table);
		tl.removeAllViews();
		mapRow.clear();
		for (Contact c : l) {
			TableRow row = new TableRow(this);
			AddTableRow(c, tl, row);
		}
	}

	private int ADD_EDIT_CONTACT_ACTIVITY_REQUEST_CODE = 100;

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ADD_EDIT_CONTACT_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Bundle b = data.getExtras();
				if (b != null)
					ReloadContact(b.getInt("ID", -1));
			} else if (resultCode == RESULT_CANCELED) {
				// Do nothing
			}
		}
	}

	private void ReloadContact(int uid) {
		if (uid == -1)
			return;
		Contact c = DataStorage.GetContact(uid);
		if (c == null)
			return;
		TableRow row = mapRow.get(Integer.valueOf(uid));
		TableLayout tl = (TableLayout) findViewById(R.id.table);
		if (row == null) {
			row = new TableRow(this);
			AddTableRow(c, tl, row);
		} else {
			mapRow.remove(Integer.valueOf(uid));
			tl.removeView(row);
			row = new TableRow(this);
			AddTableRow(c, tl, row);
		}
	}

	private void AddTableRow(final Contact c, final TableLayout tl,
			final TableRow row) {
		TableRow.LayoutParams lp = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(lp);
		TextView tv = new TextView(this);
		Button btn = new Button(this);
		tv.setText(c.getName());
		btn.setText("Remove");
		row.addView(btn);
		row.addView(tv);
		tl.addView(row);
		mapRow.put(c.uid, row);
		row.setOnClickListener(new View.OnClickListener() {
			private int uid = c.uid;

			@Override
			public void onClick(View v) {
				Intent i = new Intent(self(), AddEditContactActivity.class);
				i.putExtra("ID", uid);
				startActivityForResult(i,
						ADD_EDIT_CONTACT_ACTIVITY_REQUEST_CODE);
			}
		});
		btn.setOnClickListener(new View.OnClickListener() {
			private int uid = c.uid;

			@Override
			public void onClick(View v) {
				if (DataStorage.RemoveContact(uid))
					tl.removeView(row);
			}
		});
	}
}
