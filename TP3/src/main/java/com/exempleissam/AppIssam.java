package com.exempleissam;

import com.exempleissam.UtilisateurIssam;
import com.exempleissam.SalleIssam;
import com.exempleissam.ReservationIssam;
import com.exempleissam.EquipementIssam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class AppIssam {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations-issam");

        try {
            System.out.println("\n=== Test des relations et des operations en cascade ===");
            testRelationsEtCascadeIssam(emf);

            System.out.println("\n=== Test de la suppression orpheline ===");
            testSuppressionOrphelineIssam(emf);

            System.out.println("\n=== Test de la relation ManyToMany avec Equipement ===");
            testRelationManyToManyIssam(emf);

        } finally {
            emf.close();
        }
    }

    private static void testRelationsEtCascadeIssam(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            System.out.println("Creation des entites...");

            UtilisateurIssam utilisateur = new UtilisateurIssam("ABOUSSAKINE", "Issam", "issam.aboussakine@email.com");

            SalleIssam salle = new SalleIssam("Salle Issam A1", 30);
            salle.setDescriptionSalleIssam("Salle de reunion Issam equipee d'un projecteur");

            ReservationIssam reservation = new ReservationIssam(
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(1).plusHours(2),
                    "Reunion Issam ABOUSSAKINE"
            );

            utilisateur.ajouterReservationIssam(reservation);
            salle.ajouterReservationIssam(reservation);

            em.persist(utilisateur);
            em.persist(salle);

            em.getTransaction().commit();
            System.out.println("Entites creees et liees avec succes !");

            em.clear();

            System.out.println("\nVerification des entites persistees :");
            UtilisateurIssam utilisateurPersiste = em.find(UtilisateurIssam.class, utilisateur.getIdIssam());
            System.out.println("Utilisateur : " + utilisateurPersiste.getNomIssam() + " " + utilisateurPersiste.getPrenomIssam() + " - " + utilisateurPersiste.getEmailIssam());
            System.out.println("Nombre de reservations : " + utilisateurPersiste.getListeReservationsIssam().size());

            SalleIssam sallePersistee = em.find(SalleIssam.class, salle.getIdSalleIssam());
            System.out.println("Salle : " + sallePersistee.getNomSalleIssam() + " - " + sallePersistee.getDescriptionSalleIssam());
            System.out.println("Nombre de reservations : " + sallePersistee.getListeReservationsIssam().size());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void testSuppressionOrphelineIssam(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            UtilisateurIssam utilisateur = new UtilisateurIssam("ABOUSSAKINE", "Sophie", "sophie.aboussakine@email.com");

            SalleIssam salle1 = new SalleIssam("Salle Issam B102", 20);
            em.persist(salle1);

            SalleIssam salle2 = new SalleIssam("Salle Issam C103", 15);
            em.persist(salle2);

            ReservationIssam reservation1 = new ReservationIssam(
                    LocalDateTime.now().plusDays(2),
                    LocalDateTime.now().plusDays(2).plusHours(1),
                    "Entretien Issam"
            );

            ReservationIssam reservation2 = new ReservationIssam(
                    LocalDateTime.now().plusDays(3),
                    LocalDateTime.now().plusDays(3).plusHours(2),
                    "Formation Issam"
            );

            utilisateur.ajouterReservationIssam(reservation1);
            utilisateur.ajouterReservationIssam(reservation2);
            salle1.ajouterReservationIssam(reservation1);
            salle2.ajouterReservationIssam(reservation2);

            em.persist(utilisateur);

            em.getTransaction().commit();
            System.out.println("Utilisateur avec deux reservations cree !");

            em.getTransaction().begin();

            UtilisateurIssam utilisateurAModifier = em.find(UtilisateurIssam.class, utilisateur.getIdIssam());
            System.out.println("Nombre de reservations avant suppression : " + utilisateurAModifier.getListeReservationsIssam().size());

            ReservationIssam reservationASupprimer = utilisateurAModifier.getListeReservationsIssam().get(0);
            utilisateurAModifier.supprimerReservationIssam(reservationASupprimer);

            em.getTransaction().commit();

            em.clear();
            UtilisateurIssam utilisateurApresModification = em.find(UtilisateurIssam.class, utilisateur.getIdIssam());
            System.out.println("Nombre de reservations apres suppression : " + utilisateurApresModification.getListeReservationsIssam().size());

            Long reservationId = reservationASupprimer.getIdReservationIssam();
            ReservationIssam reservationSupprimee = em.find(ReservationIssam.class, reservationId);
            System.out.println("La reservation existe-t-elle encore ? " + (reservationSupprimee != null));

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void testRelationManyToManyIssam(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            EquipementIssam projecteurIssam = new EquipementIssam("ProjecteurIssam HD", "Projecteur haute definition de Issam");
            EquipementIssam ecranIssam = new EquipementIssam("EcranIssam interactif", "Ecran tactile 65 pouces de Issam");
            EquipementIssam visioIssam = new EquipementIssam("VisioIssam", "Systeme de visioconference de Issam avec camera HD");

            SalleIssam salleReunionIssam = new SalleIssam("Salle Reunion Issam D104", 25);
            SalleIssam salleFormationIssam = new SalleIssam("Salle Formation Issam E205", 40);

            salleReunionIssam.ajouterEquipementIssam(projecteurIssam);
            salleReunionIssam.ajouterEquipementIssam(visioIssam);

            salleFormationIssam.ajouterEquipementIssam(projecteurIssam);
            salleFormationIssam.ajouterEquipementIssam(ecranIssam);

            em.persist(salleReunionIssam);
            em.persist(salleFormationIssam);

            em.getTransaction().commit();
            System.out.println("Salles et equipements Issam crees avec succes !");

            em.clear();

            System.out.println("\nVerification des relations ManyToMany :");

            SalleIssam salleReunionPersistee = em.find(SalleIssam.class, salleReunionIssam.getIdSalleIssam());
            System.out.println("Salle : " + salleReunionPersistee.getNomSalleIssam());
            System.out.println("Equipements :");
            for (EquipementIssam equipement : salleReunionPersistee.getEquipementsIssam()) {
                System.out.println("- " + equipement.getDesignationIssam() + " : " + equipement.getDetailsIssam());
            }

            SalleIssam salleFormationPersistee = em.find(SalleIssam.class, salleFormationIssam.getIdSalleIssam());
            System.out.println("\nSalle : " + salleFormationPersistee.getNomSalleIssam());
            System.out.println("Equipements :");
            for (EquipementIssam equipement : salleFormationPersistee.getEquipementsIssam()) {
                System.out.println("- " + equipement.getDesignationIssam() + " : " + equipement.getDetailsIssam());
            }

            EquipementIssam projecteurPersiste = em.createQuery(
                            "SELECT e FROM EquipementIssam e WHERE e.designationIssam = :nom", EquipementIssam.class)
                    .setParameter("nom", "ProjecteurIssam HD")
                    .getSingleResult();

            System.out.println("\nEquipement : " + projecteurPersiste.getDesignationIssam());
            System.out.println("Salles equipees :");
            for (SalleIssam salle : projecteurPersiste.getSallesEquipeesIssam()) {
                System.out.println("- " + salle.getNomSalleIssam());
            }

            em.getTransaction().begin();

            salleReunionPersistee.supprimerEquipementIssam(projecteurPersiste);

            em.getTransaction().commit();

            em.clear();

            SalleIssam salleApresModification = em.find(SalleIssam.class, salleReunionIssam.getIdSalleIssam());
            System.out.println("\nSalle apres suppression d'un equipement : " + salleApresModification.getNomSalleIssam());
            System.out.println("Equipements restants :");
            for (EquipementIssam equipement : salleApresModification.getEquipementsIssam()) {
                System.out.println("- " + equipement.getDesignationIssam());
            }

            EquipementIssam projecteurApresModification = em.find(EquipementIssam.class, projecteurPersiste.getReferenceIssam());
            System.out.println("\nL'equipement existe-t-il encore ? " + (projecteurApresModification != null));

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}