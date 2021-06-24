*** Settings ***
Library                       SeleniumLibrary
Library                       RequestsLibrary
Library                       String
Suite Setup                   Create account
Test Timeout                  10

*** Variables ***
${REMOTE_URL}                 %{REMOTE_URL}
${BROWSER}                    ff
${ALIAS}                      None
${CHALLENGE_WEB_URL}          %{CHALLENGE_WEB_URL}
${CHALLENGE_API_URL}          %{CHALLENGE_API_URL}
${IDENTITY_SERVER_URL}        %{IDENTITY_SERVER_URL}
${CLIENT_ID}                  %{CLIENT_ID}
${CLIENT_SECRET}              %{CLIENT_SECRET}
${SCOPE}                      %{SCOPE}
${GRANT_TYPE}                 %{GRANT_TYPE}

*** Keywords ***
Get access token
    Create Session            identity_session    ${IDENTITY_SERVER_URL}
    &{data}=                  Create Dictionary   client_id=${CLIENT_ID}     client_secret=${CLIENT_SECRET}    scope=${SCOPE}    grant_type=${GRANT_TYPE} 
    &{headers}=               Create Dictionary   Content-Type=application/x-www-form-urlencoded
    ${response}=              POST On Session     identity_session    /connect/token      data=${data}    headers=${headers}
    Status Should Be          200   ${response}
    Set Suite Variable	      ${ACCESS_TOKEN}   Bearer ${response.json()['access_token']}

Create account
    Create Session            challenge_api   ${CHALLENGE_API_URL}
    Get access token
    ${id}                     Generate random string    6   0123456789
    &{data}=                  Create dictionary     id=${id}    name=Generic Name    phone=999999999    email=genericname@company.com   address=Generic Street 42 Earth     country=Navarro     department=T21R
    &{headers}=               Create Dictionary     Authorization=${ACCESS_TOKEN}
    ${response}=              POST On Session   challenge_api  /api/v1/account  json=${data}    headers=${headers}  expected_status=anything                                                                                                      
    Status Should Be          201   ${response}
    Set Suite Variable	      ${ID}   ${id}

*** Test Cases ***
Can login and see accounts
    Open Browser              ${CHALLENGE_WEB_URL}    ${BROWSER}   ${ALIAS}    ${REMOTE_URL}
    Click Element	          login-button
    Input Text                id=Username    alice
    Input Text                id=Password    alice
    Click Element	          //html/body/div[2]/div/div[2]/div/div/div[2]/form/button[1]
    Wait Until Page Contains  Alice     timeout=5
    Capture Page Screenshot   ${OUTPUTDIR}/can-login-and-see-accounts/see-alice.png
    Wait Until Page Contains  ${ID}     timeout=5
    Capture Page Screenshot   ${OUTPUTDIR}/can-login-and-see-accounts/see-id.png
    Close Browser