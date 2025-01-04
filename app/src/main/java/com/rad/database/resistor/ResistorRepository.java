package com.rad.database.resistor;

import static java.text.DateFormat.getDateTimeInstance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

import com.rad.database.DBContext;
import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResistorRepository extends DBContext implements Serializable
{
    //region Constructor

    /**
     * Initialize an objective of type ResistorHistoryRepository.
     * @param ctx Activity context.
     */
    public ResistorRepository(Context ctx)
    {
        super(ctx);
    }

    //endregion

    //#region Methods

    /**
     * Inserts a new resistor into the database.
     * @param resistor The resistor configuration to insert.
     */
    public void insert(Resistor resistor)
    {
        Open();
        String sql = "INSERT INTO ResistorHistory" +
                "(Type, FirstBand, SecondBand, ThirdBand, MultiplierBand, ToleranceBand, PPMBand, Value, ToleranceValue, PPMValue, DateTime)" +
                "VALUES (:type, :firstBand, :secondBand, :thirdBand, :multiplierBand, :toleranceBand, :ppmBand, :value, :toleranceValue, :ppmValue, :dateTime);";

        SQLiteStatement statement = sqliteDatabase.compileStatement(sql);
        statement.bindLong(1, resistor.getType().ordinal());
        statement.bindLong(2, resistor.firstBand);
        statement.bindLong(3, resistor.secondBand);

        if (resistor.thirdBand == null) statement.bindNull(4);
        else statement.bindLong(4, resistor.thirdBand);

        statement.bindLong(5, resistor.multiplierBand);
        statement.bindLong(6, resistor.toleranceBand);

        if (resistor.ppmBand == null) statement.bindNull(7);
        else statement.bindLong(7, resistor.ppmBand);

        statement.bindDouble(8, resistor.getValue());
        statement.bindDouble(9, resistor.getToleranceValue());

        if (resistor.ppmBand == null) statement.bindNull(10);
        else statement.bindDouble(10, resistor.getPpmValue());

        DateFormat dateFormat = getDateTimeInstance();
        Date date = new Date();
        statement.bindString(11, dateFormat.format(date));
        statement.executeInsert();
        Close();
    }

    /**
     * Selects a resistor from the database.
     * @param id Resistor id.
     * @return The resistor configuration.
     */
    public Resistor select(int id)
    {
        String selectQuery = "SELECT " +
                             "Id," +
                             "Type," +
                             "FirstBand," +
                             "SecondBand," +
                             "ThirdBand," +
                             "MultiplierBand," +
                             "ToleranceBand," +
                             "PPMBand," +
                             "Value," +
                             "ToleranceValue," +
                             "PPMValue," +
                             "DateTime FROM ResistorHistory WHERE Id = " + id + ";";
        
        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);
        
        Resistor resistor = null;
        if (dbCursor.getCount() > 0)
        {
            while (dbCursor.moveToNext())
            {
                int idResistor            = dbCursor.getInt(0);
                ResistorType resistorType = ResistorType.values()[dbCursor.getInt(1)];
                int firstBandColor        = dbCursor.getInt(2);
                int secondBandColor       = dbCursor.getInt(3);
                int multiplierBandColor   = dbCursor.getInt(5);
                int toleranceBandColor    = dbCursor.getInt(6);
                double value              = dbCursor.getDouble(8);
                double toleranceValue     = dbCursor.getDouble(9);
                String dateTime           = dbCursor.getString(11);
                
                Integer thirdBandColor = null;
                if (!dbCursor.isNull(4))
                    thirdBandColor = dbCursor.getInt(4);
                
                Integer ppmBandColor = null;
                Double ppmValue = null;
                if (!dbCursor.isNull(7))
                {
                    ppmBandColor = dbCursor.getInt(7);
                    ppmValue = dbCursor.getDouble(10);
                }
                
                resistor = new Resistor(
                    idResistor,
                    resistorType,
                    firstBandColor,
                    secondBandColor,
                    thirdBandColor,
                    multiplierBandColor,
                    toleranceBandColor,
                    ppmBandColor,
                    value,
                    toleranceValue,
                    ppmValue,
                    dateTime
                );
            }
            dbCursor.close();
            Close();
        }
        return resistor;
    }

    /**
     * Selects all resistor from the database.
     * @param type The resistor type.
     * @return The resistor configuration.
    */
    public ArrayList<Resistor> selectAll(ResistorType type)
    {
        String selectQuery = buildSelectQuery(type, null, null) + ";";

        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);

        ArrayList<Resistor> resistorHistory = new ArrayList<>();
        if (dbCursor.getCount() > 0)
        {
            while (dbCursor.moveToNext())
            {
                int id                    = dbCursor.getInt(0);
                ResistorType resistorType = ResistorType.values()[dbCursor.getInt(1)];
                int firstBandColor        = dbCursor.getInt(2);
                int secondBandColor       = dbCursor.getInt(3);
                int multiplierBandColor   = dbCursor.getInt(5);
                int toleranceBandColor    = dbCursor.getInt(6);
                double value              = dbCursor.getDouble(8);
                double toleranceValue     = dbCursor.getDouble(9);
                String dateTime           = dbCursor.getString(11);

                Integer thirdBandColor = null;
                if (!dbCursor.isNull(4))
                    thirdBandColor = dbCursor.getInt(4);

                Integer ppmBandColor = null;
                Double ppmValue = null;
                if (!dbCursor.isNull(7))
                {
                    ppmBandColor = dbCursor.getInt(7);
                    ppmValue = dbCursor.getDouble(10);
                }

                Resistor resistor = new Resistor(
                        id,
                        resistorType,
                        firstBandColor,
                        secondBandColor,
                        thirdBandColor,
                        multiplierBandColor,
                        toleranceBandColor,
                        ppmBandColor,
                        value,
                        toleranceValue,
                        ppmValue,
                        dateTime
                );
                resistorHistory.add(resistor);
            }
        }
        dbCursor.close();
        Close();
        return resistorHistory;
    }

    /**
     * Selects all resistor from the database.
     * @param type The resistor type.
     * @param lastId The last resistor id.
     * @param count The number of items to retrieve.
     * @return The resistor configuration.
     */
    public ArrayList<Resistor> selectPaginated(ResistorType type, int lastId, int count)
    {
        String selectQuery = buildSelectQuery(type, lastId, count) + ";";;

        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);

        ArrayList<Resistor> resistorHistory = new ArrayList<>();
        if (dbCursor.getCount() > 0)
        {
            while (dbCursor.moveToNext())
            {
                int id                    = dbCursor.getInt(0);
                ResistorType resistorType = ResistorType.values()[dbCursor.getInt(1)];
                int firstBandColor        = dbCursor.getInt(2);
                int secondBandColor       = dbCursor.getInt(3);
                Integer thirdBandColor    = dbCursor.getInt(4);
                int multiplierBandColor   = dbCursor.getInt(5);
                int toleranceBandColor    = dbCursor.getInt(6);
                int ppmBandColor          = dbCursor.getInt(7);
                double value              = dbCursor.getDouble(8);
                double toleranceValue     = dbCursor.getDouble(9);
                Double ppmValue           = dbCursor.getDouble(10);
                String dateTime           = dbCursor.getString(11);

                Resistor resistor = new Resistor(
                        id,
                        resistorType,
                        firstBandColor,
                        secondBandColor,
                        thirdBandColor,
                        multiplierBandColor,
                        toleranceBandColor,
                        ppmBandColor,
                        value,
                        toleranceValue,
                        ppmValue,
                        dateTime
                );
                resistorHistory.add(resistor);
            }
        }
        dbCursor.close();
        Close();
        return resistorHistory;
    }

    /**
     * Builds the select query.
     * @param type The resistor type.
     * @param lastId The last resistor id.
     * @param count The number of items to retrieve.
     * @return The select query.
     */
    private static @NonNull String buildSelectQuery(ResistorType type, Integer lastId, Integer count)
    {
        String selectQuery;
        if (type == ResistorType.NONE)
        {
            selectQuery = "SELECT " +
                    "Id," +
                    "Type," +
                    "FirstBand," +
                    "SecondBand," +
                    "ThirdBand," +
                    "MultiplierBand," +
                    "ToleranceBand," +
                    "PPMBand," +
                    "Value," +
                    "ToleranceValue," +
                    "PPMValue," +
                    "DateTime FROM ResistorHistory";

            if (lastId != null && count != null)
            {
                selectQuery += " WHERE Id > " + lastId + " LIMIT " + count;
            }
        }
        else
        {
            selectQuery = "SELECT " +
                "Id, " +
                "Type, " +
                "FirstBand, " +
                "SecondBand, " +
                "ThirdBand, " +
                "MultiplierBand, " +
                "ToleranceBand, " +
                "PPMBand, " +
                "Value, " +
                "ToleranceValue, " +
                "PPMValue, " +
                "DateTime FROM ResistorHistory WHERE Type = " + type.ordinal();

            if (lastId != null && count != null)
            {
                selectQuery += " AND Id > " + lastId + " LIMIT " + count;
            }
        }

        return selectQuery;
    }


    /**
     * Deletes a resistor from the database.
     * @param id The id of the resistor to delete.
     */
    public void delete(int id)
    {
        Open();
        sqliteDatabase.execSQL("DELETE FROM ResistorHistory WHERE Id = " + id + ";");
        Close();
    }

    /**
     * Selects the count of resistor from the database.
     * @return The count of resistor.
     */
    public int selectCount()
    {
        String selectQuery = "SELECT COUNT(*) FROM ResistorHistory;";
        
        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);
        
        int count = 0;
        if (dbCursor.getCount() > 0)
        {
            while (dbCursor.moveToNext())
                count = dbCursor.getInt(0);
        }
        
        dbCursor.close();
        Close();
        return count;
    }

    /**
     * Selects the count of resistor from the database.
     * @param type The resistor type.
     * @return The count of resistor.
     */
    public int selectCount(ResistorType type)
    {
        String selectQuery = "SELECT COUNT(*) FROM ResistorHistory WHERE Type = " + type.ordinal() + ";";
        
        Open();
        Cursor dbCursor = sqliteDatabase.rawQuery(selectQuery, null);
        
        int count = 0;
        if (dbCursor.getCount() > 0)
        {
            while (dbCursor.moveToNext())
                count = dbCursor.getInt(0);
        }
        
        dbCursor.close();
        Close();
        return count;
    }

    //#endregion
}
