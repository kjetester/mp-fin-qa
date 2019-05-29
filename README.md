## mp-fin-qa AT Functional test framework

# Test Cases:
1. Registration with deposit link
    1. Registration Page
        1. check if page opens
        2. fill and submit form
    2. ESIA Access Granting Page
        1. check if page opens
        2. submitForm
    3. ESIA Log In Page
        1. check if page opens
        2. fill and submit form
    4. Questionnaire page
        1. check if page opens
        2. check static fields
        3. fill and submit form
    5. Banks Setting Up Page
        1. check if page opens
        2. submit form
    6. Identification Page
        1. check if page opens
        2. skip process
    7. Deposit Conditions Page
        1. check if page opens
    8. navigate to profile via header
        1. check if page opens
        2. verify static fields
2. Opening a deposit
    1. navigate to product cart via header
        1. check if page opens
        2. verify quantity of produces is equal to 1
        3. apply for deposit
    2. Deposit Conditions Page
        1. check if page opens
        2. set amount = 9999.99 (disabled due MPFIN-1593)
        3. setDuration = 365 (disabled due MPFIN-1593)
        4. send contract to bank
    3. Wait For Response From Bank Page
        1. check if page opens
        2. go to dashboard
    4. DashboardPage
        1. check if page opens
        2. check contract status = WAITING_FOR_FUNDS

## Start-up

* `mvn clean test`

## Reporting

* TestNG report `target/surefire-reports`
* Screenshots of failures `target/screenshots`
