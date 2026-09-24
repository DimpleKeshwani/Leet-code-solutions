/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
       
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prevGroupTail = dummy;
        ListNode curr = head;
        
        while (curr != null) {
           
            ListNode groupTail = prevGroupTail;
            for (int i = 0; i < k; i++) {
                groupTail = groupTail.next;
                if (groupTail == null) {
                    return dummy.next; 
                }
            }
            
           
            ListNode nextGroupHead = groupTail.next;
            
            
            ListNode prev = nextGroupHead; 
            ListNode currentGroupNode = curr;
            
            for (int i = 0; i < k; i++) {
                ListNode nextNode = currentGroupNode.next;
                currentGroupNode.next = prev;
                prev = currentGroupNode;
                currentGroupNode = nextNode;
            }
            
            
            ListNode tempTail = curr;
            prevGroupTail.next = prev;
            prevGroupTail = tempTail;
            
            curr = nextGroupHead;
        }
        
        return dummy.next;
    }
}
