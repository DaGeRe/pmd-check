server=$1
rsync -avz \
	--include=src/*** \
	--include=***.sh \
	--include=pom.xml \
	--exclude='*' \
	. $server:workspaces/dissworkspace/jmh/
