def call(){
  println 'SYSTEM :: run @shared-lib/vars/sail.groovy'
  
  //Load prepared Jenkinsfiles
  //- https://jenkins.io/doc/pipeline/examples/#load-from-file
  
  def _default = load('Jenkinsfile.default')
  _default.start()
}
