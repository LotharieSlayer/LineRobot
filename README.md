# LineRobot
Un programme qui aligne des robots positionnés aléatoirement en les faisant communiquer ensemble sans point de rendez-vous.

**Projet de groupe (IUT du Havre) : Programmation Répartie**
Problématique : Comment programmer des robots afin qu'ils s'alignent ? (peu importe le sens)

1. Concevoir un algorithme distribué
2. Coder l'algorithme
	1 robot = 1 machine (plusieurs Thread par robot sans doute...) + 1 machine pour visualiser en "temps réel" la position de tout les robots

Contraintes :
    - Tous les robots exécutent le même algorithme
	- Ils ont des identifiants non comparables
	- Tous les robots en 1 seul point n'est pas un alignement

Hypothèses :
    - Chaque robot connait la position des autres
	- Les robots peuvent communiquer (optionnel)
			 