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
        while(current != null){
            if(current.data % 2 != 0){
                odd.add(current.data);
                current = current.next;
            }else{
                even.add(current.data);
                current = current.next;
            }
        }
        current = even.head;
        while(current != null){
            odd.add(current.data);
            current = current.next;
        }
        return odd;
   }
   public static void main(String[] args){

   }
}
