#!groovy

def mvnBuildJar(pomFldr, skipTestFlag ) {
    def mvnBuildCommand = "mvn " +
                          " clean package " +
                          " -Dmaven.test.skip=${skipTestFlag}" +
                          " -f ${pomFldr}/pom.xml"
    echo "Maven command: ${mvnBuildCommand}"
    try{
        def mvnOut = sh(returnStdout: true, script: mvnBuildCommand).trim()
        println "Output from Maven build,\n ${mvnOut}"
        def lscmd = "ls -ltrh ${pomFldr}/target"
        def out = sh(returnStdout: true, script: lscmd).trim()
        println "List of files in the dir ${pomFldr}/target\n ${out}"
    } catch(Exception e) {
        println "Caught Exception: " + e.getClass()
        println "Message         : " + e.getMessage()
        println "StackTrace,\n" + e.toString()
        sh "exit 1"
    }
}
