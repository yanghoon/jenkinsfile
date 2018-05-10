private static final String TMP_FOLDER = 'libLoader'

def call(String repoUrl = DEFAULT_REPO_URL, String repoBranch = DEFAULT_BRANCH, 
        String credentialsId = null, labelExpression = '', Closure body){
  Map<String, Object> loaded = new TreeMap<String, Object>()
    node(labelExpression) {
      //withTimestamper {
        dir(TMP_FOLDER) {
          // Flush the directory
          deleteDir()

          // Checkout
          echo "Checking out ${repoUrl}, branch=${repoBranch}"
          checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: repoBranch]], 
                          userRemoteConfigs: [[credentialsId: credentialsId, url: repoUrl]]]
          
          // Invoke body in the folder
          body();

          // Flush the directory again
          deleteDir()
        }
      //}
    }
}

/* from 'https://github.com/jenkinsci/workflow-remote-loader-plugin/blob/master/src/main/resources/org/jenkinsci/plugins/workflow/remoteloader/FileLoaderDSL/FileLoaderDSLImpl.groovy#L51' */