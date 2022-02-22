# Viktoria_Cseke_Project1
![app1](https://user-images.githubusercontent.com/44726497/155121433-8c553422-a7c8-487b-a5cc-4294a904d0ca.PNG)


# Project 1 Brief

This project is designed for you to apply all that you have learned so far to a project.

You are not allowed to use Spring Boot. You are not allowed to use JPA.

Specification
A landlord has several properties.

The landlord records the following data about each property:

* Eircode
* Capacity of property i.e. number of tenants it can hold
* Number of tenants currently renting this property
* Cost of rental per tenant per calendar month

The landlord stores the following information about each tenant:
* Name
* Email
*Phone number

The landlord wishes you to develop a command-line menu system (not web) with the following menu of options:
Search for a house by Eircode, listing the details of the household including all tenants
* View a list of houses
* View a list of houses with space in them 
* Add a new house (ensuring unique Eircode)
* Add a new tenant and assign that tenant to a household (subject to capacity)
* Move a person from one household to another (subject to capacity)
* Delete a household, along with its occupants
* Delete a tenant, and remove them from the house in which they reside (reduce the number of occupants)
* Display some statistics*
  * the average number of tenants per household
  * the total income from rental properties
  * the number of houses with no space i.e. have reached capacity
* Quit
No security is needed.

You must write 4 unit tests, 2 for the repository layer and 2 for the service layer.

Refer to the rubric for expected standards.

* STATISTICS --> Use SQL queries here - don't just retrieve lists and do the work in Java as I have seen done in the past - SQL has queries for these. 
