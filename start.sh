#!/usr/bin/env bash

#Gmail Settings
SMTP_SETTINGS='-Dmail.smtp.host="smtp.gmail.com"
               -Dmail.smtp.socketFactory.port="587"
               -Dmail.smtp.starttls.enable="true"
               -Dmail.smtp.starttls.required="true"
               -Dmail.smtp.ssl.protocols="TLSv1.2"
               -Dmail.smtp.auth="true"
               -Dmail.smtp.user="javaoasis@gmail.com"
               -Dmail.smtp.pass="xxxxxxx"'

java ${SMTP_SETTINGS} -jar target/email-relay-1.0-SNAPSHOT-jar-with-dependencies.jar
