def call(){
  println "SYSTEM :: run @shared-lib/vars/sail.groovy"
  def workspace = new File('.')
  println "SYSTEM :: ${workspace.absolutePath}"
  
  // Errors
  //def _default = load("${WORKSPACE}/Jenkinsfile.default")
  //def _default = libraryResource 'Jenkinsfile.default'
  //println _default
  
  node {
    println "SYSTEM :: ${pwd()}"
    
    //Load prepared Jenkinsfiles
    //- https://jenkins.io/doc/pipeline/examples/#load-from-file
    load("${pwd()}@libs/sail-lib/Jenkinsfile.default")
    
    //Load a pipline config file(.yaml)
    //- https://github.com/jenkinsci/workflow-remote-loader-plugin
    //println "${env.GIT_REPO_URL}"
    //println "${env.GIT_BRANCH}"
    println "${scm.repositories}"
    println "${scm.userRemoteConfigs}"
    println "${scm.userRemoteConfigs.credentialsId}"
    
    println "${scm.branches[0].name}"
    println "${scm.userRemoteConfigs[0].url}"
    
    def _yaml = fileLoader.fromGit('pipeline.yaml', scm.branches[0].name, scm.repositories[0].url, scm.userRemoteConfigs.credentialsId, '')
    println _yaml
  }
}
