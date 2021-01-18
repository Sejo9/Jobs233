package com.sejo.custodianlibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

class CircularImageView : AppCompatImageView {

    private var roundImage: Int = 0
    private var roundColor: Int = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        context.theme.obtainStyledAttributes(attrs, R.styleable.CircularImageView, 0, 0).apply {
            roundImage = getResourceId(R.styleable.CircularImageView_circularSrc, 0)
        }

        if (roundImage != 0) {
            this.setImageDrawable(makeImageCircular(roundImage))
        }

    }

    private fun makeImageCircular(image: Int): Drawable? {

        val img = BitmapFactory.decodeResource(resources, image)
        val round = RoundedBitmapDrawableFactory.create(resources, img)

        round.isCircular = true

        return round
    }

    fun setCircularImageResource(resId: Int) {
        this.setImageDrawable(makeImageCircular(resId))
    }

    fun setCircularBitmap(bm: Bitmap) {
        val round = RoundedBitmapDrawableFactory.create(resources, bm)
        round.isCircular = true
        this.setImageDrawable(round)
    }
}