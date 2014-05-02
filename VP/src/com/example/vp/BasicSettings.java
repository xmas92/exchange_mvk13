package com.example.vp;


import android.app.Activity;
import android.content.SharedPreferences;

public class BasicSettings extends Activity {
    private static final String PREFS_NAME = "VideoPhoneSettings";
    private SharedPreferences settings;
    
    private boolean booleanSetting1;
    private boolean booleanSetting2;
    private int integerSetting;
    private String stringSetting;
    
    
    public BasicSettings() {
    	settings = getSharedPreferences(PREFS_NAME, 0);
    	booleanSetting1 = settings.getBoolean("bool1", true); // format: ("varibelnamn", standardvärde)
    	booleanSetting2 = settings.getBoolean("bool2", false);
    	integerSetting = settings.getInt("int", 0);
    	stringSetting = settings.getString("str", "");
    }
    
    
    // Commit any changes. Nothing is saved until this is run.
    public void commit() {
        SharedPreferences.Editor editor = settings.edit();
        
        editor.putBoolean("bool1", booleanSetting1);
        editor.putBoolean("bool1", booleanSetting2);
        editor.putInt("int", integerSetting);
        editor.putString("str", stringSetting);

        editor.commit();
    }


    // Getters and setters
    
	public boolean isBooleanSetting1() {
		return booleanSetting1;
	}


	public void setBooleanSetting1(boolean booleanSetting1) {
		this.booleanSetting1 = booleanSetting1;
	}


	public boolean isBooleanSetting2() {
		return booleanSetting2;
	}


	public void setBooleanSetting2(boolean booleanSetting2) {
		this.booleanSetting2 = booleanSetting2;
	}


	public int getIntegerSetting() {
		return integerSetting;
	}


	public void setIntegerSetting(int integerSetting) {
		this.integerSetting = integerSetting;
	}


	public String getStringSetting() {
		return stringSetting;
	}


	public void setStringSetting(String stringSetting) {
		this.stringSetting = stringSetting;
	}
    
 
    

}
