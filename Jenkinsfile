pipeline {
  agent any
  environment { 
        CC = 'clang'
  }
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
     stage('Example') {
            options {
                timeout(time: 1, unit: 'SECONDS') 
            }
            steps {
                sleep 10
                echo 'options_timeout_1_seconds'
            }
    }
    post {
        always{
          echo 'Always do a post'
        }
    {
  }
}
