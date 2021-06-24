# Code challenge system

Requirements:

* Docker

To run the application:

1. Clone the repository
2. Go to cloned repository ```./code_challenge```.
3. Execute ```ASPNETCORE_ENVIRONMENT=docker docker-compose up -d```

Access the frontend by going to ```http://localhost:3000```

To access the API access token needs to be retreived.

1. Do ```curl -d "client_id=robot-framework&client_secret=secret&scope=challengeapi&grant_type=client_credentials" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:5000/connect/token```

#### GET Requests

* Do  ```curl -H "Authorization: Bearer {access_token}" http://localhost:8080/api/v1/account``` to return a list of all accounts.  
  
* Do  ```curl -H "Authorization: Bearer {access_token}" http://localhost:8080/api/v1/account/{id}``` to return account with specified id.  

#### POST Requests

* Do  ```curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer {access_token}"  -d '{"id":1,"name":"dsadasd","phone":999999999, "email":"test@test","address":"test","country":"test","department":"test"}' http://localhost:8080/api/v1/account``` to add account.

#### PUT Requests

* Do  ```curl -X PUT -H "Content-Type: application/json" -H "Authorization: Bearer {access_token}" -d '{"id":1,"name":"dsadasd","phone":999999999, "email":"test@test","address":"test","country":"test","department":"test"}' http://localhost:8080/api/v1/account/{id}``` to update the account with id.
  
#### DELETE Requests

* Do  ```curl -X DELETE -H "Authorization: Bearer {access_token}" http://localhost:8080/api/v1/account/{id}``` to delete account with id.

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
      - name: Build and run code challenge system - "Run system"
        run: ASPNETCORE_ENVIRONMENT=selenium REACT_APP_ENV=selenium docker-compose up -d
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
