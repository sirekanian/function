package org.sirekanyan.`fun`.qrcode

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.math.abs

private val materialColors =
    listOf(
        "#FFCDD2", // Red 100
        "#EF9A9A", // Red 200
        "#E57373", // Red 300
        "#EF5350", // Red 400
        "#F44336", // Red 500
        "#E53935", // Red 600
        "#D32F2F", // Red 700
        "#C62828", // Red 800
        "#B71C1C", // Red 900
        "#FF8A80", // Red A100
        "#FF5252", // Red A200
        "#FF1744", // Red A400
        "#D50000", // Red A700
        "#F8BBD0", // Pink 100
        "#F48FB1", // Pink 200
        "#F06292", // Pink 300
        "#EC407A", // Pink 400
        "#E91E63", // Pink 500
        "#D81B60", // Pink 600
        "#C2185B", // Pink 700
        "#AD1457", // Pink 800
        "#880E4F", // Pink 900
        "#FF80AB", // Pink A100
        "#FF4081", // Pink A200
        "#F50057", // Pink A400
        "#C51162", // Pink A700
        "#E1BEE7", // Purple 100
        "#CE93D8", // Purple 200
        "#BA68C8", // Purple 300
        "#AB47BC", // Purple 400
        "#9C27B0", // Purple 500
        "#8E24AA", // Purple 600
        "#7B1FA2", // Purple 700
        "#6A1B9A", // Purple 800
        "#4A148C", // Purple 900
        "#EA80FC", // Purple A100
        "#E040FB", // Purple A200
        "#D500F9", // Purple A400
        "#AA00FF", // Purple A700
        "#D1C4E9", // Deep Purple 100
        "#B39DDB", // Deep Purple 200
        "#9575CD", // Deep Purple 300
        "#7E57C2", // Deep Purple 400
        "#673AB7", // Deep Purple 500
        "#5E35B1", // Deep Purple 600
        "#512DA8", // Deep Purple 700
        "#4527A0", // Deep Purple 800
        "#311B92", // Deep Purple 900
        "#B388FF", // Deep Purple A100
        "#7C4DFF", // Deep Purple A200
        "#651FFF", // Deep Purple A400
        "#6200EA", // Deep Purple A700
        "#C5CAE9", // Indigo 100
        "#9FA8DA", // Indigo 200
        "#7986CB", // Indigo 300
        "#5C6BC0", // Indigo 400
        "#3F51B5", // Indigo 500
        "#3949AB", // Indigo 600
        "#303F9F", // Indigo 700
        "#283593", // Indigo 800
        "#1A237E", // Indigo 900
        "#8C9EFF", // Indigo A100
        "#536DFE", // Indigo A200
        "#3D5AFE", // Indigo A400
        "#304FFE", // Indigo A700
        "#BBDEFB", // Blue 100
        "#90CAF9", // Blue 200
        "#64B5F6", // Blue 300
        "#42A5F5", // Blue 400
        "#2196F3", // Blue 500
        "#1E88E5", // Blue 600
        "#1976D2", // Blue 700
        "#1565C0", // Blue 800
        "#0D47A1", // Blue 900
        "#82B1FF", // Blue A100
        "#448AFF", // Blue A200
        "#2979FF", // Blue A400
        "#2962FF", // Blue A700
        "#B3E5FC", // Light Blue 100
        "#81D4FA", // Light Blue 200
        "#4FC3F7", // Light Blue 300
        "#29B6F6", // Light Blue 400
        "#03A9F4", // Light Blue 500
        "#039BE5", // Light Blue 600
        "#0288D1", // Light Blue 700
        "#0277BD", // Light Blue 800
        "#01579B", // Light Blue 900
        "#80D8FF", // Light Blue A100
        "#40C4FF", // Light Blue A200
        "#00B0FF", // Light Blue A400
        "#0091EA", // Light Blue A700
        "#B2EBF2", // Cyan 100
        "#80DEEA", // Cyan 200
        "#4DD0E1", // Cyan 300
        "#26C6DA", // Cyan 400
        "#00BCD4", // Cyan 500
        "#00ACC1", // Cyan 600
        "#0097A7", // Cyan 700
        "#00838F", // Cyan 800
        "#006064", // Cyan 900
        "#84FFFF", // Cyan A100
        "#18FFFF", // Cyan A200
        "#00E5FF", // Cyan A400
        "#00B8D4", // Cyan A700
        "#B2DFDB", // Teal 100
        "#80CBC4", // Teal 200
        "#4DB6AC", // Teal 300
        "#26A69A", // Teal 400
        "#009688", // Teal 500
        "#00897B", // Teal 600
        "#00796B", // Teal 700
        "#00695C", // Teal 800
        "#004D40", // Teal 900
        "#A7FFEB", // Teal A100
        "#64FFDA", // Teal A200
        "#1DE9B6", // Teal A400
        "#00BFA5", // Teal A700
        "#C8E6C9", // Green 100
        "#A5D6A7", // Green 200
        "#81C784", // Green 300
        "#66BB6A", // Green 400
        "#4CAF50", // Green 500
        "#43A047", // Green 600
        "#388E3C", // Green 700
        "#2E7D32", // Green 800
        "#1B5E20", // Green 900
        "#B9F6CA", // Green A100
        "#69F0AE", // Green A200
        "#00E676", // Green A400
        "#00C853", // Green A700
        "#DCEDC8", // Light Green 100
        "#C5E1A5", // Light Green 200
        "#AED581", // Light Green 300
        "#9CCC65", // Light Green 400
        "#8BC34A", // Light Green 500
        "#7CB342", // Light Green 600
        "#689F38", // Light Green 700
        "#558B2F", // Light Green 800
        "#33691E", // Light Green 900
        "#CCFF90", // Light Green A100
        "#B2FF59", // Light Green A200
        "#76FF03", // Light Green A400
        "#64DD17", // Light Green A700
        "#F0F4C3", // Lime 100
        "#E6EE9C", // Lime 200
        "#DCE775", // Lime 300
        "#D4E157", // Lime 400
        "#CDDC39", // Lime 500
        "#C0CA33", // Lime 600
        "#AFB42B", // Lime 700
        "#9E9D24", // Lime 800
        "#827717", // Lime 900
        "#F4FF81", // Lime A100
        "#EEFF41", // Lime A200
        "#C6FF00", // Lime A400
        "#AEEA00", // Lime A700
        "#FFF9C4", // Yellow 100
        "#FFF59D", // Yellow 200
        "#FFF176", // Yellow 300
        "#FFEE58", // Yellow 400
        "#FFEB3B", // Yellow 500
        "#FDD835", // Yellow 600
        "#FBC02D", // Yellow 700
        "#F9A825", // Yellow 800
        "#F57F17", // Yellow 900
        "#FFFF8D", // Yellow A100
        "#FFFF00", // Yellow A200
        "#FFEA00", // Yellow A400
        "#FFD600", // Yellow A700
        "#FFECB3", // Amber 100
        "#FFE082", // Amber 200
        "#FFD54F", // Amber 300
        "#FFCA28", // Amber 400
        "#FFC107", // Amber 500
        "#FFB300", // Amber 600
        "#FFA000", // Amber 700
        "#FF8F00", // Amber 800
        "#FF6F00", // Amber 900
        "#FFE57F", // Amber A100
        "#FFD740", // Amber A200
        "#FFC400", // Amber A400
        "#FFAB00", // Amber A700
        "#FFE0B2", // Orange 100
        "#FFCC80", // Orange 200
        "#FFB74D", // Orange 300
        "#FFA726", // Orange 400
        "#FF9800", // Orange 500
        "#FB8C00", // Orange 600
        "#F57C00", // Orange 700
        "#EF6C00", // Orange 800
        "#E65100", // Orange 900
        "#FFD180", // Orange A100
        "#FFAB40", // Orange A200
        "#FF9100", // Orange A400
        "#FF6D00", // Orange A700
        "#FFCCBC", // Deep Orange 100
        "#FFAB91", // Deep Orange 200
        "#FF8A65", // Deep Orange 300
        "#FF7043", // Deep Orange 400
        "#FF5722", // Deep Orange 500
        "#F4511E", // Deep Orange 600
        "#E64A19", // Deep Orange 700
        "#D84315", // Deep Orange 800
        "#BF360C", // Deep Orange 900
        "#FF9E80", // Deep Orange A100
        "#FF6E40", // Deep Orange A200
        "#FF3D00", // Deep Orange A400
        "#DD2C00", // Deep Orange A700
        "#D7CCC8", // Brown 100
        "#BCAAA4", // Brown 200
        "#A1887F", // Brown 300
        "#8D6E63", // Brown 400
        "#795548", // Brown 500
        "#6D4C41", // Brown 600
        "#5D4037", // Brown 700
        "#4E342E", // Brown 800
        "#3E2723", // Brown 900
        "#CFD8DC", // Blue Gray 100
        "#B0BEC5", // Blue Gray 200
        "#90A4AE", // Blue Gray 300
        "#78909C", // Blue Gray 400
        "#607D8B", // Blue Gray 500
        "#546E7A", // Blue Gray 600
        "#455A64", // Blue Gray 700
        "#37474F", // Blue Gray 800
        "#263238", // Blue Gray 900
    ).map { hex ->
        Color(
            red = hex.substring(1..2).toInt(16),
            green = hex.substring(3..4).toInt(16),
            blue = hex.substring(5..6).toInt(16)
        )
    }.filter { color ->
        color.isLightColor()
    }

fun createQrCodeGradient(uuid: UUID): Brush =
    Brush.linearGradient(
        listOf(
            createRandomColor(uuid.mostSignificantBits),
            createRandomColor(uuid.leastSignificantBits),
        )
    )

private fun createRandomColor(value: Long): Color =
    materialColors[abs(value.hashCode()) % materialColors.size]

private fun Color.isLightColor(): Boolean {
    val lightness = red * 299 + green * 587 + blue * 114
    return lightness > 600 && lightness < 900
}
