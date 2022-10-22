# jkube-spring-boot-microservice

  1.first clear or delete old images
  then 
  
  2.pull the image from docker
  demob applocation and demoa application
  
   command : docker pull  venugopalmg/demobapp
  
   command :  docker pull  venugopalmg/demoaapp
   
  3. the go to each project inside  then perform below commands
  
    gradle k8sResource
    gradle k8sApply
	
	
  4.	then 
	demoa microservice to demob microservice below curl command
	a. curl   -X POST   http://localhost:8082/demoa/get_user 
	
	then if you want check logs the 
	
	kubectl logs demobpod id.
      
      b. only demob microservice
curl -X POST -v -d '{"username": "slinkydeveloper", "firstName": "Francesco", "lastName": "Guardiani", "age": 23}' -H'Content-type: application/json' -H'Ce-id: 1' -H'Ce-source: cloud-event-example' -H'Ce-type: fss.myapplication' -H'Ce-specversion: 1.0' http://localhost:8080/demob/send_user
