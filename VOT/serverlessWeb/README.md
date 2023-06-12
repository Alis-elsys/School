# AWS web application
  
This repository contains a web application that's designed to experiment with AWS lambda functions. I choose AWS since it allows its users to focus on building the core functionality of their application while AWS manages the underlying infrastructure, scalability, and security.

## Description

The goal of project was to gain a deeper understanding of AWS lambda functions and their potential use cases. The project is a simple serverless Python application in which the user can calculate amy power numbers. For this aim I have used five different AWS services: Amplify, Lambda, IAM, API Gateway and DynamoDB. 

## The steps I go throuth

First I created and hosted my website. I did this with the help of the Ampify server. It deploy my website.
Next I created my Lambda function on the Lambda service given by AWS. This is what actually is making the application serverless.
After that I used API Gateway for the public endpoint. This is important cause without it the user will need to write on the AWS console and run it. So the API Gateway enables me to create, publish, secure, and manage APIs (Application Programming Interfaces) for the application. It acts as a gateway or proxy between the web and the backend services, allowing me to expose and manage my APIs easily.
In my application the result is stored in a data base(it's not nesesary in this example, by that is done just to show how it works). For the data base I'm using the DynamoDB. It is a NoSQL database service, which is designed to provide fast and predictable performance with seamless scalability. DynamoDB is used for storing and retrieving structured data, and it offers a flexible and scalable solution for applications that require low-latency and high-throughput database operations.
And for all of that I need to gave some permissions to the Lambda function and to the data base, so the last service that I had used is the IAM. It allows me to 
change and add permisions to roles, groups and various AWS services and resources. 

## How it works

![archi](https://github.com/Alis-elsys/School/assets/71932909/90947d5f-c2f9-426a-ac76-7c7cea9a70d5)
