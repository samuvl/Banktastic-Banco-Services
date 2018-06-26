pipeline {
  agent any
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
    stage('TimeoutStage') {
      agent any
      options {
        timeout(time: 1, unit: 'SECONDS')
      }
      steps {
        sleep 1
        echo 'options_timeout_1_seconds'
      }
    }
  }
  post {
    always {
      echo 'Always do a post'

    }

  }
  parameters {
    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
  }
}