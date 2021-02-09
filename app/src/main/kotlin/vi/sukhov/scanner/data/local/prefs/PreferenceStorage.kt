package vi.sukhov.scanner.data.local.prefs

interface PreferenceStorage {
    var timeLoadedAt: Long
    var isDarkMode: Boolean
}