1. **Phase d'initialisation**

Sur le screen shot ci-dessous, nous pouvons y apercevoir l'index sur quatre navigateurs différents. 

![Image 1 Index](images/Index.png)

2. **Mise à jouer de l'index**

Sur le screen shot ci-dessous, cette fois ci le premier navigateur a créé une partie tandis que les trois autres, en actualisant leur page, nous pouvons apercevoir un nouveau bouton "rejoindre une partie"

![Image 2 maj](images/image2.png)

3. **Choix de la partie**

Sur le screen shot ci-dessous, les trois navigateurs ayant fait le choix de rejoindre une partie, se retrouvent sur la page où ils doivent choisir l'identifiant de la partie souhaitée. (Parfois il est nécessaire de cliquer sur la liste déroulante, afin de choisir une partie spécifique) 

![Image 3 ChoixPartie](images/image3.png)

4. **Choix d'équipe et de rôle**

Sur le screen shot ci-dessous, nous sommes dans la phase de "préparation de partie", les joueurs y décideront leur équipe et leur rôle. Néanmoins il faut se méfier de la liste déroulante car parfois elle n'affiche pas le pseudo en tête de liste, cela implique qu'on puisse penser que notre choix ne s'est pas fait. Donc il vous sera nécessaire de cliquer afin de voir toute la liste. 

![Image 4 PreparationPartie](images/image4.png)

5. **Lancement de la partie**

Sur le screen shot ci-dessous, nous remarquons le boutons "Lancer la partie" n'est disponible que lorsqu'il y a au moins quatre joueurs dans la partie, mais également au moins un joueur par rôle, et que vous êtes créateur de la partie en cours. Donc sur le screen nous voyons à gauche le navigateur du créateur et à droite d'un autre joueur. Cependant ce projet n'est pas compatible pour jouer à plus de quatre joueurs et plus de deux par rôle.  

![Image 5 LancementPartie](images/image5.png)

6. **Partie**

Sur le screen shot ci-dessous bien que pas très élégant du aux quatre fenêtres, montre que les décodeurs ont leurs cartes grisées (les deux fenêtes à gauche), tandis que les espions ont toutes les cartes avec leur couleur. Nous remarquons également que c'est au tour de l'espion de l'equipe rouge ("zorane1") de jouer car c'est la première manche et que l'équipe rouge a le plus de mot à trouver (neuf mots), donc la première à jouer. 

![Image 6 Partie](images/image6.png)

7. **Première manche**

Sur le screen shot ci-dessous nous rentrons plus en détails concernant la première manche. Comme c'est la première manche le score des deux équipes est à null comme indiqué en haut à droite et gauche. Il n'y a pas d'indice pour l'instant donc initialisé à null également. En dessous de la grille des cartes nous avons le champ où l'on doit indiquer notre indice (indice + nombre de mots associés) et nous envoyons l'indice en appuyant sur le boutton envoyer. Néanmoins aucune vérfication n'est faite sur l'indice, cette dernière est laissée à la bonne foi et au fair play des joueurs. 

![Image 7 PremièreManche](images/image7.png)

8. **Deuxième manche**

Sur le screen shot ci-dessous nous voyons la manche du décodeur de l'équipe rouge qui a d'ores et déjà fait deux choix qui sont avérés, donc ces derniers se sont mis à jour en montrant leur couleur à tous les décodeurs de la partie. Le score de l'équipe rouge s'est également incrémenté de deux comme on peut l'apercevoir en haut à gauche. Et nous retrouvons bien entendu l'indice de notre espion en haut de la page, juste au dessus de la grille.

![Image 8 deuxièmeManche](images/image8.png)

9. **Affichage du vainqueur**

Sur les deux screen shot ci-dessous nous retrovons l'affichage de l'équipe gagnante. Rouge si le décodeur de l'équipe bleue a choisi la carte interdite ou le décodeur de l'équipe rouge a trouvé toutes les cartes rouges. 
Bleu si le décodeur de l'équipe rouge a choisi la carte interdite ou le décodeur de l'équipe bleue a trouvé toutes les cartes bleues. 

![Image 9 VainqueurRouge](images/image9.png)
![Image 9 VainqueurBleu](images/image10.png)



10. **Ps**

On retrouvera certaines complications avec certains navigateurs (pour mon cas google Chrome), où il sera nécessaire de cliquer plusieurs fois pour valider notre choix, notamment lorsqu'on choisi le rôle voulu. Parfois il sera également nécessaire d'actualiser la page du créateur si le bouton "lancer la partie" n'est toujours pas cliquable bien que toutes les conditions sont réunies. Et lors du déroulement de la partie il est fortement déconseillé d'actualiser manuellement les pages car cela peut créer des dysfonctionnements non souhaitées. Toutes ces anomalies sont dûes aux webSocket se chargeant d'actualiser. J'ai néanmoins laissé en commentaire la webSocket de l'index qui permet d'actualiser lorsqu'une personne crée une partie afin de voir bouton "rejoindre Partie" sans avoir à actualiser manuellement, car cette dernière engendrait trop de problèmes. 



