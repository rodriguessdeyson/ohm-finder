package com.rad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBContext
{
    //region Constants
    /**
     * The name of the database.
     */
    private final String DATABASE_NAME = "OhmFinder";

    //endregion

    //region Attributes

    /**
     * Local instance of sqliteDatabase to manipulate the .db file.
     */
    protected SQLiteDatabase sqliteDatabase;

    /**
     * Activity context references.
     */
    private final Context context;

    //endregion

    //region Constructor

    /**
     * Initialize an objective of type DBContext.
     * @param ctx Activity context.
     */
    public DBContext(Context ctx)
    {
        this.context = ctx;
    }

    //endregion

    //region Methods

    /**
     * Creates a new SQLite database.
     */
    public void Create()
    {
        // Create the database informed.
        sqliteDatabase = context
                .openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        String resistorHistoryTable =
            "CREATE TABLE IF NOT EXISTS ResistorHistory(" +
                "Id             INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Type           INTEGER NOT NULL," +
                "FirstBand      INTEGER NOT NULL," +
                "SecondBand     INTEGER NOT NULL," +
                "ThirdBand      INTEGER," +
                "MultiplierBand INTEGER NOT NULL," +
                "ToleranceBand  INTEGER NOT NULL," +
                "PPMBand        INTEGER," +
                "Value          REAL NOT NULL," +
                "ToleranceValue REAL NOT NULL," +
                "PPMValue       REAL," +
                "DateTime       TEXT NOT NULL)";
        sqliteDatabase.execSQL(resistorHistoryTable);
    }

    /**
     * Opens an existing database by its name.
     */
    protected void Open()
    {
        sqliteDatabase = context
            .openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    /**
     * Closes an existing opened database.
     */
    protected void Close()
    {
        if (sqliteDatabase.isOpen())
            sqliteDatabase.close();
    }

    //endregion


}
