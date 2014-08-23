#!/usr/bin/expect
# para --> user_host userpwd nfssource nfstarget rootpwd

set user_host [lindex $argv 0]
set pwd [lindex $argv 1]
set nfsf [lindex $argv 2]
set nfst [lindex $argv 3]
set root [lindex $argv 4]

regsub @.* $user_host "" user
if {$user == "root"} {
	set path "/root"
} else {
	set path "/home/$user"
}


# <!------------------ exec scp setnfs_slaves.sh------------------>
proc cscp {uh pwd} {
	spawn scp "/nfs/setnfs_slaves.sh" "$uh:~/setnfs_salves.sh"
	exp_internal 0
	expect {
		"(yes/no)?" { send -- "yes\r"; exp_continue }
		"password:" {
			send -- "$pwd\r"
			expect {
				"password:" { puts "please check password\r"; exit }
				"100%"
			}
		}
		"100%"
	}
	expect eof
}

puts "--------------------------------------> scp start\r "
cscp $user_host $pwd
puts "--------------------------------------> scp stop\r "

puts "--------------------------------------> ssh start\r "
spawn ssh $user_host
expect {
	"(yes/no)?" {send -- "yes\r"; exp_continue}
	"password:" {
		exp_send -- "$pwd\r"
		expect "Last login:"
	}
	"Last login:"
}
expect -re "($|#)$" { send -- "cd ~\r" }
expect -re "($|#)$"	{ send -- "sudo ./setnfs_salves.sh $nfsf $nfst $path\r" }
expect -re "password"	{ send -- "$root\r" }
expect -re "mount.nfs:" { puts "\n-+-+-+-+-+-mount-+-+-+-+-error-+-+-+-+-+-> exec mount error! \n\r "}
expect -re "(.+)\r" { send -- "rm -rf setnfs_salves.sh\r" }
expect -re "($|#)$" { send -- "exit\r" }
expect eof
puts "--------------------------------------> ssh stop\r "


