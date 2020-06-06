#!/bin/bash

 cd var/www

for file in  *.html
  do
     echo "copying $file"
     sudo cp "$file" /var/ee &&  echo "copied"

   done