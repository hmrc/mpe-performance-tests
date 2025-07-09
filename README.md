# Members Protect and Enhancement Performance tests.

# Smoke test
It might be useful to try the journey with one user to check that everything works fine before running the full performance test
./mpe-smoke-test.sh

# Run the performance test
./mpe-performance-test.sh

To run a full performance test against staging environment, implement a job builder and run the test only from Jenkins.

# Scalafmt
This repository uses Scalafmt, a code formatter for Scala. The formatting rules configured for this repository are defined within .scalafmt.conf.

To apply formatting to this repository using the configured rules in .scalafmt.conf execute:

sbt scalafmtAll
To check files have been formatted as expected execute:

sbt scalafmtCheckAll scalafmtSbtCheck
Visit the official Scalafmt documentation to view a complete list of tasks which can be run.

# Logging
The template uses logback.xml to configure log levels. The default log level is WARN. This can be updated to use a lower level for example TRACE to view the requests sent and responses received during the test.
