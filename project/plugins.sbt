// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt


resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")