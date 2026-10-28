/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
         ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) { // Cycle detected!
            ListNode entry = head; // Put a pointer back at the head
            while (entry != slow) {
                entry = entry.next; // Move both 1 step at a time
                slow = slow.next;
            }
            return entry; // This is the start of the cycle
        }
    }
    return null;
        
    }
}