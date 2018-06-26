pipeline {
  agent any
  parameters {
    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
  }
  stages {
        stage('Stage1') {
          steps {
            sleep 3
            echo 'Step 1. Hello World!'
          }
        }
        stage('Parameters') {
            steps {
                echo "Hello ${params.PERSON}"
            }
        }
  }
  post {
      always{
        echo 'Always do a post'
      }
  }
}
