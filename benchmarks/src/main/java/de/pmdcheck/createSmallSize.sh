mkdir sizeable
ls *.java | while read line
do
		hasSize=$(cat $line | grep "int size = ")
		if [ ! -z "$hasSize" ]
		then
			echo $line
			cat $line | sed "s/int size = 100/int size = 2/g" \
				| sed "s/package de.pmdcheck;/package de.pmdcheck.sizeable;/g" \
				> sizeable/$line
		fi
done
