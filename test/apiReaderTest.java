import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

/**
 * Unit Test for ApiReader
 * 
 * @author Mason Marker, Devon Boldt, Marko Hine, Luke Hartley
 * @version 1.0
 */
class apiReaderTest {

    @Test
    void testLoadImageUtil() {

        ImageIcon temp = null;
        temp = ImageUtil.loadImageButton();
        assertNotNull( temp );
    }

    @Test
    void testGetBreedNamesOnly() {

        ArrayList< String > list = new ArrayList<>();
        list = ApiReader.getBreedNames();
        assertFalse( list.size() == 0 );
    }

    @Test
    void testGetFactsSizeFive() throws IOException {

        String[] factList = null;
        factList = ApiReader.getFacts( 5 );
        assertEquals( 5, factList.length );
    }

    @Test
    void testBreedGetMethods() {

        ArrayList< Breed > breedList = new ArrayList<>();
        breedList = ApiReader.getCatBreeds();
        Breed testBreed = breedList.get( 0 );
        assertEquals( "abys", testBreed.getID() );
        assertEquals( "Abyssinian", testBreed.getName() );
        assertEquals( "Active, Energetic, Independent, Intelligent, Gentle",
                        testBreed.getTemperament() );
        assertEquals( "14 - 15", testBreed.getLifeSpan() );
        assertEquals( "Egypt", testBreed.getOrigin() );
        assertEquals( "N/A", testBreed.getAltName() );
        assertEquals( 5, testBreed.getAffectionLevel() );
        assertEquals( 3, testBreed.getChildFriendly() );
        assertEquals( "Abyssinian", testBreed.toString() );
    }

    @Test
    void testCatGamesThree() {

        String[] list = null;
        assertNull( list );
        list = TwoTruthsOneLie.catGameThree();
        assertNotNull( list );
    }

    @Test
    void testGetBreedList() {

        ArrayList< Breed > breedList = new ArrayList<>();
        breedList = ApiReader.getCatBreeds();
        assertNotNull( breedList );
    }

    @Test
    void testGetBreedListCorrectFirstBreed() {

        ArrayList< Breed > breedList = new ArrayList<>();
        breedList = ApiReader.getCatBreeds();
        assertEquals( "abys", breedList.get( 0 ).getID() );
    }

    @Test
    void testImagesArrayNotNull() throws IOException {

        ArrayList< BufferedImage > images = new ArrayList<>();
        images = ApiReader.getRandomImages( 10, new JFrame() );
        assertNotNull( images );
    }

    @Test
    void testGetRandomImagesSizeZero() throws IOException {

        ArrayList< BufferedImage > images = new ArrayList<>();
        images = ApiReader.getRandomImages( 0, new JFrame() );
        assertEquals( 0, images.size() );
    }

    @Test
    void testGetRandomImagesSize() throws IOException {

        ArrayList< BufferedImage > images = new ArrayList<>();
        images = ApiReader.getRandomImages( 10, new JFrame() );
        assertEquals( 10, images.size() );
    }

    @Test
    void testImagesAreDifferent() throws IOException {

        ArrayList< BufferedImage > images = new ArrayList<>();
        ArrayList< BufferedImage > images2 = new ArrayList<>();
        images = ApiReader.getRandomImages( 10, new JFrame() );
        images2 = ApiReader.getRandomImages( 10, new JFrame() );
        assertFalse( images.get( 0 ).equals( images2.get( 0 ) ) );
    }

    @Test
    void testConvertToBufferedImage() throws IOException {

        ArrayList< BufferedImage > images = new ArrayList<>();
        images = ApiReader.getRandomImages( 10, new JFrame() );
        assertTrue( images.get( 0 ) instanceof BufferedImage );
    }

}
