*** Settings ***
Library                     SeleniumLibrary

*** Variables ***
${REMOTE_URL}               %{REMOTE_URL}
${BROWSER}                  ff
${ALIAS}                    None
${URL}                      https://www.google.com
${CHALLENGE_API_URL}        %{CHALLENGE_API_URL}

*** Test Cases ***
Scenario 1
    Open Browser            ${URL}    ${BROWSER}    ${ALIAS}    ${REMOTE_URL}
    Capture Page Screenshot
    Close Browser

Scenario 2
    Open Browser            ${CHALLENGE_API_URL}    ${BROWSER}    ${ALIAS}    ${REMOTE_URL}
    Capture Page Screenshot
    Close Browser