import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.greenox.data.InventoryDatabase
import dev.greenox.data.Item
import dev.greenox.data.ItemDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(value = AndroidJUnit4::class)
class ItemDaoTest {

    private lateinit var _itemDao: ItemDao
    private lateinit var _inventoryDatabase: InventoryDatabase

    private var _item1 = Item(id = 1, name = "Apples", price = 10.0, quantity = 20)
    private var _item2 = Item(id = 2, name = "Bananas", price = 15.0, quantity = 97)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        _inventoryDatabase = Room
            .inMemoryDatabaseBuilder(
                context = context,
                klass = InventoryDatabase::class.java,
            )
            .allowMainThreadQueries()
            .build()
        _itemDao = _inventoryDatabase.itemDao()
    }

    private suspend fun addOneItemToDb() {
        _itemDao.insert(item = _item1)
    }

    private suspend fun addTwoItemsToDb() {
        _itemDao.insert(item = _item1)
        _itemDao.insert(item = _item2)
    }

    @Test
    @Throws(IOException::class)
    fun daoInsert_insertsItemIntoDb() = runBlocking {
        addOneItemToDb()
        val allItems = _itemDao.getAllItems().first()
        assertEquals(allItems[0], _item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = _itemDao.getAllItems().first()
        assertEquals(allItems[0], _item1)
        assertEquals(allItems[1], _item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemsToDb()

        _itemDao.update(Item(id = 1, name = "Apples", price = 15.0, quantity = 25))
        _itemDao.update(Item(id = 2, name = "Bananas", price = 5.0, quantity = 50))

        val allItems = _itemDao.getAllItems().first()

        assertEquals(
            allItems[0],
            Item(id = 1, name = "Apples", price = 15.0, quantity = 25),
        )
        assertEquals(
            allItems[1],
            Item(id = 2, name = "Bananas", price = 5.0, quantity = 50),
        )
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()

        _itemDao.delete(item = _item1)
        _itemDao.delete(item = _item2)

        val allItems = _itemDao.getAllItems().first()

        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()

        val item = _itemDao.getItem(id = 1)

        assertEquals(item.first(), _item1)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        _inventoryDatabase.close()
    }
}