/* Will executed in node */
def call() {
  try (SCMFileSystem fs = SCMFileSystem.of(currentBuild.rawBuild.getParent(), scm)) {
    if (fs != null) {
      String script = fs.child('pipeline.yaml').contentAsString();
      env._config = script;
    } else {
      listener.getLogger().println("Lightweight checkout support not available, falling back to full checkout.");
    }
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
