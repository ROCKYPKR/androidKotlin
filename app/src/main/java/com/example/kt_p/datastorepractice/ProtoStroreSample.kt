import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

class ProtoStroreSample (val context: Context){
    private val USER_PREFERENCES_NAME = "user_preferences"
    private val DATA_STORE_FILE_NAME = "user_prefs.pb"
    private val SORT_ORDER_KEY = "sort_order"

//    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
//        fileName = DATA_STORE_FILE_NAME,
//        serializer = UserPreferencesSerializer
//
//    )




}