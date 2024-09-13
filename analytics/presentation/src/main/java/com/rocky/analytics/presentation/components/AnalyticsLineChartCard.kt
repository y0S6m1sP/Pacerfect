package com.rocky.analytics.presentation.components

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rocky.analytics.domain.ChartData
import com.rocky.core.presentation.designsystem.PacerfectTheme
import kotlin.math.roundToInt

@Composable
fun AnalyticsLineChartCard(
    modifier: Modifier = Modifier,
    title: String,
    data: List<ChartData>
) {
    if (data.size <= 1) return
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        AnalyticsLineChart(data = data)
    }
}

@Composable
fun AnalyticsLineChart(
    modifier: Modifier = Modifier,
    data: List<ChartData>,
    lineColor: Color = MaterialTheme.colorScheme.primary,
) {
    val density = LocalDensity.current
    val startPadding = density.run { 16.dp.toPx() }

    val coordinates = mutableListOf<PointF>()
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()

    val upperValue = remember(data) {
        (data.maxOfOrNull { it.y }?.plus(1))?.roundToInt() ?: 0
    }

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier.aspectRatio(4f / 3f)) {
        val spaceBetween = (size.width - startPadding) / (data.size - 1)
        val bottomLinePadding = size.height - 24.dp.toPx()

        // x labels
        data.indices.forEach { i ->
            drawContext.canvas.nativeCanvas.drawText(
                data[i].x,
                startPadding + i * spaceBetween,
                size.height,
                textPaint
            )
        }

        // bottom line
        drawLine(
            color = Color.White,
            start = Offset(startPadding, bottomLinePadding),
            end = Offset(size.width, bottomLinePadding),
            strokeWidth = 2f
        )

        // vertical dot line
        for (i in 0..4) {
            val dotLineXSpace = startPadding + i * (size.width - startPadding) / 5
            drawLine(
                color = Color.Gray,
                start = Offset(dotLineXSpace, bottomLinePadding),
                end = Offset(dotLineXSpace, 0f),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }

        // draw circles on the chart
        data.forEachIndexed { index, value ->
            val x = startPadding + index * spaceBetween
            val y = bottomLinePadding - (value.y / upperValue.toFloat()) * (bottomLinePadding)
            drawCircle(
                color = lineColor,
                radius = 5f,
                center = Offset(x, y.toFloat())
            )
            coordinates.add(PointF(x, y.toFloat()))
        }

        // save control points for bezier curve
        for (i in 1 until coordinates.size) {
            controlPoints1.add(
                PointF(
                    (coordinates[i].x + coordinates[i - 1].x) / 2,
                    coordinates[i - 1].y
                )
            )
            controlPoints2.add(
                PointF(
                    (coordinates[i].x + coordinates[i - 1].x) / 2,
                    coordinates[i].y
                )
            )
        }

        // draw bezier curve and fill the area under the curve
        val stroke = Path().apply {
            reset()
            moveTo(coordinates.first().x, coordinates.first().y)
            for (i in 0 until data.size - 1) {
                cubicTo(
                    controlPoints1[i].x, controlPoints1[i].y,
                    controlPoints2[i].x, controlPoints2[i].y,
                    coordinates[i + 1].x, coordinates[i + 1].y
                )
            }
        }
        val fillPath = android.graphics.Path(stroke.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(coordinates.last().x, bottomLinePadding)
                lineTo(startPadding, bottomLinePadding)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    lineColor.copy(alpha = 0.5f),
                    Color.Transparent
                ),
                endY = bottomLinePadding
            )
        )
        drawPath(
            path = stroke,
            color = lineColor,
            style = Stroke(
                width = 1.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}

@Preview
@Composable
private fun AnalyticsChartPreview() {
    PacerfectTheme {
        AnalyticsLineChartCard(
            title = "Avg. Distance Over Time",
            data = listOf(
                ChartData("09-10", 0.113),
                ChartData("09-11", 1.113),
                ChartData("09-12", 2.113),
                ChartData("09-13", 0.513),
                ChartData("09-14", 0.313),
                ChartData("09-15", 0.713),
                ChartData("09-16", 0.213),
            )
        )
    }
}

