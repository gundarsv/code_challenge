*** Settings ***
Library                           RequestsLibrary
Library                           String
Suite Setup                       Get access token
Test Setup                        Create Session    challenge_api   ${CHALLENGE_API_URL}

*** Variables ***
${CHALLENGE_API_URL}              %{CHALLENGE_API_URL}
${IDENTITY_SERVER_URL}            %{IDENTITY_SERVER_URL}
${CLIENT_ID}                      %{CLIENT_ID}
${CLIENT_SECRET}                  %{CLIENT_SECRET}
${SCOPE}                          %{SCOPE}
${GRANT_TYPE}                     %{GRANT_TYPE}

*** Keywords ***
Get access token
    Create Session                identity_session    ${IDENTITY_SERVER_URL}
    &{data}=                      Create Dictionary   client_id=${CLIENT_ID}     client_secret=${CLIENT_SECRET}    scope=${SCOPE}    grant_type=${GRANT_TYPE} 
    &{headers}=                   Create Dictionary   Content-Type=application/x-www-form-urlencoded
    ${response}=                  POST On Session     identity_session    /connect/token      data=${data}    headers=${headers}
    Status Should Be              200   ${response}
    Set Suite Variable	          ${ACCESS_TOKEN}   Bearer ${response.json()['access_token']}

*** Test Cases ***
Create account
    ${id}                         Generate random string    4   0123456789
    &{data}=                      Create dictionary     id=${id}    name=Generic Name    phone=999999999    email=genericname@company.com   address=Generic Street 42 Earth     country=Navarro     department=T21R
    &{headers}=                   Create Dictionary     Authorization=${ACCESS_TOKEN}
    ${response}=                  POST On Session   challenge_api  /api/v1/account  json=${data}    headers=${headers}  expected_status=anything                                                                                                      
    Status Should Be              201   ${response}
    Set Suite Variable	          ${ID}   ${id}

Invalid create account
    ${id}                         Generate random string    4   0123456789
    &{data}=                      Create dictionary     id=${id}    name=Generic Name    phone=9999999999999    email=genericname@company.com   address=Generic Street 42 Earth     country=Navarro     department=T21R
    &{headers}=                   Create Dictionary     Authorization=${ACCESS_TOKEN}
    ${response}=                  POST On Session   challenge_api  /api/v1/account  json=${data}    headers=${headers}  expected_status=anything                                                                                                      
    Status Should Be              400   ${response}

Update account
    &{data}=                      Create dictionary     id=${ID}    name=Generic Name    phone=222222222    email=genericname@company.com   address=Generic Street 42 Earth     country=Navarro     department=T21R
    &{headers}=                   Create Dictionary     Authorization=${ACCESS_TOKEN}
    ${response}=                  PUT On Session   challenge_api  /api/v1/account/${ID}  json=${data}    headers=${headers}  expected_status=anything                                                                                                      
    Status Should Be              200   ${response}

Delete account
    &{headers}=                   Create Dictionary     Authorization=${ACCESS_TOKEN}
    ${response}=                  Delete On Session   challenge_api  /api/v1/account/${ID}  headers=${headers}  expected_status=anything                                                                                                      
    Status Should Be              200   ${response}