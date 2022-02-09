# cc1Archtecture

Le programme peut être lancer depuis la class Main ou MemberApplicationServiceTest qui est un test pré établie et donc donc midifiable a votre guise.
Le principale point d'entrée du programme qui reprsente un service d'ajout de membre est la fonction applyForMembership de la class MembershipApplicationService 
qui prend un certain nombre de paramettres qui decrivent une demande d'adhesion au service par une personne et concerne sont identité, 
des information concernant l'abbonement et sont moyen de paiement. S'il y a erreur une exception est retourné, 
sinon un objet ApplicationResultIds qui contient l'identifiant du membre et du paiement ajouté est renvoyé.

v2 on passe à l'api spring !

localhost:8080/user -> creer un utilisateur
localhost:8080/user?id=1 -> récupère 'user dont l'id est 1
localhost:8080/user/username + {"userId":1,"userName":"test2"} -> change le nom d'utilisateur de l'utilisateur dont l'id est 1
localhost:8080/user/userpassword + {"userId":1,"password":"test2"} -> change le mot de passe de l'utilisateur dont l'id est 1
localhost:8080/user/all -> récupère tout les utilisateurs

localhost:8080/member/apply +
{
	"userId":1,
	"billingInformationsRequest":
	{
		"cardNumber":"bidule",
		"expirationDate":"bidule",
		"secretPictogram":"bidule",
		"ownerName":"bidule"
	},
	"membershipDetailsRequest":{
		"applictionPrice":10,
		"membershipDuration":30
	},
	"applicationDate":"2021-12-30"
}
-> incrit au programme membre l'utilisateur d'id 1
localhost:8080/member/renew?id=1 -> renouvelle le l'abonnement de l'user d'id 1lorsque celui-ci à pris fin
localhost:8080/member/all -> recupere tout les abonnements
localhost:8080/member?id=1 -> recupère un abonnement par id
localhost:8080/member/stopautomaticrenew?id=1 -> bloque le reouvellement automatique de l'abonnement de l'user d'id 1
