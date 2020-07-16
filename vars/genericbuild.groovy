import java.io.File;

def call(Map config=[:]){
node {
    stage('SCM') {
    	echo 'Gathering code from version control'
	git branch: '${branch}', url: 'https://github.com/FeynmanFan/JenkinsGroovy.git'
    }
    stage('Build') {
	try{
	        echo 'Building....'
		bat 'dir %windir%\Microsoft.NET\Framework /AD'
		cmd_exec('dir %windir%\Microsoft.NET\Framework /AD')
		bat 'echo %PATH%'
	        echo 'Building New Feature'
		releasenotes(changes:"true")
        }catch(ex){
        	echo 'Something went wrong'
        	echo ex.toString();
        	currentBuild.result = 'FAILURE'
        }
        finally{
        	// cleanup
        }
    }
    stage('Test') {
        echo 'Testing....'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}
}
