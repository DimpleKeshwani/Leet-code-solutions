import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Initialize a Min-Heap based on the node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        
        // Push the head node of each non-empty linked list into the heap
        for (ListNode root : lists) {
            if (root != null) {
                minHeap.add(root);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!minHeap.isEmpty()) {
            ListNode lowest = minHeap.poll();
            current.next = lowest;
            current = current.next;
            
            if (lowest.next != null) {
                minHeap.add(lowest.next);
            }
        }
        
        return dummy.next;
    }
}
