pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sleep 10
            timestamps()
          }
        }
        stage('Build_2') {
          steps {
            bat(script: 'scriptA', returnStatus: true)
            fileExists 'a.txt'
          }
        }
      }
    }
  }
}