Closed Social Network
=====================

## Description
A generic solution to build anonym communities based on invites.
Everyone can fork this project and build his own community.

## Dependencies
Latest version of [Play Framework 2](https://github.com/playframework/Play20)

## Deployment
If you wanna deploy your application it would be a great idea to change the underlying database.
We describe here the changes which should be done to use an MySQL backend.

First of all add the jdbc connector to the project dependencies in the file `project/Build.scala`,
it should look like the following snippet
```scala
val appDependencies = Seq(
  "mysql" % "mysql-connector-java" % "5.1.20"
)
```

Then it's time to change the configuration to use the mysql database. Edit the file `conf/application.conf`
and change the database section to smthg like
```ini
#db.default.driver=org.h2.Driver
#db.default.url="jdbc:h2:mem:play"
#db.default.user=sa
#db.default.password=

db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://127.0.0.1:3306/csn"
db.default.user=csn
db.default.pass=yourSecretDatabasePassword
```

If you have problems while connecting to your database check the following things:

* Is mysqld listening on a matching ip ?
* Is mysqld listening on the matching port ?
* Are username, password and databasename correct ?
* If you use dns or hostnames instead of an ip check if they are correct interpreted. localhost could be act realy different
* Are there any firewalls which block the connection ?
* What says hosts.allow and hosts.deny ? Try to add `mysqld : ALL : ALLOW` to `/etc/hosts.allow`

And of course change the `application.secret` in `conf/application.conf` before you deploy.

## Participate

* irc://irc.freenode.net/geeksofthedusk
* [Bugreports](https://github.com/GeeksOfTheDusk/ClosedSocialNetwork/issues)

## License
Closed Social Network is distributed under [GNU General Public License v3](http://www.gnu.org/licenses/gpl.html)
