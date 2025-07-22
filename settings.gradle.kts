rootProject.name = "aliucord-plugins"

include(
    "minky",
    "vennie"
)

rootProject.children.forEach {
    it.projectDir = file("plugins/kotlin/${it.name}")
}
