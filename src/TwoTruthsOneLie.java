/**
 * 
 * @author Devon Boldt
 * Cat game of two truths and one lie.
 *
 */
public class TwoTruthsOneLie {

  // Creating the array for the game of two truths and one lie
  public static String[] catGameThree = new String[2];

  // Creating the cat lies manually... Has the potential for additional cat lies.
  public static String[] catLies = new String[] {"Black cats are scientifically bad luck",
      "All cats naturally hate water", "Cats always land on their feet",
      "Cats can see in complete darkness", "Cats only purr when they're happy",
      "Cats are nocturnal", "Cow's milk is good for cats", "Cats are solitary creatures",
      "Cats are low-maintenance pets", "Declawing a cat is completely safe for cats",
      "Indoor cats live much longer than outdoor cats", "Dry food is better than wet food",
      "All cats enjoy catnip"};

  // Creating cat facts manually in the same way as the cat lies array above. Also has the potential
  // for additional cat facts.
  public static String[] catFacts = new String[] {
      "Cats are believed to be the only mammals who don’t taste sweetness", "Cats are nearsighted",
      "A house cat's genome is 95.6% tiger", "Cats are supposed to have 18 toes",
      "Cats can jump up to six times their length", "Cats have more bones than humans",
      "Cats have 230 bones", "Cats have an extra organ that allows them to taste scents in the air",
      "Cats have the largest eyes relative to their head size of any mammal",
      "Male cats are more likely to be left-pawed, while female cats are more likely to be right-pawed",
      "Cats can be ambidextrous",
      "Cats who have more than the regular number of toes are called polydactyls",
      "Cats typically sleep between 12 and 16 hours a day",
      "Cats spend up to a third of their time awake grooming themselves",
      "Female cats can get pregnant as early as 4 months old",
      "Grapes and raisin can cause kidney failure in cats", "Many cats are lactose intolerant",
      "Kittens are usually spayed/neutered when they're only 8 weeks old",
      "Spaying or neutering your cats can extend your cats life by 62 percent",
      "Cats can have 10x as many vocalizations as a dog",
      "Cats find it threatening when you make direct eye contact with them",
      "Hissing is defensive rather than offensive",
      "Cats developed meowing to communicate with people",
      "When cats wag their tails, they are getting annoyed",
      "When a cat sticks their butt in anothers face, it is a sign of friendship",
      "Whiskers are a good indicator of a cat's current mood"};

  // Grabbing the cat lies and facts arrays for use in the game.
  public static int catLiesCount = catLies.length;
  public static int catFactsCount = catFacts.length;

  /**
   * @return A string array with index 0 being a lie and indexes 1 and 2 being truths
   */
  public static String[] catGameThree() {
    int lieOne = randomInt(0, catLiesCount - 1);
    int truthOne = randomInt(0, catFactsCount - 1);
    int truthTwo = randomInt(0, catFactsCount - 1);

    // A check for making sure the two truths aren't the same... if they were, it would be easy to
    // find out the lie.
    while (truthOne == truthTwo) {
      truthOne = randomInt(0, catFactsCount - 1);
      truthTwo = randomInt(0, catFactsCount - 1);
    }

    // Placing the lies and facts into the catGameThree array.
    catGameThree[0] = catLies[lieOne];
    catGameThree[1] = catFacts[truthOne];
    catGameThree[2] = catFacts[truthTwo];

    return catGameThree;
  }

  /**
   * Gets a random int between two specified values.
   * 
   * @param min The minimum value
   * @param max The maximum value
   * @return A random int inbetween the two values
   */
  public static int randomInt(int min, int max) {
    int random = (int) (min + Math.random() * (max - min));
    return random;
  }

  /**
   * Main method used for testing.
   * 
   * @param args The command line arguments
   */
  /*
   * public static void main(String[] args) { System.out.println(catLiesCount);
   * System.out.println(catFactsCount); System.out.println(catGameThree[0] + " " + catGameThree[1] +
   * " " + catGameThree[2]); }
   */
}
