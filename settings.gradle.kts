rootProject.name = "aliucord-plugins"

include(
    "Minky",
    "Vennie",
    "HideGiftButton"
)

rootProject.children.forEach {
    it.projectDir = file("plugins/kotlin/${it.name}")
}
