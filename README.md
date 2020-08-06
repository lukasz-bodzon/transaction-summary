# transaction-summary
Sample service for a per-client transaction summary

# Assumptions
* All transactions per user are done in a single currency
* Balance and transaction dates are all in the past
* Both comma and dot are acceptable as a decimal place separator
* All transaction dates are after balance date
* Total turnover is calculated as total expenditures subtracted from total income
* No security is required
* It's not required to arrange response JSON elements in any particular order