import java.util.LinkedList;

class BT {
    private BTNode root;

    public void insert(String club, LinkedList<Booking> bookings) {
        root = insert(root, club, bookings);
    }

    private BTNode insert(BTNode node, String club, LinkedList<Booking> bookings) {
        if (node == null) return new BTNode(club, bookings);
        if (node.right == null) node.right = insert(node.right, club, bookings);
        else node.left = insert(node.left, club, bookings);
        return node;
    }

    public boolean search(String club) {
        return search(root, club);
    }

    private boolean search(BTNode node, String club) {
        if (node == null) return false;
        if (node.getClub().equals(club)) return true;
        return search(node.left, club) || search(node.right, club);
    }

    public BTNode getTargetNode(String club) {
        return getTargetNode(root, club);
    }

    private BTNode getTargetNode(BTNode node, String club) {
        if (node == null) return null;
        if (node.getClub().equals(club)) return node;
        BTNode left = getTargetNode(node.left, club);
        return (left != null) ? left : getTargetNode(node.right, club);
    }
}
