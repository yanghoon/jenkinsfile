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
    
    println scm
  }
}
