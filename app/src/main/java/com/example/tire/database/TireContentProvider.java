package com.example.tire.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TireContentProvider extends ContentProvider {
    private DBHelper mDBHelper = null;
    private Context mContext;
    private final static String AUTHORITY = "com.example.tire.provider";
    private final static int TIRE_CODE = 1;

    private final static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, TireTable.TABLE_NAME, TIRE_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDBHelper = new DBHelper(mContext);
        return true;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri) {
        String tableName;
        switch (mUriMatcher.match(uri)) {
            case TIRE_CODE:
                tableName = TireTable.TABLE_NAME;
                break;
            default:
                tableName = TireTable.TABLE_NAME;
                break;
        }
        return  tableName;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableName(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.insert(table,null,values);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        // observer : null  通知所有的observer
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int re =  db.delete(table,selection,selectionArgs);
        mContext.getContentResolver().notifyChange(uri,null);
        return re;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int re =  db.update(table,values,selection,selectionArgs);
        mContext.getContentResolver().notifyChange(uri,null);
        return re;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
