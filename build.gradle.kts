// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.ANDROID_APPLICATION) version(PluginVersion.AGP) apply false
    id(Plugins.ANDROID_LIBRARY) version(PluginVersion.AGP) apply false
    kotlin(Plugins.ANDROID) version (PluginVersion.KOTLIN) apply false
    id(Plugins.ANDROIDX_NAVIGATION) version(PluginVersion.NAVIGATION) apply false

}
