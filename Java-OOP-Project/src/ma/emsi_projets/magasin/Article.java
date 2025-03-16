package ma.emsi_projets.magasin;

public class Article {
    private long reference;
    private String description;
    private double prixHT;
    private int stock;


    public Article(long reference, String description, double prixHT, int stock) {
        this.reference = reference;
        this.description = description;
        this.prixHT = prixHT;
        this.stock = stock;
    }


    public void approvisionner(int nombreUnites) {
        if (nombreUnites > 0) {
            this.stock += nombreUnites;
        }
    }


    public boolean vendre(int nombreUnites) {
        if (nombreUnites > 0 && nombreUnites <= this.stock) {
            this.stock -= nombreUnites;
            return true;
        }
        return false;
    }


    public double prixTTC() {
        return this.prixHT * 1.10; // Taxe de 10%
    }


    public double prixVenteTTC(int nombreUnites) {
        return this.prixTTC() * nombreUnites;
    }


    @Override
    public String toString() {
        return "Article{" +
                "reference=" + reference +
                ", description='" + description + '\'' +
                ", prixHT=" + prixHT +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return reference == article.reference;
    }


    public static void main(String[] args) {
        Article[] articles = new Article[10];
        articles[0] = new Article(1, "Laptop", 1200.0, 5);
        articles[1] = new Article(2, "Smartphone", 800.0, 10);


        System.out.println(articles[0].toString());
        articles[0].approvisionner(3);
        System.out.println("Stock après réapprovisionnement : " + articles[0].stock);
        boolean venteReussie = articles[0].vendre(2);
        System.out.println("Vente réussie : " + venteReussie);
        System.out.println("Prix TTC : " + articles[0].prixTTC());
        System.out.println("Prix de vente TTC pour 3 unités : " + articles[0].prixVenteTTC(3));
    }
}