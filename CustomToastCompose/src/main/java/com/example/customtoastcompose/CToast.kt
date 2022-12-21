package com.example.customtoastcompose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

object CToast {

    object TIcons {
        val Error = Icons.Default.Cancel
        val Success = Icons.Default.CheckCircle
        val Warning = Icons.Default.Error
        val Info = Icons.Default.Info
    }

    object TColors {
        val Error = Color(0xFFE53935)
        val Warning = Color(0xFFF9A825)
        val Success = Color(0xFF43A047)
        val Info = Color(0xFF1E88E5)
        val Content = Color(0xFFFFFFFF)
    }

    @Composable
    fun InfoToast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        enableVibration: Boolean = false,
        gravity: Int? = null,
        xOffSet: Int? = null,
        yOffSet: Int? = null,
    ) {
        val context = LocalContext.current
        val errorToast = CustomToast(context)

        val lGravity = gravity ?: errorToast.gravity
        val lXOffSet = xOffSet ?: errorToast.xOffset
        val lYOffSet = yOffSet ?: (errorToast.yOffset + 100)

        if (enableVibration) errorToast.setVibration()

        errorToast.MakeText(
            message = message,
            duration = duration,
            gravity = lGravity,
            xOffSet = lXOffSet,
            yOffSet = lYOffSet,
            backgroundColor = TColors.Info,
            textColor = TColors.Content,
            vectorIcon = TIcons.Info,
            iconTint = TColors.Content,
        )

        errorToast.show()
    }

    @Composable
    fun WarningToast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        enableVibration: Boolean = false,
        gravity: Int? = null,
        xOffSet: Int? = null,
        yOffSet: Int? = null,
    ) {
        val context = LocalContext.current
        val errorToast = CustomToast(context)

        val lGravity = gravity ?: errorToast.gravity
        val lXOffSet = xOffSet ?: errorToast.xOffset
        val lYOffSet = yOffSet ?: (errorToast.yOffset + 100)

        if (enableVibration) errorToast.setVibration()

        errorToast.MakeText(
            message = message,
            duration = duration,
            gravity = lGravity,
            xOffSet = lXOffSet,
            yOffSet = lYOffSet,
            backgroundColor = TColors.Warning,
            textColor = TColors.Content,
            vectorIcon = TIcons.Warning,
            iconTint = TColors.Content,
        )

        errorToast.show()
    }

    @Composable
    fun SuccessToast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        enableVibration: Boolean = false,
        gravity: Int? = null,
        xOffSet: Int? = null,
        yOffSet: Int? = null,
    ) {
        val context = LocalContext.current
        val errorToast = CustomToast(context)

        val lGravity = gravity ?: errorToast.gravity
        val lXOffSet = xOffSet ?: errorToast.xOffset
        val lYOffSet = yOffSet ?: (errorToast.yOffset + 100)

        if (enableVibration) errorToast.setVibration()

        errorToast.MakeText(
            message = message,
            duration = duration,
            gravity = lGravity,
            xOffSet = lXOffSet,
            yOffSet = lYOffSet,
            backgroundColor = TColors.Success,
            textColor = TColors.Content,
            vectorIcon = TIcons.Success,
            iconTint = TColors.Content,
        )

        errorToast.show()
    }

    @Composable
    fun ErrorToast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        enableVibration: Boolean = true,
        gravity: Int? = null,
        xOffSet: Int? = null,
        yOffSet: Int? = null,
    ) {
        val context = LocalContext.current
        val errorToast = CustomToast(context)

        val lGravity = gravity ?: errorToast.gravity
        val lXOffSet = xOffSet ?: errorToast.xOffset
        val lYOffSet = yOffSet ?: (errorToast.yOffset + 100)

        if (enableVibration) errorToast.setVibration()

        errorToast.MakeText(
            message = message,
            duration = duration,
            gravity = lGravity,
            xOffSet = lXOffSet,
            yOffSet = lYOffSet,
            backgroundColor = TColors.Error,
            textColor = TColors.Content,
            vectorIcon = TIcons.Error,
            iconTint = TColors.Content,
        )

        errorToast.show()
    }

    @Composable
    internal fun SetView(
        message: String,
        singleLine: Boolean,
        vectorIcon: ImageVector?,
        painterIcon: Painter?,
        resourceIcon: Int?,
        iconTint: Color?,
        iconSize: Dp,
        textColor: Color,
        backgroundColor: Color,
        fontSize: TextUnit,
        fontWeight: FontWeight?,
        horizontalContentPadding: Dp = 8.dp,
        verticalContentPadding: Dp = 8.dp,
        shape: Shape,
        borderWeight: Dp?,
        borderShape: Shape?,
        borderColor: Color?,
        onClick: () -> Unit
    ) {
        val borderModifier =
            if (borderWeight != null && borderColor != null && borderShape != null)
                Modifier.border(
                    width = borderWeight,
                    color = borderColor,
                    shape = borderShape,
                ) else Modifier

        Surface(
            modifier = Modifier.wrapContentSize(),
            color = Color.Transparent,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .clickable { onClick() }
                    .background(
                        color = backgroundColor,
                        shape = shape
                    )
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .then(borderModifier)
                    .padding(horizontalContentPadding, verticalContentPadding)
                ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                when {
                    vectorIcon != null -> {
                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            imageVector = vectorIcon,
                            contentDescription = null,
                            colorFilter =
                            if (iconTint != null)
                                ColorFilter.tint(color = iconTint)
                            else
                                null,
                            modifier = Modifier.size(iconSize)
                        )
                    }

                    painterIcon != null -> {
                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterIcon,
                            contentDescription = null,
                            colorFilter =
                            if (iconTint != null)
                                ColorFilter.tint(color = iconTint)
                            else
                                null,
                            modifier = Modifier.size(iconSize)
                        )
                    }

                    resourceIcon != null -> {
                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterResource(id = resourceIcon),
                            contentDescription = null,
                            colorFilter =
                            if (iconTint != null)
                                ColorFilter.tint(color = iconTint)
                            else
                                null,
                            modifier = Modifier.size(iconSize)
                        )
                    }

                    else -> {}
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = textColor,
                    maxLines = if (singleLine) 1 else 5,
                    overflow = if (singleLine) TextOverflow.Ellipsis else TextOverflow.Visible
                )

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}