job('primer-job-DSL-test') {
    scm {
        git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('gerard300')
            node / gitConfigEmail('gerardo.aguirre.vivar.300@gmail.com')
        }
    }
    parameters {
          	  stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
              choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
              booleanParam('agente', false)
    }
	triggers {
		cron('H/7 * * * *')
	}
    steps {
		shell("bash jobscript.sh")
	}
    publishers {
      	slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(true)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
    }
    }
}
