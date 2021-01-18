package com.sejo.custodianlibrary

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class DotLoader : View {

    private val dotPath: Path = Path()
    private val dotPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var smallRad: Float = 10f
    private var bigRad: Float = 20f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        setWillNotDraw(false)

        context?.theme?.obtainStyledAttributes(attrs, R.styleable.DotLoader, 0, 0)?.apply {
            dotPaint.color = getColor(R.styleable.DotLoader_loaderColor, Color.BLACK)
        }

        dotPaint.apply {
            isDither = true
            style = Paint.Style.FILL
        }

        ObjectAnimator.ofFloat(this, "rotation", 0f, 360f).apply {
            duration = 1000L
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = 100
        val minW = resolveSizeAndState(w, widthMeasureSpec, 1)

        val h = 100
        val minH = resolveSizeAndState(h, heightMeasureSpec, 0)

        val size = minW.coerceAtMost(minH)

        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bigRad = w * 0.08f
        smallRad = w * 0.05f

        dotPath.reset()

        //Top Circle
        dotPath.addCircle(w / 2f, h * 0.2f, bigRad, Path.Direction.CW)
        //Bottom Circle
        dotPath.addCircle(w / 2f, h * 0.8f, smallRad, Path.Direction.CW)
        //Left Circle
        dotPath.addCircle(w * 0.2f, h / 2f, smallRad, Path.Direction.CW)
        //Right Circle
        dotPath.addCircle(w * 0.8f, h / 2f, smallRad, Path.Direction.CW)
        //Top-Left Circle
        dotPath.addCircle(w * 0.3f, h * 0.3f, smallRad, Path.Direction.CW)
        //Top-Right Circle
        dotPath.addCircle(w * 0.7f, h * 0.3f, smallRad, Path.Direction.CW)
        //Bottom-Left Circle
        dotPath.addCircle(w * 0.3f, h * 0.7f, smallRad, Path.Direction.CW)
        //Bottom-Right Circle
        dotPath.addCircle(w * 0.7f, h * 0.7f, smallRad, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(dotPath, dotPaint)
    }
}