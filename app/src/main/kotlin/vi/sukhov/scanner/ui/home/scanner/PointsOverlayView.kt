package vi.sukhov.scanner.ui.home.scanner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class PointsOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var points: Array<PointF> = arrayOf()
    private var paint: Paint = Paint()

    init {
        paint = Paint()
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
    }

    fun setPoints(points: Array<PointF>) {
        this.points = points
        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        for (pointF in points) {
            canvas.drawCircle(pointF.x, pointF.y, 10f, paint)
        }
    }

    fun clear() {
        points = arrayOf()
        invalidate()
    }
}