# Prerequisites for Tanaguru web application installation

## Open JDK

You need to have a JDK and JRE installed. openjdk-8-jre and openjdk-8-jdk are suggested.

Install the following packages
```sh
sudo aptitude install openjdk-8-jre
sudo update-java-alternatives -s java-1.8.0-openjdk
```

## Mysql

### Installation

Install the following packages
```sh
sudo aptitude install mysql-server-5.7 libmysql-java
```

### Configuration

Edit the my.cnf mysql configuration file.
```sh
sudo vi /etc/mysql/my.cnf
```

Set the max_allowed_packet option to 64M (default is 16M)
```sh
max_allowed_packet = 64M
```

Restart mysql service
```sh
sudo service mysql restart
```

## Tomcat 

Install the following packages :
```sh
sudo aptitude install tomcat8 libspring-instrument-java
```

Create the following symlinks : 
```sh
sudo ln -s /usr/share/java/spring3-instrument-tomcat.jar /usr/share/tomcat8/lib/spring3-instrument-tomcat.jar
sudo ln -s /usr/share/java/mysql-connector-java.jar /usr/share/tomcat8/lib/mysql-connector-java.jar
```

## Xvfb

Install the following packages
```sh
sudo aptitude install xvfb
```

Create the startup script in /etc/init.d/xvfb
```sh
sudo touch /etc/init.d/xvfb
```

Add the following content to the xvfb startup script. 
```sh
#!/bin/sh

set -e

RUN_AS_USER=tomcat8
OPTS=":99 -screen 1 1024x768x24 -nolisten tcp"
XVFB_DIR=/usr/bin
PIDFILE=/var/run/xvfb

case $1 in

start)
    start-stop-daemon --chuid $RUN_AS_USER -b --start --exec $XVFB_DIR/Xvfb --make-pidfile --pidfile $PIDFILE -- $OPTS &
;;

stop)
    start-stop-daemon --stop --user $RUN_AS_USER --pidfile $PIDFILE
    rm -f $PIDFILE
;;

restart)
    if start-stop-daemon --test --stop --user $RUN_AS_USER --pidfile $PIDFILE >/dev/null; then
        $0 stop
    fi;
    $0 start
;;

*)
    echo "Usage: $0 (start|restart|stop)"
    exit 1
;;

esac

exit 0
```

start Xvfb
```sh
sudo chmod +x /etc/init.d/xvfb
sudo /etc/init.d/xvfb start
```

Configure Xvfb to run at startup
```sh
sudo update-rc.d xvfb defaults
```

## Firefox

### For 32-bit architecture
Retrieve the [lastest version of firefox](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/61.0/linux-i686/en-US/firefox-61.0.tar.bz2).
Install it on your file system and make sure the binary is executable for the tomcat8 user.
```sh
cd /opt
sudo wget http://download-origin.cdn.mozilla.net/pub/firefox/releases/61.0/linux-i686/en-US/firefox-61.0.tar.bz2
sudo tar xvfj firefox-61.0.tar.bz2
sudo mv firefox firefox-61.0
sudo ln -s firefox-61.0 firefox
```

### For 64-bit architecture
Retrieve the [lastest version of firefox](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/61.0/linux-x86_64/en-US/firefox-61.0.tar.bz2).
Install it on your file system and make sure the binary is executable for the tomcat8 user.
```sh
cd /opt
sudo wgethttp://download-origin.cdn.mozilla.net/pub/firefox/releases/61.0/linux-x86_64/en-US/firefox-61.0.tar.bz2
sudo tar xvfj firefox-61.0.tar.bz2
sudo mv firefox firefox-61.0
sudo ln -s firefox-61.0 firefox
```

## Geckodriver
### For 32-bit architecture
Retrieve the [lastest version of geckodriver](https://github.com/mozilla/geckodriver/releases/download/v0.21.0/geckodriver-v0.21.0-linux32.tar.gz).
Install it on your file system and make sure the binary is executable for the user that will perform the analysis (jenkins for instance)
```sh
cd /opt
sudo wget https://github.com/mozilla/geckodriver/releases/download/v0.21.0/geckodriver-v0.21.0-linux32.tar.gz
sudo tar xvfj geckodriver-v0.21.0-linux32.tar.gz
```

### For 64-bit architecture
Retrieve the [lastest version of geckodriver](https://github.com/mozilla/geckodriver/releases/download/v0.21.0/geckodriver-v0.21.0-linux64.tar.gz).
Install it on your file system and make sure the binary is executable for the user that will perform the analysis (jenkins for instance)
```sh
cd /opt
sudo wget https://github.com/mozilla/geckodriver/releases/download/v0.21.0/geckodriver-v0.21.0-linux64.tar.gz
sudo tar xvfj geckodriver-v0.21.0-linux64.tar.gz
```

## Mail SMTP

Install the following packages
```sh
sudo aptitude install postfix mailutils
sudo dpkg-reconfigure postfix
```
Once the configuration is displayed, options are :

* configuration type: satellite
* SMTP relay: &lt;none&gt; (this is the trick, don't type anything here)

## W3c validator
```
cd /opt
sudo wget https://github.com/validator/validator/releases/download/18.11.5/vnu.jar_18.11.5.zip
sudo unzip vnu.jar_*.zip -d w3cValidator
sudo mv ./w3cValidator/dist/vnu.jar ./w3cValidator/
sudo chmod +x ./w3cValidator/vnu.jar
```