#link: https://www.youtube.com/watch?v=4tSyz_v9w7Q&list=PLCy_5eq0-T5AdORm9IYUrFM5EXxsagaCU

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

## IMPORTANT: ##
### don't use underscore _ in docker image name, 
### because it's a source of troubles for http queries  ###