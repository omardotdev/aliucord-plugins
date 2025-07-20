rootProject.name = "aliucord-plugins"

include(
    "minky"
)

rootProject.children.forEach {
    it.projectDir = file("plugins/kotlin/${it.name}")
}
