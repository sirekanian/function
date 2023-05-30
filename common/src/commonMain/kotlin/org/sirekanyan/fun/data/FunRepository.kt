package org.sirekanyan.`fun`.data

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import org.sirekanyan.`fun`.createSqlDriver
import org.sirekanyan.`fun`.model.Item
import java.util.UUID

class FunRepository {

    private val driver: SqlDriver = createSqlDriver()
    private val queries = FunDatabase(driver).itemQueries

    fun observeItems(): Flow<List<Item>> =
        queries.selectAll().asFlow().mapToList()

    fun putContent(content: String) {
        queries.insertItem(Item(UUID.randomUUID().toString(), content))
    }

    fun updateContent(id: String, content: String) {
        queries.updateItem(id, content)
    }

    fun delete(uuid: String) {
        queries.deleteItem(uuid)
    }

}