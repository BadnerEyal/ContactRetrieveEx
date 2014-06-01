package com.example.contactretrieveex;


import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ListActivity 
{
	private final static String TAG = MainActivity.class.getSimpleName();//  <==> "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Cursor cursor = getContacts();
        
        CursorAdapter adapter = new CursorAdapter(this, cursor, true)
        {

			@Override
			public void bindView(View view, Context arg1, Cursor cursor) 
			{
				TextView textView = (TextView)view;
				String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				textView.setText(displayName);
			}

			@Override
			public View newView(Context arg0, Cursor arg1, ViewGroup arg2) 
			{
				return new TextView(MainActivity.this);
			}
        	
        };
        
        setListAdapter(adapter);
    }

    @SuppressWarnings("deprecation")
	private Cursor getContacts() 
    {
        // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        
        Log.d(TAG, uri.toString());
        
        String[] projection = new String[] { ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME };
        
//        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
//            + ("1") + "'";
//        
        
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
            + " COLLATE LOCALIZED ASC";

        //return getContentResolver().query(uri, projection, null, null, sortOrder);
        
        return managedQuery(uri, projection, null, null,
            sortOrder);
      }
}
