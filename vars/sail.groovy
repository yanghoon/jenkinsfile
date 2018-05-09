import static groovy.io.FileType.FILES

def call(){
  def workspace = new File('.')
  println """
  SYSTEM :: run @shared-lib/vars/sail.groovy
  SYSTEM :: ${workspace.absolutePath}
  """
  
  // Errors
  //def _default = load("${WORKSPACE}/Jenkinsfile.default")
  //def _default = libraryResource 'Jenkinsfile.default'
  //println _default
  
  node {
    println "SYSTEM :: ${pwd()}"
    
    //Load prepared Jenkinsfiles
    //- https://jenkins.io/doc/pipeline/examples/#load-from-file
    load("${pwd()}@libs/sail-lib/Jenkinsfile.default")
    
    /*
    //Load a pipline config file(.yaml)
    //- https://github.com/jenkinsci/workflow-remote-loader-plugin
    println """
    ${scm.repositories}
    ${scm.userRemoteConfigs.credentialsId}
    ${scm.branches[0].name}
    ${scm.userRemoteConfigs[0].url}
    """
    
    def _yaml = fileLoader.fromGit('pipeline.yaml', scm.userRemoteConfigs[0].url, scm.branches[0].name, scm.userRemoteConfigs.credentialsId[0], '')
    println _yaml
    */
    
    def _yaml
    fileLoader.withGit scm.userRemoteConfigs[0].url, scm.branches[0].name, scm.userRemoteConfigs.credentialsId[0], '', {
      println "SYSTEM :: ${pwd()}"
      
      //_yaml = new File("${pwd()}", "pipeline.yaml").text
      _yaml = new FileNameFinder().getFileNames("${pwd()}", 'pipeline.yaml pipeline.yml')
    }
    
    _yaml.each {
      println """
      class: ${it.class}
      name : ${it}
      ${it.&text ? it.text : ''}
      """
    }
    println _yaml
  }
}
