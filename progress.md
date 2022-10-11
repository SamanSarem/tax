I have changed Vehicle to class and remove Car and Motorbike to become easy to
"Open-closed Principle"
The scenario did not give mie the possible vehicles to use enum so i went for the string
add a CongestionTaxCalculatorService to divide responsibilities to appropriate classes
add Tax Exempt vehicles to config and change meaningless map to set
add tax rates to the config to use it later
make CongestionTaxCalculator a component for test. may I should make some methods public.
refactor GetTollFee to getTollFee bad name
create test class for tax calculator
It was better to use LocalDateTime instead of Date, but I'll keep it to not rewrite codes.
add public holidays to the config file
I found that the previous code really messy, so I change the implementation.

