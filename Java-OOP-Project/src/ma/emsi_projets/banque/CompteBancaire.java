package ma.emsi_projets.banque;

import java.math.BigDecimal;

public class CompteBancaire {
    private String code;
    private Personne titulaire;
    private BigDecimal solde;
    private BigDecimal decouvertAutorise;


    public CompteBancaire(String code, Personne titulaire) {
        this.code = code;
        this.titulaire = titulaire;
        this.solde = BigDecimal.ZERO;
        this.decouvertAutorise = BigDecimal.ZERO;
    }


    public CompteBancaire(String code, Personne titulaire, BigDecimal soldeInitial) {
        this(code, titulaire);
        if (soldeInitial.compareTo(BigDecimal.ZERO) > 0) {
            this.solde = soldeInitial;
        }
    }


    public void deposer(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) > 0) {
            this.solde = this.solde.add(montant);
        }
    }


    public boolean retirer(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) > 0 &&
                this.solde.add(this.decouvertAutorise).compareTo(montant) >= 0) {
            this.solde = this.solde.subtract(montant);
            return true;
        }
        return false;
    }


    public BigDecimal getSolde() {
        return solde;
    }


    public void decouvertAutorise(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) > 0) {
            this.decouvertAutorise = montant;
        }
    }


    public boolean estDebiteur() {
        return this.solde.compareTo(BigDecimal.ZERO) < 0;
    }


    public static void main(String[] args) {
        Personne titulaire = new Personne("Dupont", "Jean");
        CompteBancaire compte = new CompteBancaire("12345", titulaire);

        compte.deposer(new BigDecimal("1000.00"));
        System.out.println("Solde après dépôt : " + compte.getSolde());

        boolean retraitReussi = compte.retirer(new BigDecimal("500.00"));
        System.out.println("Retrait réussi : " + retraitReussi);
        System.out.println("Solde après retrait : " + compte.getSolde());

        compte.decouvertAutorise(new BigDecimal("200.00"));
        retraitReussi = compte.retirer(new BigDecimal("800.00"));
        System.out.println("Retrait avec découvert réussi : " + retraitReussi);
        System.out.println("Solde après retrait avec découvert : " + compte.getSolde());

        System.out.println("Le compte est débiteur : " + compte.estDebiteur());
    }
}