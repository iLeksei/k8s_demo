The main idea of current sample project 
is to show how we can manage different services inside docker containers with k8s.

project structure:

                                          USERS
                                           ||
                                           \/
     ------------------------------------INGRESS----------------------------- k8s ----
    |                                       ||                                       |       
    |                                       \/                                       |
    |                           ___EXAMINATOR (LoadBalancer)___                      |
    |                          ||                             ||                     |
    |                          \/                             \/                     |
    |  Config-Server ->   JS-questions (1...N)     Java-questions (1...N)            |
    |_________________________________________________________________________________