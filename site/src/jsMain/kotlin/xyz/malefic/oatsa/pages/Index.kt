package xyz.malefic.oatsa.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.core.Page as PageAnnotation
import org.jetbrains.compose.web.css.Color as CssColor

// Modern TSA Theme with enhanced colors and typography
object TSATheme {
    val Primary = Color.rgb(206, 17, 38) // TSA Red
    val Secondary = Color.rgb(0, 56, 168) // TSA Blue
    val White = Color.rgb(255, 255, 255)
    val LightGray = Color.rgb(248, 249, 250)
    val DarkGray = Color.rgb(52, 58, 64)
    val Shadow = Color.rgba(0f, 0f, 0f, 0.1f)
    val ShadowHover = Color.rgba(0f, 0f, 0f, 0.15f)

    // Typography
    val FontPrimary = "Inter, 'Segoe UI', system-ui, -apple-system, sans-serif"
    val FontDisplay = "'Inter', 'Segoe UI', system-ui, -apple-system, sans-serif"
}

// Enhanced Navigation bar with modern styling
@Composable
fun NavBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(80.px)
            .background(TSATheme.White)
            .boxShadow(0.px, 2.px, 10.px, color = TSATheme.Shadow)
            .padding(leftRight = 32.px, topBottom = 16.px),
    ) {
        Row(
            Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavButton("Home", Page.Home)
            Box(Modifier.width(16.px))
            NavButton("Competitions", Page.PageOne)
            Box(Modifier.width(16.px))
            NavButton("Resources", Page.PageTwo)
        }

        // Enhanced TSA Logo section
        Row(
            Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                "https://tsaweb.org/ResourcePackages/Bootstrap5/assets/dist/img/TSA_logo.png",
                Modifier
                    .height(48.px)
                    .objectFit(ObjectFit.Contain),
            )
        }
    }
}

@Composable
fun NavButton(
    label: String,
    page: Page,
) {
    val isActive = PageState.currentPage == page
    Button(
        onClick = { PageState.currentPage = page },
        Modifier
            .background(if (isActive) TSATheme.Primary else CssColor.transparent)
            .color(if (isActive) TSATheme.White else TSATheme.DarkGray)
            .borderRadius(12.px)
            .padding(topBottom = 12.px, leftRight = 24.px)
            .fontFamily(TSATheme.FontPrimary)
            .fontSize(0.95.cssRem)
            .fontWeight(if (isActive) FontWeight.SemiBold else FontWeight.Medium)
            .transition(Transition.of("all", 200.ms, timingFunction = TransitionTimingFunction.Ease))
            .thenIf(isActive, Modifier.boxShadow(0.px, 4.px, 12.px, color = Color.rgba(206f, 17f, 28f, 0.3f))),
    ) {
        Text(label)
    }
}

@PageAnnotation
@Composable
fun Navigation() {
    Column(
        Modifier
            .fillMaxSize()
            .background(TSATheme.LightGray),
    ) {
        NavBar()
        Box(
            Modifier
                .fillMaxSize()
                .padding(32.px),
            contentAlignment = Alignment.Center,
        ) {
            when (PageState.currentPage) {
                Page.Home -> HomePage()
                Page.PageOne -> CompetitionsPage()
                Page.PageTwo -> ResourcesPage()
            }
        }
    }
}

