
package cat.uib.eps.aas.composite;

public class MainCompositeDemo {
    public static void main(String[] args) {
        DirectoryComposite root = new DirectoryComposite("home");
        DirectoryComposite userDir = new DirectoryComposite("user");
        DirectoryComposite docsDir = new DirectoryComposite("docs");
        DirectoryComposite imgDir = new DirectoryComposite("img");
        DirectoryComposite secretDir = new DirectoryComposite("secret");
        DirectoryComposite trip1Dir = new DirectoryComposite("trip1");
        DirectoryComposite trip2Dir = new DirectoryComposite("trip2");
        docsDir.add(new FileLeaf("doc1", 100));
        docsDir.add(new FileLeaf("doc2", 50));
        docsDir.add(new FileLeaf("doc3", 75));
        secretDir.add(new FileLeaf("doc1", 420));
        secretDir.add(new FileLeaf("doc2", 529));
        docsDir.add(secretDir);
        userDir.add(docsDir);

        trip1Dir.add(new FileLeaf("img1", 3452));
        trip1Dir.add(new FileLeaf("img2", 5825));
        trip2Dir.add(new FileLeaf("img1", 5928));
        trip2Dir.add(new FileLeaf("img2", 9583));
        imgDir.add(trip1Dir);
        imgDir.add(trip2Dir);
        userDir.add(imgDir);
        root.add(userDir);
        /*
         * TODO: construiu la següent estructura de fitxers i carpetes
         * home
         *   user
         *     docs
         *       doc1  (100b)
         *       doc2  (50b)
         *       doc3  (75b)
         *       secret
         *         doc1  (420b)
         *         doc2  (529b)
         *     img
         *       trip1
         *         img1  (3452b)
         *         img2  (5825b)
         *       trip2
         *         img1  (5928b)
         *         img2  (9583b)
         */
        System.out.println("Mida total: " + root.getSize() + " bytes");
        root.printStructure("\t");
    }
}
