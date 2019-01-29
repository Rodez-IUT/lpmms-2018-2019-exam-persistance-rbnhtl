#### NOM Hortala Prénom Robin 


## LP MMS / 2018-2019 /Examen pratique / Persistance des données

Vous disposez de 2h00 pour réaliser le TP. 

- L'usage des téléphones mobiles est interdit.
- Les documents papiers sont interdits.
- Il est strictement interdit de communiquer de manière directe ou indirecte, de quelque manière que ce soit
(messagerie instantanée, SMS, forum, réseaux sociaux, communication orale, etc.) avec une personne autre
que les enseignants en charge de la surveillance.
Toute violation de cet interdit entraînera **une sanction lourde immédiate et est susceptible de poursuite
pour fraude à examen**.

### Préliminaire

Inscrivez votre nom et votre prénom en tête de ce fichier et effectuez **un push contenant cette unique modification** sur votre projet Github.

Pour cette épreuve, vous allez travailler à partir d'un repository Github contenant un projet très fortement inspiré du projet OurbusinessProject.
Pour créer et accéder à votre repository Github, vous devez vous connecter à l'adresse communiquée par votre surveillant et suivre les instructions affichées.
Votre travail s'effectuera sur votre machine local après avoir cloné le repository Github qui a été créé pour vous.
Le rendu de votre travail s'effectura via  un **_push_ unique**  de vos modifications sur votre repository Github **en fin de séance** :
- une pénalité de 5 points sera appliquée à toute personne effectuant un *push* de son projet avant la fin de la séance ;
- toute personne ayant terminé son projet avant les 2 heures allouées devra tout de même attendre la fin de l'épreuve pour effectuer son *push*.

Vous devez répondre aux questions ouvertes directement dans ce fichier là où apparaissent les commentaires **"// A COMPLETER"**.

Il est strictement interdit de modifier le code contenu dans les classes _Bootstrap_, _InitializationService_ et _OurBusinessprojectApplication_.

Il est strictement interdit de modifier le code contenu dans les classes de test.  

### Prise en main du projet

1. Naviguez dans le projet, vérifiez qu'il a bien été reconnu votre IDE comme un projet Maven. Étudiez les classes du projet.
2. Lancez l'ensemble des tests est vérifiez que tous les tests passent exceptés ceux de la classe _"ZEvaluationM2DLTest"_.

Récupérez le contenu du fichier ENONCE.md disponible à l'URL fourni par votre enseignant et insérez le à la suite de ce fichier 

### Introduction

Récupérez le contenu du fichier _"ZEvaluationLPMMSTest.java"_ disponible à l'adresse suivante et insérez le dans votre fichier  _"ZEvaluationLPMMSTest.java"_.
Étudiez le code de la classe _"ZEvaluationLPMMSTest"_.
Votre travail consistera à faire en sorte que tous les tests commentés de cette classe passent en plus de tous les autres.

### Partie 1 - Gestion améliorée de la sauvegarde des objets métiers (7 points)

Cette partie vise à évaluer votre capacité à faire un bon usage de la méthode _"EntityManager.merge(...)"_.

1. Décommentez le test _"testSaveDetachedEnterprise"_ et modifiez le code principal de votre application pour faire en sorte que le test "_testSaveDetachedEnterprise_" passe. 
Vérifiez que l'ensemble des tests passent toujours. Si ce n'est pas le cas, modifiez votre code jusqu'à obtenir l'ensemble des tests au vert.
2. La méthode *"EnterpriseProjectService.save(Project project)"* contient l'instruction *"entityManager.flush()"*. En étudiant la documentation de l'API JPA, décrivez quelle garantie apporte cette instruction pour le bon fonctionnement de la méthode.

    Selon la documentation de l'API Java, la méthode EntityManager.flush() permet de 
    synchroniser le contexte de persistance et la base de données sous jacente. Appeler
    la méthode flush() explicitement à la fin de la méthode de sauvegarde permet ainsi 
    de s'assurer que la sauvegarde des entités qu'on veut persister a bien lieu en
    synchronisant la base de données et le contexte de persistance directement dans la
    méthode de sauvegarde d'une entité.

3. Décommentez le test _"testSaveDetachedProject"_ et modifiez le code principal de votre application pour faire en sorte que le test _"testSaveDetachedProject"_ passe. 
Vérifiez que l'ensemble des tests passent toujours. Si ce n'est pas le cas, modifiez votre code jusqu'à obtenir l'ensemble des tests au vert.

 
### Partie 2. Gestion du changement d'entreprise d'un projet (6 points)

1. Décommentez le test _"testSaveOfProjectAfterEnterpriseSwitch"_ et modifiez le code principal de votre application pour faire en sorte que le test _"testSaveOfProjectAfterEnterpriseSwitch"_ passe. 
Vérifiez que l'ensemble des tests passent toujours. Si ce n'est pas le cas, modifiez votre code jusqu'à obtenir l'ensemble des tests au vert.    
2. Le test _"testSaveOfProjectAfterEnterpriseSwitch"_ contient l'assertion suivante  : _"assertThat(savedProject, is(project))"_. 
Que pouvez vous en déduire sur le comportement de la méthode "merge" ?

    Ce test nous montre que les entités renvoyées par la méthode merge sont identiques
    aux entités de l'autre contexte de persistance car une entité renvoyée par le merge
    peut être comparée à l'entité avant le merge

### Partie 3. Optimistic locking (7 points)

1. Étudiez la documentation de l'annotation _"jaxa.persistence.Version"_ de l'API JPA.
2. Décommentez le test _"testProjectsAreVersionned"_ et modifiez le code principal de votre application pour faire en sorte que le test _"testProjectsAreVersionned"_ passe. 
Vérifiez que l'ensemble des tests passent toujours. Si ce n'est pas le cas, modifiez votre code jusqu'à obtenir l'ensemble des tests au vert. 
3. Décommentez le test _"testOptimisticLockingOnConcurrentProjectModification"_ et modifiez, si nécessaire, le code principal de votre application pour faire en sorte que le test _"testOptimisticLockingOnConcurrentProjectModification"_ passe. 
Vérifiez que l'ensemble des tests passent toujours. Si ce n'est pas le cas, modifiez votre code jusqu'à obtenir l'ensemble des tests au vert.
4. Expliquez clairement, en français, ce qui se passe dans le test _"testOptimisticLockingOnConcurrentProjectModification"_.

    Dans ce test, on récupère un projet de la base de données.
    On modifie le nom de ce projet grâce à une instruction SQL directement transmise 
    à la base. En parallèle, on modifie le nom de ce projet grâce à l'EntityManager.
    Etant donné que la base de données et le contexte de persistance n'ont pas été
    synchronisés, la modification effectuée avec l'instruction SQL n'est pas effective
    dans le contexte de persistance. Ce dernier essaie donc d'effectuer une modification
    sans avoir le dernier numéro de version, lorsque ceci se produit, une exception 
    "OptimisticLockException" est lancée.

