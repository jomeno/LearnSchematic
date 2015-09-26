package com.example.android.learnschematic.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Jomeno on 9/25/2015.
 */
@Database(version = NoteDatabase.VERSION, fileName = "notedb")
public final class NoteDatabase {

    private NoteDatabase(){}

    public static final int VERSION = 1;

    @Table(NoteColumns.class) public static final String NOTES = "notes";
}
