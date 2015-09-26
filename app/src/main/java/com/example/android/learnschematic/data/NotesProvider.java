package com.example.android.learnschematic.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Jomeno on 9/25/2015.
 */
@ContentProvider(authority = NotesProvider.AUTHORITY, database = NoteDatabase.class)
public final class NotesProvider {

    public static final String AUTHORITY = "com.example.android.learnschematic.data.NotesProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    interface Path {
        String NOTES = "notes";
    }

    @TableEndpoint(table = NoteDatabase.NOTES)
    public static class Notes {

        @ContentUri(
                path = Path.NOTES,
                type = "vnd.android.cursor.dir/note",
                defaultSort = NoteColumns.TITLE + " ASC")
        public static final Uri NOTES = buildUri(Path.NOTES);

        @InexactContentUri(
                name="NOTE_ID",
                path = Path.NOTES + "/#",
                type="vnd.android.cursor.item/note",
                whereColumn = NoteColumns._ID,
                pathSegment = 1)
        public static final Uri NOTE(long id) {
            return buildUri(Path.NOTES, String.valueOf(id));
        }


    }
}
