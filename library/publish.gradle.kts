import java.util.*

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").reader())

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group as String
                artifactId = "auth"
                version = project.version as String

                from(components["release"])
            }
        }
        repositories {
            maven {
                //url = uri("$buildDir/repo")

                url = uri(localProperties.getProperty("repo_url"))
                name = "public"
                credentials(AwsCredentials::class) {
                    accessKey = localProperties.getProperty("repo_access_key")
                    secretKey = localProperties.getProperty("repo_secret_key")
                }
            }
        }
    }
}