@Composable
fun HomePage() {
    Column(
        Modifier
            .maxWidth(800.px)
            .background(TSATheme.White)
            .borderRadius(16.px)
            .boxShadow(0.px, 8.px, 32.px, color = TSATheme.Shadow)
            .padding(48.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SpanText(
            "Welcome to TSA",
            Modifier
                .fontFamily(TSATheme.FontDisplay)
                .fontSize(2.5.cssRem)
                .fontWeight(FontWeight.Bold)
                .color(TSATheme.Secondary)
                .textAlign(TextAlign.Center)
                .letterSpacing((-0.02).em)
                .margin(bottom = 16.px),
        )
        SpanText(
            "Technology Student Association",
            Modifier
                .fontFamily(TSATheme.FontPrimary)
                .fontSize(1.25.cssRem)
                .color(TSATheme.DarkGray)
                .textAlign(TextAlign.Center)
                .margin(bottom = 32.px)
                .letterSpacing(0.02.em),
        )
        SpanText(
            "Empowering students to develop leadership skills, explore STEM careers, and compete in technology-based challenges.",
            Modifier
                .fontFamily(TSATheme.FontPrimary)
                .fontSize(1.1.cssRem)
                .color(TSATheme.DarkGray)
                .textAlign(TextAlign.Center)
                .lineHeight(1.6.em)
                .margin(bottom = 24.px),
        )
    }
}

@Composable
fun CompetitionsPage() {
    Column(
        Modifier
            .maxWidth(600.px)
            .background(TSATheme.White)
            .borderRadius(16.px)
            .boxShadow(0.px, 8.px, 32.px, color = TSATheme.Shadow)
            .padding(40.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SpanText(
            "Competitions",
            Modifier
                .fontFamily(TSATheme.FontDisplay)
                .fontSize(2.2.cssRem)
                .fontWeight(FontWeight.Bold)
                .color(TSATheme.Primary)
                .textAlign(TextAlign.Center)
                .margin(bottom = 24.px),
        )
        SpanText(
            "Explore our competitive events and showcase your skills in technology, engineering, and leadership.",
            Modifier
                .fontFamily(TSATheme.FontPrimary)
                .fontSize(1.1.cssRem)
                .color(TSATheme.DarkGray)
                .textAlign(TextAlign.Center)
                .lineHeight(1.6.em)
                .margin(bottom = 32.px),
        )
        ModernButton(
            text = "View Resources",
            onClick = { PageState.currentPage = Page.PageTwo },
            backgroundColor = TSATheme.Secondary,
            textColor = TSATheme.White,
        )
    }
}

@Composable
fun ResourcesPage() {
    Column(
        Modifier
            .maxWidth(600.px)
            .background(TSATheme.White)
            .borderRadius(16.px)
            .boxShadow(0.px, 8.px, 32.px, color = TSATheme.Shadow)
            .padding(40.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SpanText(
            "Resources",
            Modifier
                .fontFamily(TSATheme.FontDisplay)
                .fontSize(2.2.cssRem)
                .fontWeight(FontWeight.Bold)
                .color(TSATheme.Secondary)
                .textAlign(TextAlign.Center)
                .margin(bottom = 24.px),
        )
        SpanText(
            "Access learning materials, guides, and tools to help you succeed in TSA competitions and beyond.",
            Modifier
                .fontFamily(TSATheme.FontPrimary)
                .fontSize(1.1.cssRem)
                .color(TSATheme.DarkGray)
                .textAlign(TextAlign.Center)
                .lineHeight(1.6.em)
                .margin(bottom = 32.px),
        )
        ModernButton(
            text = "Back to Home",
            onClick = { PageState.currentPage = Page.Home },
            backgroundColor = TSATheme.Primary,
            textColor = TSATheme.White,
        )
    }
}

@Composable
fun ModernButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
) {
    Button(
        onClick = { onClick() },
        Modifier
            .background(backgroundColor)
            .color(textColor)
            .borderRadius(12.px)
            .padding(topBottom = 12.px, leftRight = 32.px)
            .fontFamily(TSATheme.FontPrimary)
            .fontSize(1.cssRem)
            .fontWeight(FontWeight.SemiBold)
            .transition(Transition.of("all", 200.ms, timingFunction = TransitionTimingFunction.Ease))
            .boxShadow(0.px, 3.px, 12.px, color = Color.rgba(0f, 0f, 0f, 0.15f)),
    ) {
        Text(text)
    }
}

enum class Page {
    Home,
    PageOne,
    PageTwo,
}

object PageState {
    var currentPage: Page by mutableStateOf(Page.Home)
}
