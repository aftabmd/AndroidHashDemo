package com.example;

import java.io.IOException;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView result;
	private Button computeSha,computeMD5;
	private EditText userName, passWord;
	private String username,passwd;
	private String SHAHash;
	public static int NO_OPTIONS=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		userName=(EditText)findViewById(R.id.userName);
		passWord=(EditText)findViewById(R.id.passWord);
		
		computeSha=(Button)findViewById(R.id.btn1);
		computeMD5=(Button)findViewById(R.id.btn2);
		
		result= (TextView)findViewById(R.id.textView2);
		
		//get username and password entered
		username= userName.getText().toString();
		passwd= passWord.getText().toString();	
	
		//check if username or passwd is not null
		
		if( (username != null && username.equals("") ) || (passwd !=null && passwd.equals("")) )
		{
			
			
			computeSha.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					//call method to compute SHA hash
					computeSHAHash(passwd);
				}
			});
			
			computeMD5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					//call method to compute SHA hash
					computeMD5Hash(passwd);
				}
			});
			
			
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Enter your credentials..", Toast.LENGTH_LONG).show();
		}
			
		
	
	} //end onCreate()

	
	
	 private static String convertToHex(byte[] data) throws java.io.IOException 
	 {
	        
	        
	        StringBuffer sb = new StringBuffer();
	        String hex=null;
	        
	        hex=Base64.encodeToString(data, 0, data.length, NO_OPTIONS);
	        
	        sb.append(hex);
	        	        
	        return sb.toString();
	    }
	
	
	public void computeSHAHash(String password)
	  {
		  MessageDigest mdSha1 = null;
	        try 
	        {
	          mdSha1 = MessageDigest.getInstance("SHA-1");
	        } catch (NoSuchAlgorithmException e1) {
	          Log.e("myapp", "Error initializing SHA1 message digest");
	        }
	        try {
				mdSha1.update(password.getBytes("ASCII"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte[] data = mdSha1.digest();
	        try {
				SHAHash=convertToHex(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			result.setText("SHA-1 hash generated is: " + " " + SHAHash);
	    }
	
	
	public void computeMD5Hash(String password)
	{

		try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(password.getBytes());
	        byte messageDigest[] = digest.digest();
	 
	        StringBuffer MD5Hash = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++)
	        {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            MD5Hash.append(h);
	        }
	             
	     	result.setText("MD5 hash generated is: " + " " + MD5Hash);
			
	    	} 
			catch (NoSuchAlgorithmException e) 
	    	{
	        e.printStackTrace();
	    	}
	   
	   	
	}
	

}
