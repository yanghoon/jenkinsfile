def call(){
  println "SYSTEM :: run @shared-lib/vars/sail.groovy"
  def workspace = new File('.')
  println "SYSTEM :: ${workspace.absolutePath}"
  println "SYSTEM :: ${env}"
  println "SYSTEM :: ${env.WORKSPACE}"
  println "SYSTEM :: ${WORKSPACE}"
  
  //Load prepared Jenkinsfiles
  //- https://jenkins.io/doc/pipeline/examples/#load-from-file
  
  //def _default = load("${WORKSPACE}/Jenkinsfile.default")
  def _default = libraryResource 'Jenkinsfile.default'
  _default.start()
}
