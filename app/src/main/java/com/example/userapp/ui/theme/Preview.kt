package com.example.userapp.ui.theme

import androidx.compose.ui.tooling.preview.Preview



@Preview(
    device = "spec:width=411dp,height=891dp",//long iPhone 430/926
    name = "Stock Reference Phone",
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    device = "spec:width=673dp,height=841dp",
    name = "Stock Reference Foldable",
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    device = "spec:width=841dp,height=673dp,dpi=480",
    name = "Stock Reference Foldable (landscape)",

    showBackground = true,
    showSystemUi = true,
)
@Preview(
    device = androidx.compose.ui.tooling.preview.Devices.NEXUS_5,
    name = "Samsung S7 / Nexus 5",
    showBackground = true,
    showSystemUi = true,
)

@Preview(
    device = androidx.compose.ui.tooling.preview.Devices.PIXEL_2,
    name = "Pixel 2",
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    device = "spec:width=320dp,height=480dp",
    name = "Small Phone",
    showBackground = true,
    showSystemUi = true,
)
@Preview
    (
    device = androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL,
    name = "Pixel 4 XL",

    showBackground = true,
    showSystemUi = true,
)
@Preview
    (
    device = androidx.compose.ui.tooling.preview.Devices.PIXEL_3A,
    name = "Pixel 3A",
    showBackground = true,
    showSystemUi = true,
)

@Preview
    (
    device = "spec:width=411dp,height=868dp,dpi=480",
    name = "Pixel 7 Pro",
    showBackground = true,
    showSystemUi = true,
)

annotation class ScreenPreview

@Preview(
    device = androidx.compose.ui.tooling.preview.Devices.NEXUS_5,
    name = "Samsung S7 / Nexus 5",
    showBackground = true,
    showSystemUi = true,
)
annotation class GMCPreview

