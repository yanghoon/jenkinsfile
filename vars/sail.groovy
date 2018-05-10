import static groovy.io.FileType.FILES

def call(){
  log """\
  |SYSTEM :: run @shared-lib/vars/sail.groovy
  |SYSTEM :: ${new File('.').absolutePath}
  |SYSTEM :: ${GroovySystem.version}"""
  
  // Errors
  //def _default = load("${WORKSPACE}/Jenkinsfile.default")
  //def _default = libraryResource 'Jenkinsfile.default'
  //println _default
  
//  node {
    //Load a pipline config file(.yaml)
    //- https://github.com/jenkinsci/workflow-remote-loader-plugin
    /*
    println """
    ${scm.repositories}
    ${scm.userRemoteConfigs.credentialsId}
    ${scm.branches[0].name}
    ${scm.userRemoteConfigs[0].url}
    """
    
    def _yaml = fileLoader.fromGit('pipeline.yaml', scm.userRemoteConfigs[0].url, scm.branches[0].name, scm.userRemoteConfigs.credentialsId[0], '')
    println _yaml
    */
    
    println "SYSTEM :: ${pwd()}"
    def _yaml
    fileLoader scm.userRemoteConfigs[0].url, scm.branches[0].name, scm.userRemoteConfigs.credentialsId[0], '', {
      println "SYSTEM :: ${pwd()}"
      
      //_yaml = new File("${pwd()}", "pipeline.yaml").text
      // Find list of file name
      // http://mrhaki.blogspot.kr/2009/11/groovy-goodness-finding-files-with.html
      def _files = new FileNameFinder().getFileNames("${pwd()}", 'pipeline.yaml pipeline.yml')
      
      _files.each {
        log """\
        | class: ${it.class}
        | name : ${it}
        | ${new File(it).text}"""
      }
      
      _yaml = _files[0] ? new File(_files[0]).text : null
    }
    println _yaml
    env._config = _yaml
    
    //Load prepared Jenkinsfiles
    //- https://jenkins.io/doc/pipeline/examples/#load-from-file
    load("${pwd()}@libs/sail-lib/Jenkinsfile.default")
//  }
}
