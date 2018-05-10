private static final String TMP_FOLDER = 'libLoader'

static def withGit(String repoUrl = DEFAULT_REPO_URL, String repoBranch = DEFAULT_BRANCH, 
        String credentialsId = null, labelExpression = '', Closure<V> body){
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
