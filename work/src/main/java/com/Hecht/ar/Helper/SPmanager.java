package com.Hecht.ar.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SPmanager {

    public static void setFirstName(Context context, String firstName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.FIRST_NAME_KEY,firstName).apply();
    }

    public static String getFirstName(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.FIRST_NAME_KEY,PreferenceConstants.FIRST_NAME_DEFAULT_VALUE);
    }


    public static void setBio(Context context, String bio){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.BIO_KEY,bio).apply();
    }

    public static String getBio(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.BIO_KEY,PreferenceConstants.BIO_DEFAULT_VALUE);
    }

    public static void setDesignation(Context context, String bio){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.DESIGNATION_KEY,bio).apply();
    }

    public static String getDesignation(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.DESIGNATION_KEY,PreferenceConstants.DESIGNATION_DEFAULT_VALUE);
    }

    public static void setLastName(Context context, String lastName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.LAST_NAME_KEY,lastName).apply();
    }

    public static String getLastName(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.LAST_NAME_KEY,PreferenceConstants.LAST_NAME_DEFAULT_VALUE);
    }


    public static void setEmail(Context context, String email){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.EMAIL_KEY,email).apply();
    }

    public static String getEmail(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.EMAIL_KEY,PreferenceConstants.EMAIL_DEFAULT_VALUE);
    }

    public static boolean getBiometricValue(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPreferences.getBoolean(PreferenceConstants.BIOMETRIC_KEY,PreferenceConstants.BIOMETRIC_KEY_DEFAULT_VALUE);
    }

    public static void setBiometric(Context context, boolean enable){
        SharedPreferences sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PreferenceConstants.BIOMETRIC_KEY,enable).apply();
    }

    public static void setPassword(Context context, String password){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.PASSWORD_KEY,password).apply();
    }

    public static String getPassword(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.PASSWORD_KEY,PreferenceConstants.PASSWORD_DEFAULT_VALUE);
    }

    public static boolean isBiometricActivated(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PreferenceConstants.BIOMETRIC_ACTIVATE_KEY,PreferenceConstants.BIOMETRIC_ACTIVATE_DEFAULT_VALUE);
    }

    public static void activateBiometric(Context context, boolean enable){
        SharedPreferences sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PreferenceConstants.BIOMETRIC_ACTIVATE_KEY,enable).apply();
    }

    public static boolean isGmailLogin(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PreferenceConstants.GMAIL_ACTIVATE_KEY,PreferenceConstants.GMAIL_ACTIVATE_DEFAULT_VALUE);
    }

    public static void activateGmailLogin(Context context, boolean enable){
        SharedPreferences sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PreferenceConstants.GMAIL_ACTIVATE_KEY,enable).apply();
    }

    public static boolean isTruckModelPlaced(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PreferenceConstants.TRUCK_MODEL_KEY,PreferenceConstants.TRUCK_MODEL_DEFAULT_VALUE);
    }

    public static void setTruckModel(Context context, boolean enable){
        SharedPreferences sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PreferenceConstants.TRUCK_MODEL_KEY,enable).apply();
    }

    public static boolean isScaned(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PreferenceConstants.IS_SCANNED_MODEL_KEY,PreferenceConstants.IS_SCANNED_DEFAULT_VALUE);
    }

    public static void setScanned(Context context, boolean enable){
        SharedPreferences sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PreferenceConstants.IS_SCANNED_MODEL_KEY,enable).apply();
    }

    public static void saveAuthID(Context context, String password){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.AUTH_ID_KEY,password).apply();
    }

    public static String getAuthID(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.AUTH_ID_KEY,PreferenceConstants.AUTH_ID_KEY_DEFAULT_VALUE);
    }


    public static void saveEnvironmentID(Context context, String environemnetID){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.ENVIRONMENT_ID_KEY,environemnetID).apply();
    }

    public static String getEnvironmentID(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.ENVIRONMENT_ID_KEY,PreferenceConstants.ENVIRONMENT_ID_KEY_DEFAULT_VALUE);
    }

    public static void saveTagID(Context context, String tagID){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(PreferenceConstants.TAG_ID_KEY,tagID).apply();
    }

    public static String getTagID(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PreferenceConstants.TAG_ID_KEY,PreferenceConstants.TAG_ID_KEY_DEFAULT_VALUE);
    }
}
