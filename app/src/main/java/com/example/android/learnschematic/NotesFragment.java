package com.example.android.learnschematic;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.learnschematic.data.NoteColumns;
import com.example.android.learnschematic.data.NotesProvider;
import com.example.android.learnschematic.data.model.Note;

import java.util.ArrayList;


public class NotesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final String LOG_TAG = "NOTES_FRAG";
    private Callback mListener;

    private static final int CURSOR_LOADER_ID = 0;

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        insertData();
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    private void insertData() {
        Log.d(LOG_TAG, "start insert");

        Note[] notes = {
                new Note("ENG 102"),
                new Note("MTH 212")
        };

        ArrayList<ContentProviderOperation> batch = new ArrayList<>();
        for (Note note : notes) {
            Log.d(LOG_TAG, note.title);
            ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(NotesProvider.Notes.NOTES);

            builder.withValue(NoteColumns.TITLE, note.title);
            batch.add(builder.build());
        }

        try{
            getActivity().getContentResolver().applyBatch(NotesProvider.AUTHORITY, batch);
        } catch(RemoteException | OperationApplicationException e){
            Log.e(LOG_TAG, "Error applying batch insert", e);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        ListView list = (ListView)rootView.findViewById(R.id.list);
        



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public interface Callback {
        public void onFragmentInteraction(Uri uri);
    }

}
