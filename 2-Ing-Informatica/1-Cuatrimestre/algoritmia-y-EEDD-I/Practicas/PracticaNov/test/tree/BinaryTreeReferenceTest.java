package tree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antoni
 */
public class BinaryTreeReferenceTest {
    private BinaryTree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new BinaryTreeReference();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(10);
        tree.insert(80);
    }

    /**
     * Test of isEmpty method, of class BinaryTreeReference.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        BinaryTree<Integer> instance = new BinaryTreeReference();
        assertTrue(instance.isEmpty());
        assertFalse(tree.isEmpty());
    }

    /**
     * Test of insert method, of class BinaryTreeReference.
     */
    @Test
    public void testInsertContains() {
        System.out.println("insertContains");
        assertFalse(tree.contains(25));
        tree.insert(25);
        assertTrue(tree.contains(25));
    }

    /**
     * Test of longestBranch and Insert method, of class BinaryTreeReference.
     */
    @Test
    public void testLongestBranchInsert() {
        System.out.println("longestBranchInsert");
        assertEquals(3, tree.longestBranch());
        tree.insert(25);
        assertEquals(3, tree.longestBranch());
        tree.insert(5);
        assertEquals(4, tree.longestBranch());
        tree.insert(81);
        tree.insert(82);
        tree.insert(83);
        assertEquals(5, tree.longestBranch());
    }

    /**
     * Test of getMother method, of class BinaryTreeReference.
     */
    @Test
    public void testGetMother() {
        System.out.println("getMother");
        assertNull(tree.getMother(50));
        int i = tree.getMother(30);
        assertEquals(50, i);
        i = tree.getMother(70);
        assertEquals(50,i); 
        i = tree.getMother(10);
        assertEquals(20, i);
        assertNull(tree.getMother(15));
        assertNull(tree.getMother(tree.getRoot()));
    }

    /**
     * Test of getRoot method, of class BinaryTreeReference.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        int i = tree.getRoot();
        assertEquals(50, i);
    }

    @Test
    public void testLongestBranchAfterInsertionAndRemoval() {
        System.out.println("longestBranchAfterInsertionAndRemoval");
        tree = new BinaryTreeReference<>();
        tree.insert(55);
        tree.insert(60);
        tree.insert(58);
        tree.insert(56);
        tree.insert(59);
        assertEquals(3, tree.longestBranch());
        tree.insert(57);
        assertEquals(4, tree.longestBranch());
        tree.insert(61);
        assertEquals(4, tree.longestBranch());
        tree.insert(62);
        assertEquals(4, tree.longestBranch());
    }


    @Test
    public void testGetMotherAfterMultipleInsertions() {
        System.out.println("getMotherAfterMultipleInsertions");
        tree = new BinaryTreeReference<>();
        tree.insert(55);
        tree.insert(60);
        tree.insert(58);
        tree.insert(56);
        tree.insert(59);
        tree.insert(57);

        assertEquals(60, (int) tree.getMother(58));
        assertEquals(58, (int) tree.getMother(56));
        assertEquals(56, (int) tree.getMother(57));
        assertNull(tree.getMother(55));
    }

    @Test
    public void testGetRootAfterInsertions() {
        System.out.println("getRootAfterInsertions");
        tree = new BinaryTreeReference<>();
        tree.insert(55);
        tree.insert(60);
        tree.insert(58);
        tree.insert(56);
        tree.insert(59);
        tree.insert(57);

        assertEquals(55, (int) tree.getRoot());
    }

}
