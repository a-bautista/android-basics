package com.example.abautista.perfectsummer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    /*public DatabaseHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }*/

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //The database must be deleted every time you start the application.
    private static final String DATABASE_NAME = "Destinations.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TAG = "DatabaseHelper";

    //create table months
    public static final String MONTHS_TABLE = "MONTHS";
    public static final String MONTHS_COL_ONE = "ID_MONTH";
    public static final String MONTHS_COL_TWO = "N_MONTH_NAME";

    //create table months_cities
    public static final String MONTHS_CITIES_TABLE = "MONTHS_CITIES";
    public static final String MONTHS_CITIES_COL_ONE = "CODE_CITY_MONTH";
    public static final String MONTHS_CITIES_COL_TWO = "ID_CITY";
    public static final String MONTHS_CITIES_COL_THREE = "ID_MONTH";

    //create table cities
    public static final String CITIES_TABLE = "CITIES";
    public static final String CITIES_COL_ONE = "ID_CITY";
    public static final String CITIES_COL_TWO = "N_CITY";

    //create table weather_temp
    public static final String WEATHER_TEMP_TABLE = "WEATHER_TABLE";
    public static final String WEATHER_TEMP_TABLE_COL_ONE  = "CODE_CITY_MONTH";
    public static final String WEATHER_TEMP_TABLE_COL_TWO  = "ID_MONTH";
    public static final String WEATHER_TEMP_TABLE_COL_THREE  = "I_AVG_WEATHER";

    //create table rainfall_avg
    public static final String RAINFALL_TABLE = "RAINFALL_AVG";
    public static final String RAINFALL_AVG_COL_ONE = "CODE_CITY_MONTH";
    public static final String RAINFALL_AVG_COL_TWO = "ID_MONTH";
    public static final String RAINFALL_AVG_COL_THREE = "I_AVG_RAINFALL";

    //create table sea_temp
    public static final String SEA_TEMP_TABLE = "SEA_TEMP";
    public static final String SEA_TEMP_COL_ONE = "CODE_CITY_MONTH";
    public static final String SEA_TEMP_COL_TWO = "ID_MONTH";
    public static final String SEA_TEMP_COL_THREE = "I_AVG_SEA_TEMP";


    //queries for creating Tables
    private static final String SQL_CREATE_TABLE_MONTHS ="CREATE TABLE "+ MONTHS_TABLE +"("
            + MONTHS_COL_ONE + " INTEGER PRIMARY KEY, "
            + MONTHS_COL_TWO + " TEXT NOT NULL"
            + ");";

    private static final String SQL_CREATE_TABLE_MONTHS_CITIES ="CREATE TABLE "+ MONTHS_CITIES_TABLE +"("
            + MONTHS_CITIES_COL_ONE + " TEXT PRIMARY KEY, "
            + MONTHS_CITIES_COL_TWO + " TEXT NOT NULL,"
            + MONTHS_CITIES_COL_THREE + " TEXT NOT NULL"
            + ");";

    private static final String SQL_CREATE_TABLE_CITIES ="CREATE TABLE "+ CITIES_TABLE +"("
            + CITIES_COL_ONE + " INTEGER PRIMARY KEY, "
            + CITIES_COL_TWO + " TEXT NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_WEATHER ="CREATE TABLE "+ WEATHER_TEMP_TABLE +"("
            + WEATHER_TEMP_TABLE_COL_ONE + " TEXT PRIMARY KEY, "
            + WEATHER_TEMP_TABLE_COL_TWO + " TEXT NOT NULL, "
            + WEATHER_TEMP_TABLE_COL_THREE + " INTEGER NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_SEA_TEMP ="CREATE TABLE "+ SEA_TEMP_TABLE +"("
            + SEA_TEMP_COL_ONE + " TEXT PRIMARY KEY, "
            + SEA_TEMP_COL_TWO + " INTEGER NOT NULL, "
            + SEA_TEMP_COL_THREE + " REAL NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_RAINFALL ="CREATE TABLE "+ RAINFALL_TABLE +"("
            + RAINFALL_AVG_COL_ONE + " TEXT PRIMARY KEY, "
            + RAINFALL_AVG_COL_TWO + " INTEGER, "
            + RAINFALL_AVG_COL_THREE + " REAL NOT NULL"
            + ");";

    //create the databases
    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table "+MONTHS_TABLE + "(ID INTEGER PRIMARY KEY, N_MONTHS_NAME TEXT ");
        db.execSQL(SQL_CREATE_TABLE_MONTHS);
        db.execSQL(SQL_CREATE_TABLE_SEA_TEMP);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        db.execSQL(SQL_CREATE_TABLE_RAINFALL);
        db.execSQL(SQL_CREATE_TABLE_CITIES);
        db.execSQL(SQL_CREATE_TABLE_MONTHS_CITIES);

        ContentValues contentValuesMonths            = new ContentValues();
        ContentValues contentValuesCities            = new ContentValues();
        ContentValues contentValuesMonthsAndCities   = new ContentValues();
        ContentValues contentValuesWeather           = new ContentValues();
        ContentValues contentValuesRainfall          = new ContentValues();
        ContentValues contentValuesSeatemp           = new ContentValues();

        //months data , "May", "June",
        //"July","August", "September","October", "November","December"

        ArrayList<String> months = new ArrayList<>(
                Arrays.asList("January", "February","March"));

        ArrayList<String> cities = new ArrayList<>(
                Arrays.asList("Cyprus","Miami","Cancun"));

        ArrayList<String> monthCities = new ArrayList<>(
                Arrays.asList("CyprusJan","CyprusFeb","MiamiJan",
                        "MiamiFeb","CancunJan","CancunFeb"));

        ArrayList<Integer> averageWeatherValues= new ArrayList<>(
                Arrays.asList(17,18,20,24,24,25,28,29,30));

        ArrayList<Integer> seaAverageValues= new ArrayList<>(
                Arrays.asList(16,17,17,22,22,23,25,25,25));

        ArrayList<Integer> rainfallAverageValues= new ArrayList<>(
                Arrays.asList(5,4,3,4,4,4,5,3,4));

        ArrayList<Integer> sunshineAverageValues= new ArrayList<>(
                Arrays.asList(2,3,3,3,3,4,4,4,4));


        //Insert months
        for (int i = 0; i<months.size(); i++){
            contentValuesMonths.put(MONTHS_COL_ONE,i);
            contentValuesMonths.put(MONTHS_COL_TWO, months.get(i));
            db.insert(MONTHS_TABLE,null,contentValuesMonths);
        }

        //Insert cities
        for (int i = 0; i<cities.size(); i++){
            contentValuesCities.put(CITIES_COL_ONE,i);
            contentValuesCities.put(CITIES_COL_TWO,cities.get(i));
            System.out.println(cities.get(i).toString());
            db.insert(CITIES_TABLE,null,contentValuesCities);
        }

        //Insert months and cities
        for (int i= 0; i<monthCities.size(); i++){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_ONE, monthCities.get(i));
            if (i == 0){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO, cities.get(0));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(0));
            }else if(i==1){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cities.get(0));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(1));
            }else if(i==2){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cities.get(1));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(0));
            }else if(i==3){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cities.get(1));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(1));
            }else if(i==4){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cities.get(2));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(0));
            }else if(i==5){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cities.get(2));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, months.get(1));
            }
            db.insert(MONTHS_CITIES_TABLE,null,contentValuesMonthsAndCities);
        }

        //insert the weather values
        for (int i=0; i<averageWeatherValues.size();i++){
            contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_ONE,monthCities.get(i));
        }


        //insert the rainfall values



        //insert the sea temp values


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version "+oldVersion + " to " + newVersion);
        //clear all data
        db.execSQL("DROP TABLE IF EXISTS " + MONTHS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SEA_TEMP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WEATHER_TEMP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RAINFALL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CITIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MONTHS_CITIES_TABLE);

        //recreate the tables
        onCreate(db);
    }

    public Cursor showResults(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+MONTHS_CITIES_TABLE, null);
        return results;
    }
}
