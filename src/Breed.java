import java.awt.image.BufferedImage;
import java.net.URI;

/**
 * Cat Breed Object Class.
 * 
 * @author Luke Hartley
 *
 */
public class Breed {

    private final String ID;
    private final String name;
    private final String temperament;
    private final String lifeSpan;
    private final String origin;
    private final String altName;
    private final String weightMetric;
    private final String hypoAllergen;
    private BufferedImage image;
    private final URI wikiLink;
    private final int affectionLevel;
    private final int childFriendly;
    private final int energyLevel;
    private final int dogLevel;
    private final int shedLevel;
    private final int socialNeeds;
    private final int healthIssues;
    private final int intelligence;
    private final int vocalisation;
    private final int grooming;
    private final int strangerFriendly;

    /**
     * Breed Constructor.
     * 
     * @param ID cat id - for getting image
     * @param name - cat name
     * @param temperament - info about cat like friendliness
     * @param lifeSpan - average life span
     * @param origin - country of origin of cat
     */
    public Breed( String ID, String name, String temperament, String lifeSpan,
                    String origin, String altName, String weightMetric, String hypoAllergen,
                    BufferedImage image, URI wikiLink, int affectionLevel,
                    int childFriendly, int energyLevel, int dogLevel,
                    int shedLevel, int socialNeeds, int healthIssues,
                    int intelligence, int vocalisation, int grooming,
                    int strangerFriendly ) {

        this.hypoAllergen = hypoAllergen;
        this.grooming = grooming;
        this.intelligence = intelligence;
        this.strangerFriendly = strangerFriendly;
        this.vocalisation = vocalisation;
        this.weightMetric = weightMetric;
        this.altName = altName;
        this.healthIssues = healthIssues;
        this.dogLevel = dogLevel;
        this.shedLevel = shedLevel;
        this.socialNeeds = socialNeeds;
        this.childFriendly = childFriendly;
        this.energyLevel = energyLevel;
        this.affectionLevel = affectionLevel;
        this.image = image;
        this.ID = ID;
        this.name = name;
        this.temperament = temperament;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.wikiLink = wikiLink;

    }

    /**
     * @return the iD
     */
    public String getID() {

        return ID;
    }

    /**
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * @return the temperament
     */
    public String getTemperament() {

        return temperament;
    }

    /**
     * @return the lifeSpan
     */
    public String getLifeSpan() {

        return lifeSpan;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {

        return origin;
    }

    public String toString() {

        return name;

    }

    /**
     * @return the image
     */
    public BufferedImage getImage() {

        return image;
    }

    /**
     * @return the wikiLink
     */
    public URI getWikiLink() {

        return wikiLink;
    }

    /**
     * @return the affectionLevel
     */
    public int getAffectionLevel() {

        return affectionLevel;
    }

    /**
     * @return the childFriendly
     */
    public int getChildFriendly() {

        return childFriendly;
    }

    /**
     * @return the energyLevel
     */
    public int getEnergyLevel() {

        return energyLevel;
    }

    /**
     * @return the healthIssues
     */
    public int getHealthIssues() {

        return healthIssues;
    }

    /**
     * @return the socialNeeds
     */
    public int getSocialNeeds() {

        return socialNeeds;
    }

    /**
     * @return the shedLevel
     */
    public int getShedLevel() {

        return shedLevel;
    }

    /**
     * @return the dogLevel
     */
    public int getDogLevel() {

        return dogLevel;
    }

    /**
     * @return the altName
     */
    public String getAltName() {

        return altName;
    }

    /**
     * @return the weightMetric
     */
    public String getWeightMetric() {

        return weightMetric;
    }

    /**
     * @return the hypoAllergen
     */
    public String getHypoAllergen() {

        return hypoAllergen;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {

        return intelligence;
    }

    /**
     * @return the vocalisation
     */
    public int getVocalisation() {

        return vocalisation;
    }

    /**
     * @return the grooming
     */
    public int getGrooming() {

        return grooming;
    }

    /**
     * @return the strangerFriendly
     */
    public int getStrangerFriendly() {

        return strangerFriendly;
    }

}
