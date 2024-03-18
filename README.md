# Vacation Pay Calculator

This project is a microservice built on Spring Boot and Java 11, 
designed to calculate vacation payouts for employees. 
It offers a single API endpoint.

# GET "/calculate"
body:
* averageSalary (required): The average salary over 12 months.
* vacationDays (required if dates is not specified): The number of vacation days.
* startDate & endDate (optional): Specific vacation dates to consider.

Response:
* Returns the total vacation payout as a double value.

How to Use:

1. Clone the repository to your local machine.
2. Ensure Java 11 is installed on your system.
3. Build the project using "mvn clean package" OR download it on releases page.
4. Run the application locally or deploy it to a server.
5. Make GET requests to the "http://localhost:8080/calculate" address with appropriate parameters to obtain vacation 
payout information.