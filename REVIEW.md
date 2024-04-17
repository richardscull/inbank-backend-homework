# TICKET-101 | Code Review

## Overview

This document is a summary of the code review for this project. This is a review for `backend` code only. 

For frontend code, please refer to the frontend code review document here: [Frontend Code Review](https://github.com/richardscull/inbank-frontend-homework/blob/main/REVIEW.md)

## 📃 General

- I've liked how best loan value is calculated. Intern assumes that for the best loan we need `credit score` equals to 1. So, he inverts the `(credit modifier / loan amount) * loan period) = 1` formula to get the best loan amount, `loan amount = credit modifier * loan period`. This is a good approach to solve the problem. 
- The code mostly follows the best practices (SRP, DRY, KISS) and is well-structured.
- Test coverage is good. Intern has written tests for `DecisionEngine` class and covered endpoints with tests. 
- Each function has a clear purpose and is well-named. They also has `Javadoc` comments which is a good practice.
- Intern used `DecisionEngineConstants` class to store the constants. This is a good practice to help with the readability and magic numbers.


## 📈 Suggestions

- I've written it in the frontend review as well, but I think it's worth mentioning here too. Intern should split his commits into smaller ones. This way, it's easier to review the code and understand the changes.
- Renaming `DecisionEngine` to `LoanDecisionService` would be more appropriate. It's more clear what the class does. 
- Use `BigDecimal` for `loanAmount` and `loanPeriod` instead of `double`. `BigDecimal` is more precise and can avoid rounding errors. Double is not recommended for financial calculations.

## 🎭 Shortcomings 

- `requestDecision()` method for "POST /decision" endpoint had multiple `setLoadAmount(null)` and `setLoanPeriod(null)` calls for each exception. I've added a new method `handleException()` for this purpose. It automatically sets `loanAmount` and `loanPeriod` to `null` and sets required status code. This way, we can avoid code duplication and make the code more readable.
- Condition for checking `loanAmount` and `loanPeriod` if they are in limits was hard to read. I've reversed the condition from `if (!(... <= ...))` to `if (... > ...)`. This way we can avoid double negation and make the code more readable.
- New class called `Validator` was introduced by me for better checks in `DecisionEngine`. Multiple checks were split into separate methods and now used in `DecisionEngine` class. It helps `DecisionEngine` follow `SRP` principle better.
- I will call it a more nitpicking, but I've changed imports for each exception to `exceptions.*`. Helps to keep the code clean and more readable for me.

## 📝 Conclusion 

The task was completed successfully. Intern has shown good understanding of the task and has implemented the solution in a good way. There are some minor improvements that can be made, but overall the code is well-structured and easy to read. Well done! 👏