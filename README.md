## 2º DAM - AED - Activity 4 by Aleyn Coello    

#### In this activity I wanted to implement the design pattern of data access objects,  
#### for this I created the following structure:  
> * ### __dao:__
>  * ___DAO ->___ Generic interface.
>  * ___DepartmentDAO ->___ Interface that implements DAO with the Department object.
>  * ___EmployeeDAO ->___ Interface that implements DAO with the Employee object.
>  * ___DAOManager ->___ Interface to handle the different DAO objects.
>  * ___DAOException ->___ Custom exception for DAO objects.  
> * ### __dao.oracle11g:__
>  * ___Ora11gDepartmentDAO ->___ Class that implements the DepartmentDAO interface.
>  * ___Ora11gEmployeeDAO ->___ Class that implements the EmployeeDAO interface.
>  * ___Oracle11gDAOManager:___  
: >       Class that implements the DAOManager interface, in this class is the
>       connection and serves to abstract the operation of the oracle11g CRUD.
