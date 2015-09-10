#!/usr/bin/expect

set user_host [lindex $argv 0]
set pwd [lindex $argv 1]

# <!------------------ get path /home/user ------------------>
proc mypath {} {
	global path
	spawn whoami
	expect {
		-re "root"		{set path "/root";}
		-re "(.+)\r"	{set path "/home/$expect_out(1,string)"}
	}
	expect eof
}

# <!------------------ exec scp ------------------>
proc cscp {p uh pwd} {
	spawn scp "$p/.ssh/id_rsa.pub" "$uh:~/.ssh/id_rsa.pub1"
	exp_internal 0
	expect {
		"(yes/no)?" {send -- "yes\r"; exp_continue}
		"password:" {
			send -- "$pwd\r"
			expect {
				"password:" {puts "please check password";exit}
				"100%"
			}
		}
		"100%"
	}
	expect eof
}

# <!------------------ exec ssh ------------------>
proc cssh {uh pwd} {
	spawn ssh $uh
	expect {
		"(yes/no)?" {send -- "yes\r"; exp_continue}
		"password:" {
			exp_send -- "$pwd\r"
			expect "Last login:" {exit;wait}
		}
		"Last login:" {exit;wait}
	}
	expect -re "($|#)$"
	send -- "cat ~/.ssh/id_rsa.pub1 >> ~/.ssh/authorized_keys\r"
	expect -re "($|#)$"
	send -- "chmod 600 ~/.ssh/authorized_keys\r"
	expect -re "($|#)$"
	send -- "rm -rf ~/.ssh/id_rsa.pub1\r"
	expect eof
}

mypath
cscp $path $user_host $pwd
cssh $user_host $pwd

