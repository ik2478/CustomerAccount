#!/bin/bash

# $? contains the return code or exit status of the previously executed command
# mkdir /tmp/bak && cp test.txt /tmp/bak
# ping -c 1 google.com && host reachable
# && command following or after  the && will only execute if the mkdir command returns exit status 0

# cp test.txt  /tmp/bak || cp test.txt /tmp/
# ping -c 1 google.com || "host unreachable
# || command following or after  the || will only execute if the mkdir command returns exit status 1

# in scripting,code is not pre-compiled unlike other languages...code is read from top to bottom

echo "heeeeey" $1
ls -ltrcc

if [ "$?" -eq 0 ]
  then
    echo "succesful"
else
  echo "unsuccessful"
fi

STRING="this is a string"
POS=1
LEN=3
#echo "${STRING:$POS:$LEN}"

function test(){
  wed=$1
  if [ "$1" == "we" ] || [ "$1" == "ce" ]
  then
    echo "hi $1"
  else
    echo "not it"
 fi
}

test "we"

exit 0