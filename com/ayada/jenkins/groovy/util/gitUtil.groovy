
import groovy.json.JsonSlurper;

def getProjects(gitUrl,group_name,private_token) {
  cmd = "curl ${gitUrl}/api/v3/groups/${group_name}/projects?private_token=${private_token}"
  try {
    proc = cmd.execute()
    proc.waitFor()
    response = proc.in.text
    proc = null
    respObj = new JsonSlurper().parseText(response)
    def projects = respObj.collect { it.name }
    projects.add(' ')
    return projects.sort()
  } catch(Exception e){
       return ["Group ${group_name} Not Found. Msg= ${e.getMessage()}"]
    }
 }
 
def getBranches(gitUrl,GIT_GROUP,GIT_PROJECT,private_token) {
  repo = "${GIT_GROUP}/${GIT_PROJECT}".replace("/", "%2F")
  cmd = "curl ${gitUrl}/api/v3/projects/${repo}/repository/branches?private_token=${private_token}"
  try{
    proc = cmd.execute()
    proc.waitFor()
    response= proc.in.text
    proc = null
    respObj = new JsonSlurper().parseText(response)
    def brnch= respObj.collect {it.name}
   brnch.add(' ')
   return brnch.sort()
  }catch(Exception e){
       return ["ErrorMsg= ${e.getMessage()}"]
  }     
}
