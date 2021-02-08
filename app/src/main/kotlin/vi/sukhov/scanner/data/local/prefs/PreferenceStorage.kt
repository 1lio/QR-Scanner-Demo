package vi.sukhov.scanner.data.local.prefs

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    var isFirstLogin: Flow<Boolean>

}
