def call(){
  println "SYSTEM :: run @shared-lib/vars/sail.groovy"
  def workspace = new File('.')
  println "SYSTEM :: ${workspace.absolutePath}"
  
  //Load prepared Jenkinsfiles
  //- https://jenkins.io/doc/pipeline/examples/#load-from-file
  
  def _default = load('Jenkinsfile.default')
  _default.start()
}
