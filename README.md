# testcontainers2
Testing using Oracle XE in a testcontainer

Git clone this repo and run the poc.OracleTest

Now, the OracleTest will likely fail the first time since it will try to pull a HUUUGE Oracle XE pre-built image.

So perhaps best to do a
```docker pull wingnut/oracle-database:18.4.0-xe-prebuilt```
separately first.
