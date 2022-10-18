pipeline {
    agent any

    options {
      disableResume()
      buildDiscarder(logRotator(numToKeepStr: '30'))
      timestamps()
      timeout(time: 2, unit: 'HOURS')
    }

   stages {
    stage('tester') {
      steps {
        script {
          properties([
            parameters([
              booleanParam(
                defaultValue: true, 
                  description: '', 
                  name: 'doDryRun'
                )

            ])
          ])
            echo "try to connect with credentials"
//            if 
            withCredentials([string(credentialsId: "leo.vm_pass", variable: 'SSHPASS')]){
                sh 'sshpass -e ssh leo@192.168.56.102 -t "export WLST_LOC="/home/leo/Documents/wlcourse/Oracle/Middleware/Oracle_Home/oracle_common/common/bin"; cd /home/leo/Documents/wlcourse/testscripts/testing; ./manage.sh ms1 stop"'
            }
        }
      }
    }
   }
}