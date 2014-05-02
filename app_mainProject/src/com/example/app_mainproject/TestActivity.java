package com.example.app_mainproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		addListenerButtons();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LoadContacts();
	}

	private void addListenerButtons() {
		addListenerAddButton();
		addListenerRemoveAllButton();
		addListenerBackButton();
		addListenerContacts();
	}

	private TestActivity self() {
		return this;
	}
	
	ArrayList<Integer> idList = new ArrayList<Integer>(Arrays.asList(
			new Integer[] {
					R.id.uid1, 
					R.id.uid2,
					R.id.uid3,
					R.id.uid4,
					R.id.uid5,
					R.id.uid6
			}));
	
	class ContactListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			int userIndex = idList.indexOf(v.getId());
			
			if(userIndex >= contacts.size() || userIndex < 0) {
				return;
			}
			
			Contact contact = contacts.get(userIndex);

			Intent i = new Intent(self(),
					AddEditContactActivity.class);
			i.putExtra("ID", contact.uid);
			startActivityForResult(i,
					ADD_EDIT_CONTACT_ACTIVITY_REQUEST_CODE);
		}
	}
	
	ContactListener contactListener = new ContactListener();
	
	private void addListenerContacts() {
		for(int i : idList) {
			ImageView img = (ImageView) findViewById(i);
			img.setOnClickListener(contactListener);
		}
	}

	private void addListenerBackButton() {
		((Button) findViewById(R.id.backButton))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}
	
	private void addListenerAddButton() {
		((Button) findViewById(R.id.addButton))
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
						ClearContacts();
						//TableLayout tl = (TableLayout) findViewById(R.id.table);
						//tl.removeAllViews();
						//mapRow.clear();
					}
				});
	}

	//private HashMap<Integer, TableRow> mapRow = new HashMap<Integer, TableRow>();
	
	List<Contact> contacts;
	
	int uids[] = new int[6];
	
	private void ClearContacts() {
		for(int i : idList) {
			ImageView img = (ImageView) findViewById(i);
			img.setVisibility(View.INVISIBLE);
		}
		((Button) findViewById(R.id.addButton)).setVisibility(View.VISIBLE);
	}

	private void LoadContacts() {
		List<Contact> l = DataStorage.GetAllContacts();
		Collections.sort(l);
		contacts = l;
		int index = 0;
		for(Contact c : l) {
			if(index>=6) {
				break;
			}
			ImageView img = (ImageView) findViewById(idList.get(index));
			img.setImageBitmap(c.getImage());
			img.setVisibility(View.VISIBLE);
			uids[index] = c.uid;
			index++;
		}
		
		if(index >= 6) {
			((Button) findViewById(R.id.addButton)).setVisibility(View.INVISIBLE);
		}
		
		for(;index<6; ++index) {
			ImageView img = (ImageView) findViewById(idList.get(index));
			img.setVisibility(View.INVISIBLE);
		}
		/*TableLayout tl = (TableLayout) findViewById(R.id.table);
		tl.removeAllViews();
		mapRow.clear();
		for (Contact c : l) {
			TableRow row = new TableRow(this);
			AddTableRow(c, tl, row);
		}*/
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
		/*TableRow row = mapRow.get(Integer.valueOf(uid));
		TableLayout tl = (TableLayout) findViewById(R.id.table);
		if (row == null) {
			row = new TableRow(this);
			AddTableRow(c, tl, row);
		} else {
			mapRow.remove(Integer.valueOf(uid));
			tl.removeView(row);
			row = new TableRow(this);
			AddTableRow(c, tl, row);
		}*/
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
		//mapRow.put(c.uid, row);
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
