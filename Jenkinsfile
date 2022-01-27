 pipeline {
    agent any 
    tools
    {
      maven 'Maven'
    }
    stages
    {
      stage('Checkout')
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
          bat 'mvn test -DsuiteXmlFile="smoke.xml"'
        }
      }
      stage('Reports')
      {
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
      stage("Zip Report File ")
      {
        steps{
          script {
            bat "del apiSmoke.zip"
            zip zipFile: 'apiSmoke.zip', archive: false, dir: 'target/allure'
          }
        }
      }
      stage ('Starting Smoke and Regression Test ')
        {
         steps {
           build job: 'bidba_smoke_and_regression'
         }
      }
   }
    post
    {
      success{
        bat "echo 'Send email on success'"
        emailext attachmentsPattern: 'apiSmoke.zip',attachLog: true, body: "Api Smoke Passed", mimeType: 'text/html',
        subject: 'Passed', to: 'sssdprojekat@gmail.com', from:'jenkinsApiSmoke@gmail.com'
      }
      failure{
        bat "echo 'Send email on failure '"
        emailext attachmentsPattern: 'apiSmoke.zip',attachLog: true, body: "Api Smoke Failed", mimeType: 'text/html',
        subject: 'Failed', to: 'sssdprojekat@gmail.com', from:'jenkinsApiSmoke@gmail.com'
      }
    }
  }
