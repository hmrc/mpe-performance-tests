# mpe-performance-tests (members protection enhancements)

## Overview
Performance tests for the members-protection-enhancements-frontend service

## Running the services

In order to have the right services started using service manager 2 you will need to use the following command:
```
sm2 --start MPE_ALL
```

## Smoke test

It may be useful to try the journey with one user to check that everything works fine before running the full performance test
To run the smoke test locally:

```
./mpe-smoke-test.sh
```

## Full load test

To run the full load test locally:

```
./mpe-performance-test.sh
```

## Licence

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
