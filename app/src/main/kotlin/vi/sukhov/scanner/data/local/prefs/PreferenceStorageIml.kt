package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
@Singleton
class PreferenceStorageIml @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        const val PREFS_NAME = "vi.sukhov.scanner"

        const val PREFS_IS_FIRS_RUN = "prefs_is_firs_run"
    }

    //Create shared preference object on first use
    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

  //  override var isFirstLogin: Boolean by BooleanPreference(prefs, PREFS_IS_FIRS_RUN, false)

}

class BooleanPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}*/
