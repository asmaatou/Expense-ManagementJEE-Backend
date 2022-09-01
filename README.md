#  Expense-ManagementJEE-Backend
 
Authentification avec Spring Security et JWT (Jason Web Token) :

Dans cette partie du projet, l’utilisateur aura la possibilité de crée un compte, s’identifier avec son nom d’utilisateur et mot de passe crypté, affecter à chaque utilisateur son rôle (Admin, Manager, Employé) afin d’avoir l’autorisation d’accéder aux ressources. 

Le diagramme suivant présente comment nous mettons en œuvre le processus d’inscription, d’ouverture de session et d’autorisation des utilisateurs.

 ![image](https://user-images.githubusercontent.com/83168701/187952020-eab251a9-6de6-4115-92f4-b25865710dbe.png)
 
JWT légal doit être ajouté à l’en-tête d’autorisation http si le client aède à des ressources protégées.
On doit implémenter le jeton d’actualisation : 

 ![image](https://user-images.githubusercontent.com/83168701/187952117-95f027a4-eeb8-4ba3-9597-08700ba18626.png)

L’architecture de Spring Boot Server :

Ci-dessous on peut voir un aperçu de notre Spring Boot Server : 

![image](https://user-images.githubusercontent.com/83168701/187952202-51b1fc8e-131c-49f4-bb1e-1009c7d114d7.png)

Explication :

- WebSecurityConfigurerAdapter : est au cœur de notre mise en œuvre de la sécurité. il fournit des configurations HttpSecurity pour configurer cors, csrf, la gestion de session, les règles pour les ressources protégées. Nous pouvons également étendre et personnaliser la configuration par défaut qui contient les éléments ci-dessous.
- UserDetailsService : Interface qui a pour méthode de charger l’utilisateur par « Username » et renvoie un objet UserDetails que Spring Security peut utiliser pour l’authentification et la validation
- UserDetails : contient les informations nécessaires (telles que : username, password, autorisation) pour crée un objet d’authentification.
- UsernamePasswordAuthentificationToken : obtient {username, password} à partir de la demande de connexion, AuthentificationManager l’utilisera pour authentifier un compte de connexion.
- AuthentificationManager : a un DaoAuthenificationProvider (avec l’aide de UserDetailsService & PasswordEncode) pour valider l’objet UsernamePasswordAuthentificationToken. En cas de succès, AuthentificationManager renvoie un objet Authentification entièrement rempli (y compris les autorités accordées).
- OncePerRequestFilter : fait une seule exécution pour chaque requête à notre API. Il fournit une doFilterInternal () méthode que nous allons implémenter pour analyser et valider JWT, charger les détails de l’utilisateur (à l’aide de UserDetailsService), vérifier l’autorisation (à l’aide de UsernamePasswordAuthentificationToken).
- AuthenticationEntryPoint : détectera l’erreur d’authentification.
- Repository : contient UserRepository et RoleRepository.
- Controller : reçoit et traite la demande après qu’elle a été filtrer par OncePerRequestFilter. 
	- AuthController : gère les demandes d’inscription / de connexion.
- TestController : a accès à des méthodes de ressources protégées avec des validations basées sur les rôles.


Structure du Projet : 
- Security : nous configurons Spring Security et implémentations des objets de sécurité ici. 
	- WebSecurityConfig : hérite de WebSecurityConfigurerAdapter.
	- UserDetailsServiceImpl : implémente l’interface UserDetailsService.
	- UserDetailsImpl : implémente l’interface UserDetails. 
	- AuthEntryPointJwt : implémente l’interface AuthentificationEntryPoint.
	- AuthTokenFilter : hérite de la classe OncePerRequestFilter.
	- JwtUtils : fournit des méthodes pour générer, analyser, valider JWT.
-UserServiceImpl : implémente l’interface UserService qui fournit des méthodes pour éditer/supprimer un utilisateur.
-DepenseServiceImpl : implémente l’interface DepenseService qui fournit des méthodes pour ajouter/éditer/supprimer/accepter/refuser une dépense.

- Controller : gèrent les demandes de l’inscription/de connexion et les demandes autorisées.
	- AuthController : @PostMapping(‘/signin’), @PostMapping(‘/signup’).
- TestController :  @GetMapping(‘/api/test/all’), @GetMapping(‘/api/test/employe’), @GetMapping(‘/api/test/manager’), @GetMapping(‘/api/test/admin’).
- UserController : @GetMapping(‘/api/usr/getEmploye’), @GetMapping(‘/api/usr/getManager’), @GetMapping(‘/api/usr/getAdmin’), @PutMapping(‘/api/usr/user/{userId}’), @DeleteMapping(‘/api/usr/user/{id}).
	- DepenseController :
 Pour rôle = (Employe) :
@GetMapping(‘/api/dep/depense’), @PostMapping(‘/api/dep/depense’), @PostMapping(‘/api/dep/depense/{depenseId}’), @DeleteMapping(‘/api/dep/depense/{id}’)
Pour rôle=(Manager) :
 @GetMapping(‘/api/dep/getDepense’), @GetMapping(‘/api/dep/getAcceptedDepense’), @GetMapping(‘/api/dep/getDeniedDepense’),
@PostMapping(‘/api/dep/acceptdep/{id}’), @PostMapping(‘/api/dep/denydep/{id}’).

- Dtos : sont des objets qui contiennent les données afin de les transporter entre les processus pour réduire le nombre d’appels de méthodes (UserDTO, DepenseDTO).

- Exceptions : contient les exceptions qui perturbe le flux normal des instructions du programme.

- Models : définit les entités nécessaires pour le projet 
	- User : id, username, email, password, rôles.
	- Role : id, nom.
	-Depense : id, dateDepense, client, produit_Projet, typeI, typeD, montant, status, username.

- Payload : définit les classes pour les objets Request et Response.
- Repository : contient les interfaces qui hérite Spring Data JPA JpaRepository pour interagir avec la base de données.

 
Configuration de sécurité Spring : 
 
![image](https://user-images.githubusercontent.com/83168701/187952456-2de075e9-148f-4516-8ea2-a783f1e5fe85.png)

![image](https://user-images.githubusercontent.com/83168701/187952473-a7717fe1-6919-4db9-841b-05605a4aa10a.png)


Explication du code : 

- @EnableWebSecurity permet à Spring de trouver et d’appliquer automatiquement la classe à la sécurité web global.
- @EnableGlobalMethodeSecurity assure le sécurité AOP sur les méthodes. Il permet @PreAuthorize, @PostAuthorize, il prend également en charge JSR-250.
- Redéfinition de la méthode configure (HttpSecurity http) de l’interface WebSecurityConfigurerAdapter. Elle indique à Spring Security comment nous configurons CORS et CSRF, quand nous voulons exiger que tous les utilisateurs soient authentifiés ou non
- Spring Security chargera les détails de l’utilisateur pour effectuer l’authentification et l’autorisation. Il a donc une interface UserDetailsService que nous devons implémenter.
- L’implémentation de UserDetailsService sera utilisée pour la configuration DaoAuthentificationProvider par AuthentificationManagerBuilder.userDetailsService() méthode.
-Nous avons également besoins d’un PasswordEncoder pour le DaoAuthentificationProvider.

Implémentation de UserDetails et UserDetailsService :
- UserDetails :

 ![image](https://user-images.githubusercontent.com/83168701/187952585-6464a310-e207-4e86-a3b4-7b2def410cae.png)
 ![image](https://user-images.githubusercontent.com/83168701/187952597-248e8fc9-3664-4787-91ac-80ab38804445.png)
 ![image](https://user-images.githubusercontent.com/83168701/187952631-42c3a662-cd0d-4c0f-9623-ca07bdb576e0.png) 

On remarque dans le code ci-dessus que nous convertissons Set<Role> en List<GranteAuthority>.Il est important de travailler avec Spring Security et Authentication.
-UserDetailsService :
Comme nous avons déjà dit, nous avons besoin UserDetailsService afin d’obtenir un UserDetails objet.
L’interface UserDetailsService n’a qu’une seule méthode nommée loadUserByUsername nous allons donc redéfinir cette méthode dans UserDetailsServiceImpl
 
![image](https://user-images.githubusercontent.com/83168701/187952691-04319f63-9092-4851-8d27-b93770daed29.png)

Dans le code ci-dessus, nous obtenons un objet utilisateur entièrement personnalisé à l’aide de UserRepository, puis nous construisons un UserDetails objet à l’aide de la méthode build ().

Filtration des demandes : 

Définissions un filtre qui s’exécute une fois par requête. Nous créons donc une classe AuthTokenFilter qui hérite OncePerRequestFilter et remplace la doFilterInternal() méthode.

Ce que nous faisons à l'intérieur doFilterInternal() :
- obtenir JWT de l'en-tête Authorization (en supprimant le Bearer préfixe)
- si la requête a JWT, la valider, l'analyser - à partir de username, créer un objet - définir le courant dans SecurityContext à l' aide de la méthode. 
Username UserDetails Authentication
UserDetails setAuthentication(authentication).

Création de la classe JWTUtils :
 
Cette classe a 3 fonctions : 

- générer un JWT username, date,expiration,secret : 
 
- obtenir le nom de l’utilisateur de JWT :
 
- valider un JWT : 
 

On ne doit pas oublier d’ajouter « gestionoteback.app.jwtSecret » et « gestionotback.app.jwtExpirationMs » propriétés dans le fichier application.properties .
 

Gestion de l’exception d’authentification :

Maintenant on doit crée AuthEntryPointJwt classe qui implémente l’interface AuthenticationEntryPoint . Ensuite , on doit redéfinir la méthode commence().Cette méthode sera déclenchée chaque fois qu’un utilisateur non authentifié demandera une ressource http sécurisée .
 
HttpServletResponse.SC_UNAUTHORIZED est le code d'état 401 . Il indique que la requête nécessite une authentification HTTP.
Nous avons déjà tout construit pour Spring Security. Les sections suivantes de ce didacticiel vous montreront comment implémenter des contrôleurs pour nos RestAPI.
Création des contrôleurs Spring RestAPIs :
- Contrôleur d’authentification :
Ce contrôleur fournit des API pour les actions d'enregistrement et de connexion.
–/api/auth/signup
	vérifier existant username/email
	créer un nouveau User(avec ROLE_USER si le rôle n'est pas spécifié)
	enregistrer User dans la base de données en utilisant UserRepository
 
–/api/auth/signin
	authentifier { nom d'utilisateur, mot de passe }
	mise à jour SecurityContext à l'aide d' Authentication un objet
	produire JWT
	obtenir UserDetails de l' Authentication objet
	la réponse contient JWT et UserDetails les données

- Contrôleur pour tester l’autorisation : 
Il existe 4 API :
– /api/test/all pour l'accès public
– /api/test/employe pour les utilisateurs qui ont le rôle ROLE_EMPLOYE
– /api/test/manager pour les utilisateurs qui ont le rôle ROLE_MANAGER
– /api/test/admin pour les utilisateurs qui ont le rôle ROLE_ADMIN
 
- Contrôleur UserController (ROLE_ADMIN) : 
Il existe 6 API :
– /api/usr/getEmploye pour affichage de la liste des utilisateurs qui ont le rôle ROLE_EMPLOYE
– /api/usr/getManager pour affichage de la liste des utilisateurs qui ont le rôle ROLE_MANAGER
– /api/usr/getAdmin pour affichage de la liste des utilisateurs qui ont le rôle ROLE_Admin
– /api/usr/user/search pour filtrage de la liste des utilisateurs par username
– /api/usr/user/{userId} pour éditer les informations d’un utilisateur
– /api/usr/user/{id} pour supprimer un utilisateur	
  
- Contrôleur DepenseController (ROLE_EMPLOYE) : 
Il existe 5 API :
– /api/dep/depenses pour affichage de la liste des dépenses selon l’utilisateurs connecter
– /api/dep/depense/search pour filtrage de la liste des depenses par status
– /api/dep/depense pour ajouter une depense selon l’utilisateur connecter
– /api/dep/depense/{depenseId}  pour modifier une depense selon l’utilisateur connecter
– /api/dep/depense/{id }  pour supprimer une depense selon l’utilisateur connecter
 
- Contrôleur DepenseController (ROLE_MANAGER) : 
Il existe 5 API :
– /api/dep/getDepense pour affichage de la liste des dépenses dont le status est {In pogress } de tous les utilisateurs qui ont comme rôle {ROLE_EMPLOYE} 
– /api/dep/getAcceptedDepense pour affichage de la liste des dépenses dont le status est {Accepted } de tous les utilisateurs qui ont comme rôle {ROLE_EMPLOYE} 
– /api/dep/getDeniedDepense pour affichage de la liste des dépenses dont le status est {Denied } de tous les utilisateurs qui ont comme rôle {ROLE_EMPLOYE} 
– /api/dep/acceptdep pour accepter les dépenses ajouter par les utilisateurs qui ont comme rôle {ROLE_EMPLOYE}
– /api/dep/denydep pour refuser les dépenses ajouter par les utilisateurs qui ont comme rôle {ROLE_EMPLOYE}
Création de la classe DepenseServiceImpl : 
Dans cette classe on implémentera les 9 fonctions de l’interface DepenseService : 
 
Quelques annotations JPA utilisées : 

@Entity : Indique que la classe est persistante est correspond à une table dans la base de données
 @Id : Associer un champ de la table à la propriété en tant que clé primaire
 @Data : Générer constructeur sans paramètre 
@Autowired : Injection Des Dépendances
 @NoArgsConstroctor : Pour ne pas générer constructeur 
@AllArgsConstructor : Générer constructeur avec paramètre
 @Transactional : Toutes Les Méthodes Sont Transactionnelles 
@Bean : Pour Exécuter Les Méthodes Au Démarrage 
@Slf4j : Loger Les Informations 
@GeneratedValue : Demander la génération automatique de la clé primaire au besoin 
@ManyToMany : Pour décrire une association plusieurs à plusieurs
@JoinedColumn : Pour décrire une clé étrangère dans une table ……. 
