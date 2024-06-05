import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.sql.DataSource
import kotlin.jvm.Throws

interface PaymentProcess {
    fun processPayment(amount:Double):String
}

class PayPalPaymentGateWay:PaymentProcess{

    override fun processPayment(amount: Double): String {
        TODO("Not yet implemented")
    }

}
 class PaymentApi:PaymentProcess{
     override fun processPayment(amount: Double): String {
         TODO("Not yet implemented")
     }

 }

interface  Repository{
        fun processPayment(amount: Double):String
}

interface DataSourceI {
    var latestNews: Flow<List<String>>
}



class NewsRepository @Inject constructor(val newsRemoteDataSource:DataSourceI) {
    val favoriteLatestNews: Flow<List<String>> =
        newsRemoteDataSource.latestNews
            .onStart {  }
            .buffer()
            .onCompletion {

            }.flowOn(Dispatchers.IO)
            .onEach {  }
            .flowOn(Dispatchers.Main)
            .catch { exception -> emit(emptyList()) }
            // If an error happens, emit the last cached values

}

@Entity
data class TaskItem(
    @ColumnInfo(name = "title")
    var taskname: String,
    @ColumnInfo(name = "Description")
    var TaskDescription: String,
    @ColumnInfo(name = "priority")
    var priority: Int

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=0
}
@Dao
interface TaskDao {
    @Insert
    suspend fun getTask(): Flow<List<TaskItem>>

    @Query("SELECT * FROM taskItem ORDER BY id DESC")
    suspend fun getAllTask(): Flow<List<TaskItem>>

    @Insert
    suspend fun insertTask(taskItem: TaskItem)

    @Delete
    suspend fun deleteTask(taskItem: TaskItem)

    @Update
    suspend fun updateTask(taskItem: TaskItem)

}
@Database(entities = [TaskItem::class], version = 1)
abstract  class TaskDatabase:RoomDatabase(){
    abstract fun taskDao():TaskDao

}

