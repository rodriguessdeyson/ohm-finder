package com.rad.database.ad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.rad.database.DBContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdRepository extends DBContext
{
    /**
     * Initialize an objective of type AdRepository.
     * @param ctx Activity context.
     */
    public AdRepository(Context ctx)
    {
        super(ctx);
    }

    /**
     * Inserts the dateTime that an ad was showed.
     * @param dateTime Time in text of showing.
     */
   public void insert(String dateTime)
    {
        Open();
        sqliteDatabase.execSQL(
                "INSERT INTO Ad(" +
                        "ShowedTime)"    +
                        "VALUES("           +
                        "'"+ dateTime +"')" + ";");
        Close();
    }

    /**
     * Updates the dateTime that an ad was showed.
     */
    public void update()
    {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = new Date();
        Open();
        sqliteDatabase.execSQL(
                "UPDATE Ad SET " +
                        "ShowedTime =" + "'" + dateFormat.format(date) + "'" +
                        "WHERE Id =" + "'" + 1 + "'" + ";");
        Close();
    }

    /**
     * Gets the last time an ad was shown
     * @return THe DateTime of shown;
     */
    public Date getTimeLastAdShowed()
    {
        Date lastShowed = null;
        String selectQuery = "SELECT * FROM Ad;";

        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);

        // If rows exist, get the values.
        if (dbCursor.getCount() > 0)
        {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            while (dbCursor.moveToNext())
            {
                try
                {
                    lastShowed = format.parse(dbCursor.getString(1));
                }
                catch (ParseException e)
                {
                    lastShowed = null;
                }
            }
        }
        dbCursor.close();
        Close();
        return lastShowed;
    }
}
