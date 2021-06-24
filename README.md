# Code challenge system

## System structure

``` bash
├───.github
│   └───workflows
├───challenge_api - "API for Accounts"
│   ├───.mvn
│   │   └───wrapper
│   └───src
│       ├───main
│       │   ├───java
│       │   │   └───com
│       │   │       └───code
│       │   │           └───challenge
│       │   │               ├───domain - "Business Layer"
│       │   │               │   ├───model
│       │   │               │   └───service
│       │   │               ├───infrastructure
│       │   │               │   └───exception
│       │   │               ├───persistence - "Data access layer"
│       │   │               │   └───repository
│       │   │               └───presentation - "Presentation layer"
│       │   │                   ├───controller
│       │   │                   └───dtos
│       │   └───resources
│       └───test
│           └───java
│               └───com
│                   └───code
│                       └───challenge
├───challenge_identity - "Identity server"
│   └───challenge_identity
│       ├───Controllers
│       ├───Infrasctructure
│       │   └───Attributes
│       ├───Models
│       ├───Properties
│       ├───ViewModels
│       │   ├───Account
│       │   ├───Consent
│       │   ├───Device
│       │   ├───Diagnostics
│       │   ├───Grants
│       │   └───Home
│       ├───Views
│       │   ├───Account
│       │   ├───Consent
│       │   ├───Device
│       │   ├───Diagnostics
│       │   ├───Grants
│       │   ├───Home
│       │   └───Shared
│       └───wwwroot
│           ├───css
│           └───js
├───challenge_web - "React frontend"
│   ├───public
│   └───src
│       ├───components
│       ├───pages
│       ├───services
│       ├───slices
│       └───utils
└───integration_tests - "Integration tests"
    ├───scripts
    └───suites - "Test suites"
```

## Github actions flow

``` bash
steps:
      - uses: actions/checkout@v1
      - name: Change wrapper permissions
        run: chmod +x ./challenge_api/mvnw
      - name: Build and run code challenge system - "Run system docker-compose"
        run: docker-compose -f docker-compose.yml up -d
      - name: Check running containers
        run: docker ps -a
      - name: Run Selenium hub - "Run selenium hub with all nodes"
        run: docker-compose -f docker-compose-selenium-hub.yml up -d 
      - name: Check running containers
        run: docker ps -a
      - name: Change script permissions
        run: chmod +x ./integration_tests/scripts/run_tests.sh
      - name: Run Integration tests - "Run integration tests"
        run: docker-compose -f docker-compose-tests.yml up
        continue-on-error: true
      - name: Archive test results - "Gather test data"
        uses: actions/upload-artifact@v2
        with:
          name: test-results
          path: ./integration_tests/reports
```
