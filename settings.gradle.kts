include("junit4-java", "junit4-groovy")
include("junit5-java", "junit5-groovy")

rootProject.name = "java-test-utils"
rootProject.children.forEach { project ->
    val projectName = project.name
    val projectDirName = "testProjects/$projectName"
    project.projectDir = File(settingsDir, projectDirName)
    assert(project.projectDir.isDirectory)
}
