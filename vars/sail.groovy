def call(def thiz){
  println "SYSTEM :: run @shared-lib/vars/sail.groovy"
  def workspace = new File('.')
  println "SYSTEM :: ${workspace.absolutePath}"
  println "SYSTEM :: ${thiz.env}"
  println "SYSTEM :: ${thiz.env.WORKSPACE}"
  println "SYSTEM :: ${thiz.WORKSPACE}"
  
  //Load prepared Jenkinsfiles
  //- https://jenkins.io/doc/pipeline/examples/#load-from-file
  
  //def _default = load("${WORKSPACE}/Jenkinsfile.default")
  def _default = libraryResource 'Jenkinsfile.default'
  _default.start()
}
