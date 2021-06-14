*** Settings ***
Library     SeleniumLibrary

*** Variables ***
${REMOTE_URL}               127.0.0.1:4444/wd/hub 
${BROWSER}                  chrome
${ALIAS}                    None
${URL}                      https://www.google.com

*** Test Cases ***
Scenario 1 
    Open Browser    ${URL}    ${BROWSER}    ${ALIAS}    ${REMOTE_URL}
    Capture Page Screenshot
    Close Browser