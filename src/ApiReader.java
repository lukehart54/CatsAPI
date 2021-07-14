import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ApiReader {

    /**
     * Returns an ArrayList of BufferedImage objects w/ images pulled from
     * thecatapi.com.
     * 
     * @param count of BufferedImages
     * @return ArrayList of BufferedImage objects of random cats
     * @throws IOException if ImageIO can't read the url
     */
    public static ArrayList< BufferedImage > getRandomImages( int count,
                    JFrame frame ) throws IOException {

        String queryUrl = "https://api.thecatapi.com/v1/images/search?limit="
                        + count;
        InputStream in = new URL( queryUrl ).openStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree( in );
        ArrayList< BufferedImage > aList = new ArrayList<>();
        for ( int i = 0; i < count; i++ ) {
            try {
                URL currentURL = new URL( tree.get( i ).get( "url" ).asText() );
                HttpURLConnection connect = (HttpURLConnection) currentURL
                                .openConnection();
                connect.setRequestProperty( "User-Agent",
                                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11" );
                BufferedImage current = ImageIO
                                .read( connect.getInputStream() );
                try {
                    if ( current.getWidth() > 800 || current.getHeight() > 600
                                    || current.getWidth() < 550
                                    || current.getHeight() < 550 ) {
                        current = convertToBufferedImage(
                                        current.getScaledInstance( 650, 500,
                                                        Image.SCALE_SMOOTH ) );
                    }
                } catch ( NullPointerException e ) {
                }
                aList.add( current );
                frame.setTitle( "Loading Cat Pictures... "
                                + String.format( "%.1f",
                                                ( ( i * 1.0 / count ) * 100 ) )
                                + "%" );
                frame.revalidate();
                frame.repaint();
            } catch ( IOException e ) {
            }
        }
        HashSet< BufferedImage > set = new HashSet<>( aList );
        aList = new ArrayList<>( set );
        return aList;
    }

    /**
     * One way to convert an Image to a BufferedImage using Graphics2D to avoid a certain type of bug.
     * 
     * @param image Image object to convert to BufferedImage
     * @return BufferedImage object of image
     */
    public static BufferedImage convertToBufferedImage( Image image ) {

        BufferedImage newImage = new BufferedImage( image.getWidth( null ),
                        image.getHeight( null ), BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = newImage.createGraphics();
        g.drawImage( image, 0, 0, null );
        g.dispose();
        return newImage;
    }

    /**
     * Returns an ArrayList of Breed objects after they are populated with data
     * from JSON.
     * 
     * @return ArrayList of cat Breeds
     * @throws IOException
     */
    public static ArrayList< Breed > getCatBreeds() {

        ArrayList< Breed > breedList = new ArrayList<>();
        Breed temp;
        String apiUrl = "https://api.thecatapi.com/v1/breeds";
        try {
            InputStream in = new URL( apiUrl ).openStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree = mapper.readTree( in );
            // Variable establishment and API retrieval
            for ( int i = 0; i < tree.size() / 2 + 3; i++ ) {
                String wikiLink = "https://en.wikipedia.org/wiki/Cat\\";
                String altName = "N/A";
                String weightMetric = "N/A";
                String id = tree.get( i ).get( "id" ).asText();
                String name = tree.get( i ).get( "name" ).asText();
                String temperament = tree.get( i ).get( "temperament" )
                                .asText();
                String lifeSpan = tree.get( i ).get( "life_span" ).asText();
                String origin = tree.get( i ).get( "origin" ).asText();
                weightMetric = tree.get( i ).get( "weight" ).get( "imperial" )
                                .asText();
                String hypoAllergen = tree.get( i ).get( "hypoallergenic" )
                                .asInt() > 0 ? "Yes" : "No";
                if ( tree.get( i ).get( "wikipedia_url" ) != null ) {
                    wikiLink = tree.get( i ).get( "wikipedia_url" ).asText();
                }
                if ( tree.get( i ).get( "alt_names" ) != null && tree.get( i )
                                .get( "alt_names" ).asText().length() > 0 ) {
                    altName = tree.get( i ).get( "alt_names" ).asText();
                }
                int childFriendly = tree.get( i ).get( "child_friendly" )
                                .asInt();
                int affectionLevel = tree.get( i ).get( "affection_level" )
                                .asInt();
                int energyLevel = tree.get( i ).get( "energy_level" ).asInt();
                int socialNeeds = tree.get( i ).get( "social_needs" ).asInt();
                int shedLevel = tree.get( i ).get( "shedding_level" ).asInt();
                int dogLevel = tree.get( i ).get( "dog_friendly" ).asInt();
                int healthIssues = tree.get( i ).get( "health_issues" ).asInt();
                int intelligence = tree.get( i ).get( "intelligence" ).asInt();
                int vocalisation = tree.get( i ).get( "vocalisation" ).asInt();
                int grooming = tree.get( i ).get( "grooming" ).asInt();
                int strangerFriendly = tree.get( i ).get( "stranger_friendly" )
                                .asInt();
                temp = new Breed( id, name, temperament, lifeSpan, origin,
                                altName, weightMetric,hypoAllergen, getCatBreedImage( id ),
                                new URI( wikiLink ), affectionLevel,
                                childFriendly, energyLevel, dogLevel, shedLevel,
                                socialNeeds, healthIssues, intelligence,
                                vocalisation, grooming, strangerFriendly );
                breedList.add( temp );
            }
        } catch ( IOException e ) {
            System.out.println( "getCatBreeds IO exception" );
        } catch ( URISyntaxException e ) {
            System.out.println( "URI Mess Up" );
        }
        return breedList;
    }

    /**
     * Returns a BufferedImage of the cat image matching id.
     * 
     * @param id String of cat
     * @return image connected to id in the API
     */
    public static BufferedImage getCatBreedImage( String id ) {

        BufferedImage b = null;
        try {
            String queryUrl = "https://api.thecatapi.com/v1/images/search?breed_ids="
                            + id;
            InputStream in = new URL( queryUrl ).openStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree = mapper.readTree( in );
            URL url = new URL( tree.get( 0 ).get( "url" ).asText() );
            HttpURLConnection connect = (HttpURLConnection) url
                            .openConnection();
            connect.setRequestProperty( "User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11" );
            b = toBuffered( ImageIO.read( connect.getInputStream() )
                            .getScaledInstance( 730, 600, Image.SCALE_FAST ) );
        } catch ( IOException e ) {
            System.out.println( "getCatBreedImage IO exception" );
        }
        return b;
    }

    /**
     * Returns an ArrayList of Strings containing the names of all breeds.
     * 
     * @return ArrayList of Strings containing the names of all breeds
     */
    public static ArrayList< String > getBreedNames() {

        ArrayList< String > list = new ArrayList<>();
        try {
            String queryUrl = "https://api.thecatapi.com/v1/breeds";
            InputStream in = new URL( queryUrl ).openStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree = mapper.readTree( in );
            for ( int i = 0; i < tree.size(); i++ ) {
                list.add( tree.get( i ).get( "name" ).asText() );
            }
        } catch ( IOException e ) {
        }
        return list;
    }

    /**
     * Safely converts an Image to a BufferedImage.
     * 
     * @param image the image to convert
     * @return a new BufferedImage
     */
    public static BufferedImage toBuffered( Image image ) {

        BufferedImage newImage = new BufferedImage( image.getWidth( null ),
                        image.getHeight( null ), BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = newImage.createGraphics();
        g.drawImage( image, 0, 0, null );
        g.dispose();
        return newImage;
    }

    /**
     * Facts api reader
     * 
     * @param count number of facts
     * @return
     * @throws IOException
     */
    public static String[] getFacts( int count ) throws IOException {

        String url = "https://catfact.ninja/facts?limit=" + count;
        InputStream in;
        in = new URL( url ).openStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree( in );
        ArrayList< String > factList = new ArrayList<>();
        for ( int i = 0; i < count; i++ ) {
            try {
                factList.add( tree.get( "data" ).get( i ).get( "fact" )
                                .asText() );
            } catch ( NullPointerException e ) {
            }
        }
        return factList.toArray( new String[ factList.size() ] );
    }

}
