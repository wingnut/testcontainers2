# testcontainers2
Testing using Oracle XE in a testcontainer

Git clone this repo and run the poc.OracleTest

Now, the OracleTest will likely fail the first time since it will try to pull a HUUUGE Oracle XE pre-built image.

So perhaps best to do a
```docker pull wingnut/oracle-database:18.4.0-xe-prebuilt```
separately first.

### DEPRECATED
This repo has been copied (complete with git history) to https://github.com/Troperty/oracle-testcontainers where the real work should continue.
But feel free to fork this repo to continue to lab outside the Troperty realm.
