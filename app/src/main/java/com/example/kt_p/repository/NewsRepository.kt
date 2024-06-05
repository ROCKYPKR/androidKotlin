import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsRemoteDataSource:DataSourceI):Repository {
    val favoriteLatestNews: Flow<List<String>> =
        newsRemoteDataSource.latestNews
            .onStart {  }
            .buffer()
            .onCompletion {

            }.flowOn(Dispatchers.IO)
            .onEach {  }
            .flowOn(Dispatchers.Main)
            .catch { exception -> emit(emptyList()) }

    override fun processPayment(amount: Double): String {
        TODO("Not yet implemented")
    }
    // If an error happens, emit the last cached values

}
interface  Repository{
    fun processPayment(amount: Double):String
}