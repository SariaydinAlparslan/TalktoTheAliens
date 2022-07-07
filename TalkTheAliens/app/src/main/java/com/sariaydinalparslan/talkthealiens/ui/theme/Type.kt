package com.sariaydinalparslan.talkthealiens.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sariaydinalparslan.talkthealiens.R

val oswald = FontFamily(
    Font(R.font.oswalsemi, FontWeight.SemiBold),
    Font(R.font.oswalregular, FontWeight.Normal),
    Font(R.font.oswalmid, FontWeight.Medium),
    Font(R.font.oswald, FontWeight.Bold),
    Font(R.font.oswaldlight, FontWeight.Light),

    )
val perma =FontFamily(
    Font(R.font.permatype, FontWeight.Bold)
)
val museumTypee =FontFamily(
    Font(R.font.museumstyle, FontWeight.Bold)
)
val parisType =FontFamily(
    Font(R.font.parisstyle, FontWeight.Bold)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
// Set of Material typography styles to start with

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)