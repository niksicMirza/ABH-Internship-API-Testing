pipeline {
    agent any 
    tools
    {
      maven 'Maven'
    }
    stages
    {
      stage('checkout')
      {
        steps
        {
          deleteDir()
          git url:'https://github.com/niksicMirza/rest_assured_testing_bidba.git'
        }
      }
      stage('Test')
      {
        steps
        {
          echo "Test"
          bat 'mvn test'
        }
      }
      stage('reports') {
      steps {
        script {
          allure([
                  includeProperties:false,
                  jdk:'',
                  properties: [],
          reportBuildPolicy:
          'ALWAYS',
                  results: [[path:
          'target/allure/allure-results']]
            ])
        }
        } 
      }
      stage("aaa"){
          steps{
        script{
            bat "del test.zip"
        zip zipFile: 'test.zip', archive: false, dir: 'target/allure'
          }
      }
    }
    }
        post {
      success {
        bat "echo 'Send mail on success'"
       emailext attachmentsPattern: 'test.zip',attachLog: true, body: "Api Smoke Passed", mimeType: 'text/html', subject: 'Passed', to: 'sssdprojekat@gmail.com', from:'jenkinsApiSmoke@gmail.com'
      }
      failure {
        bat "echo 'Send mail on failure'"
        emailext attachmentsPattern: 'test.zip',attachLog: true, body: "Api Smoke Failed", mimeType: 'text/html', subject: 'Failed', to: 'sssdprojekat@gmail.com', from:'jenkinsApiSmoke@gmail.com'
      }
    }
  }
