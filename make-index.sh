#! /bin/bash
set -eux

cd src/main/webapp

function title {
    P=$1
    cat $P | grep '<title>' | sed "s/<[^>]*>//g"
}

function printIndex {
    PAGES=$(ls *.html | grep -v index)
    echo "<ul>"
    for P in ${PAGES}; do
        echo "<li><a href=\"$P\">$(title $P)</a></li>"
    done
    echo "</ul>"
}

BEGIN='<!-- BEGIN -->'
END='<!-- END -->'

SUPPRESS=0

cat index.html | while read L ; do
    if [ $(echo "$L "| grep -c "$END") = 1 ] ; then
        SUPPRESS=0
    fi
    if [ $SUPPRESS = 0 ] ; then
        echo $L
    fi
    if [ $(echo "$L "| grep -c "$BEGIN") = 1 ] ; then
        printIndex
        SUPPRESS=1
    fi
done > index.1.html

mv index.1.html index.html

# dunno where this file comes from
rm -f 0
