package com.gagan.composelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gagan.composelayouts.ui.theme.ComposeLayoutsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutsTheme {
        LayoutsCodelab()
            }
        }
    }
}


@Composable
private fun LayoutsCodelab() {
    Scaffold {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            StaggeredGrid(modifier = Modifier) {
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
            }
        }
    }
}

/*
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    LazyList()
}
*/

@Composable
fun LazyList() {

    val scrollState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(modifier = Modifier
                .weight(1.0f)
                .padding(8.dp), onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll up")
            }
            Button(modifier = Modifier
                .weight(1.0f)
                .padding(8.dp), onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(99)
                }
            }) {
                Text(text = "Scroll Down")
            }
        }
        LazyColumn(state = scrollState) {
            items(100) {
                Card(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(0.5.dp, MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Item Text $it"
                    )
                }
            }
        }
    }
}


/*@Composable
private fun LayoutsCodelabPreview() {
    ComposeLayoutsTheme {
//        LayoutsCodelab()
        Row {
            Text(
                modifier = Modifier.firstBaselineToTop(50.dp),
                text = "Hello People",
                fontSize = 24.sp
            )
            Text(modifier = Modifier.padding(top = 50.dp), text = "Hello People", fontSize = 24.sp)
        }
    }
}*/

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable { }
        .padding(16.dp)) {
        Surface(
            modifier = Modifier.size(50.dp), shape = CircleShape, MaterialTheme.colors.surface.copy(
                alpha = 0.2f
            )
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Alfred Pennyworth",
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "3 minutes ago",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/*//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLayoutsTheme {
        PhotographerCard()
    }
}*/

fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) =
    this.then(layout { measurable, constraints ->
        val placeable = measurable.measure(constraints = constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline


        val height = placeable.height + placeableY + 100

        return@layout layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, placeableY)
        }
    })

// Add comment

/*@Composable
fun MyCustomColumn(
    modifier: Modifier,
    content: @Composable () -> Unit
) = Layout( modifier = modifier,content = content) { measurables, constraints ->

    val placeables = measurables.map { measurable ->
        measurable.measure(constraints = constraints)
    }

    var yPosition = 0

    return@Layout layout(constraints.maxWidth, constraints.maxHeight) {
        placeables.forEach { placeable ->
            placeable.placeRelative(0, yPosition)
            yPosition += placeable.height
        }
    }
}*/

@Preview(device = Devices.PIXEL_XL)
@Composable
fun MyCustomColumnPreview() {
    Scaffold {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            StaggeredGrid(modifier = Modifier) {
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
                Text(text = "Hello", Modifier.background(color = Color.Cyan))
                Text(text = "ddddddddddddddd", Modifier.background(color = Color.Blue))
                Text(text = "Heldfdflo", Modifier.background(color = Color.Green))
                Text(text = "Helldfffffffffo", Modifier.background(color = Color.Red))
                Text(text = "ffdddd", Modifier.background(color = Color.Magenta))
                Text(text = "Heldfffflo", Modifier.background(color = Color.Yellow))
                Text(text = "Hellsssssssssssso", Modifier.background(color = Color.Blue))
            }
        }
    }
}

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // measure and position children given constraints logic here

        // Keep track of the width of each row
        val rowWidths = IntArray(rows) { 0 }

        // Keep track of the max height of each row
        val rowHeights = IntArray(rows) { 0 }

        val placeables = measurables.mapIndexed { index, measurable ->

            // Measure each child
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = rowHeights[row].coerceAtLeast(placeable.height)

            return@mapIndexed placeable
        }

        // Grid's width is the widest row
        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth

        // Grid's height is the sum of the tallest element of each row
        // coerced to the height constraints
        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // Y of each row, based on the height accumulation of previous rows
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i-1] + rowHeights[i-1]
        }

        // Set the size of the parent layout
        return@Layout layout(width, height) {
            // x cord we have placed up to, per row
            val rowX = IntArray(rows) { 0 }

            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}