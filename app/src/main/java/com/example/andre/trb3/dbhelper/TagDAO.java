package com.example.andre.trb3.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.andre.trb3.objects.Tag;

import java.util.ArrayList;

public class TagDAO extends DBHelper {

    public TagDAO(Context context) {
        super(context);
    }

    public void inserirTag(Tag tag) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Tag.COLUMN_TAG, tag.getTag());

        db.insert(DBContract.Tag.TABLE_NAME, null, values);
    }

    public ArrayList<Tag> buscarTags() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DBContract.Tag.SQL_SELECT_TAGS, null);

        ArrayList<Tag> tags = new ArrayList<>();
        while(cursor.moveToNext()) {
            Tag tag = new Tag();

            tag.setId(cursor.getLong(cursor.getColumnIndex(DBContract.Tag._ID)));
            tag.setTag(cursor.getString(cursor.getColumnIndex(DBContract.Tag.COLUMN_TAG)));

            tags.add(tag);
        }

        cursor.close();

        return tags;
    }
}
