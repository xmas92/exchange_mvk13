package com.example.app_mainproject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore;

public class AddEditContactActivity extends Activity {

	public static final int MEDIA_TYPE_IMAGE = 1;

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
				.format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		return mediaFile;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				// Image captured and saved to fileUri specified in the Intent
				// fileUri = data.getData(); not required as we pass the URI as
				// an Extra
				loadBitmap(true);
			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {
				// Image capture failed, advise user
			}
		} else if (requestCode == SELECT_PICTURE) {
			if (resultCode == RESULT_OK) {
				fileUri = data.getData();
				loadBitmap(false);
			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {
				// Image capture failed, advise user
			}
		}
	}

	private Bitmap contactPictureBitmap;

	private void loadBitmap(boolean delete) {
		try {
			ParcelFileDescriptor pFD = getContentResolver().openFileDescriptor(
					fileUri, "r");
			FileDescriptor fD = pFD.getFileDescriptor();
			contactPictureBitmap = BitmapFactory.decodeFileDescriptor(fD);
			pFD.close();
			int h = contactPictureBitmap.getHeight();
			int w = contactPictureBitmap.getWidth();
			double ratio = 3.0 / 4.0;
			if (ratio * w < h) {
				int y = (int) ((h - w * ratio) / 2.0);
				contactPictureBitmap = Bitmap.createBitmap(
						contactPictureBitmap, 0, y, w, h - 2 * y);
			} else if (ratio * w > h) {
				int x = (int) ((w - (h / ratio)) / 2.0);
				contactPictureBitmap = Bitmap.createBitmap(
						contactPictureBitmap, x, 0, w - 2 * x, h);
			}
			((ImageView) findViewById(R.id.contactpictureimageview))
					.setImageBitmap(contactPictureBitmap);
			if (delete)
				new File(fileUri.getPath()).delete();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_contact);
		handleExtras(getIntent().getExtras());
		getCurrentFocus().clearFocus();
		setupUI(findViewById(R.id.addeditcontactbackground));
		addListenerButtons();
	}

	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					InputMethodManager inputMethodManager = (InputMethodManager) self()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					inputMethodManager.hideSoftInputFromWindow(self()
							.getCurrentFocus().getWindowToken(), 0);
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				View innerView = ((ViewGroup) view).getChildAt(i);
				setupUI(innerView);
			}
		}
	}

	protected Activity self() {
		return this;
	}

	private int uid = -1;

	private void handleExtras(Bundle extras) {
		if (extras == null)
			return;
		uid = extras.getInt("ID", -1);
		if (uid != -1) {
			Contact c = DataStorage.GetContact(uid);
			if(c == null) {
				Log.w("Contact", "Not found");
				return;
			}
			((EditText) findViewById(R.id.contactnameET)).setText(c.getName());
			((EditText) findViewById(R.id.skypeidET)).setText(c.getContactId());
			((EditText) findViewById(R.id.positionET)).setText(Integer
					.toString(c.getPosition()));
			contactPictureBitmap = c.getImage();
			((ImageView) findViewById(R.id.contactpictureimageview))
					.setImageBitmap(contactPictureBitmap);
		}
	}

	private void addListenerButtons() {
		addListenerCameraCaptureButton();
		addListenerBrowseGalleryButton();
		addListenerAddUpdateButton();
		addListenerCancelButton();
	}

	private void addListenerCancelButton() {
		((Button) findViewById(R.id.cancelbutton))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (getParent() == null) {
							setResult(RESULT_CANCELED);
						} else {
							getParent().setResult(RESULT_CANCELED);
						}
						finish();
					}
				});
	}
	
	private final int STORE_DATA_DIALOG = 100;
	class StoreDataAsync extends AsyncTask<Object, Boolean, Intent>{
	    @SuppressWarnings("deprecation")
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        // DIALOG_DOWNLOAD_PROGRESS is defined as 0 at start of class
	        showDialog(STORE_DATA_DIALOG);
	    }

		@Override
		protected Intent doInBackground(Object... params) {
			Intent ret = new Intent();
			String contactName = ((EditText) findViewById(R.id.contactnameET))
					.getText().toString();
			String skypeID = ((EditText) findViewById(R.id.skypeidET)).getText()
					.toString();
			String positionStr = ((EditText) findViewById(R.id.positionET))
					.getText().toString();
			int position = positionStr.equals("") ? 0 : Integer
					.parseInt(positionStr);
			if (contactPictureBitmap == null || contactName.equals("")
					|| skypeID.equals(""))
				return null;
			if (uid == -1) {
				uid = DataStorage.CreateContact(contactName, skypeID,
						contactPictureBitmap, position);
			} else {
				DataStorage.UpdateContact(uid, contactName, skypeID,
						contactPictureBitmap, position);
			}
			publishProgress(true);
			ret.putExtra("ID", uid);
			return ret;
		}
		
		@Override
	    protected void onProgressUpdate(Boolean... progress) {
	    	if (progress[0] != null && progress[0]) 
	    		mProgressDialog.setProgress(1);
	    }

	    @SuppressWarnings("deprecation")
		@Override
	    protected void onPostExecute(Intent data) {
	        dismissDialog(STORE_DATA_DIALOG);
			if (data == null)
				return;
			if (getParent() == null) {
				setResult(RESULT_OK, data);
			} else {
				getParent().setResult(RESULT_OK, data);
			}
			finish();
	    }
		
	}
	ProgressDialog mProgressDialog;
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case STORE_DATA_DIALOG:
	        mProgressDialog = new ProgressDialog(this);
	        mProgressDialog.setMessage("Storing Data...");
	        mProgressDialog.setIndeterminate(false);
	        mProgressDialog.setMax(1);
	        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        mProgressDialog.setCancelable(false);
	        mProgressDialog.show();
	        return mProgressDialog;
	    default:
	        return null;
	    }
	}

	private void addListenerAddUpdateButton() {
		((Button) findViewById(R.id.addupdatebutton))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new StoreDataAsync().execute();
					}
				});
	}

	protected Intent returnData() {
		Intent ret = new Intent();
		String contactName = ((EditText) findViewById(R.id.contactnameET))
				.getText().toString();
		String skypeID = ((EditText) findViewById(R.id.skypeidET)).getText()
				.toString();
		String positionStr = ((EditText) findViewById(R.id.positionET))
				.getText().toString();
		int position = positionStr.equals("") ? 0 : Integer
				.parseInt(positionStr);
		if (contactPictureBitmap == null || contactName.equals("")
				|| skypeID.equals(""))
			return null;
		if (uid == -1) {
			uid = DataStorage.CreateContact(contactName, skypeID,
					contactPictureBitmap, position);
		} else {
			DataStorage.UpdateContact(uid, contactName, skypeID,
					contactPictureBitmap, position);
		}
		ret.putExtra("ID", uid);
		return ret;
	}

	private static final int SELECT_PICTURE = 1;

	private void addListenerBrowseGalleryButton() {
		((Button) findViewById(R.id.browsegallerybutton))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent();
						i.setType("image/*");
						i.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(
								Intent.createChooser(i, "Select Picture"),
								SELECT_PICTURE);
					}
				});
	}

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;

	private void addListenerCameraCaptureButton() {
		((Button) findViewById(R.id.cameracapturebutton))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
						i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
						startActivityForResult(i,
								CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
					}
				});
	}

}
