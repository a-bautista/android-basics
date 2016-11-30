package com.example.abautista.perfectsummer3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //The database must be deleted every time you start the application.
    //If the below error appears, just delete the database from the adv or rename it in this code.
    //android.database.sqlite.SQLiteException: no such table: CONSUMER_PRICES (code 1): , while compiling: select * from CONSUMER_PRICES


    private static final String DATABASE_NAME = "Destinations2.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TAG = "DatabaseHelper";


    // ====================================== DEFINE TABLES =======================================

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
    public static final String RAINFALL_TABLE = "RAINFALL";
    public static final String RAINFALL_AVG_COL_ONE = "CODE_CITY_MONTH";
    public static final String RAINFALL_AVG_COL_TWO = "ID_MONTH";
    public static final String RAINFALL_AVG_COL_THREE = "I_AVG_RAINFALL";

    //create table sea_temp
    public static final String SEA_TEMP_TABLE = "SEA_TEMP";
    public static final String SEA_TEMP_COL_ONE = "CODE_CITY_MONTH";
    public static final String SEA_TEMP_COL_TWO = "ID_MONTH";
    public static final String SEA_TEMP_COL_THREE = "I_AVG_SEA_TEMP";

    //create consumer prices table
    public static final String CONSUMER_PRICES_TABLE = "CONSUMER_PRICES";
    public static final String CONSUMER_PRICES_COL_ONE = "ID_CITY";
    public static final String CONSUMER_PRICES_COL_TWO = "I_VALUE";

    //create average standard of living
    public static final String AVERAGE_STANDARD_LIVING_TABLE = "AVERAGE_STANDARD_LIVING";
    public static final String AVERAGE_STANDARD_LIVING_COL_ONE = "ID_CITY";
    public static final String AVERAGE_STANDARD_LIVING_COL_TWO = "I_VALUE";

    //create sunshine table
    public static final String SUNSHINE_TABLE = "SUNSHINE";
    public static final String SUNSHINE_COL_ONE = "CODE_CITY_MONTH";
    public static final String SUNSHINE_COL_TWO = "ID_MONTH";
    public static final String SUNSHINE_COL_THREE = "I_AVG_SUNSHINE";

    //============================ CREATE TABLES QUERIES =========================================

    private static final String SQL_CREATE_TABLE_MONTHS ="CREATE TABLE "+ MONTHS_TABLE +"("
            + MONTHS_COL_ONE + " TEXT PRIMARY KEY, "
            + MONTHS_COL_TWO + " TEXT NOT NULL"
            + ");";

    private static final String SQL_CREATE_TABLE_MONTHS_CITIES ="CREATE TABLE "+ MONTHS_CITIES_TABLE +"("
            + MONTHS_CITIES_COL_ONE + " TEXT PRIMARY KEY, "
            + MONTHS_CITIES_COL_TWO + " TEXT NOT NULL,"
            + MONTHS_CITIES_COL_THREE + " TEXT NOT NULL"
            + ");";

    private static final String SQL_CREATE_TABLE_CITIES ="CREATE TABLE "+ CITIES_TABLE +"("
            + CITIES_COL_ONE + " TEXT PRIMARY KEY, "
            + CITIES_COL_TWO + " TEXT NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_WEATHER ="CREATE TABLE "+ WEATHER_TEMP_TABLE +"("
            + WEATHER_TEMP_TABLE_COL_ONE + " TEXT PRIMARY KEY, "
            + WEATHER_TEMP_TABLE_COL_TWO + " TEXT NOT NULL, "
            + WEATHER_TEMP_TABLE_COL_THREE + " INTEGER NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_SEA_TEMP ="CREATE TABLE "+ SEA_TEMP_TABLE +"("
            + SEA_TEMP_COL_ONE + " TEXT PRIMARY KEY, "
            + SEA_TEMP_COL_TWO + " TEXT NOT NULL, "
            + SEA_TEMP_COL_THREE + " INTEGER NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_RAINFALL ="CREATE TABLE "+ RAINFALL_TABLE +"("
            + RAINFALL_AVG_COL_ONE + " TEXT PRIMARY KEY, "
            + RAINFALL_AVG_COL_TWO + " TEXT NOT NULL, "
            + RAINFALL_AVG_COL_THREE + " INTEGER NOT NULL"
            + ");";



    private static final String SQL_CREATE_TABLE_SUNSHINE = "CREATE TABLE "+ SUNSHINE_TABLE + "("
            + SUNSHINE_COL_ONE + " TEXT PRIMARY KEY, "
            + SUNSHINE_COL_TWO + " TEXT NOT NULL, "
            + SUNSHINE_COL_THREE + " INTEGER NOT NULL"
            + ");";


    private static final String SQL_CREATE_TABLE_LIVING_STANDARDS = "CREATE TABLE "+ AVERAGE_STANDARD_LIVING_TABLE + "("
            + AVERAGE_STANDARD_LIVING_COL_ONE + " TEXT PRIMARY KEY, "
            + AVERAGE_STANDARD_LIVING_COL_TWO + " INTEGER NOT NULL "
            + ");";


    private static final String SQL_CREATE_TABLE_CONSUMER_PRICES ="CREATE TABLE "+ CONSUMER_PRICES_TABLE +"("
            + CONSUMER_PRICES_COL_ONE + " TEXT PRIMARY KEY, "
            + CONSUMER_PRICES_COL_TWO + " INTEGER NOT NULL"
            + ");";


    @Override
    public void onCreate(SQLiteDatabase db) {


        //===================================== EXECUTE QUERIES ===================================

        //db.execSQL("create table "+MONTHS_TABLE + "(ID INTEGER PRIMARY KEY, N_MONTHS_NAME TEXT ");
        db.execSQL(SQL_CREATE_TABLE_MONTHS);
        db.execSQL(SQL_CREATE_TABLE_SEA_TEMP);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        db.execSQL(SQL_CREATE_TABLE_RAINFALL);
        db.execSQL(SQL_CREATE_TABLE_CITIES);
        db.execSQL(SQL_CREATE_TABLE_MONTHS_CITIES);
        db.execSQL(SQL_CREATE_TABLE_SUNSHINE);
        db.execSQL(SQL_CREATE_TABLE_LIVING_STANDARDS);
        db.execSQL(SQL_CREATE_TABLE_CONSUMER_PRICES);


        //================================== INSERT VALUES QUERIES ================================
        ContentValues contentValuesMonths            = new ContentValues();
        ContentValues contentValuesCities            = new ContentValues();
        ContentValues contentValuesMonthsAndCities   = new ContentValues();
        ContentValues contentValuesWeather           = new ContentValues();
        ContentValues contentValuesLivingStds        = new ContentValues();
        ContentValues contentValuesConsumerPrices    = new ContentValues();
        ContentValues contentValuesRainfall          = new ContentValues();
        ContentValues contentValuesSeatemp           = new ContentValues();
        ContentValues contentValuesSunshine          = new ContentValues();

        //months data , "May", "June",
        //"July","August", "September","October", "November","December"

        ArrayList<String> months = new ArrayList<>(
                Arrays.asList("January", "February","March")); //March is not displayed

        ArrayList<String> monthCodes = new ArrayList<>(
                Arrays.asList("Jan", "Feb","Mar")); //March is not displayed

        ArrayList<String> cities = new ArrayList<>(
                Arrays.asList("Cyprus","Miami","Cancun"));

        ArrayList<String> cityCodes = new ArrayList<>(
                Arrays.asList("Cyp","Mia","Can"));

        ArrayList<String> monthCities = new ArrayList<>(
                Arrays.asList("CyprusJan","CyprusFeb","MiamiJan",
                        "MiamiFeb","CancunJan","CancunFeb"));

        ArrayList<Integer> pricesOfCities = new ArrayList<>(
                Arrays.asList(1, 0, 2));

        ArrayList<Integer> averageOfLiving = new ArrayList<>(
                Arrays.asList(5, 5, 4));

        ArrayList<Integer> averageWeatherValues= new ArrayList<>(
                Arrays.asList(17,18,20,24,24,25));

        ArrayList<Integer> seaAverageValues= new ArrayList<>(
                Arrays.asList(16,17,17,22,22,23));

        ArrayList<Integer> rainfallAverageValues= new ArrayList<>(
                Arrays.asList(5,4,3,4,4,4));

        ArrayList<Integer> sunshineAverageValues= new ArrayList<>(
                Arrays.asList(2,3,3,3,3,4));


        //Insert months
        for (int i = 0; i<months.size(); i++){
            contentValuesMonths.put(MONTHS_COL_ONE,monthCodes.get(i));
            contentValuesMonths.put(MONTHS_COL_TWO, months.get(i));
            db.insert(MONTHS_TABLE,null,contentValuesMonths);
        }

        //Insert cities
        for (int i = 0; i<cities.size(); i++){
            contentValuesCities.put(CITIES_COL_ONE,cityCodes.get(i));
            contentValuesCities.put(CITIES_COL_TWO, cities.get(i));
            db.insert(CITIES_TABLE,null,contentValuesCities);
        }

        //Insert months and cities
        for (int i= 0; i<monthCities.size(); i++){
            contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_ONE, monthCities.get(i));
            if (i == 0){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO, cityCodes.get(0));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(0));
            }else if(i==1){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cityCodes.get(0));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(1));
            }else if(i==2){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cityCodes.get(1));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(0));
            }else if(i==3){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cityCodes.get(1));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(1));
            }else if(i==4){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cityCodes.get(2));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(0));
            }else if(i==5){
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_TWO,cityCodes.get(2));
                contentValuesMonthsAndCities.put(MONTHS_CITIES_COL_THREE, monthCodes.get(1));
            }
            db.insert(MONTHS_CITIES_TABLE,null,contentValuesMonthsAndCities);
        }

        //insert consumer prices
        for (int i = 0; i<pricesOfCities.size(); i++){
            contentValuesConsumerPrices.put(CONSUMER_PRICES_COL_ONE,cityCodes.get(i));
            contentValuesConsumerPrices.put(CONSUMER_PRICES_COL_TWO,pricesOfCities.get(i));
            db.insert(CONSUMER_PRICES_TABLE,null,contentValuesConsumerPrices);
        }

        //insert average of living
        for (int i = 0; i<averageOfLiving.size(); i++){
            contentValuesLivingStds.put(AVERAGE_STANDARD_LIVING_COL_ONE,cityCodes.get(i));
            contentValuesLivingStds.put(AVERAGE_STANDARD_LIVING_COL_TWO,averageOfLiving.get(i));
            db.insert(AVERAGE_STANDARD_LIVING_TABLE,null,contentValuesLivingStds);
        }

        //insert the weather values
        for (int i=0; i<averageWeatherValues.size();i++) {
            contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_ONE, monthCities.get(i));
            contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_THREE, averageWeatherValues.get(i));
            if(i==0) {
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(0));
            }else if (i==1){
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(1));
            } else if(i==2){
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(0));
            }else if(i==3){
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(1));
            }else if(i==4){
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(0));
            }else if(i==5) {
                contentValuesWeather.put(WEATHER_TEMP_TABLE_COL_TWO, monthCodes.get(1));
            }
            db.insert(WEATHER_TEMP_TABLE, null, contentValuesWeather);
        }

        //insert the rainfall values not working
        for (int i=0; i<rainfallAverageValues.size();i++) {
            contentValuesRainfall.put(RAINFALL_AVG_COL_ONE, monthCities.get(i));
            contentValuesRainfall.put(RAINFALL_AVG_COL_THREE, rainfallAverageValues.get(i));
            if(i==0) {
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if (i==1){
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            } else if(i==2){
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==3){
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }else if(i==4){
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==5) {
                contentValuesRainfall.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }
            db.insert(RAINFALL_TABLE, null, contentValuesRainfall);
        }


        //insert the sea temp values seaAverageValues
        for (int i=0; i<seaAverageValues.size();i++) {
            contentValuesSeatemp.put(SEA_TEMP_COL_ONE, monthCities.get(i));
            contentValuesSeatemp.put(SEA_TEMP_COL_THREE, seaAverageValues.get(i));
            if(i==0) {
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if (i==1){
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            } else if(i==2){
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==3){
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }else if(i==4){
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==5) {
                contentValuesSeatemp.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }
            db.insert(SEA_TEMP_TABLE, null, contentValuesSeatemp);
        }


        //insert the sunshine values
        for (int i=0; i<sunshineAverageValues.size();i++) {
            contentValuesSunshine.put(SUNSHINE_COL_ONE, monthCities.get(i));
            contentValuesSunshine.put(SUNSHINE_COL_THREE, sunshineAverageValues.get(i));
            if(i==0) {
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if (i==1){
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            } else if(i==2){
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==3){
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }else if(i==4){
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(0));
            }else if(i==5) {
                contentValuesSunshine.put(SEA_TEMP_COL_TWO, monthCodes.get(1));
            }
            db.insert(SUNSHINE_TABLE, null, contentValuesSunshine);
        }

        //Queries for retrieval

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
        db.execSQL("DROP TABLE IF EXISTS " + CONSUMER_PRICES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SUNSHINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AVERAGE_STANDARD_LIVING_TABLE);

        //recreate the tables
        onCreate(db);
    }

    public Cursor showResultsMonthsAndCities(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+MONTHS_CITIES_TABLE, null);
        return results;
    }

    public Cursor showResultsMonths(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+MONTHS_TABLE, null);
        return results;
    }

    public Cursor showResultsCities(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CITIES_TABLE, null);
        return results;
    }

    public Cursor showResultsConsumerPrices(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CONSUMER_PRICES_TABLE, null);
        return results;
    }

    public Cursor showResultsAvgStdOfLiving(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+AVERAGE_STANDARD_LIVING_TABLE, null);
        return results;
    }

    public Cursor showResultsWeather(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+WEATHER_TEMP_TABLE, null);
        return results;
    }


    //This is not working
    public Cursor showResultsRainfall(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+RAINFALL_TABLE, null);
        return results;
    }

    //This is not working
    public Cursor showResultsSunshine(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from SUNSHINE", null);
        return results;
    }

    public Cursor showResultsSeaWeather(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+SEA_TEMP_TABLE, null);
        return results;

    }


    //------------------- QUERIES FOR SELECTING THE VALUES OF EACH MONTH ------------------------//
    public Cursor showResultsWeatherJanuary(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select N_CITY, I_AVG_WEATHER from WEATHER_TABLE, MONTHS_CITIES, CITIES where WEATHER_TABLE.ID_MONTH='Jan' " +
                "and MONTHS_CITIES.ID_MONTH=WEATHER_TABLE.ID_MONTH AND CITIES.ID_CITY=MONTHS_CITIES.ID_CITY and " +
                "MONTHS_CITIES.CODE_CITY_MONTH=WEATHER_TABLE.CODE_CITY_MONTH",null);

        return results;
    }

    public Cursor showResultsSeaWeatherJanuary(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select N_CITY, I_AVG_SEA_TEMP from SEA_TEMP, MONTHS_CITIES, CITIES where SEA_TEMP.ID_MONTH='Jan' " +
                "and MONTHS_CITIES.ID_MONTH=SEA_TEMP.ID_MONTH AND CITIES.ID_CITY=MONTHS_CITIES.ID_CITY and " +
                "MONTHS_CITIES.CODE_CITY_MONTH=SEA_TEMP.CODE_CITY_MONTH",null);

        return results;

    }

    //----------------- End of queries for slecting the values for each month --------------------//

    public Cursor showResultsPrices(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select N_CITY, I_VALUE from CONSUMER_PRICES, CITIES where CONSUMER_PRICES.ID_CITY=CITIES.ID_CITY",null);
        return results;
    }

    public Cursor showAverageOfStdLiving(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select N_CITY, I_VALUE from AVERAGE_STANDARD_LIVING, CITIES where AVERAGE_STANDARD_LIVING.ID_CITY=CITIES.ID_CITY",null);
        return results;
    }

    //CORREGIR NO SUCH TABLE RAINFALL
    public Cursor showResultsRainfallJanuary(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select N_CITY, I_AVG_RAINFALL from RAINFALL, MONTHS_CITIES, CITIES where RAINFALL.ID_MONTH='Jan' " +
                "and MONTHS_CITIES.ID_MONTH=RAINFALL.ID_MONTH AND CITIES.ID_CITY=MONTHS_CITIES.ID_CITY and " +
                "MONTHS_CITIES.CODE_CITY_MONTH=RAINFALL.CODE_CITY_MONTH",null);

        return results;

    }

}
