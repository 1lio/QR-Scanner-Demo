package vi.sukhov.scanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import vi.sukhov.scanner.util.Logger
import javax.inject.Inject

@HiltAndroidApp
class QRScannerApp : Application()