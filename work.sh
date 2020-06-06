#!/bin/bash

echo "executing $0"
echo "first positional parameter is $1"

item="4"

if [ $item -ge 5 ]
#if [ $item -eq 1 ]
then
  echo $item "is hi"
else
  echo "nijce"

fi

pictures=$(ls *.gradle)
echo "me"
Date=$(date)

color="blue green yello"

for picture in $pictures
do
  echo $Date $picture
done

read -p "Your name please" USER

echo "heloo $USER"

exit 0