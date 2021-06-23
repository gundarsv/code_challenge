*** Settings ***
Library                     RequestsLibrary

*** Variables ***
${CHALLENGE_API_URL}        %{CHALLENGE_API_URL}
${IDENTITY_SERVER_URL}      %{IDENTITY_SERVER_URL}
${CLIENT_ID}                %{CLIENT_ID}
${CLIENT_SECRET}            %{CLIENT_SECRET}
${SCOPE}                    %{SCOPE}
${GRANT_TYPE}               %{GRANT_TYPE}

*** Test Cases ***
Auth
    &{data}=                Create Dictionary    client_id=${CLIENT_ID}     client_secret=${CLIENT_SECRET}    scope=${SCOPE}    grant_type=${GRANT_TYPE} 
    &{headers}=             Create Dictionary    Content-Type=application/x-www-form-urlencoded
    ${response}=            POST  ${CHALLENGE_API_URL}  /connect/token  data=${data}    headers=${headers}
    Log                     ${response}