pipeline {
  agent any
  stages {
    stage('Stage1') {
      steps {
        sleep 10
        echo 'Step 1. Hello World!'
      }
    }
    stage('Envorinment') {
            environment { 
                AN_ACCESS_KEY = credentials('my-prefined-secret-text') 
            }
            steps {
                sh 'printenv'
            }
        }
    post {
      always{
        echo 'Always do a post'
      }
      {
  }
}
