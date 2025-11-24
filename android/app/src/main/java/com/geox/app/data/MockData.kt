package com.geox.app.data

object MockData {
    val disasters = listOf(
        DisasterData(
            id = "1",
            type = DisasterType.EARTHQUAKE,
            title = "Magnitude 6.2 Earthquake",
            magnitude = 6.2f,
            location = "Tokyo, Japan",
            time = "2 hours ago",
            coordinates = Pair(35.6762, 139.6503),
            depth = "10 km",
            description = "A moderate earthquake struck near Tokyo, felt across the Kanto region. No major damage reported."
        ),
        DisasterData(
            id = "2",
            type = DisasterType.WILDFIRE,
            title = "Forest Fire",
            category = "Large",
            location = "California, USA",
            time = "5 hours ago",
            coordinates = Pair(36.7783, -119.4179),
            description = "Wildfire burning in Northern California, affecting approximately 5,000 acres."
        ),
        DisasterData(
            id = "3",
            type = DisasterType.VOLCANO,
            title = "Volcanic Activity",
            category = "Alert Level 3",
            location = "Mount Merapi, Indonesia",
            time = "1 day ago",
            coordinates = Pair(-7.5407, 110.4458),
            description = "Increased volcanic activity detected. Authorities monitoring closely."
        ),
        DisasterData(
            id = "4",
            type = DisasterType.EARTHQUAKE,
            title = "Magnitude 5.8 Earthquake",
            magnitude = 5.8f,
            location = "Chile",
            time = "3 hours ago",
            coordinates = Pair(-33.4489, -70.6693),
            depth = "25 km",
            description = "Earthquake detected off the coast of Chile. Minor tremors felt inland."
        ),
        DisasterData(
            id = "5",
            type = DisasterType.STORM,
            title = "Tropical Storm",
            category = "Category 2",
            location = "Gulf of Mexico",
            time = "6 hours ago",
            coordinates = Pair(25.7617, -80.1918),
            description = "Tropical storm forming in the Gulf, expected to strengthen."
        ),
        DisasterData(
            id = "6",
            type = DisasterType.FLOOD,
            title = "Flash Flooding",
            category = "Severe",
            location = "Bangladesh",
            time = "12 hours ago",
            coordinates = Pair(23.685, 90.3563),
            description = "Heavy monsoon rains causing widespread flooding in multiple regions."
        ),
        DisasterData(
            id = "7",
            type = DisasterType.EARTHQUAKE,
            title = "Magnitude 4.5 Earthquake",
            magnitude = 4.5f,
            location = "Greece",
            time = "8 hours ago",
            coordinates = Pair(39.0742, 21.8243),
            depth = "15 km",
            description = "Minor earthquake in central Greece. No significant damage."
        )
    )
}

