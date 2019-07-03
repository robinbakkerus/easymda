# /bin/bash
s=$(basename $(pwd))
echo $s

gradle -b ../flca.easymda.plugin/build-make-eclipse-plugin-symlinks.gradle -Parg1=$s -Pplugindir="/my-dev/ide/eclipse/mars-java/plugins/" 


