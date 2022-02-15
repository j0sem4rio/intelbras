package br.com.intelbras.model.dao

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = IntelDB.NAME, version = IntelDB.VERSION)
object IntelDB {
    const val NAME = "intelDB"
    const val VERSION = 1
}