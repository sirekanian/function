package org.sirekanyan.`fun`.data

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import org.sirekanyan.`fun`.createSqlDriver
import org.sirekanyan.`fun`.model.Item
import java.lang.System.currentTimeMillis

class FunRepository {

    private val driver: SqlDriver = createSqlDriver()
    private val queries = FunDatabase(driver).itemQueries

    fun getAllItems(): List<Item> =
        queries.selectAll().executeAsList()

    fun observeLastItems(): Flow<List<Item>> =
        queries.selectLast { id, timestamp, content -> Item(id, checkNotNull(timestamp), content) }.asFlow().mapToList()

    fun putContent(id: String, content: String) {
        queries.insertItem(Item(id, currentTimeMillis(), content))
    }

    fun putItem(item: Item) {
        queries.insertItem(item)
    }

    fun delete(id: String, timestamp: Long) {
        queries.deleteItem(id, timestamp)
    }

}