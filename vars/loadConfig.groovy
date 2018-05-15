/* Will executed in node */
import jenkins.scm.SCMFileSystem

def call() {
  println '1111'
  SCMFileSystem fs = SCMFileSystem.of(currentBuild.rawBuild.getParent(), scm)
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
