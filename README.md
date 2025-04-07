# Courses API

This API includes some endpoints where user can storage data for courses and students.

## Endpoints:

### Get a list of all students
GET: /api/students: 
### Get a specific student by ID
GET: /api/students/{id}
### Create a new student (with courses)
POST: /api/students	
### Update an existing student
PUT: /api/students/{id}	
### Delete a student by ID
DELETE: /api/students/{id}	

### Get a list of all courses
GET: /api/courses	
### Get a specific course by code
GET: /api/courses/{code} 
### Create a new course
POST: /api/courses	
### Update an existing course
PUT: /api/courses/{code}
### Delete a course by code
DELETE: /api/courses/{code}	
