package com.example.android.learnschematic.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.learnschematic.R;

/**
 * Created by Jomeno on 9/26/2015.
 */
public class NotesAdapter extends CursorAdapter {
    private final Context mContent;

    public NotesAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
        mContent = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View viewLayout = (View) LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup);

        ViewHolder viewHolder = new ViewHolder(viewLayout);
        viewLayout.setTag(viewHolder);

        return viewLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder)view.getTag();
        viewHolder.textView.setText(cursor.getString(cursor.getColumnIndex(NoteColumns.TITLE)));

    }

    private static class ViewHolder {
        TextView textView;

        public ViewHolder(View v) {
            textView = (TextView) v.findViewById(R.id.text_view);

        }
    }
}
