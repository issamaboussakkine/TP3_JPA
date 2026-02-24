package com.exempleissam;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "issam_reservations")
public class ReservationIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationIssam;

    @NotNull(message = "La date de début issam est obligatoire")
    @Column(name = "date_debut_issam", nullable = false)
    private LocalDateTime dateDebutIssam;

    @NotNull(message = "La date de fin issam est obligatoire")
    @Column(name = "date_fin_issam", nullable = false)
    private LocalDateTime dateFinIssam;

    @Size(max = 500, message = "Le motif issam ne peut pas dépasser 500 caractères")
    @Column(length = 500)
    private String motifIssam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_issam_id", nullable = false)
    private UtilisateurIssam utilisateurIssam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_issam_id", nullable = false)
    private SalleIssam salleIssam;

    // Constructeurs
    public ReservationIssam() {
    }

    public ReservationIssam(LocalDateTime dateDebutIssam, LocalDateTime dateFinIssam, String motifIssam) {
        this.dateDebutIssam = dateDebutIssam;
        this.dateFinIssam = dateFinIssam;
        this.motifIssam = motifIssam;
    }

    // Getters et Setters avec "Issam"
    public Long getIdReservationIssam() {
        return idReservationIssam;
    }

    public void setIdReservationIssam(Long idReservationIssam) {
        this.idReservationIssam = idReservationIssam;
    }

    public LocalDateTime getDateDebutIssam() {
        return dateDebutIssam;
    }

    public void setDateDebutIssam(LocalDateTime dateDebutIssam) {
        this.dateDebutIssam = dateDebutIssam;
    }

    public LocalDateTime getDateFinIssam() {
        return dateFinIssam;
    }

    public void setDateFinIssam(LocalDateTime dateFinIssam) {
        this.dateFinIssam = dateFinIssam;
    }

    public String getMotifIssam() {
        return motifIssam;
    }

    public void setMotifIssam(String motifIssam) {
        this.motifIssam = motifIssam;
    }

    public UtilisateurIssam getUtilisateurIssam() {
        return utilisateurIssam;
    }

    public void setUtilisateurIssam(UtilisateurIssam utilisateurIssam) {
        this.utilisateurIssam = utilisateurIssam;
    }

    public SalleIssam getSalleIssam() {
        return salleIssam;
    }

    public void setSalleIssam(SalleIssam salleIssam) {
        this.salleIssam = salleIssam;
    }

    @Override
    public String toString() {
        return "ReservationIssam{" +
                "idReservationIssam=" + idReservationIssam +
                ", dateDebutIssam=" + dateDebutIssam +
                ", dateFinIssam=" + dateFinIssam +
                ", motifIssam='" + motifIssam + '\'' +
                '}';
    }
}
