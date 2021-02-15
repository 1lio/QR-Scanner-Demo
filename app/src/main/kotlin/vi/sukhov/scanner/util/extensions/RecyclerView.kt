package vi.sukhov.scanner.util.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun inflate(
    ctx: Context,
    @LayoutRes layout: Int,
    viewGroup: ViewGroup,
    attach: Boolean = false
): View =
    LayoutInflater.from(ctx).inflate(layout, viewGroup, attach)
