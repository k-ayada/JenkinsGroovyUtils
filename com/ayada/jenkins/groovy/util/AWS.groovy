#!groovy
package com.ayada.jenkins.groovy.util

import groovy.json.JsonSlurper

def addSSMKey(name,value,type,region,prefix=''){
	sh (prefix + " aws ssm put-parameter \
                    --name \"${name}\" \
                    --value \"${value}\" \
                    --type \"${type}\" \
                    --region ${region} \
                    --overwrite")
}

// Fetching SSM key value
def getSSMKey(name,region){
  sh (returnStdout: true, script: "aws ssm get-parameter \
    --name \"${name}\" \
    --region \"${region}\" \
    --with-decryption")
}

def getSSMValue(name,region){
	keyJson= getSSMKey(name,region)
	def KeyParameter= new JsonSlurper().parseText(keyJson)
	return KeyParameter.Parameter.Value;
}
