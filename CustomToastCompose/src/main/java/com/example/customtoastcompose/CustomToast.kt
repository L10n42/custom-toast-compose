package com.example.customtoastcompose

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import com.example.customtoastcompose.CToast.SetView

class CustomToast(context: Context) : Toast(context) {

    private var vibrationOnShow = false
    private var milliseconds: Long = 0
    private val mContext = context

    fun setVibration(milliseconds: Long = 300) {
        vibrationOnShow = true
        this.milliseconds = milliseconds
    }

    override fun show() {
        super.show()

        if (vibrationOnShow){
            val vibrator = mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            vibrator.cancel()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            } else {
                vibrator.vibrate(milliseconds)
            }
        }
    }

    @Composable
    fun MakeText(
        message: String,
        duration: Int = LENGTH_SHORT,
        gravity: Int = this.gravity,
        xOffSet: Int = this.xOffset,
        yOffSet: Int = this.yOffset,
        singleLine: Boolean = false,
        vectorIcon: ImageVector? = null,
        painterIcon: Painter? = null,
        resourceIcon: Int? = null,
        iconTint: Color? = null,
        iconSize: Dp = 30.dp,
        textColor: Color = Color.Black,
        backgroundColor: Color = Color.White,
        fontSize: TextUnit = 16.sp,
        fontWeight: FontWeight? = null,
        horizontalContentPadding: Dp = 8.dp,
        verticalContentPadding: Dp = 8.dp,
        shape: Shape = RoundedCornerShape(16.dp),
        borderWeight: Dp? = null,
        borderShape: Shape? = null,
        borderColor: Color? = null,
        cancelOnPress: Boolean = false,
        onClick: () -> Unit = { /*TODO*/ }
    ) {
        val context = LocalContext.current

        val mView = ComposeView(context)
        mView.setContent {
            SetView(
                message = message,
                singleLine = singleLine,
                vectorIcon = vectorIcon,
                painterIcon = painterIcon,
                resourceIcon = resourceIcon,
                iconTint = iconTint,
                iconSize = iconSize,
                textColor = textColor,
                backgroundColor = backgroundColor,
                fontSize = fontSize,
                fontWeight = fontWeight,
                horizontalContentPadding = horizontalContentPadding,
                verticalContentPadding = verticalContentPadding,
                shape = shape,
                borderWeight = borderWeight,
                borderShape = borderShape,
                borderColor = borderColor,
                onClick = {
                    if (cancelOnPress) this.cancel()
                    onClick()
                }
            )
        }

        ViewTreeLifecycleOwner.set(mView, LocalLifecycleOwner.current)
        ViewTreeViewModelStoreOwner.set(mView, LocalViewModelStoreOwner.current)
        ViewTreeSavedStateRegistryOwner.set(mView, LocalSavedStateRegistryOwner.current)

        this.duration = duration
        this.view = mView
        this.setGravity(gravity,xOffSet,yOffSet)
    }
}