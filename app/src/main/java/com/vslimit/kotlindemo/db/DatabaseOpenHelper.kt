package com.vslimit.kotlindemo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.vslimit.kotlindemo.App
import org.jetbrains.anko.db.*

/**
 * Created by vslimit on 17/1/23.
 */
class DatabaseOpenHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "person"
        val DB_VERSION = 1
        val instance by lazy { DatabaseOpenHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CompanyTable.TABLE_NAME, true,
                CompanyTable.ID to INTEGER + PRIMARY_KEY + UNIQUE,
                CompanyTable.NAME to TEXT,
                CompanyTable.ADDRESS to TEXT)

//        db.createTable(PersonTable.TABLE_NAME,true,
//                PersonTable.ID to INTEGER + PRIMARY_KEY + UNIQUE,
//                PersonTable.NAME to TEXT,
//                PersonTable.ADDRESS to TEXT,
//                PersonTable.COMPANY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CompanyTable.TABLE_NAME, true)
//        db.dropTable(PersonTable.TABLE_NAME, true)
        onCreate(db)
    }
}