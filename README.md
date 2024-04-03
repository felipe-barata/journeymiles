### Alura Backend Challenge 7th Edition

#### About this project

<p>
This project is a practice based on Alura's challenge, which consists of creating a travel recommendation system.
</p>  
<p>
More information about this chalenge can be found in the link bellow
</p>  

[Alura Backend Challenge 7th Edition](https://www.alura.com.br/challenges/back-end-7)

#### Motivation

<p>
I decided to practice with this project to remember some basic concepts of development with Kotlin and Spring, since we rarely create any project from scratch in work environments. We always take something already in progress or follow some company model/standard.  
So the idea is to take something from scratch and try to apply the best development practices and, of course, always improve my development skills.
</p>  

<p>
Since this is just for the studying and learning new stuff, I took some design decisions regarding dealing with images that maybe could be different on a production environment.
</p>
<p>
I will separate the creation of users and destinations from the upload of images, to have more domains and practice DDD, and also to, in the future, have some multiple endpoints to coordinate on a Go written BFF.
</p>
<p>
Also, in a production environment, the pictures paths could have been stored in the same database as the entities, but since I wanted to play a bit with DynamoDB, I`ll be storing them in it.
</p>

#### About the project

<p>
The goal of this project is to simulate an application to recommend travel destinations, I think the goal of the challenge was to keep it simple, but I intend on using some advanced features like storing every image on S3 buckets.
</p>
<p>
This project will be divided into three stages (the idea is to do one a week, but I can't promise anything lol):

1. Create the testimonials CRUD APIs and the endpoint to display three random testimonials
2. Create the destinies CRUD APIs and the endpoint to search for a destination using it`s name
3. Update the destinies entity, create an endpoint to search for a destination by ID and use some AI to describe the
   destination
</p>

<p>
For the purpose of studying and better clarifying how the project was developed, I created a simplified SAD (Software Architecture Document)
</p>

[SAD](/docs/sad.md)

#### Tech stack

- Kotlin development language
- SpringBoot framework
- Postgresql for the database
- S3 for storing images (Emulated through Localstack)
- DynamoDB for storing ths paths for the S3 files (Emulated through Localstack)
- Docker & soon Kubernetes