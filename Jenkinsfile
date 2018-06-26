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
        timeout(time: 1, unit: 'HOURS')
      }
      steps {
        sleep 1
        echo 'options_timeout_1_seconds'
      }
    }
    stage('Input') {
      input {
        message 'Should we continue?'
        id 'Yes, we should.'
        submitter 'admin'
      }
      steps {
        echo "Hello, ${PERSON}, nice to meet you."
      }
    }
    stage('When') {
      when {
        branch 'master'
      }
      steps {
        echo 'you are in master bb'
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
  triggers {
    cron('H * * * *')
  }
}