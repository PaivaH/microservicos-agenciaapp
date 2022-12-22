# Projeto de microserviços em Java com Spring Cloud
## Projeto final da disciplina - Arquitetura de Microsserviços Java - Instituto Infnet

Para rodar aplicação, deve primeiramente iniciar o container, e em seguindo iniciar as aplicações na seguinte ordem

* server - Eureka Server
* gateway - Spring Gateway
* cadastro-clinica - Micro serviço do Crud de clinicas, profissionais e criação de agendas de profissionais
* agendamento - Micro serviço do Crud para paciente e agendamento de consultas pelos pacientes

O projeto possui um diretório com as coletctions do Postman e Swagger UI
Para ver a documentação basta acessar:

agendamento - [localhost:9090/swagger-ui/index.html](localhost:9090/swagger-ui/index.html)  
cadastro-clinica - [localhost:9095/swagger-ui/index.html](localhost:9095/swagger-ui/index.html)  