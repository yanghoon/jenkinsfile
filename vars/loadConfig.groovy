/* Will executed in node */
import jenkins.scm.api.SCMFileSystem
import hudson.plugins.git.GitSCM

def call(def s) {
  println '1111'
  s = new GitSCM('http://169.56.99.56:32336/yanghoon/jenkinsfile-test.git')
  println '1111 - 1111'
  //s = [$class:GitSCM, branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '13be602e-61cc-469d-a8e9-b3ca297f929e', url: 'https://github.com/yanghoon/jenkinsfile-test.git']]]
  s = new GitSCM(branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '13be602e-61cc-469d-a8e9-b3ca297f929e', url: 'https://github.com/yanghoon/jenkinsfile-test.git']]]
  println '1111 - 2222'
  SCMFileSystem fs = SCMFileSystem.of(currentBuild.rawBuild.getParent(), s)
  println '2222'
    if (fs != null) {
      println '3333'
      String script = fs.child('pipeline.yaml').contentAsString();
      println '4444'
      env._config = script;
      println '5555'
    } else {
      println '6666'
      listener.getLogger().println("Lightweight checkout support not available, falling back to full checkout.");
      println '7777'
    }
  /*
  fileLoader.withGit scm.userRemoteConfigs[0].url, scm.branches[0].name, scm.userRemoteConfigs.credentialsId[0], '', {
    // Find list of file name
    // http://mrhaki.blogspot.kr/2009/11/groovy-goodness-finding-files-with.html
    def _files = new FileNameFinder().getFileNames("${pwd()}", 'pipeline.yaml pipeline.yml')
    
    _files.each { log "Load '${it}'" }
      
    env._config = _files[0] ? new File(_files[0]).text : null
  }
  */
}
