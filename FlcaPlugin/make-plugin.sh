#!/bin/bash
#
# DONT FORGET TO CHANGE V1 !!
# RUN THIS CMD IN flca.easymda.plugin
#
V1=2.0

EXT_M3=ext_m3_repo
FLCA_M3=flca_m3_repo
TARGETDIR=/home/robin/temp/easymda_targets
TARGETNAME=easymda-reactive-$V1
TODIR=$TARGETDIR/$TARGETNAME
M3_REPO=/my-dev/maven/m3-repo
TO_DEMOLIBS=$TARGETDIR/demolib
TO_DROPINS=$TODIR/eclipse/dropins/flca.easymda.generator_$V1
TO_DROPINS_CARTS=$TO_DROPINS/cartridges

echo "TODIR=$TODIR"
mkdir $TO_DROPINS_CARTS -p
ls $TODIR

doGradle() {
	gradle -b ../flca.mda.generator/build.gradle s
}

copyJavadoc() {
  echo "../$1/target/docs/javadoc/  $TODIR/doc/javadocs/$1"
  cp -rf ../$1/target/docs/javadoc/  $TODIR/doc/javadocs/$1
}

copyFlcaJars() {
  #  mkdir -p $TODIR/$FLCA_M3/$1 -p
  #  mkdir -p $TODIR/$EXT_M3/$1 -p
  #  cp -v $M3_REPO/$1/$2  $TODIR/$FLCA_M3/$1
  echo ""
}

copyFlcaJarsToDropinLib() {
  # remove the symlinks to the maven jars and replace with the real jar files.
  echo "*** cp $M3_REPO/$1/$2 $TO_DROPINS/lib -v"
  rm $TO_DROPINS/lib/$2 -v
  cp $M3_REPO/$1/$2 $TO_DROPINS/lib -v
  echo ""
}


copyGenatorRuntimeToDropin() {
  echo "copy generator files -> $TO_DROPINS"
  cp -rf ../flca.mda.generator/bin/com  $TO_DROPINS 
  cp -rf ../flca.mda.generator/bin/config  $TO_DROPINS 
  cp -rf ../flca.mda.generator/META-INF  $TO_DROPINS 
  cp -rf ../flca.mda.generator/lib  $TO_DROPINS 
  cp ../flca.mda.generator/plugin.xml  $TO_DROPINS 
  cp ../flca.mda.generator/bin/*.xml  $TO_DROPINS 
}

# copy files from m3-repo to target cartridges folder
copyM3RepoToDropinCartridges() {
  echo "copy files $M3_REPO/$1/$2 -> $TO_DROPINS_CARTS"
  cp -v $M3_REPO/$1/$2  $TO_DROPINS_CARTS 
}

# copy project zip files to target cartridges folder
copyProjectZipToDropinCartridges() {
  echo "copy project zipfiles ../flca.mda.generator/cartridges/$1 -> $TO_DROPINS_CARTS"
  cp -v ../flca.mda.generator/cartridges/$1  $TO_DROPINS_CARTS 
}


copyToEasyMdaLib() {
  mkdir $TO_DEMOLIBS -p
  cp -v $M3_REPO/$1/$2  $TO_DEMOLIBS
}

makeQuickstartZip() {
	cd $TARGETDIR
	zip -r $TARGETNAME.zip 	$TARGETNAME > /home/robin/temp/zip.out
}

makeM3RepoZips() {
	cd $TARGETDIR
	mv $EXT_M3 m3_repo
	zip -r $EXT_M3-$V1.zip	m3_repo
	mv m3_repo $EXT_M3
	
	mv $FLCA_M3 m3_repo 
	zip -r $FLCA_M3-$V1.zip 	m3_repo
	mv m3_repo $FLCA_M3 
}

copySamples() {
	mkdir -p $TODIR/samples/
 	cp -rf easymda-plugin-resources/flca.mda.test.model.reactive $TODIR/samples 
 	cp $M3_REPO/easymda/easymda.cartridge.reactive/$V1/easymda.cartridge.reactive-$V1.jar $TODIR/samples/flca.mda.test.model.reactive/lib
 	cp $M3_REPO/easymda/easymda.common.api/$V1/easymda.common.api-$V1.jar $TODIR/samples/flca.mda.test.model.reactive/lib
}

copyReadMe() {
 	cp docs/README $TODIR/ -v
}

#--------- main ---------
#

# doGradle

mkdir -p $TODIR/doc/javadocs/
mkdir -p $TODIR/samples/
cp -v docs/*$V1.pdf $TODIR/doc

copyJavadoc flca.mda.common.api
#copyJavadoc flca.frw.common
#copyJavadoc flca.frw.backend
#copyJavadoc flca.mda.cartridge.webapp
#copyJavadoc flca.mda.generator
copyJavadoc flca.mda.cartridge.reactive

copyFlcaJars easymda/easymda.common.api/$V1	easymda.common.api-$V1.jar
#copyFlcaJars easymda/easymda.cartridge.template/$V1	easymda.cartridge.template-$V1.jar
#copyFlcaJars easymda/easymda.cartridge.webapp/$V1	easymda.cartridge.webapp-$V1.jar
#copyFlcaJars easymda/easymda.cartridge.wizzard/$V1	easymda.cartridge.wizzard-$V1.jar
copyFlcaJars easymda/easymda.cartridge.reactive/$V1	easymda.cartridge.reactive-$V1.jar
#copyFlcaJars flca/FlcaFrwBackend/$V1		FlcaFrwBackend-$V1.jar
#copyFlcaJars flca/FlcaFrwBackendJpa/$V1		FlcaFrwBackendJpa-$V1.jar
#copyFlcaJars flca/FlcaFrwCommon/$V1		FlcaFrwCommon-$V1.jar

#copyM3RepoToDropinCartridges easymda/easymda.cartridge.template/$V1	easymda.cartridge.template-$V1.jar
#copyM3RepoToDropinCartridges easymda/easymda.cartridge.webapp/$V1	easymda.cartridge.webapp-$V1.jar
#copyM3RepoToDropinCartridges easymda/easymda.cartridge.wizzard/$V1	easymda.cartridge.wizzard-$V1.jar
copyM3RepoToDropinCartridges easymda/easymda.cartridge.reactive/$V1	easymda.cartridge.reactive-$V1.jar
cp -v ../flca.mda.generator/cartridges/example.link $TO_DROPINS_CARTS
copyGenatorRuntimeToDropin	

copyProjectZipToDropinCartridges dart_project.zip
copyProjectZipToDropinCartridges scala_project.zip


# copyToEasyMdaLib easymda/easymda.common.api/$V1 		easymda.common.api-$V1.jar
#copyToEasyMdaLib easymda/easymda.cartridge.webapp/$V1	easymda.cartridge.webapp-$V1.jar
#copyToEasyMdaLib easymda/easymda.cartridge.wizzard/$V1	easymda.cartridge.wizzard-$V1.jar
#copyToEasyMdaLib easymda/easymda.cartridge.template/$V1	easymda.cartridge.template-$V1.jar
# copyToEasyMdaLib easymda/easymda.cartridge.reactive/$V1	easymda.cartridge.reactive-$V1.jar

copySamples 
copyReadMe

copyFlcaJarsToDropinLib easymda/easymda.common.api/$V1	easymda.common.api-$V1.jar

makeQuickstartZip
#makeM3RepoZips 


