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
    stage('when branch name') {
      when {
        expression {
          BRANCH_NAME ==~ /(master|staging)/
        }

        anyOf {
          environment name: 'DEPLOY_TO', value: 'master'
          environment name: 'DEPLOY_TO', value: 'staging'
        }

      }
      steps {
        echo 'branch name may be: master or staging. Environment name ...'
      }
    }
    stage('Script-Browser') {
      steps {
        echo 'Hello World'
        script {
          def browsers = ['chrome', 'firefox']
          for (int i = 0; i < browsers.size(); ++i) {
            echo "Testing the ${browsers[i]} browser"
          }
        }

      }
    }
    
    stage('IFELSE') {
        if (env.BRANCH_NAME == 'master') {
            echo 'I only execute on the master branch'
        } else {
            echo 'I execute elsewhere'
        }
    }
    stage('Try-CATCH') {
        try {
            sh 'exit 1'
        }
        catch (exc) {
            echo 'Something failed, I should sound the klaxons!'
            throw
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
