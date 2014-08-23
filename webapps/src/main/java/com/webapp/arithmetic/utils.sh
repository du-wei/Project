#!/bin/bash

function initSSH(){
	pre="----------> "
 	echo "${pre} prepare ssh-keygen ..."
	if [ ! -f ~/.ssh/id_rsa.pub ]; then
		ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa > /dev/null && echo "${pre} ssh-keygen finished"
		cat ~/.ssh/id_rsa.pub >> ~/.ssh/authroized_key
	else
		echo "${pre} ssh-keygen has been"
	fi 
	chmod 600 ~/.ssh/authroized_key
}

initSSH
