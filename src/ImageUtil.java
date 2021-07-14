import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class ImageUtil {

  /**
   * Produces a JFileChooser for the user to select directories to save edited images.
   * 
   * @param image Icon of image user wishes to save
   * @return true if successful, false if failed
   */
  public static boolean saveImage(Icon image) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        String fileName = file.getCanonicalPath();
        if (!fileName.endsWith(".png")) {
          file = new File(fileName + ".png");
        }
        ImageIO.write((BufferedImage) ((ImageIcon) image).getImage(), "png", file);
        return true;
      } catch (IOException ex) {
        System.out.println("Failed to save image!");
        return false;
      }
    } else {
      System.out.println("No file choosen!");
    }
    return false;
  }

  /**
   * Produces a JFileChooser for the user to navigate directories to find and upload images.
   * 
   * @return ImageIcon object of selected file (if possible) otherwise a default, null image
   */
  public static ImageIcon loadImageButton() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
    File file = new File("null.png");
    String fileName = "not initialized";
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      file = fileChooser.getSelectedFile();
      try {
        fileName = file.getCanonicalPath();
      } catch (IOException e) {

      }
    } else {
      System.out.println("No file choosen!");
    }

    if (!fileName.endsWith(".png")) {
      System.out.println("INVALID FILE TYPE");
      try {
        return new ImageIcon(ImageIO.read(new URL(
            "https://e7.pngegg.com/pngimages/448/421/png-clipart-empty-set-mathematical-notation-element-null-set-between-rim-union.png")));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println("Something went wrong reading null png");
        return null;
      }
    }

    try {
      return new ImageIcon(ImageIO.read(file));
    } catch (IOException e) {
      System.out.println("Something went wrong");
      return null;
    }
  }

}

