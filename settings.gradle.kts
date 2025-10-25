rootProject.name = "aliucord-plugins"

include(
    "Minky",
    "Vennie",
    "HideServerBanner"
)

rootProject.children.forEach {
    it.projectDir = file("plugins/kotlin/${it.name}")
}
