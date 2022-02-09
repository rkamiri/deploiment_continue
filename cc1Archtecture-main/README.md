# cc1Archtecture

Le programme peut être lancer depuis la class Main ou MemberApplicationServiceTest qui est un test pré établie et donc donc midifiable a votre guise.
Le principale point d'entrée du programme qui reprsente un service d'ajout de membre est la fonction applyForMembership de la class MembershipApplicationService 
qui prend un certain nombre de paramettres qui decrivent une demande d'adhesion au service par une personne et concerne sont identité, 
des information concernant l'abbonement et sont moyen de paiement. S'il y a erreur une exception est retourné, 
sinon un objet ApplicationResultIds qui contient l'identifiant du membre et du paiement ajouté est renvoyé.