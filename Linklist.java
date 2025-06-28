public class Linklist {
    ListNode head;

    public Linklist(int data){
        this.head = new ListNode(data);
    }

    public Linklist(){
        this.head = null;
    }

    //add node in the end of linklist
    public void add(int data){
        //special condition
        if(head == null){
            head = new ListNode(data);
        }else{
            ListNode current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new ListNode(data);
        }
    }

    //if we want to add exist node to the linklist
    public void add(ListNode node){
        if(head == null){
            head = node;
        }
        ListNode current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = node;
    }
    //print the list
    public void print(){
        ListNode current = head;
        while(current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.print("null");
    }

    //remove the nodes which has data as parameter we enter
   public void rmParam(int param){
        if(head == null) return;
        //if head should be removed
        while(head.data == param){
            head = head.next;
        }
        //normal
        ListNode current = head;
        //always delete the next node(if it should be removed)
        while(current.next != null){
            if(current.next.data == param){
                //remove the next node
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }
   }

   //reverse the linklist
   public void reverse(){
        if(head == null || head.next == null) return;
        //normal
        ListNode current = head;
        ListNode prev = null;
        while(current != null){
            //create nxt to record next node avoiding missing it
            ListNode nxt = current.next;
            current.next = prev;
            //updates prev and current
            prev = current;
            current = nxt;
        }
        //updates head
        head = prev;
   }

   //find the middle node in linklist
   //for example: 1->2->null, mid = 2 and 1->2->3->null, mid = 2
   public static ListNode findMid(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
   }

   //merge: making a sorting linklist based on two given sorting linklist
   public static Linklist merge(Linklist l1, Linklist l2){
        if(l1.head == null) return l2;
        if(l2.head == null) return l1;
        //create merge list with dummy -1
        Linklist merge = new Linklist(-1);
        ListNode cur1 = l1.head;
        ListNode cur2 = l2.head;
        ListNode cur3 = merge.head;
        while(cur1 != null && cur2 != null){
            if(cur1.data <= cur2.data){
                cur3.next = new ListNode(cur1.data);
                cur1 = cur1.next;
                cur3 = cur3.next;
            }else{
                cur3.next = new ListNode(cur2.data);
                cur2 = cur2.next;
                cur3 = cur3.next;
            }
        }
        //add leave nodes directly
       while(cur1 != null){
           cur3.next = new ListNode(cur1.data);
           cur1 = cur1.next;
           cur3 = cur3.next;
       }
       while(cur2 != null){
           cur3.next = new ListNode(cur2.data);
           cur2 = cur2.next;
           cur3 = cur3.next;
       }
       //delete dummy
       merge.head = merge.head.next;
       return merge;
   }

   //combine merge and findMid to finish mergesort
   public static Linklist mergesort(Linklist list){
        if(list.head == null || list.head.next == null) return list;
        //divide based on mid
        ListNode mid = findMid(list.head);
        Linklist left = new Linklist();
        Linklist right = new Linklist();
        ListNode current = list.head;
        //divide into two parts
        while(current != mid){
            left.add(current.data);
            current = current.next;
        }
        while(current != null){
            right.add(current.data);
            current = current.next;
        }
        //merge by recursion
        Linklist leftSort = mergesort(left);
        Linklist rightSort = mergesort(right);
        return merge(leftSort,rightSort);
   }

   //move odd in the front of list
   public static Linklist oddFront(Linklist list){
        if(list.head == null || list.head.next == null) return list;
        Linklist odd = new Linklist();
        Linklist even = new Linklist();
        ListNode current = list.head;
        //divide list to odd part and even part
        while(current != null){
            if(current.data % 2 != 0){
                odd.add(current.data);
                current = current.next;
            }else{
                even.add(current.data);
                current = current.next;
            }
        }
        //add even to the end of odd part
        current = even.head;
        while(current != null){
            odd.add(current.data);
            current = current.next;
        }
        return odd;
   }
   //resort list: divide list to left and right based on mid, reverse right part and finally connect each other
   //example:1->2->3->4->5 => 1->2->3 and 4->5 => 1->2->3 and 5->4 => 1->5->2->4->3
   public static Linklist resort(Linklist list){
        if(list.head == null || list.head.next == null) return list;
        ListNode mid = findMid(list.head);
        //spilt
        Linklist right = new Linklist();
        right.head = mid.next;
        //break point avoiding unstoppable loop
        mid.next = null;
        Linklist left = new Linklist();
        left.head = list.head;
        //reverse part
        right.reverse();
        //connection part
        ListNode first = left.head;
        ListNode second = right.head;
        //based on findMid, we know left is always larger than right
        //therefore second will run out first
        while(second != null){
            //record next node
            ListNode nxtFirst = first.next;
            ListNode nxtSecond = second.next;
            //connect
            first.next = second;
            second.next = nxtFirst;
            //updates
            first = nxtFirst;
            second = nxtSecond;
        }
        return left;
   }

   //find a common node for two linklist
   public static ListNode commonNode(Linklist l1, Linklist l2){
        if(l1.head == null || l2.head == null) return null;
        ListNode cur1 = l1.head;
        ListNode cur2 = l2.head;
        //when cur1 meet cur2 that node is common node
        while(cur1 != cur2){
            cur1 = (cur1 == null)? l2.head : cur1.next;
            cur2 = (cur2 == null)? l1.head : cur2.next;
        }
        return cur1;
   }

   //judge whether linklist is ring or not
   public static boolean isRing(Linklist list){
        if(list.head == null || list.head.next == null) return false;
        //fast and slow start at different node in order to get in loop
        ListNode fast = list.head.next;
        ListNode slow = list.head;
        while(fast != slow){
            if(fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
   }

   //find the ring entry node
   public static ListNode ringEntryNode(Linklist list){
        if(!isRing(list)) return null;
        ListNode fast = list.head.next;
        ListNode slow = list.head;
        //first round
        while(fast != slow){
            //slow must go first
            slow = slow.next;
            fast = fast.next.next;
        }
        //reset fast
        fast = list.head;
        slow = slow.next;
        //second round
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
   }

   //reverse by couple : 1->2->3->4->5 => 2->1->4->3->5
   public static Linklist reverseByCouple(Linklist list){
        if(list.head == null || list.head.next == null) return list;
        //set dummy to record head
        ListNode dummy = new ListNode(-1);
        dummy.next = list.head;
        //set pointer
        ListNode prev = dummy;
        while(prev.next != null && prev.next.next != null){
            ListNode current = prev.next;
            ListNode nxt = prev.next.next;
            //connect
            prev.next = nxt;
            current.next = nxt.next;
            nxt.next = current;
            //updates prev
            prev = current;
        }
        Linklist res = new Linklist();
        res.head = dummy.next;
        return res;
   }

   //reverse depend on k
   //example: if k=2 reverse by couple, if k=3 then 1->2->3->4 => 3->2->1->4
   //updates reverse method which can help us reverse only based on given head and tail
   public static ListNode[] reverse(ListNode head, ListNode tail){
       //reverse the whole linklist
       ListNode prev = null;
       ListNode current = head;
       while(current != null){
           ListNode nxt = current.next;
           current.next = prev;
           prev = current;
           current = nxt;
       }
       //now 1->2->3 => 1<-2<-3, what we need to do is to return new "head" and "tail"
       return new ListNode[]{tail,head};
   }
   public static Linklist reverseByK(Linklist list, int k){
        if(list.head == null || list.head.next == null) return list;
        if(k<2) return list;
        Linklist res = new Linklist();
        //set dummy to record head
        ListNode dummy = new ListNode(-1);
        dummy.next = list.head;
        ListNode prev = dummy;
        while(prev.next != null && prev.next.next != null){
            ListNode tail = prev;
            ListNode head = prev.next;
            //find tail depend on k
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                //if remain is not enough
                if(tail == null){
                    res.head = dummy.next;
                    return res;
                }
            }
            //record next node
            ListNode nxt = tail.next;
            //break head and tail
            prev.next = null;
            tail.next = null;
            //reverse
            ListNode[] reverse = reverse(head,tail);
            head = reverse[0];
            tail = reverse[1];
            //connect
            prev.next = head;
            tail.next = nxt;
            //updates
            prev = tail;
        }
        res.head = dummy.next;
        return res;
   }

   public static void main(String[] args){

   }
}
